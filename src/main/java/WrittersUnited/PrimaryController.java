package WrittersUnited;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.Optional;

import WrittersUnited.models.Character;
import WrittersUnited.DAOs.ChapterDAO;
import WrittersUnited.DAOs.CharacterDAO;
import WrittersUnited.DAOs.ProjectDAO;
import WrittersUnited.models.Chapter;
import WrittersUnited.models.Project;
import WrittersUnited.models.User;
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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class PrimaryController {

	PrimaryController main;
	User u;
	Project p;
	Chapter chapter;
	Character chara;

	ObservableList<Chapter> chapters;
	ObservableList<Character> characters;

	// MENU BAR

	@FXML
	Button btn_new;

	// CHARACTER PANE

	@FXML
	private TableView<Character> table_characters;
	@FXML
	private TableColumn<Character, String> col_characters;

	@FXML
	private ImageView img_chara;

	@FXML
	private Label txt_chara_name;

	@FXML
	private Label txt_chara_description;

	@FXML
	private Label txt_chara_story;

	@FXML
	private Label txt_chara_importance;

	@FXML
	private Label txt_chara_notes;

	// CHAPTER PANE

	@FXML
	TableView<Chapter> table_chapter;

	@FXML
	TableColumn<Chapter, String> col_chapter;

	@FXML
	private Label txt_chapter_title;

	@FXML
	private TextArea txtarea_chapter_body;

	@FXML
	private TextArea txtarea_chapter_notes;

	public void setController(User u, Project p, PrimaryController main) {

		this.main=main;
		
		this.txt_chapter_title.setText("");
		
		this.u = u;
		this.p = p;

		chapters = FXCollections.observableArrayList();
		chapters.addAll(p.getChapters());

		chapters.sort((o1, o2) -> Integer.compare(o1.getNumber(), o2.getNumber()));

		System.out.println(chapters);

		characters = FXCollections.observableArrayList();
		characters.addAll(p.getCharacters());

		table_characters.setItems(characters);
		setTableInfo();

		table_chapter.setItems(chapters);
		setTableInfo();

		if (characters != null && characters.size() > 0) {
			chara = characters.get(0);
			show_Chara_Info();
		}

		if (chapters != null && chapters.size() > 0) {
			chapter = chapters.get(0);

			txt_chapter_title.setText("Capítulo " + chapter.getNumber() + ": " + chapter.getTitle());
			txtarea_chapter_body.setText(chapter.getBody());
			txtarea_chapter_notes.setText(chapter.getNotes());
		}

		table_chapter.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent mouseEvent) {
				if (mouseEvent.getButton().equals(MouseButton.PRIMARY)) {
					if (mouseEvent.getClickCount() == 2) {
						if (table_chapter.getSelectionModel().getSelectedItem() != null) {

							if (chapter != table_chapter.getSelectionModel().getSelectedItem()) {

								chapter = table_chapter.getSelectionModel().getSelectedItem();
								change_Chapter(chapter);
							}

						}
					}
				}
			}
		});
	}

	private void setTableInfo() {
		col_characters.setCellValueFactory(eachchara -> {
			SimpleStringProperty v = new SimpleStringProperty();
			v.setValue(eachchara.getValue().getName());
			return v;
		});

		col_chapter.setCellValueFactory(eachchapter -> {
			SimpleStringProperty v = new SimpleStringProperty();
			v.setValue(eachchapter.getValue().getNumber() + ": " + eachchapter.getValue().getTitle());
			return v;
		});
	}

	public void select_Chara() {
		if (table_characters.getSelectionModel().getSelectedItem() != null) {
			chara = table_characters.getSelectionModel().getSelectedItem();
			show_Chara_Info();
		}
	}

	private void show_Chara_Info() {
		if (chara != null) {
			txt_chara_name.setText(chara.getName());
			txt_chara_description.setText(chara.getDescription());
			txt_chara_story.setText(chara.getStory());
			txt_chara_importance.setText(chara.getImportance());
			txt_chara_notes.setText(chara.getNotes());

		} else {
			txt_chara_name.setText("");
			txt_chara_description.setText("");
			txt_chara_story.setText("");
			txt_chara_importance.setText("");
			txt_chara_notes.setText("");
			img_chara.setImage(null);
		}

		File f = new File(chara.getPhoto());
		Image img = new Image("file:" + f.getPath());
		img_chara.setImage(img);
	}

	@FXML
	private void new_Chara() {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("character_creator.fxml"));
			Parent root;
			root = loader.load();
			Character_Creator_Controller ccc = loader.getController();
			ccc.setController(u, p, null, characters);
			Scene scene = new Scene(root);
			Stage stage2 = new Stage();
			stage2.setScene(scene);
			// Image image= new Image("file:src/main/resources/images/manager.png");
			stage2.setTitle("Editando Personaje: " + chara.getName());
			stage2.setMaximized(false);
			// stage2.getIcons().add(image);
			// stage2.setResizable(false);;
			stage2.initModality(Modality.APPLICATION_MODAL);
			stage2.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@FXML
	private void edit_Chara() {
		if (chara != null) {
			try {
				FXMLLoader loader = new FXMLLoader(getClass().getResource("character_creator.fxml"));
				Parent root;
				root = loader.load();
				Character_Creator_Controller ccc = loader.getController();
				ccc.setController(u, p, chara, characters);
				Scene scene = new Scene(root);
				Stage stage2 = new Stage();
				stage2.setScene(scene);
				// Image image= new Image("file:src/main/resources/images/manager.png");
				stage2.setTitle("Editando Personaje: " + chara.getName());
				stage2.setMaximized(false);
				// stage2.getIcons().add(image);
				// stage2.setResizable(false);;
				stage2.initModality(Modality.APPLICATION_MODAL);
				stage2.show();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	@FXML
	private void remove_Chara() {

		if (table_characters.getSelectionModel().getSelectedItem() != null) {
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setHeaderText(null);
			alert.setTitle("Confirmación");
			alert.setContentText(" Se eliminará el personaje.\n" + " ¿Quire continuar?");

			Optional<ButtonType> result = alert.showAndWait();
			if (result.get() == ButtonType.OK) {
				chara = table_characters.getSelectionModel().getSelectedItem();
				CharacterDAO.delete(chara);
				p.getCharacters().remove(chara);
				characters.remove(chara);
				select_Chara();
			}
		}
	}

	@FXML
	private void update_body() {
		chapter.setBody(txtarea_chapter_body.getText());
	}

	@FXML
	private void update_notes() {
		chapter.setNotes(txtarea_chapter_notes.getText());
	}

	@FXML
	public void open_Change() {

		save_Chapter();

		Stage stage = (Stage) this.btn_new.getScene().getWindow();
		stage.close();

		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("SelectProject.fxml"));
			Parent root;
			root = loader.load();
			Project_Selector_Controller psc = loader.getController();
			psc.setController(u);
			Scene scene = new Scene(root);
			Stage stage2 = new Stage();
			stage2.setScene(scene);
			Image image = new Image("file:src/main/resources/images/icons/icon_app.jpg");
			stage2.setTitle("Selección de Projecto");
			stage2.getIcons().add(image);
			// stage2.setResizable(false);
			;
			stage2.initModality(Modality.APPLICATION_MODAL);
			stage2.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@FXML
	public void new_Chapter() {

		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("chapter_creator.fxml"));
			Parent root;
			root = loader.load();
			Chapter_Creator_Controller ccc = loader.getController();
			ccc.setController(p,null,chapters,main);
			Scene scene = new Scene(root);
			Stage stage2 = new Stage();
			stage2.setScene(scene);
			//Image image = new Image("file:src/main/resources/images/icons/icon_app.jpg");
			stage2.setTitle("Creador de Capítulos");
			//stage2.getIcons().add(image);
			// stage2.setResizable(false);
			;
			stage2.initModality(Modality.APPLICATION_MODAL);
			stage2.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@FXML
	public void edit_Chapter() {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("chapter_creator.fxml"));
			Parent root;
			root = loader.load();
			Chapter_Creator_Controller ccc = loader.getController();
			ccc.setController(p,chapter,chapters,main);
			Scene scene = new Scene(root);
			Stage stage2 = new Stage();
			stage2.setScene(scene);
			//Image image = new Image("file:src/main/resources/images/icons/icon_app.jpg");
			stage2.setTitle("Editando Capítulo: "+chapter.getTitle());
			//stage2.getIcons().add(image);
			// stage2.setResizable(false);
			;
			stage2.initModality(Modality.APPLICATION_MODAL);
			stage2.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void save_Chapter() {

		chapter.setBody(txtarea_chapter_body.getText());
		int i = chapters.indexOf(chapter);
		Chapter aux = chapters.get(i);
		aux.setBody(txtarea_chapter_body.getText());
		aux.setNotes(txtarea_chapter_notes.getText());
		
	}

	public void save_Projet() {
		for(Chapter c:chapters) {
			ChapterDAO.update(c);
		}
	}
	
	public void change_Chapter(Chapter c) {
		
		this.chapter = c;

		this.txt_chapter_title.setText("Capítulo " + chapter.getNumber() + ": " + chapter.getTitle());
		this.txtarea_chapter_body.setText(chapter.getBody());
		this.txtarea_chapter_notes.setText(chapter.getNotes());
	}

	@FXML
	public void goNext() {

		save_Chapter();

		int i = chapters.indexOf(chapter);
		if (i == chapters.size() - 1) {
			chapter = chapters.get(0);
			change_Chapter(chapter);
		} else {
			i++;
			chapter = chapters.get(i);
			change_Chapter(chapter);
		}
	}

	@FXML
	public void goBack() {

		save_Chapter();

		int i = chapters.indexOf(chapter);
		if (i == 0) {
			chapter = chapters.get(chapters.size() - 1);
			change_Chapter(chapter);
		} else {
			i--;
			chapter = chapters.get(i);
			change_Chapter(chapter);
		}
	}
}