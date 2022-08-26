<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@page errorPage="error.jsp" isErrorPage="false" %>

<t:pagetemplate>
    <jsp:attribute name="header">
             Fejlside
    </jsp:attribute>

    <jsp:attribute name="footer">
            Fejlside
    </jsp:attribute>

    <jsp:body>

        <p>Der er opstået en fejl</p>

        <c:if test="${pageContext.errorData.statusCode == 404 }">
            <p><b>Fejlkode:</b> ${pageContext.errorData.statusCode}</p>
        </c:if>

        <c:if test="${pageContext.errorData.statusCode == 500 }">
            <p><b>Error code:</b> ${pageContext.errorData.statusCode}</p>
            <p>Der gik noget galt på serveren.</p>
        </c:if>


        <c:if test="${requestScope.errormessage != null}">
            <p>${requestScope.errormessage}</p>
        </c:if>

        <c:if test="${requestScope.errormessage  == null}">
            <p>Kontakt vores IT-administration for at få lidt hjælp!</p>
        </c:if>

        <p>Gå tilbage til <a href="${pageContext.request.contextPath}/index.jsp">forsiden</a>,
            Eller <a href="${pageContext.request.contextPath}/login.jsp">log på</a> igen</p>

    </jsp:body>
</t:pagetemplate>