package WrittersUnited.models;

import java.io.Serializable;

import javax.persistence.CascadeType;
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
import WrittersUnited.interfaces.IChapter;

@Entity
@Table(name="Chapter")
@NamedQueries({
	@NamedQuery(name="getByCTitle",query="SELECT c FROM Chapter c WHERE LOWER(title) LIKE :title"),
})
public class Chapter implements IChapter, Serializable{
    
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	protected Long id;
    
	@Column(name="title")
	protected String title;
    
	@Column(name="number")
	protected int number;
    
	@Column(name="description")
	protected String description;
    
	@Column(name="body",columnDefinition = "LONGTEXT")
	protected String body;
    
	@Column(name="notes",columnDefinition = "LONGTEXT")
	protected String notes;
    
	@ManyToOne(fetch=FetchType.EAGER,cascade = {CascadeType.MERGE})
	@JoinColumn(name="id_project")
	protected Project project;

    public Chapter(Long id, String title, int number, String description, String body, String notes, Project project) {
        this.id = id;
        this.title = title;
        this.number = number;
        this.description = description;
        this.body = body;
        this.notes = notes;
        this.project = project;
    }

    public Chapter(String title, int number, String description, String body, String notes, Project project) {
        this.id=null;
    	this.title = title;
        this.number = number;
        this.description = description;
        this.body = body;
        this.notes = notes;
        this.project = project;
    }
    
    public Chapter() {
    	this(1L,"",-1,"","","",new Project());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Chapter))
			return false;
		Chapter other = (Chapter) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
    public String toString() {
        return "Chapter{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", number=" + number +
                ", description='" + description + '\'' +
                ", body='" + body + '\'' +
                ", notes='" + notes + '\''+
                '}';
    }
}
