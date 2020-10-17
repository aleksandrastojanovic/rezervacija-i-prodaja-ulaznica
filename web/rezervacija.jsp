<%
    
%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Rezervacija</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>
    
    <body>
        <header>
        <!-- Meni -->
        <jsp:include page="parts/meni.jsp"></jsp:include>
    </header>
        <div>
            <form action="novaRezervacija" method="post">
                <label for="broj_ulaznica">Broj Ulaznica:</label>
                <input type="number" name="broj_ulaznica" id="broj_ulaznica" required><br>
                <input type="submit" value="Posalji" name="posalji">
                
            </form>
        </div>
        
        <footer>
        <!-- Povratak na vrh -->
        
    </footer>
    </body>
    
</html>


