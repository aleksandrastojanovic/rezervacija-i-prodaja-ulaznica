<%-- 
    Document   : izmena_dogadjaja
    Created on : Sep 21, 2020, 12:39:24 PM
    Author     : iq skola
--%>

<%@page import="klase.Blagajnik"%>
<%@page import="klase.Dogadjaj"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% Dogadjaj dogadjaj = (Dogadjaj)request.getAttribute("dogadjaj");
    Blagajnik blagajnik = (Blagajnik)request.getAttribute("blagajnik"); %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Izmena dogadjaja</title>
    </head>
    <body>
        <jsp:include page="parts/meni.jsp"></jsp:include>
        <h1>Izmena dogadjaja</h1>
        <div>
            <form action="sacuvajDogadjaj">
                
                <label>Mesto odrzavanja</label>
                <%= blagajnik.getNaziv_lokacije() %><br>
                
                <label for="naziv">Naziv:</label>
                <input type='text' id='naziv' name='naziv' value="<%= dogadjaj.getNaziv() %>"><br>
                <input type="hidden" name="dogadjaj_id" value="<%= "" + dogadjaj.getId() %>" >
                
                
                <!--
                Mislim da mi ovaj deo uopste ne treba, prikaz samo komplikuje zivot
                <label for='mesto_odrzavanja'>Mesto odrzavanja:</label><br>
                 automatski se popunjava, samo ne znam kako :D 
                input polje value postavim na mesto blagajnika, i onda on ne moze da menja
                -->
                
                <label for="vreme_odrzavanja">Vreme odrzavanja:</label>
                <input type="datetime-local" id="vreme_odrzavanja" name="vreme_odrzavanja" value="<%= dogadjaj.getDatum_i_vreme()%>"><br>
                
                <label for="detalji">Detalji dogadjaja</label>
                <input type="text" id='detalji' name="detalji" value="<%= dogadjaj.getDetalji()%>"><br>
                
                <label for="slika_glavna">Glavna fotografija:</label>
                <input type="file" id="slika_glavna" name="slika_glavna" accept="image/*" value="<%= dogadjaj.getGlavna_slika_putanja()%>"><br>
                
                <label for="slike">Dodaj ostale fotografije:</label>
                <input type="file" id="slike" name="slike" accept="image/*" multiple><br>

                <% if(dogadjaj.getVideo_putanja() != null) { %>
                
                <label for="video">Dodaj video zapis</label>
                <input type="file" id="video" name="video" accept="video/*" value="<%= dogadjaj.getVideo_putanja()%>"><br>
                <% } %>
                <!--Preko js bi trebalo da napravim da bude required cena i limit ako je cekirano -->
                
                
                <input type="submit" value="Sacuvaj izmene">
                                                           
            </form>
        </div>
        
    </body>
</html>
