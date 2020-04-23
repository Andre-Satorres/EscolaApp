package br.unicamp.cotuca;

import bd.BDSQLServer;
import bd.daos.Alunos;
import bd.dbos.Aluno;
import java.util.ArrayList;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author u17162, u17184
 */
@Path("generic")
public class Escola 
{

    @Context
    private UriInfo context;
    
    /**
     * Creates a new instance of Escola
     */
    public Escola() 
    {
        BDSQLServer.inicializar();
    }
    
    @GET
    @Path("/consultarTodos")
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<Aluno> consultarTodos()
    {
        try
        {
            return Alunos.getAlunos();
        }
        catch(Exception e)
        {
            throw new WebApplicationException(404);
        }
    }
    
    @GET
    @Path("/consultaPeloRA/{ra}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Aluno consultaPeloRA(@PathParam("ra") String ra)
    {
        try
        {
            return Alunos.getAluno(ra);
        }
        catch(Exception e)
        {
            throw new WebApplicationException(404);
        }
    }
    
    @GET
    @Path("/consultaPeloNome/{nomeAluno}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<Aluno> consultaPeloNome(@PathParam("nomeAluno") String nome)
    {
        try
        {
            return Alunos.getAlunosPorNome(nome);
        }
        catch(Exception e)
        {
            throw new WebApplicationException(404);
        }
    }
    
    @POST
    @Path("/incluirAluno")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String incluirAluno(Aluno aluno)
    {
        try 
        {
            Alunos.incluir(aluno);
            return "Aluno inserido com sucesso!";
        } 
        catch (Exception ex) 
        {
            return "Erro ao inserir aluno! Já há um aluno com esse RA!";
        }
    }
    
    @PUT
    @Path("/alterarAluno")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String alterarAluno (Aluno aluno)
    {             
        try 
        {
            Alunos.alterar(aluno);
            return "Informações do aluno alteradas com sucesso!";
        } 
        catch (Exception ex) 
        {
            return "Erro ao alterar informações do aluno! Dados inválidos. Talvez esse RA não exista.";
        }
    }   
    
    @GET //get pq o DELETE esta com problema
    @Path("/excluirAluno/{ra}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String excluirAluno(@PathParam("ra") String ra)
    {
        try 
        {
            Alunos.excluir(ra);
            return "Aluno excluído com sucesso!";
        } 
        catch (Exception ex) 
        {
            ex.printStackTrace();
            return "Erro ao excluir aluno! Provavelmente este RA não existe.";
        }
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getJson() 
    {
        throw new UnsupportedOperationException();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(String content) 
    {
        
    }
}