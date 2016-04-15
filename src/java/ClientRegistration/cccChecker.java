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
import pwp.dbConn;

/**
 *
 * @author Geofrey Nyabuto
 */
public class cccChecker extends HttpServlet {
HttpSession session;
String groupName,districtName,countyName,partnerName,serviceProvider,client_name;
String fname,mname,lname,pfname,plname,pmname;
String status,client_id,data,ccc_no;
int pos;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
         dbConn conn = new dbConn();
         session=request.getSession();
          groupName=districtName=countyName=partnerName=serviceProvider=client_name="";
          fname=mname=lname=pfname=plname=pmname="";
          status=data="";
ccc_no="";
client_id="";
            pos=0;
      
         ccc_no=request.getParameter("ccc_no");
         if(!ccc_no.equals("")){
         String checker="SELECT personal_information.fname,personal_information.mname,personal_information.lname,"
                 + "service_provider.fname,service_provider.mname,service_provider.lname,groups.group_name,"
                 + "district.district_name,partner.partner_name,county.county_name,personal_information.client_id FROM "
                 + "personal_information LEFT JOIN groups ON personal_information.group_id=groups.group_id "
                 + "LEFT JOIN district  ON personal_information.district_id=district.district_id "
                 + "LEFT JOIN county ON district.county_id=county.county_id "
                 + "LEFT JOIN partner ON personal_information.partner_id=partner.partner_id "
                 + "LEFT JOIN service_provider ON personal_information.provider_id=service_provider.provider_id "
                 + "WHERE personal_information.ccc_no=?";
         conn.pst=conn.conn.prepareStatement(checker);
         conn.pst.setString(1, ccc_no);
        
         
         conn.rs=conn.pst.executeQuery();
         
         while(conn.rs.next()){
             pos++;
          fname=conn.rs.getString(1);
          mname=conn.rs.getString(2);
          lname=conn.rs.getString(3);
          
          pfname=conn.rs.getString(4);
          pmname=conn.rs.getString(5);
          plname=conn.rs.getString(6);
          
          groupName=conn.rs.getString(7);
          districtName=conn.rs.getString(8);
          partnerName=conn.rs.getString(9);
          countyName=conn.rs.getString(10);
          if(mname.equalsIgnoreCase(lname)){
          client_name=fname+" "+lname;    
          }
             if (!mname.equalsIgnoreCase(lname)) {
            client_name=fname+" "+mname+" "+lname;     
             }
             
         if(pmname.equalsIgnoreCase(plname)){
          serviceProvider=pfname+" "+plname;    
          }
             if (!pmname.equalsIgnoreCase(plname)) {
            serviceProvider=pfname+" "+pmname+" "+plname;     
             }
           
           if(groupName==null){
               groupName="INDIVIDUAL";
           }  
            
           client_id=conn.rs.getString(11);
          status+="<p style=\"font-size:15px; color: blue; text-align:center\">"+pos+". Client name : "+client_name+", Service provider : "+serviceProvider+", Group name :"+groupName+", District : "+districtName+", <a href=\"LoadEditClient?client_id="+client_id+"\" class=\"button-link\" style=\"font-color: black;\">Click here to edit his/her registration form.</a><br></p>";   
             
             
         }
        }
         if(pos==1){
         data="<p style=\"text-align:center;color:red;\" id=\"shaker\"><b>CLIENT WITH THIS CCC NUMBER EXIST IN THE SYSTEM AS SHOWN BELOW.</b></p>";
         }
         if(pos>1){
          data="<p style=\"text-align:center;color:red;\" id=\"shaker\"><b>CLIENTS WITH THIS CCC NUMBER EXIST IN THE SYSTEM AS SHOWN BELOW.</b></p>";   
         }
         
         if(pos==0){
             data="";     
         }
        
        data+=status; 
         
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

         System.out.println(data);
            out.println(data);
        } finally {
            out.close();
        }
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
        Logger.getLogger(cccChecker.class.getName()).log(Level.SEVERE, null, ex);
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
        Logger.getLogger(cccChecker.class.getName()).log(Level.SEVERE, null, ex);
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
