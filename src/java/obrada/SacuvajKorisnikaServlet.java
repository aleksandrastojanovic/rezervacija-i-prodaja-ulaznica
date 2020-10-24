/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package obrada;

import at.favre.lib.crypto.bcrypt.BCrypt;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
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
public class SacuvajKorisnikaServlet extends HttpServlet {

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
                response.sendRedirect("proveraPrijavljen");
                return;
            }
            if (Korisnik.TIP_ADMINISTRATOR.equals(sesija.getAttribute("tip"))) {

                switch (request.getParameter("korisnik")) {
                    case Korisnik.TIP_REGISTROVANI_KORISNIK: {

                        ArrayList<RegistrovaniKorisnik> korisnici = registrovaniKorisnikBaza.all();

                        for (RegistrovaniKorisnik korisnik : korisnici) {
                            RegistrovaniKorisnik k = korisnik;
                            if (k.getKorisnickoIme().equals(request.getParameter("username"))) {
                                //poruka korisnicko ime vec postoji, pokusajte ponovo.
                                //vraca na ("admin_novi_korisnik.jsp") za sad,
                                //ne znam kako da se to radi i na frontu
                                response.sendRedirect("admin_novi_korisnik.jsp");//da ostane, metod vraca null
                                return;
                            }

                        }
                        RegistrovaniKorisnik korisnik = new RegistrovaniKorisnik();
                        korisnik.setIme(request.getParameter("ime"));
                        korisnik.setPrezime(request.getParameter("prezime"));
                        korisnik.setKorisnickoIme(request.getParameter("username"));
                        String lozinka = request.getParameter("password");
                        if (lozinka.equals(request.getParameter("password_check"))) {
                            String sifrovanaLozinka = BCrypt.withDefaults().hashToString(BCrypt.MIN_COST, lozinka.toCharArray());
                            korisnik.setLozinka(sifrovanaLozinka);
                        }
                        korisnik.setGrad(request.getParameter("grad"));
                        korisnik.setAdresa(request.getParameter("adresa"));
                        korisnik.setKontaktTelefon(request.getParameter("telefon"));
                        korisnik.setEmail(request.getParameter("email"));
                        korisnik = registrovaniKorisnikBaza.save(korisnik);
                        if (korisnik.getId() > 0) {
                            //poruka uspesno kreiran korisnik
                            response.sendRedirect("prijavljenAdministrator");
                        }
                        break;
                    }
                    case Korisnik.TIP_BLAGAJNIK: {

                        ArrayList<Blagajnik> korisnici = blagajnikBaza.all();

                        for (Blagajnik korisnik : korisnici) {
                            Blagajnik k = korisnik;
                            if (k.getKorisnickoIme().equals(request.getParameter("username"))) {
                                //poruka korisnicko ime vec postoji, pokusajte ponovo.
                                //vraca na ("admin_novi_korisnik.jsp") za sad,
                                //ne znam kako da se to radi i na frontu
                                response.sendRedirect("admin_novi_korisnik.jsp");
                                return;
                            }

                        }
                        Blagajnik korisnik = new Blagajnik();
                        korisnik.setIme(request.getParameter("ime"));
                        korisnik.setPrezime(request.getParameter("prezime"));
                        korisnik.setKorisnickoIme(request.getParameter("username"));
                        if (request.getParameter("password").equals(request.getParameter("password_check"))) {
                            String sifrovanaLozinka = BCrypt.withDefaults().hashToString(BCrypt.MIN_COST, request.getParameter("password").toCharArray());
                            korisnik.setLozinka(sifrovanaLozinka);
                        }
                        korisnik.setNazivLokacije(request.getParameter("naziv_lokacije"));
                        korisnik.setGradLokacije(request.getParameter("grad_lokacije"));
                        korisnik.setAdresaLokacije(request.getParameter("adresa_lokacije"));
                        korisnik = blagajnikBaza.save(korisnik);
                        if (korisnik.getId() > 0) {
                            response.sendRedirect("prijavljenAdministrator");
                            //poruka uspesno kreiran korisnik
                        }
                        break;
                    }
                    case Korisnik.TIP_ADMINISTRATOR: {

                        ArrayList<Administrator> korisnici = administratorBaza.all();

                        for (Administrator korisnik : korisnici) {
                            Administrator k = korisnik;
                            if (k.getKorisnickoIme().equals(request.getParameter("username"))) {
                                //poruka korisnicko ime vec postoji, pokusajte ponovo.
                                //vraca na ("admin_novi_korisnik.jsp") za sad,
                                //ne znam kako da se to radi i na frontu
                                response.sendRedirect("admin_novi_korisnik.jsp");
                                return;
                            }
                        }

                        Administrator korisnik = new Administrator();
                        korisnik.setIme(request.getParameter("ime"));
                        korisnik.setPrezime(request.getParameter("prezime"));
                        korisnik.setKorisnickoIme(request.getParameter("username"));
                        if (request.getParameter("password").equals(request.getParameter("password_check"))) {
                            String sifrovanaLozinka = BCrypt.withDefaults().hashToString(BCrypt.MIN_COST, request.getParameter("password").toCharArray());
                            korisnik.setLozinka(sifrovanaLozinka);
                        }
                        korisnik = administratorBaza.save(korisnik);
                        if (korisnik.getId() > 0) {
                            response.sendRedirect("prijavljenAdministrator");

                            //poruka uspesno kreiran korisnik
                        }
                        break;
                    }
                    default:
                        response.sendRedirect("prijavljenAdministrator");
                        break;
                }

            }
        } catch (Exception ex) {
            Logger.getLogger(SacuvajKorisnikaServlet.class.getName()).log(Level.SEVERE, null, ex);
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
