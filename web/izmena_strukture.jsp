<%-- 
    Document   : izmena_strukture
    Created on : Sep 22, 2020, 3:22:31 PM
    Author     : iq skola
--%>


<%@page import="klase.StrukturaUlaznica"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% StrukturaUlaznica struktura = (StrukturaUlaznica)request.getAttribute("struktura");%>
<!DOCTYPE html>
<html>
    <head>
        <title>Izmena Kategorije</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>

    <body>
        <header>
            <!-- Meni -->
            <jsp:include page="parts/meni.jsp"></jsp:include>
        </header>

        <div>
            <form action="sacuvajKategoriju">
                <input type="hidden" name="struktura_id" value="<%= String.valueOf(struktura.getId()) %>" >
                <input type="hidden" name="dogadjaj_id" value="<%= String.valueOf(struktura.getIdDogadjaja()) %>" >
                <label for="kategorija">Kategorija:</label>
                <input type='text' id='kategorija' name="kategorija" value="<%= struktura.getKategorija()%>"><br>

                <label for="cena">Cena:</label>
                <input type="number" id="cena" name="cena" value="<%= struktura.getCena()%>"><br>

                <label for="broj_ulaznica">Broj dostupnih ulaznica:</label>
                <input type="number" id='broj_ulaznica' name="broj_ulaznica" value="<%= struktura.getBrojDostupnihUlaznica()%>"><br>
                
                <label for="granica_po_korisniku">Granica po korisniku:</label>
                <input type="number" id='granica_po_korisniku' name="granica_po_korisniku" value="<%= struktura.getGranicaPoKorisniku()%>"><br>
                
                <input type="submit" value="Sacuvaj izmene">
            </form>
                
        </div>
        <div></div>

        <footer>
            <!-- Povratak na vrh -->

        </footer>
    </body>

</html>
