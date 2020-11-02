/*
-ukoliko prodje proveru, vrsi prijavu i salje na odgovarajuci servlet/jsp
 */
package obrada;

import at.favre.lib.crypto.bcrypt.BCrypt;
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
public class PrijavaServlet extends HttpServlet {

    private final KorisnikBaza korisnikBaza = new KorisnikBaza();

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
            /* Prolazi redom kroz korisnike, 
        i u zavisnosti od tipa korisnika prosledjuje na odgovarajuci prijavljenServlet.*/

            ArrayList<Korisnik> korisnici = korisnikBaza.all();

            for (Korisnik korisnik : korisnici) {
                String lozinka = request.getParameter("password");
                if (korisnik.getKorisnickoIme().equals(request.getParameter("username"))
                        && BCrypt.verifyer().verify(lozinka.toCharArray(), korisnik.getLozinka()).verified) {
                    HttpSession sesija = request.getSession();
                    String putanja = "";
                    String tip = korisnik.getTip();
                    sesija.setAttribute("tip", tip);
                    int korisnikId = korisnik.getId();

                    switch (tip) {
                        case Korisnik.TIP_REGISTROVANI_KORISNIK:
                            sesija.setAttribute("korisnik_id", korisnikId);
                            putanja = "proveraRegistrovanogKorisnika";
                            break;
                        case Korisnik.TIP_BLAGAJNIK:
                            sesija.setAttribute("korisnik_id", korisnikId);
                            putanja = "prijavljenBlagajnik";
                            break;
                        case Korisnik.TIP_ADMINISTRATOR:
                            sesija.setAttribute("korisnik_id", korisnikId);
                            putanja = "prijavljenAdministrator";
                            break;
                        case Korisnik.TIP_BLOKIRANI_KORISNIK:
                            sesija.setAttribute("korisnik_id", -1);
                            String poruka = "Blokirani ste. Molimo kontaktirajte administratora.";
                            RequestDispatcher rd1 = request.getRequestDispatcher("index.jsp");
                            request.setAttribute("poruka", poruka);
                            rd1.forward(request, response);
                            break;

                    }
                    RequestDispatcher rd = request.getRequestDispatcher(putanja);
                    rd.forward(request, response);
                    return;

                }

            }

            RequestDispatcher rd = request.getRequestDispatcher("prijava.jsp");
            String poruka = "Neispravno uneto korisnicko ime/lozinka. Molimo pokusajte ponovo.";
            request.setAttribute("poruka", poruka);
            rd.forward(request, response);

        } catch (IOException | ServletException ex) {
            Logger.getLogger(PrijavaServlet.class.getName()).log(Level.SEVERE, null, ex);
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
