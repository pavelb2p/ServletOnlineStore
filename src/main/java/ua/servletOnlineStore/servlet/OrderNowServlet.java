package ua.servletOnlineStore.servlet;


import ua.servletOnlineStore.dao.OrderDao;
import ua.servletOnlineStore.dao.ProductDao;
import ua.servletOnlineStore.model.connection.DBConnection;
import ua.servletOnlineStore.model.entity.Cart;
import ua.servletOnlineStore.model.entity.Order;
import ua.servletOnlineStore.model.entity.Product;
import ua.servletOnlineStore.model.entity.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.crypto.Data;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;

@WebServlet("/order-now")
public class OrderNowServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd");
            Date date = new Date();
            User auth = (User) request.getSession().getAttribute("auth");
            if (Objects.nonNull(auth)) {
                String productId = request.getParameter("id");
                int productQuantity = Integer.parseInt(request.getParameter("quantity"));
                if (productQuantity <= 0) {
                    productQuantity = 1;
                }

                Order orderModel = new Order();
                orderModel.setpId(Integer.parseInt(productId));
                orderModel.setUId(auth.getId());
                orderModel.setQuantity(productQuantity);
                orderModel.setDate(formatDate.format(date));
                if (new OrderDao(DBConnection.getConnection()).saveOrder(orderModel)) {
                    ArrayList<Cart> cart_list = (ArrayList<Cart>) request.getSession().getAttribute("cart-list");
                    ArrayList<Cart> productsInTarget = (ArrayList<Cart>) new ProductDao(DBConnection.getConnection()).getCartProducts(cart_list);
                    double productPrice = 0;
                    for (Product p : productsInTarget) {
                        if (p.getId() == orderModel.getpId()) {
                            productPrice = p.getPrice();
                            break;
                        }
                    }
                    request.setAttribute("orderSum", orderModel.getQuantity() * productPrice);
                    RequestDispatcher dis = request.getRequestDispatcher("payment.jsp");
                    dis.forward(request, response);
                }
            } else {
                response.sendRedirect("login.jsp");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (ServletException b) {
            b.printStackTrace();
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        doGet(request, response);
    }
}