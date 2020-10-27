<%-- 
    Document   : prijava
    Created on : Aug 29, 2020, 1:42:14 PM
    Author     : iq skola
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>        
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
        <style>
            .klasa {
                position: fixed;
                left: 50%;
                top: 52%;
                transform: translate(-50%, -50%);
            }
        </style>
        <title>Registracija</title>
    </head>

    <body>           
        <jsp:include page="parts/header.jsp"></jsp:include>

            <div class="klasa m-3">
                <div class="container-fluid p-3 border text-left">            
                    <h1 class="text-primary text-center">Registracija:</h1>


                    <form action='registracija' method="post">

                        <div class="form-group p-1 m-1">
                            <label class="text-primary" for="ime">Ime:</label>
                            <input class="border" type="text" name="ime" placeholder='Unesite ime' required><br>
                        </div>

                        <div class="form-group p-1 m-1">
                            <label class="text-primary" for="prezime">Prezime:</label>
                            <input class="border" type="text" name="prezime" placeholder='Unesite prezime' required><br>
                        </div>

                        <div class="form-group p-1 m-1">
                            <label class="text-primary" for="username">Korisnicko ime:</label>
                            <input class="border" type="text" name="username" placeholder='Unesite korisnicko ime' minlength='5' required><br>
                        </div>

                        <div class="form-group p-1 m-1">
                            <label class="text-primary" for="password">Lozinka:</label>
                            <input class="border" type="password" name="password" placeholder="Unesite lozinku" minlength='5' required><br>
                        </div>

                        <div class="form-group p-1 m-1">
                            <label class="text-primary" for="password_check">Potvrda lozinke:</label>
                            <input class="border" type="password" name="password_check" placeholder='Unesite potvrdu lozinke' minlength='5' required><br>
                        </div>

                        <div class="form-group p-1 m-1">
                            <label class="text-primary" for="grad">Grad:</label>
                            <input class="border" type="text" name="grad" placeholder='Unesite grad' required><br>
                        </div>

                        <div class="form-group p-1 m-1">
                            <label class="text-primary" for="adresa">Ulica i broj:</label>
                            <input class="border" type="text" name="adresa" placeholder='Unesite ulicu i broj' required><br>
                        </div>

                        <div class="form-group p-1 m-1">
                            <label class="text-primary" for="telefon">Kontakt telefon:</label>
                            <input class="border" type="text" name="telefon" placeholder='Unesite kontakt telefon' required><br>
                        </div>

                        <div class="form-group p-1 m-1">
                            <label class="text-primary" for="email">E-mail:</label>
                            <input class="border" type="text" name="email" placeholder="Unesite e-mail" required><br>
                        </div>

                        <div class="d-flex justify-content-center">
                        <input type="submit" class="btn btn-primary text-center p-1 btn-lg" value="Registruj se">
                        </div>
                        
                    </form>
                </div>


            <jsp:include page="parts/footer.jsp"></jsp:include>
        </div>
    </body>

</html>