package model.game;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import model.Board;
import model.Coordinate;
import model.Fighter;
import model.Ship;
import model.Side;
import model.exceptions.FighterAlreadyInBoardException;
import model.exceptions.FighterNotInBoardException;
import model.exceptions.OutOfBoundsException;
import model.game.exceptions.WrongFighterIdException;
import model.game.score.DestroyedFightersScore;
import model.game.score.WinsScore;

/**
 * Clase que maneja la nave del jugador
 * 
 * @author Noel Martinez Pomares 48771960T
 *
 */
public class GameShip extends Ship {
/**
 * victorias
 */
	private WinsScore winsScore;

/**
 * valor de cazas destruidos
 */
	private DestroyedFightersScore destroyedFightersScore;
	
/**
 * Metodo que busca en la flota de la nave un caza cuyo identificador coincida con el argumento id
 * 
 * @param id id del caza
 * @throws WrongFighterIdException Lanza la excepcion si el caza esta destruido o no se encuentra
 * @return caza
*/
	private Fighter getFighter(int id) throws WrongFighterIdException{
		for(int i=0;i<super.getFleetTest().size();i++) {
			if(super.getFleetTest().get(i).getId() == id) {
				if(super.getFleetTest().get(i).isDestroyed() == false) {
					return super.getFleetTest().get(i);
				}
			}
		}
		
		throw new WrongFighterIdException(id);
	}
	
/**
 * Constructor que llama a la clase madre
 * 
 * @param name nombre de la nave
 * @param side bando de la nave
 */
	public GameShip(String name,Side side) {
		super(name,side);
		winsScore=new WinsScore(side);
		destroyedFightersScore = new DestroyedFightersScore(side);
	}
	
/**
 * Metodo que comprueba si la flota esta toda destruida
 * 
 * @return true si si que lo esta, false si no
 */
	public boolean isFleetDestroyed() {
		boolean allDestroyed = false;
		
		if(super.getFleetTest() == null) {
			return true;
		}
		
		for(int i=0;i<super.getFleetTest().size();i++) {
			if(super.getFleetTest().get(i).isDestroyed() == false) {
				allDestroyed = true;
			}
		}
		
		if(allDestroyed == false) {
			return true;
		}
		else {
			return false;
		}		
	}
	
/**
 * Metodo que devuelve una lista de ids, segun la cadena que se le pase, que esten en el tablero, en la flota sin poner, o todos los de la nave
 * 
 * @param where cadena para sacar ids
 * @return la cadena
 */
	public List<Integer> getFightersId(String where){
		List<Integer> ids = new ArrayList<Integer>();
		
		if(where.equals("board")) {
			for(int i=0;i<super.getFleetTest().size();i++) {
				if((super.getFleetTest().get(i).getPosition() != null) && (super.getFleetTest().get(i).isDestroyed() == false)) {
					ids.add(super.getFleetTest().get(i).getId());
				}
			}
			
			return ids;
		}
		else if(where.equals("ship")) {
			for(int i=0;i<super.getFleetTest().size();i++) {
				if((super.getFleetTest().get(i).getPosition() == null) && (super.getFleetTest().get(i).isDestroyed() == false)) {
					ids.add(super.getFleetTest().get(i).getId());
				}
			}
			
			return ids;
		}
		else {
			for(int i=0;i<super.getFleetTest().size();i++) {
				if(super.getFleetTest().get(i).isDestroyed() == false) {
					ids.add(super.getFleetTest().get(i).getId());
				}
			}
			
			return ids;
		}
	}
	
/**
 * Metodo que llama al launch de la clase madre, con el id y la posicion del caza
 * 
 * @param id id del caza
 * @param c coordenada a posicionar
 * @param b tablero
 * @throws WrongFighterIdException la lanza el getFighter
 * @throws FighterAlreadyInBoardException Si el caza ya esta en el tablero
 * @throws OutOfBoundsException la coordenada esta fuera del tablero
 */
	public void launch(int id,Coordinate c,Board b) throws WrongFighterIdException, FighterAlreadyInBoardException, OutOfBoundsException {
		Objects.requireNonNull(c);
		Objects.requireNonNull(b);
		
		Fighter newFighter = getFighter(id);
		b.launch(c,newFighter);
	}
	
/**
 * Metodo que llama al patrol de la clase madre
 * 
 * @param id id del caza
 * @param b tablero del caza
 * @throws WrongFighterIdException la lanza getFighter
 * @throws FighterNotInBoardException Si el caza no esta en el tablero
 * @throws OutOfBoundsException la coordenada no esta en el tablero
 */
	public void patrol(int id,Board b) throws WrongFighterIdException, FighterNotInBoardException, OutOfBoundsException {
		Objects.requireNonNull(b);
		
		Fighter newFighter = getFighter(id);
		b.patrol(newFighter);
	}
	
/**
 * Metodo que mejora el caza indicado
 * 
 * @param id id del caza
 * @param qty cantidad a mejorar
 * @param b tablero
 * @throws WrongFighterIdException la lanza getFighter 
 */
	public void improveFighter(int id,int qty,Board b) throws WrongFighterIdException {
		Objects.requireNonNull(b);
		
		Fighter newFighter = getFighter(id);
		
		try {
			b.removeFighter(newFighter);
		} catch (FighterNotInBoardException e) {
		}
		
		if((qty % 2) == 0) {
			newFighter.addAttack(qty/2);
			newFighter.addShield(qty/2);
		}
		else {
			int newQTY=qty+1,newQTY_M=newQTY/2;
			
			newFighter.addAttack(newQTY_M-1);
			newFighter.addShield(newQTY_M);
		}
	}
	
/**
 * Metodo que devuelve el winscore
 * 
 * @return winscore
 */
	public WinsScore getWinsScore() {
		return winsScore;
	}
	
/**
 * Metodo que devuelve el destroyedFighterscore
 * 
 * @return destroyedFighterscore
 */
	public DestroyedFightersScore getDestroyedFightersScore() {
		return destroyedFightersScore;
	}
	
/**
 * Metodo de cambiar los resultados
 * 
 * @param r resultado
 * @param f caza destruido
 */
	public void updateResults(int r,Fighter f) {
		Objects.requireNonNull(f);
		
		super.updateResults(r, f);
		
		if(r == 1) {
			winsScore.score(r);
			destroyedFightersScore.score(f);
		}
	}
}
