package projeto;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.io.IOException;
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

public class CadastroLivroView extends JFrame {

    private static final long serialVersionUID = 1L;

    private final ArrayList<Livro> livros;
    private final LivroService livroService;

    private final JTextField tituloLivro;
    private final JTextField autorLivro;
    private final JTextField generoLivro;
    private final JTextField anoLivro;
    private final JTextField edicaoLivro;

    public CadastroLivroView(ArrayList<Livro> livros) {
        this(livros, new LivroService());
    }

    public CadastroLivroView(ArrayList<Livro> livros, LivroService livroService) {
        UIManager.put("Button.focusPainted", Boolean.FALSE);

        this.livros = livros != null ? livros : new ArrayList<>();
        this.livroService = livroService != null ? livroService : new LivroService();

        setTitle("Cadastro de Livros");
        setSize(450, 350);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Painel principal
        JPanel painel = new JPanel(new BorderLayout(10, 15));
        painel.setBorder(BorderFactory.createEmptyBorder(20, 25, 20, 25));

        // Cabeçalho
        JLabel titulo = new JLabel("CADASTRO DE LIVROS", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 20));
        painel.add(titulo, BorderLayout.NORTH);

        // Painel dos campos
        JPanel campos = new JPanel(new GridLayout(5, 2, 10, 15));

        campos.add(new JLabel("Título:"));
        tituloLivro = new JTextField();
        campos.add(tituloLivro);

        campos.add(new JLabel("Autor:"));
        autorLivro = new JTextField();
        campos.add(autorLivro);

        campos.add(new JLabel("Gênero:"));
        generoLivro = new JTextField();
        campos.add(generoLivro);

        campos.add(new JLabel("Ano de publicação:"));
        anoLivro = new JTextField();
        campos.add(anoLivro);

        campos.add(new JLabel("Edição:"));
        edicaoLivro = new JTextField();
        campos.add(edicaoLivro);

        painel.add(campos, BorderLayout.CENTER);

        // Painel dos botões
        JPanel botoes = new JPanel(new FlowLayout());
        JButton btnCadastrar = criarBotao("Cadastrar");
        JButton btnLimpar = criarBotao("Limpar");

        botoes.add(btnCadastrar);
        botoes.add(btnLimpar);
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
        String titulo = tituloLivro.getText().trim();
        String autor = autorLivro.getText().trim();
        String genero = generoLivro.getText().trim();
        String ano = anoLivro.getText().trim();
        String edicao = edicaoLivro.getText().trim();

        if (titulo.isEmpty() || autor.isEmpty() || genero.isEmpty() || ano.isEmpty() || edicao.isEmpty()) {
            JOptionPane.showMessageDialog(
                    this,
                    "Preencha todos os campos!",
                    "Atenção",
                    JOptionPane.WARNING_MESSAGE
            );
            return;
        }

        try {
            Integer anoPublicacao = Integer.parseInt(ano);
            Integer numeroEdicao = Integer.parseInt(edicao);

            Livro novoLivro = livroService.criarLivro(titulo, autor, genero, anoPublicacao, numeroEdicao);
            livros.add(novoLivro);

            try {
                livroService.salvarLivros(livros);
                JOptionPane.showMessageDialog(this, "Livro cadastrado e salvo com sucesso!");
                limparCampos();
                dispose();
            } catch (IOException erro) {
                livros.remove(novoLivro);
                JOptionPane.showMessageDialog(
                        this,
                        "Erro ao salvar o arquivo: " + erro.getMessage(),
                        "Erro",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        } catch (NumberFormatException erro) {
            JOptionPane.showMessageDialog(
                    this,
                    "Ano e edição devem conter apenas números inteiros!",
                    "Erro de validação",
                    JOptionPane.ERROR_MESSAGE
            );
        } catch (IllegalArgumentException erro) {
            JOptionPane.showMessageDialog(
                    this,
                    erro.getMessage(),
                    "Atenção",
                    JOptionPane.WARNING_MESSAGE
            );
        }
    }

    private void limparCampos() {
        tituloLivro.setText("");
        autorLivro.setText("");
        generoLivro.setText("");
        anoLivro.setText("");
        edicaoLivro.setText("");

        tituloLivro.requestFocus();
    }
}