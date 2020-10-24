<%-- 
    Document   : blaganik_novi_dogadjaj
    Created on : Aug 29, 2020, 2:32:39 PM
    Author     : iq skola
--%>

<%@page import="klase.Blagajnik"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>        
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
        <title>Novi dogadjaj</title>
    </head>

    <body>
        <jsp:include page="parts/header.jsp"></jsp:include>
        <% Blagajnik blagajnik = (Blagajnik) request.getAttribute("blagajnik");%>

        <h1>Kreiranje novog dogadjaja - 1/3</h1>
        <div>
            <form action="sacuvajDogadjaj" method="post">
                <input type="hidden" name="noviDogadjaj" value="da">

                <label>Mesto odrzavanja: </label>
                <%= blagajnik.getNazivLokacije()%><br>

                <label for="naziv">Naziv:</label>
                <input type='text' name = 'naziv' id='naziv' placeholder="Unesite naziv" required><br>

                <label for="vreme_odrzavanja">Vreme odrzavanja:</label>
                <input type="datetime-local" id="vreme_odrzavanja" name="vreme_odrzavanja" required><br>

                <label for="detalji">Detalji dogadjaja</label>
                <input type="text" id='detalji' name="detalji" placeholder='Unesite detalje dogadjaja' required><br>

                <input type="submit" value="Dalje">

                <!--Preko js bi trebalo da napravim da bude required cena i limit ako je cekirano -->



            </form>
        </div>

        <jsp:include page="parts/footer.jsp"></jsp:include>
    </body>

</html>


