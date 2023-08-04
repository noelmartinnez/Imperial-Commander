package model.game.score;

import model.Side;

/**
 * Clase que maneja las victorias del jugador
 * 
 * @author Noel Martinez Pomares 48771960T
 *
 */
public class WinsScore extends Score<Integer> {
/**
 * Constructor
 * 
 * @param side bando
 */
	public WinsScore(Side side) {
		super(side);
	}
	
/**
 * Metodo que aumenta en 1 las victorias del jugador
 * 
 * @param w resultado
 */
	public void score(Integer w) {
		if(w != null && w == 1) {
			super.score++;
		}
	}
}
