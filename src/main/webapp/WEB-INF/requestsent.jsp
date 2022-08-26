<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@page errorPage="../error.jsp" isErrorPage="false" %>

<t:pagetemplate>
    <jsp:attribute name="header">
             Din forespørgsel
    </jsp:attribute>

    <jsp:attribute name="footer">
            Forespørgsel

    </jsp:attribute>

    <jsp:body>

        Tak for din forespørgsel. Den vil nu blive behandlet af vores medarbejder og du vil snarest høre fra os.
        <br>

        <table class="table table-striped table-hover">
            <thead>
            <tr>

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

            <tr>
                <td>${requestScope.newCarportRequest.length}</td>
                <td>${requestScope.newCarportRequest.width}</td>
                <td>${requestScope.newCarportRequest.roofType}</td>
                <td>${requestScope.newCarportRequest.roofIncline}</td>
                <td>${requestScope.newCarportRequest.approved}</td>
                <td>${requestScope.newCarportRequest.shedLength}</td>
                <td>${requestScope.newCarportRequest.shedWidth}</td>
            </tr>
            <br>

            </tbody>
        </table>


        <button id="toggle" onclick="myFunction()">Vis carport tegning</button>
        <div id="carportSVG" style="display: none">
                ${requestScope.svgdrawing}
        </div>
        <br>
        <script>
            const targetDiv = document.getElementById("carportSVG");
            const button = document.getElementById("toggle");
            button.onclick = function () {
                if (targetDiv.style.display !== "none") {
                    targetDiv.style.display = "none";
                } else {
                    targetDiv.style.display = "block";
                }
            };
        </script>


    </jsp:body>
</t:pagetemplate>
