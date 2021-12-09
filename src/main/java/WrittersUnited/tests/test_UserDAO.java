package WrittersUnited.tests;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import WrittersUnited.DAOs.ProjectDAO;
import WrittersUnited.DAOs.UserDAO;
import WrittersUnited.models.Character;
import WrittersUnited.models.Project;
import WrittersUnited.models.User;
import WrittersUnited.utils.PersistenceUnit;

public class test_UserDAO {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		PersistenceUnit.conexion=2;
		User u=new User(2L,"nose","d@gmail.com","1","1",true,new HashSet<Project>());
		Set<Project>p=new HashSet<Project>();
		p.add(new Project(3L,"NARUTO","mu ubena",new HashSet<Character>(),u));
		p.add(new Project(4L,"ONE PIECE","mu ubena",new HashSet<Character>(),u));
		u.getProjects().addAll(p);
		UserDAO.save(u);
	}		

}
