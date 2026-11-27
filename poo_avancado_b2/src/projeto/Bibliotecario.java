package projeto;

public class Bibliotecario {
    private Integer id;
    private String nome;

    public Bibliotecario() {
    }

    public Bibliotecario(String nome) {
        this.nome = nome;
    }

    public Bibliotecario(Integer id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        if (id != null) {
            return "ID: " + id + " | Nome: " + nome;
        }
        return "Nome: " + nome;
    }
}