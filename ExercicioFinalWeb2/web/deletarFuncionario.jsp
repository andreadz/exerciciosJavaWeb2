<%-- 
    Document   : deletarUsuario
    Created on : 16/06/2016, 20:43:42
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
        <form method="POST" action="FuncionarioController?action=deletar">
            Código &nbsp;<input type="text" name="cdFuncionario" value="${funcionario.codigo}" readonly="true"  /></br></br>
            Nome &nbsp;<input type="text" name="nome" value="${funcionario.nome}" readonly="true" /></br></br>
            CPF &nbsp; <input type="text" name="cpf" value="${funcionario.cpf}" readonly="true" /></br></br>
            Endereço &nbsp;<input type="text" name="endereco" value="${funcionario.endereco}" readonly="true" /></br></br>
            Email &nbsp;<input type="text" name="email" value="${funcionario.email}" readonly="true" /></br></br>
            Celular &nbsp; <input type="text" name="celular" value="${funcionario.celular}" readonly="true" /></br></br>
            Empresa &nbsp; <input type="text" name="cdEmpresa" value="${funcionario.cdEmpresa}" readonly="true" /></br></br>
            
            <input type="submit" value="Excluir" /></br>
        </form>
        <a href="portal.jsp">Voltar</a>
    </body>
</html>
