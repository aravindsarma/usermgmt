/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.sliqit.servlet;

import com.sqlit.dao.UserDao;
import com.sqlit.domain.User;
import com.sqlit.domain.UserList;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Aravind Sarma Yeluripati
 */
@WebServlet(name="HomeServlet", urlPatterns={"/home"})
public class HomeServlet extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        HttpSession session = request.getSession();
        String loggedInUser=null,sessionId=null;
        try{
            loggedInUser = session.getAttribute("loggedInUser").toString();
            sessionId = session.getAttribute("sessionId").toString();        
        }catch(NullPointerException e){
            request.setAttribute("errorMessage", "Invalid login, try again");
			redirect(request,response,"/WEB-INF/jsps/login.jsp");
        }
        
        if(loggedInUser == null||sessionId==null){
            request.setAttribute("errorMessage", "Invalid login, try again");
			redirect(request,response,"/WEB-INF/jsps/login.jsp");            
        }
        else if(loggedInUser.equals(sessionId)){
        UserList<User> userList;        
        UserDao ud = new UserDao();
        userList = ud.selectAll();                                
        request.setAttribute("userList", userList);        
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>HomePage</title>");  
            out.println("</head>");
            out.println("<body>");            
            out.println("<table>");
            User u;
            for(int i=0; i<userList.size(); i++){
                u = (User) userList.get(i);
                if(u.getUserId().equals(sessionId)){                    
                    out.println("<tr>"
                        +"<td>"+ u.getUserId()+"</td>"                        
                        +"<td>"+u.getFirstName()+"</td>"
                        +"<td>"+u.getLastName()+"</td>"
                        +"<td>"+u.getEmail()+"</td>"
                        +"<td><a href=\"#?userId="+u.getUserId()+"\">modify</a></td>"
                        +"</tr>"
                    );
                }else{
                    out.println("<tr>"
                        +"<td>"+ u.getUserId()+"</td>"                        
                        +"<td>"+u.getFirstName()+"</td>"
                        +"<td>"+u.getLastName()+"</td>"
                        +"<td>"+u.getEmail()+"</td>"
                        +"<td><a href=\"#?userId="+u.getUserId()+"\">modify</a></td>"
                        +"<td><a href=\"#?userId="+u.getUserId()+"\">delete</a></td>"
                        +"</tr>"
                    );
                }
            }                    
            out.println("</table>");
            out.println("<br/><a href=\"#\">Add New User</a><br/>");
            out.println("<br/><a href=\"/usermgmt/login\">User Home</a>");
            out.println("</body>");
            out.println("</html>");
        } finally { 
            out.close();
        }
        }else{
            request.setAttribute("errorMessage", "Invalid login");
			redirect(request,response,"/WEB-INF/jsps/login.jsp");
        }
    } 

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
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
    
    private void redirect(HttpServletRequest request, HttpServletResponse response, String url) throws ServletException, IOException{
		RequestDispatcher rd = request.getRequestDispatcher(url);
		rd.forward(request, response);		
	}

}
