<%-- 
    Document   : potvrda_uplate
    Created on : Oct 13, 2020, 6:12:11 PM
    Author     : iq skola
--%>

<%@page import="klase.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% 
    StrukturaUlaznica struktura = (StrukturaUlaznica)request.getAttribute("struktura");
    Rezervacija rezervacija = (Rezervacija)request.getAttribute("rezervacija");
    Dogadjaj dogadjaj = (Dogadjaj)request.getAttribute("dogadjaj");
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <jsp:include page="parts/meni.jsp"></jsp:include>
        <div>
            <p>
                Id rezervacije: <%= rezervacija.getId()%><br>
                Naziv dogadjaja: <%= dogadjaj.getNaziv() %><br>
                Naziv lokacije: <%= dogadjaj.getNazivLokacije()%><br>
                Broj karata: <%= rezervacija.getBrojUlaznica()%><br>
                Kategorija: <%= struktura.getKategorija() %><br>
                Cena: <%= struktura.getCena() %><br>
                Ukupna cena: <%= struktura.getCena() * rezervacija.getBrojUlaznica()%><br>
                <a href="kupovinaUlaznica?rezervacija_id=<%=rezervacija.getId()%>">
                    <input type="buttton" value="Potvrdi"></a><br>
                <a href="otkazivanjeRezervacije?rezervacija_id=<%=rezervacija.getId()%>">
                    <input type="button" value="Otkazi"></a><br>
            </p>
        </div>
    </body>
</html>
