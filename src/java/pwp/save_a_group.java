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
public class save_a_group extends HttpServlet {
String group_name="", district_id,partner_id,nhf_id,location="",year_formed="";
String district_name="",partner_name="",group_id="";
HttpSession session;
double found=0;
String nextpage="",grp="",category="";
int Indvs;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
       session=request.getSession();
       dbConn conn = new dbConn();
      IdGenerator ig = new IdGenerator(); 
      grp=request.getParameter("grp");
      district_id=request.getParameter("district");
       partner_id=request.getParameter("partner");
       System.out.println(district_id+" partner id : "+partner_id);
       category=request.getParameter("cat");
 Indvs=0;
       nextpage="add_a_client.jsp";
       String dname="SELECT district_name from district where district_id='"+district_id+"'";
       conn.rs=conn.st.executeQuery(dname);
       if(conn.rs.next()==true){
        district_name=conn.rs.getString(1) ;  
       }
      String pname="SELECT partner_name FROM partner WHERE partner_id='"+partner_id+"'" ;
      conn.rs=conn.st.executeQuery(pname);
if(conn.rs.next()==true){
    partner_name=conn.rs.getString(1);
}
      
      if(category.equals("2")){
        String group_checker="SELECT group_name FROM groups WHERE group_id='"+grp+"'";
          conn.rs=conn.st.executeQuery(group_checker);
          if(conn.rs.next()==true){
            group_name=conn.rs.getString(1);     
          }  
       session.setAttribute("group_id", grp); 
       session.setAttribute("group_name", group_name);
      }
      
      
    else  if(category.equals("3")){  
         nextpage="viewIndividuals.jsp";  
       String chekInd="SELECT COUNT(client_id) FROM personal_information WHERE district_id='"+district_id+"' && partner_id='"+partner_id+"' && group_id='0'" ;
       conn.rs1=conn.st1.executeQuery(chekInd);
       if(conn.rs1.next()==true){
           Indvs=conn.rs1.getInt(1);
       }
       if(Indvs==0){
//           NO CLIENTS INDIVIDUAL EXIST IN THE SYSTEM================================
           session.setAttribute("error_group", "<font color=\"red\">No client is taking individual sessions within the selected parameters.</font>");
      nextpage="add_a_group.jsp";
       }   
       session.setAttribute("group_id", "0"); 
       session.setAttribute("group_name", "INDIVIDUAL");
      }
    else{}
       
  session.setAttribute("district_name", district_name);
  session.setAttribute("partner_name", partner_name);
   session.setAttribute("district_id", district_id);
  session.setAttribute("partner_id", partner_id);
  
  System.out.println("group id is  :  "+session.getAttribute("group_id"));
  System.out.println("group nm is  :  "+session.getAttribute("group_name"));
  System.out.println("partner_name is  :  "+session.getAttribute("partner_name"));
  System.out.println("district name is  :  "+session.getAttribute("district_name"));
   
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

  System.out.println("group _  id : "+session.getAttribute("group_id"));
  response.sendRedirect(nextpage);
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
            Logger.getLogger(save_a_group.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(save_a_group.class.getName()).log(Level.SEVERE, null, ex);
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
