<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <div class="container"></div>
    <div class="container-fluid">
        <a class="navbar-brand" href="index.jsp"><fmt:message key="milkAlliance"  bundle="${lang}"/></a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent"
                aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav ml-auto">
                <li class="nav-item">
                    <a class="nav-link active" aria-current="page" href="index.jsp"><fmt:message key="index.home"  bundle="${lang}"/></a>
                </li>
                <li class="nav-item"><a class="nav-link" href="cart.jsp"><fmt:message key="index.cart"  bundle="${lang}"/>
                        <span class="badge badge-danger"> ${ cart_list.size() } </span> </a></li>
                <%
                    if(auth != null){%>
<%--                    <li class="nav-item">--%>
<%--                    <a class="nav-link" href="orders.jsp" tabindex="-1" aria-disabled="true"><fmt:message key="index.orders"  bundle="${lang}"/></a>--%>
<%--                    </li>--%>
                    <li class="nav-item">
                    <a class="nav-link" href="log-out"><fmt:message key="index.logout"  bundle="${lang}"/></a>
                    </li>
                    <%} else{%>

                    <li class="nav-item">
                    <a class="nav-link" href="login.jsp"><fmt:message key="index.login"  bundle="${lang}"/></a>
                    </li>
                   <% }

                %>

            </ul>
        </div>
    </div>
</nav>