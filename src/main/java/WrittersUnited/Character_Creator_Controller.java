package WrittersUnited;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

import WrittersUnited.DAOs.CharacterDAO;
import WrittersUnited.models.Character;
import WrittersUnited.models.Project;
import WrittersUnited.models.User;
import WrittersUnited.utils.FileUtilities;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class Character_Creator_Controller {

	private User u;
	private Project p;
	private Character c;
	private ObservableList<Character> characters;
	
	@FXML
	Button btn_accept;
	
	@FXML
	Button btn_cancel;
	
	@FXML
	ImageView img_chara;
	
	@FXML
	TextField txt_img_path;
	
	@FXML
	TextField txt_chara_name;
	
	@FXML
	TextField txt_chara_notes;
	
	@FXML
	TextField txt_chara_rol;
	
	@FXML
	TextField txt_chara_importance;
	
	@FXML
	TextArea txt_chara_story;
	
	@FXML
	TextArea txt_chara_description;
	
	
	public void setController(User u, Project p,Character c,ObservableList<Character>characters) {
		this.u=u;
		this.p=p;
		
		if(c!=null) { //editando
			
			btn_accept.setText("Editar");
			
			this.c=c;
			
			txt_chara_name.setText(c.getName());
			txt_chara_description.setText(c.getDescription());
			txt_chara_story.setText(c.getStory());
			txt_chara_notes.setText(c.getNotes());
			txt_chara_rol.setText(c.getRol());
			txt_chara_importance.setText(c.getImportance());
			txt_img_path.setText(c.getPhoto());
			
			File f = new File(c.getPhoto());
			Image img = new Image("file:" + f.getPath());
			img_chara.setImage(img);	
			
			btn_accept.setText("Editar");
			
		}
		else { //añadiendo
			
		}
		
		this.characters=characters;
	}
	
	@FXML
	public void set_Character_Photo() {
		File file = null;
		FileChooser filechooser = new FileChooser();
		filechooser.setTitle("Selecionar imagen...");
		try {
			file = filechooser.showOpenDialog(null);
			if (file != null && file.getPath().matches(".+\\.png") || file.getPath().matches(".+\\.jpg")) {
				txt_img_path.setText(file.getPath());
				
				file = new File(txt_img_path.getText());
				Image img = new Image("file:" + file.getPath());
				img_chara.setImage(img);
			} else { // extension incorrecta
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setHeaderText(null);
				alert.setTitle("Información");
				alert.setContentText("Formato incorrecto: Debe elegir un tipo de archivo jpg o png.");
				alert.showAndWait();
			}
		} catch (Exception e) {
			// TODO: handle exception;
			txt_img_path.setText("");
			img_chara.setImage(null);
		}
	}
	
	@FXML
	public void cancel() {
		Stage stage = (Stage) this.btn_cancel.getScene().getWindow();
		stage.close();
	}
	
	@FXML
	public void save(){
		
		if(
				txt_chara_name.getText().matches("")
				||txt_chara_description.getText().matches("")
				||txt_chara_importance.getText().matches("")
				||txt_chara_notes.getText().matches("")
				||txt_chara_rol.getText().matches("")
				||txt_chara_story.getText().matches("")
				) { //INVALID
			
				//alert
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setHeaderText(null);
			alert.setTitle("Información");
			alert.setContentText(" Debe rellenar todos los campos.");
			alert.show();
			
		}
		
		else { //valid
			
			boolean confirm=true;
			
			if(txt_img_path.getText().matches("")) {
				//confirmar sin foto
				Alert alert = new Alert(AlertType.CONFIRMATION);
				alert.setHeaderText(null);
				alert.setTitle("Confirmación");
				alert.setContentText(" Se generará el personaje sin imagen, ¿Quire continuar?");

				Optional<ButtonType> result = alert.showAndWait();
				if (result.get() == ButtonType.OK){
					
				}
				else {
					confirm=false;
				}
			}
			
			if(confirm) { //guardar
				
				if(c==null) { //insert
					c=new Character(txt_chara_name.getText(),
							txt_chara_description.getText(),
							txt_chara_story.getText(),
							txt_chara_importance.getText(),
							txt_chara_story.getText(),
							"",
							p,
							txt_chara_notes.getText());
					
					Long id=CharacterDAO.save(c);
					
					if (txt_img_path.getText().matches("")) {
						c.setPhoto("");
					} else {
						c.setPhoto("src/main/resources/images/charas/c" + id + ".jpg");
						try {
							FileUtilities.saveFile(txt_img_path.getText(), c.getPhoto());
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
						
					CharacterDAO.update(c);
					
					characters.add(c);
					p.getCharacters().add(c);
				}
				
				else { //update
					
					c.setName(txt_chara_name.getText());
					c.setDescription(txt_chara_description.getText());
					c.setImportance(txt_chara_importance.getText());
					c.setNotes(txt_chara_notes.getText());
					c.setRol(txt_chara_story.getText());
					c.setStory(txt_chara_story.getText());
					
					if(!txt_img_path.getText().matches("")) {
						String realaddress= "src/main/resources/images/charas/c"+c.getId()+".jpg";
						c.setPhoto(realaddress);
						try {
							FileUtilities.saveFile(txt_img_path.getText(), realaddress);
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					else {
						if(!c.getPhoto().matches("")) {
							FileUtilities.removeFile(c.getPhoto());
						}
						c.setPhoto("");
					}
					
					CharacterDAO.update(c);
					
					int i=characters.indexOf(c);
					characters.set(i, c);
					
					
				}
				
				Stage stage = (Stage) this.btn_cancel.getScene().getWindow();
				stage.close();
				
			}
		}
	}

}
