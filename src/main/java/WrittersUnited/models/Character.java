package WrittersUnited.models;

import WrittersUnited.interfaces.ICharacter;

import javax.persistence.*;

@Entity
@Table(name = "character")
@NamedQueries({
		@NamedQuery(name = "finfByName",query = "SELECT name FROM character")
})

public class Character implements ICharacter {
	// //id,name,description,story,importance,rol,photo,project,notes
	@Id
	@Column(name = "id")
	Long id;
	@Column(name = "name")
	String name;
	String description;
	String story;
	String importance;
	String rol;
	String photo;
	Project project;
	String notes;

	public Character(Long id, String name, String description, String story, String importance, String rol,
			String photo, Project project, String notes) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.story = story;
		this.importance = importance;
		this.rol = rol;
		this.photo = photo;
		this.project = project;
		this.notes = notes;
	}

	public Character(String name, String description, String story, String importance, String rol, String photo,
			Project project, String notes) {
		super();
		this.name = name;
		this.description = description;
		this.story = story;
		this.importance = importance;
		this.rol = rol;
		this.photo = photo;
		this.project = project;
		this.notes = notes;
	}

	public Character() {
		this(-1L,"","","","","","",new Project(),"");
	}

	@Override
	public Long getId() {
		return id;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String getDescription() {
		return description;
	}

	@Override
	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String getStory() {
		return story;
	}

	@Override
	public void setStory(String story) {
		this.story = story;
	}

	@Override
	public String getImportance() {
		return importance;
	}

	@Override
	public void setImportance(String importance) {
		this.importance = importance;
	}

	@Override
	public String getRol() {
		return rol;
	}

	@Override
	public void setRol(String rol) {
		this.rol = rol;
	}

	@Override
	public String getPhoto() {
		return photo;
	}

	@Override
	public void setPhoto(String photo) {
		this.photo = photo;
	}

	@Override
	public Project getProject() {
		return project;
	}

	@Override
	public void setProject(Project project) {
		this.project = project;
	}

	@Override
	public String getNotes() {
		return notes;
	}

	@Override
	public void setNotes(String notes) {
		this.notes = notes;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Character))
			return false;
		Character other = (Character) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Character [id=" + id + ", name=" + name + ", description=" + description + ", story=" + story
				+ ", importance=" + importance + ", rol=" + rol + ", photo=" + photo + ", project=" + project
				+ ", notes=" + notes + "]";
	}

}