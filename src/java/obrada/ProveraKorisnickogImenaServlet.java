/*
-proverava da li uneto korisnicko ime vec postoji u bazi prilikom registracije
i pravljenja novog korisnika(admin)
 */
package obrada;

import java.io.IOException;
import java.io.PrintWriter;
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
public class ProveraKorisnickogImenaServlet extends HttpServlet {
    
    RegistrovaniKorisnikBaza registrovaniKorisnikBaza = new RegistrovaniKorisnikBaza();

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
        PrintWriter out = response.getWriter();
        String strStatus = "Korisnicko ime je dostupno.";
        try {
        System.out.println("Provera korisnickog imena");
        ArrayList<RegistrovaniKorisnik> korisnici = registrovaniKorisnikBaza.all();
        for (RegistrovaniKorisnik korisnik : korisnici){
            if(korisnik.getKorisnickoIme().equals(request.getParameter("korisnickoIme"))){
                strStatus = "Korisnicko ime postoji";
                out.println(strStatus);
                return;
            } else if(request.getParameter("korisnickoIme").isEmpty() || request.getParameter("korisnickoIme").equals("")){
                strStatus = "";
            }
            else {
                strStatus = "Korisnicko ime je dostupno.";
            }
        } 
        }catch(Exception e){
            strStatus = "Doslo je do greske. Molimo pokusajte ponovo."; // Return Err Msg
            e.printStackTrace();
        }
        out.println(strStatus);        
        
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
