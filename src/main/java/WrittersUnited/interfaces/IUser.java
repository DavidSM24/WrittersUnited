package WrittersUnited.interfaces;

import java.util.List;

import WrittersUnited.models.Project;

public interface IUser {
	
	public Long getId();
	public void setId(Long id);
	
	public String getUsername();
	public void setUsername(String userName);
	
	public String getPassword();
	public void setPassword(String password);
	
	public String getUsercode();
	public void setUsercode(String usercode);
	
	public boolean isConfirmed();
	public void setConfirmed(boolean confirmed);
	
	public List<Project> getProjects();
	public void setProjects(List<Project> projects);

}
