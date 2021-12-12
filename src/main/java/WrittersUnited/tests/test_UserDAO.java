package WrittersUnited.tests;

import java.util.HashSet;
import java.util.List;

import WrittersUnited.DAOs.CharacterDAO;
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
		
		System.out.println(CharacterDAO.getAll());
		
		
	
	}		

}
