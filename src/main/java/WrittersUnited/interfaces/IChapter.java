package WrittersUnited.interfaces;

public interface IChapter<T> {
    public Long getId();
    public void setId(Long id);

    public String getTitle();
    public void setTitle(String title);

    public int getNumber();
    public void setNumber(int number);

    public String getDescription();
    public void setDescription(String description);

    public String getBody();
    public void setBody(String body);

    public String getNotes();
    public void setNotes(String notes);

    public IProject getProject();
    public void setProject(Iproject project);

}
