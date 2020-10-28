<%-- 
    Document   : izmena_strukture
    Created on : Sep 22, 2020, 3:22:31 PM
    Author     : iq skola
--%>


<%@page import="klase.StrukturaUlaznica"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>        
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
        <title>Izmena Kategorije</title>
    </head>

    <body>
        <jsp:include page="parts/header.jsp"></jsp:include>
        <%
            StrukturaUlaznica struktura = (StrukturaUlaznica) request.getAttribute("struktura");
        %>
        <div class="container-fluid pt-3 my-3 border">
            <form action="sacuvajKategoriju">
                <input type="hidden" name="struktura_id" value="<%= String.valueOf(struktura.getId())%>" >
                <input type="hidden" name="dogadjaj_id" value="<%= String.valueOf(struktura.getIdDogadjaja())%>" >
                <label class="text-primary" for="kategorija">Kategorija:</label>
                <input type='text' id='kategorija' name="kategorija" value="<%= struktura.getKategorija()%>"><br>

                <label class="text-primary" for="cena">Cena:</label>
                <input type="number" id="cena" name="cena" value="<%= struktura.getCena()%>"><br>

                <label class="text-primary" for="broj_ulaznica">Broj dostupnih ulaznica:</label>
                <input type="number" id='broj_ulaznica' name="broj_ulaznica" value="<%= struktura.getBrojDostupnihUlaznica()%>"><br>

                <label class="text-primary" for="granica_po_korisniku">Granica po korisniku:</label>
                <input type="number" id='granica_po_korisniku' name="granica_po_korisniku" value="<%= struktura.getGranicaPoKorisniku()%>"><br>

                <input type="submit" class="btn btn-primary" value="Sacuvaj izmene">
            </form>

        </div>


        <jsp:include page="parts/footer.jsp"></jsp:include>
    </body>

</html>
