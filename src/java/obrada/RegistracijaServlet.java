/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package obrada;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import klase.*;

/**
 *
 * @author iq skola
 */
public class RegistracijaServlet extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");
        /* Ukoliko ne postoji korisnik sa unetim korisnickim imenom,
        pravi novog korisnika i smesta ga u bazu.
        */
        /*-Ocitava podatke iz forme registracija*
          -Postavlja tip korisnika na registrovani korisnik*
          -Prosledjuje ih adminu na odobravanje?
          - Prikazuje poruku da je zahtev uspesno prihvacen?
         */
        RegistrovaniKorisnikBaza korisnikBaza = new RegistrovaniKorisnikBaza();
        ArrayList<RegistrovaniKorisnik> korisnici = korisnikBaza.all();

        for (RegistrovaniKorisnik korisnik : korisnici) {
            RegistrovaniKorisnik k = korisnik;
            if (k.getKorisnicko_ime().equals(request.getParameter("username"))) {
                //poruka korisnicko ime vec postoji, pokusajte ponovo. 
                //vraca na registracija za sad,
                //ne znam kako da se to radi i na frontu
                response.sendRedirect("registracija.jsp");
                return;
            }
            
        }
        RegistrovaniKorisnik registrovaniKorisnik = new RegistrovaniKorisnik();

        registrovaniKorisnik.setIme(request.getParameter("ime"));
        registrovaniKorisnik.setPrezime(request.getParameter("prezime"));
        registrovaniKorisnik.setKorisnicko_ime(request.getParameter("username"));
        /*ne znam da li ova provera treba ovde ili preko js */
        // Najbolje na oba mesta, svakako mora ovde
        // ako nije dobra mora da se vrati na registraciju opet, sa porukom da sifre nisu iste
        if (request.getParameter("password").equals(request.getParameter("password_check"))) {
            registrovaniKorisnik.setLozinka(request.getParameter("password"));

        }
        registrovaniKorisnik.setGrad(request.getParameter("grad"));
        registrovaniKorisnik.setAdresa(request.getParameter("adresa"));
        registrovaniKorisnik.setKontakt_telefon(request.getParameter("telefon"));
        registrovaniKorisnik.setEmail(request.getParameter("email"));

        /*Ovaj deo se valjda izvrsava u OdobravanjeZahtevaServlet:
        -Uz izmene klase na RegistrovaniKorisnik*/
        RegistrovaniKorisnikBaza registrovaniKorisnikBaza = new RegistrovaniKorisnikBaza();

        registrovaniKorisnik = registrovaniKorisnikBaza.save(registrovaniKorisnik);
        //ArrayList<RegistrovaniKorisnik> korisnici = registrovaniKorisnikBaza.all();
        if (registrovaniKorisnik.getId() > 0) {
            //gde prosledjuje zavisi od uloge administratora
            response.sendRedirect("prijava.jsp");
        } else {
            response.sendRedirect("registracija.jsp");
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
