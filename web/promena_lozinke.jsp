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
        <style>
            .klasa {
                position: fixed;
                left: 50%;
                top: 50%;
                transform: translate(-50%, -50%);
            }
        </style>
        <title>Promena lozinke</title>
    </head>
    <body>
        <jsp:include page="parts/header.jsp"></jsp:include>

            <div class="klasa">
                <div class="container-fluid p-3 border text-left">
                    <h1 class="text-primary text-center">Promena lozinke:</h1>

                    <form action='promenaLozinke'>

                        <div class="form-group p-1 m-1">
                            <label class="text-primary" for="lozinka">Lozinka:</label>
                            <input type='password' class='border w-100' placeholder='Unesite lozniku' name='lozinka' minlength='5' required><br>
                        </div>

                        <div class="form-group p-1 m-1">
                            <label class="text-primary" for="nova_lozinka">Nova lozinka:</label>
                            <input type="password" class='border w-100' id="password" placeholder="Unesite novu lozinku" name="nova_lozinka" minlength='5' required><br>
                        </div>
                        <div class="form-group p-1 m-1">
                            <label class="text-primary" for='nova_lozinka_potvrda'>Potvrda nove lozinke:</label>
                            <input type="password" class='border w-100'  id="password_check" placeholder="Potvrda nove lozinke" name="nova_lozinka_potvrda" minlength='5' required><br>
                        </div>

                        <div class="d-flex justify-content-center">
                            <input type="submit" class="btn btn-primary text-center m-1 p-1" value='Promeni lozinku'>
                        </div>
                    </form>


                </div>
            <jsp:include page="parts/footer.jsp"></jsp:include>
<script type="text/javascript" src='provera_korisnickog_imena.js'>
</script>
        </div>
    </body>
</html>
