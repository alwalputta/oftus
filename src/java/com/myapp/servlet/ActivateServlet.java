/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myapp.servlet;

import com.myapp.admin.User;
import com.myapp.admin.UserDAO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;

/**
 *
 * @author palwal
 */
public class ActivateServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    static final Logger logger = Logger.getLogger(ActivateServlet.class);

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter po = response.getWriter();
        po.print("You have successfully validated your account");

        String userId = request.getParameter("userId");
        logger.debug("username:" + userId);
        po.println("HELLO TEST");
        logger.debug("RegisterAction activateProfile!");

        HttpSession session = request.getSession();

        UserDAO userDAO = new UserDAO();
        User user = (User) userDAO.selectUser(userId);

        if (user == null) {
            logger.debug("Userid does not exists in our system; Please register with your valid email");
            getServletContext().getRequestDispatcher("/register_form").forward(request, response);
        } else {
            user.setStatus("A");
            userDAO.updateUser(user);
            logger.debug("userid:" + user.getUserId());
            getServletContext().getRequestDispatcher("/myapp/index").forward(request, response);
        }
        po.close();
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
        processRequest(request, response);
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
