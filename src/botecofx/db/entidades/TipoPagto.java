package botecofx.db.entidades;

public class TipoPagto {
    
    private int id;
    private String nome;

    public TipoPagto() {
        this(0,"");
    }

    public TipoPagto(String nome) {
        this.nome = nome;
    }

    public TipoPagto(int id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return nome;
    }
    
    
}
