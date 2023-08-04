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
public class NoFighterAvailableException extends Exception {
/**
 * Atributo tipo
 */
	private String type;
    
/**
 * Constructor de la excepcion
 * 
 * @param type Tipo que ha dado error
 */
    public NoFighterAvailableException(String type) {
        super();
        this.type = type;
    }
    
/**
 * @return Devuelve mensaje de error 
 */
    public String getMessage() {
        return "ERROR: Theres's no fighters with that type - "+type;
    }
}
