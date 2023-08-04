package model.fighters;

import model.Fighter;
import model.Ship;

/**
 * Clase que crea cazas de tipo TIEInterceptor
 * 
 * @author Noel Martinez Pomares 48771960T
 *
 */
public class TIEInterceptor extends Fighter {
/**
 * LLama al constructor de la superclase y cambia las estadisticas
 * 
 * @param mother Nave del caza
 */	
	public TIEInterceptor(Ship mother) {
		super(mother);
		addShield(-20);
		addVelocity(45);
		addAttack(5);
	}
	
/**
 * LLama al constructor de copia de la superclase
 * 
 * @param f Caza 
 */
	private TIEInterceptor(TIEInterceptor f) {
		super(f);
	}
	
/**
 * Crea una copia del caza
 * 
 * @return Devuelve la copia
 */
	public Fighter copy() {
		TIEInterceptor fInterceptor=new TIEInterceptor(this);
		return fInterceptor;
	}
	
/**
 * @return Devuelve el simbolo
 */
	public char getSymbol() {
        return 'i';
	}
	
/**
 * @param n Numero aleatorio
 * @param enemy Caza enemigo
 * @return Devuelve el danyo
 */
	public int getDamage(int n,Fighter enemy) {
		int subDamage=super.getDamage(n, enemy);
		
		if(enemy.getType().equals("AWing")) {
			return subDamage/2;
		}
		else if(enemy.getType().equals("YWing")) {
			return subDamage*2;
		}
		else {
			return subDamage;
		}
	}
}
