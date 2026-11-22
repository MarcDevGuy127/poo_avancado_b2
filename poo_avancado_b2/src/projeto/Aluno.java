package projeto;

public class Aluno {
	private int id_aluno;
	public String nome;
	private String email;
	private String endereco;
	public int qntdeEmprestimos; // talvez criar conta para multa
	
	public Aluno(Integer id_aluno, String nome, String email, String endereco, int qntdeEmprestimos) {
		this.id_aluno = id_aluno;
		this.nome = nome;
		this.email = email;
		this.endereco = endereco;
		this.qntdeEmprestimos = qntdeEmprestimos;
	}
	
	public int getId_aluno() {
		return id_aluno;
	}
	public void setId_aluno(int id_aluno) {
		this.id_aluno = id_aluno;
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

	// retornando propriedades do objeto com uma string
	@Override
	public String toString() {
		return "Aluno{id=" + id_aluno + 
				", nome='" + nome + 
				", email='" + email + 
				",endereco='" + endereco + 
				", qntdeEmprestimos='" + id_aluno + "}";
	}

	// excecao para caso o objeto nao existir
	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		// realizando casting para considerar a instancia reserva de Reserva como a
		// instancia de Object o
		Aluno aluno = (Aluno) o;
		return id_aluno == aluno.id_aluno;
	}

	// gerando hash(seguranca) para o id
	@Override
	public int hashCode() {
		return Integer.hashCode(id_aluno);
	}
}
