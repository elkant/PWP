/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pwp;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
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
public class add_clerk extends HttpServlet {
HttpSession session;

String f_name,m_name,s_name,phoneno,username,password, userid,level;
boolean statuz=false;
MessageDigest m;
protected void processRequest(HttpServletRequest request, HttpServletResponse response)
throws ServletException, IOException {
        try {
            response.setContentType("text/html;charset=UTF-8");


              session=request.getSession();
//     if (session.getAttribute("userid") == null) {
//        response.sendRedirect("index.jsp");
//    } 
            userid=request.getParameter("userid");
            f_name=request.getParameter("f_name");
            m_name=request.getParameter("m_name");
            s_name=request.getParameter("s_name");
            level=request.getParameter("level");
            
            //random id generator
            
             
            
            
            
          if(request.getParameter("phoneno")!=null){  phoneno=request.getParameter("phoneno");}
          else{
          phoneno="none";
          } 
            username=request.getParameter("username");
            password=request.getParameter("pass");
                    

            //encrypt password

                            m = MessageDigest.getInstance("MD5");
                            m.update(password.getBytes(), 0, password.length());
                            String pw = new BigInteger(1, m.digest()).toString(16);
          
                            dbConn conn= new dbConn();                
                         //save details to clerk table
                           IdGenerator ig = new IdGenerator(); 
                            
                            String save="insert into clerks(clerk_id, first_name,sur_name, phone,timestamp) "
                                    + "values ('"+userid+"','"+f_name+"','"+s_name+"','"+phoneno+"','"+ig.toDay()+"')";
                        
                            
                            //save details to the usewrs table
                                String add_to_users="insert into users(userid,password,username,level,timestamp) "
                                    + "values ('"+userid+"','"+pw+"','"+username+"','"+level+"','"+ig.toDay()+"')";
                        
                            
                            String checker="select * from users where username='"+username+"'";
                            
           String useridchecker="select * from users where userid='"+userid+"'";
                             
                           conn.rs=conn.st.executeQuery(checker);
            
           //check if username is already used 
            if(!conn.rs.next())
            {
                
                //check if userid already exists
                 conn.rs=conn.st.executeQuery(useridchecker);
                
                 //if the userid exixts, generate another one
                 if(conn.rs.next()){
                     
                 double myran=generateRandomNumber(10889, 20000);
                 userid="";
                 userid=userid+myran;
                 
                 String useridchecker1="select * from users where userid='"+myran+"'";
                 //check again if the user id has been used
                 conn.rs=conn.st.executeQuery(useridchecker1);
                  
                 //******************LEVEL 2 USERID CHECKER***********//
                 
                 //check contonuously if the userid exists
                 
                 if(conn.rs.next()){
                 
                 double myran1=generateRandomNumber(generateRandomNumber(1, 9000), 30000);
                 userid="";
                 userid=userid+myran1;
                //add to clerks database 
                 conn.st.executeUpdate(save);    
             
                            //add top users table
                            conn.st.executeUpdate(add_to_users);  
                 
                 
                 }
                 //if not replicated, then save to database
                 else{
                     
                      System.out.println("worked at second level");
                     
                     
                  //add to clerks table  
                            conn.st.executeUpdate(save);    
             
                            //add top users table
                            conn.st.executeUpdate(add_to_users);  
                 
                 }
                 
                 
                 
                 }
                 //finally add to tables in database
                 else{
                 
                     
                     System.out.println("duplicate didnt exist::");
                     
                  //add to clerks table  
                            conn.st.executeUpdate(save);    
             
                            //add top users table
                            conn.st.executeUpdate(add_to_users);   
                 
                 }
                          
                            
            if(level.equals("0")) {               
            session.setAttribute("clerk_added", "<font color=\"green\">Administrator added succesfully</font>");
            }
            if(level.equals("2")){
             session.setAttribute("clerk_added", "<font color=\"green\">User added succesfully</font>");    
            }
            if(level.equals("5")) {               
            session.setAttribute("clerk_added", "<font color=\"green\">Guest added succesfully</font>");
            }
            }
            else{
            session.setAttribute("clerk_added", "<b><font color=\"red\">Sorry, That username is already used.Use a different one</font></b>");
            
            
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
          response.sendRedirect("add_clerk.jsp");              
                            
        } catch (SQLException ex) {
            Logger.getLogger(add_clerk.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(add_clerk.class.getName()).log(Level.SEVERE, null, ex);
        }


}

public int generateRandomNumber(int start, int end ){
        Random random = new Random();
        long fraction = (long) ((end - start + 1 ) * random.nextDouble());
        return ((int)(fraction + start));
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
