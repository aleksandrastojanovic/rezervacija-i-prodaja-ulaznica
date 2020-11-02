<%-- 
    Document   : potvrda_uplate
    Created on : Oct 13, 2020, 6:12:11 PM
    Author     : iq skola
--%>

<%@page import="klase.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="parts/pozadina.css">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
        <title>Potvrda uplate</title>
    </head>
    <body>
        <div class="container-fluid pt-3 mt-0" >
            <jsp:include page="parts/header.jsp"></jsp:include>

            <%
                StrukturaUlaznica struktura = (StrukturaUlaznica) request.getAttribute("struktura");
                Rezervacija rezervacija = (Rezervacija) request.getAttribute("rezervacija");
                Dogadjaj dogadjaj = (Dogadjaj) request.getAttribute("dogadjaj");
            %>
            <div class="container-fluid">
                <h2 class="text-primary text-center w-50">Potvrda placanja:</h2>
                <table class="table w-50">
                    <tr>
                        <td class="text-primary">ID rezervacije</td>
                        <td class="text-secondary"><%= rezervacija.getId()%></td>
                    </tr>
                    <tr>
                        <td class="text-primary">Naziv dogadjaja</td>
                        <td class="text-secondary"><%= dogadjaj.getNaziv()%></td>
                    </tr>
                    <tr>
                        <td class="text-primary">Naziv lokacije</td>
                        <td class="text-secondary"><%= dogadjaj.getNazivLokacije()%></td>
                    </tr>
                    <tr>
                        <td class="text-primary">Broj karata</td>
                        <td class="text-secondary"><%= rezervacija.getBrojUlaznica()%></td>
                    </tr>
                    <tr>
                        <td class="text-primary">Kategorija</td>
                        <td class="text-secondary"><%= struktura.getKategorija()%></td>
                    </tr>
                    <tr>
                        <td class="text-primary">Cena</td>
                        <td class="text-secondary"><%= struktura.getCena()%></td>
                    </tr>
                    <tr>
                        <td class="text-primary">Ukupna cena</td>
                        <td class="text-primary"><%= struktura.getCena() * rezervacija.getBrojUlaznica()%></td>
                    </tr>
                    <tr>
                        <td><a href="kupovinaUlaznica?rezervacija_id=<%=rezervacija.getId()%>">
                                <input type="buttton" class="btn btn-success p-3 m-1 text-center w-50" value="Potvrdi"></a>
                        </td>
                        <td><a href="otkazivanjeRezervacije?rezervacija_id=<%=rezervacija.getId()%>">
                                <input type="button" class="btn btn-danger p-3 m-1 text-center w-100" value="Otkazi"></a><br>
                        </td>
                    </tr>
                </table>

            </div>
        </div>
        <jsp:include page="parts/footer.jsp"></jsp:include>

    </body>
</html>
