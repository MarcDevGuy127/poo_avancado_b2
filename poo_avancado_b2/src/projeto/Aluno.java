package projeto;

public class Aluno {
	public String nome;
	private String email;
	private String endereco;
	public int qntdeEmprestimos; // talvez criar conta para multa
	
	public Aluno(String nome, String email, String endereco, int qntdeEmprestimos) {
		this.nome = nome;
		this.email = email;
		this.endereco = endereco;
		this.qntdeEmprestimos = qntdeEmprestimos;
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	public int getQntdeEmprestimos() {
		return qntdeEmprestimos;
	}
	public void setQntdeEmprestimos(int qntdeEmprestimos) {
		this.qntdeEmprestimos = qntdeEmprestimos;
	}
}
