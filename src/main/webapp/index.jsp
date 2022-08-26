<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page errorPage="error.jsp" isErrorPage="false" %>

<t:pagetemplate>
    <jsp:attribute name="header">

    </jsp:attribute>

    <jsp:attribute name="footer">

    </jsp:attribute>

    <jsp:body>

        <c:if test="${sessionScope.account.role == 1}">
            <h2 style="text-align: center; margin-bottom: 50px">VELKOMMEN TIL ADMIN SIDEN</h2>
            <div class="row" style="margin: 50px">
                <div class="col">
                    <form action="fc/" method="post">
                        <div class="card" style="width: 18rem;">
                            <input type="hidden" name="command" value="orderoverview">
                            <img class="card-img-top" src="images/adminorder.jpg" alt="Card image cap">
                            <div class="card-body">
                                <h5 class="card-title">Se alle ordrer</h5>
                                <button type="submit" class="btn btn-primary">Vælg</button>
                            </div>
                        </div>
                    </form>
                </div>

                <div class="col">
                    <form action="fc/" method="post">
                        <div class="card" style="width: 18rem;">
                            <input type="hidden" name="command" value="requestoverview">
                            <img class="card-img-top" src="images/quote.jpg" alt="Card image cap">
                            <div class="card-body">
                                <h5 class="card-title">Se alle forespørgsler</h5>
                                <button type="submit" class="btn btn-primary">Vælg</button>
                            </div>
                        </div>
                    </form>
                </div>

                <div class="col">
                    <form action="fc/" method="post">
                        <div class="card" style="width: 18rem;">
                            <input type="hidden" name="command" value="materialoverview">
                            <img class="card-img-top" src="images/lager.jpg" alt="Card image cap">
                            <div class="card-body">
                                <h5 class="card-title">Gå til lager</h5>
                                <button type="submit" class="btn btn-primary">Vælg</button>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </c:if>


        <c:if test="${sessionScope.customer == null && sessionScope.account == null}">
            <h2 style="text-align: center; margin-bottom: 50px">VELKOMMEN TIL FOG TRÆLAST OG BYGGECENTER</h2>

            <div class="row">
                <div class="col-sm-6">
                    <img src="images/bygselv.jpg" style="max-width: 100%; ; height: auto; display: block">
                </div>
                <div class="col-sm-6">
                    <h2>Tilmeld dig eller log ind for at shoppe</h2>
                    <div class="d-flex flex-column bd-highlight mb-3">
                        <div class="p-2 bd-highlight">
                            <a class="btn btn-secondary btn-lg" href="${pageContext.request.contextPath}/login.jsp"> Log
                                ind</a>
                        </div>
                        <div class="p-2 bd-highlight">
                            <a class="btn btn-secondary btn-lg" href="${pageContext.request.contextPath}/register.jsp">Opret
                                bruger</a>
                        </div>
                    </div>
                </div>
            </div>

        </c:if>

        <c:if test="${sessionScope.customer != null}">
            <h2 style="text-align: center; margin-bottom: 50px">VELKOMMEN TIL FOG TRÆLAST OG BYGGECENTER</h2>
            <div class="col d-flex justify-content-center">
                <div class="row">
                    <div class="col-sm-6">
                        <div class="card" style="width: 18rem; margin-right: 100px">
                            <img src="${pageContext.request.contextPath}/images/bygselv.jpg" class="card-img-top"
                                 alt="Byg selv">
                            <div class="card-body">
                                <h5 class="card-title">Byg selv</h5>
                                <p class="card-text">Her kan du selv bygge en carport ud fra dine ønskede mål.</p>
                                <a href="quickbuild.jsp" class="btn btn-primary">Vælg</a>
                            </div>
                        </div>
                    </div>

                    <div class="col-sm-6">
                        <div class="card" style="width: 18rem; margin-left: 100px">
                            <img src="${pageContext.request.contextPath}/images/standardcarport.jpg"
                                 class="card-img-top" alt="Standard carport">
                            <div class="card-body">
                                <h5 class="card-title">Standard carport</h5>
                                <p class="card-text">Vælg ud fra vores store udvalg af carporte!</p>
                                <a href="https://www.johannesfog.dk/byggecenter/have--fritid/byg-selv-produkter/"
                                   class="btn btn-primary">Vælg</a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </c:if>

    </jsp:body>

</t:pagetemplate>