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
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
        <title>Potvrda uplate</title>
    </head>
    <body>
        <jsp:include page="parts/header.jsp"></jsp:include>
        <%
            StrukturaUlaznica struktura = (StrukturaUlaznica) request.getAttribute("struktura");
            Rezervacija rezervacija = (Rezervacija) request.getAttribute("rezervacija");
            Dogadjaj dogadjaj = (Dogadjaj) request.getAttribute("dogadjaj");
        %>
        <div>
            <p>
                Id rezervacije: <%= rezervacija.getId()%><br>
                Naziv dogadjaja: <%= dogadjaj.getNaziv()%><br>
                Naziv lokacije: <%= dogadjaj.getNazivLokacije()%><br>
                Broj karata: <%= rezervacija.getBrojUlaznica()%><br>
                Kategorija: <%= struktura.getKategorija()%><br>
                Cena: <%= struktura.getCena()%><br>
                Ukupna cena: <%= struktura.getCena() * rezervacija.getBrojUlaznica()%><br>
                
                <a href="kupovinaUlaznica?rezervacija_id=<%=rezervacija.getId()%>">
                    <input type="buttton" value="Potvrdi"></a><br>
                <a href="otkazivanjeRezervacije?rezervacija_id=<%=rezervacija.getId()%>">
                    <input type="button" value="Otkazi"></a><br>
            </p>
        </div>
        <jsp:include page="parts/footer.jsp"></jsp:include>
    </body>
</html>
