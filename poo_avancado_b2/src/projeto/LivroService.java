package projeto;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LivroService {
    private final LivroDAO livroDAO;

    public LivroService() {
        this.livroDAO = new LivroDAO();
    }

    public LivroService(LivroDAO livroDAO) {
        this.livroDAO = livroDAO;
    }

    public String normalizarTexto(String texto) {
        if (texto == null) {
            return "";
        }
        String limpo = texto.trim();
        if (limpo.isEmpty()) {
            return "";
        }
        return limpo.substring(0, 1).toUpperCase() + limpo.substring(1);
    }

    public boolean numeroValido(Integer valor) {
        return valor != null && valor >= 0;
    }

    public Livro criarLivro(String titulo, String autor, String genero, Integer ano, Integer edicao) {
        String tituloNorm = normalizarTexto(titulo);
        String autorNorm = normalizarTexto(autor);
        String generoNorm = normalizarTexto(genero);

        if (tituloNorm.isEmpty()) {
            throw new IllegalArgumentException("O título do livro não pode ficar vazio.");
        }
        if (autorNorm.isEmpty()) {
            throw new IllegalArgumentException("O autor do livro não pode ficar vazio.");
        }
        if (generoNorm.isEmpty()) {
            throw new IllegalArgumentException("O gênero do livro não pode ficar vazio.");
        }
        if (!numeroValido(ano)) {
            throw new IllegalArgumentException("O ano de lançamento deve ser maior ou igual a zero.");
        }
        if (!numeroValido(edicao)) {
            throw new IllegalArgumentException("O número de edição deve ser maior ou igual a zero.");
        }

        return new Livro(tituloNorm, autorNorm, generoNorm, ano, edicao);
    }

    public Livro cadastrarLivro(List<Livro> livros, String titulo, String autor, String genero, Integer ano, Integer edicao) {
        Livro novoLivro = criarLivro(titulo, autor, genero, ano, edicao);
        if (livros != null) {
            livros.add(novoLivro);
        }
        return novoLivro;
    }

    public List<Livro> pesquisarPorTitulo(List<Livro> livros, String termo) {
        List<Livro> encontrados = new ArrayList<>();
        if (termo == null || termo.trim().isEmpty() || livros == null) {
            return encontrados;
        }
        String termoLower = termo.trim().toLowerCase();
        for (Livro livro : livros) {
            if (livro.getTitulo() != null && livro.getTitulo().toLowerCase().contains(termoLower)) {
                encontrados.add(livro);
            }
        }
        return encontrados;
    }

    public List<Livro> carregarLivros() throws IOException {
        return livroDAO.listarTodos();
    }

    public void salvarLivros(List<Livro> livros) throws IOException {
        livroDAO.salvarTodos(livros);
    }
}