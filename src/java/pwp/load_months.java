/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pwp;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Geofrey Nyabuto
 */
public class load_months extends HttpServlet {
String specific_months="";
String period="";
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
          specific_months="<option value=\"\">Choose Month</option>";  
   period=request.getParameter("period");
   if(period.equals("1")){
     specific_months+= "<option value=\"10\">October</option><option value=\"11\">November</option><option value=\"12\">December</option>" ;
   }
    if(period.equals("2")){
     specific_months+= "<option value=\"1\">January</option><option value=\"2\">February</option><option value=\"3\">March</option>" ;
   }
     if(period.equals("3")){
     specific_months+= "<option value=\"4\">April</option><option value=\"5\">May</option><option value=\"6\">June</option>" ;
   }
      if(period.equals("4")){
     specific_months+= "<option value=\"7\">July</option><option value=\"8\">August</option><option value=\"9\">September</option>" ;
   }
            
            /* TODO output your page here. You may use following sample code. */
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet load_months</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet load_months at " +specific_months+ "</h1>");
            out.println("</body>");
            out.println("</html>");
        } finally {            
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
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
     * Handles the HTTP
     * <code>POST</code> method.
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
