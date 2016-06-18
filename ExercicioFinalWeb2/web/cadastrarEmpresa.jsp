<%-- 
    Document   : cadastrarEmpresa
    Created on : 16/06/2016, 20:43:11
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
        <form method="POST" action="EmpresaController?action=cadastrarEmpresa">
            Razao <input type="text" name="razao" value="${empresa.razao}" required="required" /></br></br>
            CNPJ <input type="text" name="cnpj" value="${empresa.cnpj}" required="required" /></br></br>
            Endereço <input type="text" name="endereco" value="${empresa.endereco}" required="required" /></br></br>
            Email <input type="text" name="email" value="${empresa.email}" required="required"/></br></br>
            <input type="submit" value="Cadastrar" /></br>
        </form>
        <a href="portal.jsp">Voltar</a> 
    </body>
</html>
