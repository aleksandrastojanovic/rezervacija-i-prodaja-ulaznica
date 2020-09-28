<%@page import="java.time.LocalDateTime"%>
<%@page import="klase.Dogadjaj"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% ArrayList<Dogadjaj> dogadjaji = (ArrayList<Dogadjaj>)request.getAttribute("dogadjaji");
    String korisnik_id = "" + request.getSession().getAttribute("korisnik_id");
%>
<!DOCTYPE html>
<html>
    <head>
        <title>Pocetna stranica</title>
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
            <form action="pretragaDogadjaja" method="post">
                <label for='naziv'>Pretrazi po nazivu:</label>
                <input type="text" id="naziv" name='naziv' placeholder='Unesite naziv'><br>

                <label for='vreme_od'>Pretrazi po datumu od:</label>
                <input type="datetime-local" id="vreme_od" name='vreme_od' placeholder='Unesite od kog datuma' value="2001-01-01T00:00">
                <label for='vreme_do'> do:</label>
                <input type="datetime-local" id="vreme_do" name='vreme_do' placeholder='Unesite do kog datuma' value="2001-01-01T00:00"><br>

                <label for='mesto'>Pretrazi po mestu odrzavanja:</label>
                <input type="text" id="po_mestu" name='mesto' placeholder='Unesite mesto odrzavanja'><br>
                
                <input type="submit" value="Pretrazi">
            </form>
        </div>
        <div>
            <table>
                <thead>
                <th>Naziv dogadjaja</th>
                <th>Naziv lokacije</th>
                <th>Datum i vreme</th>
                <th>Detalji</th>
                </thead>
                <%
                    for (Dogadjaj dogadjaj : dogadjaji) {%>
                <tr>
                    <td><%= ((Dogadjaj) dogadjaj).getNaziv()%></td>
                    <td><%= ((Dogadjaj) dogadjaj).getNaziv_lokacije()%></td>
                    <td><%= ((Dogadjaj) dogadjaj).getDatum_i_vreme()%></td>
                    <% if (korisnik_id != null) {%>                    
                    <td><a href="dogadjajPojedinacno?dogadjaj_id=<%= "" + dogadjaj.getId()%>">
                            <input type="button" name="dogadjaj_pojedinacno" value="Detaljnije"></a></td>
                            <% } else { %>
                    <td><a href="registracija.jsp">
                            <input type="button" name="registracija" value="Detaljnije"></a></td>    
                            <% } %>
                </tr>
                <%
                    }
                %>
                <!-- <tr>
                    <td></td>
                </tr> -->
            </table>
        </div>
                <hr>
                <div><a href="mojeUlaznice">Moje ulaznice</a></div>

        <footer>
            <!-- Povratak na vrh -->

        </footer>
    </body>

</html>


