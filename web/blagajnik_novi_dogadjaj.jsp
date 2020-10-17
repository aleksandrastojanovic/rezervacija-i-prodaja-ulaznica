<%-- 
    Document   : blaganik_novi_dogadjaj
    Created on : Aug 29, 2020, 2:32:39 PM
    Author     : iq skola
--%>

<%@page import="klase.Blagajnik"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% Blagajnik blagajnik = (Blagajnik)request.getAttribute("korisnik_id"); %>
<!DOCTYPE html>
<html>
    <head>
        <title>Novi dogadjaj</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>
    
    <body>
        <header>
        <!-- Meni -->
        <jsp:include page="parts/meni.jsp"></jsp:include>
    </header>
        <h1>Kreiranje novog dogadjaja</h1>
        <div>
            <form action="sacuvajDogadjaj" method="post">
                
                <label>Mesto odrzavanja: </label>
                <%= blagajnik.getNaziv_lokacije() %><br>
                
                <label for="naziv">Naziv:</label>
                <input type='text' id='naziv' placeholder="Unesite naziv" required><br>
                
                <!--
                Mislim da mi ovaj deo uopste ne treba, prikaz samo komplikuje zivot
                <label for='mesto_odrzavanja'>Mesto odrzavanja:</label><br>
                 automatski se popunjava, samo ne znam kako :D 
                input polje value postavim na mesto blagajnika, i onda on ne moze da menja
                -->
                
                <label for="vreme_odrzavanja">Vreme odrzavanja:</label>
                <input type="datetime-local" id="vreme_odrzavanja" name="vreme_odrzavanja" required><br>
                
                <label for="detalji">Detalji dogadjaja</label>
                <input type="text" id='detalji' name="detalji" placeholder='Unesite detalje dogadjaja' required><br>
                
                <label for="slika_glavna">Dodaj glavnu fotografiju:</label>
                <input type="file" id="slika_glavna" name="slika_glavna" accept="image/*" required><br>
                
                <label for="slike">Dodaj ostale fotografije:</label>
                <input type="file" id="slike" name="slike" accept="image/*" multiple><br>                
                
                <label for="video">Dodaj video zapis</label>
                <input type="file" id="video" name="video" accept="video/*"><br>
                
                <!--Preko js bi trebalo da napravim da bude required cena i limit ako je cekirano -->
                

                <!-- Ne znam da li ovde sme multiple input, 
                posto treba da ostanu uvezane informacije koje unosi, 
                nisam sigurna kako se posle pristupa-->
                <label for='nova_kategorija'>Nova kategorija ulaznica:</label>
                <input type='text' id='nova_kategorija' name="nova_kategorija" placeholder='Unesite novu kateoriju ulaznica'>
                <label for='nova_kategorija_cena'>| Cena:</label>
                <input type='number' id="nova_kategorija_cena" name='nova_kategorija_cena'>
                <label for="limit_nova_kategorija">| Broj dostupnih ulaznica:</label>
                <input type="number" id="limit_nova_kategorija" name="limit_nova_kategorija"><br>
                <label for="granica_po_korisniku">| Granica po korisniku:</label>
                <input type="number" id="granica_po_korisniku" name="granica_po_korisniku"><br>
                <input type="submit" value="Kreiraj novi dogadjaj">
                                                           
            </form>
        </div>
        
        <footer>
        <!-- Povratak na vrh -->
        
    </footer>
    </body>
    
</html>


