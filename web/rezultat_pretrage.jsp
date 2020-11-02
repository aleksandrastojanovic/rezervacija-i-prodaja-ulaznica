<%-- 
    Document   : rezultat_pretrage
    Created on : Aug 29, 2020, 2:21:00 PM
    Author     : iq skola
--%>


<%@page import="java.time.format.DateTimeFormatter"%>
<%@page import="java.time.LocalDateTime"%>
<%@page import="klase.*"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="parts/pozadina.css">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">

        <title>Rezultat pretrage</title>
    </head>
    <style>

        .card-img-top {
            width: 100%;
            height: 13vw;
            object-fit: cover;
        }
        .k {
            opacity: 0.5;
        }
    </style>
    <body>
        <jsp:include page="parts/header.jsp"></jsp:include>
        <%
            ArrayList<Dogadjaj> dogadjaji = (ArrayList<Dogadjaj>) request.getAttribute("dogadjaji");
            if (dogadjaji != null && dogadjaji.size() > 0) {
        %>
        <jsp:include page="parts/pretraga_dogadjaja.jsp"></jsp:include>

            <div class="container-fluid pt-3 mt-0" >
                <h1 class="text-primary text-center">Rezultat pretrage:</h1>


                <div class="row">
                    <div class="col-md-2"></div>
                    <div class="col-md-8">
                        <div class="row">

                        <%
                            for (Dogadjaj dogadjaj : dogadjaji) {
                        %>
                        <div class="col-md-4 p-3">
                            <% if (dogadjaj.getDatumIVreme().isBefore(LocalDateTime.now())) { %>
                            <div class="card border rounded k">
                                <% } else { %>
                                <div class="card border rounded">
                                    <% } %>
                                    <% if (request.getSession().getAttribute("korisnik_id") != null && request.getSession().getAttribute("tip") != null) {%>
                                    <a href="dogadjajPojedinacno?dogadjaj_id=<%= "" + dogadjaj.getId()%>" >
                                        <% } else { %>
                                        <a href="proveraRegistrovan" class="btn btn-primary">Detaljnije</a>    
                                        <% }%>
                                        <div class="card-img-top">
                                            <%
                                                String ime = "__praznaSlika__.jpg";
                                                MediaBaza mediaBaza = new MediaBaza();
                                                ArrayList<Media> sveFotke = mediaBaza.allWhereDogadjajId(dogadjaj.getId());
                                                for (Media foto : sveFotke) {
                                                    if (foto.isGlavna()) {
                                                        ime = foto.getIme();
                                                    }
                                                }
                                            %>
                                            <img class="card-img-top" src="ucitajFoto?dogadjaj_id=<%= dogadjaj.getId()%>&ime=<%= ime%>" alt="Glavna slika">
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
                        
                    </div>
                    <% } else { %>
                    <jsp:include page="parts/nema_rezultata.jsp"></jsp:include>
                    <% }%>

                    <hr>
                    <jsp:include page="parts/footer.jsp"></jsp:include>
                    </body>

                    </html>