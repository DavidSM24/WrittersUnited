package WrittersUnited.interfaces;

import WrittersUnited.models.Character;
import java.util.List;

public interface IProject {

	public Long getId();
	public void setId(Long id);
	
	public String getTitle();
	public void setTitle(String title);
	
	public String getDescription();
	public void setDescription(String description);
	
	public List<Character> getCharacters();
	public void setCharacters(List<Character> characters);

}
