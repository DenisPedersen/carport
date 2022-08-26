<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@page errorPage="/error.jsp" isErrorPage="false" %>

<t:pagetemplate>
    <jsp:attribute name="header">
             Byg selv carport
    </jsp:attribute>

    <jsp:attribute name="footer">
            Byg selv carport
    </jsp:attribute>

    <jsp:body>
        <div class="row">
            <div class="col-sm-6">

                <form action="fc/quickbuild" method="post">
                    <input type="hidden" name="command" value="quickbuild"/>

                    <label for="carportWidth">Vælg bredde (min 240 cm og max 600 cm:)</label><br><br>
                    <select name="width" id="carportWidth" required="required">
                        <option value="">Vælg bredde</option>
                        <c:forEach varStatus="loop" begin="240" end="600" step="30">
                            <option><c:out value="${loop.current}"></c:out></option>
                        </c:forEach></select>
                    <br>
                    <br>
                    <label for="carportLength">Vælg længde (min 240 cm og max 780 cm:)</label><br><br>
                    <select name="length" id="carportLength" required="required">
                        <option value="">Vælg længde</option>
                        <br><br>
                        <c:forEach varStatus="loop" begin="240" end="780" step="30">
                            <option><c:out value="${loop.current}"></c:out></option>
                        </c:forEach></select>
                    <br>
                    <br>
                    <label for="roof">Vælg tag-type:</label><br><br>
                    <select name="roof" id="roof" required="required">
                        <option value="">Vælg tag-type</option>
                        <c:forEach items="Plasttrapezplader" var="names" begin="0" end="1">
                            <option><c:out value="${names}"></c:out></option>
                        </c:forEach></select>
                    <br>


                    <label for="shed">Ønskes skur? Klik her</label>
                    <input type="checkbox" id="shed" onclick="myFunction()"><br><br>

                    <div id="shedMeasurements" style="display:none">
                        <p id="shedWidth" name="shedWidth" style="display: none">* Bemærk at skurbredden er samme
                            størrelse som carportbredden</p>
                        <br>
                        <label for="shedLength">Vælg længde</label>
                        <select name="shedLength" id="shedLength" style="display:none">
                            <option value="">Vælg længde</option>
                            <c:forEach varStatus="loop" begin="150" end="690" step="30">
                                <option><c:out value="${loop.current}"></c:out></option>
                            </c:forEach>
                        </select>
                    </div>

                    <script>
                        function myFunction() {
                            var checkBox = document.getElementById("shed");
                            var shedMeasurements = document.getElementById("shedMeasurements");
                            var shedWidth = document.getElementById("shedWidth");
                            var shedLength = document.getElementById("shedLength");
                            if (checkBox.checked == true) {
                                shedWidth.style.display = "block";
                                shedLength.style.display = "block";
                                shedMeasurements.style.display = "block";
                            } else {
                                shedWidth.style.display = "none";
                                shedLength.style.display = "none";
                                shedMeasurements.style.display = "none";
                            }
                        }
                    </script>
                    <br>
                    <br>


                    <input type="submit" value="Vælg">
                </form>
            </div>


            <div class="col-xl-6">
                <div class="card mb-3">
                    <img class="card-img-top" src="./images/construction-worker.jpg" alt="Card image cap">
                    <div class="card-body">
                        <p class="card-text">Velkommen til Fog's byg selv side. Her kan du indtaste dine ønskede
                            mål til din
                            drømme carport! Husk du også
                            kan bestille carporte med skur. Er du i tvivl om noget, så kontakt vores
                            kundeservice for
                            hjælp</p>
                    </div>
                </div>
            </div>
        </div>

    </jsp:body>
</t:pagetemplate>
