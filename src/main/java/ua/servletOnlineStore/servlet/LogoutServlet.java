package ua.servletOnlineStore.servlet;


import ua.servletOnlineStore.dao.UserDao;
import ua.servletOnlineStore.model.connection.DBConnection;
import ua.servletOnlineStore.model.entity.User;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Objects;

@WebServlet("/log-out")
public class LogoutServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try (PrintWriter out = response.getWriter()) {
            if (request.getSession().getAttribute("auth") != null) {
                request.getSession().getAttribute("auth");
                response.sendRedirect("login.jsp");
            } else {
                response.sendRedirect("index.jsp");
            }
        }
    }


}