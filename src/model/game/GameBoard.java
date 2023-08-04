package model.game;

import java.util.Set;

import model.Board;
import model.Coordinate;
import model.Fighter;
import model.Side;
import model.exceptions.InvalidSizeException;

/**
 * Clase que maneja el tablero del jugador
 * 
 * @author Noel Martinez Pomares 48771960T
 *
 */
public class GameBoard extends Board {
/**
 * Constructor de la clase
 * 
 * @param size tamanyo del tablero
 * @throws InvalidSizeException si es menor que cinco
 */
	public GameBoard(int size) throws InvalidSizeException {
		super(size);
	}
	
/**
 * Metodo que devulve el tablero en un string
 * 
 * @return cadena
 */
	public String toString() {
		String tablero = "  ";
		int tamanyo=super.getSize();
		
		for(int i=0;i<tamanyo;i++) {
			tablero=tablero+i;
		}
		tablero=tablero+"\n";
		tablero=tablero+"  ";
		
		for(int j=0;j<tamanyo;j++) {
			tablero=tablero+"-";
		}
		tablero=tablero+"\n";
		
		for(int y=0;y<tamanyo;y++) {
			tablero=tablero+y+"|";
			for(int x=0;x<tamanyo;x++) {
				Coordinate c = new Coordinate(x,y);
				Fighter f=null;
				
				if(board.containsKey(c)) {
					f = board.get(c).copy();
					
				}
				if(f != null) {
					tablero=tablero+f.getSymbol();
				}else {
					tablero=tablero+" ";
				}
			}
			if((y+1) != tamanyo) {
				tablero=tablero+"\n";
			}
		}
		
		return tablero;
	}
	
/**
 * Metodo que devulve el numero de fighters que hay posicionados en el tablero
 * 
 * @param side tamanyo del tablero
 * @return numero de cazas
 */
	public int numFighters(Side side) {
		Set<Coordinate> coordenadas = board.keySet();
		int contador=0;
		
		for(Coordinate newCord : coordenadas) {
			Fighter newFighter = board.get(newCord);
			if(newFighter.getSide().equals(side)) {
				contador++;
			}
		}
		return contador;
	}
}