package model.exceptions;
import model.Coordinate;

/**
 * Evita errores
 */
@SuppressWarnings("serial")
/**
 * 
 * @author Noel Martinez Pomares 48771960T
 *
 */
public class OutOfBoundsException extends Exception {
/**
 * Atributo coordenada
 */
	private Coordinate c;
    
/**
 * Constructor de la excepcion
 * 
 * @param c Coordenada que ha dado error
 */
    public OutOfBoundsException(Coordinate c) {
        super();
        this.c = c;
    }
    
/**
 * @return Devuelve mensaje de error 
 */
    public String getMessage() {
        return "ERROR: The coordinate is out of the board - "+c;
    }
}
