package model.exceptions;
import model.Fighter;

/**
 * Evita errores
 */
@SuppressWarnings("serial")
/**
 * 
 * @author Noel Martinez Pomares 48771960T
 *
 */
public class FighterNotInBoardException extends Exception {
/**
 * Atributo caza
 */
	private Fighter f;
    
/**
 * Constructor de la excepcion
 * 
 * @param f Caza que ha dado error
 */
    public FighterNotInBoardException(Fighter f) {
        super();
        this.f = f;
    }
  
/**
 * @return Devuelve mensaje de error 
 */    
    public String getMessage() {
        return "ERROR: The fighter has not position in the board - "+f;
    }
}
