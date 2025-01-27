/*
- update isteklih rezervacija i blokiranih korisnika, 
pa prosledjivanje liste blokirani korisnika (ako je admin)
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
import klase.*;

/**
 *
 * @author iq skola
 */
public class PregledBlokiranihKorisnikaServlet extends HttpServlet {

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

            RequestDispatcher rd = request.getRequestDispatcher("pregled_korisnika.jsp");
            LocalDateTime pre48h = LocalDateTime.now().minusDays(2);

            ArrayList<Rezervacija> sveRezervacije = rezervacijaBaza.all();

            for (Rezervacija rezervacija : sveRezervacije) {
                if (pre48h.isEqual(rezervacija.getVreme().toLocalDateTime())
                        || rezervacija.getVreme().toLocalDateTime().plusDays(2).isBefore(LocalDateTime.now())) {
                    rezervacija.setStatus(Rezervacija.STATUS_ISTEKLO);
                    rezervacijaBaza.save(rezervacija);
                }
            }
            if (!ProvereKorisnik.postojiPrijavljenKorisnik(request)) {
                String poruka = "Morate biti prijavljen administrator kako biste pristupili stranici.";
                RequestDispatcher rd1 = request.getRequestDispatcher("prijava.jsp");
                request.setAttribute("poruka", poruka);
                rd1.forward(request, response);
                return;
            }
            if (ProvereKorisnik.postojiPrijavljenKorisnikOdredjenogTipa(request, Korisnik.TIP_ADMINISTRATOR)) {

                ArrayList<RegistrovaniKorisnik> sviKorisnici = registrovaniKorisnikBaza.all();
                ArrayList<RegistrovaniKorisnik> blokirani = registrovaniKorisnikBaza.allBlokirani();
                ArrayList<RegistrovaniKorisnik> korisnici = new ArrayList<>(blokirani);

                HashMap<Integer, ArrayList<Rezervacija>> korisniciRezervacije = new HashMap<>();
                for (Rezervacija rezervacija : sveRezervacije) {
                    ArrayList<Rezervacija> korisnikRezervacije = korisniciRezervacije.get(rezervacija.getKorisnikId());
                    if (korisnikRezervacije == null) {
                        korisnikRezervacije = new ArrayList<>();
                        korisniciRezervacije.put(rezervacija.getKorisnikId(), korisnikRezervacije);
                    }
                    korisnikRezervacije.add(rezervacija);
                }
                int brojIsteklihRezervacija = 0;

                for (RegistrovaniKorisnik korisnik : sviKorisnici) {

                    if (Korisnik.TIP_REGISTROVANI_KORISNIK.equals(korisnik.getTip())) {
                        Integer korisnikId = korisnik.getId();
                        ArrayList<Rezervacija> korisnikRezervacije = korisniciRezervacije.get(korisnikId);
                        if (korisnikRezervacije != null) {
                            for (Rezervacija rezervacija : korisnikRezervacije) {

                                if (Rezervacija.STATUS_ISTEKLO.equals(rezervacija.getStatus())) {
                                    brojIsteklihRezervacija++;
                                }
                                if (brojIsteklihRezervacija >= 3) {
                                    korisnik.setTip(Korisnik.TIP_BLOKIRANI_KORISNIK);
                                    korisnik = registrovaniKorisnikBaza.save(korisnik);
                                    korisnici.add(korisnik);
                                    // TODO:sta vec treba
                                    break; // zavrsila si sa ovom for petljom ovog korisnika
                                }

                            }
                        }

                    }

                }

                request.setAttribute("korisnici", korisnici);
                if (request.getAttribute("poruka") != null) {
                    request.setAttribute("poruka", request.getAttribute("poruka"));
                }
                if (request.getAttribute("porukaUspesno") != null) {
                    request.setAttribute("porukaUspesno", request.getAttribute("porukaUspesno"));
                }
                rd.forward(request, response);
            }
        } catch (Exception ex) {
            Logger.getLogger(PregledBlokiranihKorisnikaServlet.class.getName()).log(Level.SEVERE, null, ex);
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
