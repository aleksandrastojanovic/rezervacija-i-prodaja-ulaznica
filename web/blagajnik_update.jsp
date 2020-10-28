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

            <div class="container-fluid pt-3 my-3 border">

            <% Dogadjaj dogadjaj = (Dogadjaj) request.getAttribute("dogadjaj");
                ArrayList<StrukturaUlaznica> strukture = (ArrayList<StrukturaUlaznica>) request.getAttribute("strukture");%>

            <div class="container mt-3 p-5 border border-primary rounded-lg">
                <h2 class="text-primary">Izmena dogadjaja:</h2>
                <form action="sacuvajDogadjaj" method="post">

                    <input type="hidden" name="dogadjaj_id" value="<%= String.valueOf(dogadjaj.getId())%>">
                    <input type="hidden" name="noviDogadjaj" value="ne">

                    <div class="form-group">
                        <label for="naziv">Naziv:</label>
                        <input type='text' id='naziv' name="naziv" value="<%= dogadjaj.getNaziv()%>"><br>
                    </div>

                    <div class="form-group">
                        <label for="vreme_odrzavanja">Vreme odrzavanja:</label>
                        <%DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy. HH:mm");
                            String datumIVreme = dogadjaj.getDatumIVreme().format(formatter);%>
                        <input type="datetime-local" id="vreme_odrzavanja" name="vreme_odrzavanja" value="<%= datumIVreme%>"><br>
                    </div>

                    <div class="form-group">
                        <label for="detalji">Detalji dogadjaja</label>
                        <input type="text" id='detalji' name="detalji" placeholder='Unesite detalje dogadjaja' value="<%= dogadjaj.getDetalji()%>"><br>
                    </div>

                    <input type="submit" class="btn btn-primary" value="Sacuvaj izmene">

                </form>
            </div>
            <div class="container mt-3 p-5 border border-primary rounded-lg">
                <h2 class="text-primary">Nova kategorija: </h2>
                <form action="sacuvajKategoriju">
                    <label for='nova_kategorija'>Dodaj novu kategoriju ulaznica:</label>
                    <input type='text' id='nova_kategorija' name="nova_kategorija" placeholder='Unesite novu kategoriju'>
                    <label for='nova_kategorija_cena'>| Cena:</label>
                    <input type='number' id="nova_kategorija_cena" name='nova_kategorija_cena'>
                    <label for="limit_nova_kategorija">| Limit:</label>
                    <input type="number" id="limit_nova_kategorija" name="limit_nova_kategorija"><br>
                    <input type="submit" class="btn btn-primary" value='Sacuvaj'>
                </form>
            </div>
            <div class="container mt-3 p-5 border border-primary rounded-lg">
                <h2 class="text-primary">Izmena postojecih kategorija:</h2>
                <form action="izmenaStrukture">
                    <div>
                        <table class="table table-striped">
                            <thead class="text-primary">
                            <th>Kategorija: </th>
                            <th>Cena:</th>
                            <th>Broj dotupnih ulaznica: </th>    
                            <th></th>
                            </thead>
                            <%
                                for (StrukturaUlaznica struktura : strukture) {%>
                            <tr class="text-secondary">
                            <input type="hidden" value="<%= struktura.getId()%>" name='struktura_id'>
                            <td><%= ((StrukturaUlaznica) struktura).getKategorija()%></td>
                            <td><%= ((StrukturaUlaznica) struktura).getCena()%></td>
                            <td><%= ((StrukturaUlaznica) struktura).getBrojDostupnihUlaznica()%></td>
                            <td><input type="submit" class="btn btn-primary" name="izmeniStrukture" value="Izmeni Strukturu"></td>

                            </tr>
                            <%
                                }
                            %>
                        </table>
                    </div>
                </form>
            </div>
        </div>
        <div>

        </div>

        <jsp:include page="parts/footer.jsp"></jsp:include>
    </div>

</html>


