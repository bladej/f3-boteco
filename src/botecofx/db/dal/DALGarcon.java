package botecofx.db.dal;

import botecofx.db.entidades.Garcon;
import botecofx.db.util.Banco;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;

public class DALGarcon {
     public boolean gravar(Garcon gar)
    {
        
        String sql="insert into garcon (gar_nome,gar_cpf,gar_cep,gar_endereco,gar_cidade,gar_uf,gar_fone,gar_foto) values ('#1',#2,#3,'#4','#5','6#','#7',#8)";
        sql = sql.replaceAll("#1", gar.getNome());
        sql = sql.replaceAll("#2", "" + gar.getCpf());
        sql = sql.replaceAll("#3", "" + gar.getCep());
        sql = sql.replaceAll("#4", gar.getEndereco());
        sql = sql.replaceAll("#5", gar.getCidade());
        sql = sql.replaceAll("#6", gar.getUf());
        sql = sql.replaceAll("#7", gar.getFone());
        sql = sql.replaceAll("#8", "" + gar.getFoto());
        return Banco.getCon().manipular(sql);
    }
    
    public boolean alterar(Garcon gar)
    {
        String sql="update garcon set gar_nome = '"+gar.getNome()+"',"
                + " gar_cpf = '"+gar.getCpf()+"',gar_cep = '"+gar.getCep()+"',"
                + " gar_endereco = '"+gar.getEndereco()+"',gar_cidade ='"+gar.getCidade()+"',"
                + " gar_uf ='"+gar.getUf()+"',gar_fone ='"+gar.getFone()+"'"
                + " gar_foto = "+gar.getFoto()+" where gar_id="+gar.getId();              
        return Banco.getCon().manipular(sql);
    }
    public boolean apagar(Garcon gar) 
    {
        return Banco.getCon().manipular("delete from garcon where gar_id="+gar.getId());
    }
    public Garcon get(int id)
    {
        Garcon garaux=null;
        ResultSet rs=Banco.getCon().consultar("select * from garcon where gar_id="+id);
        try 
        {
            if(rs.next())
            {              
                garaux= new Garcon(rs.getInt("gar_id"),rs.getLong("gar_cpf"),rs.getLong("gar_cep"),
                        rs.getString("gar_uf"),rs.getString("gar_fone"),rs.getString("gar_cidade"),
                        rs.getString("gar_endereco"),rs.getString("gar_nome"),rs.getBytes("gar_foto"));
            }
        } 
        catch (SQLException ex) {  }
        return garaux;    
    }
    
    
    public List<Garcon> get(String filtro)
    {   String sql="select *from garcon";
        if(!filtro.isEmpty())
            sql+=" where "+filtro;
        List <Garcon> auxListGar = new ArrayList();
        ResultSet rs=Banco.getCon().consultar(sql);
        try 
        {
            while(rs.next())
            {
                auxListGar.add(new Garcon(rs.getInt("gar_id"),rs.getLong("gar_cpf"),rs.getLong("gar_cep"),
                        rs.getString("gar_uf"),rs.getString("gar_fone"),rs.getString("gar_cidade"),
                        rs.getString("gar_endereco"),rs.getString("gar_nome"),rs.getBytes("gar_foto")));
            }
        } 
        catch (SQLException ex) {  }
        return auxListGar;
    }
    public boolean salvarImagem(int cod, BufferedImage img)
    {
        try
        {
           PreparedStatement st = Banco.getCon().getConnect().prepareStatement(
                "update garcon set gar_foto =  ? where gar_id=?");
           ByteArrayOutputStream baos = new ByteArrayOutputStream();
           ImageIO.write(img, "jpg", baos);
           InputStream is = new ByteArrayInputStream(baos.toByteArray());
           st.setBinaryStream(1, is, baos.toByteArray().length);
           st.setInt(2, cod);
           st.executeUpdate();
           return true;
        }catch(Exception e)
        {System.out.println(e);}
        return false;
    }

   public boolean gravarFoto(Garcon g , FileInputStream foto)
   {
       try
       {
           PreparedStatement ps = Banco.getCon().getConnect().prepareStatement("UPDATE from garcon set gar_foto = ? where gar-id = ?");
           ps.setBinaryStream(1, foto);
           ps.setInt(2, 0);
           ps.executeUpdate();
           ps.close();
           foto.close();

       }
       catch(Exception  e)
       {
           return false;
           
       }
            
       return true ;
   }
    
    public InputStream getFoto(Garcon g)
    {
        InputStream is = null;
        try {
            PreparedStatement ps = Banco.getCon().getConnect().prepareStatement("SELECT *from garcon where gar_id = ?");
            ps.setInt(1, g.getId());
            ResultSet rs = ps.executeQuery();
            ps.close();
            if (rs.next()) {
                byte[] bytes = rs.getBytes("gar_foto");
                is = new ByteArrayInputStream(bytes);

            }
            ps.close();

        } catch (Exception e) {
            return null;

        }
        return is;
    }
    
    
    
}
