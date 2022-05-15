package com.linyupo.dao;


import com.linyupo.model.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDao implements  IProductDao{
    @Override
    public int save(Product product, Connection con) throws SQLException {
        int n = 0;
        String sql = "insert into product(ProductName,ProductDescription,Picture,price,CategoryID) values(?,?,?,?,?)";
        PreparedStatement pt = con.prepareStatement(sql);
        pt.setString(1, product.getProductName());
        pt.setString(2, product.getProductDescription());
        if(product.getPicture()!=null) {
            //for sql server
            pt.setBinaryStream(3, product.getPicture());
            //for mysql
            //   pt.setBlob(3, product.getPicture());
        }else pt.setBinaryStream(3, null);
        pt.setDouble(4, product.getPrice());
        pt.setInt(5, product.getCategoryId());
        n = pt.executeUpdate();
        if (n > 0) {
            return n;
        }
        return 0;
    }//end save



    @Override
    public int delete(Integer productId, Connection con) throws SQLException {
        String sql="delete from Product where productId='"+productId+"'";
        PreparedStatement pt=con.prepareStatement(sql);
        int rs=pt.executeUpdate();
        return rs;

    }

    @Override
    public int update(Product instance, Connection con) throws SQLException{
        String sql="update Product set productName='"+instance.getProductName()+"',productDescription='"+instance.getProductDescription()+"',picture='"+instance.getPicture()+"',price='"+instance.getPrice()+"',categoryId='"+instance.getCategoryId()+"' ";
        PreparedStatement pt=con.prepareStatement(sql);
        int rs=pt.executeUpdate();
        return rs;
    }


    @Override
    public Product findById(Integer productId, Connection con) throws SQLException{
        Product p=null;
        String sql="select *from Product where productId='"+productId+"' ";
        PreparedStatement pt=con.prepareStatement(sql);
        ResultSet rs=pt.executeQuery();
        if(rs.next()){
            p=new Product(rs.getInt("productId"),rs.getString("productName"),rs.getString("productDescription"),rs.getBinaryStream("picture"),rs.getDouble("price"),rs.getInt("CategoryId"));
        }
        return p;
    }

    @Override
    public List<Product> findByCategoryId(int categoryId, Connection con)throws SQLException {
        List<Product>p=new ArrayList<>();
        String sql="select *from Product where categoryId='"+categoryId+"' ";
        PreparedStatement pt=con.prepareStatement(sql);
        ResultSet rs=pt.executeQuery();
        while(rs.next()){
            Product p2=new Product(rs.getInt("productId"),rs.getString("productName"),rs.getString("productDescription"),rs.getBinaryStream("picture"),rs.getDouble("price"),rs.getInt("CategoryId"));
            p.add(p2);
        }
        return p;
    }


    @Override
    public List<Product> findByPrice(double minPrice, double maxPrice, Connection con) throws SQLException {
        List<Product>p=new ArrayList<>();
        String sql="select *from Product where price between '"+minPrice+"' and '"+maxPrice+"' ";
        PreparedStatement pt=con.prepareStatement(sql);
        ResultSet rs=pt.executeQuery();
        while(rs.next()){
            Product p2=new Product(rs.getInt("productId"),rs.getString("productName"),rs.getString("productDescription"),rs.getBinaryStream("picture"),rs.getDouble("price"),rs.getInt("CategoryId"));
            p.add(p2);
        }
        return p;
    }

    @Override
    public List<Product> findAll(Connection con) throws SQLException {
        List<Product>p=new ArrayList<>();
        String sql="select *from Product ";
        PreparedStatement pt=con.prepareStatement(sql);
        ResultSet rs=pt.executeQuery();
        while(rs.next()){
            Product p2=new Product(rs.getInt("productId"),rs.getString("productName"),rs.getString("productDescription"),rs.getBinaryStream("picture"),rs.getDouble("price"),rs.getInt("CategoryId"));
            p.add(p2);
        }
        return p;

    }

    @Override
    public List<Product> findByProductName(String productName, Connection con) throws SQLException {
        List<Product>p=new ArrayList<>();
        String sql="select *from Product where price productName= '"+productName+"' ";
        PreparedStatement pt=con.prepareStatement(sql);
        ResultSet rs=pt.executeQuery();
        while(rs.next()){
            Product p2=new Product(rs.getInt("productId"),rs.getString("productName"),rs.getString("productDescription"),rs.getBinaryStream("picture"),rs.getDouble("price"),rs.getInt("CategoryId"));
            p.add(p2);
        }
        return p;
    }

    @Override
    public List<Product> getPicture(Integer productId, Connection con) throws SQLException {
        List<Product>p=new ArrayList<>();
        String sql="select *from Product where productId='"+productId+"' ";
        PreparedStatement pt=con.prepareStatement(sql);
        ResultSet rs=pt.executeQuery();
        while(rs.next()){
            Product p2=new Product(rs.getInt("productId"),rs.getString("productName"),rs.getString("productDescription"),rs.getBinaryStream("picture"),rs.getDouble("price"),rs.getInt("CategoryId"));
            p.add(p2);
        }
        return p;
    }
}
