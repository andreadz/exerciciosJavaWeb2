<%-- 
    Document   : cadastrarUsuario
    Created on : 16/06/2016, 20:43:05
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
        <form method="POST" action="FuncionarioController?action=cadastrar">
            Nome &nbsp;<input type="text" name="nomeFuncionario"  required="required" /></br></br>
            CPF &nbsp; <input type="text" name="cpf"  required="required" /></br></br>
            Endereço &nbsp;<input type="text" name="endereco"  required="required" /></br></br>
            Email &nbsp;<input type="text" name="email"  required="required" /></br></br>
            Celular &nbsp; <input type="text" name="celular"  required="required" /></br></br>
            Empresa &nbsp;
            <select name="razaoEmpresa">
                <c:forEach items="${empresas}" var="empCad" >
                    <option value="${empCad.codigo}">${empCad.razao}</option>
                </c:forEach>
            </select>
            <input type="submit" value="Cadastrar" /></br>
        </form>
        <a href="portal.jsp">Voltar</a> 
    </body>
</html>
