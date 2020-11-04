/*
-Dozvoljava registrovanom korisniku da pritupi na moje_ulaznice.jsp
-Prosledjuje rezervacije korisnika
 */
package obrada;

import modeli.Rezervacija;
import modeli.Korisnik;
import bazaKlase.RezervacijaBaza;
import java.io.IOException;
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
public class MojeUlazniceServlet extends HttpServlet {

    RezervacijaBaza rezervacijaBaza = new RezervacijaBaza();

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
            if (ProvereKorisnik.postojiPrijavljenKorisnikOdredjenogTipa(request, Korisnik.TIP_REGISTROVANI_KORISNIK)) {
                RequestDispatcher rd = request.getRequestDispatcher("moje_ulaznice.jsp");

                ArrayList<Rezervacija> sveRezervacije = rezervacijaBaza.all();
                ArrayList<Rezervacija> rezervacije = new ArrayList<>();
                for (Rezervacija rezervacija : sveRezervacije) {
                    if (rezervacija.getKorisnikId() == (int) sesija.getAttribute("korisnik_id")) {
                        rezervacije.add(rezervacija);
                    }
                }
                request.setAttribute("rezervacije", rezervacije);
                rd.forward(request, response);
            } else {
                String poruka = "Morate biti prijavljen registrovani korisnik.";
                RequestDispatcher rd1 = request.getRequestDispatcher("prijava.jsp");
                request.setAttribute("poruka", poruka);
                rd1.forward(request, response);
            }
        } catch (Exception ex) {
            Logger.getLogger(MojeUlazniceServlet.class.getName()).log(Level.SEVERE, null, ex);
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
