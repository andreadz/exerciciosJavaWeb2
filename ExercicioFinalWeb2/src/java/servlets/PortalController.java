/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import beans.*;
import dao.*;
import javax.servlet.RequestDispatcher;

/**
 *
 * @author sdgdsgd
 */
@WebServlet(name = "PortalController", urlPatterns = {"/PortalController"})
public class PortalController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        HttpSession session = request.getSession();
        UsuarioDAO daoUsuario = new UsuarioDAO();
        Usuario usuario = new Usuario();
        Usuario usuarioSessao = (Usuario) session.getAttribute("usuario");
        RequestDispatcher rd = getServletContext().getRequestDispatcher("/index.jsp");
        if (usuarioSessao == null) {
            request.setAttribute("msg", "Login e/ou senha não informados.");
            rd = getServletContext().getRequestDispatcher("/index.jsp");
        }
        if ("cadastrarUsuario".equals(action)) {
            String nome = request.getParameter("nome");
            String login = request.getParameter("login");
            String senha = request.getParameter("senha");
            usuario.setNome(nome);
            usuario.setLogin(login);
            usuario.setSenha(senha);
            daoUsuario.cadastrarUsuario(usuario);
            request.setAttribute("msg", "Cadastro realizado com sucesso.");
        }
        rd.forward(request, response);
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
