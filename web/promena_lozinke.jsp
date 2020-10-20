<%-- 
    Document   : promena_lozinke
    Created on : Oct 15, 2020, 7:52:20 PM
    Author     : iq skola
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
        <title>Promena lozinke</title>
    </head>
    <body>
        <jsp:include page="parts/header.jsp"></jsp:include>
            <h1>Promena lozinke:</h1>
            <div>
                <form action='promenaLozinke'>
                    <label for="lozinka">Lozinka:</label>
                    <input type='password' placeholder='Unesite lozniku' name='lozinka' minlength='5' required><br>
                    <label for="nova_lozinka">Nova lozinka:</label>
                    <input type="password" placeholder="Unesite novu lozinku" name="nova_lozinka" minlength='5' required><br>
                    <label for='nova_lozinka_potvrda'>Potvrda nove lozinke:</label>
                    <input type="password" placeholder="Potvrda nove lozinke" name="nova_lozinka_potvrda" minlength='5' required><br>
                    <input type="submit" value='Promeni lozinku'>
                </form>
            </div>
        <jsp:include page="parts/footer.jsp"></jsp:include>
    </body>
</html>
