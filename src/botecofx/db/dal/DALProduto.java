package botecofx.db.dal;

import botecofx.db.entidades.Produto;
import botecofx.db.util.Banco;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class DALProduto {
    
    public boolean gravar(Produto prod)
    {
        String sql="insert into produto (cat_id,uni_id, prod_nome,prod_preco,prod_descr) values ('#1','#2','#3','#4','#5')";
        sql = sql.replaceAll("#1", ""+prod.getCat().getId());
        sql = sql.replaceAll("#2", ""+prod.getUni().getId());
        sql = sql.replaceAll("#3", prod.getNome());
        sql = sql.replaceAll("#4", "" + prod.getPreco());
        sql = sql.replaceAll("#5", prod.getDescr());

        return Banco.getCon().manipular(sql);
    }
    // erro no alterar
    public boolean alterar(Produto prod)
    {
        String sql="UPDATE produto SET cat_id="+prod.getCat().getId()+",uni_id="+prod.getUni().getId()
                +",prod_nome='"+prod.getNome()+"',prod_preco="+prod.getPreco()+",prod_descr='"+prod.getDescr()+"'"
                + "WHERE prod_id="+prod.getProd_id();          
        
        return Banco.getCon().manipular(sql);
    }
    public boolean apagar(Produto prod) 
    {
        return Banco.getCon().manipular("delete from produto where prod_id="+prod.getProd_id());
    }
    public Produto get(int id)
    {
        Produto prodaux=null;
        DALCategoria dalcat = new DALCategoria ();
        DALUnidade daluni = new DALUnidade();
        ResultSet rs=Banco.getCon().consultar("select * from produto where prod_id="+id);
        try 
        {
            if(rs.next())
            {              
                prodaux= new Produto(rs.getInt("prod_id"),rs.getString("prod_nome"),rs.getString("prod_descr"),
                        rs.getDouble("prod_preco"),dalcat.get(rs.getInt("cat_id")),
                        daluni.get(rs.getInt("uni_))id")));
            }
        } 
        catch (SQLException ex) {  }
        return prodaux;    
    }
    public List<Produto> get(String filtro)
    {   String sql="select * from produto";
        if(!filtro.isEmpty())
            sql+=" where "+filtro;
        List <Produto> auxListProd = new ArrayList();
        DALCategoria dalcat = new DALCategoria ();
        DALUnidade daluni = new DALUnidade();
        ResultSet rs=Banco.getCon().consultar(sql);
        try 
        {
            while(rs.next())
            {
                auxListProd.add(new Produto(rs.getInt("prod_id"),rs.getString("prod_nome"),rs.getString("prod_descr"),
                        rs.getDouble("prod_preco"),dalcat.get(rs.getInt("cat_id")),
                        daluni.get(rs.getInt("uni_id"))));
            }
        } 
        catch (SQLException ex) {  }
        return auxListProd;
    }
    
}
