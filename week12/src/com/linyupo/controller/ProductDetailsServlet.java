package com.linyupo.controller;

import com.linyupo.dao.ProductDao;
import com.linyupo.model.Category;
import com.linyupo.model.Product;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "ProductDetailsServlet", value = "/productDetails")
public class ProductDetailsServlet extends HttpServlet {
    private Connection con=null;

    @Override
    public void init() throws ServletException {
        con=(Connection) getServletContext().getAttribute("con");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id=request.getParameter("id") !=null ? Integer.parseInt(request.getParameter("id")):0 ;
        ProductDao productDao=new ProductDao();
        if(id==0) {
            return;
        }
        List<Category> categoryList= null;
        try {
            categoryList = Category.findAllCategory(con);
            request.setAttribute( "categoryList", categoryList);
            Product product=productDao.findById(id , con);
            request.setAttribute("p",product);
            String path ="/WEB-INF/views/productDetails.jsp";
            request.getRequestDispatcher(path).forward(request,response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
