<%-- 
    Document   : editarUsuario
    Created on : 16/06/2016, 20:42:47
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
        <form method="POST" action="FuncionarioController?action=editar">
            Código &nbsp;<input type="text" name="cdFuncionario" value="${funcionario.codigo}" readonly="true"  /></br></br>
            Nome &nbsp;<input type="text" name="nome" value="${funcionario.nome}" required="required" /></br></br>
            CPF &nbsp; <input type="text" name="cpf" value="${funcionario.cpf}" required="required" /></br></br>
            Endereço &nbsp;<input type="text" name="endereco" value="${funcionario.endereco}" required="required" /></br></br>
            Email &nbsp;<input type="text" name="email" value="${funcionario.email}" required="required" /></br></br>
            Celular &nbsp; <input type="text" name="celular" value="${funcionario.celular}" required="required"/></br></br>
            Empresa &nbsp; <input type="text" name="cdEmpresa" value="${funcionario.cdEmpresa}" required="required" /></br></br>
           <!-- <select name="razaoEmpresa">
                <c:forEach items="${empresas}" var="empEdicao" >
                    <option value="${empEdicao.codigo}">${empEdicao.razao}</option>
                </c:forEach>
            </select>-->
            <input type="submit" value="Editar" /></br>
        </form>
    </body>
</html>
