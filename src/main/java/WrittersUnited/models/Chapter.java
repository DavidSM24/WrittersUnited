package WrittersUnited.models;

public class Chapter {
    protected long id;
    protected String title;
    protected int number;
    protected  String description;
    protected String body;
    protected String notes;
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

    public long getId() {
        return id;
    }

    public void setId(long id) {
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
                ", notes='" + notes + '\'' +
                ", project=" + project +
                '}';
    }

}
