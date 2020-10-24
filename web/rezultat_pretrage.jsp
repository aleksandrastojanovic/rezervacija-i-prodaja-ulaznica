<%-- 
    Document   : rezultat_pretrage
    Created on : Aug 29, 2020, 2:21:00 PM
    Author     : iq skola
--%>

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

        %>
        <h1>Rezultat pretrage: </h1>        

        <div>
            <table>
                <thead>
                <th>Naziv dogadjaja</th>
                <th>Naziv lokacije</th>
                <th>Datum i vreme</th>
                <th>Detalji</th>
                </thead>
                <%                    for (Dogadjaj dogadjaj : dogadjaji) {%>
                <tr>
                    <td><%= ((Dogadjaj) dogadjaj).getNaziv()%></td>
                    <td><%= ((Dogadjaj) dogadjaj).getNazivLokacije()%></td>
                    <%DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
                        String datumIVreme = dogadjaj.getDatumIVreme().format(formatter);%>
                    <td><%= datumIVreme%></td>
                    <% if (sesija.getAttribute("korisnik_id") != null
                                && Korisnik.TIP_REGISTROVANI_KORISNIK.equals(sesija.getAttribute("tip"))) {%>                    
                    <td><a href="dogadjajPojedinacno?dogadjaj_id=<%= "" + dogadjaj.getId()%>">
                            <input type="button" name="dogadjaj_pojedinacno" value="Detaljnije"></a></td>
                            <% } else { %>
                    <td><a href="registracija.jsp">
                            <input type="button" name="registracija" value="Detaljnije"></a></td>    
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

        <jsp:include page="parts/footer.jsp"></jsp:include>
    </body>

</html>


