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
public class add_a_provider extends HttpServlet {
HttpSession session;
String partner_id,district_id,group_id,to_be_added;
String fname,mname,lname,phone,existing_group_ids,provider_id,a_provider_id;
int y=0;
String prov="";
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
   session=request.getSession();
   dbConn conn = new dbConn();
   provider_id=existing_group_ids=prov="";
   fname=request.getParameter("fname");
   mname=request.getParameter("mname");
   lname=request.getParameter("lname");
   phone=request.getParameter("phone");
   to_be_added=",";
   prov=request.getParameter("prov");
   if(prov!=null){
    String   full_name="";
    String provider_selector="SELECT * FROM service_provider WHERE provider_id='"+prov+"'";
           conn.rs=conn.st.executeQuery(provider_selector);
         if(conn.rs.next()==true){
               fname=conn.rs.getString(2);
               mname=conn.rs.getString(3);
               lname=conn.rs.getString(4);
               if(mname.equals(lname)){
                   mname="";
               }
          full_name=fname+" "+mname+" "+lname;  
           }    
       
   session.setAttribute("provider_name", full_name);
   session.setAttribute("provider", prov);
   }
   else{
   y=0;
   fname=fname.toUpperCase().replace("'","");
   if(mname.equals("")){
       mname=lname;
   }
    mname=mname.toUpperCase().replace("'","");
    lname=lname.toUpperCase().replace("'","");
    System.out.println("group id ggggggg: "+session.getAttribute("group_id"));
    if(session.getAttribute("partner_id")!=null && session.getAttribute("district_id")!=null && session.getAttribute("group_id")!=null){
//      ADD PROVIDER
IdGenerator ig = new IdGenerator();
partner_id=session.getAttribute("partner_id").toString();
district_id=session.getAttribute("district_id").toString();
group_id=session.getAttribute("group_id").toString();
System.out.println("details exist.");
String check_existence="SELECT * FROM service_provider WHERE (phone='"+phone+"' && phone!='') || (mname='"+mname+"' && fname='"+fname+"' && lname='"+lname+"')";
conn.rs=conn.st.executeQuery(check_existence);
if(conn.rs.next()==true){
  existing_group_ids=conn.rs.getString(6);
  provider_id=conn.rs.getString(1);   
}
if(provider_id.equals("")){
    System.out.println("gt  :  "+group_id);
    provider_id=ig.current_id();
    group_id=","+group_id+",";
    System.out.println("gt  :  "+group_id);
    String add_provider="INSERT INTO service_provider (provider_id,fname,mname,lname,phone,group_ids,partner_id,district_id,timestamp) VALUES('"+provider_id+"','"+fname+"' , '"+mname+"' , '"+lname+"' , '"+phone+"', '"+group_id+"','"+partner_id+"' , '"+district_id+"','"+ig.toDay()+"')";
   System.out.println(add_provider); 
  int num=conn.st.executeUpdate(add_provider);
  System.out.println("then num :;;      "+num);
}
else if (!provider_id.equals("")){
 to_be_added=existing_group_ids;
 if(!existing_group_ids.contains(","+group_id+",")){
 to_be_added+=group_id+",";      
}
    
 y++;   

to_be_added=to_be_added.replace(",,", ",");

String update_providers="UPDATE service_provider SET group_ids='"+to_be_added+"' WHERE provider_id='"+provider_id+"'";
conn.st.executeUpdate(update_providers);
}

    System.out.println("group id for provider:"+to_be_added);
    System.out.println("group id for provider:"+group_id);
  if(!mname.equals(lname)){
        session.setAttribute("provider_name", fname+" "+mname+" "+lname);
    }
    if(mname.equals(lname)){
        session.setAttribute("provider_name", fname+" "+lname);
    }
    session.setAttribute("provider", provider_id);
    System.out.println("provider id iss    :   "+session.getAttribute("provider").toString());
    }}
   
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

 response.sendRedirect("add_a_client.jsp");       
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
            Logger.getLogger(add_a_provider.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(add_a_provider.class.getName()).log(Level.SEVERE, null, ex);
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
