<%-- 
    Document   : admin_novi_korisnik
    Created on : Aug 29, 2020, 2:24:59 PM
    Author     : iq skola
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>

        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
        <title>Novi korisnik</title>
    </head>

    <body>
        <jsp:include page="parts/header.jsp"></jsp:include>
            <div class="container-fluid p-3 my-3 border">
                <h1 class="text-primary">Kreiranje novog korisnika:</h1>
                <form action="sacuvajKorisnika" method="post">

                    <div class="form-group">
                        <input type="radio" name='korisnik' id="reg_korisnik" value="Registrovani korisnik">
                        <label class='text-secondary p-1' for='reg_korisnik'>Novi registrovani korisnik</label>



                        <input type="radio" name='korisnik' id="blagajnik" value="Blagajnik">
                        <label class='text-secondary p-1' for='blagajnik'>Novi blagajnik</label>



                        <input type="radio" name='korisnik' id="admin" value="Administrator">
                        <label class='text-secondary p-1' for='administrator'>Novi administrator</label><br>
                    </div>

                    <div class="form-group">
                        <label class='text-secondary' for="ime">Ime:</label>
                        <input type="text" name="ime" placeholder='Unesite ime' required><br>
                    </div>

                    <div class="form-group">
                        <label class='text-secondary' for="prezime">Prezime:</label>
                        <input type="text" name="prezime" placeholder='Unesite prezime' required><br>
                    </div>

                    <div class="form-group">
                        <label class='text-secondary' for="username">Korisnicko ime:</label>
                        <input type="text" name="username" placeholder='Unesite korisnicko ime' minlength='5' required><br>
                    </div>

                    <div class="form-group">
                        <label class='text-secondary' for="password">Lozinka:</label>
                        <input type="password" name="password" placeholder="Unesite lozinku" minlength='5' required><br>
                    </div>

                    <div class="form-group">
                        <label class='text-secondary' for="password_check">Potvrda lozinke:</label>
                        <input type="password" name="password_check" placeholder='Unesite potvrdu lozinke' minlength='5' required><br>
                    </div>

                    <div class="form-group">
                        <label class='text-secondary' for="grad">Grad:</label>
                        <input type="text" name="grad" placeholder='Unesite grad'><br>
                    </div>

                    <div class="form-group">
                        <label class='text-secondary' for="adresa">Ulica i broj:</label>
                        <input type="text" name="adresa" placeholder='Unesite ulicu i broj'><br>
                    </div>

                    <div class="form-group">
                        <label class='text-secondary' for="telefon">Kontakt telefon:</label>
                        <input type="text" name="telefon" placeholder='Unesite kontakt telefon'><br>
                    </div>

                    <div class="form-group">
                        <label class='text-secondary' for="email">E-mail:</label>
                        <input type="text" name="email" placeholder="Unesite e-mail"><br>
                    </div>

                    <div class="form-group">
                        <label class='text-secondary' for="naziv_lokacije">Naziv lokacije:</label>
                        <input type="text" name="naziv_lokacije" placeholder="Unesite naziv lokacije"><br>
                    </div>

                    <div class="form-group">
                        <label class='text-secondary' for="grad_lokacije">Grad lokacije:</label>
                        <input type="text" name="grad_lokacije" placeholder="Unesite grad lokacije"><br>
                    </div>

                    <div class="form-group">
                        <label class='text-secondary' for="adresa_lokacije">Adresa lokacije:</label>
                        <input type="text" name="adresa_lokacije" placeholder="Unesite adresu lokacije"><br>
                    </div>

                    <input type="submit" class="btn btn-primary" name="posalji" value="Kreiraj">
                </form>


            <jsp:include page="parts/footer.jsp"></jsp:include>
        </div>
    </body>

</html>


