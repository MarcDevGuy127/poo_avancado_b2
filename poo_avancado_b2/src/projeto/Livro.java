package projeto;

import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Scanner;

public class Livro {
    public String titulo;
    public String autor;
    public String genero;
    public Integer ano;
    public Integer edicao;

    static String normalizarTexto(String string) {
        if (string == null) {
            return "";
        }
        string = string.trim();
        if (string.isEmpty()) {
            System.out.println("O campo não pode ficar vazio.");
            return string;
        } else {
            return string.substring(0, 1).toUpperCase() + string.substring(1);
        }
    }

    static Integer normalizarNumeros(Scanner scanner, Integer valor) {
        while (true) {
            if (scanner.hasNextInt()) {
                valor = scanner.nextInt();
                scanner.nextLine(); // Limpa o buffer

                if (valor < 0) {
                    System.out.println("O valor deve ser maior ou igual a zero!");
                } else {
                    return valor;
                }
            } else {
                System.out.println("Por favor, digite um número inteiro válido!");
                scanner.next(); // Descarta entrada inválida
            }
        }
    }

    public static void pesquisar(ArrayList<Livro> livros, String busca) {
        if (livros.isEmpty()) {
            System.out.println("Nenhum livro cadastrado.");
            return;
        }

        Scanner scanner = new Scanner(System.in);
        System.out.print("Digite o título ou parte do título do livro para pesquisar: ");
        busca = scanner.nextLine();

        if (busca.trim().isEmpty()) {
            System.out.println("Digite um título válido para realizar a busca.");
            return;
        }

        boolean encontrado = false;

        for (Livro livro : livros) {
            if (livro.getTitulo().toLowerCase().contains(busca.toLowerCase())) {
                System.out.println("\n--- Livro Encontrado ---");
                System.out.println("Título: " + livro.getTitulo());
                System.out.println("Autor: " + livro.getAutor());
                System.out.println("Gênero: " + livro.getGenero());
                System.out.println("Ano: " + livro.getAno());
                System.out.println("Edição: " + livro.getEdicao());
                encontrado = true;
            }
        }

        if (!encontrado) {
            System.out.println("Nenhum livro encontrado com o termo informado.");
        }
    }

    public static void cadastrar(Scanner scanner, ArrayList<Livro> livros) {
        System.out.println("\n--- CADASTRO DE LIVRO ---");
        
        System.out.print("Informe o título do livro: ");
        String titulo = scanner.nextLine();
        titulo = normalizarTexto(titulo);
        
        System.out.print("Informe o nome do autor do livro: ");
        String autor = scanner.nextLine();
        autor = normalizarTexto(autor);
        
        System.out.print("Informe o gênero do livro: ");
        String genero = scanner.nextLine();
        genero = normalizarTexto(genero);
        
        System.out.print("Informe o ano de lançamento do livro: ");
        Integer ano = 0;
        ano = normalizarNumeros(scanner, ano);
        
        System.out.print("Informe o número de edição do livro: ");
        Integer edicao = 0;
        edicao = normalizarNumeros(scanner, edicao);

        Livro novoLivro = new Livro(titulo, autor, genero, ano, edicao);
        livros.add(novoLivro);
        System.out.println("Livro cadastrado com sucesso na lista!");
    }

    public static void salvar(ArrayList<Livro> livros) throws IOException {
        // Grava o arquivo forçando estritamente o uso de UTF-8
        OutputStreamWriter writer = new OutputStreamWriter(
            new FileOutputStream("livros.txt"), 
            StandardCharsets.UTF_8
        );

        for (Livro livro : livros) {
            String linha = livro.getTitulo() + ";" + livro.getAutor() + ";" + livro.getGenero() + ";" + livro.getAno() + ";" + livro.getEdicao();
            writer.write(linha + "\n");
        }
        writer.close();

        System.out.println("Dados salvos no arquivo com sucesso!");
        System.out.println("Quantidade total de livros: " + livros.size());
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