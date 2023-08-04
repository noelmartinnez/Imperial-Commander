package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
/**
 * Clase para generar numeros aleatorios
 * 
 * @author Noel Martinez Pomares 48771960T
 *
 */
public class RandomNumber {
/**
 * Generador de numeros aleatorios
 */
	private static Random generator = new Random(1L);
	
/**
 * Lista de numeros generados
 */
	private static List<Integer> list = new ArrayList<Integer>();
	
/**
* Genera un numero aleatorio entre 0 y max-1
* @param max Indica el maximo valor (no incluido)
* @return Numero aleatorio entre 0 y max-1
*/
	public static int newRandomNumber(int max) {
		int r = generator.nextInt(max);
		list.add(r);
		return r;
	}
	
/**
* Getter
* @return Lista de numeros generados
*/
	public static List<Integer> getRandomNumberList() {
		return list;
	}
	
/**
* Reinicializa el generador y la lista para las pruebas unitarias
*/
	public static void resetRandomCounter() {
        list.clear();
        generator = new Random(1L);
    } 
}