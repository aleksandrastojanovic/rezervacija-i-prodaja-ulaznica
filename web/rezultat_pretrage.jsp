<%-- 
    Document   : rezultat_pretrage
    Created on : Aug 29, 2020, 2:21:00 PM
    Author     : iq skola
--%>

<%@page import="klase.ProvereKorisnik"%>
<%@page import="java.time.format.DateTimeFormatter"%>
<%@page import="klase.Korisnik"%>
<%@page import="klase.Dogadjaj"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>        
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
        <title>Rezultat pretrage</title>
    </head>

    <body>
        <jsp:include page="parts/header.jsp"></jsp:include>
        <%
            ArrayList<Dogadjaj> dogadjaji = (ArrayList<Dogadjaj>) request.getAttribute("dogadjaji");
            HttpSession sesija = request.getSession();
            if(dogadjaji.size() > 0){

        %>

        <!-- ovde dodati fotke -->

        <div class="container-fluid p-3 m-3-3">
            <h1 class="text-center text-primary">Rezultat pretrage: </h1><br>
            <table class="table">
                <thead class="text-primary">
                <th>Naziv dogadjaja</th>
                <th>Naziv lokacije</th>
                <th>Datum i vreme</th>
                <th>Detalji</th>
                </thead>
                <%                    for (Dogadjaj dogadjaj : dogadjaji) {%>
                <tr class="text-secondary">
                    <td><%= ((Dogadjaj) dogadjaj).getNaziv()%></td>
                    <td><%= ((Dogadjaj) dogadjaj).getNazivLokacije()%></td>
                    <%DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");
                        String datumIVreme = dogadjaj.getDatumIVreme().format(formatter);%>
                    <td><%= datumIVreme%></td>
                    <% if (sesija.getAttribute("korisnik_id") != null
                                && ProvereKorisnik.postojiPrijavljenKorisnik(request)) {%>                    
                    <td><a href="dogadjajPojedinacno?dogadjaj_id=<%= "" + dogadjaj.getId()%>">
                            <input type="button" class="btn btn-primary text-center p-1" name="dogadjaj_pojedinacno" value="Detaljnije"></a></td>
                            <% } else { %>
                    <td><a href="prijava.jsp">
                            <input type="button" class="btn btn-primary text-center p-1" name="registracija" value="Detaljnije"></a></td>    
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
                <% } %>

        <jsp:include page="parts/footer.jsp"></jsp:include>
    </body>

</html>


