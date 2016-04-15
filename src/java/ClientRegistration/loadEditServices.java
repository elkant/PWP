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
import pwp.attendance_bean;
import pwp.dbConn;

/**
 *
 * @author Geofrey Nyabuto
 */
public class loadEditServices extends HttpServlet {
HttpSession session;
    ArrayList clientale= new ArrayList();
    String group_id="";
    int total_clients;
    String signs_are;
    int positioner,legibleClient;
    String partner_id, district_id;
     String yea,period,month,nextpage;
     String sessions="",client_id,message,messageid;
String [] clientIDS,STATUS={"YES","NO"};
String regid,cm,rp,cg,std,ss,tp,tc,ds;
String contraceptive_method,rsp,condoms_given,screened_tb,screened_stis,tested_partner,tested_children,disclosed_status;
String imageCM,imageSP,imageTB,imageSTI,imagePartner,imageChildren,imageStatus;
  
protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
            
        session=request.getSession();
        dbConn conn= new dbConn();
        clientale.clear();
        sessions="";nextpage="";
        positioner=0;
        total_clients=0;
        signs_are="";
         messageid="";
         imageCM=imageSP=imageTB=imageSTI=imagePartner=imageChildren=imageStatus="images/absent.JPG";
        if(session.getAttribute("messageID")!=null){messageid=session.getAttribute("messageID").toString();}
        String signs_selector="SELECT * FROM attendance_signs";
        System.out.println("mesage id : "+messageid);
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
        System.out.println("client id is : "+client_id);
       String client_selector="SELECT client_id,fname,mname,lname,"
                + "DATE_FORMAT( NOW( ) , '%Y' ) - DATE_FORMAT( dob, '%Y' )-( DATE_FORMAT( NOW( ),'YYYY-%mm-%dd' )< DATE_FORMAT( dob, 'YYYY-%mm-%dd' ) )"
                + ",gender FROM personal_information WHERE client_id='"+client_id+"'"
                + "ORDER BY fname,mname,lname";
        conn.rs=conn.st.executeQuery(client_selector);
        if(conn.rs.next()==true){
            imageCM=imageSP=imageTB=imageSTI=imagePartner=imageChildren=imageStatus="images/absent.JPG";
           message=regid=cm=rp=cg=std=ss=tp=tc=ds="";
           contraceptive_method=rsp=condoms_given=screened_tb=screened_stis=tested_partner=tested_children=disclosed_status="";
           String getMessages="SELECT message FROM message_codes "
                    + "JOIN register2 ON message_codes.message_id=register2.session_no "
                    + "WHERE register2.client_id='"+client_id+"' && register2.value='1' && message_id='"+messageid+"'";
            conn.rs2=conn.st2.executeQuery(getMessages);
            if(conn.rs2.next()==true){
                
                 message=conn.rs2.getString(1);
                 session.setAttribute("messageName", message);
                 session.setAttribute("messageID", messageid);
                String checkMarked="SELECT services_id,contraceptive_method,rsp,cds_given,screened_tb,screened_stis,"
                        + "tested_partner,tested_children,disclosed_status FROM services_provided WHERE"
                        + " client_id='"+client_id+"' && session_no LIKE '%,"+messageid+",%'";
                conn.rs1=conn.st1.executeQuery(checkMarked);
                if(conn.rs1.next()==true){
                     legibleClient++;
                    regid=conn.rs1.getString(1);
                    cm=conn.rs1.getString(2);
                    rp=conn.rs1.getString(3);
                    cg=conn.rs1.getString(4);
                    std=conn.rs1.getString(5);
                    ss=conn.rs1.getString(6);
                    tp=conn.rs1.getString(7);
                    tc=conn.rs1.getString(8);
                    ds=conn.rs1.getString(9);
condoms_given=cg;
                  if(cm.equals("YES")){contraceptive_method+="checked";imageCM="images/present.JPG";}
                  
                  if(rp.equals("YES")){rsp+="checked";imageSP="images/present.JPG";}
                  
                  if(std.equals("YES")){screened_tb+="checked";imageTB="images/present.JPG";}
                  
                  if(ss.equals("YES")){screened_stis+="checked";imageSTI="images/present.JPG";}
                  
                  if(tp.equals("YES")){tested_partner+="checked";imagePartner="images/present.JPG";}
                  
                  if(tc.equals("YES")){tested_children+="checked";imageChildren="images/present.JPG";}
                  
                  if(ds.equals("YES")){disclosed_status+="checked";imageStatus="images/present.JPG";}
                  
                 }
            
            }
            if(legibleClient>0){
                attendance_bean abn=new attendance_bean();
                positioner++;
          abn.setPositioner(positioner);
       if(conn.rs.getString(3).equals(conn.rs.getString(4))){
        abn.setClient_name(conn.rs.getString(2)+" "+conn.rs.getString(4));
        }
        if(!conn.rs.getString(3).equals(conn.rs.getString(4))){
        abn.setClient_name(conn.rs.getString(2)+" "+conn.rs.getString(3)+" "+conn.rs.getString(4));
        }
        abn.setRegid(regid);
        abn.setAge(conn.rs.getString(5));
        abn.setMessage(message);
        abn.setSex(conn.rs.getString(6));
        abn.setClient_id(conn.rs.getString(1));
        abn.setDisabled("");
        abn.setChecked("checked");
        abn.setCondoms_given(condoms_given);
        abn.setContraceptive_method(contraceptive_method);
        abn.setRsp(rsp);
        abn.setScreened_stis(screened_stis);
        abn.setScreened_tb(screened_tb);
        abn.setTested_children(tested_children);
        abn.setTested_partner(tested_partner);
        abn.setDisclosed_status(disclosed_status);
        abn.setImageCM(imageCM);
        abn.setImageChildren(imageChildren);
        abn.setImagePartner(imagePartner);
        abn.setImageSP(imageSP);
        abn.setImageSTI(imageSTI);
        abn.setImageStatus(imageStatus);
        abn.setImageTB(imageTB);
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
    session.setAttribute("noSession", "<font color=\"red\"><b>All services have already been given for the selected message and clients.</b></font>");
}
else{
    nextpage="viewEditIndServices.jsp";
}
        session.setAttribute("total_clients", positioner);
        session.setAttribute("EditInd", clientale);
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
        Logger.getLogger(loadEditServices.class.getName()).log(Level.SEVERE, null, ex);
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
        Logger.getLogger(loadEditServices.class.getName()).log(Level.SEVERE, null, ex);
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
