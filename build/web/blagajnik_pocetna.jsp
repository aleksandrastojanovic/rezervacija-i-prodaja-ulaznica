<%-- 
    Document   : blagajnik_pocetna
    Created on : Aug 29, 2020, 2:11:16 PM
    Author     : iq skola
--%>

<%@page import="klase.Dogadjaj"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% ArrayList<Dogadjaj> dogadjaji = (ArrayList<Dogadjaj>)request.getAttribute("dogadjaji");
%>
<!DOCTYPE html>
<html>
    <head>
        <title>Pocetna stranica/blaganik</title>
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
        <!-- 
        - Forma za pravljenje novog blagajnika za lokaciju prijavljenog blagajnika,
            naziv lokacije, adresa, i grad se automatski popunjavaju
        - Pregled dogadjaja za lokaciju blagajnika
        -->
        <div>
            <!-- forma za novog blagajnika -->
            <form action='' method=''>
               <label for="ime">Ime:</label>
                <input type="text" name="ime" placeholder='Unesite ime' required><br>
                
                <label for="prezime">Prezime:</label>
                <input type="text" name="prezime" placeholder='Unesite prezime' required><br>
                
                <label for="username">Korisnicko ime:</label>
                <input type="text" name="username" placeholder='Unesite korisnicko ime' minlength='5' required><br>
                
                <label for="password">Lozinka:</label>
                <input type="password" name="password" placeholder="Unesite lozinku" minlength='5' required><br>
                
                <label for="password_check">Potvrda lozinke:</label>
                <input type="password" name="password_check" placeholder='Unesite potvrdu lozinke' minlength='5' required><br>
            </form>
        </div>
        
        <div>
            <table>
                <thead>
                <th>Naziv dogadjaja</th>
                <th>Naziv lokacije</th>
                <th>Datum i vreme</th>
                <th>Detalji</th>
                </thead>
                <%
                for(Dogadjaj dogadjaj: dogadjaji){%>
                <tr>
                    <td><%= ((Dogadjaj)dogadjaj).getNaziv()%></td>
                   <td><%= ((Dogadjaj)dogadjaj).getNaziv_lokacije() %></td>
                    <td><%= ((Dogadjaj)dogadjaj).getDatum_i_vreme()%></td>
                    <td><%= ((Dogadjaj)dogadjaj).getDetalji()%></td>
                </tr>
                 <%   
                }
                %>
        <footer>
        <!-- Povratak na vrh -->
        
    </footer>
    </body>
    
</html>


