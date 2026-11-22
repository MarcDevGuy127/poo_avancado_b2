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

public class CadastroLivroView extends JFrame {

	private static final long serialVersionUID = 1L;

	private ArrayList<Livro> livros;
	private JTextField tituloLivro;
	private JTextField autorLivro;
	private JTextField generoLivro;
	private JTextField anoLivro;
	private JTextField edicaoLivro;

	public CadastroLivroView(ArrayList<Livro> livros) {

		this.livros = livros;

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

		JButton btnCadastrar = new JButton("Cadastrar");
		JButton btnLimpar = new JButton("Limpar");

		botoes.add(btnCadastrar);
		botoes.add(btnLimpar);

		painel.add(botoes, BorderLayout.SOUTH);

		// Evento cadastrar
		btnCadastrar.addActionListener(e -> cadastrar());

		// Evento limpar
		btnLimpar.addActionListener(e -> limparCampos());

		add(painel);
	}

	private void cadastrar() {

		String titulo = tituloLivro.getText().trim();
		String autor = autorLivro.getText().trim();
		String genero = generoLivro.getText().trim();
		String ano = anoLivro.getText().trim();
		String edicao = edicaoLivro.getText().trim();

		// Validação dos campos vazios
		if (titulo.isEmpty() || autor.isEmpty() || genero.isEmpty() || ano.isEmpty() || edicao.isEmpty()) {

			JOptionPane.showMessageDialog(this, "Preencha todos os campos!", "Atenção", JOptionPane.WARNING_MESSAGE);

			return;
		}

		try {

			// Conversão dos valores numéricos
			Integer anoPublicacao = Integer.parseInt(ano);
			Integer numeroEdicao = Integer.parseInt(edicao);

			// Validação dos números
			if (anoPublicacao < 0 || numeroEdicao < 0) {

				JOptionPane.showMessageDialog(this, "Ano e edição devem ser maiores ou iguais a zero!");

				return;
			}

			// Normalização dos textos
			titulo = Livro.normalizarTexto(titulo);
			autor = Livro.normalizarTexto(autor);
			genero = Livro.normalizarTexto(genero);

			// Instanciação do objeto
			Livro novoLivro = new Livro(titulo, autor, genero, anoPublicacao, numeroEdicao);

			// Adiciona à lista
			livros.add(novoLivro);

			try {

				// Salva os livros no arquivo
				Livro.salvar(livros);

				JOptionPane.showMessageDialog(this, "Livro cadastrado e salvo com sucesso!");

				limparCampos();

			} catch (IOException erro) {

				// Remove da lista caso não consiga salvar
				livros.remove(novoLivro);

				JOptionPane.showMessageDialog(this, "Erro ao salvar o arquivo: " + erro.getMessage(), "Erro",
						JOptionPane.ERROR_MESSAGE);
			}

		} catch (NumberFormatException erro) {

			JOptionPane.showMessageDialog(this, "Ano e edição devem conter apenas números inteiros!",
					"Erro de validação", JOptionPane.ERROR_MESSAGE);
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
