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
            
        </style>
        <title>Registracija</title>
    </head>

    <body> 
        <jsp:include page="parts/header.jsp"></jsp:include>


            
                <div class="container-fluid p-3 mb-3 text-center w-25 border">            
                    <h1 class="text-primary text-center">Registracija:</h1>


                    <form action='registracija' method="post">

                        
                            <div class="form-group p-1 m-1 row text-left">
                                <label class="text-primary col-sm-6" for="ime">Ime:</label>
                                <input class="border input-sm col-sm-6" type="text" name="ime" placeholder='Ime' required><br>
                            </div>

                            <div class="form-group p-1 m-1 row text-left">
                                <label class="text-primary col-sm-6" for="prezime">Prezime:</label>
                                <input class="border input-sm col-sm-6" type="text" name="prezime" placeholder='Prezime' required><br>
                            </div>

                            <div class="form-group p-1 m-1 row text-left">
                                <label class="text-primary col-sm-6" for="username">Korisnicko ime:</label>
                                <input class="border input-sm col-sm-6" type="text" name="username" placeholder='Korisnicko ime' onblur="updateOutput(this.value)" minlength='5' required onkeyup="updateOutput(this.value)">
                                <span id="userNameMessage" class="text-secondary p-0 m-0 text-center"></span><br>
                            </div>

                            <div class="form-group p-1 m-1 row text-left">
                                <label class="text-primary col-sm-6" for="password">Lozinka:</label>
                                <input class="border input-sm col-sm-6" id='password' type="password" name="password" placeholder="Lozinka" minlength='5' required pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{5,}" title="Mora sazdrzati bar 1 cifru, 1 malo i 1 veliko slovo, i biti duzine 5 ili vise karaktera"><br>
                            </div>

                            <div class="form-group p-1 m-1 row text-left">
                                <label class="text-primary col-sm-6" for="password_check">Potvrda lozinke:</label>
                                <input class="border input-sm col-sm-6" id='password_check' type="password" name="password_check" placeholder='Potvrda lozinke' minlength='5' required ><br>

                            </div>

                            <div class="form-group p-1 m-1 row text-left">
                                <label class="text-primary col-sm-6" for="grad">Grad:</label>
                                <input class="border input-sm col-sm-6" type="text" name="grad" placeholder='Grad' required><br>
                            </div>

                            <div class="form-group p-1 m-1 row text-left">
                                <label class="text-primary col-sm-6" for="adresa">Ulica i broj:</label>
                                <input class="border input-sm col-sm-6" type="text" name="adresa" placeholder='Ulica i broj' required><br>
                            </div>

                            <div class="form-group p-1 m-1 row text-left">
                                <label class="text-primary col-sm-6" for="telefon">Kontakt telefon:</label>
                                <input class="border input-sm col-sm-6" type="text" name="telefon" placeholder='Kontakt telefon' required><br>
                            </div>

                            <div class="form-group p-1 m-1 row text-left">
                                <label class="text-primary col-sm-6" for="email">E-mail:</label>
                                <input class="border input-sm col-sm-6" type="text" name="email" placeholder="E-mail" required><br>
                            </div>

                            <div class="d-flex justify-content-center pt-3">
                                <input type="submit" name="submit" class="btn btn-primary text-center p-3 btn-lg" value="Registruj se">
                            </div>

                    </form>


            <jsp:include page="parts/footer.jsp"></jsp:include>
            <script type="text/javascript" src='provera_korisnickog_imena.js'>
            </script>
        </div>
    </body>

</html>