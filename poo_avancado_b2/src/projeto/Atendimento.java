package projeto;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class Atendimento implements Runnable {
    private final Socket socket;
    private final List<Livro> livros;
    private final LivroService livroService;

    public Atendimento(Socket socket, ArrayList<Livro> livros) {
        this(socket, livros, new LivroService());
    }

    public Atendimento(Socket socket, List<Livro> livros, LivroService livroService) {
        this.socket = socket;
        this.livros = livros != null ? livros : new ArrayList<>();
        this.livroService = livroService != null ? livroService : new LivroService();
    }

    @Override
    public void run() {
        try (Socket sock = this.socket;
             BufferedReader entrada = new BufferedReader(
                     new InputStreamReader(sock.getInputStream(), StandardCharsets.UTF_8));
             PrintWriter saida = new PrintWriter(
                     new BufferedWriter(new OutputStreamWriter(sock.getOutputStream(), StandardCharsets.UTF_8)), true)) {

            String pedido = entrada.readLine();
            if (pedido == null) {
                return;
            }

            System.out.println("Comando recebido: " + pedido);
            String[] campos = pedido.split(";", 2);
            String comando = campos[0].trim();

            // Protocolo simples baseado em comandos
            if ("CONSULTAR".equalsIgnoreCase(comando) && campos.length == 2) {
                String busca = campos[1].trim();
                if (busca.isEmpty()) {
                    saida.println("ERRO;Termo de busca não pode ser vazio.");
                    return;
                }

                List<Livro> baseConsulta = this.livros;
                try {
                    baseConsulta = livroService.carregarLivros();
                } catch (IOException e) {
                    System.out.println("Aviso: não foi possível recarregar livros.txt, usando cache em memória: " + e.getMessage());
                }

                List<Livro> encontrados = livroService.pesquisarPorTitulo(baseConsulta, busca);
                if (!encontrados.isEmpty()) {
                    Livro livro = encontrados.get(0);
                    saida.println("ENCONTRADO;Título: " + livro.getTitulo()
                            + " | Autor: " + livro.getAutor()
                            + " | Gênero: " + livro.getGenero()
                            + " | Ano: " + livro.getAno()
                            + " | Edição: " + livro.getEdicao());
                } else {
                    saida.println("ERRO;Nenhum livro encontrado com o termo informado.");
                }
            } else {
                saida.println("ERRO;Comando inválido.");
            }

        } catch (IOException e) {
            System.out.println("Erro no atendimento da thread: " + e.getMessage());
        }
    }
}