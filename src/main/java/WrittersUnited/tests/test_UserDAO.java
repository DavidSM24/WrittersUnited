package WrittersUnited.tests;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import WrittersUnited.DAOs.CharacterDAO;
import WrittersUnited.DAOs.ProjectDAO;
import WrittersUnited.DAOs.UserDAO;
import WrittersUnited.models.Chapter;
import WrittersUnited.models.Character;
import WrittersUnited.models.Project;
import WrittersUnited.models.User;
import WrittersUnited.utils.DOCExporter;
import WrittersUnited.utils.PersistenceUnit;

public class test_UserDAO {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Project p=ProjectDAO.getAll().get(0);
		
		try {
			DOCExporter.export_To_Word(p,"prueba.doc");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
