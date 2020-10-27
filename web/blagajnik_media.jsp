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
        <title>Media</title>
    </head>
    <body>
        <jsp:include page="parts/header.jsp"></jsp:include>
            <div class="container-fluid p-3 my-3 border">
                <h1 class="text-primary">Kreiranje novog dogadjaja - 2/3</h1>
                <div>
                    <form action = 'sacuvajMedia?dogadjaj_id=<%= request.getAttribute("dogadjaj_id")%>' method = "post" enctype = "multipart/form-data">

                    <div class="form-group">
                        <label class="text-primary" for="glavna">Dodaj glavnu fotografiju:</label>
                        <input type="file" id="glavna" name="glavna" accept="image/*" required><br>
                    </div>

                    <div class="form-group">
                        <label class="text-primary" for="slike">Dodaj ostale fotografije:</label>
                        <input type="file" id="slike" name="slike[]" accept="image/*" multiple='multiple'><br>
                    </div>

                    <div class="form-group">
                        <label class="text-primary" for="video">Dodaj video zapis</label>
                        <input type="file" id="video" name="video" accept="video/*"><br>
                    </div>

                    <input class="btn btn-primary" type="submit" value="Dalje">

                </form>
            </div>
            <jsp:include page="parts/footer.jsp"></jsp:include>
        </div>
    </body>
</html>
