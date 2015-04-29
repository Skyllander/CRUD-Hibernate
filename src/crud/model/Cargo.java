package crud.model;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.persistence.*;

import execoes.ValidacaoException;

@Entity
@Table(name = "CARGO", uniqueConstraints = {
	@UniqueConstraint(columnNames ="ID"),
	@UniqueConstraint(columnNames = "NOME") })

public class Cargo{
	
	@Transient
	private CargoDAO dao = new CargoDAO();
	
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
	
	private void validarCargoMesmoNome() {
		
		Cargo cargoMesmoNome = dao.buscaPorNome(this.nome);
		
		if (cargoMesmoNome != null) {
			throw new ValidacaoException("Nome de usuario ja existente");
		}
		
	}
	
	private void validarNomeComNumeros() {
		
		Pattern p = Pattern.compile("[0-9]*.*[0-9]+.*[0-9]*");
		Matcher m = p.matcher(nome);
		
		if (m.matches()) {
			throw new ValidacaoException("Nome nao pode conter numeros");
		}
	}
	
	private void validarNomeObrigatorio() {
		
		if (nome.isEmpty()) {
			throw new ValidacaoException("Campo de nome e obrigatorio");
		}
		
	}
	
	private void validar() {
		//TODO Anotacoes de validacao ou implementar a mao (Validator)11.6
		validarCargoMesmoNome();
		validarNomeComNumeros();
		validarNomeObrigatorio();
		
	}
	
	public void cadastra() {
		
		validar();
		
		dao.adiciona(this);
		
	}
	
}