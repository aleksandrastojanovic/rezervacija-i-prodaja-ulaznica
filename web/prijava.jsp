<%-- 
    Document   : prijava
    Created on : Aug 29, 2020, 2:04:50 PM
    Author     : iq skola
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Prijava</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>
    
    <body>
        <header>
        <!-- Meni -->
        <jsp:include page="parts/meni.jsp"></jsp:include>
    </header>
        <h1>Prijava:</h1>
        <div>
            <form action='prijava' method="post">
                                
                <label for="username">Korisnicko ime:</label>
                <input type="text" name="username" placeholder='Unesite korisnicko ime' required><br>
                
                <label for="password">Lozinka:</label>
                <input type="password" name="password" placeholder="Unesite lozinku" required><br>                
                              
                <input type="submit" value="Prijavi se">
        
            </form>
        </div>
        <hr>
        
        <footer>
        <!-- Povratak na vrh -->
        
    </footer>
        
    </body>
    
</html>