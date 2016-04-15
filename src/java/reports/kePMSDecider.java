/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package reports;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Geofrey Nyabuto
 */
public class kePMSDecider extends HttpServlet {
HttpSession session;
String partner_id,nextpage,year,period;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
      session=request.getSession();
      
      
    partner_id=request.getParameter("partner");
    year=request.getParameter("year");
    session.setAttribute("PepfarYear", year);
    nextpage=request.getParameter("nextpage");
    
    if(nextpage.equals("achieved")){
        nextpage=request.getParameter("nextpage1");
        if(nextpage.equals("kePMSDIC") || nextpage.equals("kePMSGroup") ){
        session.setAttribute("partnerDIC", partner_id);
    }
    }
    else if(nextpage.equals("services")){
     nextpage=request.getParameter("nextpage2");
    if(nextpage.equals("kePMSDICServices") || nextpage.equals("kePMSGroupServices") ){
        session.setAttribute("partnerDIC", partner_id);
    } 
    period=request.getParameter("period");
    session.setAttribute("period", period);
    }
    else if(nextpage.equals("Reached_OthersMessages")){
    period=request.getParameter("period").trim();
        System.out.println("period passed : "+period);
    if(period.equals("01-03")){period="1";}
    else if(period.equals("04-06")){period="2";}
    else if(period.equals("07-09")){period="3";}
    else if(period.equals("10-12")){period="4";}
    else{
      period="0";  
    }
        System.out.println("period is : "+period);
        
    session.setAttribute("period", period);   
    }
    
    
   System.out.println("next page is : "+nextpage);
           
    response.sendRedirect(nextpage);
      
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
        processRequest(request, response);
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
        processRequest(request, response);
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
