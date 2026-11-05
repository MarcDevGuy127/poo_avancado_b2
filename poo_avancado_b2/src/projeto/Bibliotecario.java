package projeto;

import java.util.ArrayList;
import java.util.Scanner;

public class Bibliotecario {
    public String nome;

    // Construtor
    public Bibliotecario(String nome) {
        this.nome = nome;
    }

    // Método estático para cadastrar um novo bibliotecário
    public static void cadastrar(Scanner scanner, ArrayList<Bibliotecario> bibliotecarios) {
        System.out.print("Digite o nome do bibliotecário: ");
        String nome = scanner.nextLine();

        if (nome == null || nome.trim().isEmpty()) {
            System.out.println("Nome inválido! Cadastro não realizado.\n");
            return;
        }

        bibliotecarios.add(new Bibliotecario(nome));
        System.out.println("Bibliotecário cadastrado com sucesso!\n");
    }

    // Método estático para consultar os bibliotecários cadastrados
    public static void consultar(ArrayList<Bibliotecario> bibliotecarios) {
        if (bibliotecarios.isEmpty()) {
            System.out.println("Nenhum bibliotecário cadastrado no sistema.\n");
            return;
        }

        System.out.println("--- LISTA DE BIBLIOTECÁRIOS ---");
        for (int i = 0; i < bibliotecarios.size(); i++) {
            System.out.println((i + 1) + " - Nome: " + bibliotecarios.get(i).nome);
        }
        System.out.println();
    }
}