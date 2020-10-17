<%-- 
    Document   : pretraga_dogadjaja
    Created on : Oct 15, 2020, 6:58:29 PM
    Author     : iq skola
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<div>
    <form action="pretragaDogadjaja" method="post">
        <label for='naziv'>Pretrazi po nazivu:</label>
        <input type="text" id="naziv" name='naziv' placeholder='Unesite naziv'><br>

        <label for='vreme_od'>Pretrazi po datumu od:</label>
        <input type="datetime-local" id="vreme_od" name='vreme_od' placeholder='Unesite od kog datuma' value="2001-01-01T00:00">
        <label for='vreme_do'> do:</label>
        <input type="datetime-local" id="vreme_do" name='vreme_do' placeholder='Unesite do kog datuma' value="2001-01-01T00:00"><br>

        <label for='mesto'>Pretrazi po mestu odrzavanja:</label>
        <input type="text" id="po_mestu" name='mesto' placeholder='Unesite mesto odrzavanja'><br>

        <input type="submit" value="Pretrazi">
    </form>
</div>
