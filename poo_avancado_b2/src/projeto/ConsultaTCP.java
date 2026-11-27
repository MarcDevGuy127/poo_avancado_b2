package projeto;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class ConsultaTCP {
    private static final String HOST = "127.0.0.1";
    private static final int PORTA = 5000;
    private static final int TIMEOUT_MS = 3000;

    // Testa se o servidor está ativo antes de rodar o menu principal
    public static boolean testarConexao() {
        try (Socket socket = new Socket()) {
            socket.connect(new InetSocketAddress(HOST, PORTA), TIMEOUT_MS);
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    // Método para realizar a consulta remota via socket
    public static String consultar(String comando) {
        if (comando == null || comando.trim().isEmpty()) {
            return "ERRO;Comando não pode ser vazio.";
        }

        try (Socket socket = new Socket()) {
            socket.connect(new InetSocketAddress(HOST, PORTA), TIMEOUT_MS);
            socket.setSoTimeout(TIMEOUT_MS);

            try (PrintWriter saida = new PrintWriter(
                         new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), StandardCharsets.UTF_8)), true);
                 BufferedReader entrada = new BufferedReader(
                         new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8))) {

                saida.println(comando.trim());
                String resposta = entrada.readLine();
                return resposta == null ? "Servidor não respondeu." : resposta;
            }
        } catch (IOException e) {
            return "Erro de conexão: Servidor indisponível (" + e.getMessage() + ").";
        }
    }
}