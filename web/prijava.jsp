<%-- 
    Document   : prijava
    Created on : Aug 29, 2020, 2:04:50 PM
    Author     : iq skola
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>        
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
        <title>Prijava</title>
    </head>

    <body>
        <jsp:include page="parts/header.jsp"></jsp:include>\F
            <h1>Prijava:</h1>
            <div>
                <form action='prijava' method="post">

                    <label for="username">Korisnicko ime:</label>
                    <input type="text" name="username" placeholder='Unesite korisnicko ime' required><br>

                    <label for="password">Lozinka:</label>
                    <input type="password" name="password" placeholder="Unesite lozinku" required><br>                

                    <input type="submit" value="Prijavi se">

                </form>
            </div>
            <hr>

        <jsp:include page="parts/footer.jsp"></jsp:include>

    </body>

</html>