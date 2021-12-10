package WrittersUnited.tests;

import java.util.HashSet;

import WrittersUnited.DAOs.ProjectDAO;
import WrittersUnited.DAOs.UserDAO;
import WrittersUnited.models.Chapter;
import WrittersUnited.models.Character;
import WrittersUnited.models.Project;
import WrittersUnited.models.User;
import WrittersUnited.utils.PersistenceUnit;

public class test_UserDAO {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		PersistenceUnit.conexion=1;
		
		User u = UserDAO.getByName("user");
		Project paux=(Project) u.getProjects().toArray()[0];
		paux.getChapters().add(new Chapter("afaf",0,"afaf","afafaf","afaf",paux));
		UserDAO.save(u);
		System.out.println(u.getProjects());
		
		
	
	}		

}
