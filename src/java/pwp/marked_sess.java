/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pwp;

import edits.getYR;
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
public class marked_sess extends HttpServlet {
String sessions="",group_id;
int got=0,markedClients;
HttpSession session;
int prev_date,dater,foundUnmarked,allClients,added;
String today="";
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            dbConn conn = new dbConn();
            IdGenerator IG = new IdGenerator();
            today=IG.toDay();
            session=request.getSession();
            if(session.getAttribute("groupingsNew")!=null){
                session.removeAttribute("groupingsNew");
            }
//           sessions="<option value=\"\">Choose Session</option>";
            sessions="";markedClients=0;
           group_id=session.getAttribute("group_id").toString();
           
           String totalClients="SELECT COUNT(personal_information.client_id) FROM personal_information "
                 + "WHERE personal_information.group_id='"+group_id+"'";
           conn.rs=conn.st.executeQuery(totalClients);
           if(conn.rs.next()==true){
               allClients=conn.rs.getInt(1);
           }
        got=0;
        int i=1;
        while(i<=13){
            markedClients=foundUnmarked=added=0;
         if(!group_id.equals("0")){
         String get_val="SELECT COUNT(personal_information.client_id) FROM personal_information "
                 + "LEFT JOIN register ON personal_information.client_id=register.client_id"
                 + " WHERE s"+i+"<3 && personal_information.group_id='"+group_id+"'";
         conn.rs=conn.st.executeQuery(get_val);
         if(conn.rs.next()==true){
//             System.out.println(i+"found : "+conn.rs.getInt(1));
             if(conn.rs.getInt(1)>0){
              markedClients=conn.rs.getInt(1);
             }
         }
         
       String get_unmarked="SELECT COUNT(personal_information.client_id) FROM personal_information "
                 + " LEFT JOIN register ON personal_information.client_id=register.client_id"
                 + " WHERE s"+i+"='5' && personal_information.group_id='"+group_id+"' LIMIT 1";
         conn.rs=conn.st.executeQuery(get_unmarked);
         if(conn.rs.next()==true){
             
//             System.out.println(i+"found : "+conn.rs.getInt(1));
             if(conn.rs.getInt(1)>0){
              foundUnmarked=conn.rs.getInt(1);
             }
         }   
         
String message_selector="SELECT * FROM message_codes WHERE message_id="+i+"";
conn.rs1=conn.st1.executeQuery(message_selector);
if(conn.rs1.next()==true){
    System.out.println("unmarked : "+foundUnmarked+" total clients : "+markedClients);
    if((foundUnmarked>0 || markedClients<allClients) && markedClients>0 ){
      sessions+="<option value=\""+i+"\">  *  "+i+". "+conn.rs1.getString(2)+"</option>";
      added++;
      got++;
    }
    else if(markedClients==0 && foundUnmarked<=allClients ){
     System.out.println("returned clients : ");
 sessions+="<option value=\""+i+"\">  "+i+". "+conn.rs1.getString(2)+"</option>";
 added++;
 got++;
     }
    if(added==0 && i!=9){
     sessions+="<option value=\""+i+"\" disabled>  *  "+i+". "+conn.rs1.getString(2)+"</option>";   
   got++;
    }
    else if(added==0 && i==9){
     sessions+="<option value=\""+i+"\">  *  "+i+". "+conn.rs1.getString(2)+"</option>";   
   got++;
    }
} 
             }
             else{
                 
             }
        i++; 
        }   
         
 if(got==0){
String message_selector="SELECT * FROM message_codes";
conn.rs1=conn.st1.executeQuery(message_selector);
while(conn.rs1.next()){
sessions+="<option value=\""+conn.rs1.getString(1)+"\">"+conn.rs1.getInt(1)+". "+conn.rs1.getString(2)+"</option>"; 
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

 System.out.println("sessios are : "+sessions);
            /* TODO output your page here. You may use following sample code. */
            out.println("<html>");
            out.println("<head>");
            out.println("<title></title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>" +sessions+ "</h1>");
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
            Logger.getLogger(marked_sess.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(marked_sess.class.getName()).log(Level.SEVERE, null, ex);
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
