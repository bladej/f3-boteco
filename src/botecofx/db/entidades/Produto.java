package botecofx.db.entidades;


public class Produto 
{
    private int prod_id;
    private String nome,descr;
    private double preco;
    private Categoria cat;
    private Unidade uni;

    public Produto() {
    }
    
    public Produto(String nome, String descr, double preco, Categoria cat, Unidade uni) {
        this.nome = nome;
        this.descr = descr;
        this.preco = preco;
        this.cat = cat;
        this.uni = uni;
    }

    public Produto(int prod_id, String nome, String descr, double preco, Categoria cat, Unidade uni) {
        this.prod_id = prod_id;
        this.nome = nome;
        this.descr = descr;
        this.preco = preco;
        this.cat = cat;
        this.uni = uni;
    }

    public int getProd_id() {
        return prod_id;
    }

    public String getNome() {
        return nome;
    }

    public String getDescr() {
        return descr;
    }

    public double getPreco() {
        return preco;
    }

    public Categoria getCat() {
        return cat;
    }

    public Unidade getUni() {
        return uni;
    }

    public void setProd_id(int prod_id) {
        this.prod_id = prod_id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setDescr(String descr) {
        this.descr = descr;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public void setCat(Categoria cat) {
        this.cat = cat;
    }

    public void setUni(Unidade uni) {
        this.uni = uni;
    }

    @Override
    public String toString() {
        return nome;
    }

  
    
    
    
}
