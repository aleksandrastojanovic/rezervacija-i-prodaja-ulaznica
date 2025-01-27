<%-- 
    Document   : pregled_korisnika
    Created on : Aug 29, 2020, 2:25:36 PM
    Author     : iq skola
--%>

<%@page import="modeli.*"%>
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
        <title>Pregled korisnika</title>
    </head>

    <body>
        <jsp:include page="parts/header.jsp"></jsp:include>

        <%
            ArrayList<RegistrovaniKorisnik> korisnici = (ArrayList<RegistrovaniKorisnik>) request.getAttribute("korisnici");
            if (korisnici.size() > 0) {
        %>


        <div class="container-fluid p-3 m-3 border">
            <table class="table table-striped">
                <thead class="text-primary">
                <th>ID</th>
                <th>Tip</th>
                <th>Ime</th>
                <th>Prezime</th>
                <th>Korisnicko ime</th>
                </thead>
                <%
                    for (RegistrovaniKorisnik korisnik : korisnici) {%>
                <tr class="text-secondary">
                    <td><%= ((RegistrovaniKorisnik) korisnik).getId()%></td>
                    <td><%= ((RegistrovaniKorisnik) korisnik).getTip()%></td>
                    <td><%= ((RegistrovaniKorisnik) korisnik).getIme()%></td>
                    <td><%= ((RegistrovaniKorisnik) korisnik).getPrezime()%></td>
                    <td><%= ((RegistrovaniKorisnik) korisnik).getKorisnickoIme()%></td>
                    <td><a href='odobravanjeZahteva?korisnik_id=<%= korisnik.getId()%>'>
                            <button type="button" class="btn btn-success text-center">Odblokiraj korisnika</button></a></td>
                </tr>
                <%
                    }
                %>
            </table>
        </div>
        <% } else { %>
        <jsp:include page="parts/nema_rezultata.jsp"></jsp:include>
        <% }%>

        <jsp:include page="parts/footer.jsp"></jsp:include>
    </body>

</html>


