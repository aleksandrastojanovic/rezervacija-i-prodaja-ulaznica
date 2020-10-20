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
            <div>
                <h1>Kreiranje novog korisnika:</h1>
                <form action="noviKorisnik" method="post">

                    <input type="radio" name='korisnik' id="reg_korisnik" value="Registrovani korisnik">
                    <label for='reg_korisnik'>Novi registrovani korisnik:</label><br>

                    <input type="radio" name='korisnik' id="blagajnik" value="Blagajnik">
                    <label for='blagajnik'>Novi blagajnik:</label><br>

                    <input type="radio" name='korisnik' id="admin" value="Administrator">
                    <label for='administrator'>Novi administrator</label><br>

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
                    <input type="text" name="grad" placeholder='Unesite grad'><br>

                    <label for="adresa">Ulica i broj:</label>
                    <input type="text" name="adresa" placeholder='Unesite ulicu i broj'><br>

                    <label for="telefon">Kontakt telefon:</label>
                    <input type="text" name="telefon" placeholder='Unesite kontakt telefon'><br>

                    <label for="email">E-mail:</label>
                    <input type="text" name="email" placeholder="Unesite e-mail"><br>

                    <label for="naziv_lokacije">Naziv lokacije:</label>
                    <input type="text" name="naziv_lokacije" placeholder="Unesite naziv lokacije"><br>

                    <label for="grad_lokacije">Grad lokacije:</label>
                    <input type="text" name="grad_lokacije" placeholder="Unesite grad lokacije"><br>

                    <label for="adresa_lokacije">Adresa lokacije:</label>
                    <input type="text" name="adresa_lokacije" placeholder="Unesite adresu lokacije"><br>

                    <input type="submit" name="posalji" value="Posalji">
                </form>
            </div>
            <hr>

            <!-- <div>
                 <h1>Kreiranje novog aministratora</h1>
                 <form>
                     <label for="novi_admin_korisnicko_ime">Korisnicko ime:</label>
                     <input type='text' id="novi_admin_kornisnicko_ime" name='novi_admin_korisnicko_ime'
                            placeholder='Unesite korisnicko ime' required><br>
                     
                     <label for="novi_admin_lozinka">Lozinka:</label>
                     <input type='password' id="novi_admin_lozinka" name='novi_admin_lozinka'
                            placeholder='Unesite lozinku' required><br>
                     <input type="submit" name='posalji_novi_admin' value="Kreiraj novog administratora">
                 </form>
             </div> -->

        <jsp:include page="parts/footer.jsp"></jsp:include>
    </body>

</html>


