package WrittersUnited.models;

import java.util.ArrayList;
import java.util.List;
import WrittersUnited.models.Character;

import WrittersUnited.interfaces.IProject;

public class Project implements IProject {

	Long id;
	String title;
	String description;
	List<Character> characters;	
	
	public Project(Long id, String title, String description, List<Character> characters) {
		super();
		this.id = id;
		this.title = title;
		this.description = description;
		this.characters = characters;
	}

	public Project(String title, String description, List<Character> characters) {
		super();
		this.title = title;
		this.description = description;
		this.characters = characters;
	}

	public Project() {
		this(-1L,"","",new ArrayList<Character>());
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
	public String getTitle() {
		return title;
	}

	@Override
	public void setTitle(String title) {
		this.title = title;
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
	public List<Character> getCharacters() {
		return characters;
	}

	@Override
	public void setCharacters(List<Character> characters) {
		this.characters = characters;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Project))
			return false;
		Project other = (Project) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Project [id=" + id + ", title=" + title + ", description=" + description + ", characters=" + characters
				+ "]";
	}

}