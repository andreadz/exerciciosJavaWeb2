/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import beans.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author sdgdsgd
 */
@WebServlet(name = "FuncionarioController", urlPatterns = {"/FuncionarioController"})
public class FuncionarioController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        HttpSession session = request.getSession();
        Funcionario funcionario = new Funcionario();
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        RequestDispatcher rd = getServletContext().getRequestDispatcher("/portal.jsp");

        if (usuario == null) {
            request.setAttribute("msg", "Login e/ou senha não informados.");
            rd = getServletContext().getRequestDispatcher("/index.jsp");
        }
        else if ("buscarFuncionarios".equals(action)) {
            String nome = request.getParameter("nome") == null ? "" : request.getParameter("nome");
            funcionario.setNome(nome);
            Client client = ClientBuilder.newClient();
            //ArrayList<Funcionario> funcionarios;
            Xixi resp = client.target("http://localhost:8084/SistemaWSFinal/webresources/WsFinal/FuncionarioByNome/" + nome )
                    .request(MediaType.APPLICATION_JSON).get(Xixi.class);
            ArrayList<Funcionario> funcionarios = resp.getFuncionarios();
            
            request.setAttribute("funcionarios", funcionarios);
        }
        else if ("buscarTodos".equals(action)) {
            Client client = ClientBuilder.newClient();
            Xixi resp = client.target("http://localhost:8084/SistemaWSFinal/webresources/WsFinal/TodosFuncionarios" )
                    .request(MediaType.APPLICATION_JSON).get(Xixi.class);
            ArrayList<Funcionario> funcionarios = resp.getFuncionarios();
            //List<Funcionario> funcionarios = resp.readEntity(new GenericType<List<Funcionario>>() {});

//            response = client.target("http://localhost:8080/SistemaWSFinal/webresources/WsFinal")
//                    .request(MediaType.APPLICATION_JSON).post(Entity.json(funcionario),new GenericType<List<Funcionario>>());
//                    .request(MediaType.APPLICATION_JSON).post(Entity.json(funcionario));
//            


//List<Funcionario> funcionarios = resp.readEntity(new GenericType<List<Funcionario>>() {});

//            response = client.target("http://localhost:8080/SistemaWSFinal/webresources/WsFinal")
//                    .request(MediaType.APPLICATION_JSON).post(Entity.json(funcionario),new GenericType<List<Funcionario>>());
//                    .request(MediaType.APPLICATION_JSON).post(Entity.json(funcionario));
//         
            request.setAttribute("funcionarios", funcionarios);
            
        }
        else if ("cadastrar".equals(action)) {
            String cpf = request.getParameter("cpf");
            String nomeFuncionario = request.getParameter("nomeFuncionario");
            String endereco = request.getParameter("endereco");
            String email = request.getParameter("email");
            String celular = request.getParameter("celular");
            int cdEmpresa = Integer.parseInt(request.getParameter("razaoEmpresa"));
            funcionario.setNome(nomeFuncionario);
            funcionario.setCpf(cpf);
            funcionario.setEndereco(endereco);
            funcionario.setEmail(email);
            funcionario.setCelular(celular);
            funcionario.setCdEmpresa(cdEmpresa);

            Client client = ClientBuilder.newClient();
            Response resp = client.target("http://localhost:8084/SistemaWSFinal/webresources/WsFinal/Cadastrar")
                    .request(MediaType.APPLICATION_JSON).post(Entity.json(funcionario));
            request.setAttribute("status", resp.getStatus());
            request.setAttribute("msg", "Cadastrado com sucesso");
            //resp = client.target("http://localhost:8084/SistemaWSFinal/webresources/WsFinal/TodosFuncionarios/")
                   // .request(MediaType.APPLICATION_JSON).get();
             //List<Funcionario> funcionarios = resp.readEntity(new GenericType<List<Funcionario>>() {});
             //session.setAttribute("funcionarios", funcionarios);
        }
        else if ("solEdicao".equals(action)) {
            int codigo = Integer.parseInt(request.getParameter("cdFuncionario"));
            Client client = ClientBuilder.newClient();
            Response resp = client.target("http://localhost:8084/SistemaWSFinal/webresources/WsFinal/FuncionariosByCodigo/" + codigo )
                    .request(MediaType.APPLICATION_JSON).get();
            
            request.setAttribute("funcionario", resp.readEntity(Funcionario.class));
            rd = getServletContext().getRequestDispatcher("/editarFuncionario.jsp");
            
        }
        else if ("solDeletar".equals(action)) {
            int codigo = Integer.parseInt(request.getParameter("cdFuncionario"));
            Client client = ClientBuilder.newClient();
            Response resp = client.target("http://localhost:8084/SistemaWSFinal/webresources/WsFinal/FuncionariosByCodigo/" + codigo )
                    .request(MediaType.APPLICATION_JSON).get();
            
            request.setAttribute("funcionario", resp.readEntity(Funcionario.class));
            rd = getServletContext().getRequestDispatcher("/deletarFuncionario.jsp");            
        }
        else if ("editar".equals(action)) {
            String cpf = request.getParameter("cpf");
            String nome = request.getParameter("nome");
            String endereco = request.getParameter("endereco");
            String email = request.getParameter("email");
            String celular = request.getParameter("celular");
            int cdEmpresa = Integer.parseInt(request.getParameter("cdEmpresa"));
            int codigo = Integer.parseInt(request.getParameter("cdFuncionario"));
            funcionario.setNome(nome);
            funcionario.setCpf(cpf);
            funcionario.setEndereco(endereco);
            funcionario.setCodigo(codigo);
            funcionario.setEmail(email);
            funcionario.setCelular(celular);
            funcionario.setCdEmpresa(cdEmpresa);

            Client client = ClientBuilder.newClient();
            Response resp = client.target("http://localhost:8084/SistemaWSFinal/webresources/WsFinal/Editar")
                    .request(MediaType.APPLICATION_JSON).put(Entity.json(funcionario));
            request.setAttribute("status", resp.getStatus());
            Xixi funcs = client.target("http://localhost:8084/SistemaWSFinal/webresources/WsFinal/TodosFuncionarios" )
                    .request(MediaType.APPLICATION_JSON).get(Xixi.class);
            ArrayList<Funcionario> funcionarios = funcs.getFuncionarios();
            request.setAttribute("funcionarios", funcionarios);
            request.setAttribute("msg", "Editado com sucesso");
            //request.setAttribute("pessoa", resp.readEntity(Funcionario.class));
        }
        else if ("deletar".equals(action)) {
            String cpf = request.getParameter("cpf");
            String nome = request.getParameter("nome");
            String endereco = request.getParameter("endereco");
            String email = request.getParameter("email");
            String celular = request.getParameter("celular");
            int cdEmpresa = Integer.parseInt(request.getParameter("cdEmpresa"));
            int codigo = Integer.parseInt(request.getParameter("cdFuncionario"));
            funcionario.setNome(nome);
            funcionario.setCodigo(codigo);
            funcionario.setCpf(cpf);
            funcionario.setEndereco(endereco);
            funcionario.setEmail(email);
            funcionario.setCelular(celular);
            funcionario.setCdEmpresa(cdEmpresa);

            Client client = ClientBuilder.newClient();
            Response resp = client.target("http://localhost:8084/SistemaWSFinal/webresources/WsFinal/Deletar")
                    .request(MediaType.APPLICATION_JSON).put(Entity.json(funcionario));
            request.setAttribute("status", resp.getStatus());
            Xixi funcs = client.target("http://localhost:8084/SistemaWSFinal/webresources/WsFinal/TodosFuncionarios" )
                    .request(MediaType.APPLICATION_JSON).get(Xixi.class);
            ArrayList<Funcionario> funcionarios = funcs.getFuncionarios();
            request.setAttribute("funcionarios", funcionarios);
            request.setAttribute("msg", "Deletado com sucesso");
            //request.setAttribute("pessoa", resp.readEntity(Funcionario.class));
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
