/**
 * 
 */
package br.com.rpires.restaurante.exception;

/**
 * @author digao
 *
 */
public class SystemException extends Exception {

	private static final long serialVersionUID = -2801576659060984937L;
	
	public SystemException(Throwable cause) {
		super("CONTATE OADMINISTRADO DO SISTEMA", cause);
	}

	public SystemException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public SystemException(String message) {
		super(message);
	}

}
