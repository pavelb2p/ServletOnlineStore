<%--
  Created by IntelliJ IDEA.
  User: 1
  Date: 30.08.2021
  Time: 9:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<html>
<head>
    <title>Title</title>
</head>
<body>
<p>Total amount of your order</p><p><%=request.getAttribute("orderSum")%></p>
<label>Enter cart number</label><input type="text">

<a class="btn btn-primary" href="#">Thanks !</a>
</body>
</html>
