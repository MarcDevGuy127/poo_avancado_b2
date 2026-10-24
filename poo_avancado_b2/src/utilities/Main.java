package application;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Main {
	static class Aluno {
		String nome;
		double media;

		Aluno(String nome, double media) {
			this.nome = nome;
			this.media = media;
		}

		public String toString() {
			return nome + " - " + media;
		}
	}

	public static void main(String[] args) throws IOException {
		// FileWriter writer = new FileWriter("registros.txt");
		// writer.write("Ana;8.5\n");
		// writer.write("Carlos;7.0\n");
		// writer.write("Marina;9.2\n");
		// writer.close();
		// System.out.println("Arquivo gerado com sucesso!");
		BufferedReader reader = new BufferedReader(new FileReader("registros.txt"));
		ArrayList<Aluno> alunos = new ArrayList<Aluno>();
		String linha;
		
		while ((linha = reader.readLine()) != null) {
			String[] partes = linha.split(";");
			String nome = partes[0];
			double media = Double.parseDouble(partes[1]);
			
			Aluno aluno = new Aluno(nome, media);
			alunos.add(aluno);
		}
		reader.close();
		for (Aluno aluno : alunos) {
			System.out.println(aluno);
		}
	}

}
