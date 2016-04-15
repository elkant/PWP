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
//import sun.org.mozilla.javascript.internal.regexp.SubString;

/**
 *
 * @author Geofrey Nyabuto
 */
public class load_provider_details extends HttpServlet {
HttpSession session;
String provider_id;
String district_dets,county_dets,group_details,fname,mname,lname,phone,partner_details;
String district_id,county_id,partner_id;
String groups_id="", group_ids=""; 
String tracker="";
int found,j,k;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
       
        session=request.getSession();
        dbConn conn = new dbConn();
      provider_id=request.getParameter("provider");
      
     tracker=","; 
     found=j=k=0;
     district_dets=county_dets=group_details=fname=mname=lname=phone=partner_details="";
      String select_provider_details="SELECT * FROM service_provider WHERE provider_id='"+provider_id+"'";
      conn.rs=conn.st.executeQuery(select_provider_details);
      conn.rs.next();
      fname=conn.rs.getString("fname");
      mname=conn.rs.getString("mname");
      lname=conn.rs.getString("lname");
      phone=conn.rs.getString("phone");
      session.setAttribute("provider_id", conn.rs.getString("provider_id"));
      if(lname.equals(mname)){
          mname="";
      }
     groups_id=conn.rs.getString("group_ids");
     
     partner_id=conn.rs.getString("partner_id");
     
     district_id=conn.rs.getString("district_id");
     
     String county_dets_selector="SELECT * FROM  district WHERE district_id='"+district_id+"'";
     conn.rs=conn.st.executeQuery(county_dets_selector);
     conn.rs.next();
     county_id=conn.rs.getString("county_id");
     
     
     
//     ^^^^^^^^^^^^^^^ARRANGE FOR THE DISPLAY^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
     county_dets="";
 String select_county="SELECT * FROM county" ;
 conn.rs=conn.st.executeQuery(select_county);
 while(conn.rs.next()){
     
     if(county_id.equals(conn.rs.getString(1))){
       county_dets+="<option value=\""+conn.rs.getString(1)+"\" selected>"+conn.rs.getString(2) +"</option>";  
     }
     else{
         county_dets+="<option value=\""+conn.rs.getString(1)+"\">"+conn.rs.getString(2) +"</option>"; 
     }
 }
 
 
 partner_details="";
 String select_partner="SELECT * FROM partner" ;
 conn.rs=conn.st.executeQuery(select_partner);
 while(conn.rs.next()){
     
     if(partner_id.equals(conn.rs.getString(1))){
       partner_details+="<option value=\""+conn.rs.getString(1)+"\" selected>"+conn.rs.getString(2) +"</option>";  
     }
     else{
         partner_details+="<option value=\""+conn.rs.getString(1)+"\">"+conn.rs.getString(2) +"</option>"; 
     }
 }
 
 
 
 district_dets="";
 String district_seletor="SELECT * FROM district WHERE county_id='"+county_id+"'";
 conn.rs=conn.st.executeQuery(district_seletor);
 while(conn.rs.next()){
     
    if(district_id.equals(conn.rs.getString(1))){
       district_dets+="<option value=\""+conn.rs.getString(1)+"\" selected>"+conn.rs.getString(3) +"</option>";  
     }
     else{
         district_dets+="<option value=\""+conn.rs.getString(1)+"\">"+conn.rs.getString(3) +"</option>"; 
     } 
     
 }
   
// %%%%%%%%%%%%GROUP DETAILS SELECTOR%%%%%%%%%%%%%%%%%%%%%%%%%%%%

// int len=groups_id.length();
// groups_id=groups_id.substring(1,len);
//     String[] all_grps=groups_id.split(",");
    group_details="";  
 if(groups_id.contains(",0,")){
       group_details+="<option value=\"0\" selected=\"true\">INDIVIDUALS</option>";  
     }
 else{
    group_details+="<option value=\"0\">INDIVIDUALS</option>";  
 }
  
 System.out.println("The String has  : "+groups_id.length());
 System.out.println("the new word  : "+groups_id);

 String group="SELECT * FROM groups WHERE district_id='"+district_id+"' && partner_id='"+partner_id+"' ORDER BY group_name";
 conn.rs=conn.st.executeQuery(group);
 while(conn.rs.next()){
 if(groups_id.contains(conn.rs.getString(1))){
       group_details+="<option value=\""+conn.rs.getString(1)+"\" selected=\"true\">"+conn.rs.getString(2) +"</option>";    
     }
     else{
       group_details+="<option value=\""+conn.rs.getString(1)+"\" >"+conn.rs.getString(2) +"</option>";    
     }      
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
  System.out.println("tracker  :  "+tracker); 
 session.setAttribute("fname",fname);
 session.setAttribute("mname",mname);
 session.setAttribute("lname",lname);
 session.setAttribute("phone",phone);
 session.setAttribute("group_details", group_details);
 session.setAttribute("county_dets", county_dets);
 session.setAttribute("partner_details", partner_details);
 session.setAttribute("district_dets", district_dets);
 tracker="";
 System.out.println(" "+group_details);
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
            Logger.getLogger(load_provider_details.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(load_provider_details.class.getName()).log(Level.SEVERE, null, ex);
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
