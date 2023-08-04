package model.game.score;

import java.util.Objects;

import model.Side;

/**
 * Clase que maneja la puntuacion del jugador
 * 
 * @author Noel Martinez Pomares 48771960T
 *
 */
public abstract class Score<T> implements Comparable<Score<T>> {
/**
 * bando
 */
	private Side side;
	
/**
 * cantidad de victorias
 */
	protected int score;
	
/**
 * Constructor de la clase
 * 
 * @param side bando
 */
	public Score(Side side) {
		Objects.requireNonNull(side);
		
		this.side=side;
		score=0;
	}
	
/**
 * Metodo getter
 * 
 * @return score
 */
	public int getScore() {
		return score;
	}
	
/**
 * Metodo toString
 * 
 * @return devulve la informacion de victorias del jugador
 */
	public String toString() {
		Side s = Side.REBEL;
		String salida="";
		
		if(side.equals(s)) {
			salida="Player REBEL: "+score;
			
			return salida;
		}else {
			salida="Player IMPERIAL: "+score;
			
			return salida;
		}
	}
	
	@Override
	public int compareTo(Score<T> other) {
		Objects.requireNonNull(other);
		
		if(score == other.getScore()) {
			return side.compareTo(other.side);
		}
		return other.getScore() - score;
	}
	
/**
 * Metodo abstracto
 * 
 * @param sc objeto
 */
	public abstract void score(T sc);
}