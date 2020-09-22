<%-- 
    Document   : blagajnik_update
    Created on : Aug 29, 2020, 2:23:18 PM
    Author     : iq skola
--%>

<%@page import="klase.StrukturaUlaznica"%>
<%@page import="java.util.ArrayList"%>
<%@page import="klase.Dogadjaj"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% Dogadjaj dogadjaj = (Dogadjaj) request.getAttribute("dogadjaj");
    ArrayList<StrukturaUlaznica> strukture = (ArrayList<StrukturaUlaznica>) request.getAttribute("strukture");%>
<!DOCTYPE html>
<html>
    <head>
        <title>Blagajnik - Update</title>
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

        <div>
            <form action="sacuvajDogadjaj" method="post">
                <input type="hidden" name="dogadjaj_id" value="<%= String.valueOf(dogadjaj.getId()) %>" />
                <label for="naziv">Naziv:</label>
                <input type='text' id='naziv' name="naziv" value="<%= dogadjaj.getNaziv()%>"><br>

                <!--
                Mislim da mi ovaj deo uopste ne treba, prikaz samo komplikuje zivot
                <label for='mesto_odrzavanja'>Mesto odrzavanja:</label><br>
                 automatski se popunjava, samo ne znam kako :D 
                input polje value postavim na mesto blagajnika, i onda on ne moze da menja
                -->

                <label for="vreme_odrzavanja">Vreme odrzavanja:</label>
                <input type="datetime-local" id="vreme_odrzavanja" name="vreme_odrzavanja" value="<%= dogadjaj.getDatum_i_vreme()%>"><br>

                <label for="detalji">Detalji dogadjaja</label>
                <input type="text" id='detalji' name="detalji" placeholder='Unesite detalje dogadjaja' value="<%= dogadjaj.getDetalji()%>"><br>

                <label for="slika_glavna">Dodaj glavnu fotografiju:</label>
                <input type="file" id="slika_glavna" name="slika_glavna" accept="image/*" ><br>

                <label for="slike">Dodaj ostale fotografije:</label>
                <input type="file" id="slike" name="slike" accept="image/*" multiple><br>                

                <label for="video">Dodaj video zapis</label>
                <input type="file" id="video" name="video" accept="video/*"><br>

                <!--Preko js bi trebalo da napravim da bude required cena i limit ako je cekirano -->
                

                <!-- Ne znam da li ovde sme multiple input, 
                posto treba da ostanu uvezane informacije koje unosi, 
                nisam sigurna kako se posle pristupa-->
                <label for='nova_kategorija'>Dodaj novu kategoriju ulaznica:</label>
                <input type='text' id='nova_kategorija' name="nova_kategorija" placeholder='Unesite novu kateoriju ulaznica'>
                <label for='nova_kategorija_cena'>| Cena:</label>
                <input type='number' id="nova_kategorija_cena" name='nova_kategorija_cena'>
                <label for="limit_nova_kategorija">| Limit:</label>
                <input type="number" id="limit_nova_kategorija" name="limit_nova_kategorija"><br>
                <input type="submit" value="Sacuvaj izmene">

            </form>
                <form action="izmenaStruktura">
                    <div>
                    <table>
                        <thead>
                        <th>Kategorija: </th>
                        <th>Cena:</th>
                        <th>Broj dotupnih ulaznica: </th>    
                        <th></th>
                        </thead>
                        <%
                    for (StrukturaUlaznica struktura : strukture) {%>
                        <tr>
                            <td><%= ((StrukturaUlaznica) struktura).getKategorija()%></td>
                            <td><%= ((StrukturaUlaznica) struktura).getCena()%></td>
                            <td><%= ((StrukturaUlaznica) struktura).getBroj_dostupnih_ulaznica()%></td>
                            <td><a href=""><input type="button" name="izmeniStrukture" value="IzmeniStrukturu"></a></td>
                        </tr>
                        <%
                            }
                        %>
                    </table>
                </div>
                </form>
        </div>
        <div></div>

        <footer>
            <!-- Povratak na vrh -->

        </footer>
    </body>

</html>


