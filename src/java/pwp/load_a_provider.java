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
import javax.servlet.http.HttpSession;

/**
 *
 * @author Geofrey Nyabuto
 */
public class load_a_provider extends HttpServlet {
HttpSession session;
String fname,mname,lname,partner_id,providers,district,full_name,group_id="";
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
       session=request.getSession();
       dbConn conn = new dbConn();
       
        partner_id=session.getAttribute("partner_id").toString();
           district=session.getAttribute("district_id").toString();
           group_id=session.getAttribute("group_id").toString();
           providers="";
           full_name=fname=mname=lname="";
           System.out.println("partner id is  :   "+partner_id);
           String provider_selector="SELECT * FROM service_provider WHERE partner_id='"+partner_id+"'";
           System.out.println(provider_selector);
           conn.rs=conn.st.executeQuery(provider_selector);
           while(conn.rs.next()){
               System.out.println("data : "+fname);
               String group_ids=conn.rs.getString("group_ids");
               fname=conn.rs.getString(2);
               mname=conn.rs.getString(3);
               lname=conn.rs.getString(4);
                System.out.println("data : "+fname);
               if(mname.equals(lname)){
                   mname="";
               }
             full_name=fname+" "+mname+" "+lname; 
//             if(group_ids.contains(","+group_id+",")){
           providers+="<option value=\""+conn.rs.getString(1)+"\" >"+full_name+"</option>";    
//             } 
           }
           
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

           session.setAttribute("prov", providers);
           response.sendRedirect("add_a_provider.jsp");
           System.out.println("prov  :  "+session.getAttribute("prov"));
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
            Logger.getLogger(load_a_provider.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(load_a_provider.class.getName()).log(Level.SEVERE, null, ex);
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
