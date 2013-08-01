/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sliqit.servlet;

import com.sqlit.dao.UserDao;
import com.sqlit.domain.UserLoginInfo;
import java.io.IOException;
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
@WebServlet(name = "LoginServlet", urlPatterns = {"/login"})
public class LoginServlet extends HttpServlet {

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
        HttpSession session = request.getSession();
        String loggedInUser = null, sessionId = null;
        try {
            loggedInUser = session.getAttribute("loggedInUser").toString();
            sessionId = session.getAttribute("sessionId").toString();
        } catch (NullPointerException e) {
            request.setAttribute("errorMessage", "Invalid login");
            redirect(request, response, "/WEB-INF/jsps/login.jsp");
        }

        if (loggedInUser == null || sessionId == null) {
            request.setAttribute("errorMessage", "Invalid login");
            redirect(request, response, "/WEB-INF/jsps/login.jsp");
        } else if (loggedInUser.equals(sessionId)) {
            redirect(request, response, "/WEB-INF/jsps/home.jsp");
        } else {
            request.setAttribute("errorMessage", "Invalid login");
            redirect(request, response, "/WEB-INF/jsps/login.jsp");
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
        HttpSession session = request.getSession();


        String userId, password;
        boolean success = false;
        userId = request.getParameter("txtUserId");
        password = request.getParameter("txtPassword");
        System.out.println("userId:" + userId + "\npassword:" + password);

        UserLoginInfo<String> loginInfo;
        UserDao ud = new UserDao();
        loginInfo = ud.selectLoginInfo();
        if (loginInfo.containsKey(userId)) {
            if (loginInfo.containsValue(password)) {
                success = true;//userId and password matched
            } else {
                success = false;//userId exists, password didn't match
            }
        } else {
            success = false;//userId doesn't exist
        }

        if (success) {
            session.setAttribute("sessionId", userId);
            System.out.println("sessionId:" + session.getAttribute("sessionId").toString());
            session.setAttribute("loggedInUser", userId);
            redirect(request, response, "/WEB-INF/jsps/home.jsp");
        } else {
            request.setAttribute("errorMessage", "Invalid login");
            redirect(request, response, "/WEB-INF/jsps/login.jsp");
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Login servlet, authenticates user";
    }

    private void redirect(HttpServletRequest request, HttpServletResponse response, String url) throws ServletException, IOException {
        RequestDispatcher rd = request.getRequestDispatcher(url);
        rd.forward(request, response);
    }
}
