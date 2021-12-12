package WrittersUnited.DAOs;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import WrittersUnited.models.Project;
import WrittersUnited.models.User;
import WrittersUnited.utils.PersistenceUnit;

public class UserDAO {
	
	public static EntityManager createEm() {
		EntityManagerFactory emf=null;
		
		emf=PersistenceUnit.getInstance();
		
		return emf.createEntityManager();
	}
	
	public static List<User> getAll(){
		List<User> result=new ArrayList<User>();
		
		EntityManager em=createEm();
		em.getTransaction().begin();
		result=em.createQuery("FROM User").getResultList();
		em.getTransaction().commit();
		em.clear();
		em.close();
		PersistenceUnit.emf.close();
		return result;
	}
	
	public static User getById(Long id) {
		User result=null;
		
		EntityManager em=createEm();
		em.getTransaction().begin();
		result=em.find(User.class, id);
		em.getTransaction().commit();
		return result;
	}
	
	public static User getByName(String name){
		User result=null;
		
		EntityManager em=createEm();
		em.getTransaction().begin();
		
		TypedQuery<User> q=em.createNamedQuery("getByName",User.class);
		q.setParameter("username",name);
	
		if(q.getResultList()!=null&&q.getResultList().size()>0) {
			result=q.getResultList().get(0);
		}
		
		
		em.getTransaction().commit();
		return result;
	}
	
	public static User getUserByMail(String mail){
		User result=null;
		
		EntityManager em=createEm();
		em.getTransaction().begin();
		
		TypedQuery<User> q=em.createNamedQuery("getByMail",User.class);
		q.setParameter("mail",mail);
		if(q.getResultList()!=null&&q.getResultList().size()>0) {
			result=q.getResultList().get(0);
		}
		
		
		em.getTransaction().commit();
		return result;
	}
	
	public static User getUserByUserCode(String code){
		User result=null;
		
		EntityManager em=createEm();
		em.getTransaction().begin();
		
		TypedQuery<User> q=em.createNamedQuery("getByUserCode",User.class);
		q.setParameter("usercode",code);

		if(q.getResultList()!=null&&q.getResultList().size()>0) {
			result=q.getResultList().get(0);
		}
		
		em.getTransaction().commit();
		return result;
	}
	
	public static void save(User u) {
		EntityManager em=createEm();
		em.getTransaction().begin();	
		
		if(em.find(Project.class, u.getId())!=null) {
			em.merge(u);
		}
		else {
			em.persist(u);
		}
		
		em.merge(u);
		em.getTransaction().commit();
		PersistenceUnit.emf.close();
	}
	
	public static void delete(User u) {
		EntityManager em=createEm();
		em.getTransaction().begin();	
		em.remove(u);
		em.getTransaction().commit();
	}
	
	public static void deleteAll() {

		EntityManager em=createEm();
		em.getTransaction().begin();
		em.createNativeQuery("DELETE FROM User");		
		em.getTransaction().commit();

	}
	
	public static void addProject(User u,Project p) {
		EntityManager em=createEm();
		em.getTransaction().begin();
		
		User ux=em.merge(u);
		Set<Project> ls=ux.getProjects();
		ls.add(p);
		ux.setProjects(ls);
		em.getTransaction().commit();
		
	}
	
	public static void removeProject(User u,Project p){

		EntityManager em=createEm();
		em.getTransaction().begin();
		
		User ux=em.merge(u);
		Set<Project> ls=ux.getProjects();
		ls.remove(p);
		ux.setProjects(ls);		
		
		em.getTransaction().commit();
	}
	
}
