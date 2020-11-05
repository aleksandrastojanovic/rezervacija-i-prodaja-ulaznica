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
        <link rel="stylesheet" href="parts/pozadina.css">
        <link rel="icon" href="favicon.ico" type="image/icon type">
        <title>Nova struktura</title>
    </head>
    <body>
        <jsp:include page="parts/header.jsp"></jsp:include>

            <div class="container-fluid p-3 mb-0 text-center w-100 border">

                <h1 class="text-primary">Kreiranje novog dogadjaja - 3/3</h1>
                <h2 id='naslov' class="text-primary">Dodaj novu kategoriju:</h2>
                <div class="w-100">
                    <form id='forma' action='sacuvajKategoriju' class="w-100">
                        <table class="table w-100">
                            <input type="hidden" name="dogadjaj_id" value='<%= request.getAttribute("dogadjaj_id")%>'>

                        <tr class="form-group p-1 m-1 row text-left w-100">
                            <td class=" col-6"><label class="text-primary" for='kategorija'>Nova kategorija ulaznica:</label></td>
                            <td class=" col-6"><input class="border input-sm col-6" type='text' id='kategorija' name="kategorija" placeholder='Unesi novu kateoriju ulaznica'></td>
                        </tr>
                        <tr class="form-group p-1 m-1 row text-left">
                            <td class=" col-6"><label class="text-primary col-sm-6" for='cena'>Cena:</label></td>
                            <td class=" col-6"><input class="border input-sm col-6" type='number' id="cena" name='cena'></td>
                        </tr>

                        <tr class="form-group p-1 m-1 row text-left">
                            <td class=" col-6"><label class="text-primary col-sm-6" for="broj_ulaznica">Broj dostupnih ulaznica:</label></td>
                            <td class=" col-6"><input class="border input-sm col-6" type="number" id="broj_ulaznica" name="broj_ulaznica"></td>
                        </tr>


                        <tr class="form-group p-1 m-1 row text-left">
                            <td class=" col-6"> <label class="text-primary col-sm-6" for="granica_po_korisniku">Granica po korisniku:</label></td>
                            <td class=" col-6"><input class="border input-sm col-6" type="number" id="granica_po_korisniku" name="granica_po_korisniku"></td>>
                        </tr>

                        <tr class="form-group p-1 m-1 row text-left">

                            <td class=" col-6"><input class="btn btn-primary col-5" type="button" onclick="submitIReset()" value="Sacuvaj unetu kategoriju"></td>
                            <td class=" col-6"><a a class="btn btn-primary col-7" href='dogadjajPojedinacno?dogadjaj_id=<%= request.getAttribute("dogadjaj_id")%>' role='button'>
                                    Zavrsi sa kreiranjem dogadjaja</a></td>
                        </tr>
                    </table>
                </form>
            </div>

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
    </body>
</html>
