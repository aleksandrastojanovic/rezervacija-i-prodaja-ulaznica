<%-- 
    Document   : moje_ulaznice
    Created on : Aug 29, 2020, 2:18:45 PM
    Author     : iq skola
--%>

<%@page import="klase.Dogadjaj"%>
<%@page import="klase.DogadjajBaza"%>
<%@page import="klase.Rezervacija"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>        
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
        <title>Moje ulaznice</title>
    </head>

    <body>
        <jsp:include page="parts/header.jsp"></jsp:include>
        <%
            ArrayList<Rezervacija> rezervacije = (ArrayList<Rezervacija>) request.getAttribute("rezervacije");
        %>
        <div>
            <table>
                <thead>
                <th>Id rezervacije</th>
                <th>Naziv dogadjaja</th>
                <th>Broj ulaznica</th>
                <th>Vreme</th>
                <th>Status</th>
                </thead>
                <%
                    for (Rezervacija rezervacija : rezervacije) {%>
                <tr>
                    <td><%= ((Rezervacija) rezervacija).getId()%></td>
                    <td><% DogadjajBaza db = new DogadjajBaza();
                        Dogadjaj d = db.find(rezervacija.getDogadjajId());%>
                        <%= d.getNaziv()%></td>
                    <td><%= ((Rezervacija) rezervacija).getBrojUlaznica()%></td>
                    <td><%= ((Rezervacija) rezervacija).getVreme()%></td>
                    <td><%= ((Rezervacija) rezervacija).getStatus()%></td>
                    <td><a href='otkazivanjeRezervacije?rezervacija_id=<%= "" + rezervacija.getId()%>'><input type="button" value="Otkazi rezervaciju"></a></td>
                </tr>
                <%
                    }
                %>

            </table>
        </div>
        <div></div>

        <jsp:include page="parts/footer.jsp"></jsp:include>
    </body>

</html>


