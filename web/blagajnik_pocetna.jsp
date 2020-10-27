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
            <div class="container-fluid pt-3 my-3 border">
            <%
                ArrayList<Dogadjaj> dogadjaji = (ArrayList<Dogadjaj>) request.getAttribute("dogadjaji");
            %>

            <%  HttpSession sesija = request.getSession();
                if (Korisnik.TIP_BLAGAJNIK.equals(sesija.getAttribute("tip"))) {
            %>
            <div class="container-fluid mb-3 p-3 w-25 text-center border border-primary rounded">
                <form action="potvrdaRezervacije">
                    <label class='text-primary '>ID rezervacije</label>
                    <input type="number" placeholder="Unesi ID rezervacije" name="rezervacija_id">
                    <input type="submit" class="btn btn-primary" value="Placanje">
                </form>
            </div>
            <%}%>
            <!-- 
            
            - Pregled dogadjaja za lokaciju blagajnika
            -->

            <div class="container-fluid">
                <table class="table">
                    <thead class="text-primary">
                    <th scope="col">Naziv dogadjaja</th>
                    <th scope="col">Naziv lokacije</th>
                    <th scope="col">Datum i vreme</th>
                    <th scope="col">Detalji</th>

                    </thead>
                    <%
                        for (Dogadjaj dogadjaj : dogadjaji) {%>
                    <tr class="text-secondary" scope="row">
                        <td style="width:20%"><%= ((Dogadjaj) dogadjaj).getNaziv()%></td>
                        <td style="width:20%"><%= ((Dogadjaj) dogadjaj).getNazivLokacije()%></td>
                        <%DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy. HH:mm");
                            String datumIVreme = dogadjaj.getDatumIVreme().format(formatter);%>
                        <td style="width:20%"><%= datumIVreme%></td>
                        <td style="width:20%"><%= ((Dogadjaj) dogadjaj).getDetalji()%></td>

                        <td style="width:5%"><a href="dogadjajPojedinacno?dogadjaj_id=<%=String.valueOf(dogadjaj.getId())%>">
                                <input type="button" class="btn btn-primary" name="dogadjajPojedinacno" value="Izaberi"></a></td>
                        <td style="width:5%"><a href="novaIzmenaDogadjaja?dogadjaj_id=<%=String.valueOf(dogadjaj.getId())%>">
                                <input type="button"  class="btn btn-primary" name="izmeni" value="Izmeni dogadjaj"></a></td>
                        <td style="width:10%"><a href="kategorijeUlaznica?dogadjaj_id=<%=String.valueOf(dogadjaj.getId())%>">
                                <input type="button"  class="btn btn-primary" name="kategorije" value="Kategorije ulaznica"></a></td>

                    </tr>
                    <%
                        }
                    %>
                </table>
            </div>
            <jsp:include page="parts/footer.jsp"></jsp:include>
        </div>
    </body>

</html>


