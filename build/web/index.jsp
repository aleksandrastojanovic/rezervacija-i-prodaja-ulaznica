<%-- 
    Document   : index
    Created on : Aug 29, 2020, 1:57:59 PM
    Author     : iq skola
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Pocetna stranica</title>
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
            <li><a href="registracija.jsp">Registruj se</a>
                </li>
            <li>Prijavi se</li>
                        
        </ul>
        </nav>
    </header>
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
        <div></div>
        
        <footer>
        <!-- Povratak na vrh -->
        
    </footer>
    </body>
    
</html>

