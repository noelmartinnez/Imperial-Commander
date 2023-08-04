package model;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import model.exceptions.NoFighterAvailableException;

/**
 * Esta clase se encarga de manejar una nave creado, que contenga toda una flota de cazas
 * y manejar los cazas tambien
 * 
 * @author Noel Martinez Pomares 48771960T
 *
 */
public class Ship {
/**
 * El atributo name es el nombre de la nave
 */
	private String name;
	
/**
 * El atributo wins son las victorias y losses las derrotas
 */
	private int wins,losses;

/**
 * El atributo side es el bando
 */
	private Side side;

/**
 * El atributo fleet es la flota que contiene los cazas
 */
	protected ArrayList<Fighter> fleet;
	
/**
 * Constructor de la clase, que inicializa los datos de la nave
 * 
 * @param name Nombre del caza
 * @param side Bando del caza
 */
	public Ship(String name,Side side) {		
		Objects.requireNonNull(name);
		Objects.requireNonNull(side);
		
		this.name=name;
		wins=0;
		losses=0;
		this.side=side;
		fleet=new ArrayList<Fighter>();
	}
	
/**
* @return Devuelve el nombre
*/
	public String getName() {
		return name;
	}
	
/**
 * @return Devuelve el bando
 */
	public Side getSide() {
		return side;
	}

/**
* @return Devuelve las victorias
*/
	public int getWins() {
		return wins;
	}
	
/**
* @return Devuelve las derrotas
*/
	public int getLosses() {
		return losses;
	}
	
/**
 * Metodo que actualiza los valores de wins y losses segun el parametro
 *
 * @param r Suma una victoria con 1 y losses con -1
 * @param f caza eliminado
 */
	public void updateResults(int r,Fighter f) {
		Objects.requireNonNull(f);
		
		if(r==1) {
			wins++;
		}
			
		if(r==-1) {
			losses++;
		}
	}
	
/**
 * @return Devuelve la flota de cazas
 */
	public List<Fighter> getFleetTest(){	
		return fleet;
	}
	
/**
 * A partir de una cadena, construye los cazas indicados y loas anyade a la flota de cazas
 * de la nave
 * 
 * @param fd Cadena a separar e introducir
 */
	public void addFighters(String fd) {
		Objects.requireNonNull(fd);
		
		if(fd.contains(":") == false) {
			String[] parts = fd.split("/");
			String st1=parts[0];
			String st2=parts[1];
			
			Integer cantidad= Integer.parseInt(st1);
			
			for(int i=0;i<cantidad;i++) {
				Fighter f= FighterFactory.createFighter(st2,this);
				
				if(f != null) {
					fleet.add(f);
				}
			}
		}
		else {
			String[] cadenas = fd.split(":");
			for(int w=0;w<cadenas.length;w++) {
				String[] two_parts = cadenas[w].split("/");
				String firstP=two_parts[0];
				String secondP=two_parts[1];
				
				Integer typeInt= Integer.parseInt(firstP);
				
				for(int j=0;j<typeInt;j++) {
					Fighter f2=FighterFactory.createFighter(secondP,this);
					
					if(f2 != null) {
						fleet.add(f2);
					}
				}				
			}
		}
	}
	
/**
 * Devuelve el primer caza no destruido de la flota del tipo indicado como argumento
 * 
 * @param type Tipo a buscar
 * @return  Devuelve el primer caza no destruido, si type es cadena vacia devuelve el primer no destruido
 * 			y si no hay cazas o estan destruidos devuelve null
 * @throws NoFighterAvailableException Si no hay ningun caza disponible se lanza
 */
	public Fighter getFirstAvailableFighter(String type) throws NoFighterAvailableException {
		Objects.requireNonNull(type);
		
		for(int i=0;i<fleet.size();i++) {
			if((fleet.get(i).isDestroyed() == false) && (fleet.get(i).getPosition() == null)) {
				if(type.isEmpty()) {
					return fleet.get(i);
				}
				else if(fleet.get(i).getType().equals(type)) {
					return fleet.get(i);
				}
			}
		}
		
		throw new NoFighterAvailableException(type);
	}
	
/**
 * Metodo que borra de la flota los cazas destruidos
 */
	public void purgeFleet() {
		for(int i=0;i<fleet.size();i++) {
			if(fleet.get(i).isDestroyed()) {
				fleet.remove(i);
				i--;
			}
		}
	}
	
/**
 * Metodo que devuelve una cadena con la informacion de todos los cazas
 * 
 * @return Devuelve una String con la informacion de todos los cazas linea por linea
 */
	public String showFleet() {
		String fleetString="";
		
		if(fleet.size() == 0) {
			return fleetString;
		}
		else {
			for(int i=0;i<fleet.size();i++) {
				if(fleet.get(i).isDestroyed()) {
					fleetString=fleetString+fleet.get(i).toString()+" (X)\n";
				}
				else {
					fleetString=fleetString+fleet.get(i).toString()+"\n";
				}
			}
			return fleetString;
		}
	}
	
/**
 * Metodo que forma una cadena con el mismo formato de addFighters con los cazas no destruidos de la flota
 * 
 * @return Devuelve la candena formada
 */
	public String myFleet() {
		String myFleet_String="";
		boolean newType;
		List<String> tipo= new ArrayList<String>();
		ArrayList<Integer> numero= new ArrayList<Integer>();
		
		if(fleet.size() == 0) {
			return myFleet_String;
		}
		else {
			for(int i=0;i<fleet.size();i++) {
				if(fleet.get(i).isDestroyed()==false) {
					tipo.add(fleet.get(0).getType());
					numero.add(0);
					break;
				}
			}
			
			for(int j=0;j<fleet.size();j++) {
				newType=true;
				if(fleet.get(j).isDestroyed()==false) {
					for(int w=0;w<tipo.size();w++) {
						if(fleet.get(j).getType().equals(tipo.get(w))) {
							numero.set(w,numero.get(w)+1);
							newType=false;
							break;
						}
					}
					if(newType==true) {
						tipo.add(fleet.get(j).getType());
						numero.add(1);
					}
				}
			}
			
			for(int k=0;k<tipo.size();k++) {
				myFleet_String=myFleet_String+numero.get(k)+"/"+tipo.get(k);
				if(k!=tipo.size()-1) {
					myFleet_String=myFleet_String+":";
				}
			}
			
			return myFleet_String;
		}
	}

/**
 * Metodo que devuelve un String con todos los datos de la nave
 * 
 * @return Devuelve el string con los datos
 */
	public String toString() {
		return "Ship [" + name + " " + wins + "/" + losses + "] "+myFleet();		
				
	}
}