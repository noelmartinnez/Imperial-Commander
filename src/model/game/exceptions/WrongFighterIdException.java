package model.game.exceptions;

/**
 * 
 * Evita errores
 *
 */
@SuppressWarnings("serial")
/**
 * 
 * @author Noel Martinez Pomares 48771960T
 *
 */
public class WrongFighterIdException extends Exception {
	/**
	 * Atributo id
	 */
		private int id;
	    
	/**
	 * Constructor de la excepcion
	 * 
	 * @param id id que ha dado error
	 */
	    public WrongFighterIdException(int id) {
	        super();
	        this.id = id;
	    }
	    
	/**
	 * @return Devuelve mensaje de error 
	 */
	    public String getMessage() {
	        return "ERROR: All fighters are not destroyed - "+id;
	    }
}
