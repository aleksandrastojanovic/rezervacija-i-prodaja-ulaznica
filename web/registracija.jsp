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
        <title>Registracija</title>
    </head>

    <body>           
        <jsp:include page="parts/header.jsp"></jsp:include>

            <div class="container-fluid pt-5">            
                <h1>Registracija:</h1>

                <div>
                    <form action='registracija' method="post">
                        <label for="ime">Ime:</label>
                        <input type="text" name="ime" placeholder='Unesite ime' required><br>

                        <label for="prezime">Prezime:</label>
                        <input type="text" name="prezime" placeholder='Unesite prezime' required><br>

                        <label for="username">Korisnicko ime:</label>
                        <input type="text" name="username" placeholder='Unesite korisnicko ime' minlength='5' required><br>

                        <label for="password">Lozinka:</label>
                        <input type="password" name="password" placeholder="Unesite lozinku" minlength='5' required><br>

                        <label for="password_check">Potvrda lozinke:</label>
                        <input type="password" name="password_check" placeholder='Unesite potvrdu lozinke' minlength='5' required><br>

                        <label for="grad">Grad:</label>
                        <input type="text" name="grad" placeholder='Unesite grad' required><br>

                        <label for="adresa">Ulica i broj:</label>
                        <input type="text" name="adresa" placeholder='Unesite ulicu i broj' required><br>

                        <label for="telefon">Kontakt telefon:</label>
                        <input type="text" name="telefon" placeholder='Unesite kontakt telefon' required><br>

                        <label for="email">E-mail:</label>
                        <input type="text" name="email" placeholder="Unesite e-mail" required><br>

                        <input type="submit" value="Registruj se">

                    </form>
                </div>
                <hr>

            <jsp:include page="parts/footer.jsp"></jsp:include>
        </div>
    </body>

</html>