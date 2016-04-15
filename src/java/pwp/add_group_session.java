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
public class add_group_session extends HttpServlet {
String district_name, partner_name,health_facility_name;
HttpSession session;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        session=request.getSession();
       dbConn conn= new dbConn();
       
String district=request.getParameter("district");
String health_facility=request.getParameter("health_facility");
String partner=request.getParameter("partner");
String total_groups=request.getParameter("no_of_groups");
System.out.println("District id : "+district);
System.out.println("Partner id : "+partner);
System.out.println("health id : "+health_facility);

session.setAttribute("no_of_groups", total_groups);
session.setAttribute("district_id", district);
session.setAttribute("partner_id", partner);
session.setAttribute("health_facility_id", health_facility);


String district_selector="SELECT district_name FROM district WHERE district_id='"+district+"'";
conn.rs=conn.st.executeQuery(district_selector);
conn.rs.next();
district_name=conn.rs.getString(1);

String partner_selector="SELECT partner_name FROM partner WHERE partner_id='"+partner+"'";
conn.rs=conn.st.executeQuery(partner_selector);
conn.rs.next();
partner_name=conn.rs.getString(1);

String nhf_selector="SELECT nhf_name FROM health_facility WHERE nhf_id='"+health_facility+"'";
conn.rs=conn.st.executeQuery(nhf_selector);
conn.rs.next();
health_facility_name=conn.rs.getString(1);


session.setAttribute("district_name", district_name);
session.setAttribute("partner_name", partner_name);
session.setAttribute("health_facility_name", health_facility_name);

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


response.sendRedirect("groups_maker");

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
            Logger.getLogger(add_group_session.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(add_group_session.class.getName()).log(Level.SEVERE, null, ex);
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
