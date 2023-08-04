package model.fighters;

import model.Fighter;
import model.Ship;

/**
 * Clase que crea cazas de tipo YWing
 * 
 * @author Noel Martinez Pomares 48771960T
 *
 */
public class YWing extends Fighter {
/**
 * LLama al constructor de la superclase y cambia las estadisticas
 * 
 * @param mother Nave del caza
 */
	public YWing(Ship mother) {
		super(mother);
		addShield(30);
		addVelocity(-20);
		addAttack(-10);
	}
	
/**
 * LLama al constructor de copia de la superclase
 * 
 * @param f Caza 
 */
	private YWing(YWing f) {
		super(f);
	}
	
/**
 * Crea una copia del caza
 * 
 * @return Devuelve la copia
 */
	public Fighter copy() {
		YWing fYWing=new YWing(this);
		return fYWing;
	}
	
/**
 * @return Devuelve el simbolo
 */
	public char getSymbol() {
        return 'Y';
	}
	
/**
 * @param n Numero aleatorio
 * @param enemy Caza enemigo
 * @return Devuelve el danyo
 */
	public int getDamage(int n,Fighter enemy) {
		int subDamage=super.getDamage(n, enemy);
		
		if(enemy.getType().equals("TIEBomber")) {
			return subDamage/2;
		}
		else if((enemy.getType().equals("TIEFighter"))||(enemy.getType().equals("TIEInterceptor"))) {
			return subDamage/3;
		}
		else {
			return subDamage;
		}
	}
}
