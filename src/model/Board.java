package model;
import java.util.Map;
import java.util.Objects;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import model.exceptions.FighterAlreadyInBoardException;
import model.exceptions.FighterIsDestroyedException;
import model.exceptions.FighterNotInBoardException;
import model.exceptions.InvalidSizeException;
import model.exceptions.OutOfBoundsException;

/**
 * Clase que representa el tablero en el que se desarrollara el juego
 * 
 * @author Noel Martinez Pomares 48771960T
 *
 */
public class Board {
/**
 * El atributo size es el tamanyo del tablero
 */
	private int size;
	
/**
 * Board es el mapa que almacena los cazas en posiciones del tablero
 */
	protected Map<Coordinate,Fighter> board;
	
/**
 * Constructor que inicializa los datos del tablero
 * 
 * @param size Tamanyo del tablero
 * @throws InvalidSizeException Lanza la excepcion si el tamanyo indicado es menor que 5
 */
	public Board(int size) throws InvalidSizeException {
		if(size < 5) {
			throw new InvalidSizeException(size);
		}
		
		this.size=size;
		board=new HashMap<Coordinate,Fighter>();
	}
	
/**
* @return Devuelve el tamanyo
*/
	public int getSize() {
		return size;
	}
	
/**
 * Metodo que devuelve el contenido del tablero en una posicion indicada
 * 
 * @param c Coordenada a comprobar
 * @return Devuelve el caza 
 */
	public Fighter getFighter(Coordinate c) {
		Objects.requireNonNull(c);
		
		Fighter newFighter=null;
		
		if(board.containsKey(c)) {
			newFighter = board.get(c).copy();
			
		}
		return newFighter;
	}
	
/**
 * Metodo que borra del tablero el caza que esta en la posicion del caza pasado por parametro,
 * si estos son iguales
 * 
 * @param f Caza por parametro
 * @throws FighterNotInBoardException Lanza la excepcion cuando el caza no esta en el tablero o no es igual que 
 * el caza situado en su posicion
 */
	public void removeFighter(Fighter f) throws FighterNotInBoardException{
		Objects.requireNonNull(f);
		
		if(board.containsValue(f) == false || getFighter(f.getPosition())==null) {
			throw new FighterNotInBoardException(f);
		}
		
		if(board.containsKey(f.getPosition())) {
			if(f.equals(board.get(f.getPosition()))) {
				board.remove(f.getPosition());
				f.setPosition(null);
				
			}
			else {
				throw new FighterNotInBoardException(f);
			}
		}
	}

/**
 * Metodo que comprueba que la coordenada pasado por parametro, esta dentro del tal tablero y que
 * no se sale de sus limites
 * 
 * @param c Coordenada a comprobar
 * @return Devuelve true si esta en el tablero y false si no lo esta
 */
	public boolean inside(Coordinate c) {
		Objects.requireNonNull(c);
		
		if(c.equals(null)) {
			return false;
		}
		
		if(((c.getX()>=0) && (c.getX()<=size-1)) && ((c.getY()>=0) && (c.getY()<=size-1))) {
			return true;
		}			
		
		return false;
	}
	
/**
 * Metodo que devuelve las posiciones vecinas a una coordenada pasada por parametro, que
 * esten dentro del tablero
 * 
 * @param c Coordenada a comprobar sus vecinos
 * @return TreeSet con las coordendas de los vecinos
 * @throws OutOfBoundsException La coordenada está fuera del tablero
 */
	public Set<Coordinate> getNeighborhood(Coordinate c) throws OutOfBoundsException{
		Objects.requireNonNull(c);
		
		if(inside(c)==false) {
			throw new OutOfBoundsException(c);
		}
		
		Set<Coordinate> newSet = new TreeSet<Coordinate>();
		
		for(int i=-1;i<=1;i++) {
			for(int j=-1;j<=1;j++) {
				if(!(i==0 && j==0)) {
					if((inside(new Coordinate(c.getX()+i,c.getY()+j)))) {
						newSet.add(new Coordinate(c.getX()+i,c.getY()+j));
					}
				}
			}
		}
		
		return newSet;
	}
	
/**
 * Metodo que intenta colocar un caza en una posicion del tablero. Si la coordenada esta ocupada por
 * un caza enemigo, luchara contra el hasta que uno de los dos muera
 * 
 * @param c Coordenada a colocar el caza
 * @param f Caza a colocar en la coordenada
 * @return Devuelve el resultado de la lucha (en caso que la haya) y en caso contrario devuelve 0
 * @throws FighterAlreadyInBoardException El caza ya tiene posicion
 * @throws OutOfBoundsException La coordenada está fuera del tablero
 */
	public int launch(Coordinate c,Fighter f) throws FighterAlreadyInBoardException, OutOfBoundsException {
		Objects.requireNonNull(c);
		Objects.requireNonNull(f);
		
		
			if(f.getPosition()!=null) {
				throw new FighterAlreadyInBoardException(f);
			}
			
			if(!inside(c)) {
				throw new OutOfBoundsException(c);
			}
			
			if(board.containsKey(c)) {
				if(board.get(c).getSide() == f.getSide()) {
					return 0;
				}
				else {
					
					try {
						switch(f.fight(board.get(c))) {
						case 1:
							f.getMotherShip().updateResults(1,board.get(c));
							board.get(c).getMotherShip().updateResults(-1,board.get(c));
							
							board.get(c).setPosition(null);
							f.setPosition(c);
							board.put(c,f);
							return 1;
							
						case -1:
							f.getMotherShip().updateResults(-1,f);
							board.get(c).getMotherShip().updateResults(1,f);
							return -1;
						}
					} catch (FighterIsDestroyedException e) {
						throw new RuntimeException();
					}
				}
			}
			
			f.setPosition(c);
			board.put(c,f);
			return 0;

	}
	
/**
 * Metodo que simula una patrulla del caza pasado por parametro por su vecindad, peleando contra
 * todos aquellos cazas enemigos que se encuentr
 * 
 * @param f Caza patrullero
 * @throws FighterNotInBoardException El caza no tiene posicion en el tablero
 * @throws OutOfBoundsException El caza está fuera del tablero
 */
	public void patrol(Fighter f) throws FighterNotInBoardException, OutOfBoundsException{
		Objects.requireNonNull(f);
		
		if(f.getPosition()==null) {
			throw new FighterNotInBoardException(f);
		}
		
		if(!inside(f.getPosition())) {
			throw new RuntimeException();
		}
		
		if(board.containsValue(f) == true) {
			List<Coordinate> deadSet=new ArrayList<Coordinate>();
			Set<Coordinate> newSet = getNeighborhood(f.getPosition());
			
			for(Coordinate CoordAux : newSet) {
				if(board.containsKey(CoordAux)) {
					if(board.get(CoordAux).getSide() != f.getSide()) {
						try {
							if(f.fight(board.get(CoordAux)) == 1) {
								deadSet.add(CoordAux);
								board.get(CoordAux).setPosition(null);
								f.getMotherShip().updateResults(1,board.get(CoordAux));
								board.get(CoordAux).getMotherShip().updateResults(-1,board.get(CoordAux));
							}
							else {
								deadSet.add(f.getPosition());
								board.remove(f.getPosition());
								f.setPosition(null);
								f.getMotherShip().updateResults(-1,f);
								board.get(CoordAux).getMotherShip().updateResults(1,f);
								break;
							}
						} catch (FighterIsDestroyedException e) {
							throw new RuntimeException();
						}
					}
				}		
			}
					
			for(int i=0;i<deadSet.size();i++) {
				board.remove(deadSet.get(i));
			}
		}
				
	}
}