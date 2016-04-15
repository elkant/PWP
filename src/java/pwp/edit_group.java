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
public class edit_group extends HttpServlet {
HttpSession session;
String group_name, group_id,district_id,partner_id,nhf_id;
int counter,counter2;
String location,year;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
            
                 session=request.getSession();
                 dbConn conn = new dbConn();
                 IdGenerator IG = new IdGenerator();
                 counter=counter2=0;
                 if(session.getAttribute("grp_id")!=null){
                 
                  group_id = session.getAttribute("grp_id").toString();
                  
                  group_name=request.getParameter("group_name");
                   district_id=request.getParameter("district");
                   partner_id=request.getParameter("partner");
                   nhf_id=request.getParameter("health_facility");
                   location=request.getParameter("location");
                   year=request.getParameter("year");
                   group_name=group_name.toUpperCase().replace("'", "");
                   location=location.toUpperCase();
                   String check_if_has_det="SELECT COUNT(client_id) FROM clients WHERE group_id='"+group_id+"' GROUP BY client_id";
                   conn.rs=conn.st.executeQuery(check_if_has_det);
                   if(conn.rs.next()==true){
                      counter=conn.rs.getInt(1); 
                   }
                   String check_if_has_det2="SELECT COUNT(group_id) FROM groups WHERE group_name='"+group_name+"' && group_id!='"+group_id+"' GROUP BY group_id";
                   conn.rs=conn.st.executeQuery(check_if_has_det2);
                   if(conn.rs.next()==true){
                      counter2=conn.rs.getInt(1); 
                   }
                   if(counter==0 && counter2==0){
                    
                       String group_updator="UPDATE groups SET group_name='"+group_name+"', partner_id='"+partner_id+"', district_id='"+district_id+"',"
                               + "nhf_id='"+nhf_id+"', location='"+location+"',year_formed='"+year+"',timestamp='"+IG.toDay()+"' WHERE group_id='"+group_id+"'";
                       conn.st.executeUpdate(group_updator);
                    session.setAttribute("edit_group", "<font color=\"green\">"+group_name+" Group Details were edited and saved successfully.</font>");   
                   }
                 else  if(counter>0 && counter2==0){
                       String names_updator="UPDATE groups SET group_name='"+group_name+"',location='"+location+"',year_formed='"+year+"' WHERE group_id='"+group_id+"'";
                        conn.st.executeUpdate(names_updator);
                  session.setAttribute("edit_group", "<font color=\"blue\">"+group_name+" Details Changed Successfully. </font>");   
                   }
                   
                   else{
                    session.setAttribute("edit_group", "<font color=\"red\">"+group_name+" Group Details cannot be edited . A group with the same name Already exist.</font>");   
                   }
                 }
                 else{
                     session.setAttribute("edit_group", "<font color=\"red\">"+group_name+" Group Details were not edited . please log out and try again.</font>");
                 }
if(conn.rs!=null){
            conn.rs.close();
            }
if(conn.st!=null){
            conn.st.close();
            }
                 response.sendRedirect("edit_group.jsp");
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
            Logger.getLogger(edit_group.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(edit_group.class.getName()).log(Level.SEVERE, null, ex);
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
