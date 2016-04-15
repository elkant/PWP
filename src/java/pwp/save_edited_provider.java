/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pwp;

import java.io.IOException;
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
public class save_edited_provider extends HttpServlet {
HttpSession session;
String district_id,partner_id,fname,mname,lname,phone,groups,provider_id;
String [] grp_ids;
String full_name="",full_name2="";
int found=0;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
       session=request.getSession();
       dbConn conn = new dbConn();
       IdGenerator IG = new IdGenerator();
       if(session.getAttribute("provider_id")!=null){
       fname=request.getParameter("fname");
       mname=request.getParameter("mname");
       lname=request.getParameter("lname");
       phone=request.getParameter("phone");
       found=0;
       district_id=request.getParameter("district");
       partner_id=request.getParameter("partner");
       
      grp_ids=request.getParameterValues("groups");
      provider_id=session.getAttribute("provider_id").toString();
      
      groups=",";
      for (int i=0;i<grp_ids.length; i++){
        if(!"".equals(grp_ids[i]) || grp_ids[i]!=null){
         groups+=grp_ids[i]+",";   
            
        }  
      }
//      &&&&&&&&&&&REMOVE TRAILING WHITESPACE&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&
      fname=fname.replaceAll("\\s+","");
      lname=lname.replaceAll("\\s+","");
      mname=mname.replaceAll("\\s+","");
      if(mname.equals("") || mname==null){
          mname=lname;
      }
      fname=fname.toUpperCase();
      mname=mname.toUpperCase();
      lname=lname.toUpperCase();
      
       full_name=fname+" "+mname+" "+lname;
      groups=groups.replace(",,", ",");
      System.out.println("provider id is  :  "+groups);
      String check_existence="SELECT count(provider_id),fname,mname,lname FROM service_provider WHERE phone='"+phone+"' && provider_id!='"+provider_id+"' GROUP BY phone";
conn.rs=conn.st.executeQuery(check_existence);
if(conn.rs.next()==true){
    found=conn.rs.getInt(1);
    full_name2=conn.rs.getString(2)+" "+conn.rs.getString(3)+" "+conn.rs.getString(4);
}
if(found==0){
      String update_provider_details="UPDATE service_provider SET fname='"+fname+"',mname='"+mname+"',lname='"+lname+"',"
              + "phone='"+phone+"', group_ids='"+groups+"',partner_id='"+partner_id+"',timestamp='"+IG.toDay()+"',district_id='"+district_id+"'"
              + "WHERE provider_id='"+provider_id+"'";
      conn.st.executeUpdate(update_provider_details);
 session.setAttribute("edit_provider", "<font color=\"black\">"+full_name+" </font><font color=\"green\">Details updated successfully.</font>");      
}
if(found>0){
  session.setAttribute("edit_provider", "<font color=\"black\">"+full_name+"</font> <font color=\"red\">Service Provider Details were not updated. The Phone Number</font><font color=\"black\"> "+phone+"</font><font color=\"red\"> is alredy assigned to<font color=\"black\"> "+full_name2+"</font>");   
}
       }
     
       else{
           session.setAttribute("edit_provider", "<font color=\"red\">Updating "+full_name+" Service Provider Details was not updated. Log Out and try again.</font>");
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

    response.sendRedirect("edit_provider.jsp");   
       
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
            Logger.getLogger(save_edited_provider.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(save_edited_provider.class.getName()).log(Level.SEVERE, null, ex);
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
