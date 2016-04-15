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
public class get_providers extends HttpServlet {
String partner,district,group;
String providers="",fname,mname,lname,fullname;
int found=0;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
         dbConn conn = new dbConn();
//         System.out.println("entered get providersss");
         partner=request.getParameter("partner");
         district=request.getParameter("district");
         group=","+request.getParameter("group")+",";
         found=0;
         
         providers="<option value=\"\">Choose Provider</option>";
         System.out.println("partner  : "+partner+"   district  :  "+district+"     group   :  "+group);
            
          String provider_selector="SELECT * FROM service_provider WHERE partner_id='"+partner+"' && district_id='"+district+"'";
          conn.rs=conn.st.executeQuery(provider_selector);
          while(conn.rs.next()){
              fname=mname=lname=fullname="";
            String groups=conn.rs.getString("group_ids");
            
            if(groups.contains(group)){
            found++;  
            fname=conn.rs.getString(2);
            mname=conn.rs.getString(3);
            lname=conn.rs.getString(4);
            if(mname.equals(lname)){
                fullname=  fname+" "+lname;
            }
          
           if(!mname.equals(lname)){
          fullname=  fname+" "+mname+" "+lname;     
            } 
            providers+="<option value=\""+conn.rs.getString(1)+"\">"+fullname+"</option>";    
                
            }
          }
            
          System.out.println(providers);  
        if(conn.rs!=null){conn.rs.close();}
if(conn.st!=null){conn.st.close();}
if(conn.rs1!=null){conn.rs1.close();}
if(conn.st1!=null){conn.st1.close();}
if(conn.rs2!=null){conn.rs2.close();}
if(conn.st2!=null){conn.st2.close();}
if(conn.st3!=null){conn.st3.close();}
if(conn.pst!=null){conn.pst.close();}
if(conn.pst!=null){conn.pst.close();}
if(conn.pst1!=null){conn.pst1.close();}
if(conn.pst1!=null){conn.pst1.close();}
if(conn.conn!=null){conn.conn.close();}
            /* TODO output your page here. You may use following sample code. */
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet get_providers</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet get_providers at " +providers+ "</h1>");
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
            Logger.getLogger(get_providers.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(get_providers.class.getName()).log(Level.SEVERE, null, ex);
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
