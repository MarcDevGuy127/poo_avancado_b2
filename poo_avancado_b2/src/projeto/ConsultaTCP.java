package projeto;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ConsultaTCP {
    // Testa se o servidor está ativo antes de rodar o menu principal
    public static boolean testarConexao() {
        try (Socket socket = new Socket("127.0.0.1", 5000)) {
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    // Método para realizar a consulta remota via socket
    public static String consultar(String comando) {
        try (Socket socket = new Socket("127.0.0.1", 5000);
             PrintWriter saida = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
            
            saida.println(comando);
            String resposta = entrada.readLine();
            return resposta == null ? "Servidor não respondeu." : resposta;
            
        } catch (IOException e) {
            return "Erro de conexão: Servidor indisponível.";
        }
    }
}