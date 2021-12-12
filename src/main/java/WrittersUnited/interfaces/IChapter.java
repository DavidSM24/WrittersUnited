package WrittersUnited.interfaces;

import WrittersUnited.models.Chapter;
import WrittersUnited.models.Project;
import WrittersUnited.models.User;

public interface IChapter {
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

    public Project getProject();
    public void setProject(Project project);

}
