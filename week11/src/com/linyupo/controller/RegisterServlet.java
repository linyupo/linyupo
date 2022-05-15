package com.linyupo.controller;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.*;

@WebServlet(name = "RegisterServlet", value = "/register")
public class RegisterServlet extends HttpServlet {

    private Connection con;

    @Override
    public void init() throws ServletException {
        super.init();
//4. Get all 4 context init parameters within RegisterServletinit()
       /* ServletContext context=getServletContext();
        String driver=context.getInitParameter("driver");//<param-name>driver</param-name>
        String url=context.getInitParameter("url");
        String username=context.getInitParameter("username");
        String password=context.getInitParameter("password");
        //5.Create database connection within init()
        try {
            Class.forName(driver);
            con = DriverManager.getConnection(url, username, password);
            System.out.println("init()--> " + con);// ok(java code)- ok(use config-in web.xml) -- ok -use( @webservlet )--> (use context) ok-- use mysql ( no need to change in java code )
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }*/


        con = (Connection) getServletContext().getAttribute("con");//name of attibute
        //please check the video live demo#4

    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("WEB-INF/views/register.jsp").forward(request,response);;
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //when method =post -request come in doPost

        //get all data - from Request
        String username =request.getParameter("username");//get Username <input type="text" name="username" />
        String password =request.getParameter("password");//get  password <input type="password" name="password" />
        String email =request.getParameter("email");//get <input type="text" name="email" />
        String gender =request.getParameter("gender");//get <input type="radio" name="gender"
        String birthDate =request.getParameter("birthdate");//get Birth Date <input type="text" name="birthDate" />

        //6.Insert a row into database table “usertable” in doPost()

        try {
            Statement st = con.createStatement();
            String sql = "insert into usertable(username,password,email,gender,birthdate)" +
                    " values('" + username + "','" + password + "','" + email + "','" + gender + "','" + birthDate + "')";
            System.out.println("sql" + sql);//check sql is ok or not

            int n = st.executeUpdate(sql);
            System.out.println("n-->" + n);//== 1 success--insert


            response.sendRedirect("login");

        } catch (SQLException e ) {
            e.printStackTrace();
        }


        //print all data-- write into response
         /*PrintWriter out=response.getWriter();
         out.println("UserName : "+username);
        out.println("Password : "+password);
        out.println("email : "+email);
        out.println("gender : "+gender);
        out.println("BirthDate : "+birthDate);
        out.println("UserName : "+username);*/

        //

    }
}
