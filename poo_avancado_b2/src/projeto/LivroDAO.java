package projeto;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class LivroDAO {
    private static final String ARQUIVO_LIVROS = "livros.txt";

    // Futuro JDBC: substituir pela leitura da tabela livro via Connection / PreparedStatement / ResultSet
    public List<Livro> listarTodos() throws IOException {
        List<Livro> livros = new ArrayList<>();
        File arquivo = new File(ARQUIVO_LIVROS);

        if (!arquivo.exists()) {
            return livros;
        }

        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(new FileInputStream(arquivo), StandardCharsets.UTF_8))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                if (linha.trim().isEmpty()) {
                    continue;
                }
                String[] partes = linha.split(";");
                if (partes.length >= 5) {
                    String titulo = partes[0].trim();
                    String autor = partes[1].trim();
                    String genero = partes[2].trim();
                    Integer ano = parseIntSeguro(partes[3].trim());
                    Integer edicao = parseIntSeguro(partes[4].trim());
                    livros.add(new Livro(titulo, autor, genero, ano, edicao));
                }
            }
        }
        return livros;
    }

    // Futuro JDBC: substituir pela gravação/atualização na tabela livro via Connection / PreparedStatement
    public void salvarTodos(List<Livro> livros) throws IOException {
        try (OutputStreamWriter writer = new OutputStreamWriter(
                new FileOutputStream(ARQUIVO_LIVROS), StandardCharsets.UTF_8)) {
            for (Livro livro : livros) {
                String linha = livro.getTitulo() + ";"
                        + livro.getAutor() + ";"
                        + livro.getGenero() + ";"
                        + livro.getAno() + ";"
                        + livro.getEdicao();
                writer.write(linha + System.lineSeparator());
            }
        }
    }

    private Integer parseIntSeguro(String valor) {
        try {
            return Integer.parseInt(valor);
        } catch (NumberFormatException e) {
            return 0;
        }
    }
}