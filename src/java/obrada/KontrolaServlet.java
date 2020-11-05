/*
        - update za istekle rezervacije
        - update za blokirane korisnike
        - setuje tip i korisnikId u sesiji
        - poziva se u header-u u svakoj jsp stranici
 */
package obrada;

import modeli.RegistrovaniKorisnik;
import modeli.Rezervacija;
import modeli.Korisnik;
import bazaKlase.RezervacijaBaza;
import bazaKlase.RegistrovaniKorisnikBaza;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author iq skola
 */
public class KontrolaServlet extends HttpServlet {

    private final RezervacijaBaza rezervacijaBaza = new RezervacijaBaza();
    private final RegistrovaniKorisnikBaza registrovaniKorisnikBaza = new RegistrovaniKorisnikBaza();

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
            if (sesija.getAttribute("korisnik_id") == null) {
                sesija.setAttribute("korisnik_id", -1);
                sesija.setAttribute("tip", Korisnik.TIP_NEREGISTROVANI_KORISNIK);
            }
            int korisnikId = (int) sesija.getAttribute("korisnik_id");
            LocalDateTime pre48h = LocalDateTime.now().minusDays(2);
            ArrayList<Rezervacija> sveRezervacije = rezervacijaBaza.all();
            HashMap<Integer, Integer> korisnikIstekleRezervacije = new HashMap<>();
            for (Rezervacija rezervacija : sveRezervacije) {
                if (!Rezervacija.STATUS_PLACENO.equals(rezervacija.getStatus()) && (pre48h.isEqual(rezervacija.getVreme().toLocalDateTime())
                        || pre48h.isAfter(rezervacija.getVreme().toLocalDateTime()))) {

                    rezervacija.setStatus(Rezervacija.STATUS_ISTEKLO);
                    rezervacija = rezervacijaBaza.save(rezervacija);
                    Integer brojISteklihRezervacija = korisnikIstekleRezervacije.get(rezervacija.getKorisnikId());
                    if (brojISteklihRezervacija == null) {
                        brojISteklihRezervacija = 0;
                    }
                    korisnikIstekleRezervacije.put(rezervacija.getKorisnikId(), ++brojISteklihRezervacija);
                }
            }
            ArrayList<RegistrovaniKorisnik> registrovaniKorisnici = registrovaniKorisnikBaza.all();
            for (RegistrovaniKorisnik registrovaniKorisnik : registrovaniKorisnici) {
                Integer brojISteklihRezervacija = korisnikIstekleRezervacije.get(registrovaniKorisnik.getId());
                if (brojISteklihRezervacija != null && brojISteklihRezervacija >= 3) {
                    registrovaniKorisnik.setTip(Korisnik.TIP_BLOKIRANI_KORISNIK);
                    registrovaniKorisnikBaza.save(registrovaniKorisnik);

                    if (registrovaniKorisnik.getId() == korisnikId) {
                        RequestDispatcher rd = request.getRequestDispatcher("blokirani_korisnik.jsp");
                        String poruka = "Blokirani ste. Kontaktirajte aministratora.";
                        sesija.setAttribute("korisnik_id", -1);
                        sesija.setAttribute("tip", Korisnik.TIP_NEREGISTROVANI_KORISNIK);
                        request.setAttribute("poruka", poruka);
                        rd.forward(request, response);
                    }

                }

            }
        } catch (Exception ex) {
            Logger.getLogger(KontrolaServlet.class.getName()).log(Level.SEVERE, null, ex);
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

}
