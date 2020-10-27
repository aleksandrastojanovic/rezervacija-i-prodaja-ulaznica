<%-- 
    Document   : moje_ulaznice
    Created on : Aug 29, 2020, 2:18:45 PM
    Author     : iq skola
--%>

<%@page import="java.text.SimpleDateFormat"%>
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
        <div class="container-fluid p-3 m3-3 ">
            <table class="table pb-0 mb-0">
                <thead class='text-primary'>
                <th>Id rezervacije</th>
                <th>Naziv dogadjaja</th>
                <th>Broj ulaznica</th>
                <th>Vreme</th>
                <th>Status</th>
                <th></th>
                </thead>
                <%
                    for (Rezervacija rezervacija : rezervacije) {%>
                <tr class="text-secondary p-3 m-3">
                    <td><%= ((Rezervacija) rezervacija).getId()%></td>
                    <td><% DogadjajBaza db = new DogadjajBaza();
                        Dogadjaj d = db.find(rezervacija.getDogadjajId());%>
                        <%= d.getNaziv()%></td>
                    <td><%= ((Rezervacija) rezervacija).getBrojUlaznica()%></td>
                    <%
                        String vreme = new SimpleDateFormat("dd.MM.yyyy. HH:mm:ss").format(rezervacija.getVreme());
                    %>
                    <td><%= vreme%></td>
                    <td><%= ((Rezervacija) rezervacija).getStatus()%></td>
                    <td><a href='otkazivanjeRezervacije?rezervacija_id=<%= "" + rezervacija.getId()%>'><input type="button" class="form-control btn btn-primary" value="Otkazi rezervaciju"></a></td>
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


