/*
 - dozvoljava registrovanom korisniku i blagajniku brisanje rezervacije
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
import javax.servlet.http.HttpSession;
import klase.*;

/**
 *
 * @author iq skola
 */
public class OtkazivanjeRezervacijeServlet extends HttpServlet {

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
            if (!ProvereKorisnik.postojiPrijavljenKorisnik(request)) {
                String poruka = "Morate biti prijavljen korisnik kako biste pristupili stranici.";
                RequestDispatcher rd1 = request.getRequestDispatcher("prijava.jsp");
                request.setAttribute("poruka", poruka);
                rd1.forward(request, response);
                return;
            }
            String putanja = "";
            if (Korisnik.TIP_REGISTROVANI_KORISNIK.equals(sesija.getAttribute("tip"))) {
                putanja = "mojeUlaznice";
            }
            if (Korisnik.TIP_BLAGAJNIK.equals(sesija.getAttribute("tip"))) {
                putanja = "prijavljenBlagajnik";
            }
            RequestDispatcher rd = request.getRequestDispatcher(putanja);
            Rezervacija rezervacija = rezervacijaBaza.find(Integer.parseInt(request.getParameter("rezervacija_id")));
            StrukturaUlaznica strukturaUlaznica = strukturaUlaznicaBaza.find(rezervacija.getStrukturaId());
            strukturaUlaznica.setPreostaloUlaznica(strukturaUlaznica.getPreostaloUlaznica() + rezervacija.getBrojUlaznica());
            strukturaUlaznicaBaza.save(strukturaUlaznica);
            rezervacijaBaza.delete(rezervacija);
            String porukaUspesno = "Uspesno otkazana rezervacija.";
            request.setAttribute("porukaUspesno", porukaUspesno);
            rd.forward(request, response);
        } catch (Exception ex) {
            Logger.getLogger(OtkazivanjeRezervacijeServlet.class.getName()).log(Level.SEVERE, null, ex);
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
