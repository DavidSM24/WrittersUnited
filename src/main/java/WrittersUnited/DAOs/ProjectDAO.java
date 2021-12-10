package WrittersUnited.DAOs;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import WrittersUnited.models.Project;
import WrittersUnited.models.User;
import WrittersUnited.utils.PersistenceUnit;

public class ProjectDAO {

	public static EntityManager createEm() {
		EntityManagerFactory emf = null;

		if (PersistenceUnit.conexion == 1) {
			emf = PersistenceUnit.getInstance();
		} else {
			emf = PersistenceUnit.getLocalInstance();
		}

		return emf.createEntityManager();
	}

	public static List<Project> getAll() {
		List<Project> result = new ArrayList<Project>();

		EntityManager em = createEm();
		em.getTransaction().begin();

		result = em.createQuery("FROM Project").getResultList();

		em.getTransaction().commit();
		return result;
	}

	public static Project getById(Long id) {
		Project result = null;

		EntityManager em = createEm();
		em.getTransaction().begin();
		result = em.find(Project.class, id);
		em.getTransaction().commit();
		return result;
	}

	public static List<Project> getByTitle(String title) {
		List<Project> result = new ArrayList<Project>();

		EntityManager em = createEm();
		em.getTransaction().begin();

		TypedQuery<Project> q = em.createNamedQuery("getByTitle", Project.class);
		q.setParameter("title", "%" + title.toLowerCase() + "%");
		result = q.getResultList();

		em.getTransaction().commit();
		return result;
	}

	public static List<Project> getByUserCreator(User u) {
		List<Project> result = new ArrayList<Project>();
		EntityManager em = createEm();
		em.getTransaction().begin();
		TypedQuery<Project> q = em.createNamedQuery("getByUserCreator", Project.class);
		q.setParameter("id", u.getId());
		result = q.getResultList();
		em.getTransaction().commit();
		return result;
	}

	public static void save(Project p) {
		EntityManager em=createEm();
		em.getTransaction().begin();	
		em.merge(p);
		em.getTransaction().commit();
	}

	public static void delete(Project p) {
		EntityManager em = createEm();
		em.getTransaction().begin();
		em.remove(p);
		em.getTransaction().commit();
	}

	public static void deleteAll() {

		EntityManager em = createEm();
		em.getTransaction().begin();
		em.createNativeQuery("DELETE FROM Project");
		em.getTransaction().commit();
	}

}
