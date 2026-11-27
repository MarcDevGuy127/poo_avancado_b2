package projeto;

import java.io.IOException;
import java.util.ArrayList;

public class BibliotecaService {
    private final BibliotecaDAO bibliotecaDAO;
    private final LivroService livroService;
    private final BibliotecarioService bibliotecarioService;

    public BibliotecaService() {
        this.bibliotecaDAO = new BibliotecaDAO();
        this.livroService = new LivroService();
        this.bibliotecarioService = new BibliotecarioService();
    }

    public BibliotecaService(
            BibliotecaDAO bibliotecaDAO,
            LivroService livroService,
            BibliotecarioService bibliotecarioService
    ) {
        this.bibliotecaDAO = bibliotecaDAO;
        this.livroService = livroService;
        this.bibliotecarioService = bibliotecarioService;
    }

    public Biblioteca inicializarBiblioteca() throws IOException {
        return bibliotecaDAO.carregarBiblioteca();
    }

    public Biblioteca criarBibliotecaVazia() {
        return new Biblioteca(new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
    }

    public void salvarDados(Biblioteca biblioteca) throws IOException {
        bibliotecaDAO.salvarBiblioteca(biblioteca);
    }

    public String gerarResumoAcervo(Biblioteca biblioteca) {
        if (biblioteca == null) {
            return "Biblioteca não inicializada.";
        }
        int totalLivros = biblioteca.getLivro() != null ? biblioteca.getLivro().size() : 0;
        int totalBibliotecarios = biblioteca.getBibliotecario() != null ? biblioteca.getBibliotecario().size() : 0;
        int totalAlunos = biblioteca.getAluno() != null ? biblioteca.getAluno().size() : 0;

        return "Resumo da Biblioteca -> Livros: " + totalLivros
                + " | Bibliotecários: " + totalBibliotecarios
                + " | Alunos: " + totalAlunos;
    }

    public LivroService getLivroService() {
        return livroService;
    }

    public BibliotecarioService getBibliotecarioService() {
        return bibliotecarioService;
    }
}