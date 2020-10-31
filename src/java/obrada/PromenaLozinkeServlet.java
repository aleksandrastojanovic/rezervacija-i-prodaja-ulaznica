/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package obrada;

import at.favre.lib.crypto.bcrypt.BCrypt;
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
public class PromenaLozinkeServlet extends HttpServlet {

    private final RegistrovaniKorisnikBaza registrovaniKorisnikBaza = new RegistrovaniKorisnikBaza();
    private final BlagajnikBaza blagajnikBaza = new BlagajnikBaza();
    private final AdministratorBaza administratorBaza = new AdministratorBaza();

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
                String poruka = "Morate biti prijavljeni kako biste pristupili stranici.";
                RequestDispatcher rd1 = request.getRequestDispatcher("prijava.jsp");
                request.setAttribute("poruka", poruka);
                rd1.forward(request, response);
                return;
            }

            String lozinka = request.getParameter("lozinka");
            String novaLozinka = request.getParameter("nova_lozinka");
            String novaLozinkaPotvrda = request.getParameter("nova_lozinka_potvrda");

            if (novaLozinka.equals(novaLozinkaPotvrda)) {
                String tip = sesija.getAttribute("tip").toString();
                int korisnikId = (int) sesija.getAttribute("korisnik_id");
                RequestDispatcher rd;
                String porukaUspesno;
                switch (tip) {
                    case Korisnik.TIP_REGISTROVANI_KORISNIK:
                        RegistrovaniKorisnik korisnik = registrovaniKorisnikBaza.find(korisnikId);
                        if (verifikujIIzmeniLozinku(korisnik, lozinka, novaLozinka)) {
                            registrovaniKorisnikBaza.save(korisnik);

                        } else {
                            String poruka = "Pogresno uneta postojeca lozinka";
                            RequestDispatcher rd1 = request.getRequestDispatcher("index.jsp");
                            request.setAttribute("poruka", poruka);
                            rd1.forward(request, response);
                        }
                        rd = request.getRequestDispatcher("index.jsp");
                        porukaUspesno = "Uspesno promenjena lozinka.";
                        request.setAttribute("porukaUspesno", porukaUspesno);
                        rd.forward(request, response);
                        break;
                    case Korisnik.TIP_BLAGAJNIK:
                        Blagajnik blagajnik = blagajnikBaza.find(korisnikId);
                        if (verifikujIIzmeniLozinku(blagajnik, lozinka, novaLozinka)) {
                            blagajnikBaza.save(blagajnik);
                        }
                        rd = request.getRequestDispatcher("blagajnik_pocetna.jsp");
                        porukaUspesno = "Uspesno promenjena lozinka.";
                        request.setAttribute("porukaUspesno", porukaUspesno);
                        rd.forward(request, response);
                        break;
                    case Korisnik.TIP_ADMINISTRATOR:
                        Administrator administrator = administratorBaza.find(korisnikId);
                        if (verifikujIIzmeniLozinku(administrator, lozinka, novaLozinka)) {
                            administratorBaza.save(administrator);

                        }
                        rd = request.getRequestDispatcher("admin_pocetna.jsp");
                        porukaUspesno = "Uspesno promenjena lozinka.";
                        request.setAttribute("porukaUspesno", porukaUspesno);
                        rd.forward(request, response);
                        break;
                    default:
                        response.sendRedirect("proveraPrijavljen");
                        break;

                }

            } else {
                String poruka = "Netacan unos/potvrda nove loznike.";
                RequestDispatcher rd1 = request.getRequestDispatcher("index.jsp");
                request.setAttribute("poruka", poruka);
                rd1.forward(request, response);
            }
        } catch (Exception ex) {
            Logger.getLogger(PromenaLozinkeServlet.class.getName()).log(Level.SEVERE, null, ex);
            response.sendRedirect("error.jsp");
        }

    }

    private boolean verifikujIIzmeniLozinku(Korisnik korisnik, String lozinka, String novaLozinka) {
        if (BCrypt.verifyer().verify(lozinka.toCharArray(), korisnik.getLozinka()).verified) {
            String sifrovanaLozinka = BCrypt.withDefaults().hashToString(BCrypt.MIN_COST, novaLozinka.toCharArray());
            korisnik.setLozinka(sifrovanaLozinka);
            return true;
        }
        return false;
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
