/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pwp;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Random;
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
public class add_clients extends HttpServlet {
HttpSession session;
int total_clients;
int found;
String clients_added="";
String partner_id, district_id;
String groupings;
String full_date;
String all_names,already_existing;
String yea,period,provider_id,nextpage,group_id,month_s;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        session=request.getSession();
        dbConn conn= new dbConn();
        
        yea=period=partner_id=district_id=nextpage=group_id="";
        Calendar cal = Calendar.getInstance();
int year=cal.get(Calendar.YEAR);
int month=cal.get(Calendar.MONTH)+1;
int date=cal.get(Calendar.DATE);
int hour = cal.get(Calendar.HOUR_OF_DAY);
int min=cal.get(Calendar.MINUTE);
full_date=year+"-"+month+"-"+date+"-"+hour+"-"+min;

all_names=already_existing="";
partner_id= district_id="";
if(session.getAttribute("total_clients")!=null){
    total_clients=Integer.parseInt(session.getAttribute("total_clients").toString());
   System.out.println("group id within add  client    :  "+session.getAttribute("group_id"));
    yea=session.getAttribute("year").toString() ;
    group_id=session.getAttribute("group_id").toString();
    partner_id=session.getAttribute("partner_id").toString();
    district_id=session.getAttribute("district_id").toString();
    System.out.println("partner_ id  :  "+partner_id+"  district   :   "+district_id);
    provider_id=session.getAttribute("provider").toString();    
    if(session.getAttribute("src").toString().equals("add_clients2.jsp")){
    nextpage="add_clients.jsp";
    }
    
    if(session.getAttribute("src").toString().equals("add_a_clients2.jsp")){
        session.setAttribute("sessions_group_id", group_id);
        session.setAttribute("sessions_group_name", session.getAttribute("group_name"));
   nextpage="load_a_session";
    System.out.println("the group id is  ;  "+session.getAttribute("sessions_group_id"));}
    
    found=0;
    clients_added="";
       for(int i=1; i<=total_clients; i++){
            String fname=request.getParameter("fname"+i);
            String mname=request.getParameter("mname"+i);
            String lname=request.getParameter("lname"+i);
            String age=request.getParameter("age"+i);
            String gender=request.getParameter("gender"+i);
            fname=fname.replaceAll("\\s+","");
      lname=lname.replaceAll("\\s+","");
      mname=mname.replaceAll("\\s+","");
      age=age.replaceAll("\\s+","");
      int ager=Integer.parseInt(age);
            if(mname==null || mname.equals("")){
                mname=lname;
            }
            fname=fname.toUpperCase().replace("'","");
            mname=mname.toUpperCase().replace("'","");
            lname=lname.toUpperCase().replace("'","");
            
            if(fname!=null && !fname.equals("")){
                if(group_id.equals("0")){
                    groupings=full_date;
                }
                 if(group_id.equals("0") && session.getAttribute("groupingsNew")!=null){
                    groupings=session.getAttribute("groupingsNew").toString();
                    session.setAttribute("groupings", session.getAttribute("groupingsNew").toString());
                }
                
                if(!group_id.equals("0")){
                    groupings=group_id;
                }
                if(group_id.equals("0") && session.getAttribute("src").toString().equals("add_a_clients2.jsp")){
                    nextpage="a_individual_session_loader";
                    session.setAttribute("groupings", groupings);
                }
//                if(!group_id.equals("0")){
//                    groupings=group_id;
//                }
           IdGenerator ig = new IdGenerator();    
                
 group_id=group_id.replace(".", "");
 group_id=group_id.replace("E", "");
//String client_checker="SELECT COUNT(client_id) FROM clients WHERE fname='"+fname+"' && mname='"+mname+"' && lname='"+lname+"'"
//        + "&& age='"+age+"' && gender='"+gender+"' && year='"+yea+"' && group_id='"+group_id+"'&& groupings='"+groupings+"' && partner_id='"+partner_id+"' && district_id='"+district_id+"'";
String client_checker="SELECT COUNT(client_id) FROM clients WHERE fname='"+fname+"' && mname='"+mname+"' && lname='"+lname+"'"
        + "&& age BETWEEN '"+(ager-3)+"' AND  '"+(ager+3)+"' ";
conn.rs=conn.st.executeQuery(client_checker);
if(conn.rs.next()==true){
  found=conn.rs.getInt(1);  
    
}
  if(found==0) {
  String client_adder="INSERT INTO clients SET client_id='"+ig.current_id()+"', fname='"+fname+"',year='"+yea+"', mname='"+mname+"',lname='"+lname+"'"
        + ", age='"+age+"', gender='"+gender+"',group_id='"+group_id+"',groupings='"+groupings+"',partner_id='"+partner_id+"' , district_id='"+district_id+"'"
          + ", lessons_attended='0',marking_status='NO',provider_id='"+provider_id+"',timestamp='"+ig.toDay()+"'";
     conn.st.executeUpdate(client_adder);    
  clients_added+=fname+",";    
  
  }   
  else{
      already_existing+=fname+" "+lname+" ,";
  }
            }
            
            
            found=0;
            }
       
       IdGenerator idg=new IdGenerator();
       if(group_id.equals("0")){
       String    nhf_id=session.getAttribute("nhf").toString();
      String insert_no_group="INSERT INTO no_group (id,name,district_id,partner_id,nhf_id,status,timestamp) VALUES('"+idg.current_id()+"','"+groupings+"','"+district_id+"','"+partner_id+"','"+nhf_id+"','NO','"+idg.toDay()+"')";
 conn.st.executeUpdate(insert_no_group);
 
       }
if(conn.rs!=null){
            conn.rs.close();
            }
if(conn.st!=null){
            conn.st.close();
            }
if(!clients_added.equals("")){
      session.setAttribute("clients_added","<font color=\"green\">" +clients_added+" Added Successfully</font>");
}
System.out.println("already existing : "+already_existing);
if(!already_existing.equals("")){
      session.setAttribute("clients_exist","<font color=\"red\">"+ already_existing+" were not added, They already exist in the system.</font>");
}
}
else{
    session.setAttribute("already_clients", "<font color=\"red\">No Client was added,Please Log Out ang Try again</font>");
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
            Logger.getLogger(add_clients.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(add_clients.class.getName()).log(Level.SEVERE, null, ex);
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
