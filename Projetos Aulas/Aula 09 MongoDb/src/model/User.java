package model;

import org.bson.Document;

public class User {
	private String nome;
	private String email;
	private int idade;
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
	public int getIdade() {
		return idade;
	}
	public void setIdade(int idade) {
		this.idade = idade;
	}
	@Override
	public String toString() {
		return "User [nome=" + nome + ", email=" + email + ", idade=" + idade + "]";
	}
	

	public String toJson() {
		return "{nome: '"+nome+"', email: '"+email+"', idade: '"+idade+"'}";
	}
	
	public User(String nome, String email, int idade) {
		super();
		this.nome = nome;
		this.email = email;
		this.idade = idade;
	}
	
	public static User fromDocument(Document doc) {
		return new User(doc);
	}
	
	private User(Document doc) {
		this(doc.getString("nome"), doc.getString("email"), doc.getInteger("idade"));
	}
	
	public Document toDocument() {
		return Document.parse(this.toJson());
	}
	
}
