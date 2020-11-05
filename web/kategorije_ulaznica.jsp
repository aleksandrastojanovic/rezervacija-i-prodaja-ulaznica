<%-- 
    Document   : kategorije_ulaznica
    Created on : Sep 22, 2020, 3:04:09 PM
    Author     : iq skola
--%>

<%@page import="modeli.*"%>
<%@page import="bazaKlase.*"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>        
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="parts/pozadina.css">
        <link rel="icon" href="favicon.ico" type="image/icon type">
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
                <h1 class="text-center text-primary p-3">Kategorije ulaznica:</h1>
                <table class="table">
                    <thead class="text-primary">
                    <th>Kategorija: </th>
                    <th>Cena:</th>
                    <th>Broj dotupnih ulaznica: </th>    
                    <th></th>
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


            <div class="w-100 border rounded p-3">
                <h2 id='naslov' class="text-primary text-center">Dodaj novu kategoriju:</h2>
                <form id='forma' action='sacuvajKategoriju' class="w-100">
                    <table class="table w-100">
                        <input type="hidden" name="dogadjaj_id" value='<%= request.getAttribute("dogadjaj_id")%>' />

                        <tr class="form-group p-1 m-1 row text-left w-100">
                            <td class=" col-6"><label class="text-primary" for='kategorija'>Nova kategorija ulaznica:</label></td>
                            <td class=" col-6"><input class="border input-sm col-6" type='text' id='kategorija' name="kategorija" placeholder='Unesi kategoriju ulaznica'></td>
                        </tr>
                        <tr class="form-group p-1 m-1 row text-left">
                            <td class=" col-6"><label class="text-primary col-sm-6" for='cena'>Cena:</label></td>
                            <td class=" col-6"><input class="border input-sm col-6" type='number' id="cena" name='cena'></td>
                        </tr>

                        <tr class="form-group p-1 m-1 row text-left">
                            <td class=" col-6"><label class="text-primary col-sm-6" for="broj_ulaznica">Broj dostupnih ulaznica:</label></td>
                            <td class=" col-6"><input class="border input-sm col-6" type="number" id="broj_ulaznica" name="broj_ulaznica"></td>
                        </tr>


                        <tr class="form-group p-1 m-1 row text-left">
                            <td class=" col-6"> <label class="text-primary col-sm-6" for="granica_po_korisniku">Granica po korisniku:</label></td>
                            <td class=" col-6"><input class="border input-sm col-6" type="number" id="granica_po_korisniku" name="granica_po_korisniku"></td>>
                        </tr>

                        <tr class="form-group p-1 m-1 row text-left justify-content-center">
                            <td class=" col-12"><input type="submit" class="btn btn-primary" value="Sacuvaj novu kategoriju"></td>
                        </tr>
                    </table>
                </form>
            </div>
        </div>
        <jsp:include page="parts/footer.jsp"></jsp:include>


        <% } else { %>
        <jsp:include page="parts/nema_rezultata.jsp"></jsp:include>
        <% }%>


    </body>

</html>