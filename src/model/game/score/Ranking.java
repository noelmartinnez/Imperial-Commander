package model.game.score;

import java.util.Objects;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * Clase que maneja los rankings del juego
 * 
 * @author Noel Martinez Pomares 48771960T
 *
 */
public class Ranking<ScoreType extends Score<?>> {
/**
 * lista de puntuaciones
 */
	private SortedSet<ScoreType> scoreSet;
	
/**
 * Constructor que inicializa la lista
 */
	public Ranking(){
		scoreSet = new TreeSet<>();
	}
	
/**
 * Metodo getter
 * 
 * @return scoreSet
 */
	public SortedSet<ScoreType> getSortedRanking(){
		return scoreSet;
	}
	
/**
 * Metodo que añade un objeto a la lista
 * 
 * @param st objeto
 */
	public void addScore(ScoreType st) {
		Objects.requireNonNull(st);
		
		scoreSet.add(st);
	}
	
/**
 * Metodo getter que devuelve el ganador
 * 
 * @return primer objeto en la lista
 */
	public ScoreType getWinner() {
		if(scoreSet.isEmpty()) {
			throw new RuntimeException(); 
		}else {
			return scoreSet.first();
		}
	}
	
/**
 * Metodo toString
 * 
 * @return ranking a mostrar
 */
	public String toString() {
		String toString="|";
		
		for(ScoreType type : scoreSet) {
			toString=toString+" "+type.toString()+" |";
		}
		
		return toString;
	}
}
