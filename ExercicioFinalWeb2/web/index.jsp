<%-- 
    Document   : index
    Created on : 16/06/2016, 20:42:06
    Author     : sdgdsgd
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h2>Login</h2>
        <form method="POST" action="LoginController?action=login">
            Login <input type="text" name="login" maxlength="10" /></br>
            Senha <input type="password" name="senha" maxlength="10" /></br>
            <input type="submit" value="Acessar" /></br>
        </form>
        </br>
        <a href="cadastrarUsuario.jsp">Cadastrar</a>
        ${msg}
    </body>
</html>
