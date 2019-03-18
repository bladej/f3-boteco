package botecofx.db.dal;

import botecofx.db.entidades.Categoria;
import botecofx.db.util.Banco;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class DALCategoria {
     public boolean gravar(Categoria cat)
    {
        String sql="insert into categoria (cat_nome) values ('#1')";
        sql=sql.replaceAll("#1", cat.getNome());
        return Banco.getCon().manipular(sql);
    }
    
    public boolean alterar(Categoria cat)
    {
        String sql="UPDATE categoria SET cat_nome = '"+cat.getNome()+"' WHERE cat_id = "+cat.getId();
               
        return Banco.getCon().manipular(sql);
    }
    public boolean apagar(Categoria cat) 
    {
        return Banco.getCon().manipular("delete from categoria where cat_id="+cat.getId());
    }
    public Categoria get(int id)
    {
        Categoria cataux=null;
        ResultSet rs=Banco.getCon().consultar("select * from categoria where cat_id="+id);
        try 
        {
            if(rs.next())
            {
                cataux=new Categoria(rs.getInt("cat_id"),rs.getString("cat_nome"));
            }
        } 
        catch (SQLException ex) {  }
        return cataux;    
    }
    public List<Categoria> get(String filtro)
    {   String sql="select * from categoria";
        if(!filtro.isEmpty())
            sql+=" where "+filtro;
        List <Categoria> auxListCat = new ArrayList();
        ResultSet rs=Banco.getCon().consultar(sql);
        try 
        {
            while(rs.next())
            {
                auxListCat.add(new Categoria(rs.getInt("cat_id"),rs.getString("cat_nome")));
            }
        } 
        catch (SQLException ex) {  }
        return auxListCat;
    }
    
}
