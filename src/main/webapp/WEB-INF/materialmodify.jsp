<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page errorPage="../error.jsp" isErrorPage="false" %>

<t:pagetemplate>
    <jsp:attribute name="header">
         Rediger materialer
    </jsp:attribute>

    <jsp:attribute name="footer">
        Welcome to the adminsite
    </jsp:attribute>

    <jsp:body>

        <form action="modify" method="post">
            <input type="hidden" name="command" value="materialmodify"/>
            <input type="hidden" name="materialId" value="${requestScope.material.materialId}">
            <div class="form-group">
                <label for="name">Navn</label>
                <input type="text" class="form-control" name="name" id="name" value="${requestScope.material.name}"
                       required="required">
            </div>
            <div class="form-group">
                <label for="price">Pris</label>
                <input type="number" class="form-control" name="price" id="price" value="${requestScope.material.price}"
                       required="required">
            </div>
            <div class="form-group">
                <label for="unit">Enhed</label>
                <input type="text" class="form-control" name="unit" id="unit" value="${requestScope.material.unit}"
                       required="required">
            </div>
            <div class="form-group">
                <label for="unit">Antal</label>
                <input type="text" class="form-control" name="quantity" id="quantity"
                       value="${requestScope.material.quantity}" required="required">
            </div>
            <div class="form-group">
                <label for="length">Længde</label>
                <input type="number" class="form-control" name="length" id="length"
                       value="${requestScope.material.length}" required="required">
            </div>
            <div class="form-group">
                <label for="width">Bredde</label>
                <input type="number" class="form-control" name="width" id="width" value="${requestScope.material.width}"
                       required="required">
            </div>
            <div class="form-group">
                <label for="height">Højde</label>
                <input type="number" class="form-control" name="height" id="height"
                       value="${requestScope.material.height}" required="required">
            </div>
            <div class="form-group">

                <label for="typeName">Type navn</label>
                <select name="typeName" class="form-control" id="typeName" required="required">
                    <option>${requestScope.material.typeName}</option>
                    <c:forEach var="item" items="${requestScope.stringArrayList}">
                        <option><c:out value="${item}"></c:out></option>
                    </c:forEach></select>
            </div>

            <div style="margin-top: 10px; margin-bottom: 10px">
                <button type="submit" class="btn btn-primary">Godkend</button>
                <!-- Button trigger modal -->
                <button type="button" class="btn btn-danger" data-bs-toggle="modal" data-bs-target="#exampleModal">
                    Slet
                </button>
            </div>
        </form>

        <!-- Modal -->
        <div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModal"
             aria-hidden="true">
            <div class="modal-dialog modal-dialog-centered" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="exampleModalLongTitle">Modal title</h5>
                        <button type="submit" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        Er du sikker på du vil slette?
                    </div>
                    <div class="modal-footer">
                        <button type="submit" class="btn btn-secondary" data-dismiss="modal">Close</button>
                        <form action="fc/delete"><input type="hidden" name="command" value="deletematerial"/>
                            <button id="delete" value="${requestScope.material.materialId}" name="delete"
                                    class="btn btn-danger">Bekræft
                            </button>
                        </form>
                    </div>
                </div>
            </div>
        </div>


    </jsp:body>

</t:pagetemplate>