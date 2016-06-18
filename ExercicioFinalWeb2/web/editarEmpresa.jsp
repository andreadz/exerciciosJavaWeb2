<%-- 
    Document   : editar
    Created on : 16/06/2016, 20:42:26
    Author     : sdgdsgd
--%>

<%@page import="beans.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <% Usuario usuarioSessao = (Usuario) session.getAttribute("usuario");

        if (usuarioSessao == null) {
            request.setAttribute("msg", "Login e/ou senha não informados.");
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/index.jsp");
            rd.forward(request, response);
        }
            %>
    <body>
        <form method="POST" action="EmpresaController?action=editarEmpresa">
            Código <input type="text" name="codigo" value="${empresa.codigo}" readonly="true" /></br>
            Razao <input type="text" name="razao" value="${empresa.razao}" /></br>
            CNPJ <input type="text" name="cnpj" value="${empresa.cnpj}" /></br>
            Endereço <input type="text" name="endereco" value="${empresa.endereco}" /></br>
            Email <input type="text" name="email" value="${empresa.email}" /></br>
           <input type="submit" value="Editar" /></br>
        </form>
           <a href="portal.jsp">Voltar</a> 
    </body>
</html>
