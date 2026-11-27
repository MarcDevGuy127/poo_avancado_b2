package projeto;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

public class MenuBiblioteca extends JFrame {

    private static final long serialVersionUID = 1L;

    private final ArrayList<Livro> livros;
    private final ArrayList<Bibliotecario> bibliotecarios;
    private final LivroService livroService;
    private final BibliotecarioService bibliotecarioService;
    private final JPanel painel;

    private CadastroLivroView cadastroLivroView;
    private BibliotecarioView bibliotecarioView;

    public MenuBiblioteca(ArrayList<Livro> livros) {
        this(livros, new ArrayList<>(), new BibliotecaService());
    }

    public MenuBiblioteca(
            ArrayList<Livro> livros,
            ArrayList<Bibliotecario> bibliotecarios,
            BibliotecaService bibliotecaService
    ) {
        UIManager.put("Button.focusPainted", Boolean.FALSE);

        this.livros = livros != null ? livros : new ArrayList<>();
        this.bibliotecarios = bibliotecarios != null ? bibliotecarios : new ArrayList<>();
        BibliotecaService service = bibliotecaService != null ? bibliotecaService : new BibliotecaService();
        this.livroService = service.getLivroService();
        this.bibliotecarioService = service.getBibliotecarioService();

        setTitle("Sistema de Controle de Biblioteca");
        setSize(650, 550);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Painel principal
        painel = new JPanel();
        painel.setLayout(new BorderLayout(15, 15));
        painel.setBorder(BorderFactory.createEmptyBorder(25, 30, 25, 30));

        // Cabeçalho
        JLabel titulo = new JLabel("SISTEMA DE CONTROLE DE BIBLIOTECA", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 20));

        JLabel subtitulo = new JLabel(
                "Etapa 2 - Gerenciamento de livros e bibliotecários",
                SwingConstants.CENTER
        );
        subtitulo.setFont(new Font("Arial", Font.PLAIN, 13));

        JPanel cabecalho = new JPanel(new GridLayout(2, 1, 5, 5));
        cabecalho.add(titulo);
        cabecalho.add(subtitulo);

        painel.add(cabecalho, BorderLayout.NORTH);

        // Menu de funcionalidades
        JPanel menu = new JPanel(new GridLayout(4, 2, 12, 12));

        JButton btnCadastrarLivro = criarBotaoMenu("Cadastrar Livro");
        JButton btnPesquisarLivro = criarBotaoMenu("Pesquisar Livro");
        JButton btnConsultarTCP = criarBotaoMenu("Consultar via TCP");
        JButton btnEmprestimos = criarBotaoMenu("Listar Empréstimos");
        JButton btnCadastrarBibliotecario = criarBotaoMenu("Cadastrar Bibliotecário");
        JButton btnConsultarBibliotecarios = criarBotaoMenu("Consultar Bibliotecários");
        JButton btnRelatorio = criarBotaoMenu("Salvar Relatório");
        JButton btnSair = criarBotaoMenu("Sair");

        menu.add(btnCadastrarLivro);
        menu.add(btnPesquisarLivro);
        menu.add(btnConsultarTCP);
        menu.add(btnEmprestimos);
        menu.add(btnCadastrarBibliotecario);
        menu.add(btnConsultarBibliotecarios);
        menu.add(btnRelatorio);
        menu.add(btnSair);

        painel.add(menu, BorderLayout.CENTER);

        // Rodapé
        JLabel rodape = new JLabel("Sistema de Biblioteca - Aplicação Java", SwingConstants.CENTER);
        painel.add(rodape, BorderLayout.SOUTH);

        // Eventos dos botões
        btnCadastrarLivro.addActionListener(e -> abrirCadastroLivro());
        btnPesquisarLivro.addActionListener(e -> pesquisarLivro());
        btnConsultarTCP.addActionListener(e -> consultarLivroTCP());
        btnEmprestimos.addActionListener(e -> listarEmprestimos());
        btnCadastrarBibliotecario.addActionListener(e -> abrirCadastroBibliotecario());
        btnConsultarBibliotecarios.addActionListener(e -> consultarBibliotecarios());
        btnRelatorio.addActionListener(e -> salvarRelatorio());
        btnSair.addActionListener(e -> confirmarSaida());

        add(painel);
    }

    private JButton criarBotaoMenu(String texto) {
        JButton botao = new JButton(texto);
        botao.setFocusPainted(false);
        return botao;
    }

    private void abrirCadastroLivro() {
        if (cadastroLivroView == null || !cadastroLivroView.isDisplayable()) {
            cadastroLivroView = new CadastroLivroView(livros, livroService);
            cadastroLivroView.setVisible(true);
        } else {
            cadastroLivroView.toFront();
            cadastroLivroView.requestFocus();
        }
        painel.requestFocusInWindow();
    }

    private void pesquisarLivro() {
        if (livros.isEmpty()) {
            JOptionPane.showMessageDialog(
                    this,
                    "Nenhum livro cadastrado.",
                    "Pesquisa de Livros",
                    JOptionPane.INFORMATION_MESSAGE
            );
            painel.requestFocusInWindow();
            return;
        }

        String termo = JOptionPane.showInputDialog(
                this,
                "Digite o título ou parte do título do livro para pesquisar:"
        );

        if (termo == null) {
            painel.requestFocusInWindow();
            return;
        }

        if (termo.trim().isEmpty()) {
            JOptionPane.showMessageDialog(
                    this,
                    "Digite um título válido para realizar a busca.",
                    "Atenção",
                    JOptionPane.WARNING_MESSAGE
            );
            painel.requestFocusInWindow();
            return;
        }

        List<Livro> encontrados = livroService.pesquisarPorTitulo(livros, termo);
        if (encontrados.isEmpty()) {
            JOptionPane.showMessageDialog(
                    this,
                    "Nenhum livro encontrado com o termo informado.",
                    "Resultado da Busca",
                    JOptionPane.INFORMATION_MESSAGE
            );
            painel.requestFocusInWindow();
            return;
        }

        StringBuilder sb = new StringBuilder();
        for (Livro livro : encontrados) {
            sb.append("Título: ").append(livro.getTitulo()).append("\n")
              .append("Autor: ").append(livro.getAutor()).append("\n")
              .append("Gênero: ").append(livro.getGenero()).append("\n")
              .append("Ano: ").append(livro.getAno()).append("\n")
              .append("Edição: ").append(livro.getEdicao()).append("\n")
              .append("----------------------------------------\n");
        }

        exibirTextoEmJanela("Livros Encontrados", sb.toString());
        painel.requestFocusInWindow();
    }

    private void consultarLivroTCP() {
        String titulo = JOptionPane.showInputDialog(
                this,
                "Digite o título do livro:"
        );

        if (titulo == null || titulo.isBlank()) {
            painel.requestFocusInWindow();
            return;
        }

        String resposta = ConsultaTCP.consultar("CONSULTAR;" + titulo.trim());

        JOptionPane.showMessageDialog(
                this,
                resposta,
                "Resposta do Servidor TCP",
                JOptionPane.INFORMATION_MESSAGE
        );
        painel.requestFocusInWindow();
    }

    private void listarEmprestimos() {
        JOptionPane.showMessageDialog(
                this,
                "Funcionalidade de empréstimos será implementada na próxima etapa.",
                "Empréstimos",
                JOptionPane.INFORMATION_MESSAGE
        );
        painel.requestFocusInWindow();
    }

    private void abrirCadastroBibliotecario() {
        if (bibliotecarioView == null || !bibliotecarioView.isDisplayable()) {
            bibliotecarioView = new BibliotecarioView(bibliotecarios, bibliotecarioService);
            bibliotecarioView.setVisible(true);
        } else {
            bibliotecarioView.toFront();
            bibliotecarioView.requestFocus();
        }
        painel.requestFocusInWindow();
    }

    private void consultarBibliotecarios() {
        List<Bibliotecario> listaAtual = bibliotecarioService.consultar(bibliotecarios);
        if (listaAtual.isEmpty()) {
            JOptionPane.showMessageDialog(
                    this,
                    "Nenhum bibliotecário cadastrado no sistema.",
                    "Bibliotecários",
                    JOptionPane.INFORMATION_MESSAGE
            );
            painel.requestFocusInWindow();
            return;
        }

        StringBuilder sb = new StringBuilder("--- LISTA DE BIBLIOTECÁRIOS ---\n\n");
        for (int i = 0; i < listaAtual.size(); i++) {
            Bibliotecario b = listaAtual.get(i);
            sb.append(i + 1).append(" - Nome: ").append(b.getNome()).append("\n");
        }

        exibirTextoEmJanela("Bibliotecários Cadastrados", sb.toString());
        painel.requestFocusInWindow();
    }

    private void salvarRelatorio() {
        try {
            livroService.salvarLivros(livros);
            JOptionPane.showMessageDialog(
                    this,
                    "Operação de salvamento concluída! Total de livros: " + livros.size(),
                    "Salvar Relatório",
                    JOptionPane.INFORMATION_MESSAGE
            );
        } catch (IOException e) {
            JOptionPane.showMessageDialog(
                    this,
                    "Erro ao salvar relatório de livros: " + e.getMessage(),
                    "Erro",
                    JOptionPane.ERROR_MESSAGE
            );
        }
        painel.requestFocusInWindow();
    }

    private void confirmarSaida() {
        int resposta = JOptionPane.showConfirmDialog(
                this,
                "Deseja realmente sair?",
                "Encerrar sistema",
                JOptionPane.YES_NO_OPTION
        );

        if (resposta == JOptionPane.YES_OPTION) {
            dispose();
        } else {
            painel.requestFocusInWindow();
        }
    }

    private void exibirTextoEmJanela(String tituloJanela, String conteudo) {
        JTextArea area = new JTextArea(conteudo);
        area.setEditable(false);
        area.setFont(new Font("Monospaced", Font.PLAIN, 12));

        JScrollPane scroll = new JScrollPane(area);
        scroll.setPreferredSize(new Dimension(480, 300));

        JOptionPane.showMessageDialog(this, scroll, tituloJanela, JOptionPane.INFORMATION_MESSAGE);
    }

    public static void main(String[] args) {
        UIManager.put("Button.focusPainted", Boolean.FALSE);

        SwingUtilities.invokeLater(() -> {
            BibliotecaService service = new BibliotecaService();
            ArrayList<Livro> livrosCarregados;
            try {
                livrosCarregados = service.inicializarBiblioteca().getLivro();
            } catch (IOException e) {
                livrosCarregados = new ArrayList<>();
            }

            MenuBiblioteca janela = new MenuBiblioteca(livrosCarregados, new ArrayList<>(), service);
            janela.setVisible(true);
        });
    }
}