<%-- 
    Document   : blaganik_novi_dogadjaj
    Created on : Aug 29, 2020, 2:32:39 PM
    Author     : iq skola
--%>

<%@page import="modeli.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>        
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
        <link rel="stylesheet" href="parts/pozadina.css">
        <link rel="icon" href="favicon.ico" type="image/icon type">
        <title>Novi dogadjaj</title>
    </head>

    <body>
        <jsp:include page="parts/header.jsp"></jsp:include>

            <div class="container-fluid p-3 mb-3 text-center w-100 border">
            <% Blagajnik blagajnik = (Blagajnik) request.getAttribute("blagajnik");%>

            <h1 class="text-primary text-center">Kreiranje novog dogadjaja - 1/3</h1>
            <div>
                <form action="sacuvajDogadjaj" method="post">
                    <input type="hidden" name="noviDogadjaj" value="da">

                    <div class="form-group p-1 m-1 row text-left">
                        <label  class="text-primary col-sm-6">Mesto odrzavanja: </label>
                        <p class="text-primary col-sm-6"><%= blagajnik.getNazivLokacije()%></p><br>
                    </div>

                    <div class="form-group p-1 m-1 row text-left">
                        <label class="text-primary col-sm-6" for="naziv">Naziv:</label>
                        <input type='text' class="border input-sm col-sm-6" name = 'naziv' id='naziv' placeholder="Unesite naziv" required><br>
                    </div>

                    <div class="form-group p-1 m-1 row text-left">
                        <label class="text-primary col-sm-6" for="vreme_odrzavanja">Vreme odrzavanja:</label>
                        <input type="datetime-local" class="border input-sm col-sm-6" id="vreme_odrzavanja" name="vreme_odrzavanja" required><br>
                    </div>

                    <div class="form-group p-1 m-1 row text-left">
                        <label class="text-primary col-sm-6" for="detalji">Detalji dogadjaja:</label><br>
                        <textarea type="text" class="col-sm-6" id='detalji' name="detalji" rows="4" cols="50" placeholder='Unesite detalje dogadjaja' required></textarea><br>
                    </div>

                    <div class="d-flex justify-content-center pt-3">
                        <input class="btn btn-primary" type="submit" value="Dalje">
                    </div>

                    <!--Preko js bi trebalo da napravim da bude required cena i limit ako je cekirano -->



                </form>
            </div>
        </div>
        <jsp:include page="parts/footer.jsp"></jsp:include>

    </body>

</html>


