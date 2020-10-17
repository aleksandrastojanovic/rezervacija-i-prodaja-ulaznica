<%-- 
    Document   : pregled_korisnika
    Created on : Aug 29, 2020, 2:25:36 PM
    Author     : iq skola
--%>

<%@page import="klase.RegistrovaniKorisnik"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% ArrayList<RegistrovaniKorisnik> korisnici = (ArrayList<RegistrovaniKorisnik>)request.getAttribute("korisnici");%>
<!DOCTYPE html>
<html>
    <head>
        <title>Pregled korisnika</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>
    
    <body>
        <header>
        <!-- Meni -->
        <jsp:include page="parts/meni.jsp"></jsp:include>
    </header>
        <div></div>
        <div>
            <table>
                <thead>
                <th>Id</th>
                <th>Tip</th>
                <th>Ime</th>
                <th>Prezime</th>
                <th>Korisnicko ime</th>
                </thead>
                <%
                for(RegistrovaniKorisnik korisnik: korisnici){%>
                <tr>
                    <td><%= ((RegistrovaniKorisnik)korisnik).getId()%></td>
                    <td><%= ((RegistrovaniKorisnik)korisnik).getTip()%></td>
                    <td><%= ((RegistrovaniKorisnik)korisnik).getIme()%></td>
                   <td><%= ((RegistrovaniKorisnik)korisnik).getPrezime() %></td>
                    <td><%= ((RegistrovaniKorisnik)korisnik).getKorisnicko_ime()%></td>
                    <td><a href='odobravanjeZahteva?korisnik_id=<%= korisnik.getId() %>'>
                            <input type="button" value="Odblokiraj korisnika"></a></td>
                </tr>
                 <%   
                }
                %>
            </table>
</div>
        
        <footer>
        <!-- Povratak na vrh -->
        
    </footer>
    </body>
    
</html>


