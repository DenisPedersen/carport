<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@page errorPage="error.jsp" isErrorPage="false" %>

<t:pagetemplate>
    <jsp:attribute name="header">
        Registrer
    </jsp:attribute>

    <jsp:attribute name="footer">

    </jsp:attribute>

    <jsp:body>

        <div class="row">
            <div class="col-sm-6">
                <img src="images/registrer.jpg" style="margin-right: auto;margin-bottom: auto"/>
            </div>
            <div class="col-sm-6">
                <form action="fc/register" method="post">
                    <input type="hidden" name="command" value="register"/>
                    <label for="name">Navn:</label><br>
                    <input type="text" id="name" name="name" placeholder="Skriv dit navn"><br>
                    <label for="adr">Adresse:</label><br>
                    <input type="text" id="adr" name="adr" placeholder="Adresse"><br><br>
                    <label for="city">By:</label><br>
                    <input type="text" id="city" name="city" placeholder="By"><br><br>
                    <label for="zip">Postnummer:</label><br>
                    <input type="text" max="9999" id="zip" name="zip" placeholder="Postnummer"><br><br>
                    <label for="mobile">Mobil:</label><br>
                    <input type="text" id="mobile" name="mobile" placeholder="Mobil"><br><br>
                    <label for="email">Email:</label><br>
                    <input type="text" id="email" name="email" placeholder="Email"><br><br>
                    <label for="password">Password:</label><br>
                    <input type="password" id="password" name="password" placeholder="Password"><br><br>
                    <input type="submit" value="Godkend">

                </form>
            </div>
        </div>
    </jsp:body>
</t:pagetemplate>