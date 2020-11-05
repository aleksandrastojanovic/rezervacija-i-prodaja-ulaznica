/*
- proverava da li se blagajnik uspesno ulogovao i salje na odgovarajucu pocetnu stranicu
 */
package obrada;

import modeli.Korisnik;
import modeli.Dogadjaj;
import modeli.Blagajnik;
import bazaKlase.DogadjajBaza;
import bazaKlase.BlagajnikBaza;
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
public class PrijavljenBlagajnikServlet extends HttpServlet {

    private final BlagajnikBaza blagajnikBaza = new BlagajnikBaza();
    private final DogadjajBaza dogadjajBaza = new DogadjajBaza();

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
            if (ProvereKorisnik.postojiPrijavljenKorisnikOdredjenogTipa(request, Korisnik.TIP_BLAGAJNIK)) {
                RequestDispatcher rd = request.getRequestDispatcher("blagajnik_pocetna.jsp");
                Blagajnik blagajnik = (Blagajnik) blagajnikBaza.find((Integer) sesija.getAttribute("korisnik_id"));
                request.setAttribute("korisnik", blagajnik);

                ArrayList<Dogadjaj> sviDogadjaji = dogadjajBaza.all();
                ArrayList<Dogadjaj> dogadjaji = new ArrayList<>();
                for (Dogadjaj dogadjaj : sviDogadjaji) {
                    if (dogadjaj.getNazivLokacije().equals(blagajnik.getNazivLokacije())) {
                        dogadjaji.add(dogadjaj);
                    }
                }
                request.setAttribute("dogadjaji", dogadjaji);
                if (request.getAttribute("poruka") != null) {
                    request.setAttribute("poruka", request.getAttribute("poruka"));
                }
                if (request.getAttribute("porukaUspesno") != null) {
                    request.setAttribute("porukaUspesno", request.getAttribute("porukaUspesno"));
                }

                rd.forward(request, response);
            } else {
                String poruka = "Morate biti prijavljen blagajnik kako biste pristupili stranici.";
                RequestDispatcher rd1 = request.getRequestDispatcher("prijava.jsp");
                request.setAttribute("poruka", poruka);
                rd1.forward(request, response);
            }
        } catch (Exception ex) {
            Logger.getLogger(PrijavljenBlagajnikServlet.class.getName()).log(Level.SEVERE, null, ex);
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
