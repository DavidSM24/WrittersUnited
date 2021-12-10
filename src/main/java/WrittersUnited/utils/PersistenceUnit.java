package WrittersUnited.utils;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class PersistenceUnit {
	
	public static int conexion=1;
	private static final String PUN= "aplicacionMariaDB";
	private static final String LPUN= "aplicacionH2";
									
	private static EntityManagerFactory emf;
	private static EntityManagerFactory emfl;
	
	public static EntityManagerFactory getLocalInstance() {
		if(emf==null) {
			emf = Persistence.createEntityManagerFactory(LPUN); // <-- puntero a META-INF
		}
		return emf;
	}
	
	public static EntityManagerFactory getInstance() {
		if(emfl==null) {
			emfl = Persistence.createEntityManagerFactory(PUN); // <-- puntero a META-INF
		}
		return emfl;
	}
}