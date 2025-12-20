package com.example.demo.servlet;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet("/course")
public class CourseServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws IOException {

        String courseName = request.getParameter("courseName");

        response.setContentType("text/plain");
        response.getWriter().println("Course Added: " + courseName);
    }

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws IOException {

        response.setContentType("text/plain");
        response.getWriter().println("Available Courses:");
        response.getWriter().println("Java");
        response.getWriter().println("Spring Boot");
        response.getWriter().println("Data Structures");
    }
}
