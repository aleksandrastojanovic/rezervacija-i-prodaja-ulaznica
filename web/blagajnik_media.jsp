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
        <title>Media</title>
    </head>
    <body>
        <header>
            <!-- Meni -->
            <jsp:include page="parts/meni.jsp"></jsp:include>
        </header>
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
    </body>
</html>
