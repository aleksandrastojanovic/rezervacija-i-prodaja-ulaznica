/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package obrada;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
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

    private final DogadjajBaza dogadjajBaza = new DogadjajBaza();
    private final RezervacijaBaza rezervacijaBaza = new RezervacijaBaza();
    private final StrukturaUlaznicaBaza strukturaUlaznicaBaza = new StrukturaUlaznicaBaza();

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
        try {
            response.setContentType("text/html;charset=UTF-8");
            HttpSession sesija = request.getSession();
            int korisnikId = (Integer) sesija.getAttribute("korisnik_id");
            if (!ProvereKorisnik.postojiPrijavljenKorisnik(request)) {
                response.sendRedirect("proveraPrijavljen");
                return;
            }

            String tip = sesija.getAttribute("tip").toString();
            String putanja;

            Dogadjaj dogadjaj = dogadjajBaza.find(Integer.parseInt(request.getParameter("dogadjaj_id")));
            ArrayList<Rezervacija> rezervacije = rezervacijaBaza.all();

            Rezervacija rezervacija = napraviRezervaciju(sesija, request);
            StrukturaUlaznica struktura = strukturaUlaznicaBaza.find(rezervacija.getStrukturaId());

            int brojUlaznica = Integer.parseInt(request.getParameter("broj_ulaznica"));
            switch (tip) {
                case Korisnik.TIP_REGISTROVANI_KORISNIK:
                    if (LocalDateTime.now().isAfter(dogadjaj.getDatumIVreme().minusDays(2))) {
                        //poruka ulaznice je moguce kupiti samo na blagajni
                        response.sendRedirect("index");
                        return;
                    }
                    putanja = "mojeUlaznice";
                    //prodjem kroz sve rezervacije za tog korisnika i nadjem po strukturi id, i onda sabiram broj_ulaznica
                    //i poredim sa granicomPoKorisniku
                    //i rezervacija vazi dva dana, nakon cega registrovani korisnik ne moze da rezervise za taj dogadjaj, ako nije placeno

                    for (Rezervacija rezervacijaKorisnika : rezervacije) {
                        int ukupanBrojUlaznica = brojUlaznica;
                        if (rezervacijaKorisnika.getKorisnikId() == korisnikId) {
                            if (rezervacijaKorisnika.getDogadjajId() == dogadjaj.getId()
                                    && !Rezervacija.STATUS_PLACENO.equals(rezervacijaKorisnika.getStatus())) {
                                putanja = "dogadjajPojedinacno"; //greska ne moze da rezervise za taj dogadjaj dok ne plati
                                request.setAttribute("dogadjajId", dogadjaj.getId());
                            }
                            if (rezervacijaKorisnika.getStrukturaId() == struktura.getId()) {
                                ukupanBrojUlaznica += rezervacijaKorisnika.getBrojUlaznica();
                                if (struktura.getGranicaPoKorisniku() - ukupanBrojUlaznica < 0) {
                                    response.sendRedirect("index"); //greska ne moze da rezervise vise od granicaPoKorisniku karata
                                }
                            }
                        }
                    }
                    break;
                case Korisnik.TIP_BLAGAJNIK:
                    putanja = "potvrdaRezervacije";
                    break;
                default:
                    response.sendRedirect("proveraPrijavljen");
                    return;
            }

            if (struktura.getPreostaloUlaznica() - brojUlaznica >= 0) {
                rezervacija.setBrojUlaznica(brojUlaznica);
                struktura.setPreostaloUlaznica(struktura.getPreostaloUlaznica() - rezervacija.getBrojUlaznica());
                strukturaUlaznicaBaza.save(struktura);
                rezervacija = rezervacijaBaza.save(rezervacija);

                if (rezervacija.getId() > 0) {
                    request.setAttribute("rezervacija_id", rezervacija.getId());
                } else {
                    //poruka da nije uspesno
//                response.sendRedirect("dogadjajPojedinacno");
                    putanja = "dogadjajPojedinacno";
                    request.setAttribute("dogadjajId", dogadjaj.getId());
                }
            } else {
                //nema vise dostupnih ulaznica, molimo izaberite drugu kategoriju.
                //ili jos bolje, da se prikazuje odmah poruka cim ide na submit, ali
                //mislim da svakako ostaje i ovde provera
                response.sendRedirect("index");
            }
            RequestDispatcher rd = request.getRequestDispatcher(putanja);
            rd.forward(request, response);
        } catch (Exception ex) {
            Logger.getLogger(SacuvajRezervacijuServlet.class.getName()).log(Level.SEVERE, null, ex);
            response.sendRedirect("error.jsp");
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

    private Rezervacija napraviRezervaciju(HttpSession session, HttpServletRequest request) {
        int korisnikId = (int) session.getAttribute("korisnik_id");
        Rezervacija rezervacija = new Rezervacija();
        rezervacija.setDogadjajId(Integer.parseInt(request.getParameter("dogadjaj_id")));
        rezervacija.setKorisnikId(korisnikId);
        rezervacija.setStrukturaId(Integer.parseInt(request.getParameter("struktura_id")));
        return rezervacija;
    }
}
