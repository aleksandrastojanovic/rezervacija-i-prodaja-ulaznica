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
        <title>Nova struktura</title>
    </head>
    <body>
        <header>
            <!-- Meni -->
            <jsp:include page="parts/meni.jsp"></jsp:include>
            </header>
        
        <h1>Dodaj novu strukturu: </h1>
        
        <div>
            <form action="sacuvajKategoriju">
                <label for='nova_kategorija'>Nova kategorija ulaznica:</label>
                <input type='text' id='nova_kategorija' name="nova_kategorija" placeholder='Unesite novu kateoriju ulaznica'>
                <label for='nova_kategorija_cena'>| Cena:</label>
                <input type='number' id="nova_kategorija_cena" name='nova_kategorija_cena'>
                <label for="limit_nova_kategorija">| Broj dostupnih ulaznica:</label>
                <input type="number" id="limit_nova_kategorija" name="limit_nova_kategorija"><br>
                <label for="granica_po_korisniku">| Granica po korisniku:</label>
                <input type="number" id="granica_po_korisniku" name="granica_po_korisniku"><br>
                <input type="submit" value="Kreiraj novi dogadjaj">
            </form>
        </div>
    </body>
</html>
