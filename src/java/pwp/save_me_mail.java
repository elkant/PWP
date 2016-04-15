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
public class save_me_mail extends HttpServlet {
HttpSession session;
String mail,countyid,partnerid,county,partner,urmail;
int found,mail_id;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
    session=request.getSession();
    found=mail_id=0;
    mail="";urmail="";
    partnerid=request.getParameter("partner");
    countyid=request.getParameter("county");
    dbConn conn = new dbConn();
    mail=request.getParameter("mail");
    urmail=request.getParameter("urmail");
    
    System.out.println("me mail : "+mail+" urmail: "+urmail);
    String getcounty="SELECT county_name FROM county WHERE county_id='"+countyid+"'";
    conn.rs=conn.st.executeQuery(getcounty);
    while(conn.rs.next()){
        county=conn.rs.getString(1);
    }
    
    String getpartner="SELECT partner_name FROM partner WHERE partner_id='"+partnerid+"'";
    conn.rs=conn.st.executeQuery(getpartner);
    while(conn.rs.next()){
        partner=conn.rs.getString(1);
    }
    String m_selector="SELECT mail_id FROM mail";
    conn.rs=conn.st.executeQuery(m_selector);
    if(conn.rs.next()==true){
    mail_id=conn.rs.getInt(1);    
    }
    if(mail_id>0){
        String update="UPDATE mail SET mail='"+mail+"',county='"+county+"',partner='"+partner+"',urmail='"+urmail+"' WHERE mail_id='"+mail_id+"' ";
        conn.st.executeUpdate(update);
        session.setAttribute("mail", "<font color=\"green\">Mail Updated Successfully.</font>");
    }
    if(mail_id==0){
        String update="INSERT INTO mail SET mail_id='1',mail='"+mail+"',county='"+county+"',partner='"+partner+"',urmail='"+urmail+"'";
        conn.st.executeUpdate(update);
        session.setAttribute("mail", "<font color=\"green\">Mail Saved Successfully.</font>");
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

    response.sendRedirect("set_email.jsp");
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
            Logger.getLogger(save_me_mail.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(save_me_mail.class.getName()).log(Level.SEVERE, null, ex);
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
