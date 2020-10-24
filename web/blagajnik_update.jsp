<%-- 
    Document   : blagajnik_update
    Created on : Aug 29, 2020, 2:23:18 PM
    Author     : iq skola
--%>

<%@page import="java.time.format.DateTimeFormatter"%>
<%@page import="klase.StrukturaUlaznica"%>
<%@page import="java.util.ArrayList"%>
<%@page import="klase.Dogadjaj"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>

        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
        <title>Blagajnik - Update</title>
    </head>

    <body>
        <jsp:include page="parts/header.jsp"></jsp:include>

        <% Dogadjaj dogadjaj = (Dogadjaj) request.getAttribute("dogadjaj");
            ArrayList<StrukturaUlaznica> strukture = (ArrayList<StrukturaUlaznica>) request.getAttribute("strukture");%>

        <div>
            <form action="sacuvajDogadjaj" method="post">
                <input type="hidden" name="dogadjaj_id" value="<%= String.valueOf(dogadjaj.getId())%>">
                <input type="hidden" name="noviDogadjaj" value="ne">

                <label for="naziv">Naziv:</label>
                <input type='text' id='naziv' name="naziv" value="<%= dogadjaj.getNaziv()%>"><br>

                <label for="vreme_odrzavanja">Vreme odrzavanja:</label>
                <%DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
                    String datumIVreme = dogadjaj.getDatumIVreme().format(formatter);%>
                <input type="datetime-local" id="vreme_odrzavanja" name="vreme_odrzavanja" value="<%= datumIVreme%>"><br>

                <label for="detalji">Detalji dogadjaja</label>
                <input type="text" id='detalji' name="detalji" placeholder='Unesite detalje dogadjaja' value="<%= dogadjaj.getDetalji()%>"><br>

                <input type="submit" value="Sacuvaj izmene">

            </form>
            <h2>Nova kategorija: </h2>
            <form action="sacuvajKategoriju">
                <label for='nova_kategorija'>Dodaj novu kategoriju ulaznica:</label>
                <input type='text' id='nova_kategorija' name="nova_kategorija" placeholder='Unesite novu kateoriju ulaznica'>
                <label for='nova_kategorija_cena'>| Cena:</label>
                <input type='number' id="nova_kategorija_cena" name='nova_kategorija_cena'>
                <label for="limit_nova_kategorija">| Limit:</label>
                <input type="number" id="limit_nova_kategorija" name="limit_nova_kategorija"><br>
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
                            <td><%= ((StrukturaUlaznica) struktura).getBrojDostupnihUlaznica()%></td>
                            <td><a href=""><input type="button" name="izmeniStrukture" value="Izmeni Strukturu"></a></td>

                        </tr>
                        <%
                            }
                        %>
                    </table>
                </div>
            </form>
        </div>
        <div></div>

        <jsp:include page="parts/footer.jsp"></jsp:include>

</html>


