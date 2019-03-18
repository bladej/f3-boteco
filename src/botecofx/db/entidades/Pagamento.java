package botecofx.db.entidades;

public class Pagamento {
    
    private int id;
    private double valor;
    private TipoPagto tpg;
    private Comanda com;

    public Pagamento() {
    }

    public Pagamento(double valor, TipoPagto tpg, Comanda com) {
        this.valor = valor;
        this.tpg = tpg;
        this.com = com;
    }

    public Pagamento(int id, double valor, TipoPagto tpg, Comanda com) {
        this.id = id;
        this.valor = valor;
        this.tpg = tpg;
        this.com = com;
    }

    public int getId() {
        return id;
    }

    public double getValor() {
        return valor;
    }

    public TipoPagto getTpg() {
        return tpg;
    }

    public Comanda getCom() {
        return com;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public void setTpg(TipoPagto tpg) {
        this.tpg = tpg;
    }

    public void setCom(Comanda com) {
        this.com = com;
    }
    
    
    
    
    
}

