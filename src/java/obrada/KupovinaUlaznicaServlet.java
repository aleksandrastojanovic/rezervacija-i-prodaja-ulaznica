/*
       - blagajnik moze da izvrsi kupovinu
       - menja status rezervacije
       - menja broj preostalih ulaznica za strukturu
 */
package obrada;

import modeli.StrukturaUlaznica;
import modeli.Rezervacija;
import modeli.Korisnik;
import bazaKlase.StrukturaUlaznicaBaza;
import bazaKlase.RezervacijaBaza;
import java.io.IOException;
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
public class KupovinaUlaznicaServlet extends HttpServlet {

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
            /*
            - Proverava da li je blagajnik
            - Ako jeste vrsi prodaju
            - vraca ga na pocetnu
             */
            if (!ProvereKorisnik.postojiPrijavljenKorisnik(request)) {
                String poruka = "Morate biti prijavljeni kako biste pristupili stranici.";
                RequestDispatcher rd1 = request.getRequestDispatcher("prijava.jsp");
                request.setAttribute("poruka", poruka);
                rd1.forward(request, response);
                return;
            }
            if (ProvereKorisnik.postojiPrijavljenKorisnikOdredjenogTipa(request, Korisnik.TIP_BLAGAJNIK)) {

                Rezervacija rezervacija = rezervacijaBaza.find(Integer.parseInt(request.getParameter("rezervacija_id")));
                if (Rezervacija.STATUS_REZERVISANO.equals(rezervacija.getStatus())) {
                    rezervacija.setStatus(Rezervacija.STATUS_PLACENO);
                    StrukturaUlaznica struktura = strukturaUlaznicaBaza.find(rezervacija.getStrukturaId());
                    struktura.setPreostaloUlaznica(struktura.getPreostaloUlaznica() - rezervacija.getBrojUlaznica());

                    strukturaUlaznicaBaza.save(struktura);
                    rezervacija = rezervacijaBaza.save(rezervacija);
                    if (rezervacija.getId() > 0 && rezervacija.getStatus().equals(Rezervacija.STATUS_PLACENO)) {
                        String porukaUspesno = "Uspesno izvrsena prodaja.";
                        RequestDispatcher rd = request.getRequestDispatcher("blagajnik_pocetna");
                        request.setAttribute("porukaUspesno", porukaUspesno);
                        rd.forward(request, response);
                    } else {
                        String poruka = "Nije moguce izvrsiti placanje.";
                        RequestDispatcher rd1 = request.getRequestDispatcher("blagajnik_pocetna.jsp");
                        request.setAttribute("poruka", poruka);
                        rd1.forward(request, response);
                    }
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(KupovinaUlaznicaServlet.class.getName()).log(Level.SEVERE, null, ex);
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
