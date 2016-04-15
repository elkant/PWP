/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ClientRegistration;

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
import pwp.IdGenerator;
import pwp.attendance_bean;
import pwp.dbConn;

/**
 *
 * @author Geofrey Nyabuto
 */
public class LoadIndNewServices extends HttpServlet {
HttpSession session;
    ArrayList clientale= new ArrayList();
    String group_id="";
    int total_clients;
    String signs_are;
    int positioner,legibleClient;
    String partner_id, district_id;
     String yea,period,month,nextpage;
     String sessions="",client_id,message;
String [] clientIDS;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
      session=request.getSession();
        dbConn conn= new dbConn();
        clientale.clear();
        sessions="";nextpage="";
//        partner_id=session.getAttribute("partner_id").toString();
//         district_id=session.getAttribute("district_id").toString();
        positioner=0;
        total_clients=0;
        signs_are="";
        String signs_selector="SELECT * FROM attendance_signs";
        conn.rs=conn.st.executeQuery(signs_selector);
        while(conn.rs.next()){
            signs_are+="<option value=\""+conn.rs.getString(1)+"\">"+conn.rs.getString(2)+"</option>";
        }
       clientIDS=session.getAttribute("indClicents").toString().split(",");
        int totalSelected=clientIDS.length; 
        System.out.println("totals selected : "+totalSelected);
        for(int i=1; i<=totalSelected;i++){      
    client_id=clientIDS[i-1];
    if(!client_id.equals("") && !client_id.equals(",")){
        legibleClient=0;
       String client_selector="SELECT client_id,fname,mname,lname,"
                + "DATE_FORMAT( NOW( ) , '%Y' ) - DATE_FORMAT( dob, '%Y' )-( DATE_FORMAT( NOW( ),'YYYY-%mm-%dd' )< DATE_FORMAT( dob, 'YYYY-%mm-%dd' ) )"
                + ",gender FROM personal_information WHERE client_id='"+client_id+"'"
                + "ORDER BY fname,mname,lname";
        conn.rs=conn.st.executeQuery(client_selector);
        if(conn.rs.next()==true){
           message="";
             attendance_bean abn=new attendance_bean();
            String getMessages="SELECT message_id,message FROM message_codes "
                    + "JOIN register2 ON message_codes.message_id=register2.session_no "
                    + "WHERE register2.client_id='"+client_id+"' && register2.value='1'";
            conn.rs2=conn.st2.executeQuery(getMessages);
            while(conn.rs2.next()){
                String checkMarked="SELECT services_id FROM services_provided WHERE"
                        + " client_id='"+client_id+"' && session_no LIKE '%,"+conn.rs2.getString(1)+",%'";
                conn.rs1=conn.st1.executeQuery(checkMarked);
                if(conn.rs1.next()==true){
               System.out.println("already marked mesage id "+conn.rs2.getString(1)+" client id is : "+client_id);
                message+="<option value=\""+conn.rs2.getString(1)+"\" disabled>*"+conn.rs2.getString(2)+"</option>";  
                }
                else{
                    legibleClient++;
                      System.out.println("new mesage id "+conn.rs2.getString(1)+" client id is : "+client_id);
              message+="<option value=\""+conn.rs2.getString(1)+"\">"+conn.rs2.getString(2)+"</option>";  
            }
            }
            if(legibleClient>0){
                positioner++;
          abn.setPositioner(positioner);
       if(conn.rs.getString(3).equals(conn.rs.getString(4))){
        abn.setClient_name(conn.rs.getString(2)+" "+conn.rs.getString(4));
        }
        if(!conn.rs.getString(3).equals(conn.rs.getString(4))){
        abn.setClient_name(conn.rs.getString(2)+" "+conn.rs.getString(3)+" "+conn.rs.getString(4));
        }
        abn.setAge(conn.rs.getString(5));
        abn.setMessage(message);
        abn.setSex(conn.rs.getString(6));
        abn.setClient_id(conn.rs.getString(1));
        abn.setDisabled("");
        abn.setChecked("checked");
        
total_clients++;
clientale.add(abn);
            } 
    }
    }
        }
if(conn.rs!=null){
            conn.rs.close();
            }
if(conn.st!=null){
            conn.st.close();
            }
if(session.getAttribute("total_cds")==null){
   session.setAttribute("total_cds", "0");
}
if(positioner==0){
    nextpage="viewIndividuals.jsp";
    session.setAttribute("noSession", "<font color=\"red\"><b>All services have already been given for messages that were given to the selected clients.</b></font>");
}
else{
    nextpage="markIndividualServices.jsp";
}
        session.setAttribute("total_clients", positioner);
        session.setAttribute("clientale", clientale);
        session.setAttribute("signs_are", signs_are);
        
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

        response.sendRedirect(nextpage);
   
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
        Logger.getLogger(LoadIndNewServices.class.getName()).log(Level.SEVERE, null, ex);
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
        Logger.getLogger(LoadIndNewServices.class.getName()).log(Level.SEVERE, null, ex);
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
