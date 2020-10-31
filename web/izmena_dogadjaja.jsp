<%-- 
    Document   : izmena_dogadjaja
    Created on : Sep 21, 2020, 12:39:24 PM
    Author     : iq skola
--%>

<%@page import="java.time.format.DateTimeFormatter"%>
<%@page import="klase.Blagajnik"%>
<%@page import="klase.Dogadjaj"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
        <title>Izmena dogadjaja</title>
    </head>
    <body>
        <<jsp:include page="parts/header.jsp"></jsp:include>

        <%
            Dogadjaj dogadjaj = (Dogadjaj) request.getAttribute("dogadjaj");
            Blagajnik blagajnik = (Blagajnik) request.getAttribute("bagajnik");
        %>
        <h1 class="text-primary">Izmena dogadjaja</h1>
        <div class="container-fluid p-3">
            <form action="sacuvajDogadjaj">

                <label class="text-primary">Mesto odrzavanja</label>
                <%= blagajnik.getNazivLokacije()%><br>

                <label class="text-primary" for="naziv">Naziv:</label>
                <input type='text' id='naziv' name='naziv' value="<%= dogadjaj.getNaziv()%>"><br>
                <input type="hidden" name="dogadjaj_id" value="<%= "" + dogadjaj.getId()%>" >

                <label class="text-primary" for="vreme_odrzavanja">Vreme odrzavanja:</label>
                <%DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy. HH:mm");
                    String datumIVreme = dogadjaj.getDatumIVreme().format(formatter);%>
                <input type="datetime-local" id="vreme_odrzavanja" name="vreme_odrzavanja" value="<%= datumIVreme%>"><br>

                <label class="text-primary" for="detalji">Detalji dogadjaja</label>
                <input type="text" id='detalji' name="detalji" value="<%= dogadjaj.getDetalji()%>"><br>

                <label class="text-primary" for="slika_glavna">Glavna fotografija:</label>
                <input type="file" id="slika_glavna" name="slika_glavna" accept="image/*" value="<%= dogadjaj.getGlavnaSlikaPutanja()%>"><br>

                <label class="text-primary" for="slike">Dodaj ostale fotografije:</label>
                <input type="file" id="slike" name="slike" accept="image/*" multiple><br>

                <% if (dogadjaj.getVideoPutanja() != null) {%>

                <label class="text-primary" for="video">Dodaj video zapis</label>
                <input type="file" id="video" name="video" accept="video/*" value="<%= dogadjaj.getVideoPutanja()%>"><br>
                <% }%>
                <!--Preko js bi trebalo da napravim da bude required cena i limit ako je cekirano -->


                <input type="submit" class="btn btn-primary" value="Sacuvaj izmene">

            </form>
        </div>
        <jsp:include page="parts/footer.jsp"></jsp:include>
    </body>
</html>
