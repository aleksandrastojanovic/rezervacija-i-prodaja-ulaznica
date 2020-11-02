<%-- 
    Document   : error
    Created on : Oct 19, 2020, 2:23:07 PM
    Author     : iq skola
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
        <style>
            body, html {
                height: 100%;
            }

            .bg {
                /* The image used */
                background-image: url("error.png");

                /* Full height */
                height: 100%;

                /* Center and scale the image nicely */

                background-repeat: no-repeat;
                background-size: cover;
            }

        </style>
        <title>Error</title>
    </head>
    <body>
        <jsp:include page="parts/header.jsp"></jsp:include>
            <div class="container-fluid bg pt-5">


                <h1 class="py-5 text-center">Doslo je do greske
                </h1>

            <jsp:include page="parts/footer.jsp"></jsp:include>
        </div>
    </body>
</html>
