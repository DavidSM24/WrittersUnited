package WrittersUnited.interfaces;

import java.util.List;

public interface IUser {
	
	public Long getId();
	public void setId(Long id);
	
	public String getUserName();
	public void setUserName(String userName);
	
	public String getPassword();
	public void setPasword(String password);
	
	public boolean isConfirmed();
	public void setConfirmed(boolean confirmed);
	
	public List<IProject> getProjects();
	public void setProjects(List<IProject> projects);

}
