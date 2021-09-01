<%@ page import="ua.servletOnlineStore.model.connection.DBConnection" %>
<%@ page import="ua.servletOnlineStore.model.entity.User" %>
<%@ page import="ua.servletOnlineStore.dao.ProductDao" %>
<%@ page import="java.util.List" %>
<%@ page import="ua.servletOnlineStore.model.entity.Product" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="ua.servletOnlineStore.model.entity.Cart" %>
<%@ page import="java.util.Objects" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<%@page language="java" contentType="text/html; UTF-8"
        pageEncoding="UTF-8" %>
<%
    User auth = (User) request.getSession().getAttribute("auth");
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



    if (auth != null) {
        request.setAttribute("auth", auth);
    }

    ProductDao productDao = new ProductDao(DBConnection.getConnection());
    List<Product> products = productDao.getAllProduct();

    ArrayList<Cart> cart_list = (ArrayList<Cart>) session.getAttribute("cart-list");
    if (cart_list != null){
         request.setAttribute("cart_list", cart_list);
    }

%>
<html>
<head>
    <title> Milk Alliance </title>
    <%@include file="includes/head.jsp" %>
</head>
<body>

<fmt:setLocale value="${sessionScope.locale}" scope="session"/>
<fmt:setBundle basename="properties" var="lang"/>
<%@include file="includes/navbar.jsp" %>

<div class="container">
    <a href="changeLocale"><%=changeLocale%></a>

    <div class="card-header my-3"><fmt:message key="index.allProducts"  bundle="${lang}"/></div>

    <div class="row">
        <%
        if(!products.isEmpty()){
            for (Product pr : products){%>
                <div class="col-md-3 my-3">
            <div class="card w-100" style="width: 18rem;">
                <img src="product-images/<%= pr.getImage()%>" class="card-img-top" alt="...">
                <div class="card-body">
                    <h5 class="card-title"> <%= pr.getName()%> </h5>
                    <h6 class="price"><%= pr.getPrice()%></h6>
                    <h6 class="category"> <fmt:message key="cart.category"  bundle="${lang}"/> : <%= pr.getCategory()%> </h6>
                    <div class="mt-3 d-flex justify-content-between"></div>
                    <a href="add-to-cart?id=<%= pr.getId()%>" class="btn btn-primary"><fmt:message key="index.buynow"  bundle="${lang}"/></a>
<%--                    <a href="#" class="btn btn-primary">Buy now</a>--%>
                </div>
            </div>
        </div>
            <%}
        }
        %>

    </div>

</div>

<%--//part3--%>
<%@include file="includes/footer.jsp" %>
</body>
</html>
