<%@ page import="ua.servletOnlineStore.model.connection.DBConnection" %>
<%@ page import="ua.servletOnlineStore.model.entity.User" %>
<%@ page import="ua.servletOnlineStore.model.entity.Cart" %>
<%@ page import="java.util.ArrayList" %>


<%@page language="java" contentType="text/html; UTF-8"
        pageEncoding="UTF-8" %>

<%
    User auth = (User) request.getSession().getAttribute("auth");
    if (auth != null) {
        request.setAttribute("auth", auth);
    }

    ArrayList<Cart> cart_list = (ArrayList<Cart>) session.getAttribute("cart-list");
    if (cart_list != null){
        request.setAttribute("cart_list", cart_list);
    }
%>


<html>
<head>

    <title> Orders page </title>
    <%@include file="includes/head.jsp" %>

</head>
<body>
<%@include file="includes/navbar.jsp" %>

<%@include file="includes/footer.jsp" %>


</body>
</html>
