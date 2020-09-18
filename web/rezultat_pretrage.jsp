<%-- 
    Document   : rezultat_pretrage
    Created on : Aug 29, 2020, 2:21:00 PM
    Author     : iq skola
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Rezultat pretrage</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>
    
    <body>
        <header>
        <!-- Meni -->
        <nav>
        <ul>
            <li>Pocetna stranica</li>
            <li><ul>
                    <li>Pozoriste</li>
                    <li>Muzika</li>
                    <li>Sport</li>
                    <li>Festivali</li>
                    <li>Muzeji</li>
                    <li>Ostalo</li>
                </ul>
            </li>            
            <li>Registruj se</li>
            <li>Prijavi se</li>
                        
        </ul>
        </nav>
    </header>
        <h1>Pretraga dogadjaja</h1>
        <!-- forma za pretragu dogadjaja-->
        <div>
            <form>
                <label for='po_nazivu'>Pretrazi po nazivu:</label>
                <input type="text" id="po_nazivu" name='po_nazivu' placeholder='Unesite naziv'><br>
                
                <label for='vreme_od'>Pretrazi po datumu od:</label>
                <input type="datetime-local" id="vreme_od" name='vreme_od' placeholder='Unesite od kog datuma'>
                <label for='vreme_do'> do:</label>
                <input type="datetime-local" id="vreme_do" name='vreme_do' placeholder='Unesite do kog datuma'><br>
                
                <label for='po_mestu'>Pretrazi po mestu odrzavanja:</label>
                <input type="text" id="po_mestu" name='po_metu' placeholder='Unesite mesto odrzavanja'><br>
            </form>
        </div>
        
        <footer>
        <!-- Povratak na vrh -->
        
    </footer>
    </body>
    
</html>


