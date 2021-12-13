package WrittersUnited;

import WrittersUnited.DAOs.ChapterDAO;
import WrittersUnited.DAOs.ProjectDAO;
import WrittersUnited.models.Chapter;
import WrittersUnited.models.Project;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class Chapter_Creator_Controller {

	PrimaryController main;
	Project p;
	Chapter c;
	ObservableList<Chapter> chapters;

	@FXML
	Button btn_accept;
	
	@FXML
	private Button btn_cancel;
	
	@FXML
	private TextField txt_chapter_title;
	
	@FXML
	private TextArea txtarea_chapter_description;
	
	@FXML
	private TextArea txtarea_chapter_notes;
	
	public void setController(Project p, Chapter c, ObservableList<Chapter> chapters,PrimaryController main) {
		
		this.main=main;
		
		this.p=p;
		this.c=c;
		this.chapters=chapters;
		
		if(c!=null) { //update
			
			btn_accept.setText("Editar");
			
			txt_chapter_title.setText(c.getTitle());
			txtarea_chapter_description.setText(c.getDescription());
			txtarea_chapter_notes.setText(c.getNotes());
		}
		
	}
	
	@FXML
	public void save() {
		if(txt_chapter_title.getText().matches("")) {
			//alert rellenar campos
		}
		else { //guardar
			if(c!=null) { //update
				
				c.setTitle(txt_chapter_title.getText());
				c.setDescription(txtarea_chapter_description.getText());
				c.setNotes(txtarea_chapter_notes.getText());
				
				ChapterDAO.update(c);
				
				if(main.chapter!=null&&main.chapter.equals(c)) {
					main.save_Chapter();
					main.change_Chapter(c);
				}
				
				int i=chapters.indexOf(c);
				chapters.set(i, c);
			}
			else { //insert
				boolean first=false;
				
				if(chapters.size()<1) {
					first=true;
				}
				
				c=new Chapter(txt_chapter_title.getText(),
						chapters.size()+1,
						txtarea_chapter_description.getText(),
						"",
						txtarea_chapter_notes.getText(),p
						);
				p.getChapters().add(c);
				
				if(main.chapters.size()!=0) {
					//main.save_Chapter();
				}
				//ProjectDAO.update(p);
				ChapterDAO.insert(c);
			
				chapters.add(c);
				
				
				
				//main.chapter=aux;
				//main.change_Chapter(aux);
				
			}
			
			cancel(); //close windows
		}
	}
	
	@FXML
	public void cancel(){
		Stage stage = (Stage) this.btn_cancel.getScene().getWindow();
		stage.close();
	}
	
}
