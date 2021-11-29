package WrittersUnited.interfaces;

public interface ICharacter {

	public Long getId();
	public void setId(Long id);
	
	public String getName();
	public void setName(String name);
	
	public String getDescription();
	public void setDescription(String description);
	
	public String getStory();
	public void setStory(String story);
	
	public String getImportance();
	public void setImportance(String imporance);
	
	public String getRol();
	public void setRol(String rol);
	
	public String getPhoto();
	public void setPhoto(String photo);
	
	public IProject getProject();
	public void setProject(IProject project);
	
	public String getNotes();
	public void setNotes(String notes);
}
