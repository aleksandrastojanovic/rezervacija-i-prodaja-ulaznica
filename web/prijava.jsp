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
        <link rel="stylesheet" href="parts/pozadina.css">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
        <style>
            .klasa {
                position: fixed;
                left: 50%;
                top: 50%;
                transform: translate(-50%, -50%);
            }
        </style>
        <title>Prijava</title>
    </head>

    <body>
        <jsp:include page="parts/header.jsp"></jsp:include>

            <div class="klasa">
                <div class="container-fluid p-3 border text-left">


                    <h1 class="text-primary text-center">Prijava:</h1>

                    <form action='prijava' method="post">

                        <div class="form-group p-1 m-1">
                            <label class="text-primary" for="username">Korisnicko ime:</label>
                            <input type="text" class="border w-100" name="username" placeholder='Unesite korisnicko ime' required><br>
                        </div>

                        <div class="form-group p-1 m-1">
                            <label class="text-primary" for="password">Lozinka:</label>
                            <input type="password" class="border w-100" name="password" placeholder="Unesite lozinku" required><br>                
                        </div>

                        <div class="d-flex justify-content-center">
                            <input type="submit" class="btn btn-primary text-center p-1" value="Prijavi se">
                        </div>

                    </form>


                    <div>
                    </div>
                <jsp:include page="parts/footer.jsp"></jsp:include>

                </body>

                </html>