/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pwp;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Random;
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
public class add_nhfs extends HttpServlet {
HttpSession session;
int total_nhfs,found;
String all_nhfs,nhf_name,district_id;
String already_exist, added;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {

        session=request.getSession();
        dbConn conn = new dbConn();
             
       
found=0;
        nhf_name="";
        already_exist=added="";
        if(session.getAttribute("district_id")!=null && session.getAttribute("total_nhfs")!=null){
            district_id=session.getAttribute("district_id").toString();
            all_nhfs=session.getAttribute("total_nhfs").toString();
            total_nhfs=Integer.parseInt(all_nhfs);
            for ( int i=0; i<=total_nhfs; i++){
IdGenerator ig = new IdGenerator();

        nhf_name=request.getParameter("nhf_name"+i);  
              System.out.println("name isss :    "+nhf_name);
              if(!"".equals(nhf_name) && nhf_name!=null){
              System.out.println("name isss 2   :    "+nhf_name);
              nhf_name=nhf_name.toUpperCase();
                  String check_existence="SELECT COUNT(nhf_id) FROM health_facility WHERE nhf_name='"+nhf_name+"' && district_id='"+district_id+"' ";
                  conn.rs=conn.st.executeQuery(check_existence);
                if(conn.rs.next()==true){
                  found=conn.rs.getInt(1);      
                }
                 
                if(found==0){
                    String add_facility="INSERT INTO health_facility (nhf_id,nhf_name,district_id,timestamp) VALUES('"+ig.current_id()+"','"+nhf_name+"','"+district_id+"','"+ig.toDay()+"')";
                    
                    conn.st.executeUpdate(add_facility);   
               added+=nhf_name+",";
                }
                 if(found>0){
                   already_exist+=nhf_name+",";  
                 }
                
                      } 
              found=0;
              nhf_name="";
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

if(!added.equals("")){
  session.setAttribute("added_nhf","<font color=\"green\">"+ added+" Healthy Facility  Added Successfully</ font>");
} if(!already_exist.equals("")){
  session.setAttribute("already_exist_nhf", "<font color=\"red\">"+already_exist+" Healthy Facility Already Exist</ font>");
}
        }
        else{
         session.setAttribute("already_exist_nhf", "<font color=\"red\">The healthy facilities were <b>not added</b>. Please Log out and try again</ font>");    
         }
  response.sendRedirect("add_nhf1.jsp");
   
    
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
            Logger.getLogger(add_nhfs.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(add_nhfs.class.getName()).log(Level.SEVERE, null, ex);
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
