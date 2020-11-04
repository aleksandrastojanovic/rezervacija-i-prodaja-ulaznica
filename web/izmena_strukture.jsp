<%-- 
    Document   : izmena_strukture
    Created on : Sep 22, 2020, 3:22:31 PM
    Author     : iq skola
--%>


<%@page import="modeli.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>        
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="parts/pozadina.css">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
        <title>Izmena Kategorije</title>
    </head>

    <body>
        <jsp:include page="parts/header.jsp"></jsp:include>
        <%
            StrukturaUlaznica struktura = (StrukturaUlaznica) request.getAttribute("struktura");
        %>
        <div class="container-fluid p-3 mb-3 text-center w-100 border">
            <h1 class="text-primary text-center p-1">Izmena strukture:</h1>
            <form action="sacuvajKategoriju">
                <input type="hidden" name="struktura_id" value="<%= String.valueOf(struktura.getId())%>" >
                <input type="hidden" name="dogadjaj_id" value="<%= String.valueOf(struktura.getIdDogadjaja())%>" >
                <div class="form-group p-1 m-1 row text-left">
                    <label class="text-primary col-6 text-left" for="kategorija">Kategorija:</label>
                    <input class="border input-sm col-6"  type='text' id='kategorija' name="kategorija" value="<%= struktura.getKategorija()%>"><br>
                </div>
                <div class="form-group p-1 m-1 row text-left">
                    <label class="text-primary col-6" for="cena">Cena:</label>
                    <input class="border input-sm col-6"  type="number" id="cena" name="cena" value="<%= struktura.getCena()%>"><br>
                </div>
                <div class="form-group p-1 m-1 row text-left">
                    <label class="text-primary col-6" for="broj_ulaznica">Broj dostupnih ulaznica:</label>
                    <input class="border input-sm col-6"  type="number" id='broj_ulaznica' name="broj_ulaznica" value="<%= struktura.getBrojDostupnihUlaznica()%>"><br>
                </div>
                <div class="form-group p-1 m-1 row text-left">
                    <label class="text-primary col-6" for="granica_po_korisniku">Granica po korisniku:</label>
                    <input class="border input-sm col-6"  type="number" id='granica_po_korisniku' name="granica_po_korisniku" value="<%= struktura.getGranicaPoKorisniku()%>"><br>
                </div>
                <div class="d-flex justify-content-center pt-3">
                <input type="submit" class="btn btn-primary" value="Sacuvaj izmene">
                </div>
            </form>

        </div>


        <jsp:include page="parts/footer.jsp"></jsp:include>
    </body>

</html>
