package model.game.score;

import java.util.Objects;

import model.Fighter;
import model.Side;

/**
 * Clase que maneja los puntos de los cazas destruidos
 * 
 * @author Noel Martinez Pomares 48771960T
 *
 */
public class DestroyedFightersScore extends Score<Fighter> {
/**
 * Constructor de la clase
 * 
 * @param side bando
 */
	public DestroyedFightersScore(Side side) {
		super(side);
	}
	
/**
 * Obtiene el valor de los cazas destruido
 * 
 * @param f caza destruido
 */
	public void score(Fighter f) {
		
		if(f!=null) {
			super.score=super.score + f.getValue();
		}
	}
}
