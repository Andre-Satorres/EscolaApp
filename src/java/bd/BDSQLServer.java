package bd;

import bd.core.*;
import bd.daos.*;

public class BDSQLServer
{
    public static final MeuPreparedStatement COMANDO;

    static
    {
        System.out.print("const2");
    	MeuPreparedStatement comando = null;

    	try
        {
            System.out.print("bgdfhsfdg");
            comando =
            new MeuPreparedStatement (
            "com.microsoft.sqlserver.jdbc.SQLServerDriver",
            "jdbc:sqlserver://regulus.cotuca.unicamp.br:1433;databasename=BD17162",
            "BD17162", "andre123");
        }
        catch (Exception erro)
        {
            System.err.println ("Problemas de conexao com o BD");
            System.exit(0); // aborta o programa
        }
        
        COMANDO = comando;
    }
    
    public static void inicializar()
    {}
}