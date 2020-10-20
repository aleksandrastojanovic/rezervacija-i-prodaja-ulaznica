/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package obrada;

import java.io.IOException;
import java.time.LocalDateTime;
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
public class SacuvajDogadjajServlet extends HttpServlet {

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
            if (!ProvereKorisnik.postojiPrijavljenKorisnikOdredjenogTipa(request, Korisnik.TIP_BLAGAJNIK)) {
                response.sendRedirect("proveraPrijavljen");
                return;
            }

            RequestDispatcher rd = request.getRequestDispatcher("media");

            Blagajnik blagajnik = blagajnikBaza.find((int) sesija.getAttribute("korisnik_id"));

            Dogadjaj dogadjaj = new Dogadjaj();
            boolean noviDogadjaj = request.getParameter("dogadjaj_id") == null || Integer.parseInt(request.getParameter("dogadjaj_id")) <= 0;
            if (!noviDogadjaj) {
                dogadjaj = dogadjajBaza.find(Integer.parseInt(request.getParameter("dogadjaj_id")));
            }

            dogadjaj.setNaziv(request.getParameter("naziv"));
            dogadjaj.setNazivLokacije(blagajnik.getNazivLokacije());
            dogadjaj.setDatumIVreme(LocalDateTime.parse(request.getParameter("vreme_odrzavanja")));
            dogadjaj.setDetalji(request.getParameter("detalji"));

            dogadjaj = dogadjajBaza.save(dogadjaj);
            if (dogadjaj.getId() > 0) {
                //uspesno kreiran dogadjaj
                rd.forward(request, response);
            } else {
                //poruka da nije kreiran, za sad vraca na blagajnik_novi_dogadjaj.jsp
                response.sendRedirect("noviDogadjaj");

            }

        } catch (Exception ex) {
            Logger.getLogger(SacuvajDogadjajServlet.class.getName()).log(Level.SEVERE, null, ex);
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
