package bd.daos;

import java.sql.*;
import bd.*;
import bd.core.*;
import bd.dbos.Aluno;
import java.util.ArrayList;

public class Alunos
{
    private static ArrayList<Aluno> lista = new ArrayList<>();
    
    public static boolean cadastrado (String ra) throws Exception
    {
        boolean retorno = false;

        try
        {
            String sql;

            sql = "SELECT * " +
                  "FROM aluno_3 " +
                  "WHERE ra = ?";

            BDSQLServer.COMANDO.prepareStatement (sql);

            BDSQLServer.COMANDO.setString (1, ra);

            MeuResultSet resultado = (MeuResultSet)BDSQLServer.COMANDO.executeQuery ();

            retorno = resultado.first(); // pode-se usar resultado.last() ou resultado.next() ou resultado.previous() ou resultado.absotule(numeroDaLinha)
        }
        catch (SQLException erro)
        {
            throw new Exception ("Erro ao procurar aluno");
        }

        return retorno;
    }

    public static void incluir (Aluno aluno) throws Exception
    {
        if (aluno==null)
            throw new Exception ("Aluno nao fornecido");

        try
        {
            String sql;

            sql = "INSERT INTO aluno_3 " +
                  "(ra,NOME, email) " +
                  "VALUES " +
                  "(?,?,?)";

            BDSQLServer.COMANDO.prepareStatement (sql);

            BDSQLServer.COMANDO.setString (1, aluno.getRa());
            BDSQLServer.COMANDO.setString (2, aluno.getNome ());
            BDSQLServer.COMANDO.setString (3, aluno.getEmail());

            BDSQLServer.COMANDO.executeUpdate ();
            BDSQLServer.COMANDO.commit        ();
            
            lista.add(aluno);
        }
        catch (SQLException erro)
        {
            throw new Exception ("Erro ao inserir aluno");  
        }
    }

    public static void excluir (String ra) throws Exception
    {
        if (!cadastrado (ra))
            throw new Exception ("Nao cadastrado");

        try
        {
            String sql;

            sql = "DELETE FROM aluno_3 " +
                  "WHERE ra=?";

            BDSQLServer.COMANDO.prepareStatement (sql);

            BDSQLServer.COMANDO.setString (1, ra);

            BDSQLServer.COMANDO.executeUpdate ();
            BDSQLServer.COMANDO.commit        ();       
            
            for(int i=0; i<lista.size();)
            {
                if(lista.get(i).getRa().equals(ra))
                    lista.remove(i);
                else
                    i++;
            }
        }
        catch (SQLException erro)
        {
            throw new Exception ("Erro ao excluir aluno");
        }
    }

    public static void alterar (Aluno aluno) throws Exception
    {
        if (aluno==null)
            throw new Exception ("Aluno nao fornecido");

        if (!cadastrado (aluno.getRa()))
            throw new Exception ("Nao cadastrado");

        try
        {
            String sql;

            sql = "UPDATE aluno_3 " +
                  "SET NOME=? " +
                  ", email=? " +
                  "WHERE ra = ?";

            BDSQLServer.COMANDO.prepareStatement (sql);

            BDSQLServer.COMANDO.setString (1, aluno.getNome ());
            BDSQLServer.COMANDO.setString (2, aluno.getEmail());
            BDSQLServer.COMANDO.setString (3, aluno.getRa());

            BDSQLServer.COMANDO.executeUpdate ();
            BDSQLServer.COMANDO.commit        ();
            
            for(Aluno a: lista)
            {
                if(a.getRa().equals(aluno.getRa()))
                {
                    a.setNome(aluno.getNome());
                    a.setEmail(aluno.getEmail());
                }
            }
        }
        catch (SQLException erro)
        {
            throw new Exception ("Erro ao atualizar dados de aluno");
        }
    }

    public static Aluno getAluno (String ra) throws Exception
    {
        Aluno aluno = null;

        try
        {
            String sql;

            sql = "SELECT * " +
                  "FROM aluno_3 " +
                  "WHERE ra = ?";

            BDSQLServer.COMANDO.prepareStatement (sql);

            BDSQLServer.COMANDO.setString (1, ra);

            MeuResultSet resultado = (MeuResultSet)BDSQLServer.COMANDO.executeQuery ();

            if (!resultado.first())
                throw new Exception ("Nao cadastrado");

            aluno = new Aluno (resultado.getString("ra"),
                               resultado.getString("nome"),
                               resultado.getString("email"));
        }
        catch (SQLException erro)
        {
            throw new Exception ("Erro ao procurar aluno");
        }

        return aluno;
    }
    
    public static ArrayList<Aluno> getAlunosPorNome(String nome) throws Exception
    {
        ArrayList<Aluno> result = new ArrayList<>();
        
        try
        {
            String sql;

            sql = "SELECT * " +
                  "FROM aluno_3 " +
                  "WHERE nome = ?";

            BDSQLServer.COMANDO.prepareStatement (sql);

            BDSQLServer.COMANDO.setString (1, nome);

            MeuResultSet resultado = (MeuResultSet)BDSQLServer.COMANDO.executeQuery ();

            if (!resultado.first())
                throw new Exception ("Nao cadastrado");
            
            resultado.beforeFirst();

            while(resultado.next())
            {
                result.add(new Aluno (resultado.getString("ra"), resultado.getString("NOME"), resultado.getString("email")));
            }
        }
        catch (SQLException erro)
        {
            throw new Exception ("Erro ao procurar aluno");
        }

        return result;
    }
    
    static
    {
        System.out.print("Const1");
        MeuResultSet resultado = null;

        try
        {
            String sql;

            sql = "SELECT * " +
                  "FROM aluno_3";

            BDSQLServer.COMANDO.prepareStatement (sql);

            resultado = (MeuResultSet)BDSQLServer.COMANDO.executeQuery ();
            
            resultado.beforeFirst();
            
            while(resultado.next())
            {
                try
                {
                    Alunos.lista.add(new Aluno(resultado.getString("ra"), resultado.getString("nome"), resultado.getString("email")));
                }
                catch(Exception e)
                {
                    e.printStackTrace();
                }
            }
        }
        catch (SQLException erro)
        { 
            erro.printStackTrace();
        }
    }

    public static ArrayList<Aluno> getAlunos () throws Exception
    {
        return Alunos.lista;
    }
}