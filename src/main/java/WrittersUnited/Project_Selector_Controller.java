package WrittersUnited;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import WrittersUnited.DAOs.ProjectDAO;
import WrittersUnited.DAOs.UserDAO;
import WrittersUnited.models.Project;
import WrittersUnited.models.User;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class Project_Selector_Controller {
	
	User u;
	
	ObservableList<Project> projects;
	
	@FXML
	Button btn_select;
	
	@FXML
	TableView<Project> table_projects;
	
	@FXML
	TableColumn<Project, String> col_name;
	
	public void setController(User u) {
		
		this.u=u;
		
		
		if(u!=null) {
			projects=FXCollections.observableArrayList();
			if(u.getProjects()!=null) {
				for(Project p:u.getProjects()) {
					projects.add(p);
				}
			}
			table_projects.setItems(projects);
			setTableInfo();
			System.out.println(projects);
		}
	}

	@FXML
	private void select() {
		if(table_projects.getSelectionModel().getSelectedItem()!=null) {
			Project p=table_projects.getSelectionModel().getSelectedItem();
			
			Stage stage = (Stage) this.btn_select.getScene().getWindow();
			stage.close();

			
			try {
				FXMLLoader loader = new FXMLLoader(getClass().getResource("primary.fxml"));
				Parent root;
				root = loader.load();
				PrimaryController pc = loader.getController();
				pc.setController(u, p, pc);
				Scene scene = new Scene(root);
				Stage stage2 = new Stage();
				stage2.setScene(scene);
				//Image image= new Image("file:src/main/resources/images/manager.png");
				stage2.setTitle("Writters United: "+p.getTitle());
				stage2.setMaximized(true);
				//stage2.getIcons().add(image);
				//stage2.setResizable(false);;
				//stage2.initModality(Modality.APPLICATION_MODAL);
				stage2.show();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
	
	@FXML
	private void remove() {
		if(table_projects.getSelectionModel().getSelectedItem()!=null) {
			
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setHeaderText(null);
			alert.setTitle("Confirmación");
			alert.setContentText(" ¿Está seguro de que quiere eliminar el projecto seleccionado?");

			Optional<ButtonType> result = alert.showAndWait();
			if (result.get() == ButtonType.OK){
				Project p=table_projects.getSelectionModel().getSelectedItem();
				
				u.getProjects().remove(p);
				ProjectDAO.delete(p);
				
				projects.remove(p);
			}
		}
	}
	
	@FXML
	public void add() {
		
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("Create_Edit_Project.fxml"));
			Parent root;
			root = loader.load();
			Project_Manager pm = loader.getController();
			pm.setController(u,null,projects);
			Scene scene = new Scene(root);
			Stage stage2 = new Stage();
			stage2.setScene(scene);
			Image image = new Image("file:src/main/resources/images/icons/icon_app.jpg");
			stage2.setTitle("Selección de Projecto");
			stage2.getIcons().add(image);
			//stage2.setResizable(false);
			;
			stage2.initModality(Modality.APPLICATION_MODAL);
			stage2.show();
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
	}
	
	public void edit() {
		
		if(table_projects.getSelectionModel().getSelectedItem()!=null) {
			
			Project aux=table_projects.getSelectionModel().getSelectedItem();
			
			try {
				FXMLLoader loader = new FXMLLoader(getClass().getResource("Create_Edit_Project.fxml"));
				Parent root;
				root = loader.load();
				Project_Manager pm = loader.getController();
				pm.setController(u,aux,projects);
				Scene scene = new Scene(root);
				Stage stage2 = new Stage();
				stage2.setScene(scene);
				Image image = new Image("file:src/main/resources/images/icons/icon_app.jpg");
				stage2.setTitle("Selección de Projecto");
				stage2.getIcons().add(image);
				//stage2.setResizable(false);
				;
				stage2.initModality(Modality.APPLICATION_MODAL);
				stage2.show();
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		
		
	}
	
	private void setTableInfo() {
		// TODO Auto-generated method stub
		
		col_name.setCellValueFactory(eachproject -> {
			SimpleStringProperty v = new SimpleStringProperty();
			v.setValue(eachproject.getValue().getTitle());
			return v;
		});
		
		
	}
}
