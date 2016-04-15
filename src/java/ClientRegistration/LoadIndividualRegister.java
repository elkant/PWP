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
public class LoadIndividualRegister extends HttpServlet {
HttpSession session;
    ArrayList clientale= new ArrayList();
    String group_id="";
    String [] clientIDS;
    int total_clients;
    String signs_are;
    int positioner,client_found;
    String partner_id, district_id;
    String yea,period,month,client_id,session_date;
String regid1,regid2,regid3,regid4,regid5,regid6,regid7,regid8,regid9,regid10,regid11,regid12,regid13;
String s1,s2,s3,s4,s5,s6,s7,s8,s9,s10,s11,s12,s13;
        String ds1,ds2,ds3,ds4,ds5,ds6,ds7,ds8,ds9,ds10,ds11,ds12,ds13;
        String img1,img2,img3,img4,img5,img6,img7,img8,img9,img10,img11,img12,img13;
        String s9status;int attended9;
        String disSele1,disSele2,disSele3,disSele4,disSele5,disSele6,disSele7,disSele8,disSele9,disSele10,disSele11,disSele12,disSele13;
//        String disDate1,disDate2,disDate3,disDate4,disDate5,disDate6,disDate7,disDate8,disDate9,disDate10,disDate11,disDate12,disDate13;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        session=request.getSession();
        dbConn conn= new dbConn();
        clientale.clear();
        client_id=request.getParameter("client_id");
        positioner=0;
        total_clients=0;
        
//System.out.println("selected :"+request.getParameter("all")+"here");
        clientIDS=session.getAttribute("indClicents").toString().split(",");
        int totalSelected=clientIDS.length;
     
        
signs_are="";
        String signs_selector="SELECT * FROM attendance_signs WHERE sign_id<='2'";
        conn.rs=conn.st.executeQuery(signs_selector);
        while(conn.rs.next()){
            signs_are+="<option value=\""+conn.rs.getString(1)+"\">"+conn.rs.getString(2)+"</option>";
        }
        System.out.println("total clients : "+totalSelected);


        
        
for(int i=1; i<=totalSelected;i++){      
    client_id=clientIDS[i-1];
    if(!client_id.equals("") && !client_id.equals(",")){
        
        String client_selector="SELECT client_id,fname,mname,lname,"
                + "DATE_FORMAT( NOW( ) , '%Y' ) - DATE_FORMAT( dob, '%Y' )-( DATE_FORMAT( NOW( ),'YYYY-%mm-%dd' )< DATE_FORMAT( dob, 'YYYY-%mm-%dd' ) )"
                + ",gender FROM personal_information WHERE client_id='"+client_id+"'"
                + " ORDER BY fname,mname,lname";
        conn.rs=conn.st.executeQuery(client_selector);
        if(conn.rs.next()==true){
            s9status="";attended9=0;
regid1=regid2=regid3=regid4=regid5=regid6=regid7=regid8=regid9=regid10=regid11=regid12=regid13="";
s1=s2=s3=s4=s5=s6=s7=s8=s9=s10=s11=s12=s13="<option value=\"\">Choose Status</option>";
ds1=ds2=ds3=ds4=ds5=ds6=ds7=ds8=ds9=ds10=ds11=ds12=ds13="";
img1=img2=img3=img4=img5=img6=img7=img8=img9=img10=img11=img12=img13="images/editor.jpg";  
//disSele1=disSele2=disSele3=disSele4=disSele5=disSele6=disSele7=disSele8=disSele9=disSele10=disSele11=disSele12=disSele13="";
//disDate1=disDate2=disDate3=disDate4=disDate5=disDate6=disDate7=disDate8=disDate9=disDate10=disDate11=disDate12=disDate13="";
            positioner++;
        IndividualAttenBean abn=new IndividualAttenBean();
    client_found=0;    
String getSessions="SELECT reg_id,session_no,value,date FROM register2 WHERE client_id='"+client_id+"' ORDER BY session_no";
conn.rs1=conn.st1.executeQuery(getSessions);
while(conn.rs1.next()){
    session_date="";
if(!conn.rs1.getString(4).equals("0")){
session_date=conn.rs1.getString(4);}
//    SESSION 1
if(conn.rs1.getString(2).equals("1")){
regid1=conn.rs1.getString(1);
ds1=session_date;
if(conn.rs1.getInt(3)<5){disSele1="hidden";}
else{disSele1="";}
String getSigns="SELECT sign_id,sign_name,image_url FROM attendance_signs ORDER BY sign_id ASC LIMIT 2";
conn.rs2=conn.st2.executeQuery(getSigns);
while(conn.rs2.next()){
    if(conn.rs2.getString(1).equals(conn.rs1.getString(3))){
 s1+="<option value=\""+conn.rs2.getString(1)+"\" selected>"+conn.rs2.getString(2)+"</option>";
 img1=conn.rs2.getString(3);
    }
    else{
   s1+="<option value=\""+conn.rs2.getString(1)+"\">"+conn.rs2.getString(2)+"</option>";     
    }

}
}
//    SESSION 2
if(conn.rs1.getString(2).equals("2")){
regid2=conn.rs1.getString(1);
ds2=session_date;
if(conn.rs1.getInt(3)<5){disSele2="hidden";}
else{disSele2="";}
String getSigns="SELECT sign_id,sign_name,image_url FROM attendance_signs ORDER BY sign_id ASC LIMIT 2";
conn.rs2=conn.st2.executeQuery(getSigns);
while(conn.rs2.next()){
    if(conn.rs2.getString(1).equals(conn.rs1.getString(3))){
 s2+="<option value=\""+conn.rs2.getString(1)+"\" selected>"+conn.rs2.getString(2)+"</option>";   
    img2=conn.rs2.getString(3);
    }
    else{
   s2+="<option value=\""+conn.rs2.getString(1)+"\">"+conn.rs2.getString(2)+"</option>";     
    }
}
}

//    SESSION 3
if(conn.rs1.getString(2).equals("3")){
regid3=conn.rs1.getString(1);
ds3=session_date;
if(conn.rs1.getInt(3)<5){disSele3="hidden";}
else{disSele3="";}
String getSigns="SELECT sign_id,sign_name,image_url FROM attendance_signs ORDER BY sign_id ASC LIMIT 2";
conn.rs2=conn.st2.executeQuery(getSigns);
while(conn.rs2.next()){
    if(conn.rs2.getString(1).equals(conn.rs1.getString(3))){
 s3+="<option value=\""+conn.rs2.getString(1)+"\" selected>"+conn.rs2.getString(2)+"</option>";   
    img3=conn.rs2.getString(3);
    }
    else{
   s3+="<option value=\""+conn.rs2.getString(1)+"\">"+conn.rs2.getString(2)+"</option>";     
    }
}
}

//    SESSION 4
if(conn.rs1.getString(2).equals("4")){
regid4=conn.rs1.getString(1);
ds4=session_date;
if(conn.rs1.getInt(3)<5){disSele4="hidden";}
else{disSele4="";}
String getSigns="SELECT sign_id,sign_name,image_url FROM attendance_signs ORDER BY sign_id ASC LIMIT 2";
conn.rs2=conn.st2.executeQuery(getSigns);
while(conn.rs2.next()){
    if(conn.rs2.getString(1).equals(conn.rs1.getString(3))){
 s4+="<option value=\""+conn.rs2.getString(1)+"\" selected>"+conn.rs2.getString(2)+"</option>";   
    img4=conn.rs2.getString(3);
    }
    else{
   s4+="<option value=\""+conn.rs2.getString(1)+"\">"+conn.rs2.getString(2)+"</option>";     
    }
}
}

//    SESSION 5
if(conn.rs1.getString(2).equals("5")){
regid5=conn.rs1.getString(1);
ds5=session_date;
if(conn.rs1.getInt(3)<5){disSele5="hidden";}
else{disSele5="";}
String getSigns="SELECT sign_id,sign_name,image_url FROM attendance_signs ORDER BY sign_id ASC LIMIT 2";
conn.rs2=conn.st2.executeQuery(getSigns);
while(conn.rs2.next()){
    if(conn.rs2.getString(1).equals(conn.rs1.getString(3))){
 s5+="<option value=\""+conn.rs2.getString(1)+"\" selected>"+conn.rs2.getString(2)+"</option>";   
   img5=conn.rs2.getString(3);
    }
    else{
   s5+="<option value=\""+conn.rs2.getString(1)+"\">"+conn.rs2.getString(2)+"</option>";     
    }
}
}

//    SESSION 6
if(conn.rs1.getString(2).equals("6")){
regid6=conn.rs1.getString(1);
ds6=session_date;
if(conn.rs1.getInt(3)<5){disSele6="hidden";}
else{disSele6="";}
String getSigns="SELECT sign_id,sign_name,image_url FROM attendance_signs ORDER BY sign_id ASC LIMIT 2";
conn.rs2=conn.st2.executeQuery(getSigns);
while(conn.rs2.next()){
    if(conn.rs2.getString(1).equals(conn.rs1.getString(3))){
 s6+="<option value=\""+conn.rs2.getString(1)+"\" selected>"+conn.rs2.getString(2)+"</option>";   
   img6=conn.rs2.getString(3);
    }
    else{
   s6+="<option value=\""+conn.rs2.getString(1)+"\">"+conn.rs2.getString(2)+"</option>";     
    }
}
}

//    SESSION 7
if(conn.rs1.getString(2).equals("7")){
regid7=conn.rs1.getString(1);
ds7=session_date;
if(conn.rs1.getInt(3)<5){disSele7="hidden";}
else{disSele7="";}
String getSigns="SELECT sign_id,sign_name,image_url FROM attendance_signs ORDER BY sign_id ASC LIMIT 2";
conn.rs2=conn.st2.executeQuery(getSigns);
while(conn.rs2.next()){
    if(conn.rs2.getString(1).equals(conn.rs1.getString(3))){
 s7+="<option value=\""+conn.rs2.getString(1)+"\" selected>"+conn.rs2.getString(2)+"</option>";   
   img7=conn.rs2.getString(3); 
    }
    else{
   s7+="<option value=\""+conn.rs2.getString(1)+"\">"+conn.rs2.getString(2)+"</option>";     
    }
}
}

//    SESSION 8
if(conn.rs1.getString(2).equals("8")){
regid8=conn.rs1.getString(1);
ds8=session_date;
if(conn.rs1.getInt(3)<5){disSele8="hidden";}
else{disSele8="";}
String getSigns="SELECT sign_id,sign_name,image_url FROM attendance_signs ORDER BY sign_id ASC LIMIT 2";
conn.rs2=conn.st2.executeQuery(getSigns);
while(conn.rs2.next()){
    if(conn.rs2.getString(1).equals(conn.rs1.getString(3))){
 s8+="<option value=\""+conn.rs2.getString(1)+"\" selected>"+conn.rs2.getString(2)+"</option>";   
   img8=conn.rs2.getString(3);
    }
    else{
   s8+="<option value=\""+conn.rs2.getString(1)+"\">"+conn.rs2.getString(2)+"</option>";     
    }
}
}

//    SESSION 9
if(conn.rs1.getString(2).equals("9")){
    ds9=session_date;
  String maxSess="",maxDate=""; 
   String getMaxDate="SELECT MAX(session_no) FROM adherence WHERE client_id='"+client_id+"'";
   conn.rs3=conn.st3.executeQuery(getMaxDate);
   if(conn.rs3.next()==true){
    maxSess=conn.rs3.getString(1);
   }
 String getDate="SELECT date_of_session FROM adherence WHERE session_no='"+maxSess+"' && client_id='"+client_id+"'";
 conn.rs3=conn.st3.executeQuery(getDate);
 if(conn.rs3.next()==true){
 ds9=conn.rs3.getString(1);
 }    
   
regid9=conn.rs1.getString(1);

if(conn.rs1.getInt(3)<5){disSele9="";}
else{disSele9="";}
String getSigns="SELECT sign_id,sign_name,image_url FROM attendance_signs ORDER BY sign_id ASC LIMIT 2";
conn.rs2=conn.st2.executeQuery(getSigns);
while(conn.rs2.next()){
    if(conn.rs2.getString(1).equals(conn.rs1.getString(3))){
 s9+="<option value=\""+conn.rs2.getString(1)+"\" selected>"+conn.rs2.getString(2)+"</option>";   
   img9=conn.rs2.getString(3);
   attended9++;
    }
    else{
   s9+="<option value=\""+conn.rs2.getString(1)+"\">"+conn.rs2.getString(2)+"</option>";     
    }
}
}

//    SESSION 10
if(conn.rs1.getString(2).equals("10")){
regid10=conn.rs1.getString(1);
ds10=session_date;
if(conn.rs1.getInt(3)<5){disSele10="hidden";}
else{disSele10="";}
String getSigns="SELECT sign_id,sign_name,image_url FROM attendance_signs ORDER BY sign_id ASC LIMIT 2";
conn.rs2=conn.st2.executeQuery(getSigns);
while(conn.rs2.next()){
    if(conn.rs2.getString(1).equals(conn.rs1.getString(3))){
 s10+="<option value=\""+conn.rs2.getString(1)+"\" selected>"+conn.rs2.getString(2)+"</option>";   
    img10=conn.rs2.getString(3);
    }
    else{
   s10+="<option value=\""+conn.rs2.getString(1)+"\">"+conn.rs2.getString(2)+"</option>";     
    }
}
}

//    SESSION 11
if(conn.rs1.getString(2).equals("11")){
regid11=conn.rs1.getString(1);
ds11=session_date;
if(conn.rs1.getInt(3)<5){disSele11="hidden";}
else{disSele11="";}
String getSigns="SELECT sign_id,sign_name,image_url FROM attendance_signs ORDER BY sign_id ASC LIMIT 2";
conn.rs2=conn.st2.executeQuery(getSigns);
while(conn.rs2.next()){
    if(conn.rs2.getString(1).equals(conn.rs1.getString(3))){
 s11+="<option value=\""+conn.rs2.getString(1)+"\" selected>"+conn.rs2.getString(2)+"</option>";   
   img11=conn.rs2.getString(3);
    }
    else{
   s11+="<option value=\""+conn.rs2.getString(1)+"\">"+conn.rs2.getString(2)+"</option>";     
    }
}
}

//    SESSION 12
if(conn.rs1.getString(2).equals("12")){
regid12=conn.rs1.getString(1);
ds12=session_date;
if(conn.rs1.getInt(3)<5){disSele12="hidden";}
else{disSele12="";}
String getSigns="SELECT sign_id,sign_name,image_url FROM attendance_signs ORDER BY sign_id ASC LIMIT 2";
conn.rs2=conn.st2.executeQuery(getSigns);
while(conn.rs2.next()){
    if(conn.rs2.getString(1).equals(conn.rs1.getString(3))){
 s12+="<option value=\""+conn.rs2.getString(1)+"\" selected>"+conn.rs2.getString(2)+"</option>";   
   img12=conn.rs2.getString(3);
    }
    else{
   s12+="<option value=\""+conn.rs2.getString(1)+"\">"+conn.rs2.getString(2)+"</option>";     
    }
}
}

//    SESSION 13
if(conn.rs1.getString(2).equals("13")){
regid13=conn.rs1.getString(1);
ds13=session_date;
if(conn.rs1.getInt(3)<5){disSele13="hidden";}
else{disSele13="";}
String getSigns="SELECT sign_id,sign_name,image_url FROM attendance_signs ORDER BY sign_id ASC LIMIT 2";
conn.rs2=conn.st2.executeQuery(getSigns);
while(conn.rs2.next()){
    if(conn.rs2.getString(1).equals(conn.rs1.getString(3))){
 s13+="<option value=\""+conn.rs2.getString(1)+"\" selected>"+conn.rs2.getString(2)+"</option>";   
    img13=conn.rs2.getString(3);
    }
    else{
   s13+="<option value=\""+conn.rs2.getString(1)+"\">"+conn.rs2.getString(2)+"</option>";     
    }
}
}
if(attended9>0){
//    s9status="<br><hr><select style=\"border-color:green; width: 60px;font-size: 12px; \" name=\"subsequent_"+positioner+"\" id=\"subsequent_"+positioner+"\">"
//            + "<option value=\"2\" selected>Subsequent Marking</option>"
//            + "<option value=\"1\">Editing</option>"
//            + "</select>";
    
s9status="<br><hr>Marked";
}
client_found++;
}
if(client_found==0){
regid1=regid2=regid3=regid4=regid5=regid6=regid7=regid8=regid9=regid10=regid11=regid12=regid13="0";
s1=s2=s3=s4=s5=s6=s7=s8=s9=s10=s11=s12=s13="<option value=\"\">Choose Status</option><option value=\"1\">PRESENT</option><option value=\"2\">ABSENT</option>";
ds1=ds2=ds3=ds4=ds5=ds6=ds7=ds8=ds9=ds10=ds11=ds12=ds13="";
img1=img2=img3=img4=img5=img6=img7=img8=img9=img10=img11=img12=img13="image/warning.png"; 
}

//END OF CLIENT DETAILS=============

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
        abn.setS1(s1);abn.setS2(s2);abn.setS3(s3);abn.setS4(s4);abn.setS5(s5);abn.setS6(s6);abn.setS7(s7);
        abn.setS8(s8);abn.setS9(s9);abn.setS10(s10);abn.setS11(s11);abn.setS12(s12);abn.setS13(s13);
        abn.setDs1(ds1);abn.setDs2(ds2);abn.setDs3(ds3);abn.setDs4(ds4);
        abn.setDs5(ds5);abn.setDs6(ds6);abn.setDs7(ds7);abn.setDs8(ds8);abn.setDs9(ds9);
        abn.setDs10(ds10);abn.setDs11(ds11);abn.setDs12(ds12);abn.setDs13(ds13);
        abn.setRegid1(regid1);abn.setRegid2(regid2);abn.setRegid3(regid3); abn.setRegid4(regid4);abn.setRegid5(regid5);
        abn.setRegid6(regid6);abn.setRegid7(regid7);abn.setRegid8(regid8);abn.setRegid9(regid9);
        abn.setRegid10(regid10);abn.setRegid11(regid11);abn.setRegid12(regid12);abn.setRegid13(regid13);
        abn.setImg1(img1);
        abn.setImg2(img2);
        abn.setImg3(img3);
        abn.setImg4(img4);
        abn.setImg5(img5);
        abn.setImg6(img6);
        abn.setImg7(img7);
        abn.setImg8(img8);
        abn.setImg9(img9);
        abn.setImg10(img10);
        abn.setImg11(img11);
        abn.setImg12(img12);
        abn.setImg13(img13);
        abn.setS9special(s9status);
        
        abn.setDisSele1(disSele1);
        abn.setDisSele2(disSele2);
        abn.setDisSele3(disSele3);
        abn.setDisSele4(disSele4);
        abn.setDisSele5(disSele5);
        abn.setDisSele6(disSele6);
        abn.setDisSele7(disSele7);
        abn.setDisSele8(disSele8);
        abn.setDisSele9(disSele9);
        abn.setDisSele10(disSele10);
        abn.setDisSele11(disSele11);
        abn.setDisSele12(disSele12);
        abn.setDisSele13(disSele13);
total_clients++;
clientale.add(abn);




}
    }    
    }
    
     session.setAttribute("total_clients", positioner);
        
 if(conn.rs!=null){
            conn.rs.close();
            }
if(conn.st!=null){
            conn.st.close();
            }
        session.setAttribute("total_clients", positioner);
        session.setAttribute("individual", clientale);
        session.setAttribute("signs_are", signs_are);
        response.sendRedirect("markIndividualRegister.jsp");
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
        Logger.getLogger(LoadIndividualRegister.class.getName()).log(Level.SEVERE, null, ex);
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
        Logger.getLogger(LoadIndividualRegister.class.getName()).log(Level.SEVERE, null, ex);
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
