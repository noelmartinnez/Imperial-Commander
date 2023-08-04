package model;
import java.util.Set;
import java.util.TreeSet;

/**
 * El proposito de esta clase es usar y manejar una coordenada
 * 
 * @author Noel Martinez Pomares 48771960T
 *
 */

public class Coordinate implements Comparable<Coordinate> {
/**
 * Atributos que representan dos ejes
 */
	private int x,y;
	
/**
 * Constructor de la clase Coordinate pasando por parametro la coordinada x e y
 * 
 * @param x Eje x de la coordenada
 * @param y Eje y de la coordenada
 */
	public Coordinate(int x,int y) {
		this.x=x;
		this.y=y;
	}
	
/**
 * Constructor de copia de la clase Coordinate pasando como parametro otro objeto de la clase Coordinate
 * 
 * @param c Recibe otro objeto de la clase Coordinate
 */
	public Coordinate(Coordinate c) {
		x=c.x;
		y=c.y;
	}
	
/**
 * Este metodo devuelve el valor del atributo X
 * 
 * @return Valor del atributo X
 */
	public int getX() {
		return x;
	}
	
/**
 * Este metodo devuelve el valor del atributo Y
 * 
 * @return Valor del atributo Y
 */
	public int getY() {
		return y;
	}
	 
/**
 * Metodo que se encarga de crear un String con las coordendas de la Clase
 * 
 * @return Devuelve el String creado
 */
	public String toString() {
		return "["+x+","+y+"]";
	}
	
/**
 * @return Devuelve el resultado del hashCode
 */
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + x;
		result = prime * result + y;
		return result;
	}
	
/**
 * @param obj objeto que se pasa por parametro
 * @return Devuelve el resueltado del equals
 */
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Coordinate other = (Coordinate) obj;
		if (x != other.x)
			return false;
		if (y != other.y)
			return false;
		return true;
	}
	
/**
 * Metodo que crea un nuevo objeto de la clase Coordinate 
 * sumando los atributos del objeto pasado por parametro
 * 
 * @param c Recibe otro objeto de la clase Coordinate
 * @return Devuelve el nuevo objeto creado
 */
	public Coordinate add(Coordinate c) {
		Coordinate new_c=new Coordinate(x+c.x,y+c.y);
	
		return new_c;	
	} 
	
/**
 * Metodo que crea un nuevo objeto de la clase Coordinate
 * sumando a este nuevo objeto los valores x e y
 * 
 * @param x Eje de las X
 * @param y Eje de las Y
 * @return Devuelve el nuevo objeto creado
 */
	public Coordinate add(int x,int y) {
		Coordinate new_c=new Coordinate(this.x+x,this.y+y);
		
		return new_c;
	}
	
/**
 * @param otra Coordenada a comparar con la propia 
 * @return Devuelve un valor segun si la coordenada por parametro es mayor, menor o son iguales
 */
	public int compareTo(Coordinate otra) {
		if(x<otra.x) {
			return -1;
		}
		else if(x>otra.x) {
			return 1;
		}
		else {
			if(y<otra.y) {
				return -1;
			}
			else if(y>otra.y) {
				return 1;
			}
			else {
				return 0;
			}
		}
	}
	
/**
 * @return Devuelve un TreeSet con las coordendas de alrededor de la coordenada
 */
	public Set<Coordinate> getNeighborhood(){			
		Set<Coordinate> C=new TreeSet<Coordinate>();
		Coordinate vecinos[]=new Coordinate[8];
		
		vecinos[0]=new Coordinate(x+1,y);
		vecinos[1]=new Coordinate(x+1,y+1);
		vecinos[2]=new Coordinate(x+1,y-1);
		vecinos[3]=new Coordinate(x,y+1);
		vecinos[4]=new Coordinate(x,y-1);
		vecinos[5]=new Coordinate(x-1,y);
		vecinos[6]=new Coordinate(x-1,y+1);
		vecinos[7]=new Coordinate(x-1,y-1);
		
		for(int i=0;i<vecinos.length;i++) {
			C.add(vecinos[i]);
		}
		
		return C;
	}
}
