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

<!DOCTYPE html>
<html>
    <head>        
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
        <title>Kategorije ulaznica</title>
    </head>

    <body>
        <jsp:include page="parts/header.jsp"></jsp:include>

        <%
            ArrayList<StrukturaUlaznica> strukture = (ArrayList<StrukturaUlaznica>) request.getAttribute("strukture");
            if (strukture.size() > 0) {
        %>
        <div class="container-fluid pt-3 my-3">
            <div class='container-fluid'>

                <table class="table">
                    <thead class="text-primary">
                    <th>Kategorija: </th>
                    <th>Cena:</th>
                    <th>Broj dotupnih ulaznica: </th>    
                    <th></th>
                    </thead>
                    <%
                        for (StrukturaUlaznica struktura : strukture) {%>
                    <tr class="text-secondary">
                        <td><%= ((StrukturaUlaznica) struktura).getKategorija()%></td>
                        <td><%= ((StrukturaUlaznica) struktura).getCena()%></td>
                        <td><%= ((StrukturaUlaznica) struktura).getBrojDostupnihUlaznica()%></td>
                        <td><a href="izmenaStrukture?struktura_id=<%=String.valueOf(struktura.getId())%>">
                                <input type="button" class="btn btn-primary"name="izmeniStrukture" value="Izmeni Strukturu"></a></td>
                        <td><a href="brisanjeStrukture?struktura_id=<%=String.valueOf(struktura.getId())%>">
                                <input type="button" class="btn btn-primary" name="brisanjeStrukture" value="Obrisi Strukturu"></a></td>
                    </tr>
                    <%
                        }
                    %>
                </table>
            </div>

            <div class="container-fluid">
                <form action="sacuvajKategoriju">

                    <h3>Dodaj novu kategoriju ulaznica: </h3><br>
                    <input type="hidden" name="dogadjaj_id" value="<%= request.getAttribute("dogadjaj_id")%>" />
                    <label class='text-primary' for='kategorija'>Nova kategorija ulaznica:</label>
                    <input type='text' id='kategorija' name="kategorija" placeholder="Unesite naziv kategorije">
                    <label class='text-primary' for='cena'>| Cena:</label>
                    <input type='number' id="cena" name='cena'>
                    <label class='text-primary' for="broj_ulaznica">Broj dostupnih ulaznica:</label>
                    <input type="number" id='broj_ulaznica' name="broj_ulaznica" ><br>

                    <label class='text-primary' for="granica_po_korisniku">Granica po korisniku:</label>
                    <input type="number" id='granica_po_korisniku' name="granica_po_korisniku" ><br>

                    <input type="submit" class="btn btn-primary" value="Sacuvaj novu kategoriju">
                </form>
            </div>
            <jsp:include page="parts/footer.jsp"></jsp:include>
            </div>
        <% } else { %>
        <jsp:include page="parts/nema_rezultata.jsp"></jsp:include>
        <% }%>


    </body>

</html>