package WrittersUnited.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import WrittersUnited.interfaces.ICharacter;

@Entity
@Table(name="Chara")
@NamedQueries({
	@NamedQuery(name="getCharacterName",query="SELECT c FROM Character c WHERE LOWER(name) LIKE :name"),
})
public class Character implements ICharacter,Serializable {
	// //id,name,description,story,importance,rol,photo,project,notes
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	Long id;
	
	@Column(name="name")
	String name;
	
	@Column(name="description")
	String description;
	
	@Column(name="story")
	String story;
	
	@Column(name="importance")
	String importance;
	
	@Column(name="rol")
	String rol;
	
	@Column(name="photo")
	String photo;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="id_project")
	Project chara_project;
	
	@Column(name="notes")
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
		this.chara_project = project;
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
		this.chara_project = project;
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
		return chara_project;
	}

	@Override
	public void setProject(Project project) {
		this.chara_project = project;
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
		return "["+id+" - "+name+"]";
	}

}