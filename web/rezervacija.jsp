

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>        
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
        <title>Rezervacija</title>
    </head>

    <body>
        <jsp:include page="parts/header.jsp"></jsp:include>
            <div>
                <form action="novaRezervacija" method="post">
                    <label for="broj_ulaznica">Broj Ulaznica:</label>
                    <input type="number" name="broj_ulaznica" id="broj_ulaznica" required><br>
                    <input type="submit" value="Posalji" name="posalji">

                </form>
            </div>

        <jsp:include page="parts/footer.jsp"></jsp:include>
    </body>

</html>


