/*
- potvrda rezervacije od strane blagajnika ukoliko vrsi kupovinu nerezervisanih ulaznica
 */
package obrada;

import modeli.StrukturaUlaznica;
import modeli.Rezervacija;
import modeli.Korisnik;
import modeli.Dogadjaj;
import modeli.Blagajnik;
import bazaKlase.StrukturaUlaznicaBaza;
import bazaKlase.RezervacijaBaza;
import bazaKlase.DogadjajBaza;
import bazaKlase.BlagajnikBaza;
import java.io.IOException;
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
public class PotvrdaRezervacijeServlet extends HttpServlet {

    private final RezervacijaBaza rezervacijaBaza = new RezervacijaBaza();
    private final StrukturaUlaznicaBaza strukturaUlaznicaBaza = new StrukturaUlaznicaBaza();
    private final DogadjajBaza dogadjajBaza = new DogadjajBaza();
    private final BlagajnikBaza blagajnikBaza = new BlagajnikBaza();

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
        try {
            if (!ProvereKorisnik.postojiPrijavljenKorisnik(request)) {
                String poruka = "Morate biti prijavljeni kako biste pristupili stranici.";
                RequestDispatcher rd1 = request.getRequestDispatcher("prijava.jsp");
                request.setAttribute("poruka", poruka);
                rd1.forward(request, response);
                return;
            }

            HttpSession sesija = request.getSession();
            int korisnikId = (int) sesija.getAttribute("korisnik_id");
            if (ProvereKorisnik.postojiPrijavljenKorisnikOdredjenogTipa(request, Korisnik.TIP_BLAGAJNIK)) {
                int rezervacijaId;
                if (request.getParameter("rezervacija_id") != null) {
                    rezervacijaId = Integer.parseInt(request.getParameter("rezervacija_id"));
                } else if (request.getAttribute("rezervacija_id") != null) {
                    rezervacijaId = (int) request.getAttribute("rezervacija_id");
                } else {
                    String poruka = "Morate biti prijavljen blagajnik kako biste pristupili stranici.";
                    RequestDispatcher rd1 = request.getRequestDispatcher("prijava.jsp");
                    request.setAttribute("poruka", poruka);
                    rd1.forward(request, response);
                    return;
                }
                Rezervacija rezervacija = rezervacijaBaza.find(rezervacijaId);

                StrukturaUlaznica strukturaUlaznica = strukturaUlaznicaBaza.find(rezervacija.getStrukturaId());

                Dogadjaj dogadjaj = dogadjajBaza.find(rezervacija.getDogadjajId());
                Blagajnik blagajnik = blagajnikBaza.find(korisnikId);
                if (!blagajnik.getNazivLokacije().equals(dogadjaj.getNazivLokacije())) {
                    String poruka = "Nije pronadjena rezervacija";
                    RequestDispatcher rd1 = request.getRequestDispatcher("blagajnik_pocetna.jsp");
                    request.setAttribute("poruka", poruka);
                    rd1.forward(request, response);
                    return;
                }

                RequestDispatcher rd = request.getRequestDispatcher("potvrda_uplate.jsp");
                if (request.getAttribute("poruka") != null) {
                    request.setAttribute("poruka", request.getAttribute("poruka"));
                }
                if (request.getAttribute("porukaUspesno") != null) {
                    request.setAttribute("porukaUspesno", request.getAttribute("porukaUspesno"));
                }
                request.setAttribute("rezervacija", rezervacija);
                request.setAttribute("struktura", strukturaUlaznica);
                request.setAttribute("dogadjaj", dogadjaj);
                rd.forward(request, response);

            } else {
                String poruka = "Morate biti prijavljen blagajnik kako biste pristupili stranici.";
                RequestDispatcher rd1 = request.getRequestDispatcher("prijava.jsp");
                request.setAttribute("poruka", poruka);
                rd1.forward(request, response);
            }
        } catch (Exception ex) {
            Logger.getLogger(PotvrdaRezervacijeServlet.class.getName()).log(Level.SEVERE, null, ex);
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
