package projeto;

import java.io.IOException;
import java.util.ArrayList;

public class BibliotecaDAO {
    private final LivroDAO livroDAO;
    private final BibliotecarioDAO bibliotecarioDAO;

    public BibliotecaDAO() {
        this.livroDAO = new LivroDAO();
        this.bibliotecarioDAO = new BibliotecarioDAO();
    }

    public BibliotecaDAO(LivroDAO livroDAO, BibliotecarioDAO bibliotecarioDAO) {
        this.livroDAO = livroDAO;
        this.bibliotecarioDAO = bibliotecarioDAO;
    }

    // Futuro JDBC: carregar dados da biblioteca via Connection / SELECT nas tabelas do banco
    public Biblioteca carregarBiblioteca() throws IOException {
        ArrayList<Livro> livros = new ArrayList<>(livroDAO.listarTodos());
        ArrayList<Bibliotecario> bibliotecarios = new ArrayList<>(bibliotecarioDAO.listarTodos());
        ArrayList<Aluno> alunos = new ArrayList<>();
        return new Biblioteca(alunos, bibliotecarios, livros);
    }

    // Futuro JDBC: salvar em transação (setAutoCommit(false) / commit / rollback)
    public void salvarBiblioteca(Biblioteca biblioteca) throws IOException {
        if (biblioteca == null) {
            throw new IllegalArgumentException("A instância de Biblioteca não pode ser nula.");
        }
        livroDAO.salvarTodos(biblioteca.getLivro());
    }
}