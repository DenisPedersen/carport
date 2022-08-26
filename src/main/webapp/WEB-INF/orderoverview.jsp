<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@page errorPage="../error.jsp" isErrorPage="false" %>

<t:pagetemplate>
    <jsp:attribute name="header">
             Ordreoversigt
    </jsp:attribute>

    <jsp:attribute name="footer">
            Ordreoversigt
    </jsp:attribute>

    <jsp:body>

        <h3>Her er en oversigt over alle ordrer</h3>

        <table class="table table-striped table-hover">
            <thead>
            <tr>
                <th scope="col">Ordre ID</th>
                <th scope="col">Kunde ID</th>
                <th scope="col">Dato</th>
                <th scope="col">Carport type</th>
                <th scope="col">Forespørgsel ID</th>
                <th scope="col">Pris</th>
                <th></th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="item" items="${requestScope.orderArraylist}">
                <tr>
                    <th scope="row">${item.orderId}</th>
                    <td>${item.customerId}</td>
                    <td>${item.dateTime}</td>
                    <td>${item.carportType}</td>
                    <td>${item.carportRequestId}</td>
                    <td>${item.price}</td>
                    <td>
                        <form action="fc/overview" method="post"><input type="hidden" name="command" value="orderview"/>
                            <button name="orderId" value="${item.orderId}">Vis</button>
                        </form>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </jsp:body>
</t:pagetemplate>