package execoes;

public class ValidationException extends RuntimeException {
	
	public ValidationException() {
		super();
	}
	
	public ValidationException(String mensagem) {
		super(mensagem);
	}
	
}