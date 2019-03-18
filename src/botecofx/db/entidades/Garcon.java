package botecofx.db.entidades;

public class Garcon 
{
    private int id;
    private String uf,fone,cidade,endereco,nome;
    private byte[] foto;
    private long cpf,cep;

    public Garcon() {
        this(0,0,"","","","","",null);
    }

    public Garcon(long cpf, long cep, String uf, String fone, String cidade, String endereco, String nome) {
        this.uf = uf;
        this.fone = fone;
        this.cidade = cidade;
        this.endereco = endereco;
        this.nome = nome;
        this.cpf = cpf;
        this.cep = cep;
    }
    
  

    public Garcon(long cpf, long cep, String uf, String fone, String cidade, String endereco, String nome, byte[] foto) {
        this.cpf = cpf;
        this.cep = cep;
        this.uf = uf;
        this.fone = fone;
        this.cidade = cidade;
        this.endereco = endereco;
        this.nome = nome;
        this.foto = foto;
    }

    public Garcon(int id, long cpf, long cep, String uf, String fone, String cidade, String endereco, String nome, byte[] foto) {
        this.id = id;
        this.cpf = cpf;
        this.cep = cep;
        this.uf = uf;
        this.fone = fone;
        this.cidade = cidade;
        this.endereco = endereco;
        this.nome = nome;
        this.foto = foto;
    }
    
    public Garcon(int id, long cpf, long cep, String uf, String fone, String cidade, String endereco, String nome) {
        this.id = id;
        this.cpf = cpf;
        this.cep = cep;
        this.uf = uf;
        this.fone = fone;
        this.cidade = cidade;
        this.endereco = endereco;
        this.nome = nome;
        this.foto = foto;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public String getFone() {
        return fone;
    }

    public void setFone(String fone) {
        this.fone = fone;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public byte[] getFoto() {
        return foto;
    }

    public void setFoto(byte[] foto) {
        this.foto = foto;
    }

    public long getCpf() {
        return cpf;
    }

    public void setCpf(long cpf) {
        this.cpf = cpf;
    }

    public long getCep() {
        return cep;
    }

    public void setCep(long cep) {
        this.cep = cep;
    } 

    @Override
    public String toString() {
        return "Garcon{" + "foto=" + foto + '}';
    }
    
   
    
    
}
