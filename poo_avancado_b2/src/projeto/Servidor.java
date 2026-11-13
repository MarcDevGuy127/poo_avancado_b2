package projeto;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Servidor {
    private static ArrayList<Livro> catalogoLivros = new ArrayList<>();

    public static void main(String[] args) {
        int porta = 5000;
        carregarDadosIniciais();

        try (ServerSocket servidor = new ServerSocket(porta)) {
            System.out.println("Servidor TCP iniciado e escutando na porta " + porta + "...");
            
            while (true) {
                Socket cliente = servidor.accept();
                System.out.println("Novo cliente conectado: " + cliente.getInetAddress());
                
                // Cria uma Thread independente para cada cliente (Concorrência da Aula 14)
                new Thread(new Atendimento(cliente, catalogoLivros)).start();
            }
        } catch (IOException e) {
            System.out.println("Erro crítico no servidor: " + e.getMessage());
        }
    }

    private static void carregarDadosIniciais() {
        try (BufferedReader br = new BufferedReader(new FileReader("livros.txt"))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] partes = linha.split(";");
                if (partes.length >= 5) {
                    Livro livro = new Livro(
                        partes[0], 
                        partes[1], 
                        partes[2], 
                        Integer.parseInt(partes[3]), 
                        Integer.parseInt(partes[4])
                    );
                    catalogoLivros.add(livro);
                }
            }
            System.out.println("Catálogo carregado no servidor com sucesso! Total de livros: " + catalogoLivros.size());
        } catch (IOException e) {
            System.out.println("Aviso: 'livros.txt' não encontrado. O servidor iniciará com catálogo vazio.");
        }
    }
}