package projeto;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

public class Main {

    static void carregar(ArrayList<Livro> livros) throws IOException {
        livros.clear();
        File arquivo = new File("livros.txt");
        
        if (!arquivo.exists()) {
            return;
        }

        // Lê o arquivo forçando a codificação UTF-8
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(arquivo), StandardCharsets.UTF_8));
        String linha;

        while ((linha = reader.readLine()) != null) {
            String[] separador = linha.split(";");
            
            if (separador.length >= 5) {
                Livro livro = new Livro(
                    separador[0],
                    separador[1],
                    separador[2],
                    Integer.parseInt(separador[3]),
                    Integer.parseInt(separador[4])
                );
                livros.add(livro);
            }
        }
        reader.close();
    }

    public static void main(String[] args) throws IOException {
        ArrayList<Livro> livros = new ArrayList<>();
        carregar(livros);

        // CORREÇÃO CRUCIAL: Força o Scanner a ler o teclado em UTF-8
        Scanner scanner = new Scanner(System.in, StandardCharsets.UTF_8);
        scanner.useLocale(Locale.US);
        int opcao = -1;

        do {
            System.out.println("\nSISTEMA DE CONTROLE DE TURMA");
            System.out.println("1 - Cadastrar livro");
            System.out.println("2 - Pesquisar livro");
            System.out.println("3 - Efetuar emprestimo");
            System.out.println("4 - Listar emprestimos");
            System.out.println("5 - Cadastrar bibliotecario");
            System.out.println("6 - Consultar bibliotecarios");
            System.out.println("7 - Salvar relatorio em arquivo");
            System.out.println("0 - Sair do sistema");
            System.out.print("Escolha uma opcao: ");

            if (scanner.hasNextInt()) {
                opcao = scanner.nextInt();
                scanner.nextLine(); // Limpa o buffer do enter

                switch (opcao) {
                    case 1:
                        Livro.cadastrar(scanner, livros);
                        Livro.salvar(livros);
                        break;
                    case 2:
                        Livro.pesquisar(livros, "");
                        break;
                    case 3:
                        System.out.println("\n--- Efetuar Empréstimo ---");
                        break;
                    case 4:
                        System.out.println("\n--- Listar Empréstimos ---");
                        break;
                    case 5:
                        Bibliotecario.cadastrar(scanner, null); // Ajuste conforme sua lista de bibliotecários se necessário
                        break;
                    case 6:
                        Bibliotecario.consultar(null);
                        break;
                    case 7:
                        System.out.println("\n--- Salvar Relatório ---");
                        Livro.salvar(livros);
                        break;
                    case 0:
                        System.out.println("\nA sair do sistema...");
                        break;
                    default:
                        System.out.println("\nOpcao invalida! Escolha um numero entre 0 e 7.");
                }
            } else {
                System.out.println("\nPor favor, digite um numero valido.");
                scanner.next(); // Descarta entrada inválida
            }

        } while (opcao != 0);

        scanner.close();
    }
}