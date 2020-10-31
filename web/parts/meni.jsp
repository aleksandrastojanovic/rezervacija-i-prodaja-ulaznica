<%-- 
    Document   : meni
    Created on : Oct 15, 2020, 6:10:20 PM
    Author     : iq skola
--%>

<%@page import="klase.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    HttpSession sesija = request.getSession();
    boolean uslov = sesija.getAttribute("korisnik_id") != null
            && sesija.getAttribute("tip") != null && Integer.parseInt(sesija.getAttribute("korisnik_id").toString()) > 0;
%>
<!DOCTYPE html>
<!--<div class="container-fluid pt-3">-->
<div class="container-fluid p-3 my-3 border">
    <nav class="navbar  fixed-top navbar-expand-sm bg-light navbar-light justify-content-end text-primary">
        <% if (sesija.getAttribute("tip") != null) {
                String tip = sesija.getAttribute("tip").toString();%>
        <!--<ul class="nav nav-tabs">-->
        <ul class="nav nav-tabs justify-content-end">
            <% if (Korisnik.TIP_REGISTROVANI_KORISNIK.equals(tip)) { %>
            <li class="nav-items" role="presentation"><a class="nav-link"  href="index">Pocetna stranica</a></li>
            <li class="nav-items" role="presentation" class="dropdown"><a class="nav-link"  href="mojeUlaznice">Moje ulaznice</a></li>

            <% } else if (Korisnik.TIP_BLAGAJNIK.equals(tip)) { %>
            <li class="nav-items" role="presentation"><a class="nav-link"  href="prijavljenBlagajnik">Pocetna stranica</a></li>
            <li class="nav-items" role="presentation"><a class="nav-link"  href="noviDogadjaj">Novi Dogadjaj</a></li>

            <% } else if (Korisnik.TIP_ADMINISTRATOR.equals(tip)) { %>
            <li class="nav-items" role="presentation"><a class="nav-link"  href="prijavljenAdministrator">Pocetna stranica</a></li>
            <li class="nav-items" role="presentation"><a class="nav-link"  href="pregledBlokiranihKorisnika">Pregled blokiranih korisnika</a></li>
            <li class="nav-items" role="presentation"><a class="nav-link"  href="noviKorisnik">Novi korisnik</a></li>
                <% } else { %>
            <li class="nav-items" role="presentation"><a class="nav-link"  href="index">Pocetna stranica</a></li>
                <% } %>


            <li class="nav-items"role="presentation" class="dropdown">
                <a class="nav-link"   class="dropdown-toggle" data-toggle="dropdown" href="#" role="button" aria-haspopup="true" aria-expanded="false">
                    Opcije
                    <span class="caret"></span>
                </a>
                <ul class="dropdown-menu dropdown-menu-right justify-content-end">
                    <% if (uslov) {%>
                    <li class="nav-items" ><a class="dropdown-item text-secondary"  href="promena_lozinke.jsp">Promena lozinke</a></li>
                    <li class="nav-items" ><a class="dropdown-item text-secondary"  href="proveraPrijavljen">Odjava</a></li>
                        <%} else {%>
                    <li class="nav-items" ><a class="dropdown-item text-secondary"  href="proveraRegistrovan">Registruj se</a></li>
                    <li class="nav-items" ><a class="dropdown-item text-secondary"  href="proveraPrijavljen">Prijavi se</a></li>
                        <% }%>
                </ul>
            </li>



        </ul>
        <% }%>
    </nav>
</div>
