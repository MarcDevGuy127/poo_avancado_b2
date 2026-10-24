package projeto;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Biblioteca {
	private ArrayList<Aluno> aluno;
	private ArrayList<Bibliotecario> bibliotecario;
	private ArrayList<Livro> livro;
	
	
	public Biblioteca(ArrayList<Aluno> aluno, ArrayList<Bibliotecario> bibliotecario, ArrayList<Livro> livro) {
		this.aluno = aluno;
		this.bibliotecario = bibliotecario;
		this.livro = livro;
	}
	
	

	public ArrayList<Aluno> getAluno() {
		return aluno;
	}

	public ArrayList<Bibliotecario> getBibliotecario() {
		return bibliotecario;
	}

	public ArrayList<Livro> getLivro() {
		return livro;
	}
	
}
