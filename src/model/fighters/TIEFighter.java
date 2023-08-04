package model.fighters;

import model.Fighter;
import model.Ship;

/**
 * Clase que crea cazas de tipo TIEFighter
 * 
 * @author Noel Martinez Pomares 48771960T
 *
 */
public class TIEFighter extends Fighter {
/**
 * LLama al constructor de la superclase y cambia las estadisticas
 * 
 * @param mother Nave del caza
 */
	public TIEFighter(Ship mother) {
		super(mother);
		addShield(-10);
		addVelocity(10);
		addAttack(5);
	}
	
/**
 * LLama al constructor de copia de la superclase
 * 
 * @param f Caza 
 */
	private TIEFighter(TIEFighter f) {
		super(f);
	}
	
/**
 * Crea una copia del caza
 * 
 * @return Devuelve la copia
 */
	public Fighter copy() {
		TIEFighter fFighter=new TIEFighter(this);
		return fFighter;
	}
	
/**
 * @return Devuelve el simbolo
 */
	public char getSymbol() {
        return 'f';
	}
}
