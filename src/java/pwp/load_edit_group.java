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
public class load_edit_group extends HttpServlet {
HttpSession session;
String group_id,group_name,county_name,partner_name,nhf_name;
String partner_id,district_id,county_id,nhf_id,district_name;
String county_details,district_details,healthy_facility_details, group_det,partner_dets;
String location,year;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
     session=request.getSession();
     dbConn conn = new dbConn();
     
     
     county_details=district_details=healthy_facility_details=partner_dets=group_det="";
     group_id=request.getParameter("group_id");
     
     session.setAttribute("grp_id", group_id);
     
     String get_group_detail="SELECT * FROM groups WHERE group_id='"+group_id+"'";
     conn.rs=conn.st.executeQuery(get_group_detail);
     conn.rs.next();
      
     group_name=conn.rs.getString(2);
     group_det="<input type=\"text\" name=\"group_name\" id=\"group_name\" value=\""+group_name+"\" class=\"textbox\" required>";
     location="<input type=\"text\" name=\"location\" id=\"location\" value=\""+conn.rs.getString("location")+"\" class=\"textbox\" required>";
     year="<input type=\"text\" name=\"year\" id=\"year\" value=\""+conn.rs.getString("year_formed")+"\" onkeypress=\"return numbers(event)\"  maxlength=\"4\" class=\"textbox\" required>";
     district_id=conn.rs.getString("district_id") ;
     partner_id=conn.rs.getString("partner_id") ;
     nhf_id=conn.rs.getString("nhf_id") ;
     conn.rs.close();
     String district_name_selector="SELECT * FROM district WHERE district_id='"+district_id+"'";
     conn.rs=conn.st.executeQuery(district_name_selector);
     conn.rs.next();
     
     
     district_name=conn.rs.getString("district_name") ;
     
     county_id=conn.rs.getString("county_id") ;
     
     String nhf_name_selector="SELECT * FROM health_facility WHERE hf_id='"+nhf_id+"'";
     conn.rs=conn.st.executeQuery(nhf_name_selector);
     conn.rs.next();
      
     nhf_name=conn.rs.getString("hf_name") ;
     
    String county_name_selector="SELECT * FROM county WHERE county_id='"+county_id+"'";
    conn.rs=conn.st.executeQuery(county_name_selector);
    conn.rs.next();
    
    county_name=conn.rs.getString("county_name");
     
    String county_det_selector="SELECT * FROM county";
    conn.rs=conn.st.executeQuery(county_det_selector);
    while(conn.rs.next()){
     if(conn.rs.getString(1).equals(county_id)){   
     county_details+="<option value=\""+conn.rs.getString(1)+"\" selected>"+conn.rs.getString(2)+"</option>";   
    }
    
     else{
      county_details+="<option value=\""+conn.rs.getString(1)+"\">"+conn.rs.getString(2)+"</option>";     
     }
    }
    
    
    String district_det_selector="SELECT * FROM district WHERE county_id='"+county_id+"'";
    conn.rs=conn.st.executeQuery(district_det_selector);
    while(conn.rs.next()){
      if(conn.rs.getString(1).equals(district_id)){   
     district_details+="<option value=\""+conn.rs.getString(1)+"\" selected>"+conn.rs.getString(3)+"</option>";   
    }
    
     else{
      district_details+="<option value=\""+conn.rs.getString(1)+"\">"+conn.rs.getString(3)+"</option>";     
     }   
        
    }
    
    String nhf_det_selector="SELECT * FROM health_facility WHERE district_id='"+district_id+"'";
    conn.rs=conn.st.executeQuery(nhf_det_selector);
    while(conn.rs.next()){
      if(conn.rs.getString(1).equals(nhf_id)){   
     healthy_facility_details+="<option value=\""+conn.rs.getString(1)+"\" selected>"+conn.rs.getString(2)+"</option>";   
    }
    
     else{
      healthy_facility_details+="<option value=\""+conn.rs.getString(1)+"\">"+conn.rs.getString(2)+"</option>";     
     }   
        
    }
    
    String partner_det_selector="SELECT * FROM partner";
    conn.rs=conn.st.executeQuery(partner_det_selector);
    while(conn.rs.next()){
      if(conn.rs.getString(1).equals(partner_id)){   
     partner_dets+="<option value=\""+conn.rs.getString(1)+"\" selected>"+conn.rs.getString(2)+"</option>";   
    }
    
     else{
      partner_dets+="<option value=\""+conn.rs.getString(1)+"\">"+conn.rs.getString(2)+"</option>";     
     }   
        
    }
    System.out.println("here");
//    <><><><><><><><><><><<><><><<SET SESSIONS <><><><><><><><><<><m><><><><><<>><>
    session.setAttribute("group_det",group_det );
    session.setAttribute("healthy_facility_details", healthy_facility_details);
    session.setAttribute("district_details", district_details);
    session.setAttribute("county_details", county_details);
    session.setAttribute("partner_dets", partner_dets);
    session.setAttribute("location", location);
    session.setAttribute("year", year);

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
            Logger.getLogger(load_edit_group.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(load_edit_group.class.getName()).log(Level.SEVERE, null, ex);
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
