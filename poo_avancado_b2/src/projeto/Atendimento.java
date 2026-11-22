package projeto;



import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

public class Atendimento implements Runnable {
    private final Socket socket;
    private final ArrayList<Livro> livros;

    public Atendimento(Socket socket, ArrayList<Livro> livros) {
        this.socket = socket;
        this.livros = livros;
    }

    @Override
    public void run() {
        try (BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter saida = new PrintWriter(socket.getOutputStream(), true)) {
            
            String pedido = entrada.readLine();
            if (pedido == null) return;
            
            System.out.println("Comando recebido: " + pedido);
            String[] campos = pedido.split(";");
            String comando = campos[0];

            // Protocolo simples baseado em comandos (Aula 14)
            if ("CONSULTAR".equalsIgnoreCase(comando) && campos.length == 2) {
                String busca = campos[1].trim().toLowerCase();
                boolean encontrado = false;
                
                for (Livro livro : livros) {
                    if (livro.getTitulo().toLowerCase().contains(busca)) {
                        saida.println("ENCONTRADO;Título: " + livro.getTitulo() + " | Autor: " + livro.getAutor() + " | Gênero: " + livro.getGenero() + " | Ano: " + livro.getAno() + " | Edição: " + livro.getEdicao());
                        encontrado = true;
                        break;
                    }
                }
                
                if (!encontrado) {
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