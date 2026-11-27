package projeto;

import java.util.ArrayList;

public class Biblioteca {
    private ArrayList<Aluno> aluno;
    private ArrayList<Bibliotecario> bibliotecario;
    private ArrayList<Livro> livro;

    public Biblioteca() {
        this.aluno = new ArrayList<>();
        this.bibliotecario = new ArrayList<>();
        this.livro = new ArrayList<>();
    }

    public Biblioteca(ArrayList<Aluno> aluno, ArrayList<Bibliotecario> bibliotecario, ArrayList<Livro> livro) {
        this.aluno = aluno != null ? aluno : new ArrayList<>();
        this.bibliotecario = bibliotecario != null ? bibliotecario : new ArrayList<>();
        this.livro = livro != null ? livro : new ArrayList<>();
    }

    public ArrayList<Aluno> getAluno() {
        return aluno;
    }

    public void setAluno(ArrayList<Aluno> aluno) {
        this.aluno = aluno != null ? aluno : new ArrayList<>();
    }

    public ArrayList<Bibliotecario> getBibliotecario() {
        return bibliotecario;
    }

    public void setBibliotecario(ArrayList<Bibliotecario> bibliotecario) {
        this.bibliotecario = bibliotecario != null ? bibliotecario : new ArrayList<>();
    }

    public ArrayList<Livro> getLivro() {
        return livro;
    }

    public void setLivro(ArrayList<Livro> livro) {
        this.livro = livro != null ? livro : new ArrayList<>();
    }
}