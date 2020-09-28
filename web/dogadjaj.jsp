<%-- 
    Document   : dogadjaj
    Created on : Sep 23, 2020, 6:15:38 PM
    Author     : iq skola
--%>

<%@page import="klase.StrukturaUlaznica"%>
<%@page import="java.util.ArrayList"%>
<%@page import="klase.Dogadjaj"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% Dogadjaj dogadjaj = (Dogadjaj)request.getAttribute("dogadjaj");
    ArrayList<StrukturaUlaznica> strukture = (ArrayList<StrukturaUlaznica>)request.getAttribute("strukture"); %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><%= "" + dogadjaj.getNaziv() %></title>
    </head>
    <body>
        <h1><%= "" + dogadjaj.getNaziv() %></h1>
        <div>
            Mesto odrzavanja: <%= dogadjaj.getNaziv_lokacije() %><br>
            Vreme odrzavanja: <%= dogadjaj.getDatum_i_vreme()%><br>
            Detalji: <%= dogadjaj.getDetalji()%><br>
            <!--fotografije i video kad bude front, za sad bez, plan da bude galerija -->
             </div>
            <div>
                <form action='sacuvajRezervaciju'>
                    <h3>Rezervisi ulaznice:</h3><br>
                    <table>
                        <thead>
                        <th></th>
                        <th>Kategorija:</th>
                        <th>Cena:</th>
                        <th>Broj ulaznica</th>
                        </thead>
                    <%
                            for (StrukturaUlaznica struktura : strukture) {%>
                <tr>
                    <input type="hidden" name="struktura_id" value='<%= "" + struktura.getId() %>' >
                    <input type="hidden" name="dogadjaj_id" value="<%= "" + struktura.getId_dogadjaja() %>" >
                    <td><input type="radio" name='kategorija' id='<%= "" + struktura.getKategorija() %>'
                               value='<%= "" + struktura.getId() %>'></td>
                    <td><%= struktura.getKategorija()%></td>
                    <td><%= struktura.getCena()%></td>
                    <td><input type="number" placeholder="Broj ulaznica" name='broj_ulaznica' value='0'></td>
                    
                </tr>
                <%
                    }
                %>
                    </table>
                    <input type="submit" value='Rezervisi'>
                </form>
            </div>        
    </body>
</html>
