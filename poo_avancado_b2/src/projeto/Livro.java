package projeto;

public class Livro {
	public String titulo;
	public String autor;
	public String genero;
	public Integer ano;
	public Integer edicao;
	
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

	public String getAutor() {
		return autor;
	}

	public String getGenero() {
		return genero;
	}

	public Integer getAno() {
		return ano;
	}

	public Integer getEdicao() {
		return edicao;
	}
	
	
}
