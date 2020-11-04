<%-- 
    Document   : blagajnik_update
    Created on : Aug 29, 2020, 2:23:18 PM
    Author     : iq skola
--%>

<%@page import="java.time.format.DateTimeFormatter"%>
<%@page import="modeli.*"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>

        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
        <link rel="stylesheet" href="parts/pozadina.css">
        <title>Blagajnik - Update</title>
    </head>

    <body>
        <jsp:include page="parts/header.jsp"></jsp:include>

            <div class="container-fluid pt-3 my-3 border">

            <% Dogadjaj dogadjaj = (Dogadjaj) request.getAttribute("dogadjaj");
                ArrayList<StrukturaUlaznica> strukture = (ArrayList<StrukturaUlaznica>) request.getAttribute("strukture");%>

            <div class="container m-3 p-5 border border-primary rounded-lg w-100">
                <h2 class="text-primary text-center p-3">Izmena dogadjaja:</h2>
                <form action="sacuvajDogadjaj" method="post" class="w-100">

                    <input class="border input-sm col-sm-6" type="hidden" name="dogadjaj_id" value="<%= String.valueOf(dogadjaj.getId())%>">
                    <input class="border input-sm col-sm-6" type="hidden" name="noviDogadjaj" value="ne">

                    <div class="form-group p-1 m-1 row text-left">
                        <label class="text-primary col-sm-6" for="naziv">Naziv:</label>
                        <input class="border input-sm col-sm-6" type='text' id='naziv' name="naziv" value="<%= dogadjaj.getNaziv()%>"><br>
                    </div>

                    <div class="form-group p-1 m-1 row text-left">
                        <label class="text-primary col-sm-6" for="vreme_odrzavanja">Vreme odrzavanja:</label>
                        <input class="border input-sm col-sm-6" type="datetime-local" id="vreme_odrzavanja" name="vreme_odrzavanja" value="<%= dogadjaj.getDatumIVreme()%>"><br>
                    </div>

                    <div class="form-group p-1 m-1 row text-left">
                        <label class="text-primary col-sm-6" for="detalji">Detalji dogadjaja</label>
                        <input class="border input-sm col-sm-6" type="text" id='detalji' name="detalji" placeholder='Unesite detalje dogadjaja' value="<%= dogadjaj.getDetalji()%>"><br>
                    </div>
                    <div class="d-flex justify-content-center pt-3">
                        <input type="submit" class="btn btn-primary" value="Sacuvaj izmene">
                    </div>

                </form>
            </div>
            <div class="container m-3 p-5 border border-primary rounded-lg w-100">
                <h2 class="text-primary text-center p-3">Nova kategorija: </h2>
                <form action="sacuvajKategoriju"  class="w-100">
                    <div class="form-group p-1 m-1 row text-left">
                        <label class="text-primary col-sm-6" for='kategorija'>Dodaj novu kategoriju ulaznica:</label>
                        <input class="border input-sm col-sm-6" type='text' id='kategorija' name="kategorija" placeholder='Unesite novu kategoriju'>
                    </div>
                    <div class="form-group p-1 m-1 row text-left">

                        <label class="text-primary col-sm-6" for='cena'>Cena:</label>
                        <input class="border input-sm col-sm-6" type='number' id="cena" name='cena'>
                    </div>
                    <div class="form-group p-1 m-1 row text-left">
                        <label class="text-primary col-sm-6" for="broj_ulaznica">Broj dostupnih ulaznica:</label>
                        <input class="border input-sm col-sm-6" type="number" id="broj_ulaznica" name="broj_ulaznica">
                    </div>
                    <div class="form-group p-1 m-1 row text-left">
                        <label class="text-primary col-sm-6" for="granica_po_korisniku">Granica po korisniku:</label>
                        <input class="border input-sm col-sm-6" type="number" id="granica_po_korisniku" name="granica_po_korisniku">
                    </div>
                    <div class="d-flex justify-content-center pt-3">
                        <input type="submit" class="btn btn-primary" value='Sacuvaj kategoriju'>
                    </div>
                </form>
            </div>
            <div class="container m-3 p-5 border border-primary rounded-lg w-100">
                <h2 class="text-primary text-center p-3">Izmena postojecih kategorija:</h2>
                <form action="izmenaStrukture"  class="w-100">
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
                            <input class="border input-sm col-sm-6" type="hidden" value="<%= struktura.getId()%>" name='struktura_id'>
                            <td><%= ((StrukturaUlaznica) struktura).getKategorija()%></td>
                            <td><%= ((StrukturaUlaznica) struktura).getCena()%></td>
                            <td><%= ((StrukturaUlaznica) struktura).getBrojDostupnihUlaznica()%></td>
                            <td><a href="brisanjeStrukture?struktura_id=<%=String.valueOf(struktura.getId())%>">
                                    <input type="button" class="btn btn-primary" name="brisanjeStrukture" value="Obrisi Strukturu"></a></td>
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
    </div>
    <jsp:include page="parts/footer.jsp"></jsp:include>
</body>

</html>


