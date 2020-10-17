<%-- 
    Document   : meni
    Created on : Oct 15, 2020, 6:10:20 PM
    Author     : iq skola
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    HttpSession sesija = request.getSession();
    boolean uslov = sesija.getAttribute("korisnik_id") != null 
            && sesija.getAttribute("tip") != null && Integer.parseInt(sesija.getAttribute("korisnik_id").toString())>0;
%>
<!DOCTYPE html>
<nav>
                <ul>
                    <li><a href="index">Pocetna stranica</a></li>
                    <!-- <li><ul>
                            <li>Pozoriste</li>
                            <li>Muzika</li>
                            <li>Sport</li>
                            <li>Festivali</li>
                            <li>Muzeji</li>
                            <li>Ostalo</li>
                        </ul>
                    </li>   -->
                    <% if(uslov){%>
                    <li><a href="promena_lozinke.jsp">Promena lozinke</a></li>
                    <li><a href="proveraPrijavljen">Odjava</a></li>
                    <%} else {%>
                    <li><a href="proveraRegistrovan">Registruj se</a></li>
                    <li><a href="proveraPrijavljen">Prijavi se</a></li>
                    <% } %>
                    

                </ul>
            </nav>
