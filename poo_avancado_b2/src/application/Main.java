package application;

import java.io.FileWriter;
import java.io.IOException;

public class Main {

	public static void main(String[] args) throws IOException {
		// registrando arquivo
		
		FileWriter writer = new FileWriter("registros.txt");
		writer.write("Ana;8.5/n");
		writer.write("Carlos;7.0/n");
		writer.write("Marina;9.2/n");
		writer.close();
		System.out.println("Arquivo gerado com sucesso!");
		
	}

}
