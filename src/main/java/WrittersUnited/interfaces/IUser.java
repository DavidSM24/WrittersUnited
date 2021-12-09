package WrittersUnited.interfaces;

import java.util.List;
import java.util.Set;

import WrittersUnited.models.Project;

public interface IUser {
	
	public Long getId();
	public void setId(Long id);
	
	public String getUsername();
	public void setUsername(String userName);
	
	public String getMail();
	public void setMail(String mail);
	
	public String getPassword();
	public void setPassword(String password);
	
	public String getUsercode();
	public void setUsercode(String usercode);
	
	public boolean isConfirmed();
	public void setConfirmed(boolean confirmed);
	
	public Set<Project> getProjects();
	public void setProjects(Set<Project> projects);

}
