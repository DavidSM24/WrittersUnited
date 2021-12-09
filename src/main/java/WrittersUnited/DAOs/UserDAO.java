package WrittersUnited.DAOs;

import java.util.ArrayList;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import WrittersUnited.models.User;
import WrittersUnited.utils.PersistenceUnit;

public class UserDAO {
	
	public static EntityManager createEm() {
		EntityManagerFactory emf=null;
		
		if(PersistenceUnit.conexion==1) {
		 emf=PersistenceUnit.getInstance();
		}
		else {
		 emf=PersistenceUnit.getLocalInstance();
		}
		
		return emf.createEntityManager();
	}
	
	public static List<User> getAll(){
		List<User> result=new ArrayList<User>();
		
		EntityManager em=createEm();
		em.getTransaction().begin();
		
		result=em.createQuery("FROM User").getResultList();
		
		
		
		em.getTransaction().commit();
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
		em.merge(u);
		em.getTransaction().commit();
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
	
}
