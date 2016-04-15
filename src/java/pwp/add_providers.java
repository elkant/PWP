/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pwp;

import java.io.IOException;
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
public class add_providers extends HttpServlet {
HttpSession session;
String total_prov,fname,mname,lname,phone,group_ids;
int total_providers;
String [] groups;
String existing_group_ids="";
String provider_id,nextpage="";
String partner_id, district_id;
String added_new, updated_existing,existing;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
session=request.getSession();
dbConn conn= new dbConn();
group_ids=",";
        provider_id="";
        existing_group_ids=nextpage="";


        added_new= updated_existing=existing="";
total_prov=session.getAttribute("no_of_providers").toString();
total_providers=Integer.parseInt(total_prov);
if(session.getAttribute("no_of_providers")==null){
    
 session.setAttribute("failed_add_provider", "<font color=\"red\">No Provider was Registered.Please Log out and Log In TO try Again.</font>");  
}
else{
    partner_id=session.getAttribute("partner_id").toString();
    district_id=session.getAttribute("district_id").toString();
for( int i=1; i<=total_providers; i++){
    
groups=request.getParameterValues("groups"+i+"");
if(groups!=null){
for (int j=0;j<groups.length;j++){
    if(!groups[j].equals("")){
 group_ids+=groups[j]+",";  
}
}
fname=request.getParameter("fname"+i);
mname=request.getParameter("mname"+i);
lname=request.getParameter("lname"+i);
phone=request.getParameter("phone"+i);
fname=fname.replaceAll("\\s+","");
      lname=lname.replaceAll("\\s+","");
      mname=mname.replaceAll("\\s+","");
      phone=phone.replaceAll("\\s+","");
fname=fname.toUpperCase();
lname=lname.toUpperCase();
if(mname!=null){
 mname=mname.toUpperCase();      
}
if(mname==null || "".equals(mname)){
 mname=lname;  
}
IdGenerator ig = new IdGenerator();

String check_existence="SELECT * FROM service_provider WHERE phone='"+phone+"'";
conn.rs=conn.st.executeQuery(check_existence);
if(conn.rs.next()==true){
  existing_group_ids=conn.rs.getString(6);
  provider_id=conn.rs.getString(1);   
}
if(provider_id.equals("")){
    added_new+=fname+",";
    String add_provider="INSERT INTO service_provider SET provider_id='"+ig.current_id()+"', fname='"+fname+"' , mname='"+mname+"' , lname='"+lname+"' , phone='"+phone+"', group_ids='"+group_ids+"',partner_id='"+partner_id+"' , district_id='"+district_id+"',timestamp='"+ig.toDay()+"'";
    conn.st.executeUpdate(add_provider);
}
else{
String [] div_grps=group_ids.split(",");
int y=0;
String to_be_added=existing_group_ids;
while(y<div_grps.length){
 String one_id=","+div_grps[y]+",";  
 
 if(!existing_group_ids.contains(one_id)){
 to_be_added+=div_grps[y]+",";      
}
    
 y++;   
}
to_be_added=to_be_added.replace(",,", ",");

String update_providers="UPDATE service_provider SET group_ids='"+to_be_added+"' WHERE provider_id='"+provider_id+"'";
conn.st.executeUpdate(update_providers);

updated_existing+=fname+", ";
}
provider_id="";
existing_group_ids="";
group_ids=",";
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
if(!added_new.equals("")){
session.setAttribute("adder", "<font color=\"green\">"+added_new+" Service Providers were Added to the system.");
}
if(!updated_existing.equals("")){
session.setAttribute("updator", "<font color=\"blue\">"+updated_existing+" Service Providers Details were  updated successfully.");
}
response.sendRedirect("add_provider.jsp");

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
            Logger.getLogger(add_providers.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(add_providers.class.getName()).log(Level.SEVERE, null, ex);
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
