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
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
        <title>Odjava</title>
    </head>
    <body>
        <jsp:include page="parts/header.jsp"></jsp:include>

            <div>
                <p>
                <h2>Molimo potvrdite odjavu:</h2>
                <a href="odjava"><input type="button" value="Potvrdi" name="potvrda"></a><br>
                <a href="index"><input type="button" value="Odustani" name="odustani"></a>
            </p>
        </div>
    <jsp:include page="parts/footer.jsp"></jsp:include>
</body>
</html>
