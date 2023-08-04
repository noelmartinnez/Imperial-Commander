package model.exceptions;

/**
 * Evita errores
 */
@SuppressWarnings("serial")
/**
 * 
 * @author Noel Martinez Pomares 48771960T
 *
 */
public class InvalidSizeException extends Exception {
/**
 * Atributo tamanyo
 */
	private int size;
    
/**
 * Constructor de la excepcion
 * 
 * @param size Tamanyo que ha dado error
 */
    public InvalidSizeException(int size) {
        super();
        this.size = size;
    }
    
/**
 * @return Devuelve mensaje de error 
 */
    public String getMessage() {
        return "ERROR: The size passed by argument is less than five - "+size;
    }
}
