/*
- vrsi cuvanje kategorije u bazu, i vraca na pravljenje nove strukture
 */
package obrada;

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
public class SacuvajKategorijuServlet extends HttpServlet {

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
            if (!ProvereKorisnik.postojiPrijavljenKorisnikOdredjenogTipa(request, Korisnik.TIP_BLAGAJNIK)) {
                String poruka = "Morate biti prijavljen blagajnik kako biste pristupili stranici.";
                RequestDispatcher rd1 = request.getRequestDispatcher("prijava.jsp");
                request.setAttribute("poruka", poruka);
                rd1.forward(request, response);
                return;
            }

            StrukturaUlaznica struktura = new StrukturaUlaznica();
            if (request.getParameter("struktura_id") != null) {
                struktura = strukturaUlaznicaBaza.find(Integer.parseInt(request.getParameter("struktura_id")));
                struktura.setId(Integer.parseInt(request.getParameter("struktura_id")));
            }

            struktura.setKategorija(request.getParameter("kategorija"));
            struktura.setCena(Double.parseDouble(request.getParameter("cena")));
            struktura.setBrojDostupnihUlaznica(Integer.parseInt(request.getParameter("broj_ulaznica")));
            struktura.setPreostaloUlaznica(struktura.getBrojDostupnihUlaznica());
            struktura.setGranicaPoKorisniku(Integer.parseInt(request.getParameter("granica_po_korisniku")));
            struktura.setIdDogadjaja(Integer.parseInt(request.getParameter("dogadjaj_id")));

            struktura = strukturaUlaznicaBaza.save(struktura);

            if (struktura.getId() > 0) {

                RequestDispatcher rd = request.getRequestDispatcher("blagajnik_nova_struktura.jsp");
                String porukaUspesno = "Uspesno sacuvana kategorija.";
                request.setAttribute("porukaUspesno", porukaUspesno);
                request.setAttribute("dogadjaj_id", struktura.getIdDogadjaja());
                rd.forward(request, response);

            } else {
                String poruka = "Neuspesno cuvanje kategorije.";
                RequestDispatcher rd1 = request.getRequestDispatcher("blagajnik_pocetna.jsp");
                request.setAttribute("poruka", poruka);
                rd1.forward(request, response);
            }
        } catch (Exception ex) {
            Logger.getLogger(SacuvajKategorijuServlet.class.getName()).log(Level.SEVERE, null, ex);
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
