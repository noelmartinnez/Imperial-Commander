package model;

import java.util.Objects;

import model.exceptions.FighterIsDestroyedException;

/**
 * Clase que permite crear cazas de una nave, manejar y cambiar sus datos
 * 
 * @author Noel Martinez Pomares 48771960T
 *
 */
public abstract class Fighter {
/**
 * El atributo velocity es la velocidad del caza, attack es el ataque del caza, shield es el escudo del caza, id es su identificador
 */
	private int velocity,attack,shield,id;

/**
 * El atributo nextId va aumentando segun aumenten los cazas
 */
	private static int nextId=1;
	
/**
 * El atributo motherShip es la nave a la que pertenece
 */
	private Ship motherShip;
	
/**
 * El atributo position es la posicion del caza en el tablero
 */
	private Coordinate position;
	
/**
 * Constructor que inicializa el caza con sus valores y una nave
 * 
 * @param mother Nave a la que pertenece
 */
	protected Fighter(Ship mother) {
		Objects.requireNonNull(mother);
		
		velocity=100;
		attack=80;
		shield=80;
		id=Fighter.nextId;
		Fighter.nextId++;
		motherShip=mother;
		position=null;
	}

/**
 * Constructor de copia que devuelve un caza con los mismos valores que el caza pasado por parametro
 * 
 * @param f Caza al que extraer los datos
 */
	protected Fighter(Fighter f) {		   
		velocity=f.getVelocity();
		attack=f.getAttack();
		shield=f.getShield();
		id=f.getId();
		motherShip=f.getMotherShip();
		position=f.getPosition();
	}
	
/**
 * Metodo que aumenta en uno el id estatico
 */
	public static void resetNextId() {
		nextId=1;
	}
	
/**
 * Metodo que comprueba si el caza esta destruido
 * 
 * @return True si esta destruido y False si no lo esta
 */
	public boolean isDestroyed() {
		if((shield == 0) || (shield < 0)) {
			return true;
		}
		else {
			return false;
		}
	}
	
/**
 * Devuelve el danyo inflingido por el caza enemigo
 * 
 * @param n Numero aleatorio
 * @param enemy Caza que inflige el danyo
 * @return Devuelve el danyo
 */
	public int getDamage(int n,Fighter enemy) {
		Objects.requireNonNull(enemy);
		return (n*attack)/300;
	}
	
/**
 * @return Devuelve la posicion en la que se encuentra el caza
 */
	public Coordinate getPosition() {	
		return position;
	}
	
/**
 * @return Devuelve la nave a la que pertenece el caza
 */
	public Ship getMotherShip() {		
		return motherShip;
	}
	
/**
 * @return Devuelve el Side de la nave a la que pertenece el caza
 */
	public Side getSide() {					
		return motherShip.getSide();
	}
	
/**
 * Metodo que asigna la coordenada pasada por parametro como la coordenada del caza
 * 
 * @param p Coordenada a asignar
 */
	public void setPosition(Coordinate p) {	
		position=p;
	}
	
/**
 * @return Devuelve el resultado del hashCode
 */
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}
	
/**
 * @param obj Objeto que se pasa por parametro
 * @return Devuelve el resultado del equals
 */
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Fighter other = (Fighter) obj;
		if (id != other.id)
			return false;
		return true;
	}

/**
* @return Devuelve el tipo
*/
	public String getType() {
		return getClass().getSimpleName();
	} 

/**
* @return Devuelve la velocidad
*/
	public int getVelocity() {
		return velocity;
	}

/**
* @param vel Aumenta la velocidad con la velocidad pasada por parametro
*/
	public void addVelocity(int vel) {
		this.velocity =this.velocity+vel;
		
		if(this.velocity<0) {
			this.velocity=0;
		}
	}

/**
* @return Devuelve el ataque
*/
	public int getAttack() {
		return attack;
	}

/**
* @param at Aumenta el ataque con el ataque pasado por parametro
*/
	public void addAttack(int at) {
		this.attack =this.attack+at;
		
		if(this.attack<0) {
			this.attack=0;
		}
	}

/**
* @return Devuelve el escudo
*/
	public int getShield() {
		return shield;
	}
	
/**
* @param sh Aumenta el escudo con el escudo pasado por parametro
*/
	public void addShield(int sh) {
		this.shield =this.shield+sh;
	}

/**
* @return Devuelve el id
*/
	public int getId() {
		return id;
	}	
	
/**
 * Metodo que simula una lucha entre dos caza, hasta que uno de ellos queda destruido
 * 
 * @param enemy Caza enemigo
 * @return Si alguno esta destruido devuelve 0, si gana el caza devuelve 1 y -1 si gana el enemigo
 * @throws FighterIsDestroyedException El caza ya estaba destruido
 */
	public int fight(Fighter enemy) throws FighterIsDestroyedException {
		Objects.requireNonNull(enemy);
		
		int resultadoGanador=0,umbral=(100*velocity)/(velocity+enemy.velocity);
		boolean terminar=false;
		
		if((getShield()<=0)||(enemy.getShield()<=0)) {
			if(getShield()<=0) {
				throw new FighterIsDestroyedException(this);
			}else {
				throw new FighterIsDestroyedException(enemy);
			}
		}
		
		do {
			int n=RandomNumber.newRandomNumber(100);
			
			if( umbral <= n) {
				enemy.addShield(-getDamage(n,enemy));
				
				if(enemy.getShield()<=0) {
					terminar=true;
					resultadoGanador=1;
				}
				else if(getShield()<=0) {
					terminar=true;
					resultadoGanador=-1;
				}
			}
			else {
				addShield(-enemy.getDamage(100-n,this));
				
				if(getShield()<=0) {
					terminar=true;
					resultadoGanador=-1;
				}
				else if(enemy.getShield()<=0) {
					terminar=true;
					resultadoGanador=1;
				}
			}
						
		}while(terminar != true);
		
		return resultadoGanador;
	}
	
/**
 * Metodo que se encarga de crear un String con la descripcion del caza
 * 
 * @return Devuelve el String creado
 */
	public String toString() {			
		String stringToReturn;
		
		if(position==null) {
			stringToReturn="("+getType()+" "+id+" "+getSide()+" null {"+velocity+","+attack+","+shield+"})";
			return stringToReturn;
		}
		else {
			stringToReturn="("+getType()+" "+id+" "+getSide()+" ["+position.getX()+","+position.getY()+"] {"+velocity+","+attack+","+shield+"})";
			return stringToReturn;
		}
	}
	
/**
 * Devuelve el valor
 * 
 * @return valor
 */
	public int getValue() {
		return getAttack()+getVelocity();
	}
	
/**
 * Metodo abstracto que se declara aqui, pero se usa en las subclases
 * 
 * @return Devuelve una copia del caza
 */
	public abstract Fighter copy();
	
/**
 * Metodo abstracto que se declara aqui, pero se usa en las subclases
 * 
 * @return Devuelve el simbolo del caza
 */
	public abstract char getSymbol();
}
