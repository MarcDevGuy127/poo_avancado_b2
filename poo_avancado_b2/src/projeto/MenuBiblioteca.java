package projeto;


import java.awt.BorderLayout;
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
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

public class MenuBiblioteca extends JFrame {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<Livro> livros;

    public MenuBiblioteca(ArrayList<Livro> livros) {

        this.livros = livros;

        setTitle("Sistema de Controle de Biblioteca");
        setSize(650, 550);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Painel principal
        JPanel painel = new JPanel();
        painel.setLayout(new BorderLayout(15, 15));
        painel.setBorder(
            BorderFactory.createEmptyBorder(25, 30, 25, 30)
        );

        // Cabeçalho
        JLabel titulo = new JLabel(
            "SISTEMA DE CONTROLE DE BIBLIOTECA",
            SwingConstants.CENTER
        );

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

        JButton btnCadastrarLivro = new JButton("Cadastrar Livro");
        JButton btnPesquisarLivro = new JButton("Pesquisar Livro");
        JButton btnConsultarTCP = new JButton("Consultar via TCP");
        JButton btnEmprestimos = new JButton("Listar Empréstimos");
        JButton btnCadastrarBibliotecario = new JButton("Cadastrar Bibliotecário");
        JButton btnConsultarBibliotecarios = new JButton("Consultar Bibliotecários");
        JButton btnRelatorio = new JButton("Salvar Relatório");
        JButton btnSair = new JButton("Sair");

        // Adicionando botões
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
        JLabel rodape = new JLabel(
            "Sistema de Biblioteca - Aplicação Java",
            SwingConstants.CENTER
        );

        painel.add(rodape, BorderLayout.SOUTH);

        // Eventos dos botões

        btnCadastrarLivro.addActionListener(e -> {

            CadastroLivroView cadastro = new CadastroLivroView(livros);

            cadastro.setVisible(true);

        });

        btnPesquisarLivro.addActionListener(e -> {
            Livro.pesquisar(livros, "");
        });

        btnConsultarTCP.addActionListener(e -> {
            consultarLivroTCP();
        });

        btnEmprestimos.addActionListener(e -> {
            JOptionPane.showMessageDialog(
                this,
                "Listar Empréstimos"
            );
        });

        btnCadastrarBibliotecario.addActionListener(e -> {
            cadastrarBibliotecario();
        });

        btnConsultarBibliotecarios.addActionListener(e -> {
            Bibliotecario.consultar(new ArrayList<>());
        });

        btnRelatorio.addActionListener(e -> {
            try {
				Livro.salvar(livros);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

            JOptionPane.showMessageDialog(
                this,
                "Operação de salvamento concluída!"
            );
        });

        btnSair.addActionListener(e -> {
            int resposta = JOptionPane.showConfirmDialog(
                this,
                "Deseja realmente sair?",
                "Encerrar sistema",
                JOptionPane.YES_NO_OPTION
            );

            if (resposta == JOptionPane.YES_OPTION) {
                dispose();
            }
        });

        add(painel);
    }

    // Métodos auxiliares


    private void consultarLivroTCP() {

        String titulo = JOptionPane.showInputDialog(
            this,
            "Digite o título do livro:"
        );

        if (titulo == null || titulo.isBlank()) {
            return;
        }

        String resposta = ConsultaTCP.consultar(
            "CONSULTAR;" + titulo
        );

        JOptionPane.showMessageDialog(
            this,
            resposta,
            "Resposta do Servidor TCP",
            JOptionPane.INFORMATION_MESSAGE
        );
    }

    private void cadastrarBibliotecario() {
        JOptionPane.showMessageDialog(
            this,
            "Implementar formulário de cadastro de bibliotecário."
        );
    }

    public static void main(String[] args) {

        ArrayList<Livro> livros = new ArrayList<>();

        SwingUtilities.invokeLater(() -> {

            MenuBiblioteca janela = new MenuBiblioteca(livros);

            janela.setVisible(true);

        });
    }
}
