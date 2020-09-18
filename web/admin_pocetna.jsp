<%-- 
    Document   : admin_pocetna
    Created on : Aug 29, 2020, 2:15:09 PM
    Author     : iq skola
--%>
<%@page import="java.util.ArrayList"%>
<%@page import="klase.Korisnik"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%             
    ArrayList<Korisnik> korisnici = (ArrayList<Korisnik>)request.getAttribute("korisnici");
 %>

<!DOCTYPE html>
<html>
    <head>
        <title>Pocetna stranica/admin</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>
    
    <body>
        <header>
        <!-- Meni -->
        <nav>
        <ul>
            <li>Pocetna stranica</li>
            <li><ul>
                    <li>Pozoriste</li>
                    <li>Muzika</li>
                    <li>Sport</li>
                    <li>Festivali</li>
                    <li>Muzeji</li>
                    <li>Ostalo</li>
                </ul>
            </li>            
            <li>Registruj se</li>
            <li>Prijavi se</li>
                        
        </ul>
        </nav>
    </header>

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
                for(Korisnik korisnik: korisnici){%>
                <tr>
                    <td><%= ((Korisnik)korisnik).getId()%></td>
                    <td><%= ((Korisnik)korisnik).getTip()%></td>
                    <td><%= ((Korisnik)korisnik).getIme()%></td>
                   <td><%= ((Korisnik)korisnik).getPrezime() %></td>
                    <td><%= ((Korisnik)korisnik).getKorisnicko_ime()%></td>
                </tr>
                 <%   
                }
                %>
        
        <footer>
        <!-- Povratak na vrh -->
        
    </footer>
    </body>
    
</html>


