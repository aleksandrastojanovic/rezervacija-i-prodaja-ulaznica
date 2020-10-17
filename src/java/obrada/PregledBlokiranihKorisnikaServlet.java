/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package obrada;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
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
public class PregledBlokiranihKorisnikaServlet extends HttpServlet {

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
        RequestDispatcher rd = request.getRequestDispatcher("pregled_korisnika.jsp");
        LocalDateTime pre48h = LocalDateTime.now().minusDays(2);

        RezervacijaBaza rezervacijaBaza = new RezervacijaBaza();
        ArrayList<Rezervacija> sveRezervacije = rezervacijaBaza.all();

        for (Rezervacija rezervacija : sveRezervacije) {
            if (pre48h.isEqual(rezervacija.getVreme().toLocalDateTime())
                    || rezervacija.getVreme().toLocalDateTime().plusDays(2).isBefore(LocalDateTime.now())) {
                rezervacija.setStatus(Rezervacija.STATUS_ISTEKLO);
                rezervacijaBaza.save(rezervacija);
            }
        }
        if (sesija.getAttribute("korisnik_id") == null || (int) sesija.getAttribute("korisnik_id") < 0) {
            response.sendRedirect("proveraPrijavljen");
            return;
        }
        if (Korisnik.TIP_ADMINISTRATOR.equals(sesija.getAttribute("tip"))) {
            KorisnikBaza korisnikBaza = new KorisnikBaza();
            ArrayList<Korisnik> sviKorisnici = korisnikBaza.all();
            ArrayList<Korisnik> korisnici = new ArrayList<>();

            HashMap<Integer, ArrayList<Rezervacija>> korisniciRezervacije = new HashMap<>();
            for (Rezervacija rezervacija : sveRezervacije) {
                ArrayList<Rezervacija> korisnikRezervacije = korisniciRezervacije.get(rezervacija.getKorisnik_id());
                if (korisnikRezervacije == null) {
                    korisnikRezervacije = new ArrayList<>();
                    korisniciRezervacije.put(rezervacija.getKorisnik_id(), korisnikRezervacije);
                }
                korisnikRezervacije.add(rezervacija);
            }
            int brojIsteklihRezervacija = 0;
            for (Korisnik korisnik : sviKorisnici) {
                if (Korisnik.TIP_REGISTROVANI_KORISNIK.equals(korisnik.getTip())) {
                    Integer korisnikId = korisnik.getId();
                    ArrayList<Rezervacija> korisnikRezervacije = korisniciRezervacije.get(korisnikId);
                    if (korisnikRezervacije != null) {
                        for (Rezervacija rezervacija : korisnikRezervacije) {

                            if (Rezervacija.STATUS_ISTEKLO.equals(rezervacija.getStatus())) {
                                brojIsteklihRezervacija++;
                            }
                            if (brojIsteklihRezervacija >= 3) {
                                // TODO:sta vec treba
                                break; // zavrsila si sa ovom for petljom ovog korisnika
                            }

                        }
                    }

                }

            }

            request.setAttribute("korisnici", korisnici);
            rd.forward(request, response);
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
