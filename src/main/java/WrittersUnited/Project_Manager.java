package WrittersUnited;

import java.util.HashSet;
import java.util.List;

import WrittersUnited.models.Character;
import WrittersUnited.DAOs.ProjectDAO;
import WrittersUnited.DAOs.UserDAO;
import WrittersUnited.models.Chapter;
import WrittersUnited.models.Project;
import WrittersUnited.models.User;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class Project_Manager {

	User u;
	List<Project> projects;
	Project p;
	
	@FXML
	Button btn_cancel;
	
	@FXML
	TextField txt_title;
	
	@FXML
	TextArea txt_description;
	
	public void setController(User u, Project p, List<Project> projects) {
		this.projects=projects;
		this.u=u;
		
		if(p!=null) {
			this.p=p;
			txt_title.setText(p.getTitle());
			txt_description.setText(p.getDescription());
		}
	}
	
	@FXML
	private void add() {
		if(txt_title.getText().matches("")
				||txt_description.getText().matches("")) {
			//alerta campos
		}
		else {
			//alerta confirmar
			
			if(p==null) { //insert
				Project p= new Project(txt_title.getText(),txt_description.getText(),new HashSet<Character>(),u,new HashSet<Chapter>());
				ProjectDAO.save(p);
				
				projects.add(p);
				
				//System.out.println("creado correctamente");
			}
			
			else { //update		
				
				this.p.setTitle(txt_title.getText());
				this.p.setDescription(txt_description.getText());
				
				int i=projects.indexOf(this.p);
				projects.set(i, p);
				
				try {
					ProjectDAO.update(this.p);
				} catch (Exception e) {
					// TODO: handle exception
				}
				
				
				
			}
			
			
			
			Stage stage = (Stage) this.btn_cancel.getScene().getWindow();
			stage.close();
		}
	}
	
	@FXML
	private void cancel(){
		Stage stage = (Stage) this.btn_cancel.getScene().getWindow();
		stage.close();
	}
}
