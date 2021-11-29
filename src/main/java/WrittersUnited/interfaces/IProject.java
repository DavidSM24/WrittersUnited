package WrittersUnited.interfaces;

import java.util.List;

public interface IProject {

	public Long getId();
	public void setId(Long id);
	
	public String getTitle();
	public void setTitle(String title);
	
	public String getDescription();
	public void setDescription(String description);
	
	public List<ICharacter> getCharacters();
	public void setCharacters(List<ICharacter> characters);

}
