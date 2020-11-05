<%-- 
    Document   : prikaz_fotke
    Created on : Nov 1, 2020, 5:08:19 PM
    Author     : iq skola
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="icon" href="favicon.ico" type="image/icon type">
        <title>Fotografija</title>
    </head>
    <body>
        <img class="" src='ucitajFoto?dogadjaj_id=<%= request.getParameter("dogadjaj_id")%>&ime=<%= request.getParameter("ime")%>'>
    </body>
</html>
