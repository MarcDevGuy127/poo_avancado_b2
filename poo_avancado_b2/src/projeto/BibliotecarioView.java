package projeto;

import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;

public class BibliotecarioView extends JFrame {

	/**
	 * Classe BibliotecarioView
	 */
	private static final long serialVersionUID = 1L;

	private ArrayList<Bibliotecario> bibliotecarios;
	private JTextField nomeBibliotecario;
	
	// normalizando texto
	static String normalizarTexto(String string) {
		if (string == null) {
			return "";
		}
		string = string.trim();
		if (string.isEmpty()) {
			System.out.println("O campo não pode ficar vazio.");
			return string;
		} else {
			return string.substring(0, 1).toUpperCase() + string.substring(1);
		}
	}
	
	// construtor da classe
	public BibliotecarioView(ArrayList<Bibliotecario> bibliotecarios) {
		this.bibliotecarios = bibliotecarios;

	    setTitle("Cadastro de Bibliotecario");
		setSize(450, 350);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);

		// Painel principal
		JPanel painel = new JPanel(new BorderLayout(10, 15));

		painel.setBorder(BorderFactory.createEmptyBorder(20, 25, 20, 25));

		// Cabeçalho
		JLabel titulo = new JLabel("CADASTRO DE BIBLIOTECÁRIO", SwingConstants.CENTER);

		titulo.setFont(new Font("Arial", Font.BOLD, 20));

		painel.add(titulo, BorderLayout.NORTH);

		// Painel dos campos
		JPanel campos = new JPanel(new GridLayout(1, 2, 10, 15));

		campos.add(new JLabel("Nome Completo:"));
		
		nomeBibliotecario = new JTextField();
		campos.add(nomeBibliotecario);

		
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

		String nome = nomeBibliotecario.getText().trim();

		// Validação dos campos vazios
		if (nome.isEmpty()) {

			JOptionPane.showMessageDialog(
					this, 
					"Preencha todos os campos!", 
					"Atenção", 
					JOptionPane.WARNING_MESSAGE);

			return;
		}

		// Normalização dos textos
		nome = normalizarTexto(nome);

		// Instanciação do objeto
		Bibliotecario novoBibliotecario = new Bibliotecario(nome);

		// Adiciona à lista
		bibliotecarios.add(novoBibliotecario);

	}

	private void limparCampos() {

		nomeBibliotecario.setText("");
		nomeBibliotecario.requestFocus();
	}

}
