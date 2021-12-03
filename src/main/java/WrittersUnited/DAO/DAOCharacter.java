package WrittersUnited.DAO;

import WrittersUnited.Utils.PersistenceUnit;
import WrittersUnited.models.Chapter;
import WrittersUnited.models.Character;
import WrittersUnited.models.Project;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;

public class DAOCharacter extends Character {




    public static EntityManager createEM(){
        EntityManagerFactory emf= PersistenceUnit.getInstance();
        return emf.createEntityManager();
    }
    public static List<Character> getAll() {
        return null;
    }

    public static Project getById(int id) {
        return null;
    }

    public static List<Character> getByName(String name) {
        return null;
    }

    public static Project getByChapter(Chapter chapter) {
        return null;
    }

    public static Project getByCharacter(Character character) {
        return null;

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

    }


}
