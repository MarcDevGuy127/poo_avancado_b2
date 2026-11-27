package projeto;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

public class BibliotecarioView extends JFrame {

    private static final long serialVersionUID = 1L;

    private final ArrayList<Bibliotecario> bibliotecarios;
    private final BibliotecarioService bibliotecarioService;
    private final JTextField nomeBibliotecario;

    public BibliotecarioView(ArrayList<Bibliotecario> bibliotecarios) {
        this(bibliotecarios, new BibliotecarioService());
    }

    public BibliotecarioView(ArrayList<Bibliotecario> bibliotecarios, BibliotecarioService bibliotecarioService) {
        UIManager.put("Button.focusPainted", Boolean.FALSE);

        this.bibliotecarios = bibliotecarios != null ? bibliotecarios : new ArrayList<>();
        this.bibliotecarioService = bibliotecarioService != null ? bibliotecarioService : new BibliotecarioService();

        setTitle("Cadastro de Bibliotecário");
        setSize(450, 260);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Painel principal
        JPanel painel = new JPanel(new BorderLayout(10, 15));
        painel.setBorder(BorderFactory.createEmptyBorder(20, 25, 20, 25));

        // Cabeçalho
        JLabel titulo = new JLabel("CADASTRO DE BIBLIOTECÁRIO", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 18));
        painel.add(titulo, BorderLayout.NORTH);

        // Painel dos campos
        JPanel wrapperCampos = new JPanel(new BorderLayout());
        JPanel campos = new JPanel(new GridLayout(1, 2, 10, 10));
        campos.add(new JLabel("Nome Completo:"));

        nomeBibliotecario = new JTextField();
        campos.add(nomeBibliotecario);

        wrapperCampos.add(campos, BorderLayout.NORTH);
        painel.add(wrapperCampos, BorderLayout.CENTER);

        // Painel dos botões
        JPanel botoes = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
        JButton btnLimpar = criarBotao("Limpar");
        JButton btnCadastrar = criarBotao("Cadastrar");

        botoes.add(btnLimpar);
        botoes.add(btnCadastrar);
        painel.add(botoes, BorderLayout.SOUTH);

        // Eventos
        btnCadastrar.addActionListener(e -> cadastrar());
        btnLimpar.addActionListener(e -> limparCampos());

        add(painel);
    }

    private JButton criarBotao(String texto) {
        JButton botao = new JButton(texto);
        botao.setFocusPainted(false);
        return botao;
    }

    private void cadastrar() {
        String nome = nomeBibliotecario.getText();

        if (nome == null || nome.trim().isEmpty()) {
            JOptionPane.showMessageDialog(
                    this,
                    "Preencha todos os campos!",
                    "Atenção",
                    JOptionPane.WARNING_MESSAGE
            );
            nomeBibliotecario.requestFocus();
            return;
        }

        try {
            Bibliotecario novoBibliotecario = bibliotecarioService.cadastrarBibliotecario(bibliotecarios, nome);
            JOptionPane.showMessageDialog(
                    this,
                    "Bibliotecário \"" + novoBibliotecario.getNome() + "\" cadastrado com sucesso!",
                    "Sucesso",
                    JOptionPane.INFORMATION_MESSAGE
            );
            limparCampos();
            dispose();
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(
                    this,
                    ex.getMessage(),
                    "Atenção",
                    JOptionPane.WARNING_MESSAGE
            );
        }
    }

    private void limparCampos() {
        nomeBibliotecario.setText("");
        nomeBibliotecario.requestFocus();
    }
}