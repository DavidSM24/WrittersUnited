package WrittersUnited.DAO;

import WrittersUnited.Utils.PersistenceUnit;
import WrittersUnited.models.Chapter;
import WrittersUnited.models.Character;
import WrittersUnited.models.Project;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

public class DAOCharacter extends Character {




    public static EntityManager createEM(){
        EntityManagerFactory emf=null;

        if(PersistenceUnit.conexion==1) {
            emf=PersistenceUnit.getInstance();
        }
        else {
            emf=PersistenceUnit.getLocalInstance();
        }

        return emf.createEntityManager();
    }
    public static List<Character> getAll() {
        List<Character> result = new ArrayList<Character>();

        EntityManager em = createEM();
        em.getTransaction().begin();

        result = em.createQuery("FROM Character").getResultList();

        em.getTransaction().commit();
        return result;
    }

    public static Project getById(int id) {
        Project result = null;

        EntityManager em = createEM();
        em.getTransaction().begin();
        result = em.find(Project.class, id);
        em.getTransaction().commit();
        return result;
    }

    public static List<Character> getByName(String name) {
        List<Character> result = new ArrayList<Character>();

        EntityManager em = createEM();
        em.getTransaction().begin();

        TypedQuery<Character> q = em.createNamedQuery("getByName", Character.class);
        q.setParameter("name", "%" + name.toLowerCase() + "%");
        result = q.getResultList();

        em.getTransaction().commit();
        return result;
    }

    public static Project getByChapter(Chapter chapter) {
        List<Character> result = new ArrayList<Character>();

        EntityManager em = createEM();
        em.getTransaction().begin();

        TypedQuery<Character> q = em.createNamedQuery("getByChapter", Character.class);
        q.setParameter("chapter", "%" + chapter.toLowerCase() + "%");
        result = q.getResultList();

        em.getTransaction().commit();
        return result;
    }

    public static Project getByCharacter(Character character) {
        List<Project> result = new ArrayList<Project>();

        EntityManager em = createEM();
        em.getTransaction().begin();

        TypedQuery<Project> q = em.createNamedQuery("getByCharacter", Character.class);
        q.setParameter("id", character.getId());
        result = q.getResultList();

        em.getTransaction().commit();
        return result;

    }

    public static void save(Character character) {
        EntityManager em=createEM();
        em.getTransaction().begin();
        em.persist(character);
        em.getTransaction().commit();
    }


    public static void delete(Character character) {
        EntityManager em=createEM();
        em.getTransaction().begin();
        em.remove(character);
        em.getTransaction().commit();
    }

    public static void deleteAll() {
        EntityManager em=createEM();
        em.getTransaction().begin();
        em.createNativeQuery("DELETE FROM CHARACTER");
        em.getTransaction().commit();


    }


}
