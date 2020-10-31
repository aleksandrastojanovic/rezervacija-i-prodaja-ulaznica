<%-- 
    Document   : blagajnik_nova_struktura
    Created on : Oct 18, 2020, 12:14:36 PM
    Author     : iq skola
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">

        <title>Nova struktura</title>
    </head>
    <body>
        <jsp:include page="parts/header.jsp"></jsp:include>

            <div class="container-fluid p-3 my-3 border">

                <h1 class="text-primary">Kreiranje novog dogadjaja - 3/3</h1>
                <h2 id='naslov' class="text-primary">Dodaj novu kategoriju:</h2>
                <div>
                    <form id='forma' action='sacuvajKategoriju'>

                        <table class="table">
                            <input type="hidden" name="dogadjaj_id" value="<%= request.getAttribute("dogadjaj_id")%>"
                               <tr>
                        <div class="form-group">
                            <td><label class="text-primary" for='kategorija'>Nova kategorija ulaznica:</label></td>
                            <td><input type='text' id='kategorija' name="kategorija" placeholder='Unesi novu kateoriju ulaznica'></td>
                        </div>
                        </tr>
                        <tr><div class="form-group">
                            <td><label class="text-primary" for='cena'>Cena:</label></td>
                            <td><input type='number' id="cena" name='cena'></td>
                        </div>
                        </tr>

                        <tr>
                        <div class="form-group">
                            <td><label class="text-primary" for="broj_ulaznica">Broj dostupnih ulaznica:</label></td>
                            <td><input type="number" id="broj_ulaznica" name="broj_ulaznica"></td>
                        </div>
                        </tr>


                        <tr>
                        <div class="form-group">      
                            <td> <label class="text-primary" for="granica_po_korisniku">Granica po korisniku:</label></td>
                            <td><input type="number" id="granica_po_korisniku" name="granica_po_korisniku"></td>>
                        </div>
                        </tr>

                        <tr>
                            <td></td>
                            <td><input class="btn btn-primary" type="button" onclick="submitIReset()" value="Sacuvaj unetu kategoriju"></td>
                            
                        </tr>
                    </table>
                </form>
            </div>

            <jsp:include page="parts/footer.jsp"></jsp:include>
                <script>
                    function submitIReset() {
                        var forma = document.getElementById("forma");
                        forma.submit();
                        forma.reset();
                        document.getElementById("naslov").innerHTML = "Dodaj jos jednu kategoriju";
                        return false;
                    }
                </script>
                <div class="d-flex justify-content pt-3">
                    <a a class="btn btn-primary" href='dogadjajPojedinacno?dogadjaj_id=<%= request.getAttribute("dogadjaj_id")%>' role='button'>
                    Zavrsi sa kreiranjem dogadjaja</a>
            </div>
        </div>

    </body>
</html>
