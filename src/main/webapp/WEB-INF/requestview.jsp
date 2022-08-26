<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@page errorPage="../error.jsp" isErrorPage="false" %>

<t:pagetemplate>
    <jsp:attribute name="header">
             Forespørgelse
    </jsp:attribute>

    <jsp:attribute name="footer">
            Forespørgelse
    </jsp:attribute>

    <jsp:body>

        <h3>Her er forespørgelse nr ${requestScope.carportRequestDTO.carportRequestId}</h3>

        <table class="table table-striped table-hover">
            <thead>
            <tr>
                <th scope="col">Forespørgsel ID</th>
                <th scope="col">Længde</th>
                <th scope="col">Bredde</th>
                <th scope="col">Tagtype</th>
                <th scope="col">Taghældning</th>
                <th scope="col">Godkendt</th>
                <th scope="col">Skur Længde</th>
                <th scope="col">Skur Bredde</th>

            </tr>
            </thead>
            <tbody>

            <tr>
                <th scope="row">${requestScope.carportRequestDTO.carportRequestId}</th>
                <td>${requestScope.carportRequestDTO.length}</td>
                <td>${requestScope.carportRequestDTO.width}</td>
                <td>${requestScope.carportRequestDTO.roof}</td>
                <td>${requestScope.carportRequestDTO.roofIncline}</td>
                <td>${requestScope.carportRequestDTO.approved}</td>
                <td>${requestScope.carportRequestDTO.shedLength}</td>
                <td>${requestScope.carportRequestDTO.shedWidth}</td>
            </tr>
            <br>
            </tbody>
        </table>

        <h3>Kunde Oplysninger:</h3>
        <table class="table table-striped table-hover">
            <thead>
            <tr>
                <th scope="col">Kunde ID</th>
                <th scope="col">Kunde Navn</th>
                <th scope="col">Kunde Adresse</th>
                <th scope="col">Mobil Nr.</th>

            </tr>
            </thead>
            <tbody>
            <tr>
                <th scope="row">${requestScope.carportRequestDTO.customerId}</th>
                <td>${requestScope.carportRequestDTO.name}</td>
                <td>${requestScope.carportRequestDTO.address}</td>
                <td>${requestScope.carportRequestDTO.mobile}</td>
            </tr>
            <br>
            </tbody>
        </table>

        <form action="fc/approveRequest" method="post"><input type="hidden" name="command" value="requestapprove"/>
            <button name="requestId" class="btn btn-success" value="${requestScope.carportRequestDTO.carportRequestId}">
                Godkend
            </button>
        </form>
        <form action="fc/denyRequest" method="post"><input type="hidden" name="command" value="requestdeny"/>
            <button name="requestId" class="btn btn-outline-danger"
                    value="${requestScope.carportRequestDTO.carportRequestId}">Afvis
            </button>
        </form>


    </jsp:body>
</t:pagetemplate>