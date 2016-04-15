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
public class load_services_provided extends HttpServlet {
HttpSession session;
    ArrayList clientale= new ArrayList();
    String group_id="";
    int total_clients;
    String signs_are;
    int positioner;
    String partner_id, district_id;
     String yea,period,month;
     String sessions="";
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        session=request.getSession();
        dbConn conn= new dbConn();
        clientale.clear();
        sessions="";
        sessions="s"+session.getAttribute("chosen_session").toString().replace("null", "");
        partner_id=session.getAttribute("partner_id").toString();
         district_id=session.getAttribute("district_id").toString();
        positioner=0;
        total_clients=0;
        signs_are="<Option value=\"\"> </option>";
        String signs_selector="SELECT * FROM attendance_signs";
        conn.rs=conn.st.executeQuery(signs_selector);
        while(conn.rs.next()){
            signs_are+="<option value=\""+conn.rs.getString(1)+"\">"+conn.rs.getString(2)+"</option>";
        }
       System.out.println("missed clients  :   "+session.getAttribute("missed_clients"));
       if(session.getAttribute("client_ids")!=null){
           String activeClients[] =session.getAttribute("client_ids").toString().split(",");
           String clientid="";
           for(int j=0;j<activeClients.length;j++){
           clientid= activeClients[j]  ; 
           if(!clientid.equals("")){
        String client_selector="SELECT client_id,fname,mname,lname,"
                + "DATE_FORMAT( NOW( ) , '%Y' ) - DATE_FORMAT( dob, '%Y' )-( DATE_FORMAT( NOW( ),'YYYY-%mm-%dd' )< DATE_FORMAT( dob, 'YYYY-%mm-%dd' ) )"
                + ",gender FROM personal_information WHERE client_id='"+clientid+"'"
                + " ORDER BY fname,mname,lname";
        conn.rs=conn.st.executeQuery(client_selector);    
          if(conn.rs.next()==true){
            String status="";
            String [] sesser=session.getAttribute("chosen_session").toString().replace("null", "").split(",");
            for (String sess: sesser){
                if(!sess.equals("")){
                String s="s"+sess;
                System.out.println("sessions   : "+session.getAttribute("chosen_session").toString());
            String sess_status="SELECT "+s+" FROM register WHERE client_id='"+conn.rs.getString(1)+"'";
            conn.rs0=conn.st0.executeQuery(sess_status);
            if(conn.rs0.next()){
               status=conn.rs0.getString(1);
               if(!status.equals("2")){
                   break;
               }
            }
            }}
            System.out.println("status   :   "+status);
           
            positioner++;
             attendance_bean abn=new attendance_bean();
            if(status.equals("2")){
        abn.setPositioner(positioner);
       if(conn.rs.getString(3).equals(conn.rs.getString(4))){
        abn.setClient_name(conn.rs.getString(2)+" "+conn.rs.getString(4));
        }
        if(!conn.rs.getString(3).equals(conn.rs.getString(4))){
        abn.setClient_name(conn.rs.getString(2)+" "+conn.rs.getString(3)+" "+conn.rs.getString(4));
        }
        abn.setAge(conn.rs.getString(5));
        abn.setSex(conn.rs.getString(6));
        abn.setClient_id(conn.rs.getString(1));
        abn.setDisabled("disabled");
        abn.setChecked("");
            }
         else{
        abn.setPositioner(positioner);
       if(conn.rs.getString(3).equals(conn.rs.getString(4))){
        abn.setClient_name(conn.rs.getString(2)+" "+conn.rs.getString(4));
        }
        if(!conn.rs.getString(3).equals(conn.rs.getString(4))){
        abn.setClient_name(conn.rs.getString(2)+" "+conn.rs.getString(3)+" "+conn.rs.getString(4));
        }
        abn.setAge(conn.rs.getString(5));
        abn.setSex(conn.rs.getString(6));
        abn.setClient_id(conn.rs.getString(1));
        abn.setDisabled("");
        abn.setChecked("checked");
            }
total_clients++;
clientale.add(abn);
    }    
           
           }     }     
       }
       
       else{
        group_id=session.getAttribute("sessions_group_id").toString();
        if(session.getAttribute("sessions_group_id")!=null){
            if(!group_id.equals("0")){
        String client_selector="SELECT client_id,fname,mname,lname,"
                + "DATE_FORMAT( NOW( ) , '%Y' ) - DATE_FORMAT( dob, '%Y' )-( DATE_FORMAT( NOW( ),'YYYY-%mm-%dd' )< DATE_FORMAT( dob, 'YYYY-%mm-%dd' ) )"
                + ",gender FROM personal_information WHERE group_id='"+group_id+"'&& partner_id='"+partner_id+"' && district_id='"+district_id+"'"
                + " ORDER BY fname,mname,lname";
        conn.rs=conn.st.executeQuery(client_selector);
            }
            else if(group_id.equals("0")){ 
        String client_selector="SELECT client_id,fname,mname,lname,"
                + "DATE_FORMAT( NOW( ) , '%Y' ) - DATE_FORMAT( dob, '%Y' )-( DATE_FORMAT( NOW( ),'YYYY-%mm-%dd' )< DATE_FORMAT( dob, 'YYYY-%mm-%dd' ) )"
                + ",gender FROM personal_information WHERE groupings='"+session.getAttribute("groupings").toString() +"' && partner_id='"+partner_id+"' && district_id='"+district_id+"' "
                + "ORDER BY fname,mname,lname";
        conn.rs=conn.st.executeQuery(client_selector);
            }
        while(conn.rs.next()){
            String status="";
            String [] sesser=session.getAttribute("chosen_session").toString().replace("null", "").split(",");
            for (String sess: sesser){
                if(!sess.equals("")){
                String s="s"+sess;
                System.out.println("sessions   : "+session.getAttribute("chosen_session").toString());
            String sess_status="SELECT "+s+" FROM register WHERE client_id='"+conn.rs.getString(1)+"'";
            conn.rs0=conn.st0.executeQuery(sess_status);
            if(conn.rs0.next()){
               status=conn.rs0.getString(1);
               if(!status.equals("2")){
                   break;
               }
            }
            }}
            System.out.println("status   :   "+status);
           
            positioner++;
             attendance_bean abn=new attendance_bean();
            if(status.equals("2")){
        abn.setPositioner(positioner);
       if(conn.rs.getString(3).equals(conn.rs.getString(4))){
        abn.setClient_name(conn.rs.getString(2)+" "+conn.rs.getString(4));
        }
        if(!conn.rs.getString(3).equals(conn.rs.getString(4))){
        abn.setClient_name(conn.rs.getString(2)+" "+conn.rs.getString(3)+" "+conn.rs.getString(4));
        }
        abn.setAge(conn.rs.getString(5));
        abn.setSex(conn.rs.getString(6));
        abn.setClient_id(conn.rs.getString(1));
        abn.setDisabled("disabled");
        abn.setChecked("");
            }
         else{
        abn.setPositioner(positioner);
       if(conn.rs.getString(3).equals(conn.rs.getString(4))){
        abn.setClient_name(conn.rs.getString(2)+" "+conn.rs.getString(4));
        }
        if(!conn.rs.getString(3).equals(conn.rs.getString(4))){
        abn.setClient_name(conn.rs.getString(2)+" "+conn.rs.getString(3)+" "+conn.rs.getString(4));
        }
        abn.setAge(conn.rs.getString(5));
        abn.setSex(conn.rs.getString(6));
        abn.setClient_id(conn.rs.getString(1));
        abn.setDisabled("");
        abn.setChecked("checked");
            }
total_clients++;
clientale.add(abn);
    }
        int found=0;
     //       INSERT TRACKER TO THE DATABASE---------------------
        String checker="SELECT COUNT(trackerid) FROM tracker";
        conn.rs=conn.st.executeQuery(checker);
       if(conn.rs.next()==true){
           found=conn.rs.getInt(1);
       }
       if(found==0){
      
           IdGenerator IGM = new IdGenerator();
           
      String inserter="INSERT INTO tracker (trackerid,groupid,sessions,timestamp,year,partner,district,groupings,missed_clients) "
           + "VALUES('"+IGM.current_id()+"','"+group_id+"','"+session.getAttribute("chosen_session").toString()+"','"+IGM.toDay()+"','"+yea+"','"+partner_id+"','"+district_id+"','"+session.getAttribute("groupings").toString()+"','"+session.getAttribute("missed_clients")+"')";
           conn.st4.executeUpdate(inserter);
      
       }
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

if(session.getAttribute("total_cds")==null){
   session.setAttribute("total_cds", "0");
}
        session.setAttribute("total_clients", positioner);
        session.setAttribute("clientale", clientale);
        session.setAttribute("signs_are", signs_are);
        response.sendRedirect("mark_register4.jsp");
        

        
        
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
            Logger.getLogger(load_services_provided.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(load_services_provided.class.getName()).log(Level.SEVERE, null, ex);
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
