<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@page errorPage="../error.jsp" isErrorPage="false" %>

<t:pagetemplate>
    <jsp:attribute name="header">
             Førespørgselsliste
    </jsp:attribute>

    <jsp:attribute name="footer">

    </jsp:attribute>

    <jsp:body>

        <h3>Her er en oversigt over dine forespørgsler</h3>

        <table class="table table-striped table-hover">
            <thead>
            <tr>
                <th scope="col">Forespørgsel ID</th>
                <th scope="col">Kunde ID</th>
                <th scope="col">Længde</th>
                <th scope="col">Bredde</th>
                <th scope="col">Tagtype</th>
                <th scope="col">Taghældning</th>
                <th scope="col">Godkendt</th>
                <th scope="col">Skur Længde</th>
                <th scope="col">Skur Bredde</th>
                <th></th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="item" items="${requestScope.carportRequestArraylist}">
                <tr>
                    <th scope="row">${item.requestId}</th>
                    <td>${item.customerId}</td>
                    <td>${item.length}</td>
                    <td>${item.width}</td>
                    <td>${item.roofType}</td>
                    <td>${item.roofIncline}</td>
                    <td>
                        <c:choose>
                            <c:when test="${item.approved == false}">
                                <div class="alert alert-info" role="alert">
                                    Under behandling
                                </div>
                            </c:when>
                            <c:otherwise>
                                <div class="alert alert-success" role="alert">
                                    Godkendt
                                </div>
                            </c:otherwise>
                        </c:choose>
                    </td>
                    <td>${item.approved}</td>
                    <td>${item.shedLength}</td>
                    <td>${item.shedWidth}</td>
                </tr>
                <br>
            </c:forEach>
            </tbody>
        </table>
    </jsp:body>
</t:pagetemplate>