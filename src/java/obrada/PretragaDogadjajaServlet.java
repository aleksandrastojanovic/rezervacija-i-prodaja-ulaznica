/*
- vrsi pretragu dogadjaja za unete parametre (prikazuju se rezultati koji prodju sve filtere)
 */
package obrada;

import modeli.Dogadjaj;
import bazaKlase.DogadjajBaza;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author iq skola
 */
public class PretragaDogadjajaServlet extends HttpServlet {

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
            /*
        -Omogucava pretragu dogadjaja po nazivu
        -Omogucava pretragu dogadjaja po vremenskom intervalu
        -Omogucava pretragu dogadjaja po mestu odrzavanja
        -U slucaju da je u pitanju registrovani korisnik, omogucava preusmeravanje na rezervaciju(pojedinacni dogadjaj
             */

            ArrayList<Dogadjaj> sviDogadjaji = dogadjajBaza.all();
            ArrayList<Dogadjaj> dogadjajiSviFilteri = new ArrayList<>();
            boolean prosaoSveFiltere = true;

            String naziv = request.getParameter("naziv").toLowerCase();
            naziv = naziv.trim();
            String vremeOdParametar = request.getParameter("vreme_od");
            String vremeDoParametar = request.getParameter("vreme_do");
            LocalDateTime vremeOd = vremeOdParametar != null && !vremeOdParametar.isEmpty() ? LocalDateTime.parse(vremeOdParametar) : null;
            LocalDateTime vremeDo = vremeDoParametar != null && !vremeDoParametar.isEmpty() ? LocalDateTime.parse(vremeDoParametar) : null;

            String mesto = request.getParameter("mesto");
            if (mesto != null) {
                mesto = mesto.toLowerCase().trim();
            }

            for (Dogadjaj dogadjaj : sviDogadjaji) {
                prosaoSveFiltere = true;
                if (prosaoSveFiltere && naziv != null && !naziv.isEmpty()) {
                    prosaoSveFiltere = dogadjaj.getNaziv().toLowerCase().contains(naziv) || dogadjaj.getNaziv().toLowerCase().equals(naziv);
                }
                if (prosaoSveFiltere && vremeOd != null) {
                    prosaoSveFiltere = vremeOd.isAfter(LocalDateTime.now())
                            && (dogadjaj.getDatumIVreme().isAfter(vremeOd)
                            || dogadjaj.getDatumIVreme().isEqual(vremeOd));
                }
                if (prosaoSveFiltere && vremeDo != null) {

                    prosaoSveFiltere = vremeDo.isAfter(LocalDateTime.now())
                            && (dogadjaj.getDatumIVreme().isBefore(vremeDo)
                            || dogadjaj.getDatumIVreme().isEqual(vremeDo));
                }
                if (prosaoSveFiltere && mesto != null && !mesto.isEmpty()) {
                    prosaoSveFiltere = dogadjaj.getNazivLokacije().toLowerCase().contains(mesto);
                }
                if (prosaoSveFiltere) {
                    if (dogadjaj.getDatumIVreme().isAfter(LocalDateTime.now())) {
                        dogadjajiSviFilteri.add(dogadjaj);
                    }
                }

            }
            if (dogadjajiSviFilteri.size() > 0) {
                RequestDispatcher rd = request.getRequestDispatcher("rezultat_pretrage.jsp");
                request.setAttribute("dogadjaji", dogadjajiSviFilteri);
                rd.forward(request, response);
            } else {
                String poruka = "Nema rezultata za zadate kriterijume pretrage.";
                RequestDispatcher rd1 = request.getRequestDispatcher("index.jsp");
                request.setAttribute("poruka", poruka);
                rd1.forward(request, response);
            }

        } catch (Exception ex) {
            Logger.getLogger(PretragaDogadjajaServlet.class.getName()).log(Level.SEVERE, null, ex);
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
