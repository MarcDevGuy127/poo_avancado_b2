package projeto;

import java.io.IOException;
import java.util.ArrayList;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

public class Main {

    public static void main(String[] args) {
        UIManager.put("Button.focusPainted", Boolean.FALSE);

        BibliotecaService bibliotecaService = new BibliotecaService();
        ArrayList<Livro> livros;
        ArrayList<Bibliotecario> bibliotecarios;

        try {
            Biblioteca biblioteca = bibliotecaService.inicializarBiblioteca();
            livros = (biblioteca != null && biblioteca.getLivro() != null)
                    ? biblioteca.getLivro()
                    : new ArrayList<>();
            bibliotecarios = (biblioteca != null && biblioteca.getBibliotecario() != null)
                    ? biblioteca.getBibliotecario()
                    : new ArrayList<>();
        } catch (IOException e) {
            livros = new ArrayList<>();
            bibliotecarios = new ArrayList<>();
        }

        final ArrayList<Livro> livrosFinais = livros;
        final ArrayList<Bibliotecario> bibliotecariosFinais = bibliotecarios;

        SwingUtilities.invokeLater(() -> {
            MenuBiblioteca menu = new MenuBiblioteca(livrosFinais, bibliotecariosFinais, bibliotecaService);
            menu.setVisible(true);
        });
    }
}