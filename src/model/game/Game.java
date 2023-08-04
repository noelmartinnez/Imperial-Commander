package model.game;

import java.util.Objects;

import model.Side;
import model.exceptions.InvalidSizeException;
import model.game.score.DestroyedFightersScore;
import model.game.score.WinsScore;
import model.game.score.Ranking;

/**
 * Clase que maneja el juego
 * 
 * @author Noel Martinez Pomares 48771960T
 *
 */
public class Game {
/**
 * tamanyo estatico del tablero
 */
	private static final int BOARD_SIZE = 10;
	
/**
 * tablero del juego
 */
	private GameBoard board;

/**
 * jugadores
 */
	private IPlayer rebel,imperial;
	
/**
 * Metodo privado para sacar los rankings por pantalla
 * 
 * @param imperial imperial
 * @param rebel rebel
 */
	private void showRanking(IPlayer imperial,IPlayer rebel) {
		Ranking<WinsScore> rh = new Ranking<>();
		Ranking<DestroyedFightersScore> rc = new Ranking<>();
		
		rh.addScore(imperial.getGameShip().getWinsScore());
		rh.addScore(rebel.getGameShip().getWinsScore());
		
		rc.addScore(imperial.getGameShip().getDestroyedFightersScore());
		rc.addScore(rebel.getGameShip().getDestroyedFightersScore());
		
		System.out.print("RANKING WINS:  ");
		System.out.println(rh.toString());
		System.out.print("RANKING DESTROYED:  ");
		System.out.println(rc.toString());
	}
	
/**
 * Metodo constructor del juego
 * 
 * @param imperial jugador
 * @param rebel jugador
 */
	public Game(IPlayer imperial, IPlayer rebel) {
		Objects.requireNonNull(imperial);
		Objects.requireNonNull(rebel);
		try {
			this.imperial=imperial;
			this.rebel=rebel;
			
			board=new GameBoard(Game.BOARD_SIZE);
			
			this.rebel.setBoard(board);
			this.imperial.setBoard(board);
		} catch (InvalidSizeException e) {
			throw new RuntimeException();
		}
	}
	
/**
 * Devuelve el tablero
 * 
 * @return tablero
 */
	public GameBoard getGameBoard() {
		return board;
	}
	
/**
 * LLeva a cabo el juego entre los dos jugadores
 * 
 * @return bando del ganador
 */
	public Side play() {
		String nada = "";
		imperial.initFighters();
		rebel.initFighters();
		Side imp = Side.IMPERIAL;
		Side reb = Side.REBEL;
		Side ganador=null;
		boolean exit = true;
		
		while(exit == true) {
			showRanking(imperial,rebel);
			System.out.println("BEFORE IMPERIAL");
			System.out.println(board.toString());
			System.out.println(nada);
			System.out.println(imperial.showShip());
			System.out.println(rebel.showShip());
			System.out.print("IMPERIAL("+board.numFighters(imp)+"): ");
			
			exit=imperial.nextPlay();
			if(!exit) {
				ganador=Side.REBEL;
				break;
			}
			
			System.out.println("AFTER IMPERIAL, BEFORE REBEL");
			System.out.println(board.toString());
			System.out.println(nada);
			System.out.println(imperial.showShip());
			System.out.println(rebel.showShip());
			
			if((imperial.isFleetDestroyed()==true) || (rebel.isFleetDestroyed()==true)) {
				break;
			}
			
			System.out.print("REBEL("+board.numFighters(reb)+"): ");
			
			exit=rebel.nextPlay();
			if(!exit) {
				ganador=Side.IMPERIAL;
				break;
			}
			
			System.out.println("AFTER REBEL");
			System.out.println(board.toString());
			System.out.println(nada);
			System.out.println(imperial.showShip());
			System.out.println(rebel.showShip());
			
			imperial.purgeFleet();
			rebel.purgeFleet();
			
			if((imperial.isFleetDestroyed()==true) || (rebel.isFleetDestroyed()==true)) {
				break;
			}
		}
		
		imperial.purgeFleet();
		rebel.purgeFleet();
		
		showRanking(imperial,rebel);
		
		if(imperial.isFleetDestroyed()==true) {
			return Side.REBEL;
		}
		
		if(rebel.isFleetDestroyed()==true) {
			return Side.IMPERIAL;
		}
		
		return ganador;
	}
}
