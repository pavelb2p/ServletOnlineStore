package ua.servletOnlineStore.servlet;


import ua.servletOnlineStore.model.entity.Cart;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Change Locale Servlet
 */
@WebServlet("/changeLocale")
public class ChangeLocaleServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html, charset=UTF-8");
        HttpSession session = request.getSession();
        String currentLocale = (String) session.getAttribute("locale");
        if (currentLocale.equals("en")) {
            session.setAttribute("locale", "ru");
        } else {
            session.setAttribute("locale", "en");
        }
        RequestDispatcher dis = request.getRequestDispatcher("index.jsp");
        dis.forward(request, response);


    }
}

