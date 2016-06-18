<%-- 
    Document   : deletarEmpresa
    Created on : 16/06/2016, 20:43:33
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
    <body>
        <% Usuario usuarioSessao = (Usuario) session.getAttribute("usuario");

        if (usuarioSessao == null) {
            request.setAttribute("msg", "Login e/ou senha não informados.");
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/index.jsp");
            rd.forward(request, response);
        }
            %>
         <form method="POST" action="EmpresaController?action=deletarEmpresa">
            Código <input type="text" name="codigo" value="${empresa.codigo}" readonly="true" /></br>
            Razao <input type="text" name="razao" value="${empresa.razao}" readonly="true" /></br>
            CNPJ <input type="text" name="cnpj" value="${empresa.cnpj}" readonly="true" /></br>
            Endereço <input type="text" name="endereco" value="${empresa.endereco}" readonly="true" /></br>
            Email <input type="text" name="email" value="${empresa.email}" readonly="true" /></br>
           <input type="submit" value="Excluir" /></br>
        </form>
           <a href="portal.jsp">Voltar</a> 
    </body>
</html>
