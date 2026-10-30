package projeto;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

public class Main {

	
	
	
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
		
		Scanner scanner = new Scanner(System.in).useLocale(Locale.US);
		int opcao = -1;

		String busca = "hobbit";
		
		//livros.add(new Livro("O Hobbit: A Sociedade do Anel", "J.R.R. Tolkien", "Fantasia", 1954, 1));
		//livros.add(new Livro("O Diário de Anne Frank", "Anne Frank", "Biografia", 1945, 1));

		do {
			System.out.println("SISTEMA DE CONTROLE DE TURMA");
			System.out.println("1 - Cadastrar livro");
			System.out.println("2 - Pesquisar livro");
			System.out.println("3 - Efetuar empréstimo");
			System.out.println("4 - Listar empréstimos");
			System.out.println("5 - Cadastrar bibliotecário");
			System.out.println("6 - Consultar bibliotecários");
			System.out.println("7 - Salvar relatório em arquivo");
			System.out.println("0 - Sair do sistema");
			opcao = scanner.nextInt();
			
			switch (opcao) {
			case 0:
				System.out.println("Encerrando programa...");
				break;
			case 1:
				carregar(livros);
				Livro.cadastrar(scanner,livros);
				break;
			case 2:
				Livro.pesquisar(livros, busca);
				break;
			/*case 3:
				Aluno.buscarPorNome(scanner, alunos);
				break;
			case 4:
				Aluno.mostrarNotasOrdenadas(scanner, alunos);
				break;
			case 5:
				Aluno.mostrarEstatisticas(alunos);
				break;
			case 6:
				Aluno.filtrarPorSituacao(scanner, alunos);
				break;*/
			default:
				System.out.println("Opcao invalida \n");
			}
		} while (opcao != 0);
		
		carregar(livros);
		Livro.salvar(livros);
		//Livro.pesquisar(livros, busca);
		mostrarArquivo();
	}

}
