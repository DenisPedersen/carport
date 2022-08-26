<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@page errorPage="error.jsp" isErrorPage="false" %>

<t:pagetemplate>
    <jsp:attribute name="header">
    </jsp:attribute>

    <jsp:attribute name="footer">
            Log ind
    </jsp:attribute>

    <jsp:body>

        <div class="h-100 d-flex align-items-center justify-content-center" style="margin-top: 20px">
            <h1>Log ind</h1>
        </div>
        <div class="h-100 d-flex align-items-center justify-content-center" style="margin-top: 50px">
            <form action="fc/login" method="post">
                <input type="hidden" name="command" value="login"/>
                <label for="email">Email: </label>
                <input type="text" id="email" name="email"/>
                <label for="password">Kodeord: </label>
                <input type="password" id="password" name="password"/>
                <input type="submit" value="Log ind"/>
            </form>
        </div>

    </jsp:body>
</t:pagetemplate>