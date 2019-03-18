package botecofx.db.dal;

import botecofx.db.entidades.Unidade;
import botecofx.db.util.Banco;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DALUnidade {
    
    public boolean gravar(Unidade u)
    {
        String sql="insert into unidade (uni_nome) values ('#1')";
        sql=sql.replaceAll("#1", u.getNome());
        return Banco.getCon().manipular(sql);
    }
    
    public boolean alterar(Unidade u)
    {
        String sql="update unidade set uni_nome ='"+u.getNome()+"'where uni_id="+u.getId();
        return Banco.getCon().manipular(sql);
    }
    public boolean apagar(Unidade u)
    {
        return Banco.getCon().manipular("delete from unidade where uni_id="+u.getId());
    }
    public Unidade get(int id)
    {
        Unidade uniaux=null;
        ResultSet rs=Banco.getCon().consultar("select * from unidade where uni_id="+id);
        try 
        {
            if(rs.next())
            {
                uniaux=new Unidade(rs.getInt("uni_id"),rs.getString("uni_nome"));
            }
        } 
        catch (SQLException ex) {  }
        return uniaux;    
    }
    public List<Unidade> get(String filtro)
    {   String sql="select * from unidade";
        if(!filtro.isEmpty())
            sql+="where"+filtro;
        List <Unidade> auxListUni = new ArrayList();
        ResultSet rs=Banco.getCon().consultar(sql);
        try 
        {
            while(rs.next())
            {
                auxListUni.add(new Unidade(rs.getInt("uni_id"),rs.getString("uni_nome")));
            }
        } 
        catch (SQLException ex) {  }
        return auxListUni;
    }
    
    
}
