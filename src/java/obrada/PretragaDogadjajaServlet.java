/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package obrada;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
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
public class PretragaDogadjajaServlet extends HttpServlet {

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
        -Omogucava pretragu dogadjaja po nazivu
        -Omogucava pretragu dogadjaja po vremenskom intervalu
        -Omogucava pretragu dogadjaja po mestu odrzavanja
        -U slucaju da je u pitanju registrovani korisnik, omogucava preusmeravanje na rezervaciju(pojedinacni dogadjaj
         */

        RequestDispatcher rd = request.getRequestDispatcher("rezultat_pretrage.jsp");

        DogadjajBaza dogadjajBaza = new DogadjajBaza();
        ArrayList<Dogadjaj> sviDogadjaji = dogadjajBaza.all();
        ArrayList<Dogadjaj> dogadjajiSviFilteri = new ArrayList<>();
        boolean prosaoSveFiltere = true;

        String naziv = request.getParameter("naziv").toLowerCase();
        naziv = naziv.trim();
        LocalDateTime vreme_od = LocalDateTime.parse(request.getParameter("vreme_od"));
        LocalDateTime vreme_do = LocalDateTime.parse(request.getParameter("vreme_do"));
        String mesto = request.getParameter("mesto").toLowerCase();
        mesto = mesto.trim();

        for (Dogadjaj dogadjaj : sviDogadjaji) {

            if (prosaoSveFiltere && naziv != null) {
                prosaoSveFiltere = dogadjaj.getNaziv().toLowerCase().contains(naziv);
            }
            if (prosaoSveFiltere && !LocalDateTime.of(2001, Month.JANUARY, 1, 0, 0).equals(vreme_od)) {
                prosaoSveFiltere = vreme_od.isAfter(LocalDateTime.now())
                        && (dogadjaj.getDatum_i_vreme().isAfter(vreme_od)
                        || dogadjaj.getDatum_i_vreme().isEqual(vreme_od));
            }
            if (prosaoSveFiltere && !LocalDateTime.of(2001, Month.JANUARY, 1, 0, 0).equals(vreme_do)) {

                prosaoSveFiltere = vreme_do.isAfter(LocalDateTime.now())
                        && (dogadjaj.getDatum_i_vreme().isBefore(vreme_do)
                        || dogadjaj.getDatum_i_vreme().isEqual(vreme_do));
            }
            if (prosaoSveFiltere && mesto != null) {
                prosaoSveFiltere = dogadjaj.getNaziv_lokacije().toLowerCase().contains(mesto);
            }

            if (prosaoSveFiltere) {
                dogadjajiSviFilteri.add(dogadjaj);
            }
        }

        request.setAttribute("dogadjaji", dogadjajiSviFilteri);
        rd.forward(request, response);

        //if(registrovan){
        //response.sendRedirect("proveraPrijavljen");
        //return;
        // }
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
