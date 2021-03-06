package ua.servletOnlineStore.servlet;


import ua.servletOnlineStore.model.entity.Cart;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Objects;

/**
 * Increasing or decreasing the number of products in the cart Servlet
 */
@WebServlet("/quantity-inr-dec")
public class QuantityIncDecServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html; charset=UTF-8");
            String action = request.getParameter("action");
            int id = Integer.parseInt(request.getParameter("id"));

            ArrayList<Cart> cart_list = (ArrayList<Cart>) request.getSession().getAttribute("cart-list");

            if (Objects.nonNull(action) && id >= 1) {
                for (Cart c : cart_list) {
                    if (c.getId() == id) {
                        int quantity = c.getQuantity();
                        if(action.equals("inc")){
                        quantity++;}
                        else if(action.equals("dec")){
                            quantity--;
                        }
                        c.setQuantity(quantity);
                    }
                }
            }
            response.sendRedirect("cart.jsp");
            }
}