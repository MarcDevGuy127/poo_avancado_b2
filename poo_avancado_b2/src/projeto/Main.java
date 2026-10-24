package projeto;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Main {

	static void salvar(ArrayList<Livro> livros) throws IOException {

		FileWriter writer = new FileWriter("livros.txt");

		for (Livro livro : livros) {
			String linha = livro.getTitulo() + ";" 
						+ livro.getGenero() + ";"
						+ livro.getAutor() + ";"
						+ livro.getAno() + ";" 
						+ livro.getEdicao();

			writer.write(linha + "\n");
		}
		writer.close();
		
		System.out.println("Livro registrado");
		System.out.println("Quantidade de livros: " + livros.size());
	}
	
	
	static void carregar(ArrayList<Livro> livros) throws IOException {

		livros.clear();

		BufferedReader reader = new BufferedReader(new FileReader("livros.txt"));

		String linha;

		while ((linha = reader.readLine()) != null) {
			String[] separador = linha.split(";");

			for (String parte : separador) {
				if (!parte.isEmpty()) {
					System.out.println(parte);
				}
			}
			
			Livro livro = new Livro(
					separador[0],
					separador[1],
					separador[2],
					Integer.parseInt(separador[3]),
					Integer.parseInt(separador[4]));
		}

		reader.close();
	}
	
	static void buscarPorNome(ArrayList<Livro> livros, String busca) {

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

	static void mostrarArquivo() throws IOException {

		BufferedReader reader = new BufferedReader(new FileReader("livros.txt"));
		String linha;
		while ((linha = reader.readLine()) != null) {
			System.out.println(linha);
		}
		reader.close();
	}
	
	public static void main(String[] args) throws IOException {
		ArrayList<Livro> livros = new ArrayList<>();

		//int opcao = -1;

		String busca = "hobbit";
		
		livros.add(new Livro("O Hobbit: A Sociedade do Anel", "J.R.R. Tolkien", "Fantasia", 1954, 1));
		livros.add(new Livro("O Diário de Anne Frank", "Anne Frank", "Biografia", 1945, 1));

		carregar(livros);
		salvar(livros);
		buscarPorNome(livros, busca);
		mostrarArquivo();
	}

}
