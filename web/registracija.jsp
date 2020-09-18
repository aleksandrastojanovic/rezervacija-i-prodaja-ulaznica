<%-- 
    Document   : prijava
    Created on : Aug 29, 2020, 1:42:14 PM
    Author     : iq skola
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Registracija</title>
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
        <h1>Registracija:</h1>
        <div>
            <form action='registracija' method="post">
                <label for="ime">Ime:</label>
                <input type="text" name="ime" placeholder='Unesite ime' required><br>
                
                <label for="prezime">Prezime:</label>
                <input type="text" name="prezime" placeholder='Unesite prezime' required><br>
                
                <label for="username">Korisnicko ime:</label>
                <input type="text" name="username" placeholder='Unesite korisnicko ime' minlength='5' required><br>
                
                <label for="password">Lozinka:</label>
                <input type="password" name="password" placeholder="Unesite lozinku" minlength='5' required><br>
                
                <label for="password_check">Potvrda lozinke:</label>
                <input type="password" name="password_check" placeholder='Unesite potvrdu lozinke' minlength='5' required><br>
                
                <label for="grad">Grad:</label>
                <input type="text" name="grad" placeholder='Unesite grad' required><br>
                
                <label for="adresa">Ulica i broj:</label>
                <input type="text" name="adresa" placeholder='Unesite ulicu i broj' required><br>
                
                <label for="telefon">Kontakt telefon:</label>
                <input type="text" name="telefon" placeholder='Unesite kontakt telefon' required><br>
                
                <label for="email">E-mail:</label>
                <input type="text" name="email" placeholder="Unesite e-mail" required><br>
                
                <input type="submit" value="Registruj se">
        
            </form>
        </div>
        <hr>
        
        <footer>
        <!-- Povratak na vrh -->
        
    </footer>
    </body>
    
</html>