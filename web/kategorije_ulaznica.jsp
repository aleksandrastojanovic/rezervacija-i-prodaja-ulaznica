<%-- 
    Document   : kategorije_ulaznica
    Created on : Sep 22, 2020, 3:04:09 PM
    Author     : iq skola
--%>

<%@page import="klase.DogadjajBaza"%>
<%@page import="klase.Dogadjaj"%>
<%@page import="klase.Blagajnik"%>
<%@page import="klase.StrukturaUlaznica"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<% Blagajnik blagajnik = (Blagajnik) request.getAttribute("korisnik");
    ArrayList<StrukturaUlaznica> strukture = (ArrayList<StrukturaUlaznica>) request.getAttribute("strukture");
%>
<!DOCTYPE html>
<html>
    <head>
        <title>Kategorije ulaznica</title>
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
                    <td><a href="izmenaStrukture?struktura_id=<%=String.valueOf(struktura.getId())%>">
                            <input type="button" name="izmeniStrukture" value="Izmeni Strukturu"></a></td>
                </tr>
                <%
                    }
                %>
            </table>
        </div>
            
        <div>
            <form action="sacuvajKategoriju">

                <h3>Dodaj novu kategoriju ulaznica: </h3><br>
                <input type="hidden" name="dogadjaj_id" value="<%= request.getAttribute("dogadjaj_id") %>" />
                <label for='kategorija'>Nova kategorija ulaznica:</label>
                <input type='text' id='kategorija' name="kategorija" placeholder="Unesite naziv kategorije">
                <label for='cena'>| Cena:</label>
                <input type='number' id="cena" name='cena'>
                <label for="broj_ulaznica">| Limit:</label>
                <input type="number" id="broj_ulaznica" name="broj_ulaznica"><br>
                <input type="submit" value="Sacuvaj novu kategoriju">
            </form>
        </div>

        <footer>
            <!-- Povratak na vrh -->

        </footer>
    </body>

</html>