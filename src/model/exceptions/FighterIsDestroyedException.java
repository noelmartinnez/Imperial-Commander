package model.exceptions;
import model.Fighter;

/**
 * Evita que salga error
 */
@SuppressWarnings("serial")
/**
 * 
 * @author Noel Martinez Pomares 48771960T
 *
 */
public class FighterIsDestroyedException extends Exception {
/**
 * Atributo caza
 */
	private Fighter f;
    
/**
 * Constructor de la excepcion
 * 
 * @param f Caza que ha dado error
 */
    public FighterIsDestroyedException(Fighter f) {
        super();
        this.f = f;
    }
    
/**
 * @return Devuelve mensaje de error 
 */
    public String getMessage() {
        return "ERROR: Some of the fighters are already destroyed - "+f;
    }
}
