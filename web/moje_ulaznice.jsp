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
<% ArrayList<Rezervacija> rezervacije = (ArrayList<Rezervacija>)request.getAttribute("rezervacije");
%>
<!DOCTYPE html>
<html>
    <head>
        <title>Moje ulaznice</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>
    
    <body>
        <header>
        <!-- Meni -->
        <jsp:include page="parts/meni.jsp"></jsp:include>
    </header>
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
                for(Rezervacija rezervacija: rezervacije){%>
                <tr>
                    <td><%= ((Rezervacija)rezervacija).getId()%></td>
                    <td><% DogadjajBaza db = new DogadjajBaza();
                        Dogadjaj d = db.find(rezervacija.getDogadjaj_id());%>
                    <%= d.getNaziv() %></td>
                    <td><%= ((Rezervacija)rezervacija).getBroj_ulaznica()%></td>
                    <td><%= ((Rezervacija)rezervacija).getVreme()%></td>
                    <td><%= ((Rezervacija)rezervacija).getStatus()%></td>
                    <td><a href='otkazivanjeRezervacije?rezervacija_id=<%= "" + rezervacija.getId()%>'><input type="button" value="Otkazi rezervaciju"></a></td>
                </tr>
                 <%   
                }
                %>
                
            </table>
        </div>
        <div></div>
        
        <footer>
        <!-- Povratak na vrh -->
        
    </footer>
    </body>
    
</html>


