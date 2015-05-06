package crud.model;

import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.validation.*;

import execoes.ValidationException;

public abstract class Validate {

	private static ValidatorFactory validatorFactory =  
			Validation.buildDefaultValidatorFactory();
	
	public static Validator validator = getValidator();
	
	public static Validator getValidator() {
		return validatorFactory.getValidator();
	}
	
	public static void checkNumero(String numero) {
		validarNumeroComLetra(numero);
	}
	
	public static void checkNome(String nome) {
		validarInicioMaiscula(nome);
		validarNomeComNumeros(nome);
	}
	
	public static <T> Set<ConstraintViolation<T>> hibernateCheck(T obj) {
		return validator.validate(obj);
	}
	
	public static void validarNumeroComLetra(String numero) {
		Pattern p = Pattern.compile("[a-zA-Z]*.*[a-zA-Z]+.*[a-zA-Z]*");
		Matcher m = p.matcher(numero);

		if (m.matches()) {
			throw new ValidationException("Nome nao pode conter numeros");
		}
	}

	private static void validarInicioMaiscula(String nome) {

		Pattern p = Pattern.compile("[A-Z]+.*");
		Matcher m = p.matcher(nome);

		if (!m.matches()) {
			throw new ValidationException("Nome deve ser iniciado com letra maiuscula");
		}
	}

	private static void validarNomeComNumeros(String nome) {

		Pattern p = Pattern.compile("[0-9]*.*[0-9]+.*[0-9]*");
		Matcher m = p.matcher(nome);

		if (m.matches()) {
			throw new ValidationException("Nome nao pode conter numeros");
		}
	}
	

	
}