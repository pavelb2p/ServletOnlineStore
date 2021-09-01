<%@ page import="ua.servletOnlineStore.model.connection.DBConnection" %>
<%@ page import="ua.servletOnlineStore.model.entity.User" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="ua.servletOnlineStore.model.entity.Cart" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Objects" %>
<%@ page import="ua.servletOnlineStore.dao.ProductDao" %>
<%@ page import="java.sql.SQLException" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<%@page language="java" contentType="text/html; UTF-8"
        pageEncoding="UTF-8" %>

<%
    //---------------------------------------------------------
    String currentLocale=(String)request.getSession().getAttribute("locale");
    String changeLocale=null;
    if(Objects.isNull(currentLocale)){
        request.getSession().setAttribute("locale","en");
        changeLocale="ru";
    }
    else{
        if(currentLocale.equals("en")){
            changeLocale="ru";

        }
        else{
            changeLocale="en";
        }

    }

   //--------------------------------------------------

    User auth = (User) request.getSession().getAttribute("auth");
    if (auth != null) {
        request.setAttribute("auth", auth);
    }

    ArrayList<Cart> cart_list = (ArrayList<Cart>) session.getAttribute("cart-list");
    List<Cart> cartProduct = null;
    if (cart_list != null) {
        ProductDao productDao = null;
        try {
            productDao = new ProductDao(DBConnection.getConnection());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        cartProduct = productDao.getCartProducts(cart_list);
        double total = productDao.getTotalCartPrice(cart_list);
        request.setAttribute("cart_list", cart_list);
        request.setAttribute("total", total);
    }

%>
<html>
<head>

    <title> Cart page </title>
    <%@include file="includes/head.jsp" %>

    <style type="text/css">
        .table tbody td {
            vartical-align: middle
        }

        .increment-button, .decrement-button {
            box-shadow: none;
            font-size: 25px;
        }
        .form-control{
            width:25px;

        }

    </style>

</head>
<body>
<fmt:setLocale value="${sessionScope.locale}" scope="session"/>
<fmt:setBundle basename="properties" var="lang"/>

<%@include file="includes/navbar.jsp" %>

<div class="container">
    <a href="changeLocale"><%=changeLocale%></a>
    <div class="d-flex py-3">
        <h3><fmt:message key="cart.totalprice"  bundle="${lang}"/> $ ${(total>0)?total:0}</h3>
        <a class="mx-3 btn btn-primary" href="/buyAllTargetServlet"><fmt:message key="cart.checkOut"  bundle="${lang}"/></a>
    </div>
    <table class="table table-light">
        <thead>
        <tr>
            <th scope="col"> <fmt:message key="cart.name"  bundle="${lang}"/></th>
            <th scope="col"> <fmt:message key="cart.category"  bundle="${lang}"/></th>
            <th scope="col"> <fmt:message key="cart.price"  bundle="${lang}"/></th>
            <th scope="col"> <fmt:message key="cart.buynow"  bundle="${lang}"/></th>
            <th scope="col"> <fmt:message key="cart.cancel"  bundle="${lang}"/></th>
        </tr>

        </thead>
        <tbody>

        <%
            if (cart_list != null) {
                for (Cart c : cartProduct) {%>
        <tr>
            <td><%=c.getName() %>
            </td>
            <td><%=c.getCategory() %>
            </td>
            <td><%=c.getPrice() %> $</td>
            <td class="main-cell">
                <form action="order-now" method="post" class="form-inline">
                    <input type="hidden" name="id" value="<%=c.getId() %>" class="form-input">
                    <div class="form-group d-flex justify-content-between w-20">
                        <a class="btn btn-sm decrement-button" href="quantity-inr-dec?action=dec&id=<%=c.getId()%>"> <i
                                class="fas fa-minus-square"></i></a>
                        <input style="width:40px" type="text" name="quantity" class="form-control" value="<%= c.getQuantity()%>" readonly>
                        <a class="btn btn-sm increment-button" href="quantity-inr-dec?action=inc&id=<%=c.getId()%>"> <i
                                class="fas fa-plus-square"></i></a>
                        <button type="submit" class="btn btn-primary btn-sm w-20"><fmt:message key="cart.buy.button"  bundle="${lang}"/></button>
                    </div>

                </form>
            </td>
            <td><a class="button btn-sm btn-danger" href="remove-from-cart?id=<%= c.getId() %>"> <fmt:message key="cart.remove.button"  bundle="${lang}"/></a></td>
        </tr>
        <% }
        }
        %>


        </tbody>
    </table>
</div>

<%@include file="includes/footer.jsp" %>


</body>
</html>
