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
public class load_groups extends HttpServlet {
String groups;
HttpSession session;
String partner_name,district_name;
int tracker=0;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            session=request.getSession();
         String district_id=request.getParameter("district_id");
         String partner_id=request.getParameter("partner_id");
      
         tracker=0;
         groups="<option value=\"\">Choose Group </option>";
        
//        groups+="<option value=\"0\">INDIVIDUALS</option>";
        
         partner_name=district_name="";
         
         dbConn conn = new dbConn();
         
         String getALLgroups="SELECT groups.group_id,group_name,COUNT(personal_information.client_id) AS CLIENTS "
            + "FROM personal_information JOIN groups on personal_information.group_id=groups.group_id "
            + " JOIN district ON groups.district_id=district.district_id "
            + "WHERE personal_information.district_id='"+district_id+"' && personal_information.partner_id='"+partner_id+"' "
                 + " GROUP BY groups.group_id "
                    + "ORDER BY groups.group_name";
            conn.rs=conn.st.executeQuery(getALLgroups);
            while(conn.rs.next()){
          tracker++;
             groups+="<option value=\""+conn.rs.getString(1)+"\">"+conn.rs.getString(2)+"</option>";
            }
         
         
//         String district_selector="SELECT * FROM district WHERE district_id='"+district_id+"'";
//         conn.rs=conn.st.executeQuery(district_selector);
//        if(conn.rs.next()){
//            district_name=conn.rs.getString(3);
//        }
//        String partner_selector="SELECT * FROM partner WHERE partner_id='"+partner_id+"'";
//         conn.rs=conn.st.executeQuery(partner_selector);
//        if(conn.rs.next()){
//            partner_name=conn.rs.getString(2);
//        }
//        session.setAttribute("partner_name", partner_name);
//        session.setAttribute("district_name", district_name);
//        session.setAttribute("partner_id", partner_id);
//        session.setAttribute("district_id", district_id);
//        
//        
//         
//         String groups_loader="SELECT groups.group_id,groups.group_name,COUNT(personal_) FROM groups WHERE district_id='"+district_id+"' && partner_id='"+partner_id+"'";
//         conn.rs=conn.st.executeQuery(groups_loader);
//         while(conn.rs.next()){
//             tracker++;
//             groups+="<option value=\""+conn.rs.getString(1)+"\">"+conn.rs.getString(2)+"</option>";
//         }

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

if(tracker==0){
    groups="<option value=\"\">No Existing Group</option>";
}
         System.out.println("Groups are  :  "+groups);
            out.println("<html>");
            out.println("<head>");
            out.println("<title></title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>" +groups+"</h1>");
            out.println("</body>");
            out.println("</html>");
        } finally {            
            out.close();
        }
        
        
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
            Logger.getLogger(load_groups.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(load_groups.class.getName()).log(Level.SEVERE, null, ex);
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
