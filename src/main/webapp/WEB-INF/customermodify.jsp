<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page errorPage="../error.jsp" isErrorPage="false" %>

<t:pagetemplate>
    <jsp:attribute name="header">
         Rediger kundeoplysninger
    </jsp:attribute>

    <jsp:attribute name="footer">
        Rediger kundeoplysninger
    </jsp:attribute>

    <jsp:body>

        <form action="fc/" method="post">
            <input type="hidden" name="command" value="customermodify"/>
            <div class="mb-3">
                <label for="name" class="form-label">Navn:</label>
                <br>
                <input type="text" class="form-label" name="name" id="name" value="${sessionScope.customer.name}"
                       required="required">

            </div>
            <div class="mb-3">
                <label for="email" class="form-label">Email:</label>
                <br>
                <input type="email" class="form-label" name="email" id="email"
                       value="${sessionScope.customer.getEmail()}" required="required">
            </div>
            <div class="mb-3">
                <label for="password" class="form-label">Password:</label>
                <br>
                <input type="password" class="form-label" name="password" id="password"
                       value="${sessionScope.customer.getPassword()}" required="required">
            </div>
            <div class="mb-3">
                <label for="address" class="form-label">Adresse:</label>
                <br>
                <input type="text" class="form-label" name="address" id="address"
                       value="${sessionScope.customer.address}" required="required">
            </div>
            <div class="mb-3">
                <label for="city" class="form-label">By:</label>
                <br>
                <input type="text" class="form-label" name="city" id="city" value="${sessionScope.customer.city}"
                       required="required">
            </div>
            <div class="mb-3">
                <label for="zip" class="form-label">Postnummer:</label>
                <br>
                <input type="number" class="form-label" name="zip" id="zip" value="${sessionScope.customer.zip}"
                       required="required">
            </div>
            <div class="mb-3">
                <label for="mobile" class="form-label">Telefon nr.:</label>
                <br>
                <input type="number" class="form-label" name="mobile" id="mobile"
                       value="${sessionScope.customer.mobile}" required="required">
            </div>
            <button type="submit">Opdater</button>
        </form>


    </jsp:body>

</t:pagetemplate>