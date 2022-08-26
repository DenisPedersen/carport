<%@tag description="Overall Page template" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@attribute name="header" fragment="true" %>
<%@attribute name="footer" fragment="true" %>

<!DOCTYPE html>
<html lang="da">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>
        <jsp:invoke fragment="header"/>
    </title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/square.css">
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">

</head>
<body class="d-flex flex-column min-vh-100">
<header>
    <nav class="navbar navbar-expand-lg navbar-dark" style="background-color: #0c2069;">
        <div class="container">
            <a class="navbar-brand" href="${pageContext.request.contextPath}/index.jsp">
                <img src="https://www.johannesfog.dk/globalassets/header/logo.png" class="img-fluid"/>
            </a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNavAltMarkup"
                    aria-controls="navbarNavAltMarkup" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse justify-content-end" id="navbarNavAltMarkup">
                <div class="navbar-nav">


                    <c:if test="${sessionScope.account.role == 1 }">
                        <a class="nav-item nav-link" href="${pageContext.request.contextPath}/index.jsp">Forside</a>
                        <a class="nav-item nav-link"
                           href="${pageContext.request.contextPath}/fc/admin?command=requestoverview">Forespørgelser</a>
                        <a class="nav-item nav-link"
                           href="${pageContext.request.contextPath}/fc/orderoverview?command=orderoverview">Ordrer</a>
                        <a class="nav-item nav-link"
                           href="${pageContext.request.contextPath}/fc/materialoverview?command=materialoverview">Lager</a>
                        <a class="nav-item nav-link" href="${pageContext.request.contextPath}/fc/logout?command=logout">Log
                            ud</a>
                    </c:if>


                    <c:if test="${sessionScope.customer != null }">
                        <a class="nav-item nav-link" href="${pageContext.request.contextPath}/quickbuild.jsp">Byg
                            selv</a>
                        <a class="nav-item nav-link"
                           href="${pageContext.request.contextPath}/fc/customerindex?command=customerindex">Kundeprofil</a>
                        <a class="nav-item nav-link" href="${pageContext.request.contextPath}/fc/logout?command=logout">Log
                            ud</a>
                    </c:if>

                </div>
            </div>
        </div>
    </nav>
</header>

<div id="body" class="container mt-4" style="min-height: 400px;">
    <h1>
        <jsp:invoke fragment="header"/>
    </h1>
    <jsp:doBody/>
</div>

<!-- Footer -->
<footer class="mt-auto" style="background-color: #003d76; color:white">
    <div class="container p-4">
        <div class="row mt-4">
            <div class="col">
                Johannes Fog A/S - Firskovvej 20 - 2800 Lyngby<br/>
            </div>
            <div class="col">
                Alle priser er inkl. moms - CVR-nr. 16314439<br/>
            </div>
        </div>
    </div>
</footer>

</div>

<!-- Bootstrap Bundle with Popper -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
        crossorigin="anonymous"></script>

</body>
</html>