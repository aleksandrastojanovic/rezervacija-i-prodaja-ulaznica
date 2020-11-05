<%-- 
    Document   : dogadjaj
    Created on : Sep 23, 2020, 6:15:38 PM
    Author     : iq skola
--%>

<%@page import="modeli.*"%>
<%@page import="java.time.LocalDateTime"%>
<%@page import="java.time.format.DateTimeFormatter"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    Dogadjaj dogadjaj = (Dogadjaj) request.getAttribute("dogadjaj");
%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="parts/pozadina.css">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
        <link rel="icon" href="favicon.ico" type="image/icon type">
        <title><%= dogadjaj.getNaziv() %></title>
        <style>
            .card-img-top {
                width: 100%;
                height: 13vw;
                object-fit: cover;
            }
        </style>
    </head>
    <body>
        <jsp:include page="parts/header.jsp"></jsp:include>

            <div class="container-fluid pt-3" >
                <div class="row">
                    <div class="col-md-2"></div>
                    <div class="col-md-8">
                        <div class="row">

                        <%
                            ArrayList<String> ostaleFotografije = (ArrayList<String>) request.getAttribute("ostaleFotografije");
                            for (String imeFotografije : ostaleFotografije) {

                        %>
                        <div class="col-md-4 pt-2">
                            <div class="card border rounded">
                                <!-- ovde  na klik skida sliku umesto da prikaze celu -->
                                <a href='prikaz_fotke.jsp?dogadjaj_id=<%= dogadjaj.getId()%>&ime=<%= imeFotografije%>' target="_self">
                                    <div class="card-img-top">
                                        <img class="card-img-top" src="ucitajFoto?dogadjaj_id=<%= dogadjaj.getId()%>&ime=<%= imeFotografije%>" alt="<%= imeFotografije%>">
                                    </div>
                                </a>
                            </div>
                        </div>


                        <% } %>


                        <!--        odavde ostaje-->
                        <%
                            ArrayList<StrukturaUlaznica> strukture = (ArrayList<StrukturaUlaznica>) request.getAttribute("strukture");
                        %>

                        <div class="container-fluid pt-3">

                            <blockquote class="blockquote ">
                                <h1 class="text-center text-primary"><%= "" + dogadjaj.getNaziv()%></h1>
                                <ul class="text-secondary">
                                    <li>Mesto odrzavanja: <%= dogadjaj.getNazivLokacije()%><br>
                                        <%DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy. HH:mm");
                                            String datumIVreme = dogadjaj.getDatumIVreme().format(formatter);%></li>
                                    <li>Vreme odrzavanja: <%= datumIVreme%><br></li>
                                </ul>
                                <p class="mb-0 text-secondary">
                                    Detalji: <%= dogadjaj.getDetalji()%>
                                </p>
                            </blockquote><br>
                            <!--fotografije i video kad bude front, za sad bez, plan da bude galerija -->
                        </div>
                        <%
                            if (request.getAttribute("videoIme") != null) {
                                String videoIme = (String) request.getAttribute("videoIme");
                        %>

                        <video width="620" height="480" controls>
                            <source src="ucitajVideo?dogadjaj_id=<%= dogadjaj.getId()%>&ime=<%= videoIme%>" type="video/mp4">
                            Your browser does not support the video tag.
                        </video>
                    </div>
                    <% } %>

                    <%  HttpSession sesija = request.getSession();
                    %>

                    <% if (Korisnik.TIP_BLAGAJNIK.equals(sesija.getAttribute("tip"))
                                || (Korisnik.TIP_REGISTROVANI_KORISNIK.equals(sesija.getAttribute("tip"))
                                && LocalDateTime.now().isBefore(dogadjaj.getDatumIVreme().minusDays(2)))) { %>
                    <div class="container-fluid p-3 mt-3 border border-primary rounded">
                        <form action='sacuvajRezervaciju' method="post">
                            <h3 class="text-primary text-center">Rezervisi ulaznice:</h3>
                            <table class="table text-secondary">
                                <thead class="text-primary">
                                <th></th>
                                <th>Kategorija:</th>
                                <th>Cena:</th>
                                <th>Broj ulaznica</th>
                                </thead>
                                <div class="form-group">
                                    <%
                                        for (StrukturaUlaznica struktura : strukture) {%>
                                    <tr>
                                    <input type="hidden" name="struktura_id" value='<%= "" + struktura.getId()%>' >
                                    <input type="hidden" name="dogadjaj_id" value='<%= "" + struktura.getIdDogadjaja()%>' >
                                    <td><input type="radio" name='kategorija' id='<%= "" + struktura.getKategorija()%>'
                                               value='<%= "" + struktura.getId()%>'></td>
                                    <td><%= struktura.getKategorija()%></td>
                                    <td><%= struktura.getCena()%></td>
                                    <td><input type="number" placeholder="Broj ulaznica" name='broj_ulaznica' value='0'></td>

                                    </tr>
                                    <%
                                        }
                                    %>
                                </div>
                            </table>
                            <div class="d-flex justify-content-center pt-3">
                                <button type="submit" class="btn btn-primary btn-lg">Rezervisi</button>
                            </div>
                            <br>                       
                        </form>
                        <% }%>
                    </div> 
                </div>
            </div>
            <br>
            <jsp:include page="parts/footer.jsp"></jsp:include>
            </body>
            </html>
