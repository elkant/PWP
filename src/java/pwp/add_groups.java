/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pwp;

import java.io.IOException;
import java.io.PrintWriter;
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
public class add_groups extends HttpServlet {
HttpSession session;
String partner_id,district_id,health_facility_id;
int found=0;
String groups_inserted,groups_fail;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
session=request.getSession();
dbConn conn = new dbConn();

if(session.getAttribute("no_of_groups")!=null){
     int total_groups=Integer.parseInt(session.getAttribute("no_of_groups").toString());
     partner_id=session.getAttribute("partner_id").toString();
     district_id= session.getAttribute("district_id").toString();
     health_facility_id=session.getAttribute("health_facility_id").toString();
     System.out.println("Total grops are  :  "+total_groups);
     found=0;
     groups_inserted=groups_fail="";
     int i=1;
     while(i<=total_groups){
     String group_name=request.getParameter("group"+i);
     String location=request.getParameter("location"+i);
     String year_formed=request.getParameter("year_formed"+i);
     
     
     
      if(group_name!=null && !group_name.equals("")){
          group_name=group_name.toUpperCase();
         location=location.toUpperCase();
         IdGenerator ig = new IdGenerator();
         
         
       
         String group_checker="SELECT COUNT(group_id) FROM groups WHERE group_name='"+group_name+"' && partner_id='"+partner_id+"' && district_id='"+district_id+"' "
         + "";
//          + "nhf_id='"+health_facility_id+"' && location='"+location+"' && year_formed='"+year_formed+"'";
          conn.rs=conn.st.executeQuery(group_checker);
          if(conn.rs.next()==true){
            found=conn.rs.getInt(1);     
          }
       if(found==0){
           
                String group_inserter="INSERT INTO groups SET group_id='"+ig.current_id()+"', group_name='"+group_name+"', partner_id='"+partner_id+"', district_id='"+district_id+"',"
         + "nhf_id='"+health_facility_id+"', location='"+location+"',year_formed='"+year_formed+"',timestamp='"+ig.toDay()+"'";
         System.out.println(group_inserter);
                conn.st.executeUpdate(group_inserter);    
         groups_inserted+=group_name+",";  
       }   
       else{
           groups_fail+=group_name+", ";
       }
          
          found=0;
      }   
      i++;   
     }
if(!groups_inserted.equals("")){
     session.setAttribute("group_success", "<font color=\"green\">"+groups_inserted+" Groups Registered Successfully.</font>");
}if(!groups_fail.equals("")){
     session.setAttribute("group_fail", "<font color=\"red\">"+groups_fail+" Groups Already Exist hence NOT Registered.</font>");
}
if(conn.rs!=null){
            conn.rs.close();
            }
if(conn.st!=null){
            conn.st.close();
            }
System.out.println("health facility : "+health_facility_id);
          System.out.println("District : "+district_id);
          System.out.println("Partner : "+partner_id);
}
else{
 session.setAttribute("group_fail", "<font color=\"red\">NO Group was Registered. <b>Log Out</b> and Then Log in To Try Again.</font>");   
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

     response.sendRedirect("add_groups.jsp");
  System.out.println("SESSIONS ADDED ARE  :  "+groups_inserted);
    System.out.println("SESSIONS FAIL ARE  :  "+groups_fail);
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
            Logger.getLogger(add_groups.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(add_groups.class.getName()).log(Level.SEVERE, null, ex);
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
