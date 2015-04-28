package crud.model;

import javax.persistence.*;

@Entity
@Table(name = "CARGO", uniqueConstraints = {
	@UniqueConstraint(columnNames ="ID"),
	@UniqueConstraint(columnNames = "NOME") })

public class Cargo{
	
	@Id
	@GeneratedValue
	@Column(name = "ID", unique = true, nullable = false)
	public Integer id;
	
	@Column(name = "NOME", unique = true, nullable = false)
	public String nome;
	
	public Cargo() {
	}
	
	public Cargo (String nome) {
		this.nome = nome;
	}
	
}