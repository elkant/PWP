/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pwp;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.net.InetAddress;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import tracker.autoBackUp;

/**
 *
 * @author Geofrey Nyabuto
 */
public class login extends HttpServlet {
 String uname,pass,error_login,nextPage,current_time;
    String computername;
    MessageDigest m;
    String full_name;
    HttpSession session;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException, NoSuchAlgorithmException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
           Calendar cal = Calendar.getInstance();
int year=cal.get(Calendar.YEAR);
int month=cal.get(Calendar.MONTH)+1;
int date=cal.get(Calendar.DATE);
int hour = cal.get(Calendar.HOUR_OF_DAY);
int min=cal.get(Calendar.MINUTE);
int sec=cal.get(Calendar.SECOND);
String yr,mnth,dater,hr,mn,sc,action="";
yr=Integer.toString(year);
mnth=Integer.toString(month);
dater=Integer.toString(date);
hr=Integer.toString(hour);
mn=Integer.toString(min);
sc=Integer.toString(sec);
session= request.getSession();

//____________________COMPUTER NAME____________________________________
computername=InetAddress.getLocalHost().getHostName();
System.out.println("Computer name "+computername);
session.setAttribute("computer_name", computername);

current_time=yr+"-"+mnth+"-"+dater+"-"+hr+":"+mn+":"+sc;
            //get username and password
            
           uname=request.getParameter("uname");
            
           pass=request.getParameter("pass");
           
           
           
           
           
              
        //encrypt password
            
                m = MessageDigest.getInstance("MD5");
                m.update(pass.getBytes(), 0, pass.length());
                String pw = new BigInteger(1, m.digest()).toString(16);
         
                
                //connection to database class instance
                dbConn conn = new dbConn();
               String executer="CREATE TABLE IF NOT EXISTS mail(`mail_id` int(11) NOT NULL, `mail` varchar(50) NOT NULL, PRIMARY KEY(mail_id))";
                                conn.st.executeUpdate(executer);  
                
                //query for checking user existance in the database
                String select1 = "select * from users";
                
                
                
                
//System.out.println("worked up to here 1;");
                
                   conn.rs = conn.st.executeQuery(select1);
                   
                  // System.out.println("username:"+uname+"  Password :"+pw );
                   
                   
                    while (conn.rs.next()) {
                        session.setAttribute("level", conn.rs.getString("level"));
                       String get_fullnames="SELECT * FROM clerks WHERE clerk_id='"+conn.rs.getString("userid")+"'";
         conn.rs1=conn.st1.executeQuery(get_fullnames);
         if(conn.rs1.next()==true){
           full_name=conn.rs1.getString("first_name")+" "+conn.rs1.getString("sur_name");                         
         }
         session.setAttribute("fullname", full_name); 
                        
  if (conn.rs.getString("username").equals(uname) && conn.rs.getString("password").equals(pw)) {
          
                    error_login = "";
                    if (conn.rs.getString("level").equals("0") || conn.rs.getString("level").equals("1") || conn.rs.getString("level").equals("3")) {
                     String ip=InetAddress.getLocalHost().getHostAddress(); 
                     if(conn.rs.getString("level").equals("0")){
                       session.setAttribute("level", "1");   
                     }
                      //  System.out.println("level:"+conn.rs.getString("level"));
    String inserter="insert into audit set host_comp='"+computername+" "+ip+"' , action='Logged in ',time='"+current_time+"',actor_id='"+conn.rs.getString("userid")+"'";                     
     
    //String inserter="insert into audit  (action,time,actor_id,host_comp) values ('"++"','"+"')";
    
    conn.st3.executeUpdate(inserter);                
                        //the next page to be opened based on user level
                        nextPage = "add_clerk.jsp";
                         session.setAttribute("userid", conn.rs.getString("userid"));
                        session.setAttribute("username", conn.rs.getString("username"));
                      break;  
                    }//end of admin level
                    
     
                       
                       
//****************************Clerk module**********************************************        
                       else if(conn.rs.getString("level").equals("2"))
                           

{
    // System.out.println("level 2");      
                nextPage = "clerk_main.jsp";
               
               autoBackUp backup = new autoBackUp();
               backup.CreateBackUp();
                
                session.setAttribute("userid", conn.rs.getString(1));
                session.setAttribute("username", conn.rs.getString("username"));

                //save other session details to dbase
               
                String clerk="select * from clerks";
                
                 conn.rs = conn.st.executeQuery(clerk); 
                 
                while( conn.rs.next()){
                    
                    if(conn.rs.getString("clerk_id").equals(session.getAttribute("userid"))){
                    
                     session.setAttribute("f_name", conn.rs.getString("first_name"));   
                    session.setAttribute("s_name", conn.rs.getString("sur_name")); 
                   String ip=InetAddress.getLocalHost().getHostAddress();  
                       String inserter="insert into audit set host_comp='"+computername+" "+ip+"' , action='Logged in',time='"+current_time+"',actor_id='"+conn.rs.getString("clerk_id")+"'";
                     conn.st3.executeUpdate(inserter);
                     
                break;
                    }
                
                }
error_login="<b><font color=\"red\">ooops! wrong username and / or password combination</font></b>";

 break;

} 
 
//         ^^^^^^^^^^^^^^^^ IF  USER EXIST  ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^              
                       else if(conn.rs.getString("level").equals("5"))
                       {
                        nextPage = "guest_home.jsp";
               
                
                
                session.setAttribute("userid", conn.rs.getString(1));
                session.setAttribute("username", conn.rs.getString("username"));
                
                //save other session details to dbase
               
                String guest="select * from guest";
                
                 conn.rs = conn.st.executeQuery(guest); 
                 
                while( conn.rs.next()){
                    
                    if(conn.rs.getString("user_id").equals(session.getAttribute("userid"))){
                    session.setAttribute("who", "guest");
                     session.setAttribute("f_name", conn.rs.getString("first_name"));   
                    session.setAttribute("s_name", conn.rs.getString("last_name"));
                    session.setAttribute("position", conn.rs.getString("position"));
                   String ip=InetAddress.getLocalHost().getHostAddress();  
                       String inserter="insert into audit set host_comp='"+computername+" "+ip+"' , action='Logged in(guest)',time='"+current_time+"',actor_id='"+conn.rs.getString("user_id")+"'";
                     conn.st3.executeUpdate(inserter);
                     
                break;
                    }
                
                }
error_login="<b><font color=\"red\">ooops! wrong username and / or password combination</font></b>";

 break;
    }                     
                       
                       
                       
//****************************wrong username password                        
                       else {
                       
                       nextPage = "index.jsp";
                     
                        System.out.println("third level");
                       
                     error_login="<b><font color=\"red\">ooops! wrong username and / or password combination</font></b>";
                       
                       }
                
                
                           
           
           
            
            
            
        }//end of first if

  
  else {
   
        //System.out.println("worked up to here 6;");
      
                       nextPage = "index.jsp";
                     session.setAttribute("error_login", "<b><font color=\"red\">wrong username and or password</font></b>");
                        error_login="<b><font color=\"red\">wrong username and or password</font></b>";
                       
                     System.out.println(">>"+nextPage);   
  
  }
  
  
  
                    }

if(conn.rs!=null){conn.rs.close();}
if(conn.st!=null){conn.st.close();}
if(conn.rs1!=null){conn.rs1.close();}
if(conn.st1!=null){conn.st1.close();}
if(conn.rs2!=null){conn.rs2.close();}
if(conn.st2!=null){conn.st2.close();}
if(conn.st3!=null){conn.st3.close();}
                

      if(conn.conn!=null){conn.conn.close();}
                   session.setAttribute("error_login", error_login);
                   response.sendRedirect(nextPage); 
                    
              
                           
                        
                         
                           
                 
                          
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
            Logger.getLogger(login.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(login.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(login.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(login.class.getName()).log(Level.SEVERE, null, ex);
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
