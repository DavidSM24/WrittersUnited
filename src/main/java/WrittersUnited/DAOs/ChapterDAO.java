package WrittersUnited.DAOs;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import WrittersUnited.models.Chapter;
import WrittersUnited.models.Character;
import WrittersUnited.models.Project;
import WrittersUnited.utils.PersistenceUnit;

public class ChapterDAO {

	public static EntityManager createEm() {
		EntityManagerFactory emf=null;
		
		emf=PersistenceUnit.getInstance();
		
		return emf.createEntityManager();
	}

	public static List<Chapter> getAll() {

		List<Chapter> chapter = new ArrayList<Chapter>();
		EntityManager em = createEm();

		em.getTransaction().begin();
		chapter = em.createQuery("FROM Chapter").getResultList();

		em.getTransaction().commit();
		return chapter;
	}

	public static Chapter getById(Long id) {

		Chapter result = null;

		EntityManager em = createEm();
		em.getTransaction().begin();
		result = em.find(Chapter.class, id);
		em.getTransaction().commit();

		return result;
	}

	public static List<Chapter> getByTitle(String title) {

		List<Chapter> chapter = new ArrayList<Chapter>();
		EntityManager em = createEm();

		em.getTransaction().begin();
		TypedQuery<Chapter> q = em.createNamedQuery("getByCTitle", Chapter.class);
		q.setParameter("title", "%" + title.toLowerCase() + "%");
		chapter = q.getResultList();
		em.getTransaction().commit();
		em.getEntityManagerFactory().getCache().evictAll();
		em.close();
		return chapter;
	}

	public static void insert(Chapter chapter) { // inserta
		EntityManager em=createEm();
		em.getTransaction().begin();	
		em.persist(chapter);
		em.getTransaction().commit();
	}
	
	public static void update(Chapter chapter) { // inserta
		EntityManager em = createEm();
		em.getTransaction().begin();
		em.merge(chapter);
		em.getTransaction().commit();
	}

	public static void delete(Chapter chapter) {
		EntityManager em = createEm();
		em.getTransaction().begin();
		em.remove(chapter);
		em.getTransaction().commit();
	}

	public static void deleteAll() {

		EntityManager em = createEm();
		em.getTransaction().begin();
		em.createNativeQuery("DELETE FROM Chapter");
		em.getTransaction().commit();
	}
}
