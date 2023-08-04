package model.game;

import model.game.score.DestroyedFightersScore;
import model.game.score.WinsScore;

/**
 * Clase que maneja los jugadores
 * 
 * @author Noel Martinez Pomares 48771960T
 *
 */
public interface IPlayer {
/**
 * Metodo que asigna tablero
 * 
 * @param gb tablero
 */
	public void setBoard(GameBoard gb);
	
/**
 * Devuelve la nave
 * 
 * @return nave
 */
	public GameShip getGameShip();
	
/**
 * Metodo que inicializa los cazas de los jugadores
 */
	public void initFighters();
	
/**
 * Metodo que llama al metodo de la nave para ver si esta destruida su flota
 * 
 * @return true o false
 */
	public boolean isFleetDestroyed();
	
/**
 * Devuelve una cadena con toda la flota de los jugadores
 * 
 * @return cadena
 */
	public String showShip();
	
/**
 * Metodo que elimina los cazas eliminados
 */
	public void purgeFleet();
	
/**
 * Siguiente jugada del jugador 
 * 
 * @return true o false si es exit
 */
	public boolean nextPlay();
	
/**
 * Metodo getter
 * 
 * @return winscore
 */
	public WinsScore getWinsScore();
	
/**
 * Metodo getter
 * 
 * @return destroyedFighterscore
 */
	public DestroyedFightersScore getDestroyedFightersScore();
}
