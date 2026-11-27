package projeto;

import java.util.ArrayList;
import java.util.List;

public class BibliotecarioService {
    private final BibliotecarioDAO bibliotecarioDAO;

    public BibliotecarioService() {
        this.bibliotecarioDAO = new BibliotecarioDAO();
    }

    public BibliotecarioService(BibliotecarioDAO bibliotecarioDAO) {
        this.bibliotecarioDAO = bibliotecarioDAO;
    }

    public String normalizarNome(String nome) {
        if (nome == null) {
            return "";
        }
        String limpo = nome.trim();
        if (limpo.isEmpty()) {
            return "";
        }
        return limpo.substring(0, 1).toUpperCase() + limpo.substring(1);
    }

    public Bibliotecario criarBibliotecario(String nome) {
        String nomeNormalizado = normalizarNome(nome);
        if (nomeNormalizado.isEmpty()) {
            throw new IllegalArgumentException("Nome inválido! O nome do bibliotecário não pode ficar vazio.");
        }
        return new Bibliotecario(nomeNormalizado);
    }

    public Bibliotecario cadastrarBibliotecario(List<Bibliotecario> lista, String nome) {
        Bibliotecario novo = criarBibliotecario(nome);
        bibliotecarioDAO.salvar(novo);
        if (lista != null) {
            lista.add(novo);
        }
        return novo;
    }

    public List<Bibliotecario> consultar(List<Bibliotecario> lista) {
        if (lista != null && !lista.isEmpty()) {
            return new ArrayList<>(lista);
        }
        return bibliotecarioDAO.listarTodos();
    }

    public List<Bibliotecario> buscarPorNome(List<Bibliotecario> lista, String termo) {
        List<Bibliotecario> resultados = new ArrayList<>();
        if (lista == null || termo == null || termo.trim().isEmpty()) {
            return resultados;
        }
        String termoLower = termo.trim().toLowerCase();
        for (Bibliotecario b : lista) {
            if (b.getNome() != null && b.getNome().toLowerCase().contains(termoLower)) {
                resultados.add(b);
            }
        }
        return resultados;
    }
}