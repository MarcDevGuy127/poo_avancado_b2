package projeto;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Livro {
	public String titulo;
	public String autor;
	public String genero;
	public Integer ano;
	public Integer edicao;

	static String normalizarTexto(String string) {

		string = string.trim().toUpperCase();

		if (string.isEmpty()) {
			System.out.println("Nome nao pode ficar vazio.");
			return string;
		} else {
			return string.substring(0, 1).toUpperCase() + string.substring(1);
		}
	}

	static Integer normalizarNumeros(Scanner scanner, Integer valor) {
		while (true) {

			valor = scanner.nextInt();

			if (valor <= -1) {
				System.out.println("O valor deve ser maior que zero!");
			} else {
				return valor;
			}
		}
	}

	public static void pesquisar(ArrayList<Livro> livros, String busca) {

		System.out.print("Digite o nome ou parte do nome do aluno: ");
		busca = "hobbit";

		if (livros.isEmpty()) {
			System.out.println("Nenhum livro cadastrado.");
		}

		if (busca.isEmpty()) {
			System.out.println("Digite um título para realizar a busca.");
		}

		boolean encontrado = false;

		for (Livro livro : livros) {

			if (livro.getTitulo().toLowerCase().contains(busca.toLowerCase())) {

				System.out.println("\nTítulo: " + livro.getTitulo());
				System.out.println("\nAutor: " + livro.getAutor());
				System.out.println("\nGênero: " + livro.getGenero());
				System.out.println("\nAno: " + livro.getAno());
				System.out.printf("\nEdição: %.1f%n", livro.getEdicao());

				encontrado = true;
			}

			if (!encontrado) {
				System.out.println("Nenhum aluno encontrado.");
			}
		}

	}

	public static void cadastrar(Scanner scanner, ArrayList<Livro> livros) {
		System.out.println("CADASTRO DE LIVRO");
		System.out.println("Informe o título do livro:");
		String titulo = scanner.nextLine();
		titulo = normalizarTexto(titulo);
		System.out.println("Informe o nome do autor do livro:");
		String autor = scanner.nextLine();
		autor = normalizarTexto(autor);
		System.out.println("Informe o gênero do livro:");
		String genero = scanner.nextLine();
		genero = normalizarTexto(genero);
		System.out.println("Informe o ano de lançamento do livro:");
		Integer ano = normalizarNumeros(scanner, ano);
		System.out.println("Informe número de edição do livro:");
		Integer edicao = normalizarNumeros(scanner, edicao);

		livros.add(new Livro(titulo, autor, genero, ano, edicao));
	}

	public static void salvar(ArrayList<Livro> livros) throws IOException {

		FileWriter writer = new FileWriter("livros.txt");

		for (Livro livro : livros) {
			String linha = livro.getTitulo() + ";" + livro.getGenero() + ";" + livro.getAutor() + ";" + livro.getAno()
					+ ";" + livro.getEdicao();

			writer.write(linha + "\n");
		}
		writer.close();

		System.out.println("Livro registrado");
		System.out.println("Quantidade de livros: " + livros.size());
	}

	/*
	 * static String normalizarNome(String nome) { nome = nome.trim().toLowerCase();
	 * 
	 * if (nome.isEmpty()) { System.out.println("Nome nao pode ficar vazio.");
	 * return nome; }
	 * 
	 * return nome.substring(0, 1).toUpperCase() + nome.substring(1); }
	 */

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
