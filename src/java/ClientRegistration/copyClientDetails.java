/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ClientRegistration;

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
import pwp.IdGenerator;
import pwp.dbConn;

/**
 *
 * @author Geofrey Nyabuto
 */
public class copyClientDetails extends HttpServlet {
HttpSession session;
String found,client_id,fname,mname,lname,gender,group_id,district_id,partner_id,lessons_attended;
String year,provider_id,timestamp,id,completionyear,completionmonth;
String groupings,hf_id,status;
int added,failed;
String location,national_id,mobile_no,dob,marital_status,employment_status,education_level,under_18s,ovc_children;
String hiv_year,art_status,ccc_no,registration_date,approved_by,designation,approval_date;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        session=request.getSession();
        dbConn conn = new dbConn();
        
        added=failed=0;
//      client_id="3293108714562";
      String getAllClients="SELECT * FROM clients";
      conn.rs=conn.st.executeQuery(getAllClients);
      while(conn.rs.next()){
 found=client_id=fname=mname=lname=gender=group_id=district_id=partner_id=lessons_attended="";
 year=provider_id=timestamp=id=completionyear=completionmonth=groupings=hf_id="";
       client_id=conn.rs.getString("client_id");
       fname=conn.rs.getString("fname");
       mname=conn.rs.getString("mname");
       lname=conn.rs.getString("lname");
       gender=conn.rs.getString("gender");
       group_id=conn.rs.getString("group_id");
       groupings=conn.rs.getString("groupings");
       district_id=conn.rs.getString("district_id");
       partner_id=conn.rs.getString("partner_id");
       lessons_attended=conn.rs.getString("lessons_attended");
       year=conn.rs.getString("year");
       provider_id=conn.rs.getString("provider_id");
       timestamp=conn.rs.getString("timestamp");
       id=conn.rs.getString("id");
       completionyear=conn.rs.getString("completionyear");
       completionmonth=conn.rs.getString("completionmonth"); 
       location=national_id=mobile_no=dob=marital_status=employment_status=education_level=under_18s=ovc_children="";
       hiv_year=art_status=ccc_no=registration_date=approved_by=designation=approval_date="";
       
       
     if(!group_id.equals("0")){
       String getHF="SELECT nhf_id FROM groups WHERE group_id='"+group_id+"'";
       conn.rs1=conn.st1.executeQuery(getHF);
       if(conn.rs1.next()==true){
       hf_id=conn.rs1.getString(1);
       }
     }
     else{
       String  getHF2="SELECT nhf_id FROM no_group WHERE id='"+groupings+"'";
         conn.rs1=conn.st1.executeQuery(getHF2);
         if(conn.rs1.next()==true){
             hf_id=conn.rs1.getString(1);
         }
     }
//      System.out.println("hf id  : "+hf_id);
        String checkIfRegistered="SELECT client_id FROM personal_information WHERE client_id=?";
     conn.pst=conn.conn.prepareStatement(checkIfRegistered);
     conn.pst.setString(1, client_id);
      
      conn.rs1=conn.pst.executeQuery();
      if(conn.rs1.next()==true){
          found=conn.rs1.getString(1);
      }
      
      if(found.length()==0){
          IdGenerator IG = new IdGenerator();
          timestamp=IG.toDay();
    String add_Client="INSERT INTO personal_information "
+ "(client_id,fname,mname,lname,district_id,gender,group_id,provider_id,partner_id,hf_id,"
+ "location,national_id,mobile_no,dob,marital_status,employment_status,education_level,under_18s,ovc_children,"
+ "hiv_year,art_status,ccc_no,registration_date,approved_by,designation,"
+ "approval_date,lessons_attended,completionyear,completionmonth,timestamp,dic_id)"
+ "VALUES(?,?,?,?,?,?,?,?,?,? ,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
          conn.pst=conn.conn.prepareStatement(add_Client);
          
          conn.pst.setString(1, client_id);
          conn.pst.setString(2, fname);
          conn.pst.setString(3, mname);
          conn.pst.setString(4, lname);
          conn.pst.setString(5, district_id);
          conn.pst.setString(6, gender);
          conn.pst.setString(7, group_id);
          conn.pst.setString(8, provider_id);
          conn.pst.setString(9, partner_id);
          conn.pst.setString(10, hf_id);
          conn.pst.setString(11, "");
          conn.pst.setString(12, "");
          conn.pst.setString(13, "");
          conn.pst.setString(14, "");
          conn.pst.setString(15, "");
          conn.pst.setString(16, "");
          conn.pst.setString(17, "");
          conn.pst.setString(18, "");
          conn.pst.setString(19, "");
          conn.pst.setString(20, "");
          conn.pst.setString(21, "");
          conn.pst.setString(22, "");
          conn.pst.setString(23, "");
          conn.pst.setString(24, "");
          conn.pst.setString(25, "");
          conn.pst.setString(26, "");
          conn.pst.setString(27, lessons_attended);
          conn.pst.setString(28, completionyear);
          conn.pst.setString(29, completionmonth);
          conn.pst.setString(30, timestamp);
          conn.pst.setString(31, "");
          
          conn.pst.executeUpdate();
       
     added++;  
     System.out.println("Added at pos : "+added);
      }
      else{
       failed++;
        System.out.println("Skipped at pos : "+failed+" client id : "+client_id);
      }
       }
      
      
      String notification=" <font color=\"\">"+added+"</font> <font color=\"green\">Clients were transferred.</font> <br> <font color=\"black\">"+failed+"</font> <font color=\"red\">Clients Already Existed.</font><br>";
      System.out.println("Transfered : "+added+"  Failed : "+failed);
      session.setAttribute("MovedData", notification);
      
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


      response.sendRedirect("copyClientDetails.jsp");
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
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
        Logger.getLogger(copyClientDetails.class.getName()).log(Level.SEVERE, null, ex);
    }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
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
        Logger.getLogger(copyClientDetails.class.getName()).log(Level.SEVERE, null, ex);
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
