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

                <div>
                    <form id='forma' action='sacuvajKategoriju'>
                        <input type="hidden" name="dogadjaj_id" value="<%= request.getAttribute("dogadjaj_id")%>"
                           <table class="table">
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

                        <tr><td><input class="btn btn-primary" type="button" onclick="submitIReset()" value="Sacuvaj i kreiraj novu kategoriju"></td>
                            <td><input type="submit" class="btn btn-primary" value="Sacuvaj dogadjaj"></td></tr>
                    </table>
                </form>
            </div>
            <jsp:include page="parts/footer.jsp"></jsp:include>
            <script>
                function submitIReset() {
                    var forma = document.getElementById("forma");
                    forma.submit();
                    forma.reset();
                    return false;
                }
            </script>
        </div>
    </body>
</html>
