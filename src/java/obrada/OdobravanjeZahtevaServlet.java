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
public class OdobravanjeZahtevaServlet extends HttpServlet {
    
    private final RegistrovaniKorisnikBaza registrovaniKorisnikBaza = new RegistrovaniKorisnikBaza();
    private final RezervacijaBaza rezervacijaBaza = new RezervacijaBaza();

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
                String poruka = "Morate biti prijavljen administrator kako biste pristupili stranici.";
                RequestDispatcher rd1 = request.getRequestDispatcher("prijava.jsp");
                request.setAttribute("poruka", poruka);
                rd1.forward(request, response);
                return;
            }
            if (ProvereKorisnik.postojiPrijavljenKorisnikOdredjenogTipa(request, Korisnik.TIP_ADMINISTRATOR)) {
                RegistrovaniKorisnik korisnik = registrovaniKorisnikBaza.find((Integer.parseInt(request.getParameter("korisnik_id"))));
                korisnik.setTip(Korisnik.TIP_REGISTROVANI_KORISNIK);
                
                ArrayList<Rezervacija> sveRezervacije = rezervacijaBaza.all();
                for (Rezervacija rezervacija : sveRezervacije) {
                    if (rezervacija.getKorisnikId() == korisnik.getId()
                            && Rezervacija.STATUS_ISTEKLO.equals(rezervacija.getStatus())) {
                        rezervacijaBaza.delete(rezervacija);
                    }
                }
                
                korisnik = registrovaniKorisnikBaza.save(korisnik);
                if (korisnik.getId() > 0) {
                    RequestDispatcher rd = request.getRequestDispatcher("admin_pocetna.jsp");
                    String porukaUspesno = "Uspesno odblokiran korisnik.";
                    request.setAttribute("porukaUspesno", porukaUspesno);
                    rd.forward(request, response);
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(OdobravanjeZahtevaServlet.class.getName()).log(Level.SEVERE, null, ex);
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
