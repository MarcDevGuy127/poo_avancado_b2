package projeto;

public class Livro {
    private String titulo;
    private String autor;
    private String genero;
    private Integer ano;
    private Integer edicao;

    public Livro() {
    }

    public Livro(String titulo, String autor, String genero, Integer ano, Integer edicao) {
        this.titulo = titulo;
        this.autor = autor;
        this.genero = genero;
        this.ano = ano;
        this.edicao = edicao;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public Integer getAno() {
        return ano;
    }

    public void setAno(Integer ano) {
        this.ano = ano;
    }

    public Integer getEdicao() {
        return edicao;
    }

    public void setEdicao(Integer edicao) {
        this.edicao = edicao;
    }

    @Override
    public String toString() {
        return "Título: " + titulo
                + " | Autor: " + autor
                + " | Gênero: " + genero
                + " | Ano: " + ano
                + " | Edição: " + edicao;
    }
}