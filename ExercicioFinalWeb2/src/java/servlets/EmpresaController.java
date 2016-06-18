/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import beans.*;
import dao.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author sdgdsgd
 */
@WebServlet(name = "EmpresaController", urlPatterns = {"/EmpresaController"})
public class EmpresaController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        HttpSession session = request.getSession();
        UsuarioDAO daoUsuario = new UsuarioDAO();
        EmpresaDAO daoEmpresa = new EmpresaDAO();
        RequestDispatcher rd = getServletContext().getRequestDispatcher("/portal.jsp");
        ArrayList<Empresa> empresas = new ArrayList();
        Usuario usuario = new Usuario();
        Empresa empresa = new Empresa();
        Usuario usuarioSessao = (Usuario) session.getAttribute("usuario");

        if (usuarioSessao == null) {
            request.setAttribute("msg", "Login e/ou senha não informados.");
            rd = getServletContext().getRequestDispatcher("/index.jsp");
        }else {            
            if ("cadastrarEmpresa".equals(action)) {
                String razao = request.getParameter("razao");
                String cnpj = request.getParameter("cnpj");
                String endereco = request.getParameter("endereco");
                String email = request.getParameter("email");
                empresa.setRazao(razao);
                empresa.setCnpj(cnpj);
                empresa.setEndereco(endereco);
                empresa.setEmail(email);
                daoEmpresa.cadastrarEmpresa(empresa);
                request.setAttribute("msg", "Cadastro realizado com sucesso.");
                empresas = daoEmpresa.todasEmpresas();
                session.setAttribute("empresas", empresas);
            }
            else if ("selecionarEditar".equals(action)) {
               int codigo = Integer.parseInt(request.getParameter("codigo"));
                empresa = daoEmpresa.empresaByCodigo(codigo);
                request.setAttribute("empresa", empresa);
                rd = getServletContext().getRequestDispatcher("/editarEmpresa.jsp");
            }
            else if ("selecionarExcluir".equals(action)) {
               int codigo = Integer.parseInt(request.getParameter("codigo"));
                empresa = daoEmpresa.empresaByCodigo(codigo);
                request.setAttribute("empresa", empresa);
                rd = getServletContext().getRequestDispatcher("/deletarEmpresa.jsp");
            }
            else if ("editarEmpresa".equals(action)) {
                String razao = request.getParameter("razao");
                int codigo = Integer.parseInt(request.getParameter("codigo"));
                String cnpj = request.getParameter("cnpj");
                String endereco = request.getParameter("endereco");
                String email = request.getParameter("email");
                empresa.setRazao(razao);
                empresa.setCnpj(cnpj);
                empresa.setCodigo(codigo);
                empresa.setEndereco(endereco);
                empresa.setEmail(email);
                daoEmpresa.atualizarEmpresa(empresa);
                request.setAttribute("msg", "Edição de empresa realizada com sucesso.");
                empresas = daoEmpresa.todasEmpresas();
                session.setAttribute("empresas", empresas);
            }
            else if ("deletarEmpresa".equals(action)) {
                int codigo = Integer.parseInt(request.getParameter("codigo"));
                daoEmpresa.deletar(codigo);
                request.setAttribute("msg", "Empresa deletada com sucesso.");
                empresas = daoEmpresa.todasEmpresas();
                session.setAttribute("empresas", empresas);
            }
            else if ("buscarEmpresas".equals(action)) {
                String razao =  request.getParameter("buscarEmpresa") == null ? "" :  request.getParameter("buscarEmpresa");
                empresas = daoEmpresa.empresaByRazao(razao);
                if (empresas.isEmpty()) {
                    request.setAttribute("msg", "Nenhuma empresa encontrada.");
                }
                session.setAttribute("empresas", empresas);
            }            
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
