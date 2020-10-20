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
            <h1>Kreiranje novog dogadjaja - 2/3</h1>
            <div>
                <form action = "sacuvajMedia" method = "post" enctype = "multipart/form-data">

                    <label for="glavna">Dodaj glavnu fotografiju:</label>
                    <input type="file" id="glavna" name="glavna" accept="image/*" required><br>

                    <label for="slike">Dodaj ostale fotografije:</label>
                    <input type="file" id="slike" name="slike" accept="image/*" multiple><br>                

                    <label for="video">Dodaj video zapis</label>
                    <input type="file" id="video" name="video" accept="video/*"><br>

                    <input type="submit" value="Dalje">
                    <!--<input type = "text" name = "imeSlike" placeholder="unesi ime slike">
                    <input type = "file" name = "glavna" size = "50" multiple>
                    <br>
                    <input type = "submit" value = "Upload File" >-->
                </form>
            </div>
        <jsp:include page="parts/footer.jsp"></jsp:include>
    </body>
</html>
