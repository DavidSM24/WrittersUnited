package WrittersUnited;

import java.util.ArrayList;
import java.util.List;

import WrittersUnited.DAOs.ChapterDAO;
import WrittersUnited.models.Chapter;
import WrittersUnited.models.Project;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

public class Sort_Controller {

	boolean changed=false;
	Project p;
	ObservableList<Chapter> chapters;
	PrimaryController main;

	@FXML
	Button btn_cancel;
	
	@FXML
	Button btn_Up;
	
	@FXML
	Button btn_Down;

	@FXML
	TableView<Chapter> table_chapter;

	@FXML
	TableColumn<Chapter, String> col_num;

	@FXML
	TableColumn<Chapter, String> col_name;

	@FXML
	public void save() {
		if(changed) {
			chapters=table_chapter.getItems();
			
			for(Chapter c:chapters) {
				ChapterDAO.update(c);
			}
			
			main.table_chapter.setItems(null);
			main.table_chapter.setItems(chapters);
			main.setTableInfo();
		}
		
		cancel();
	}
	
	@FXML
	public void cancel(){
		Stage stage = (Stage) this.btn_cancel.getScene().getWindow();
		stage.close();
	}
	
	public void setController(Project p, ObservableList<Chapter> chapters, PrimaryController main) {
		this.main = main;
		this.p = p;
		this.chapters = chapters;

		if(chapters.size()>0) {
			ObservableList<Chapter> listTable=FXCollections.observableArrayList();
			for(Chapter ca:chapters) {
				listTable.add(ca);
				table_chapter.setItems(listTable);
			}
		}
		else {
			table_chapter.setItems(chapters);
		}
		
		setTableInfo();
		
	}

	private void setTableInfo() {
		// TODO Auto-generated method stub
		col_name.setCellValueFactory(eachchapter -> {
			SimpleStringProperty v = new SimpleStringProperty();
			v.setValue(eachchapter.getValue().getTitle());
			return v;
		});

		List<Integer> ns=new ArrayList<Integer>();
		for (int i = 0; i < chapters.size(); i++) {
			ns.add(i+1);
		}
		
		col_num.setCellValueFactory(eachchapter -> {

			int i=1;

			SimpleStringProperty v = new SimpleStringProperty();
			v.setValue(ns.get(eachchapter.getValue().getNumber()-1)+"");
				
			return v;
		});

	}

	@FXML
	public void toUp() {
		if(table_chapter.getSelectionModel().getSelectedIndex()!=0) { //no es el último
			
			changed=true;
			
			int index = table_chapter.getSelectionModel().getSelectedIndex();
	        // swap items
	        table_chapter.getItems().add(index-1, table_chapter.getItems().remove(index));
	        // select item at new position
	        table_chapter.getSelectionModel().clearAndSelect(index-1);        
	        
	        table_chapter.getSelectionModel().getSelectedItem().setNumber(index);	
	        table_chapter.getItems().get(index).setNumber(index+1);
	        
	        //setTableInfo();
			
		}
	}
	
	@FXML
	public void toDown() {
		if(table_chapter.getSelectionModel().getSelectedIndex()<chapters.size()-1) { //no es el último
			
			changed=true;
			
			int index = table_chapter.getSelectionModel().getSelectedIndex();
	        // swap items
	        table_chapter.getItems().add(index+1, table_chapter.getItems().remove(index));
	        // select item at new position
	        table_chapter.getSelectionModel().clearAndSelect(index+1);
	        
	        
	        //cnahge n
	        table_chapter.getSelectionModel().getSelectedItem().setNumber(index+2);
	        table_chapter.getItems().get(index).setNumber(index+1);
			
	        //setTableInfo();
		}
	}
}
