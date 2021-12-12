package WrittersUnited.utils;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class PersistenceUnit {
	
	public static int conexion=1;
	private static final String PUN= "aplicacionMariaDB";
	private static final String LPUN= "aplicacionH2";
									
	public static EntityManagerFactory emf;
	
	public static EntityManagerFactory getInstance() {
		if(conexion==1) {
			emf = Persistence.createEntityManagerFactory(PUN);
		}
		else {
			emf = Persistence.createEntityManagerFactory(LPUN);

		}
		return emf;
	}
}