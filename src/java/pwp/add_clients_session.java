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
public class add_clients_session extends HttpServlet {
HttpSession session;
String group_id,group_name,clients,nextpage="";
String year,period,district,partner,provider,nhf,src,cat,month,chosen_session,chosen_session_1,chosen_sessioner=",";;
String chossen_sessions [];
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
session=request.getSession();
dbConn conn=new dbConn();
chosen_session_1=",";
chosen_sessioner=",s";
chosen_session="";
nhf="";
//nextpage="load_no_clients";
//cat=request.getParameter("cat");
clients=request.getParameter("no_of_clients");
partner=request.getParameter("partner");
district=request.getParameter("district");
provider=request.getParameter("provider");
year=request.getParameter("year");
chossen_sessions=request.getParameterValues("chosen_session");
group_id=request.getParameter("group");
src=request.getParameter("src");
session.setAttribute("src", src);
if(request.getParameterValues("chosen_session")!=null){
for (int i=0;i<chossen_sessions.length;i++){
    if(!chossen_sessions[i].equals("") && chossen_sessions[i]!=null){
         chosen_session+=","+chossen_sessions[i];
         chosen_session_1+= chossen_sessions[i]+","; 
         chosen_sessioner+= chossen_sessions[i]+",s"; 
            }
}

session.setAttribute("chosen_session", chosen_session);
session.setAttribute("chosen_session_1", chosen_session_1);
session.setAttribute("marked_sessions", chosen_session_1);
        
System.out.println("group id iss : "+group_id);
if(src.equals("add_clients2.jsp")){
session.setAttribute("group_id", group_id);
session.setAttribute("provider", provider);
session.setAttribute("partner_id", partner);
session.setAttribute("district_id", district);
}
session.setAttribute("year", year);

if(src.equals("add_a_clients2.jsp")){
 session.setAttribute("src", src);
 if(session.getAttribute("group_id").toString().equals("0")){
//   nextpage= "choose_individual";
 nextpage="load_a_session";   
 }
 if(!session.getAttribute("group_id").toString().equals("0")){
  session.setAttribute("sessions_group_id", session.getAttribute("group_id"));
        session.setAttribute("sessions_group_name", session.getAttribute("group_name"));
   nextpage="load_a_session";
 }
}
System.out.println("group id is  :  "+group_id);

if(group_id!=null){
if(!group_id.equals("0")){
String group_selector="SELECT * FROM groups WHERE group_id='"+group_id+"'";
conn.rs=conn.st.executeQuery(group_selector);
conn.rs.next();

group_name=conn.rs.getString(2);
}
String dist_name="SELECT * FROM district WHERE district_id='"+district+"'";
conn.rs=conn.st.executeQuery(dist_name);
if(conn.rs.next()==true){
    session.setAttribute("district_name", conn.rs.getString(3));
}
String part_name="SELECT * FROM partner WHERE partner_id='"+partner+"'";
conn.rs=conn.st.executeQuery(part_name);

if(conn.rs.next()==true){
    session.setAttribute("partner_name", conn.rs.getString(2));
}


if(group_id.equals("0")){
    nextpage="viewIndividuals.jsp";
   nhf=request.getParameter("nhf"); 
 group_name="INDIVIDUAL SESSIONS";  
 session.setAttribute("nhf", nhf);
}
session.setAttribute("group_name", group_name);
}
session.setAttribute("no_of_clients", clients);

if(conn.rs!=null){
            conn.rs.close();
            }
if(conn.st!=null){
            conn.st.close();
            }
}
else{
    nextpage="add_a_client.jsp";
    session.setAttribute("chosen_mess_error", "<font color=\"red\">Error With the chosen messages.</font>");
}
if(session.getAttribute("group_id").toString().equals("0")){
    nextpage="viewIndividuals.jsp";
}
System.out.println("ind id : "+group_id);

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
            Logger.getLogger(add_clients_session.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(add_clients_session.class.getName()).log(Level.SEVERE, null, ex);
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
