/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package obrada;

import java.io.IOException;
import java.sql.Timestamp;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import klase.Blagajnik;
import klase.BlagajnikBaza;
import klase.Dogadjaj;
import klase.DogadjajBaza;
import klase.StrukturaUlaznica;
import klase.StrukturaUlaznicaBaza;


/**
 *
 * @author iq skola
 */
public class NoviDogadjajServlet extends HttpServlet {

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
        /*
        -Ocitava podatke iz blagajnik_novi_dogadjaj
        -Na osnovu njih kreira novi dogadjaj i po potrebi novu kategoriju ulaznica
        -Smesta novi dogadjaj u bazu
        -Postavlja ogranicenja za rezervaciju, podrazumevano je 48h pre pocetka dogadjaja
        -Postavlja ogranicenje broja ulaznica koje jedan korisnik moze da rezervise tj. kupi*/
        
        HttpSession sesija = request.getSession();
        BlagajnikBaza blagajnikBaza = new BlagajnikBaza();
        Blagajnik blagajnik = blagajnikBaza.find((int)sesija.getAttribute("korisnik_id"));
        if(blagajnik.getId() == -1){
            response.sendRedirect("index.jsp");
            return;
        }
        
        Dogadjaj dogadjaj = new Dogadjaj();
        dogadjaj.setNaziv(request.getParameter("naziv"));
        dogadjaj.setNaziv_lokacije(blagajnik.getNaziv_lokacije());
        dogadjaj.setDatum_i_vreme(Timestamp.valueOf(request.getParameter("vreme_odrzavanja")).toLocalDateTime());
        dogadjaj.setDetalji(request.getParameter("detalji"));
        /*
        Nisam sigurna kako funkcionise, da li ovako prima putanju ili ne, mada 
        se ne buni, znaci String je.*/ 
        // Za sad ignorisi fotke to cemo kasnije
        dogadjaj.setGlavna_slika_putanja(request.getParameter("slika_glavna"));
        dogadjaj.setVideo_putanja(request.getParameter("video"));
        
        DogadjajBaza dogadjajBaza = new DogadjajBaza();
        dogadjaj = dogadjajBaza.save(dogadjaj);
        if(dogadjaj.getId() > 0){
            //uspesno kreiran dogadjaj
        } else {
            //poruka da nije kreiran, za sad vraca na blagajnik_novi_dogadjaj.jsp
            response.sendRedirect("blagajnik_novi_dogadjaj.jsp");
            return;
        }
        
        StrukturaUlaznica strukturaUlaznica = new StrukturaUlaznica();
        StrukturaUlaznicaBaza strukturaUlaznicaBaza = new StrukturaUlaznicaBaza();
        
        strukturaUlaznica.setId_dogadjaja(dogadjaj.getId());
        strukturaUlaznica.setKategorija(request.getParameter("nova_kategorija"));
        strukturaUlaznica.setCena(Double.parseDouble(request.getParameter("nova_kategorija_cena")));
        strukturaUlaznica.setBroj_dostupnih_ulaznica(Integer.parseInt(request.getParameter("limit_nova_kategorija")));
        
        strukturaUlaznica = strukturaUlaznicaBaza.save(strukturaUlaznica);
        if(strukturaUlaznica.getId() > 0){
            //uspesno dodata struktura
        } else {
            //poruka da nije uspesno
        }
        
        /*DogadjajBaza dogadjajBaza = new DogadjajBaza();
        dogadjaj = dogadjajBaza.save(dogadjaj);
        if(dogadjaj.getId() > 0){
            //uspesno kreiran dogadjaj
        } else {
            //poruka da nije kreiran, za sad vraca na blagajnik_novi_dogadjaj.jsp
            response.sendRedirect("blagajnik_novi_dogadjaj.jsp");
        }*/
        
        
        
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
