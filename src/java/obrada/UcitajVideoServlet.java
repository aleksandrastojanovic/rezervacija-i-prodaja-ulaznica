/*
- ucitava video fajlove
 */
package obrada;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author iq skola
 */
public class UcitajVideoServlet extends HttpServlet {

    private final String putanjaFoldera = "E:/ProjekatMedia/";

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
        response.setContentType("video/*");
        File fajl = napraviFajl(request);
        //proverim da li je tip video i ispravim sta ako treba
        try (BufferedInputStream bin = new BufferedInputStream(new FileInputStream(fajl));
                ServletOutputStream out = response.getOutputStream();
                BufferedOutputStream bout = new BufferedOutputStream(out)) {
            int ch;
            while ((ch = bin.read()) != -1) {
                bout.write(ch);
            }
        } catch (Exception ex) {
            Logger.getLogger(UcitajFotoServlet.class.getName()).log(Level.SEVERE, null, ex);
            response.sendRedirect("error.jsp");
        }
    }

    private File napraviFajl(HttpServletRequest request) {
        String ime = request.getParameter("ime");
        String dogadjajId = request.getParameter("dogadjaj_id");
        File fajl = new File(putanjaFoldera + dogadjajId + "/" + ime);
        if (!fajl.isFile()) {
            fajl = new File(putanjaFoldera + "__praznaSlika__.jpg");
        }
        return fajl;
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
