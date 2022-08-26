<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page errorPage="../error.jsp" isErrorPage="false" %>

<t:pagetemplate>
    <jsp:attribute name="header">

    </jsp:attribute>

    <jsp:attribute name="footer">

    </jsp:attribute>

    <jsp:body>

        <c:if test="${requestScope.deletionSuccess == true}">
            <div class="alert alert-danger" role="alert">
                    ${requestScope.deletionMessage}
            </div>
        </c:if>
        <c:if test="${requestScope.creationSuccess == true}">
            <div class="alert alert-success" role="alert">
                    ${requestScope.creationMessage}
            </div>
        </c:if>

        <div style="display: flex ; justify-content: right ; margin-bottom: 15px ; margin-top: 15px">
            <form action="create" method="post"><input type="hidden" name="command" value="materialcreate">
                <button type="submit" class="btn btn-primary">Opret nyt materiale</button>
            </form>
        </div>

        <form action="fc/" method="post">
            <input type="hidden" name="command" value="materialgetbyid"/>
            <table class="table table-striped table-hover">
                <thead>
                <tr>
                    <th scope="col">Materiale ID</th>
                    <th scope="col">Navn</th>
                    <th scope="col">Pris</th>
                    <th scope="col">Enhed</th>
                    <th scope="col">Antal</th>
                    <th scope="col">Længde</th>
                    <th scope="col">Bredde</th>
                    <th scope="col">Højde</th>
                    <th scope="col">Type ID</th>
                    <th></th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="item" items="${requestScope.materialArrayList}">
                    <tr>
                        <th scope="row">${item.materialId}</th>
                        <td>${item.name}</td>
                        <td>${item.price}</td>
                        <td>${item.unit}</td>
                        <td>${item.quantity}</td>
                        <td>${item.length}</td>
                        <td>${item.width}</td>
                        <td>${item.height}</td>
                        <td>${item.typeName}</td>

                        <td>
                            <button name="modify" value="${item.materialId}">Slet/Rediger</button>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </form>

    </jsp:body>

</t:pagetemplate>