package WrittersUnited;

import java.util.List;

import WrittersUnited.DAOs.ProjectDAO;
import WrittersUnited.DAOs.UserDAO;
import WrittersUnited.models.Project;
import WrittersUnited.models.User;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class Share_Controller {

	User selected;
	Project p;
	User u;

	ObservableList<Project> projects;
	ObservableList<User> users;

	@FXML
	Button btn_add;

	@FXML
	Button btn_remove;

	@FXML
	TextField txt_filter;

	@FXML
	TableView<Project> table_project;
	@FXML
	TableColumn<Project, String> col_projects;

	@FXML
	TableView<User> table_shared;
	@FXML
	TableColumn<User, String> col_shared;

	@FXML
	TableView<User> table_filter;
	@FXML
	TableColumn<User, String> col_filter;

	public void setController(User u) {
		this.u = u;

		projects = FXCollections.observableArrayList();
		projects.addAll(u.getProjects());

		table_project.setItems(projects);

		users = FXCollections.observableArrayList();
		users.addAll(UserDAO.getAll());
		users.remove(u);

		table_filter.setItems(users);

		setProjects_TableInfo();
		setFilterInfo();
	}

	private void setProjects_TableInfo() {
		// TODO Auto-generated method stub
		col_projects.setCellValueFactory(eachproject -> {
			SimpleStringProperty v = new SimpleStringProperty();
			v.setValue(eachproject.getValue().getTitle());
			return v;
		});
	}

	@FXML
	public void select_Project() {
		if (table_project.getSelectionModel().getSelectedItem() != null) {
			p = table_project.getSelectionModel().getSelectedItem();

			ObservableList<User> auxU = FXCollections.observableArrayList();
			for (User u : p.getShared_users()) {
				auxU.add(u);
			}

			table_shared.setId(null);
			table_shared.setItems(auxU);

			col_shared.setCellValueFactory(eachuser -> {
				SimpleStringProperty v = new SimpleStringProperty();
				v.setValue(eachuser.getValue().getUsername());
				return v;
			});
		}

		if (p != null) {
			select_user();
		} else {
			btn_add.setDisable(true);
			btn_remove.setDisable(true);
		}

	}

	public void setFilterInfo() {
		col_filter.setCellValueFactory(eachuser -> {
			SimpleStringProperty v = new SimpleStringProperty();
			v.setValue(eachuser.getValue().getUsername());
			return v;
		});
	}

	@FXML
	public void filter() {

		if (txt_filter.getText().matches("")) {
			table_filter.setItems(null);
			table_filter.setItems(users);
			setFilterInfo();
		} else {
			ObservableList<User> filter = FXCollections.observableArrayList();
			filter.addAll(UserDAO.filterByName(txt_filter.getText()));

			table_filter.setItems(null);
			table_filter.setItems(filter);
			setFilterInfo();

		}
	}

	@FXML
	public void select_user() {
		if (table_filter.getSelectionModel().getSelectedItem() != null) {
			selected = table_filter.getSelectionModel().getSelectedItem();

			if (p != null && p.getShared_users().contains(selected)) {
				btn_add.setDisable(true);
				btn_remove.setDisable(false);
			} else if (p != null) {
				btn_add.setDisable(false);
				btn_remove.setDisable(true);
			} else {
				btn_add.setDisable(true);
				btn_remove.setDisable(true);
			}
		} else {
			btn_add.setDisable(true);
			btn_remove.setDisable(true);
		}
	}

	@FXML
	public void select_from_shared() {
		if (table_shared.getSelectionModel().getSelectedItem() != null) {
			selected = table_shared.getSelectionModel().getSelectedItem();

			if (p != null && p.getShared_users().contains(selected)) {
				btn_add.setDisable(true);
				btn_remove.setDisable(false);
			} else if (p != null) {
				btn_add.setDisable(false);
				btn_remove.setDisable(true);
			} else {
				btn_add.setDisable(true);
				btn_remove.setDisable(true);
			}
		}
	}

	@FXML
	public void add() {
		
		if (p != null && selected != null) {
			ProjectDAO.shareProject(p, selected);
			table_shared.getItems().add(selected);
			p.getShared_users().add(selected);
			
			btn_add.setDisable(true);
			btn_remove.setDisable(false);
		}
		
	}

	@FXML
	public void remove() {
		if (p != null && selected != null) {
			ProjectDAO.unshareProject(p, selected);
			table_shared.getItems().remove(selected);
			p.getShared_users().remove(selected);

			btn_add.setDisable(false);
			btn_remove.setDisable(true);
		}
	}
}
