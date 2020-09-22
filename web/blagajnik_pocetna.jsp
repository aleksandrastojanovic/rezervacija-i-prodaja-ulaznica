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
        
        - Pregled dogadjaja za lokaciju blagajnika
        -->
               
        <div>
            <table>
                <thead>
                <th>Naziv dogadjaja</th>
                <th>Naziv lokacije</th>
                <th>Datum i vreme</th>
                <th>Detalji</th>
                <th></th>
                </thead>
                <%
                for(Dogadjaj dogadjaj: dogadjaji){%>
                <tr>
                    <td><%= ((Dogadjaj)dogadjaj).getNaziv()%></td>
                   <td><%= ((Dogadjaj)dogadjaj).getNaziv_lokacije() %></td>
                    <td><%= ((Dogadjaj)dogadjaj).getDatum_i_vreme()%></td>
                    <td><%= ((Dogadjaj)dogadjaj).getDetalji()%></td>
                   
                    <td><a href="izmenaDogadjaja?dogadjaj_id=<%=String.valueOf(dogadjaj.getId())%>">
                            <input type="button" name="izmeni" value="Izmeni dogadjaj"></a></td>
                    <td><a href="kategorijeUlaznica?dogadjaj_id=<%=String.valueOf(dogadjaj.getId())%>">
                            <input type="button" name="kategorije" value="Kategorije ulaznica"></a></td>
                    
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


