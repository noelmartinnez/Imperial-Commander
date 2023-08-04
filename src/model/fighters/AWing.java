package model.fighters;

import model.Fighter;
import model.Ship;

/**
 * Clase que crea cazas de tipo AWing
 * 
 * @author Noel Martinez Pomares 48771960T
 *
 */
public class AWing extends Fighter {
/**
 * LLama al constructor de la superclase y cambia las estadisticas
 * 
 * @param mother Nave del caza
 */
	public AWing(Ship mother) {
		super(mother);
		addShield(-50);
		addVelocity(40);
		addAttack(5);
	}
	
/**
 * LLama al constructor de copia de la superclase
 * 
 * @param f Caza 
 */
	private AWing(AWing f) {
		super(f);
	}
	
/**
 * Crea una copia del caza
 * 
 * @return Devuelve la copia
 */
	public Fighter copy() {
		AWing fAWing=new AWing(this);
		return fAWing;
	}
	
/**
 * @return Devuelve el simbolo
 */
	public char getSymbol() {
        return 'A';
	}
	
/**
 * @param n Numero aleatorio
 * @param enemy Caza enemigo
 * @return Devuelve el danyo
 */
	public int getDamage(int n,Fighter enemy) {
		int subDamage=super.getDamage(n, enemy);
		
		if(enemy.getType().equals("TIEBomber")) {
			return subDamage*2;
		}
		else {
			return subDamage;
		}
	}
}
