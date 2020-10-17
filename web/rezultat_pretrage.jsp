<%-- 
    Document   : rezultat_pretrage
    Created on : Aug 29, 2020, 2:21:00 PM
    Author     : iq skola
--%>

<%@page import="klase.Korisnik"%>
<%@page import="klase.Dogadjaj"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% 
    ArrayList<Dogadjaj> dogadjaji = (ArrayList<Dogadjaj>)request.getAttribute("dogadjaji");
    HttpSession sesija = request.getSession();
    
%>
<!DOCTYPE html>
<html>
    <head>
        <title>Rezultat pretrage</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>
    
    <body>
        <header>
        <!-- Meni -->
        <jsp:include page="parts/meni.jsp"></jsp:include>
    </header>
        <h1>Rezultat pretrage: </h1>        
        
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
        
        <footer>
        <!-- Povratak na vrh -->
        
    </footer>
    </body>
    
</html>


