package WrittersUnited.DAOs;

import WrittersUnited.models.Character;
import WrittersUnited.models.Project;
import WrittersUnited.utils.PersistenceUnit;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

import java.util.ArrayList;
import java.util.List;

public class CharacterDAO {

	public static EntityManager createEm() {
		EntityManagerFactory emf = null;

		emf = PersistenceUnit.getInstance();

		return emf.createEntityManager();
	}

	public static List<Character> getAll() {
		List<Character> result = new ArrayList<Character>();

		EntityManager em = createEm();
		em.getTransaction().begin();

		result = em.createQuery("FROM Character").getResultList();

		em.getTransaction().commit();
		return result;
	}

	public static Character getById(int id) {
		Character result = null;

		EntityManager em = createEm();
		em.getTransaction().begin();
		result = em.find(Character.class, id);
		em.getTransaction().commit();
		return result;
	}

	public static List<Character> getByName(String name) {
		List<Character> result = new ArrayList<Character>();

        EntityManager em = createEm();
        em.getTransaction().begin();

        TypedQuery<Character> q = em.createNamedQuery("getCharacterName", Character.class);
        q.setParameter("name", "%" + name.toLowerCase() + "%");
        result = q.getResultList();

        em.getTransaction().commit();
		return result;
	}

	public static Long save(Character character) {
		EntityManager em = createEm();
		em.getTransaction().begin();
		em.persist(character);
		Character aux=em.find(Character.class, character.getId());
		Long id=aux.getId();
		em.getTransaction().commit();
		
		return id;
	}

	public static void update(Character character) {
		EntityManager em = createEm();
		em.getTransaction().begin();
		em.merge(character);
		em.getTransaction().commit();
	}

	public static void delete(Character character) {
		EntityManager em = createEm();
		em.getTransaction().begin();
		Character aux=em.merge(character);
		em.remove(aux);
		em.getTransaction().commit();
	}

	public static void deleteAll() {
		EntityManager em = createEm();
		em.getTransaction().begin();
		em.createNativeQuery("DELETE FROM Chara");
		em.getTransaction().commit();
	}

}