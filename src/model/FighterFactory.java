package model;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Objects;

/**
 * Clase que nos sirve como una factoria de cazas, crea cazas de todas las subclases
 * 
 * @author Noel Martinez Pomares 48771960T
 *
 */
public class FighterFactory {
/**
 * Crea un caza segun del tipo que sea
 * 
 * @param type Tipo del caza
 * @param mother Nave a la que pertenece
 * @return Devuelve el caza creado
 */
	public static Fighter createFighter(String type,Ship mother) {
		Objects.requireNonNull(type);
		Objects.requireNonNull(mother);
		
		Class<?> c = null;
		
		try {
			String name = "model.fighters."+type;
			
			c = Class.forName(name);
			Class<?>[] paramTypes = new Class[] {Ship.class};
			Constructor<?> m = c.getConstructor(paramTypes);
			Object[] argumentos = new Object[] {mother};
			
			Fighter f = (Fighter) m.newInstance(argumentos);
			
			return f;
			
		}catch(ClassNotFoundException e){
			
		}catch(InstantiationException ei) {
			
		}catch(NoSuchMethodException ua) {
			
		}catch(InvocationTargetException ie) {
			
		}catch(IllegalAccessException vu) {
			
		}
		
		return null;
	}
}