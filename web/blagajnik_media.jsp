<%-- 
    Document   : blagajnik_media
    Created on : Oct 18, 2020, 12:22:41 PM
    Author     : iq skola
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
        <link rel="stylesheet" href="parts/pozadina.css">
        <title>Media</title>
    </head>
    <body>
        <jsp:include page="parts/header.jsp"></jsp:include>

            <div class="container-fluid p-3 mb-3 text-center w-100 border">
                <h1 class="text-primary text-center">Kreiranje novog dogadjaja - 2/3</h1>
                <div>
                    <form action = 'sacuvajMedia?dogadjaj_id=<%= request.getAttribute("dogadjaj_id")%>' method = "post" enctype = "multipart/form-data">

                    <div class="form-group p-1 m-1 row text-left">
                        <label class="text-primary col-sm-6" for="glavna">Dodaj glavnu fotografiju:</label>
                        <input type="file" class="border input-sm col-sm-6" id="glavna" name="glavna" accept="image/*" required><br>
                    </div>

                    <div class="form-group p-1 m-1 row text-left">
                        <label class="text-primary col-sm-6" for="slike">Dodaj ostale fotografije:</label>
                        <input type="file" class="border input-sm col-sm-6" id="slike" name="slike[]" accept="image/*" multiple='multiple'><br>
                    </div>

                    <div class="form-group p-1 m-1 row text-left">
                        <label class="text-primary col-sm-6" for="video">Dodaj video zapis</label>
                        <input type="file" class="border input-sm col-sm-6" id="video" name="video" accept="video/*"><br>
                    </div>

                    <div class="d-flex justify-content-center pt-3">
                        <input class="btn btn-primary btn-lg" type="submit" value="Dalje">
                    </div>

                </form>
            </div>
        </div>
        <jsp:include page="parts/footer.jsp"></jsp:include>

    </body>
</html>
