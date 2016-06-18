<%-- 
    Document   : portal
    Created on : 16/06/2016, 20:42:14
    Author     : sdgdsgd
--%>

<%@page import="beans.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <% Usuario usuarioSessao = (Usuario) session.getAttribute("usuario");

            if (usuarioSessao == null) {
                request.setAttribute("msg", "Login e/ou senha não informados.");
                RequestDispatcher rd = getServletContext().getRequestDispatcher("/index.jsp");
                rd.forward(request, response);
            }
        %>
        <h2>Portal</h2>
        Olá ${usuario.nome} &nbsp;&nbsp; <a href="LoginController?action=logout">Logout</a>  </br>

        <form method="POST" action="EmpresaController?action=buscarEmpresas">
            Empresa: <input type="text" name="buscarEmpresa" /> &nbsp; 
            <input type="submit" value="Buscar Empresa" ></br>
        </form>

        <!--<form method="POST" action="FuncionarioController?action=buscarFuncionarios">
            Funcionário <input type="text" name="nome" />&nbsp; 
            <input type="submit" value="Buscar Funcionário" ></br></br>
        </form>-->
        <form method="POST" action="FuncionarioController?action=buscarTodos">
            Funcionário  
            <input type="submit" value="Buscar Funcionário" ></br></br>
        </form>
        <a href="cadastrarEmpresa.jsp" type="button" >Nova Empresa</a>
        <a href="cadastrarFuncionario.jsp" type="button" >Novo Funcionário</a>
        <a href="RelatorioController" type="button" >Relatório Empresa</a>

        <c:if test="${empresas.size()>0}">
            <table>
                <thead>
                    <tr>
                        <th>Código</th>
                        <th>CNPJ</th>
                        <th>Razão</th>
                        <th>Endereço</th>
                        <th>E-mail</th>
                        <th colspan="2">Ação</th>
                    </tr>
                </thead>
                <tbody>                        
                    <c:forEach items="${empresas}" var="empdao">
                        <c:url var="editarEmp" value="EmpresaController?action=selecionarEditar">
                            <c:param name="codigo" value="${empdao.codigo}" />
                        </c:url>
                        <c:url var="excluirEmp" value="EmpresaController?action=selecionarExcluir">
                            <c:param name="codigo" value="${empdao.codigo}" />
                        </c:url>
                        <tr>
                            <td>${empdao.codigo}</td>
                            <td>${empdao.cnpj}</td>
                            <td>${empdao.razao}</td>
                            <td>${empdao.endereco}</td>
                            <td>${empdao.email}</td>
                            <td><a href="${editarEmp}">Editar</a></td>
                            <td><a href="${excluirEmp}">Excluir</a></td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </c:if>
        <c:if test="${funcionarios.size()>0}">                
            <table style="border: 1px;">
                <thead>
                    <tr>
                        <th>Código</th>
                        <th>CPF</th>
                        <th>Nome</th>
                        <th>Endereço</th>
                        <th>Email</th>
                        <th>Celular</th>
                        <th>código Empresa</th>
                        <th colspan="2">Ação</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${funcionarios}" var="funcs">
                        <c:url var="editarU" value="FuncionarioController?action=solEdicao">
                            <c:param name="cdFuncionario" value="${funcs.codigo}" />
                        </c:url>
                        <c:url var="excluirU" value="FuncionarioController?action=solDeletar">
                            <c:param name="cdFuncionario" value="${funcs.codigo}" />
                        </c:url>
                        <tr>
                            <td>${funcs.codigo}</td>
                            <td>${funcs.cpf}</td>
                            <td>${funcs.nome}</td>
                            <td>${funcs.endereco}</td>
                            <td>${funcs.email}</td>
                            <td>${funcs.celular}</td>
                            <td>${funcs.cdEmpresa}</td>
                            <td><a href="${editarU}">Editar</a></td>
                            <td><a href="${excluirU}">Excluir</a></td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </c:if>

        ${msg}
    </body>
</html>
