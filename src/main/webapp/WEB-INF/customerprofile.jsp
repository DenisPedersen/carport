<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page errorPage="../error.jsp" isErrorPage="false" %>

<t:pagetemplate>
    <jsp:attribute name="header">
         Kundeoplysninger
    </jsp:attribute>

    <jsp:attribute name="footer">
        Kundeoplysninger
    </jsp:attribute>

    <jsp:body>


        <c:if test="${sessionScope.modificationDone == true}">
            <div class="alert alert-success" role="alert">
                    ${sessionScope.customerModified}
            </div>
        </c:if>
            <form action="fc/" method="post">
                <input type="hidden" name="command" value="customermodifyredirect"/>
                <div class="mb-3">
                    <label for="name" class="form-label">Navn:</label>
                    <br>
                    <p type="text" class="form-label" id="name">${sessionScope.customer.name}</p>

            </div>
            <div class="mb-3">
                <label for="email" class="form-label">Email:</label>
                <br>
                <p type="text" class="form-label" id="email">${sessionScope.customer.getEmail()}</p>
            </div>
            <div class="mb-3">
                <label for="city" class="form-label">By:</label>
                <br>
                <p type="text" class="form-label" id="city">${sessionScope.customer.city}</p>
            </div>
            <div class="mb-3">
                <label for="zip" class="form-label">Postnummer:</label>
                <br>
                <p type="text" class="form-label" id="zip">${sessionScope.customer.zip}</p>
            </div>
            <div class="mb-3">
                <label for="mobile" class="form-label">Telefon nr.:</label>
                <br>
                <p type="text" class="form-label" id="mobile">${sessionScope.customer.mobile}</p>
            </div>
            <button name="modify">Rediger oplysninger</button>
        </form>

    </jsp:body>

</t:pagetemplate>