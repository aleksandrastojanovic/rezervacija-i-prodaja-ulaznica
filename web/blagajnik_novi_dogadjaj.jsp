<%-- 
    Document   : blaganik_novi_dogadjaj
    Created on : Aug 29, 2020, 2:32:39 PM
    Author     : iq skola
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
        <nav>
        <ul>
            <li>Pocetna stranica</li>
            <li><ul>
                    <li>Pozoriste</li>
                    <li>Muzika</li>
                    <li>Sport</li>
                    <li>Festivali</li>
                    <li>Muzeji</li>
                    <li>Ostalo</li>
                </ul>
            </li>            
            <li>Registruj se</li>
            <li>Prijavi se</li>
                        
        </ul>
        </nav>
    </header>
        <h1>Kreiranje novog dogadjaja</h1>
        <div>
            <form>
                
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
                
                <input type='checkbox' id='parter1' name='kategorija[]' value='Parter 1'>
                <label for='parter1'>Parter 1</label>
                <label for cena_parter1>| Cena - Parter 1</label>
                <input type='number' id="cena_parter1" name='cena_parter1'>
                <label for="limit_parter1">| Limit:</label>
                <input type="number" id="limit_parter1" name="limit_parter1">
                <br>
                <input type='checkbox' id='parter2' name='kategorija[]' value='Parter 2'>
                <label for='parter1'>Parter 2</label>
                <label for='cena_parter2'>| Cena - Parter 2</label>
                <input type='number' id="cena_parter2" name='cena_parter2'>
                <label for="limit_parter2">| Limit:</label>
                <input type="number" id="limit_parter2" name="limit_parter2"><br>
                
                <input type='checkbox' id='fan_pit' name='kategorija[]' value='Fan pit'>
                <label for='fan_pit'>Fan pit</label>
                <label for='fan_pit_cena'>| Cena - Fan pit</label>
                <input type='number' id="fan_pit_cena" name='fan_pit_cena'>
                <label for="limit_fan_pit">| Limit:</label>
                <input type="number" id="limit_fan_pit" name="limit_fan_pit"><br>
                
                <input type='checkbox' id='galerija' name='kategorija[]' value='Galerija'>
                <label for='galerija'>Galerija</label>
                <label for='galerija_cena'>| Cena - Galerija</label>
                <input type='number' id="galerija_cena" name='galerija_cena'>
                <label for="limit_galerija">| Limit:</label>
                <input type="number" id="limit_galerija" name="limit_galeirja"><br>
                
                <!-- Ne znam da li ovde sme multiple input, 
                posto treba da ostanu uvezane informacije koje unosi, 
                nisam sigurna kako se posle pristupa-->
                <label for='nova_kategorija'>Nova kategorija ulaznica:</label>
                <input type='text' id='nova_kategorija' name="nova_kategorija" placeholder='Unesite novu kateoriju ulaznica'>
                <label for='nova_kategorija_cena'>| Cena:</label>
                <input type='number' id="nova_kategorija_cena" name='nova_kategorija_cena'>
                <label for="limit_nova_kategorija">| Limit:</label>
                <input type="number" id="limit_nova_kategorija" name="limit_nova_kategorija"><br>
                <input type="submit" value="Kreiraj novi dogadjaj">
                                                           
            </form>
        </div>
        
        <footer>
        <!-- Povratak na vrh -->
        
    </footer>
    </body>
    
</html>


