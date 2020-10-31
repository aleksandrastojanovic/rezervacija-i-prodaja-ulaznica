<%-- 
    Document   : header
    Created on : Oct 15, 2020, 6:10:33 PM
    Author     : iq skola
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<header>
    <jsp:include page="/kontrola"></jsp:include>
    <jsp:include page="meni.jsp"></jsp:include>
    <%
        if (request.getAttribute("poruka") != null) {
    %>
    <div class="alert alert-warning text-center" role="alert">
        <h2><%= request.getAttribute("poruka")%></h2>
    </div>

    <%
        }
        if (request.getAttribute("porukaUspesno") != null) {
    %>
    <div class="alert alert-success text-center" role="alert">
        <h2><%= request.getAttribute("porukaUspesno")%></h2>
    </div>
    <% }%>
</header>
