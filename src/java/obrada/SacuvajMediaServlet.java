/*
- vrsi cuvanje foto/video u bazu, i prosledjuje dalje blagajnika
 */
package obrada;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import klase.*;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 *
 * @author iq skola
 */
public class SacuvajMediaServlet extends HttpServlet {

    private final MediaBaza mediaBaza = new MediaBaza();
    private final String putanjaFoldera = "E:/ProjekatMedia/";
    private final int maksimalanBrojMedia = 12;

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
        try {
            if (!ProvereKorisnik.postojiPrijavljenKorisnik(request)) {
                String poruka = "Morate biti prijavljeni";
                RequestDispatcher rd1 = request.getRequestDispatcher("prijava.jsp");
                request.setAttribute("poruka", poruka);
                rd1.forward(request, response);
                return;
            }

            if (!ServletFileUpload.isMultipartContent(request)) {
                // poruka: greska pri snimanju
                // da ga salje na pravljenje novog dogadjaja ispocetka?
                String poruka = "Greska pri snimanju fajla";
                RequestDispatcher rd1 = request.getRequestDispatcher("error.jsp");
                request.setAttribute("poruka", poruka);
                rd1.forward(request, response);
                return;
            }
            int dogadjajId = Integer.parseInt(request.getParameter("dogadjaj_id"));
            String putanjaZaDogadjaj = putanjaFoldera + dogadjajId + "/";
            int brojacMedia = 0;
            try {
                List<FileItem> fileItems = ucitaj(request);
                
                for (FileItem fileItem : fileItems) {
                    if (!fileItem.isFormField()) {
                        if (brojacMedia++ >= maksimalanBrojMedia) {
                            break;
                        }
                        Media media = napraviMedia(fileItem, putanjaZaDogadjaj, dogadjajId, brojacMedia);
                        media = mediaBaza.save(media);
                        if (media.getId() < 0) {
                            String poruka = "Greska pri snimanju fajla.";
                            RequestDispatcher rd1 = request.getRequestDispatcher("error.jsp");
                            request.setAttribute("poruka", poruka);
                            rd1.forward(request, response);
                            return;
                            // greska, nije snimio fajl
                            // ali imamo ih vise, sta ako ne snimi jedan samo?
                        }
                    }
                }
            } catch (Exception ex) {
                System.out.println(ex);
                // da ga salje na pravljenje novog dogadjaja ispocetka?
                response.sendRedirect("error.jsp");
            }

            RequestDispatcher rd = request.getRequestDispatcher("novaStruktura?dogadjaj_id=" + dogadjajId);
            String porukaUspesno = "Uspesno sacuvano " + brojacMedia + " fajlova.";
            request.setAttribute("porukaUspesno", porukaUspesno);
            rd.forward(request, response);
        } catch (Exception ex) {
            Logger.getLogger(SacuvajMediaServlet.class.getName()).log(Level.SEVERE, null, ex);
            response.sendRedirect("error.jsp");
        }
    }

    private Media napraviMedia(FileItem fajl, String putanjaZaDogadjaj, int dogadjajId, int brojacMedia) {

        boolean glavna = "glavna".equals(fajl.getFieldName());
        String tip = "video".equals(fajl.getFieldName()) ? Media.TIP_VIDEO : Media.TIP_FOTOGRAFIJA;
        String ime = "slika_" + brojacMedia;
        if (Media.TIP_VIDEO.equals(tip)) {
            ime = "video";
        } else if (glavna) {
            ime = "glavna";
        }
        ime = ime + fajl.getName().substring(fajl.getName().lastIndexOf("."));
        return new Media(fajl, glavna, tip, putanjaZaDogadjaj + ime, dogadjajId, ime);

    }

    private List<FileItem> ucitaj(HttpServletRequest request) throws FileUploadException {
        DiskFileItemFactory factory = new DiskFileItemFactory();
        factory.setSizeThreshold(4 * 1024 * 1024);
        factory.setRepository(new File(putanjaFoldera + "__temp"));
        ServletFileUpload upload = new ServletFileUpload(factory);
        upload.setSizeMax(50 * 1024 * 1024);
        return upload.parseRequest(request);
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
