package model.game;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;

import model.Coordinate;
import model.RandomNumber;
import model.Side;
import model.exceptions.FighterAlreadyInBoardException;
import model.exceptions.FighterNotInBoardException;
import model.exceptions.OutOfBoundsException;
import model.game.exceptions.WrongFighterIdException;
import model.game.score.DestroyedFightersScore;
import model.game.score.WinsScore;

/**
 * Clase que maneja la jugada del jugador
 * 
 * @author Noel Martinez Pomares 48771960T
 *
 */
public class PlayerRandom implements IPlayer{
/**
 * numero de jugadores en el tablero
 */
	private int numFighters;
	
/**
 * nave del jugador
 */
	private GameShip ship;
	
/**
 * tablero del jugador
 */
	private GameBoard board;
	
/**
 * Constructor de la clase
 * 
 * @param side bando de la nave
 * @param numFighters numero de jugadores
 */
	public PlayerRandom(Side side,int numFighters) {
		Objects.requireNonNull(side);
		Side s = Side.REBEL;
		
		if(side.equals(s)) {
			String name="PlayerRandom REBEL Ship";
			ship=new GameShip(name,side);
		}
		else {
			String name="PlayerRandom IMPERIAL Ship";
			ship=new GameShip(name,side);
		}
		
		this.numFighters=numFighters;
	}
	
	@Override
	public void setBoard(GameBoard gb) {
		Objects.requireNonNull(gb);
		board=gb;
	}
	
	@Override
	public GameShip getGameShip() {
		return ship;
	}
	
	@Override
	public boolean isFleetDestroyed() {
		return this.ship.isFleetDestroyed();
	}
	
	@Override
	public void purgeFleet() {
		this.ship.purgeFleet();
	}
	
	@Override
	public String showShip() {
		String newShowShip="";
		
		newShowShip=newShowShip+this.ship.toString()+"\n";
		newShowShip=newShowShip+this.ship.showFleet();
		
		return newShowShip;
	}
	
	@Override
	public void initFighters() {
		Side s = Side.REBEL;
		int num1,num2,num3;
		String initString="";
		
		if(ship.getSide().equals(s)) {
			num1=RandomNumber.newRandomNumber(numFighters);
			num2=RandomNumber.newRandomNumber(numFighters);
			num3=RandomNumber.newRandomNumber(numFighters);
			
			if(num1 != 0) {
				initString=initString+num1+"/XWing";
				
				if(num2 != 0) {
					initString=initString+":";
				}
				else if(num3 != 0) {
					initString=initString+":";
				}
			}
			
			if(num2 != 0) {
				initString=initString+num2+"/YWing";
				
				if(num3 != 0) {
					initString=initString+":";
				}
			}
			
			if(num3 != 0) {
				initString=initString+num3+"/AWing";
			}
		}
		else {
			num1=RandomNumber.newRandomNumber(numFighters);
			num2=RandomNumber.newRandomNumber(numFighters);
			num3=RandomNumber.newRandomNumber(numFighters);
			
			if(num1 != 0) {
				initString=initString+num1+"/TIEFighter";
				
				if(num2 != 0) {
					initString=initString+":";
				}
				else if(num3 != 0) {
					initString=initString+":";
				}
			}
			
			if(num2 != 0) {
				initString=initString+num2+"/TIEBomber";
				
				if(num3 != 0) {
					initString=initString+":";
				}
			}
			
			if(num3 != 0) {
				initString=initString+num3+"/TIEInterceptor";
			}
		}
		
		if(initString != "") {
			ship.addFighters(initString);
		}
	}
	
	@Override
	public boolean nextPlay() {
		int option=RandomNumber.newRandomNumber(100);
		
		if(option == 99) {
			return false;
		}
		
		if((option>=85)&&(option<=98)) {
			List<Integer> idsBoard = ship.getFightersId("board");
			List<Integer> idsShip = ship.getFightersId("ship");
			Set<Integer> idsTreeset=new TreeSet<Integer>();
			
			for(int i=0;i<idsBoard.size();i++) {
				idsTreeset.add(idsBoard.get(i));
			}
			
			for(int j=0;j<idsShip.size();j++) {
				idsTreeset.add(idsShip.get(j));	
			}
			
			List<Integer> ids = new ArrayList<Integer> (idsTreeset);
			
			if(ids.isEmpty()) {
				System.out.println("ERROR: The ids list is empty IMPROVE");
			}
			else {
				int posicion = RandomNumber.newRandomNumber(ids.size());
				int id = ids.get(posicion);
				
				try {
					ship.improveFighter(id, option, board);
					
				} catch (WrongFighterIdException e) {
					throw new RuntimeException(e);
				}
			}
			
		}
		
		if((option>=25)&&(option<=84)) {
			List<Integer> ids = ship.getFightersId("ship");
			
			if(ids.isEmpty()) {
				System.out.println("ERROR: The ids list is empty LAUNCH");
			}
			else {
				int posicion = RandomNumber.newRandomNumber(ids.size());
				int id = ids.get(posicion);
				int x = RandomNumber.newRandomNumber(board.getSize());
				int y = RandomNumber.newRandomNumber(board.getSize());
				Coordinate new_c = new Coordinate(x,y);
				
				try {
					ship.launch(id, new_c, board);
					
				} catch (WrongFighterIdException | FighterAlreadyInBoardException | OutOfBoundsException e) {
					throw new RuntimeException(e);
				}
				
			}
		}
		
		if((option>=0)&&(option<=24)) {
			List<Integer> ids = ship.getFightersId("board");
			
			if(ids.isEmpty()) {
				System.out.println("ERROR: The ids list is empty PATROL");
			}
			else {
				int posicion = RandomNumber.newRandomNumber(ids.size());
				int id = ids.get(posicion);
				
				try {
					ship.patrol(id, board);
					
				} catch (WrongFighterIdException | FighterNotInBoardException | OutOfBoundsException e) {
					throw new RuntimeException(e);
				}	
				
			}
		}
		
		return true;
	}
	
	@Override
	public WinsScore getWinsScore() {
		return ship.getWinsScore();
	}
	
	@Override
	public DestroyedFightersScore getDestroyedFightersScore() {
		return ship.getDestroyedFightersScore();
	}
}
