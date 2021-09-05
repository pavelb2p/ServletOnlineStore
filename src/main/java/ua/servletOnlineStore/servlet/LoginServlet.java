package ua.servletOnlineStore.servlet;


import ua.servletOnlineStore.dao.UserDao;
import ua.servletOnlineStore.model.connection.DBConnection;
import ua.servletOnlineStore.model.entity.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Objects;

/**
 * Checks if the login and password are correct when entering
 */
@WebServlet("/user-login")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.sendRedirect("login.jsp");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html; charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            String email = request.getParameter("login-email");
            String password = request.getParameter("login-password");

            try {
                UserDao userDao = new UserDao(DBConnection.getConnection());
                User user = userDao.userLogin(email, password);

                if (Objects.nonNull(user)) {
                    request.getSession().setAttribute("auth", user);
                    response.sendRedirect("index.jsp");
                } else {
                    out.print("user login ERROR? not correct");
                }
            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
            }
            out.print(email + "and " + password);
        }
    }
}