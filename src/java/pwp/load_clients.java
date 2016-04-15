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
public class load_clients extends HttpServlet {
HttpSession session;
String partner,district,county;
String client_id;
String group_id,fname,mname,lname,age,sex;
String all_counties,all_districts,all_groups,all_sex,all_partner;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
           dbConn conn = new dbConn();
           session=request.getSession();
           client_id=request.getParameter("client");
           
           System.out.println("client id is  :: "+client_id);
           all_counties=all_districts=all_groups=all_partner=all_sex="";
           district=session.getAttribute("district_id").toString();
           partner=session.getAttribute("partner_id").toString();
           
           String part="SELECT * FROM partner";
           conn.rs=conn.st.executeQuery(part);
           while(conn.rs.next()){
            String par=conn.rs.getString(1);
             if(partner.equals(par)){
            all_partner+="<option value=\""+par+"\" selected>"+conn.rs.getString(2)+"</option>";  
          }
         else{
          all_partner+="<option value=\""+par+"\">"+conn.rs.getString(2)+"</option>";   
          }   
               
           }
           
           String checker="SELECT * FROM clients  WHERE client_id='"+client_id+"' &&"
                   + "clients.district_id='"+district+"' && clients.partner_id='"+partner+"'";
           conn.rs=conn.st.executeQuery(checker);
            conn.rs.next();
          client_id=conn.rs.getString("client_id");
          fname=conn.rs.getString("fname");
          mname=conn.rs.getString("mname");
          lname=conn.rs.getString("lname");
          age=conn.rs.getString("age");
          sex=conn.rs.getString("gender");
          group_id=conn.rs.getString("group_id");
         if(mname.equals(lname)){
             mname="";
         }
          String sex_selector="SELECT * FROM gender";
          conn.rs=conn.st.executeQuery(sex_selector);
          while(conn.rs.next()){
         String sex1=conn.rs.getString(2);
             if(sex.equals(sex1)){
            all_sex+="<option value=\""+sex1+"\" selected>"+conn.rs.getString(2)+"</option>";  
          }
         else{
          all_sex+="<option value=\""+sex1+"\">"+conn.rs.getString(2)+"</option>";   
          }     
              
          }
//          &&&&&&&&&&&&CHOOSE TO CHANGE GROUP&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&
          if(group_id.equals("0")){
            all_groups+="<option value=\"0\" selected>INDIVIDUALS</option>";  
          }
         else{
            all_groups+="<option value=\"0\">INDIVIDUALS</option>";  
          }
          
          String groups_selector="SELECT * FROM groups WHERE district_id='"+district+"' && partner_id='"+partner+"'";
          conn.rs=conn.st.executeQuery(groups_selector);
          while(conn.rs.next()){
             String grp=conn.rs.getString(1);
             if(group_id.equals(grp)){
            all_groups+="<option value=\""+grp+"\" selected>"+conn.rs.getString(2)+"</option>";  
          }
         else{
          all_groups+="<option value=\""+grp+"\">"+conn.rs.getString(2)+"</option>";   
          }
              
              
          }
          
          String dis="SELECT * FROM district WHERE district_id='"+district+"'";
          conn.rs=conn.st.executeQuery(dis);
          conn.rs.next();
          county=conn.rs.getString(2);
          
          String county_selector="SELECT * FROM county";
          conn.rs=conn.st.executeQuery(county_selector);
          while(conn.rs.next()){
              String ct=conn.rs.getString(1);
              if(ct.equals(county)){
              all_counties+="<option value=\""+ct+"\" selected>"+conn.rs.getString(2)+"</option>";
          }
              else{
              all_counties+="<option value=\""+ct+"\">"+conn.rs.getString(2)+"</option>";
          }
          }
          
          
          String dist="select * from district where county_id='"+county+"'";
          conn.rs=conn.st.executeQuery(dist);
          while(conn.rs.next()){
             String ct=conn.rs.getString(1);
              if(ct.equals(district)){
              all_districts+="<option value=\""+ct+"\" selected>"+conn.rs.getString(3)+"</option>";
          }
              else{
              all_districts+="<option value=\""+ct+"\">"+conn.rs.getString(3)+"</option>";
          }  
          }
          session.setAttribute("client", client_id);
          session.setAttribute("fname", fname);
          session.setAttribute("mname", mname);
          session.setAttribute("lname", lname);
          session.setAttribute("age", age);
          session.setAttribute("all_sex", all_sex);
          session.setAttribute("all_groups", all_groups);
          session.setAttribute("all_districts", all_districts);
          session.setAttribute("all_counties", all_counties);
          session.setAttribute("all_partner", all_partner);

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
          System.out.println("groups  :  "+all_groups);
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
            Logger.getLogger(load_clients.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(load_clients.class.getName()).log(Level.SEVERE, null, ex);
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
