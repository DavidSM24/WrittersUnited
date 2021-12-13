package WrittersUnited;

import java.io.File;
import java.util.Optional;

import WrittersUnited.DAOs.ProjectDAO;
import WrittersUnited.models.Project;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class Export_Controller {

	Project p;
	
	@FXML
	Button btn_cancel;
	
	@FXML
	private TextField txt_rute;
	
	public void setController(Project p) {
		this.p=p;
	}
	
	public void add() {
		if(txt_rute.getText().matches("")) {
			
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setHeaderText(null);
			alert.setTitle("Información");
			alert.setContentText(" Debe seleccionar una ruta para generar el documento.");
			alert.show();
		}
		else {
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setHeaderText(null);
			alert.setTitle("Confirmación");
			alert.setContentText(" Se generará el libro, ¿Quire continuar?");

			Optional<ButtonType> result = alert.showAndWait();
			if (result.get() == ButtonType.OK){
<<<<<<< HEAD
				try {
					
					File f=new File(txt_rute.getText());
					
					DOCExporter.export_To_Word(p,f.getAbsolutePath()+"\\"+p.getTitle()+".doc");
					
					ProjectDAO.toLibrary(p);
					
					cancel();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
=======
				
>>>>>>> parent of a3ebb8f (Implementation export to doc)
			}
		}
	}
	
	@FXML
	public void set_Rute() {
		File file = null;
		DirectoryChooser directorychooser = new DirectoryChooser();
		directorychooser.setTitle("Selecionar ruta...");
		try {
			file = directorychooser.showDialog(null);
			txt_rute.setText(file.getPath());
		} catch (Exception e) {
			// TODO: handle exception;
			txt_rute.setText("");
		}
	}
	
	@FXML
	public void cancel() {
		Stage stage = (Stage) this.btn_cancel.getScene().getWindow();
		stage.close();
	}
}
