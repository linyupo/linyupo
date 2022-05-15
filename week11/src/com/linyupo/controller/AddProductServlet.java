package com.linyupo.controller;

import com.linyupo.dao.ProductDao;
import com.linyupo.model.Category;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "AddProductServlet", value = "/admin/addProduct")
@MultipartConfig(maxFileSize = 16177215)
public class AddProductServlet extends HttpServlet {
    Connection con=null;
    public void init(){
        con=(Connection) getServletContext().getAttribute("con");
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Category> categoryList= null;
        try {
            categoryList = Category.findAllCategory(con);
            request.setAttribute("category",categoryList);
            String path="/WEB-INF/views/admin/addProduct.jsp";
            request.getRequestDispatcher(path).forward(request,response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String productName=request.getParameter("productName");
        double price=request.getParameter("price")!=null?Double.parseDouble(request.getParameter("price")):0.0;
        int categoryId=request.getParameter("categoryId")!=null?Integer.parseInt(request.getParameter("categoryId")):0;

        String productDescription=request.getParameter("productDescription");

        InputStream inputStream=null;
        Part filePart=request.getPart("picture");
        if (filePart!=null){
            inputStream=filePart.getInputStream();
        }
        Product Product=new Product();
        Product.setProductName(productName);
        Product.setProductDescription(productDescription);
        Product.setPrice(price);
        Product.setCategoryId(categoryId);
        Product.setPicture(inputStream);

        ProductDao productDao=new ProductDao();
        try {
            int save = productDao.save(Product, con);
            if (save>0){
                response.sendRedirect("productList");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
