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
public class load_edit_nhf extends HttpServlet {
HttpSession session;
String district,county,nhf;
String district_det,county_det,healthy_facility,nhf_name;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
 session=request.getSession();
 dbConn conn = new dbConn();
 district=request.getParameter("district");
 county=request.getParameter("county");
 nhf=request.getParameter("nhf");
 
 
// System.out.println("district "+district+"  county  :"+county+"   nhf  :  "+nhf);
 county_det="";
 String county_selector="SELECT * FROM county";
 conn.rs=conn.st.executeQuery(county_selector);
 while(conn.rs.next()){
     if(conn.rs.getString(1).equals(county)){
  county_det+="<option value=\""+conn.rs.getString(1)+"\" selected> "+conn.rs.getString(2)+"</option>";   
     }
     else{
      county_det+="<option value=\""+conn.rs.getString(1)+"\" > "+conn.rs.getString(2)+"</option>";    
     }
 }
 
 district_det="";
 String district_selector="SELECT * FROM district WHERE county_id='"+county+"'";
 conn.rs=conn.st.executeQuery(district_selector);
 while(conn.rs.next()){
     if(conn.rs.getString(1).equals(district)){
  district_det+="<option value=\""+conn.rs.getString(1)+"\" selected> "+conn.rs.getString(3)+"</option>";   
     }
     else{
      district_det+="<option value=\""+conn.rs.getString(1)+"\" > "+conn.rs.getString(3)+"</option>";    
     }
 }
 
 healthy_facility="";
 String nhf_selector="SELECT * FROM health_facility WHERE nhf_id='"+nhf+"'";
 conn.rs=conn.st.executeQuery(nhf_selector);
 conn.rs.next();
 nhf_name="<input type=\"text\" name=\"health_facility\" class=\"textbox\" value=\""+conn.rs.getString(2) +"\">";  
     
 
 
 
// &&&&&&&&&&&&&&&&&&&SET SESSIONS
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

 session.setAttribute("county_det", county_det);
 session.setAttribute("district_det", district_det);
 session.setAttribute("healthy_facility", nhf);
 session.setAttribute("healthy_facility_name", nhf_name);
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
            Logger.getLogger(load_edit_nhf.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(load_edit_nhf.class.getName()).log(Level.SEVERE, null, ex);
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
