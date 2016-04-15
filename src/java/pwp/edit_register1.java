/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pwp;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
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
public class edit_register1 extends HttpServlet {
    ArrayList edit_reg1 = new ArrayList();
    HttpSession session;
    String group_id;
    String message,methods;
    int positioner,message_counter,already_marked;
    String partner_id, district_id,nextpage="",yr,period,month;
    String marked_sessions;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        
        session=request.getSession();
        dbConn conn = new dbConn();
        nextpage="";
        edit_reg1.clear();
        message=methods="";
        positioner=0;
        marked_sessions=",";
        if(session.getAttribute("sessions_group_id")!=null ){
        group_id=session.getAttribute("sessions_group_id").toString();
        partner_id=session.getAttribute("partner_id").toString();
         district_id=session.getAttribute("district_id").toString();
        
         if(!group_id.equals("0")){
        String session_details_selector="SELECT * FROM sessions WHERE group_id='"+group_id+"' && partner_id='"+partner_id+"' && district_id='"+district_id+"' ORDER BY session_id";
        conn.rs=conn.st.executeQuery(session_details_selector);
//        System.out.println("Session edit     .1    "+session.getAttribute("sessions_group_id").toString());
         }
         
         else if(group_id.equals("0")){
        String session_details_selector="SELECT * FROM sessions WHERE groupings='"+session.getAttribute("groupings")+"' && partner_id='"+partner_id+"' && district_id='"+district_id+"' ORDER BY session_id";
        conn.rs=conn.st.executeQuery(session_details_selector);
//         System.out.println("Session edit     0    "+session.getAttribute("sessions_group_id").toString());
         }
        while(conn.rs.next()){
        positioner++;
       message=methods="";
      message_counter=already_marked=0;
            edit_register1_bean ebean1 = new edit_register1_bean();
//           System.out.println(Integer.parseInt(session.getAttribute("chosen_session").toString())<conn.rs.getInt(3) && (conn.rs.getString(5).equals("0")));
            ebean1.setPositioner(positioner);
//            if(Integer.parseInt(session.getAttribute("chosen_session").toString())<conn.rs.getInt(4) && (conn.rs.getString(5).equals("0"))){
             if(session.getAttribute("chosen_session_1").toString().contains(","+conn.rs.getInt(4)+",") && (conn.rs.getString(5).equals(""))){         
            ebean1.setSessions_id(conn.rs.getString(1));
            ebean1.setSession_id(conn.rs.getInt(3));
            ebean1.setSession_date("");
            ebean1.setDuration("");
            ebean1.setMale_cds("");
            ebean1.setFemale_cds("");
            ebean1.setIec_materials("");    
         marked_sessions+=positioner+",";
            int gt=0;
         String method_selector="SELECT * FROM methods_used";
                conn.rs1=conn.st1.executeQuery(method_selector);
               while(conn.rs1.next()){
                  
                   gt++;
                 if(gt!=1){  
                methods+="<option value=\""+conn.rs1.getString(1)+"\">"+conn.rs1.getString(1)+". "+conn.rs1.getString(2)+"</option>";  
                 }
                if(gt==1){
              methods+="<option value=\""+conn.rs1.getString(1)+"\" selected>"+conn.rs1.getString(1)+". "+conn.rs1.getString(2)+"</option>";        
                }
               }
            ebean1.setMethod_used(methods); 
            ebean1.setDisabled("");
            }
        else  if(!conn.rs.getString(5).equals(""))
        {
            marked_sessions+=positioner+",";
           ebean1.setSessions_id(conn.rs.getString(1));
            ebean1.setSession_id(conn.rs.getInt(3));
            ebean1.setSession_date(conn.rs.getString(5));
            ebean1.setDuration(conn.rs.getString(8));
            ebean1.setMale_cds(conn.rs.getString(9));
            ebean1.setFemale_cds(conn.rs.getString(10));
            ebean1.setIec_materials(conn.rs.getString(11));

            
            String method_ids=conn.rs.getString(7);
            
                  String method_selector="SELECT * FROM methods_used";
                conn.rs1=conn.st1.executeQuery(method_selector);
               while(conn.rs1.next()){
                if(method_ids.contains(","+conn.rs1.getString(1)+",")){
                methods+="<option value=\""+conn.rs1.getString(1)+"\" selected>"+conn.rs1.getString(1)+". "+conn.rs1.getString(2)+"</option>";   
               }
                else{
                   methods+="<option value=\""+conn.rs1.getString(1)+"\">"+conn.rs1.getString(1)+". "+conn.rs1.getString(2)+"</option>";  
                }
               }
         ebean1.setMethod_used(methods);
         ebean1.setDisabled("");
          
        }
            else{
            ebean1.setSessions_id(conn.rs.getString(1));
            ebean1.setSession_id(conn.rs.getInt(3));
            ebean1.setSession_date(conn.rs.getString(5));
            ebean1.setDuration(conn.rs.getString(8));
            ebean1.setMale_cds(conn.rs.getString(9));
            ebean1.setFemale_cds(conn.rs.getString(10));
            ebean1.setIec_materials(conn.rs.getString(11));
            ebean1.setDisabled("disabled");
            session.setAttribute("sess_"+positioner, "1");
            
                  String method_selector="SELECT * FROM methods_used";
                conn.rs1=conn.st1.executeQuery(method_selector);
               while(conn.rs1.next()){
               methods+="<option value=\""+conn.rs1.getString(1)+"\" disabled>"+conn.rs1.getString(2)+"</option>";   
               }
               
         ebean1.setMethod_used(methods);   
        }
          edit_reg1.add(ebean1); 
        }
      session.setAttribute("edit_reg1", edit_reg1);

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

   session.setAttribute("marked_sessions", marked_sessions);
      nextpage="edit_sessions.jsp"; 
        }
   else{
      nextpage="mark_attendance.jsp" ;
   }
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
            Logger.getLogger(edit_register1.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(edit_register1.class.getName()).log(Level.SEVERE, null, ex);
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
