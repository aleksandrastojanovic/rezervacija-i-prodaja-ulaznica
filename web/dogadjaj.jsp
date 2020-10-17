<%-- 
    Document   : dogadjaj
    Created on : Sep 23, 2020, 6:15:38 PM
    Author     : iq skola
--%>

<%@page import="java.time.LocalDateTime"%>
<%@page import="klase.Korisnik"%>
<%@page import="java.time.format.DateTimeFormatter"%>
<%@page import="klase.StrukturaUlaznica"%>
<%@page import="java.util.ArrayList"%>
<%@page import="klase.Dogadjaj"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% Dogadjaj dogadjaj = (Dogadjaj) request.getAttribute("dogadjaj");
    ArrayList<StrukturaUlaznica> strukture = (ArrayList<StrukturaUlaznica>) request.getAttribute("strukture");%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><%= "" + dogadjaj.getNaziv()%></title>
    </head>
    <body>
        <jsp:include page="parts/meni.jsp"></jsp:include>
        <h1><%= "" + dogadjaj.getNaziv()%></h1>
        <div>
            Mesto odrzavanja: <%= dogadjaj.getNaziv_lokacije()%><br>
            <%DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
                String datumIVreme = dogadjaj.getDatum_i_vreme().format(formatter);%>
            Vreme odrzavanja: <%= datumIVreme%><br>
            Detalji: <%= dogadjaj.getDetalji()%><br>
            <!--fotografije i video kad bude front, za sad bez, plan da bude galerija -->
        </div>
        <%  HttpSession sesija = request.getSession();
            if (sesija.getAttribute("tip").equals(Korisnik.TIP_BLAGAJNIK)) {
        %>
        <div>
            <form aciton="potvrdaRezervacije">
                <label>ID rezervacije</label>
                <input type="number" placeholder="Unesi ID rezervacije" name="rezervacija_id">
                <input type="submit" value="Placanje">
            </form>
        </div>
        <%}%>

        <% if (Korisnik.TIP_BLAGAJNIK.equals(sesija.getAttribute("tip"))
                    || (Korisnik.TIP_REGISTROVANI_KORISNIK.equals(sesija.getAttribute("tip"))
                    && LocalDateTime.now().isBefore(dogadjaj.getDatum_i_vreme().minusDays(2)))){ %>
        <div>
            <form action='sacuvajRezervaciju'>
                <h3>Rezervisi ulaznice:</h3><br>
                <table>
                    <thead>
                    <th></th>
                    <th>Kategorija:</th>
                    <th>Cena:</th>
                    <th>Broj ulaznica</th>
                    </thead>
                    <%
                        for (StrukturaUlaznica struktura : strukture) {%>
                    <tr>
                    <input type="hidden" name="struktura_id" value='<%= "" + struktura.getId()%>' >
                    <input type="hidden" name="dogadjaj_id" value="<%= "" + struktura.getId_dogadjaja()%>" >
                    <td><input type="radio" name='kategorija' id='<%= "" + struktura.getKategorija()%>'
                               value='<%= "" + struktura.getId()%>'></td>
                    <td><%= struktura.getKategorija()%></td>
                    <td><%= struktura.getCena()%></td>
                    <td><input type="number" placeholder="Broj ulaznica" name='broj_ulaznica' value='0'></td>

                    </tr>
                    <%
                        }
                    %>
                </table>
                <input type="submit" value='Rezervisi'>
            </form>
                <% } %>
        </div>        
    </body>
</html>
