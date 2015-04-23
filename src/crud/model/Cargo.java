package crud.model;

import javax.persistence.*;

@Entity
public class Cargo {
	
	@Id
	@GeneratedValue
	private Integer id;
	
	private String nome;
	
	public Integer getId() {
		return id;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
}