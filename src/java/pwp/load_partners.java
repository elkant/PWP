/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pwp;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Geofrey Nyabuto
 */
public class load_partners extends HttpServlet {
String partners,county_id;
String partner_ids [];
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
          dbConn conn= new dbConn();
          
          if(request.getParameter("county")!=null){
          county_id=request.getParameter("county");
          }
          else{
              county_id="";
          }
          System.out.println("countyid : "+county_id);
          partners="<option value=\"\">Choose Partner</option>";
          
          if(county_id.equals("")){
          String partner_selector="SELECT * FROM partner ORDER BY partner_name";
          conn.rs=conn.st.executeQuery(partner_selector);
          while(conn.rs.next()){
           partners+="<option value=\""+conn.rs.getString(1)+"\">"+conn.rs.getString(2)+"</option>";   
              
          }
          }
          else{
         String getpartners="SELECT partners FROM county WHERE county_id='"+county_id+"'";
         conn.rs=conn.st.executeQuery(getpartners);
         if(conn.rs.next()==true){
             partner_ids =conn.rs.getString(1).split(",");
         }
         for(String partnerid:partner_ids){
             if(!partnerid.equals("") && !partnerid.equals(",")){
                 
          String partner_selector="SELECT * FROM partner WHERE partner_id='"+partnerid+"'";
          conn.rs=conn.st.executeQuery(partner_selector);
          while(conn.rs.next()){
           partners+="<option value=\""+conn.rs.getString(1)+"\">"+conn.rs.getString(2)+"</option>";   
              
          }       
             }
         } 
         
          }
          System.out.println("partners   :  "+partners);  
          if(conn.rs!=null){
            conn.rs.close();
            }
if(conn.st!=null){
            conn.st.close();
            }
            out.println("<html>");
            out.println("<head>");
            out.println("<title></title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>" +partners+ "</h1>");
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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(load_partners.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(load_partners.class.getName()).log(Level.SEVERE, null, ex);
        }
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
