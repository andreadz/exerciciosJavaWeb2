/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import beans.Funcionario;
import beans.Xixi;
import dao.*;
import java.util.ArrayList;
import java.util.List;
import static javax.ws.rs.HttpMethod.POST;
import javax.ws.rs.POST;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * REST Web Service
 *
 * @author sdgdsgd
 */
@Path("WsFinal")
public class WSFinalResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of WSFinalResource
     */
    public WSFinalResource() {
    }

    /**
     * Retrieves representation of an instance of ws.WSFinalResource
     * @return an instance of java.lang.String
     */
    @GET
    @Path("/TodosFuncionarios")
    @Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON)
    public Xixi getTodosFuncionarios() {
        ArrayList<Funcionario> funcionarios;
        FuncionarioDAO daoFunc = new FuncionarioDAO();
        
        funcionarios = daoFunc.todosFuncionarios();
        Xixi xixi = new Xixi();
        xixi.setFuncionarios(funcionarios);
        return xixi;
    }
    
    @GET
    @Path("/FuncionarioByNome/{nome}")
    @Produces(MediaType.APPLICATION_JSON)
    public Xixi getFuncionariosByNome(@PathParam("nome") String nome) {
        ArrayList<Funcionario> funcionarios;
        FuncionarioDAO daoFunc = new FuncionarioDAO();
        
        funcionarios = daoFunc.funcionarioByNome(nome);
        Xixi xixi = new Xixi();
        xixi.setFuncionarios(funcionarios);
        return xixi;
    }
    
    @POST
    @Path("/Cadastrar") 
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response inserirFuncionario(Funcionario funcionario){
        FuncionarioDAO daoFunc = new FuncionarioDAO();
        daoFunc.cadastrarFuncionario(funcionario);
         return Response.status(Response.Status.OK).header("Access-Control-Allow-Origin", "*").build();
    }
    
    @GET
    @Path("/FuncionariosByCodigo/{codigo}")
    @Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON)
    public Response getFuncionariosByCodigo(@PathParam("codigo") int codigo) {
        Funcionario funcionario;
        FuncionarioDAO daoFunc = new FuncionarioDAO();  
        
        funcionario = daoFunc.funcionarioByCodigo(codigo);
       return Response.status(Response.Status.OK).header("Access-Control-Allow-Origin", "*").entity(funcionario).build();
    }

    @PUT
    @Path("/Editar")
    @Consumes(javax.ws.rs.core.MediaType.APPLICATION_JSON)
    public Response editarFuncionario(Funcionario funcionario){
        FuncionarioDAO daoFunc = new FuncionarioDAO();
        daoFunc.atualizarFuncionario(funcionario);
        return Response.status(Response.Status.OK).header("Access-Control-Allow-Origin", "*").build();
    }
    @PUT
    @Path("/Deletar")
    @Consumes(javax.ws.rs.core.MediaType.APPLICATION_JSON)
    public Response deletarFuncionario(Funcionario funcionario){
        FuncionarioDAO daoFunc = new FuncionarioDAO();
        daoFunc.deletar(funcionario);
        return Response.status(Response.Status.OK).header("Access-Control-Allow-Origin", "*").build();
    }
}
