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
        <link rel="stylesheet" href="parts/pozadina.css">
        <style>

            #akoRegistrovaniKorisnik, #akoBlagajnik { display: none;}
            #registrovani_korisnik:checked ~ #akoRegistrovaniKorisnik {display: block;} 
            #blagajnik:checked ~ #akoBlagajnik {display: block;}
        </style>
        <title>Novi korisnik</title>
    </head>

    <body>
        <jsp:include page="parts/header.jsp"></jsp:include>

            <div class="container-fluid p-3 mb-3 w-50 h-100 border">
                <h1 class="text-primary text-center">Kreiranje novog korisnika:</h1>
                <form action="sacuvajKorisnika" method="post">


                    <input type="radio" class="" name='korisnik' id="registrovani_korisnik" value="Registrovani korisnik">
                    <label class='text-secondary p-1' for='registrovani_korisnik'>Novi registrovani korisnik</label>


                    <input type="radio" class="" name='korisnik' id="blagajnik" value="Blagajnik">
                    <label class='text-secondary p-1' for='blagajnik'>Novi blagajnik</label>


                    <input type="radio" class="" name='korisnik' id="administrator" value="Administrator" checked>
                    <label class='text-secondary p-1' for='administrator'>Novi administrator</label><br>
                    <br>

                    <label class='text-primary text-left' for="ime">Ime:</label>
                    <input type="text" class=" w-100" name="ime" placeholder='Unesite ime' required><br>
                    <br>

                    <label class='text-primary text-left' for="prezime">Prezime:</label>
                    <input type="text" class=" w-100" name="prezime" placeholder='Unesite prezime' required><br>
                    <br>

                    <label class='text-primary text-left' for="username">Korisnicko ime:</label>
                    <input type="text" class=" w-100" name="username" placeholder='Unesite korisnicko ime' minlength='5' required onblur="updateOutput(this.value)" onkeyup="updateOutput(this.value)">
                    <span id="userNameMessage" class="text-secondary p-0 m-0 text-center"></span><br>
                    <br>

                    <label class='text-primary text-left' for="password">Lozinka:</label>
                    <input id="password" class=" w-100 " type="password" name="password" placeholder="Unesite lozinku" minlength='5' required pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{5,}" title="Mora sazdrzati bar 1 cifru, 1 malo i 1 veliko slovo, i biti duzine 5 ili vise karaktera"><br>
                    <br>

                    <label class='text-primary text-left' for="password_check">Potvrda lozinke:</label>
                    <input id="password_check" class="w-100" type="password" name="password_check" placeholder='Unesite potvrdu lozinke' minlength='5' required><br>
                    <br>


                    <div id="akoRegistrovaniKorisnik">

                        <label class='text-primary text-left' for="grad">Grad:</label>
                        <input type="text" class="w-100" name="grad" placeholder='Unesite grad' ><br>
                        <br>

                        <label class='text-primary text-left' for="adresa">Ulica i broj:</label>
                        <input type="text" class="w-100" name="adresa" placeholder='Unesite ulicu i broj' ><br>
                        <br>

                        <label class='text-primary text-left' for="telefon">Kontakt telefon:</label>
                        <input type="text" class="w-100" name="telefon" placeholder='Unesite kontakt telefon' ><br>
                        <br>

                        <label class='text-primary text-left' for="email">E-mail:</label>
                        <input type="text" class="w-100" name="email" placeholder="Unesite e-mail" ><br>
                        <br>
                    </div>


                    <div id="akoBlagajnik">

                        <label class='text-primary text-left' for="naziv_lokacije">Naziv lokacije:</label>
                        <input type="text" class="w-100" name="naziv_lokacije" placeholder="Unesite naziv lokacije" ><br>
                        <br>

                        <label class='text-primary text-left' for="grad_lokacije">Grad lokacije:</label>
                        <input type="text" class="w-100" name="grad_lokacije" placeholder="Unesite grad lokacije" ><br>
                        <br>

                        <label class='text-primary text-left' for="adresa_lokacije">Adresa lokacije:</label>
                        <input type="text" class="w-100" name="adresa_lokacije" placeholder="Unesite adresu lokacije" ><br>
                        <br>
                    </div>




                    <div class="d-flex justify-content-center pt-3">
                        <input type="submit" name="posalji" class="btn btn-primary text-center p-3 btn-lg" value="Kreiraj">
                    </div>


                </form>

            </div>
        <jsp:include page="parts/footer.jsp"></jsp:include>
        <script type="text/javascript" src='provera_korisnickog_imena.js'>
        </script>


    </body>

</html>


