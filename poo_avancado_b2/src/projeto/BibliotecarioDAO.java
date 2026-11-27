package projeto;

import java.util.ArrayList;
import java.util.List;

public class BibliotecarioDAO {
    // Futuro JDBC: persistir e consultar na tabela bibliotecario via Connection / PreparedStatement / ResultSet
    private final List<Bibliotecario> registros = new ArrayList<>();
    private int proximoId = 1;

    public Bibliotecario salvar(Bibliotecario bibliotecario) {
        if (bibliotecario == null) {
            return null;
        }
        if (bibliotecario.getId() == null) {
            bibliotecario.setId(proximoId++);
        }
        registros.add(bibliotecario);
        return bibliotecario;
    }

    public List<Bibliotecario> listarTodos() {
        return new ArrayList<>(registros);
    }
}