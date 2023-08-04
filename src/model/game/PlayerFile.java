package model.game;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Objects;

import model.Coordinate;
import model.Fighter;
import model.Side;
import model.exceptions.FighterAlreadyInBoardException;
import model.exceptions.FighterNotInBoardException;
import model.exceptions.NoFighterAvailableException;
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
public class PlayerFile implements IPlayer{
/**
 * buffer
 */
	private BufferedReader br;
	
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
 * @param br buffer
 */
	public PlayerFile(Side side,BufferedReader br) {
		Objects.requireNonNull(side);
		Objects.requireNonNull(br);
		
		Side s = Side.REBEL;
		
		if(side.equals(s)) {
			String name="PlayerFile REBEL Ship";
			ship=new GameShip(name,side);
		}
		else {
			String name="PlayerFile IMPERIAL Ship";
			ship=new GameShip(name,side);
		}
		
		this.br=br;
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
	public boolean nextPlay() {
		String nextLine = "";
		try {
			nextLine=nextLine+br.readLine();
			
			String[] cadena = nextLine.split("\\s+");
			
			if(cadena[0].equals("exit")) {
				return false;
			} 
			else if(cadena[0].equals("improve")) {
				String[] partes = nextLine.split("\\s+");
				
				if(partes.length == 3) {
					String st1=partes[1];
					String st2=partes[2];
					
					int id= Integer.parseInt(st1);
					int qty= Integer.parseInt(st2);
					
					if(qty < 100) {
						try {
							ship.improveFighter(id, qty, board);
						} catch (WrongFighterIdException e) {
							System.out.println("ERROR: No debió lanzarse: "+e.getMessage());
						}
					}
					else {
						System.out.println("ERROR: qty it's higher than 100");
					}
				}
				else {
					System.out.println("ERROR: length it's not 3");
				}
			} else if(cadena[0].equals("patrol")) {
				String[] partesPatrol = nextLine.split("\\s+");
				
				if(partesPatrol.length == 2) {
					String st1=partesPatrol[1];
					
					Integer id= Integer.parseInt(st1);
					
					try {
						ship.patrol(id, board);
					} catch (WrongFighterIdException | FighterNotInBoardException | OutOfBoundsException e) {
						System.out.println("ERROR: No debió lanzarse: "+e.getMessage());
					}	
				}
				else {
					System.out.println("ERROR: length it's not 2");
				}
			} else if(cadena[0].equals("launch")) {
				String[] partesLaunch = nextLine.split("\\s+");
				
				if((partesLaunch.length == 4) || (partesLaunch.length == 3)) {
					if(partesLaunch.length == 3){
						String st1=partesLaunch[1];
						String st2=partesLaunch[2];
						
						Integer x= Integer.parseInt(st1);
						Integer y= Integer.parseInt(st2);
						
						Coordinate c = new Coordinate(x,y);
						try {
							Fighter f = ship.getFirstAvailableFighter("");
							try {
								ship.launch(f.getId(), c, board);
							} catch (WrongFighterIdException | FighterAlreadyInBoardException
									| OutOfBoundsException e) {
								System.out.println("ERROR: No debió lanzarse: "+e.getMessage());
							}
						} catch (NoFighterAvailableException e) {
							System.out.println("ERROR: No debió lanzarse: "+e.getMessage());
						}
					}
					
					if(partesLaunch.length == 4) {
						String st1=partesLaunch[1];
						String st2=partesLaunch[2];
						String st3=partesLaunch[3];
						
						try {
							Integer id= Integer.parseInt(st3);
							Integer x= Integer.parseInt(st1);
							Integer y= Integer.parseInt(st2);
							
							Coordinate c = new Coordinate(x,y);
							
							try {
								ship.launch(id, c, board);
							} catch (WrongFighterIdException | FighterAlreadyInBoardException
									| OutOfBoundsException e) {
								System.out.println("ERROR: No debió lanzarse: "+e.getMessage());
							}
							
						}
						catch(NumberFormatException e) {
							Integer x= Integer.parseInt(st1);
							Integer y= Integer.parseInt(st2);
							
							Coordinate c = new Coordinate(x,y);
							
							try {
								Fighter f = ship.getFirstAvailableFighter(st3);
								
								try {
									ship.launch(f.getId(), c, board);
								} catch (WrongFighterIdException | FighterAlreadyInBoardException
										| OutOfBoundsException e1) {
									System.out.println("ERROR: No debió lanzarse: "+e.getMessage());
								}
								
							} catch (NoFighterAvailableException e1) {
								System.out.println("ERROR: No debió lanzarse: "+e.getMessage());
							}
						}
					}
				}
				else {
					System.out.println("ERROR: length it's not 4 or 3");
				}
			}
			else {
				System.out.println("ERROR: Ningún movimiento elegido");
			}
			
			return true;
			
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	@Override
	public void initFighters() {
		String bufferLine="";
		
		try {
			bufferLine=br.readLine();
			ship.addFighters(bufferLine);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
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
