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
            <div class="container-fluid pt-3 my-3 border">
            <% Blagajnik blagajnik = (Blagajnik) request.getAttribute("blagajnik");%>

            <h1 class="text-primary text-center">Kreiranje novog dogadjaja - 1/3</h1>
            <div>
                <form action="sacuvajDogadjaj" method="post">
                    <input type="hidden" name="noviDogadjaj" value="da">

                    <div class="form-group text-secondary">
                        <label  class="text-primary">Mesto odrzavanja: </label>
                        <%= blagajnik.getNazivLokacije()%><br>
                    </div>

                    <div class="form-group">
                        <label class="text-primary" for="naziv">Naziv:</label>
                        <input type='text' name = 'naziv' id='naziv' placeholder="Unesite naziv" required><br>
                    </div>

                    <div class="form-group">
                        <label class="text-primary" for="vreme_odrzavanja">Vreme odrzavanja:</label>
                        <input type="datetime-local" id="vreme_odrzavanja" name="vreme_odrzavanja" required><br>
                    </div>

                    <div class="form-group">
                        <label class="text-primary" for="detalji">Detalji dogadjaja:</label><br>
                        <textarea type="text" id='detalji' name="detalji" rows="4" cols="50" placeholder='Unesite detalje dogadjaja' required></textarea><br>
                    </div>

                    <input class="btn btn-primary" type="submit" value="Dalje">

                    <!--Preko js bi trebalo da napravim da bude required cena i limit ako je cekirano -->



                </form>
            </div>

            <jsp:include page="parts/footer.jsp"></jsp:include>
        </div>
    </body>

</html>


