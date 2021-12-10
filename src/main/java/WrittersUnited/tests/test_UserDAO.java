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
		PersistenceUnit.conexion=2;
		
		User u = new User();
		UserDAO.save(u);
		
		
	
	}		

}
