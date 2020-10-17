<%-- 
    Document   : index
    Created on : Aug 29, 2020, 1:57:59 PM
    Author     : iq skola
--%>

<%@page import="java.time.LocalDateTime"%>
<%@page import="klase.Dogadjaj"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% 
    ArrayList<Dogadjaj> dogadjaji = (ArrayList<Dogadjaj>) request.getAttribute("dogadjaji");
%><!DOCTYPE html>
<html>
    <head>
        <title>Pocetna stranica</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>

    <body>
        <header>
            <!-- Meni -->
            <jsp:include page="parts/meni.jsp"></jsp:include>
            </header>
            <jsp:include page="parts/pretraga_dogadjaja.jsp"></jsp:include>
            <div>
                <table>
                    <thead>
                    <th>Naziv dogadjaja</th>
                    <th>Naziv lokacije</th>
                    <th>Datum i vreme</th>
                    <th>Detalji</th>
                    </thead>
                <%
                    for (Dogadjaj dogadjaj : dogadjaji) {%>
                <tr>
                    <td><%= ((Dogadjaj) dogadjaj).getNaziv()%></td>
                    <td><%= ((Dogadjaj) dogadjaj).getNaziv_lokacije()%></td>
                    <td><%= ((Dogadjaj) dogadjaj).getDatum_i_vreme()%></td>
                    <% if (request.getSession().getAttribute("korisnik_id") != null && request.getSession().getAttribute("tip") != null) {%>                    
                    <td><a href="dogadjajPojedinacno?dogadjaj_id=<%= "" + dogadjaj.getId()%>">
                            <input type="button" name="dogadjaj_pojedinacno" value="Detaljnije"></a></td>
                            <% } else { %>
                    <td><a href="proveraRegistrovan">
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
        <hr>
        <div><a href="mojeUlaznice">Moje ulaznice</a></div>

        <footer>
            <!-- Povratak na vrh -->

        </footer>
    </body>

</html>

