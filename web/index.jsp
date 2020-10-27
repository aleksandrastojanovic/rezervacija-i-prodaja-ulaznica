<%-- 
    Document   : index
    Created on : Aug 29, 2020, 1:57:59 PM
    Author     : iq skola
--%>

<%@page import="java.time.format.DateTimeFormatter"%>
<%@page import="java.time.LocalDateTime"%>
<%@page import="klase.Dogadjaj"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
        <title>Pocetna stranica</title>
    </head>
    <style>
        .card-img-top {
            width: 100%;
            height: 13vw;
            object-fit: cover;
        }
    </style>
    <body>
        <jsp:include page="parts/header.jsp"></jsp:include>
        <%
            ArrayList<Dogadjaj> dogadjaji = (ArrayList<Dogadjaj>) request.getAttribute("dogadjaji");
            int ukupnoStrana = (Integer) request.getAttribute("ukupnoStrana");
        %>
<jsp:include page="parts/pretraga_dogadjaja.jsp"></jsp:include>
        <div class="container-fluid pt-3 mt-0" >
            

                <div class="row">
                    <div class="col-md-2"></div>
                    <div class="col-md-8">
                        <div class="row">

                        <%
                            for (Dogadjaj dogadjaj : dogadjaji) {%>
                        <div class="col-md-4 p-3">
                            <div class="card border rounded">
                                <% if (request.getSession().getAttribute("korisnik_id") != null && request.getSession().getAttribute("tip") != null) {%>
                                <a href="dogadjajPojedinacno?dogadjaj_id=<%= "" + dogadjaj.getId()%>" >
                                    <% } else { %>
                                    <a href="proveraRegistrovan" class="btn btn-primary">Detaljnije</a>    
                                    <% }%>
                                    <div class="card-img-top">
                                        <img class="card-img-top" src="ucitajFoto?dogadjaj_id=1&ime=prva.jpg" alt="Glavna slika">
                                    </div>
                                </a>
                                <div class="card-body">
                                    <h5 class="card-title text-primary"><%= ((Dogadjaj) dogadjaj).getNaziv()%></h5>
                                    <p class="card-text text-secondary">
                                        <%= ((Dogadjaj) dogadjaj).getNazivLokacije()%>
                                        <br>
                                        <%DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy. HH:mm");
                                            String datumIVreme = dogadjaj.getDatumIVreme().format(formatter);%>
                                        <%= datumIVreme%>
                                    </p>
                                </div>
                            </div>
                        </div>


                        <% }%>


                    </div>
                    <div>
                        <nav aria-label="Page navigation example">
                            <ul class="pagination justify-content-center p-3">
                                <% for (int i = 1; i <= ukupnoStrana; i++) {%>
                                <li class="page-item"><a class="page-link" href="index?trenutniBrojStrane=<%= i%>"> <%= i%> </a></li>
                                    <% }%>
                            </ul>
                        </nav>
                    </div>
                </div>

                <hr>
                <jsp:include page="parts/footer.jsp"></jsp:include>
                </body>

                </html>

