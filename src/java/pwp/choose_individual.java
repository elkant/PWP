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
public class choose_individual extends HttpServlet {
HttpSession session;
String clients;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
       
            dbConn conn = new dbConn();
            session=request.getSession();
          clients="<option value=\"\">Choose Clients</option>" .toString();
        String district_id=session.getAttribute("district_id").toString();
         String partner_id=session.getAttribute("partner_id").toString();
         String yr=session.getAttribute("year").toString();
         
         String clients_selector="SELECT * FROM clients WHERE group_id='0' && partner_id='"+partner_id+"' && district_id='"+district_id+"' && provider_id='"+session.getAttribute("provider")+"' ORDER BY fname,mname,lname";
         conn.rs=conn.st.executeQuery(clients_selector);
         while(conn.rs.next()){
                String fname,mname,lname;
    fname=conn.rs.getString(2);
     mname=conn.rs.getString(3);
     lname =conn.rs.getString(4) ;    
    if(mname.equals(lname)){
       mname="" ;
    }
           clients+="<option value=\""+conn.rs.getString("groupings")+"\">"+fname+" "+mname+" "+lname+"</option>" ; 
         }
if(conn.rs!=null){
            conn.rs.close();
            }
if(conn.st!=null){
            conn.st.close();
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

session.setAttribute("a_clt", clients);
      System.out.println(clients);  
        response.sendRedirect("choose_individual.jsp");
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
            Logger.getLogger(choose_individual.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(choose_individual.class.getName()).log(Level.SEVERE, null, ex);
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
