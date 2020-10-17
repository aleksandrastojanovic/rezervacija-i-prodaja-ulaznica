<%-- 
    Document   : odjava.jsp
    Created on : Oct 15, 2020, 6:51:09 PM
    Author     : iq skola
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Odjava</title>
    </head>
    <body>
        <header>
            <jsp:include page="parts/meni.jsp"></jsp:include>
            <h1>Odjava:</h1>
        </header>
        
        <div>
            <p>
            <h2>Molimo potvrdite odjavu:</h2>
            <a href="odjava"><input type="button" value="Potvrdi" name="potvrda"></a><br>
            <a href="index"><input type="button" value="Odustani" name="odustani"></a>
            </p>
        </div>
    </body>
</html>
