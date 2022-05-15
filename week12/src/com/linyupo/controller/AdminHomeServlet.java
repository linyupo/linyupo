package com.linyupo.controller;

import com.linyupo.model.User;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;


import java.io.IOException;

@WebServlet(name = "AdminHomeServlet", value = "/admin/home")
public class AdminHomeServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session=req.getSession(false);
        if (session!=null&&session.getAttribute("user")!=null){
            User user=(User)session.getAttribute("user");
            if ("admin".equals(user.getUsername())){//admin username must be in table
                req.getRequestDispatcher("../WEB-INF/views/admin/index.jsp").forward(req,resp);
            }else {
                // not admin user
                session.invalidate();//kill session right now
                req.setAttribute("message","Unauthorized Access Admin Module!!!");
                req.getRequestDispatcher("../WEB-INF/views/login.jsp").forward(req,resp);
            }
        }else {
            //no session
            req.setAttribute("message","Please login as admin!!!");
            req.getRequestDispatcher("../WEB-INF/views/login.jsp").forward(req,resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
