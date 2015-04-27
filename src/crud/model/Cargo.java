package crud.model;

import javax.persistence.*;

@Entity
@Table(name = "CARGO", uniqueConstraints = {
	@UniqueConstraint(columnNames ="ID"),
	@UniqueConstraint(columnNames = "NOME") })
public class Cargo implements Comparable<Object>{
	
	@Id
	@GeneratedValue
	@Column(name = "ID", unique = true, nullable = false)
	private Integer id;
	
	@Column(name = "NOME", unique = true, nullable = false)
	private String nome;
	
	public Cargo() {
	}
	
	public Cargo (String nome) {
		this.nome = nome;
	}
	
	public Integer getId() {
		return id;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}

	@Override
	public int compareTo(Object o) {
	    if (!(o instanceof Cargo))
	        throw new ClassCastException();
	    
	    Cargo e = (Cargo) o;
	    
		return nome.compareTo(e.getNome());
	}
	
}