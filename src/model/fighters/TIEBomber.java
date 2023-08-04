package model.fighters;

import model.Fighter;
import model.Ship;

/**
 * Clase que crea cazas de tipo TIEBomber
 * 
 * @author Noel Martinez Pomares 48771960T
 *
 */
public class TIEBomber extends Fighter {
/**
 * LLama al constructor de la superclase y cambia las estadisticas
 * 
 * @param mother Nave del caza
 */
	public TIEBomber(Ship mother) {
		super(mother);
		addShield(35);
		addVelocity(-30);
		addAttack(-50);
	}
	
/**
 * LLama al constructor de copia de la superclase
 * 
 * @param f Caza 
 */
	private TIEBomber(TIEBomber f) {
		super(f);
	}
	
/**
 * Crea una copia del caza
 * 
 * @return Devuelve la copia
 */
	public Fighter copy() {
		TIEBomber fBomber=new TIEBomber(this);
		return fBomber;
	}

/**
 * @return Devuelve el simbolo
 */	
	public char getSymbol() {
        return 'b';
	}
	
/**
 * @param n Numero aleatorio
 * @param enemy Caza enemigo
 * @return Devuelve el danyo
 */
	public int getDamage(int n,Fighter enemy) {
		int subDamage=super.getDamage(n, enemy);
		
		if(enemy.getType().equals("AWing")) {
			return subDamage/3;
		}
		else if((enemy.getType().equals("XWing"))||(enemy.getType().equals("YWing"))) {
			return subDamage/2;
		}
		else {
			return subDamage;
		}
	}
}
