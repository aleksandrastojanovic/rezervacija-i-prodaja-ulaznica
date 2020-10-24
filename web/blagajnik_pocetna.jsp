<%-- 
    Document   : blagajnik_pocetna
    Created on : Aug 29, 2020, 2:11:16 PM
    Author     : iq skola
--%>

<%@page import="java.time.format.DateTimeFormatter"%>
<%@page import="klase.Korisnik"%>
<%@page import="klase.Dogadjaj"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>        
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
        <title>Pocetna stranica/blaganik</title>
    </head>

    <body>
        <jsp:include page="parts/header.jsp"></jsp:include>
        <%
            ArrayList<Dogadjaj> dogadjaji = (ArrayList<Dogadjaj>) request.getAttribute("dogadjaji");
        %>
        <!-- 
        
        - Pregled dogadjaja za lokaciju blagajnika
        -->

        <div>
            <table>
                <thead>
                <th>Naziv dogadjaja</th>
                <th>Naziv lokacije</th>
                <th>Datum i vreme</th>
                <th>Detalji</th>
                <th></th>
                </thead>
                <%
                    for (Dogadjaj dogadjaj : dogadjaji) {%>
                <tr>
                    <td><%= ((Dogadjaj) dogadjaj).getNaziv()%></td>
                    <td><%= ((Dogadjaj) dogadjaj).getNazivLokacije()%></td>
                    <%DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
                        String datumIVreme = dogadjaj.getDatumIVreme().format(formatter);%>
                    <td><%= datumIVreme%></td>
                    <td><%= ((Dogadjaj) dogadjaj).getDetalji()%></td>

                    <td><a href="dogadjajPojedinacno?dogadjaj_id=<%=String.valueOf(dogadjaj.getId())%>">
                            <input type="button" name="dogadjajPojedinacno" value="Izaberi"></a></td>
                    <td><a href="novaIzmenaDogadjaja?dogadjaj_id=<%=String.valueOf(dogadjaj.getId())%>">
                            <input type="button" name="izmeni" value="Izmeni dogadjaj"></a></td>
                    <td><a href="kategorijeUlaznica?dogadjaj_id=<%=String.valueOf(dogadjaj.getId())%>">
                            <input type="button" name="kategorije" value="Kategorije ulaznica"></a></td>

                </tr>
                <%
                    }
                %>
            </table>
        </div>
        <%  HttpSession sesija = request.getSession();
            if (Korisnik.TIP_BLAGAJNIK.equals(sesija.getAttribute("tip"))) {
        %>
        <div>
            <form action="potvrdaRezervacije">
                <label>ID rezervacije</label>
                <input type="number" placeholder="Unesi ID rezervacije" name="rezervacija_id">
                <input type="submit" value="Placanje">
            </form>
        </div>
        <%}%>
        <jsp:include page="parts/footer.jsp"></jsp:include>
    </body>

</html>


