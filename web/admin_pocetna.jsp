<%-- 
    Document   : admin_pocetna
    Created on : Aug 29, 2020, 2:15:09 PM
    Author     : iq skola
--%>
<%@page import="java.util.ArrayList"%>
<%@page import="klase.Korisnik"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>


<!DOCTYPE html>
<html>
    <head>

        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">

        <title>Pocetna stranica/admin</title>
    </head>

    <body>
        <div class="container-fluid pt-3 my-3 border">
            <jsp:include page="parts/header.jsp"></jsp:include>


            <%
                ArrayList<Korisnik> korisnici = (ArrayList<Korisnik>) request.getAttribute("korisnici");
                if (korisnici != null && korisnici.size() > 0) {
            %>

            <div>
                <table class="table table-striped">
                    <thead class="text-primary">
                    <th scope="col">Id</th>
                    <th scope="col">Tip</th>
                    <th scope="col">Ime</th>
                    <th scope="col">Prezime</th>
                    <th scope="col">Korisnicko ime</th>
                    </thead>
                    <%
                        for (Korisnik korisnik : korisnici) {%>
                    <tr class="text-secondary">
                        <td><%= ((Korisnik) korisnik).getId()%></td>
                        <td><%= ((Korisnik) korisnik).getTip()%></td>
                        <td><%= ((Korisnik) korisnik).getIme()%></td>
                        <td><%= ((Korisnik) korisnik).getPrezime()%></td>
                        <td><%= ((Korisnik) korisnik).getKorisnickoIme()%></td>
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
        </div>
    </body>

</html>


