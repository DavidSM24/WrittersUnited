package WrittersUnited.DAOs;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

import WrittersUnited.models.Sesion;
import WrittersUnited.models.User;
import WrittersUnited.utils.PersistenceUnit;

public class SesionDAO {

	/**
	 * Instancia los eventos de la bbdd si estaban desconectados...
	 */
	public static void activateEvents() {
		
		if(PersistenceUnit.conexion==1) {
			EntityManager em=createEm();
			em.getTransaction().begin();
			em.createNativeQuery("SET GLOBAL event_scheduler = ON");		
			em.getTransaction().commit();
		}
	}

	public static EntityManager createEm() {
		EntityManagerFactory emf = null;

		if (PersistenceUnit.conexion == 1) {
			emf = PersistenceUnit.getInstance();
		} else {
			emf = PersistenceUnit.getLocalInstance();
		}

		return emf.createEntityManager();
	}
	
	public static boolean isConnected(User u) {
		
		boolean result=false;
		
		EntityManager em=createEm();
		em.getTransaction().begin();
		
		TypedQuery<Sesion> q=em.createNamedQuery("isLogged",Sesion.class);
		q.setParameter("id_user",u.getId());
		
		if(q.getResultList()!=null&&q.getResultList().size()>0) {
			result = true;
		}
		
		em.getTransaction().commit();
		
		return result;
	}
	
	public static void save(Sesion s) {
		EntityManager em=createEm();
		em.getTransaction().begin();	
		em.merge(s);
		em.getTransaction().commit();
	}
}
