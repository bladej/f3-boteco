package botecofx.db.entidades;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

public class Comanda 
{
   //inner class
    public class Item
    {
       private Produto p;
       private int quant;
       private double valor; //valor unitario;

        public Item(Produto p, int quant, double valor) {
            this.p = p;
            this.quant = quant;
            this.valor = valor;
        }

        public Produto getP() {
            return p;
        }

        public void setP(Produto p) {
            this.p = p;
        }

        public int getQuant() {
            return quant;
        }

        public void setQuant(int quant) {
            this.quant = quant;
        }

        public double getValor() {
            return valor;
        }

        public void setValor(double valor) {
            this.valor = valor;
        }
       
    }
    public class Pagamento
    {
        private double valor;
        private TipoPagto tipo;

        public Pagamento(double valor, TipoPagto tipo) {
            this.valor = valor;
            this.tipo = tipo;
        }

        public double getValor() {
            return valor;
        }

        public void setValor(double valor) {
            this.valor = valor;
        }

        public TipoPagto getTipo() {
            return tipo;
        }

        public void setTipo(TipoPagto tipo) {
            this.tipo = tipo;
        }
        
    }
  
  
    private int cod;
    private Garcon gar;
    private int num;
    private String nome;
    private Date data;
    private String desc;
    private double valor;
    private char status;
    private List <Item> itens;
    private List <Pagamento> pagamentos;
    
    

    public Comanda() {
        this(0,null,0,"",null,"",0,' ');
    }

    public Comanda(int cod, Garcon gar, int num, String nome, Date data, String desc, double valor, char status) {
        this.cod = cod;
        this.gar = gar;
        this.num = num;
        this.nome = nome;
        this.data = data;
        this.desc = desc;
        this.valor = valor;
        this.status = status;
    }

    public Comanda(Garcon gar, int num, String nome, Date data, String desc, double valor, char status) {
        this.gar = gar;
        this.num = num;
        this.nome = nome;
        this.data = data;
        this.desc = desc;
        this.valor = valor;
        this.status = status;
    }

    public int getCod() {
        return cod;
    }

    public void setCod(int cod) {
        this.cod = cod;
    }

    public Garcon getGar() {
        return gar;
    }

    public void setGar(Garcon gar) {
        this.gar = gar;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public char getStatus() {
        return status;
    }

    public void setStatus(char status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return nome;
    }
    public void addProduto(Produto p, int q)
    {
        itens.add(new Item(p, q, p.getPreco()));
    }
    public void addPagamento(double valor, TipoPagto tp)
    {
        pagamentos.add(new Pagamento(valor, tp));
    }
    
}