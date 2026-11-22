package projeto;

import javax.swing.SwingUtilities;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        ArrayList<Livro> livros = new ArrayList<>();

        // invocando interface do menu
        SwingUtilities.invokeLater(() -> {

            MenuBiblioteca menu = new MenuBiblioteca(livros);

            menu.setVisible(true);

        });

    }
}