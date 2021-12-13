package WrittersUnited.DAOs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import WrittersUnited.models.Project;
import WrittersUnited.models.User;
import WrittersUnited.utils.Conexion;
import WrittersUnited.utils.PersistenceUnit;

public class ProjectDAO {

	private static final String INSERT = "INSERT INTO book (id,title) " + "VALUES (?,?) "
			+ "ON DUPLICATE KEY UPDATE title=?;";

	public static EntityManager createEm() {
		EntityManagerFactory emf = null;

		emf = PersistenceUnit.getInstance();

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

	public static Project getBookById(Long id) {
		return null;

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
		EntityManager em = createEm();
		em.getTransaction().begin();
		em.persist(p);
		em.getTransaction().commit();
	}

	public static void update(Project p) {
		EntityManager em = createEm();
		em.getTransaction().begin();
		em.merge(p);
		em.getTransaction().commit();
	}

	public static void delete(Project p) {
		EntityManager em = createEm();
		em.getTransaction().begin();
		Project aux = em.merge(p);
		em.remove(aux);
		em.getTransaction().commit();
	}

	public static void deleteAll() {

		EntityManager em = createEm();
		em.getTransaction().begin();
		em.createNativeQuery("DELETE FROM Project");
		em.getTransaction().commit();
	}

	public static void shareProject(Project p, User u) {

		EntityManager em = createEm();

		em.getTransaction().begin();

		Query q = em.createNativeQuery(
				"INSERT INTO user_projectshared (id_user, id_project) VALUES (:id_user,:id_project)");
		q.setParameter("id_user", u.getId());
		q.setParameter("id_project", p.getId());
		q.executeUpdate();

		em.getTransaction().commit();
	}

	public static void unshareProject(Project p, User u) {

		EntityManager em = createEm();

		em.getTransaction().begin();

		Query q = em
				.createNativeQuery("DELETE FROM user_projectshared WHERE id_user=:id_user AND id_project=:id_project");
		q.setParameter("id_user", u.getId());
		q.setParameter("id_project", p.getId());
		q.executeUpdate();

		em.getTransaction().commit();
	}

	public static void toLibrary(Project p) {
		// INSERT o UPDATE
		// INSERT -> si no existe OK
		// En caso de ERROR -> hago un update
		int rs = 0;
		PreparedStatement ps = null;
		Connection con = Conexion.getConexion();

		if (con != null) {
			try {
				ps = con.prepareStatement(INSERT);
				ps.setLong(1, p.getId());
				ps.setString(2, p.getTitle());
				
				ps.setString(3, p.getTitle());

				rs = ps.executeUpdate();

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			finally {
				try {
					ps.close();
				} catch (SQLException e) {
					// TODO: handle exception
				}
			}

		}
	}
}
