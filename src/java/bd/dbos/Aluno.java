package bd.dbos;

import java.io.Serializable;

/**
 *
 * @authors u17162, u17184
 */
public class Aluno implements Serializable
{
    private String ra;
    private String nome;
    private String email;
    
    public Aluno()
    {}

    public String getRa() 
    {
        return ra;
    }

    public void setRa(String ra) throws Exception
    {
        if(ra.length() != 5)
            throw new Exception("RA invalido!!");
        this.ra = ra;
    }

    public String getNome() 
    {
        return nome;
    }

    public void setNome(String nome) throws Exception
    {
        if(nome == null || nome.trim().equals(""))
            throw new Exception("Nome invalido!!");
        this.nome = nome;
    }

    public String getEmail() 
    {
        return email;
    }

    public void setEmail(String email) throws Exception
    {
        if(email == null || email.trim().equals(""))
            throw new Exception("Email invalido!!");
        
        this.email = email;
    }
    
    public Aluno(String ra, String nome, String email) throws Exception 
    {
        if(ra.length()!= 5 || nome == null || nome.trim().equals("") || email == null || email.trim().equals(""))
            throw new Exception("Valores invalidos!!");
        
        this.ra = ra;
        this.nome = nome;
        this.email = email;
    }
    
    @Override
    public Object clone()
    {
        try
        {
            return new Aluno(this);
        }
        catch(Exception e)
        {}
        return null;
    }
    
    public Aluno (Aluno a)
    {
        this.ra    = a.ra;
        this.nome  = a.nome;
        this.email = a.email;
    }
    
    @Override
    public String toString() 
    {
        return "Aluno{" + "ra=" + ra + ", nome=" + nome + ", email=" + email + '}';
    }

    @Override
    public int hashCode() 
    {
        int hash = 7;
        hash = 53 * hash + Integer.parseInt(this.ra);
        hash = 53 * hash + Integer.parseInt(this.nome);
        hash = 53 * hash + Integer.parseInt(this.email);
        return hash;
    }

    @Override
    public boolean equals(Object obj) 
    {
        if (this == obj) 
        {
            return true;
        }
        if (obj == null) 
        {
            return false;
        }
        if (getClass() != obj.getClass()) 
        {
            return false;
        }
        
        final Aluno other = (Aluno) obj;
        if (!(this.ra.equals(other.ra))) 
        {
            return false;
        }
        
        if (!(this.nome.equals(other.nome))) 
        {
            return false;
        }
        
        if (!(this.email.equals(other.email))) 
        {
            return false;
        }
        
        return true;
    }
}
