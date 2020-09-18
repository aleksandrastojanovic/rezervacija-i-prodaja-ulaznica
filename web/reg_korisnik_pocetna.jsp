<%@page import="klase.Dogadjaj"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% ArrayList<Dogadjaj> dogadjaji = (ArrayList<Dogadjaj>)request.getAttribute("dogadjaji");
%>
<!DOCTYPE html>
<html>
    <head>
        <title>Pocetna stranica</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>
    
    <body>
        <header>
        <!-- Meni -->
        <nav>
        <ul>
            <li>Pocetna stranica</li>
            <li><ul>
                    <li>Pozoriste</li>
                    <li>Muzika</li>
                    <li>Sport</li>
                    <li>Festivali</li>
                    <li>Muzeji</li>
                    <li>Ostalo</li>
                </ul>
            </li>            
            <li>Registruj se</li>
            <li>Prijavi se</li>
                        
        </ul>
        </nav>
    </header>
        <div>
            <form>
                <label for='po_nazivu'>Pretrazi po nazivu:</label>
                <input type="text" id="po_nazivu" name='po_nazivu' placeholder='Unesite naziv'><br>
                
                <label for='vreme_od'>Pretrazi po datumu od:</label>
                <input type="datetime-local" id="vreme_od" name='vreme_od' placeholder='Unesite od kog datuma'>
                <label for='vreme_do'> do:</label>
                <input type="datetime-local" id="vreme_do" name='vreme_do' placeholder='Unesite do kog datuma'><br>
                
                <label for='po_mestu'>Pretrazi po mestu odrzavanja:</label>
                <input type="text" id="po_mestu" name='po_metu' placeholder='Unesite mesto odrzavanja'><br>
            </form>
        </div>
        <div>
            <table>
                <thead>
                <th>Naziv dogadjaja</th>
                <th>Naziv lokacije</th>
                <th>Datum i vreme</th>
                <th>Detalji</th>
                </thead>
                <%
                for(Dogadjaj dogadjaj: dogadjaji){%>
                <tr>
                    <td><%= ((Dogadjaj)dogadjaj).getNaziv()%></td>
                   <td><%= ((Dogadjaj)dogadjaj).getNaziv_lokacije() %></td>
                    <td><%= ((Dogadjaj)dogadjaj).getDatum_i_vreme()%></td>
                    <td><%= ((Dogadjaj)dogadjaj).getDetalji()%></td>
                </tr>
                 <%   
                }
                %>
                <!-- <tr>
                    <td></td>
                </tr> -->
            </table>
        </div>
        
        <footer>
        <!-- Povratak na vrh -->
        
    </footer>
    </body>
    
</html>


