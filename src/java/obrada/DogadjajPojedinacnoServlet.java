/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package obrada;

import java.io.IOException;
import java.util.ArrayList;
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
public class DogadjajPojedinacnoServlet extends HttpServlet {

    private final StrukturaUlaznicaBaza strukturaUlaznicaBaza = new StrukturaUlaznicaBaza();
    private final DogadjajBaza dogadjajBaza = new DogadjajBaza();
    private final MediaBaza mediaBaza = new MediaBaza();

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
            if (!ProvereKorisnik.postojiPrijavljenKorisnik(request)) {
                String poruka = "Morate se prijaviti kako biste videli vise detalja.";
                RequestDispatcher rd = request.getRequestDispatcher("prijava.jsp");
                request.setAttribute("poruka", poruka);
                rd.forward(request, response);
                return;
            }

            RequestDispatcher rd = request.getRequestDispatcher("dogadjaj.jsp");
            Dogadjaj dogadjaj = dogadjajBaza.find(Integer.parseInt(request.getParameter("dogadjaj_id")));

            ArrayList<StrukturaUlaznica> strukture = strukturaUlaznicaBaza.allForDogadjajId(dogadjaj.getId());

            ArrayList<String> ostaleFotografije = new ArrayList<>();
            ArrayList<Media> medie = mediaBaza.allWhereDogadjajId(dogadjaj.getId());
            String videoIme;
            for (Media media : medie) {
                if (!Media.TIP_VIDEO.equals(media.getTip())) {
                    ostaleFotografije.add(media.getIme());
                } else {
                    videoIme = media.getIme();
                    request.setAttribute("videoIme", videoIme);
                }

            }

            request.setAttribute("strukture", strukture);
            request.setAttribute("dogadjaj", dogadjaj);
            request.setAttribute("ostaleFotografije", ostaleFotografije);

            rd.forward(request, response);
        } catch (Exception ex) {
            Logger.getLogger(DogadjajPojedinacnoServlet.class.getName()).log(Level.SEVERE, null, ex);
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
