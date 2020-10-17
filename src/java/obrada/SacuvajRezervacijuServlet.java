/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package obrada;

import java.io.IOException;
import java.time.LocalDateTime;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import klase.*;

/**
 *
 * @author iq skola
 */
public class SacuvajRezervacijuServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession sesija = request.getSession();
        int korisnik_id = (Integer) sesija.getAttribute("korisnik_id");
        if (korisnik_id == -1) {
            response.sendRedirect("proveraPrijavljen");
            return;
        }

        String tip = sesija.getAttribute("tip").toString();
        String putanja;
        switch (tip) {
            case Korisnik.TIP_REGISTROVANI_KORISNIK:
                DogadjajBaza dogadjajBaza = new DogadjajBaza();
                Dogadjaj dogadjaj = dogadjajBaza.find(Integer.parseInt(request.getParameter("dogadjaj_id")));
                if(LocalDateTime.now().isAfter(dogadjaj.getDatum_i_vreme().minusDays(2))){
                    //poruka ulaznice je moguce kupiti samo na blagajni
                    response.sendRedirect("index");
                    return;
                }
                putanja = "mojeUlaznice";
                break;
            case Korisnik.TIP_BLAGAJNIK:
                putanja = "potvrdaRezervacije";
                break;
            default:
                response.sendRedirect("proveraPrijavljen");
                return;
        }

        RezervacijaBaza rezervacijaBaza = new RezervacijaBaza();
        Rezervacija rezervacija = new Rezervacija();
        /*if (request.getParameter("struktura_id") != null) {
            rezervacija = rezervacijaBaza.find(Integer.parseInt(request.getParameter("struktura_id")));
        }*/
        //ovde for petlja da prolazi kroz checkboxove? ili je to preko js?             

        rezervacija.setDogadjaj_id(Integer.parseInt(request.getParameter("dogadjaj_id")));
        rezervacija.setKorisnik_id(korisnik_id);
        rezervacija.setStruktura_id(Integer.parseInt(request.getParameter("struktura_id")));
        StrukturaUlaznicaBaza strukturaUlaznicaBaza = new StrukturaUlaznicaBaza();
        StrukturaUlaznica struktura = strukturaUlaznicaBaza.find(rezervacija.getStruktura_id());
        //prodjem kroz sve rezervacije za tog korisnika i nadjem po strukturi id, i onda sabiram broj_ulaznica
        //i poredim sa granicomPoKorisniku
        int brojUlaznica = Integer.parseInt(request.getParameter("broj_ulaznica"));
        if (struktura.getPreostalo_ulaznica() - brojUlaznica >= 0
                && struktura.getGranicaPoKorisniku() - brojUlaznica >= 0) {

            rezervacija.setBroj_ulaznica(brojUlaznica);
            struktura.setPreostalo_ulaznica(struktura.getPreostalo_ulaznica() - rezervacija.getBroj_ulaznica());
            strukturaUlaznicaBaza.save(struktura);
            rezervacija = rezervacijaBaza.save(rezervacija);
            RequestDispatcher rd = request.getRequestDispatcher(putanja);
            if (rezervacija.getId() > 0) {
                request.setAttribute("rezervacija_id", rezervacija.getId());
                
            } else {
                //poruka da nije uspesno
                response.sendRedirect("dogadjajPojedinacno");
                return;
            }
            rd.forward(request, response);

        } else {
            //nema vise dostupnih ulaznica, molimo izaberite drugu kategoriju.
            //ili jos bolje, da se prikazuje odmah poruka cim ide na submit, ali
            //mislim da svakako ostaje i ovde provera
        }

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
