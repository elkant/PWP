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
public class edit_nhf extends HttpServlet {
HttpSession session;
String district_id, nhf_name;
int found=0;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
            session=request.getSession();
            dbConn conn= new dbConn();
            IdGenerator IG = new IdGenerator();
            district_id= request.getParameter("district");
            nhf_name=request.getParameter("health_facility");
            if(session.getAttribute("healthy_facility")!=null){
             found=0;
             nhf_name=nhf_name.toUpperCase();
                String nhf_id=session.getAttribute("healthy_facility").toString();
                String check_existence="SELECT count(nhf_id) FROM health_facility WHERE nhf_name='"+nhf_name+"' && district_id='"+district_id+"' && nhf_id!='"+nhf_id+"'";
                conn.rs=conn.st.executeQuery(check_existence);
                if(conn.rs.next()==true){
                found=conn.rs.getInt(1);    
                }
                if(found==0){
                String nhf_updator="UPDATE health_facility SET nhf_name='"+nhf_name+"' , district_id='"+district_id+"',timestamp='"+IG.toDay()+"' WHERE nhf_id='"+nhf_id+"'";
                conn.st.executeUpdate(nhf_updator);
                session.setAttribute("edited_successfully", "<font color=\"green\">"+nhf_name+" Details Saved Successfully.</font>");
                System.out.println("here added as new");
                }
                if(found==1){
                 session.setAttribute("edited_successfully", "<font color=\"red\">"+nhf_name+" Details already exist hence they were <B>NOT SAVED</B>.</font>");    
               System.out.println("Here where exist.");
                }
                
            }
            else{
            session.setAttribute("edited_successfully", "<font color=\"red\">Failed to edit Healthy Facility details. <b>Log out</b> and tyr logging in again.</font>");     
            }
            found=0;
if(conn.rs!=null){
            conn.rs.close();
            }
if(conn.st!=null){
            conn.st.close();
            }
    response.sendRedirect("edit_nhf.jsp");
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
            Logger.getLogger(edit_nhf.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(edit_nhf.class.getName()).log(Level.SEVERE, null, ex);
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
