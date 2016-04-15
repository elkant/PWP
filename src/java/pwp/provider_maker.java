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
public class provider_maker extends HttpServlet {
HttpSession session;
String groups,all_rows,rws,district_id,partner_id;
int no_of_providers;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        session=request.getSession();
        dbConn conn = new dbConn();
       
        
        district_id=request.getParameter("district");
        partner_id=request.getParameter("partner");
        rws=request.getParameter("no_of_providers");
        
        no_of_providers=Integer.parseInt(rws);
        System.out.println("hahahahah");
        all_rows="";
         
        int q=0;
        String Partner_selector="SELECT partner_name FROM partner WHERE partner_id='"+partner_id+"'";
        conn.rs=conn.st.executeQuery(Partner_selector);
        conn.rs.next();
        String partner_name=conn.rs.getString(1);
        conn.rs.close();
        
        String district_selector="SELECT district_name FROM district WHERE district_id='"+district_id+"'";
        conn.rs=conn.st.executeQuery(district_selector);
        conn.rs.next();
        String district_name=conn.rs.getString(1);
        conn.rs.close();
        
        groups="<option value=\"\" >Choose Groups</option>";
        groups+="<option value=\"0\">INDIVIDUALS</option>";
        String group_loads="SELECT * from groups WHERE district_id='"+district_id+"' && partner_id='"+partner_id+"'";
        conn.rs=conn.st.executeQuery(group_loads);
        while(conn.rs.next()){
          groups+="<option value=\""+conn.rs.getString(1)+"\">"+conn.rs.getString(2)+"</option>";  
            q++;
        }
        
        for (int i=1;i<=no_of_providers;i++){
          all_rows+="<tr>"
                  + "<td>"+i+"</td>"
                  + "<td><select name=\"groups"+i+"\"  id=\"groups"+i+"\" class=\"textbox3\" style=\"width: 140px; height=\"60px\"\"  multiple>"
                  + ""+groups+""
                  + "</select></td>"
                  + "<td><input type=\"text\" value=\"\" name=\"fname"+i+"\"  id=\"fname"+i+"\" placeholder=\"First Name\" class=\"textbox\" style=\"width: 100px;\"   /></td>"
                  + "<td><input type=\"text\" value=\"\" name=\"mname"+i+"\"  id=\"mname"+i+"\" placeholder=\"Middle Name\" class=\"textbox\"  style=\"width: 100px;\" /> </td>"
                  + "<td><input type=\"text\" value=\"\" name=\"lname"+i+"\"  id=\"lname"+i+"\" placeholder=\"Last Name\" class=\"textbox\"  style=\"width: 100px;\" /></td>"
                  + "<td><input type=\"text\" value=\"\" name=\"phone"+i+"\" onkeypress=\"return numbers(event)\"  id=\"phone"+i+"\" placeholder=\"Phone Number\" class=\"textbox\"  style=\"width: 100px;\" pattern=\"[0-9]{10,10}\" maxlength=\"10\" onkeyup=\"return number_validator();\" /></td>"
                  + "</tr>" ; 
            
        }
        
        session.setAttribute("groups_there", q);
        session.setAttribute("all_providers", all_rows);
        session.setAttribute("no_of_providers", no_of_providers);
        session.setAttribute("partner_name", partner_name);
        session.setAttribute("district_name", district_name);
        session.setAttribute("partner_id", partner_id);
        session.setAttribute("district_id", district_id);

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
        
response.sendRedirect("add_providers2.jsp");
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
            Logger.getLogger(provider_maker.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(provider_maker.class.getName()).log(Level.SEVERE, null, ex);
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
