package WrittersUnited.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import WrittersUnited.models.Character;

import WrittersUnited.interfaces.IProject;

@Entity
@Table(name="Project")
@NamedQueries({
	@NamedQuery(name="getByTitle",query="SELECT p FROM Project p WHERE LOWER(title) LIKE :title"),
	@NamedQuery(name="getByUserCreator",query="SELECT p FROM Project p WHERE id_creation_user= :id")
})
public class Project implements IProject,Serializable {
//id,title,description,id_cration_user
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	Long id;
	
	@Column(name="title")
	String title;
	
	@Column(name="description")
	String description;
	
	@OneToMany(mappedBy = "project",orphanRemoval = true)
	Set<Chapter> chapters;
	
	@OneToMany(mappedBy = "chara_project",orphanRemoval = true)
	Set<Character> characters;
	
	@ManyToOne(fetch=FetchType.EAGER,cascade = CascadeType.MERGE)
	@JoinColumn(name="id_creation_user")
	User user_creator;
	
	@ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(name = "user_projectshared",
            joinColumns = @JoinColumn(name = "id_project"),
            inverseJoinColumns = @JoinColumn(name = "id_user")
    )
	Set<User> shared_users;
	
	public Project(Long id, String title, String description, Set<Character> characters, User user, Set<Chapter> chapters) {
		super();
		this.id = id;
		this.title = title;
		this.description = description;
		this.characters = characters;
		this.chapters=chapters;
		this.user_creator=user;
	}

	public Project(String title, String description, Set<Character> characters,User user,Set<Chapter> chapters) {
		super();
		this.title = title;
		this.description = description;
		this.characters = characters;
		this.chapters=chapters;
		this.user_creator=user;
	}

	public Project() {
		this(-1L,"","",new HashSet<Character>(), new User(),new HashSet<Chapter>());
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
	public Set<Character> getCharacters() {
		return characters;
	}

	@Override
	public void setCharacters(Set<Character> characters) {
		this.characters = characters;
	}
	
	public Set<Chapter> getChapters() {
		return chapters;
	}

	public void setChapters(Set<Chapter> chapters) {
		this.chapters = chapters;
	}

	public User getUser_creator() {
		return user_creator;
	}

	public void setUser_creator(User user_creator) {
		this.user_creator = user_creator;
	}

	public Set<User> getShared_users() {
		return shared_users;
	}

	public void setShared_users(Set<User> shared_users) {
		this.shared_users = shared_users;
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
		return "Project [id=" + id + ", title=" + title + ", description=" + description + ", chapters=" + chapters
				+ ", user_creator=" + user_creator.getUsername() + ", characters=" + characters + "]";
	}

}