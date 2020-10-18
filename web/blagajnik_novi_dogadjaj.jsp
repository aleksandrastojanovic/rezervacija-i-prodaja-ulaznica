<%-- 
    Document   : blaganik_novi_dogadjaj
    Created on : Aug 29, 2020, 2:32:39 PM
    Author     : iq skola
--%>

<%@page import="klase.Blagajnik"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% Blagajnik blagajnik = (Blagajnik)request.getAttribute("korisnik_id"); %>
<!DOCTYPE html>
<html>
    <head>
        <title>Novi dogadjaj</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>
    
    <body>
        <header>
        <!-- Meni -->
        <jsp:include page="parts/meni.jsp"></jsp:include>
    </header>
        <h1>Kreiranje novog dogadjaja - 1/3</h1>
        <div>
            <form action="sacuvajDogadjaj" method="post">
                
                <label>Mesto odrzavanja: </label>
                <%= blagajnik.getNazivLokacije() %><br>
                
                <label for="naziv">Naziv:</label>
                <input type='text' id='naziv' placeholder="Unesite naziv" required><br>
                                                
                <label for="vreme_odrzavanja">Vreme odrzavanja:</label>
                <input type="datetime-local" id="vreme_odrzavanja" name="vreme_odrzavanja" required><br>
                
                <label for="detalji">Detalji dogadjaja</label>
                <input type="text" id='detalji' name="detalji" placeholder='Unesite detalje dogadjaja' required><br>
                
                <input type="submit" value="Dalje">
                
                <!--Preko js bi trebalo da napravim da bude required cena i limit ako je cekirano -->
               
                
                                                           
            </form>
        </div>
        
        <footer>
        <!-- Povratak na vrh -->
        
    </footer>
    </body>
    
</html>


