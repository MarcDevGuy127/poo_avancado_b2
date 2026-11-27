package projeto;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Servidor {
    private static final int PORTA = 5000;
    private static final LivroService livroService = new LivroService();
    private static final ArrayList<Livro> catalogoLivros = new ArrayList<>();

    public static void main(String[] args) {
        carregarDadosIniciais();

        try (ServerSocket servidor = new ServerSocket(PORTA)) {
            System.out.println("Servidor TCP iniciado e escutando na porta " + PORTA + "...");

            while (true) {
                Socket cliente = servidor.accept();
                System.out.println("Novo cliente conectado: " + cliente.getInetAddress());

                List<Livro> snapshotAtual;
                synchronized (catalogoLivros) {
                    snapshotAtual = new ArrayList<>(catalogoLivros);
                }

                new Thread(new Atendimento(cliente, snapshotAtual, livroService)).start();
            }
        } catch (IOException e) {
            System.out.println("Erro crítico no servidor: " + e.getMessage());
        }
    }

    private static void carregarDadosIniciais() {
        try {
            // Delega a leitura ao LivroService / LivroDAO (UTF-8; futuramente JDBC)
            List<Livro> carregados = livroService.carregarLivros();
            synchronized (catalogoLivros) {
                catalogoLivros.clear();
                catalogoLivros.addAll(carregados);
            }
            System.out.println("Catálogo carregado no servidor com sucesso! Total de livros: " + catalogoLivros.size());
        } catch (IOException e) {
            System.out.println("Aviso: não foi possível carregar os livros iniciais (" + e.getMessage() + "). O servidor iniciará com catálogo vazio.");
        }
    }

    public static void atualizarCatalogo(List<Livro> novosLivros) {
        if (novosLivros == null) {
            return;
        }
        synchronized (catalogoLivros) {
            catalogoLivros.clear();
            catalogoLivros.addAll(novosLivros);
        }
    }

    public static LivroService getLivroService() {
        return livroService;
    }
}