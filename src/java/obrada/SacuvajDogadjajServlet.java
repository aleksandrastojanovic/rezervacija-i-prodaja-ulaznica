/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package obrada;

import java.io.IOException;
import java.time.LocalDateTime;
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
        HttpSession sesija = request.getSession();
        if(sesija.getAttribute("korisnik_id") == null || (int)sesija.getAttribute("korisnik_id") < 0){
            response.sendRedirect("proveraPrijavljen");
            return;
        }
        if(sesija.getAttribute("tip").equals(Korisnik.TIP_BLAGAJNIK)){
            
        RequestDispatcher rd = request.getRequestDispatcher("prijavljenBlagajnik");

        BlagajnikBaza blagajnikBaza = new BlagajnikBaza();
        Blagajnik blagajnik = blagajnikBaza.find((int) sesija.getAttribute("korisnik_id"));
        
        DogadjajBaza dogadjajBaza = new DogadjajBaza();
        Dogadjaj dogadjaj = new Dogadjaj();
        boolean noviDogadjaj = request.getParameter("dogadjaj_id") == null || Integer.parseInt(request.getParameter("dogadjaj_id")) <= 0;
        if (!noviDogadjaj) {
            dogadjaj = dogadjajBaza.find(Integer.parseInt(request.getParameter("dogadjaj_id")));            
        }

        dogadjaj.setNaziv(request.getParameter("naziv"));
        dogadjaj.setNazivLokacije(blagajnik.getNazivLokacije());
        dogadjaj.setDatumIVreme(LocalDateTime.parse(request.getParameter("vreme_odrzavanja")));
        dogadjaj.setDetalji(request.getParameter("detalji"));
        /*
        Nisam sigurna kako funkcionise, da li ovako prima putanju ili ne, mada 
        se ne buni, znaci String je.*/
        // Za sad ignorisi fotke to cemo kasnije
        dogadjaj.setGlavnaSlikaPutanja(request.getParameter("slika_glavna"));
        dogadjaj.setVideoPutanja(request.getParameter("video"));

        dogadjaj = dogadjajBaza.save(dogadjaj);
        if (dogadjaj.getId() > 0) {
            //uspesno kreiran dogadjaj
        } else {
            //poruka da nije kreiran, za sad vraca na blagajnik_novi_dogadjaj.jsp
            response.sendRedirect("noviDogadjaj");
            return;
        }
        
        if(noviDogadjaj){
        StrukturaUlaznica strukturaUlaznica = new StrukturaUlaznica();
        StrukturaUlaznicaBaza strukturaUlaznicaBaza = new StrukturaUlaznicaBaza();

        strukturaUlaznica.setIdDogadjaja(dogadjaj.getId());
        strukturaUlaznica.setKategorija(request.getParameter("nova_kategorija"));
        strukturaUlaznica.setCena(Double.parseDouble(request.getParameter("nova_kategorija_cena")));
        strukturaUlaznica.setBrojDostupnihUlaznica(Integer.parseInt(request.getParameter("limit_nova_kategorija")));
        strukturaUlaznica.setPreostaloUlaznica(strukturaUlaznica.getBrojDostupnihUlaznica());
        strukturaUlaznica.setGranicaPoKorisniku(Integer.parseInt(request.getParameter("granica_po_korisniku")));

        strukturaUlaznica = strukturaUlaznicaBaza.save(strukturaUlaznica);
        if (strukturaUlaznica.getId() > 0) {
            //uspesno dodata struktura
        } else {
            //poruka da nije uspesno
        }}
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
