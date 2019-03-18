package botecofx.db.dal;

import botecofx.db.entidades.Comanda;
import botecofx.db.util.Banco;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DALComanda {

    public boolean gravar(Comanda com) {
        String sql = "INSERT INTO comanda(gar_id, com_numero, com_nome, com_data, com_desc, com_valor, com_status)"
                + " VALUES (#1, #2, '#3', #4, '#5', #6, #7);";
        sql = sql.replaceAll("#1", ""+com.getGar().getId());
        sql = sql.replaceAll("#2", "" + com.getNum());
        sql = sql.replaceAll("#3", com.getNome());
        sql = sql.replaceAll("#4", ""+com.getData());
        sql = sql.replaceAll("#5", com.getDesc());
        sql = sql.replaceAll("#6", ""+com.getValor());
        sql = sql.replaceAll("#7", ""+com.getStatus());
        return Banco.getCon().manipular(sql);
    }

    public boolean alterar(Comanda com) {
        String sql = "UPDATE comanda SET gar_id="+com.getGar().getId()+",com_numero="+com.getNum()+","
                + "com_nome='"+com.getNome()+"',com_data="+com.getData()+",com_desc='"+com.getDesc()+"',"
                + "com_valor="+com.getValor()+",com_status="+com.getStatus()+" WHERE com_id ="+com.getCod();

        return Banco.getCon().manipular(sql);
    }

    public boolean apagar(Comanda com) {
        return Banco.getCon().manipular("delete from comanda where com_id=" +com.getCod());
    }

    public Comanda get(int id) {
        Comanda comaux = null;
        DALGarcon garcon = new DALGarcon();
        ResultSet rs = Banco.getCon().consultar("select * from comanda where com_id=" + id);
        try {
            if (rs.next()) {
                comaux = new Comanda(rs.getInt("com_id"),garcon.get(rs.getInt("gar_id")),rs.getInt("com_numero"),
                rs.getString("com_nome"),rs.getDate("com_data"),rs.getString("com_desc"),rs.getDouble("com_valor"),
                (char) rs.getInt("com_status"));
            }
        } catch (SQLException ex) {
        }
        return comaux;
    }

    public List<Comanda> get(String filtro) {
        String sql = "select * from comanda";
        if (!filtro.isEmpty()) {
            sql += " where " + filtro;
        }
        List<Comanda> auxListCom = new ArrayList();
        DALGarcon garcon = new DALGarcon();
        ResultSet rs = Banco.getCon().consultar(sql);
        try {
            while (rs.next()) {
                auxListCom.add(new Comanda(rs.getInt("com_id"),garcon.get(rs.getInt("gar_id")),rs.getInt("com_numero"),
                rs.getString("com_nome"),rs.getDate("com_data"),rs.getString("com_desc"),rs.getDouble("com_valor"),
                (char) rs.getInt("com_status")));
            }
        } catch (SQLException ex) {
        }
        return auxListCom;
    }
}


