/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ClientRegistration;

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
import pwp.dbConn;

/**
 *
 * @author Geofrey Nyabuto
 */
public class checkRegisteredClients extends HttpServlet {
HttpSession session;
String status;
int clients,clientsold;
double percent;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            clientsold=clients=0;
            status="";percent=0;
           dbConn conn = new dbConn();
           String counter="SELECT COUNT(client_id) FROM personal_information";
           conn.rs=conn.st.executeQuery(counter);
           if(conn.rs.next()==true){
               clients=conn.rs.getInt(1);
           }
            String checker2="SELECT COUNT(client_id) FROM clients" ;
    conn.rs=conn.st.executeQuery(checker2);
           if(conn.rs.next()==true){
        clientsold=conn.rs.getInt(1);
        System.out.println("clients od========="+clientsold);
 
    }
//   clientsold=56;
//   clients=12;
           
          percent=(100*(clientsold-clients)/clientsold);        
   if(clients<20 && clientsold>20) {
    status="<p style=\"color:red; font-size:25px; background-color:#E3E3E3;\" ><b>Sorry:</b> You Must Move the Clients from <u>old PWP registration form</u> to the <u>new registration form</u> before marking or registering any new clients.<br><br>" ;
  
    status+="Click <a href=\"copyClientDetails.jsp\" style=\"-webkit-border-radius: 2px;\n" +
"    -moz-border-radius: 2px;\n" +
"    border-radius: 2px;\n" +
"    border: solid 1px #20538D;\n" +
"    text-shadow: 0 -1px 0 rgba(0, 0, 0, 0.4);\n" +
"    -webkit-box-shadow: inset 0 1px 0 rgba(255, 255, 255, 0.4), 0 1px 1px rgba(0, 0, 0, 0.2);\n" +
"    -moz-box-shadow: inset 0 1px 0 rgba(255, 255, 255, 0.4), 0 1px 1px rgba(0, 0, 0, 0.2);\n" +
"    box-shadow: inset 0 1px 0 rgba(255, 255, 255, 0.4), 0 1px 1px rgba(0, 0, 0, 0.2);\n" +
"    background: #4479BA;\n" +
"    color: #FFF;\n" +
"    padding: 1px 2px;\n" +
"    text-decoration: none;\">"
          + ""
          + ""
          + "HERE</a> to move clients data.</p><br><br><br><br>";
    }
    else {
        status="1".trim();
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

 System.out.println("percent : "+percent);
 System.out.println("clients : "+clients+" clients old : "+clientsold);
 out.println(status);
 System.out.println(status);
        } finally {
            out.close();
        }
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
        Logger.getLogger(checkRegisteredClients.class.getName()).log(Level.SEVERE, null, ex);
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
        Logger.getLogger(checkRegisteredClients.class.getName()).log(Level.SEVERE, null, ex);
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
