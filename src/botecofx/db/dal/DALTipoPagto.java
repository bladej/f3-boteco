package botecofx.db.dal;

import botecofx.db.entidades.TipoPagto;
import botecofx.db.util.Banco;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DALTipoPagto {
    
    
     public boolean gravar(TipoPagto tp)
    {
        String sql="insert into tipopgto (tpg_nome) values ('#1')";
        sql=sql.replaceAll("#1", tp.getNome());
        return Banco.getCon().manipular(sql);
    }
    
    public boolean alterar(TipoPagto tp)
    {
        String sql="update tipopgto set tpg_nome = '"+tp.getNome()+"' where tpg_id="+tp.getId();
        return Banco.getCon().manipular(sql);
    }
    public boolean apagar(TipoPagto tp) 
    {
        return Banco.getCon().manipular("delete from tipopgto where tpg_id="+tp.getId());
    }
    public TipoPagto get(int id)
    {
        TipoPagto tpaux=null;
        ResultSet rs=Banco.getCon().consultar("select * from TipoPagto where tpg_id="+id);
        try 
        {
            if(rs.next())
            {
                tpaux=new TipoPagto(rs.getInt("tpg_id"),rs.getString("tpg_nome"));
            }
        } 
        catch (SQLException ex) {  }
        return tpaux;    
    }
    public List<TipoPagto> get(String filtro)
    {   String sql="select * from tipopgto";
        if(!filtro.isEmpty())
            sql+=" where "+filtro;
        List <TipoPagto> auxListTp = new ArrayList();
        ResultSet rs=Banco.getCon().consultar(sql);
        try 
        {
            while(rs.next())
            {
                auxListTp.add(new TipoPagto(rs.getInt("tpg_id"),rs.getString("tpg_nome")));
            }
        } 
        catch (SQLException ex) {  }
        return auxListTp;
    }
}
