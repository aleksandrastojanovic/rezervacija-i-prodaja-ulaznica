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
            && sesija.getAttribute("tip") != null && Integer.parseInt(sesija.getAttribute("korisnik_id").toString())>0;
%>
<!DOCTYPE html>
<nav>
                <ul>
                    <% switch (sesija.getAttribute("tip").toString()) {
                            case Korisnik.TIP_REGISTROVANI_KORISNIK: %>
                    <li><a href="index">Pocetna stranica</a></li>
                     <li><ul>
                             <li><a href="mojeUlaznice">Moje ulaznice</a></li>
                            <li></li>
                            <li></li>
                            <li></li>
                            <li></li>
                            <li></li>
                        </ul>
                    </li>  
                    <% break;
                    case Korisnik.TIP_BLAGAJNIK: %>
                    <li><a href="prijavljenBlagajnik">Pocetna stranica</a></li>
                     <li><ul>
                             <li><a href="noviDogadjaj">Novi Dogadjaj</a></li>
                            <li></li>
                            <li></li>
                            <li></li>
                            <li></li>
                            <li></li>
                        </ul>
                    </li> 
                    <% break;
                    case Korisnik.TIP_ADMINISTRATOR: %>
                    <li><a href="prijavljenAdministrator">Pocetna stranica</a></li>
                     <li><ul>
                             <li><a href="pregledBlokiranihKorisnika">Pregled blokiranih korisnika:</a></li>
                             <li><a href="noviKorisnik">Novi korisnik</a></li>
                            <li></li>
                            <li></li>
                            <li></li>
                            <li></li>
                        </ul>
                    </li> 
                    <% break;
                    default: %>
                    <li><a href="index">Pocetna stranica</a></li>
                    <% } %>
                    <% if(uslov){%>
                    <li><a href="promena_lozinke.jsp">Promena lozinke</a></li>
                    <li><a href="proveraPrijavljen">Odjava</a></li>
                    <%} else {%>
                    <li><a href="proveraRegistrovan">Registruj se</a></li>
                    <li><a href="proveraPrijavljen">Prijavi se</a></li>
                    <% } %>
                    

                </ul>
            </nav>
