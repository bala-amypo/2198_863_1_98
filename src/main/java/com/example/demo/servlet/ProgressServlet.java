package com.example.demo.servlet;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet("/progress")
public class ProgressServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws IOException {

        String userId = request.getParameter("userId");
        String lessonId = request.getParameter("lessonId");
        String status = request.getParameter("status");

        response.setContentType("text/plain");

        response.getWriter().println("Progress Updated");
        response.getWriter().println("User ID: " + userId);
        response.getWriter().println("Lesson ID: " + lessonId);
        response.getWriter().println("Status: " + status);
    }
}
