package model.fighters;

import model.Fighter;
import model.Ship;

/**
 * Clase que crea cazas de tipo XWing
 * 
 * @author Noel Martinez Pomares 48771960T
 *
 */
public class XWing extends Fighter {
/**
 * LLama al constructor de la superclase y cambia las estadisticas
 * 
 * @param mother Nave del caza
 */
	public XWing(Ship mother) {
		super(mother);
		addVelocity(10);
		addAttack(20);
	}
	
/**
 * LLama al constructor de copia de la superclase
 * 
 * @param f Caza 
 */
	private XWing(XWing f) {
		super(f);
	}
	
/**
 * Crea una copia del caza
 * 
 * @return Devuelve la copia
 */
	public Fighter copy() {
		XWing fXWing=new XWing(this);
		return fXWing;
	}
	
/**
 * @return Devuelve el simbolo
 */
	public char getSymbol() {
        return 'X';
	}
}
