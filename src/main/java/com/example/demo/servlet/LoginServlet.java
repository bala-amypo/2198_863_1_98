package com.example.demo.servlet;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws IOException {

        String email = request.getParameter("email");
        String password = request.getParameter("password");

        response.setContentType("text/plain");

        if ("admin@gmail.com".equals(email)
                && "admin123".equals(password)) {
            response.getWriter().println("Login Successful");
        } else {
            response.getWriter().println("Invalid Credentials");
        }
    }
}
