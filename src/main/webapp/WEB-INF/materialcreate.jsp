<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@page errorPage="../error.jsp" isErrorPage="false" %>

<t:pagetemplate>
    <jsp:attribute name="header">
    </jsp:attribute>

    <jsp:attribute name="footer">
            Log ind
    </jsp:attribute>

    <jsp:body>

        <form action="fc/" method="post">
            <input type="hidden" name="command" value="creatematerial"/>
            <div class="form-group">
                <label for="name">Navn</label>
                <input type="text" class="form-control" name="name" id="name" placeholder="Navn">
            </div>
            <div class="form-group">
                <label for="price">Pris</label>
                <input type="number" class="form-control" name="price" id="price" placeholder="Pris">
            </div>
            <div class="form-group">
                <label for="unit">Enhed</label>
                <input type="text" class="form-control" name="unit" id="unit" placeholder="Enhed">
            </div>
            <div class="form-group">
                <label for="quantity">Antal</label>
                <input type="text" class="form-control" name="quantity" id="quantity" placeholder="Antal">
            </div>
            <div class="form-group">
                <label for="length">Længde</label>
                <input type="number" class="form-control" name="length" id="length" placeholder="Længde">
            </div>
            <div class="form-group">
                <label for="width">Bredde</label>
                <input type="number" class="form-control" name="width" id="width" placeholder="Bredde">
            </div>
            <div class="form-group">
                <label for="height">Højde</label>
                <input type="number" class="form-control" name="height" id="height" placeholder="Højde">
            </div>
            <div class="form-group">
                <label for="typeId">Type ID</label>
                <input type="number" class="form-control" name="typeId" id="typeId" placeholder="Type Id">
            </div>
            <button type="submit" class="btn btn-primary">Submit</button>
        </form>

    </jsp:body>
</t:pagetemplate>
