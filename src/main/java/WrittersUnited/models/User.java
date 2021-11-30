package WrittersUnited.models;

import java.util.ArrayList;
import java.util.List;

import WrittersUnited.interfaces.IProject;
import WrittersUnited.interfaces.IUser;

public class User implements IUser{

	Long id;
	String username;
	String password;
	String usercode;
	boolean confirmed;
	List<Project> projects;

	public User(Long id, String username, String password, String usercode, boolean confirmed, List<Project> projects) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.usercode = usercode;
		this.confirmed = confirmed;
		this.projects = projects;
	}

	public User(String username, String password, String usercode, boolean confirmed, List<Project> projects) {
		super();
		this.username = username;
		this.password = password;
		this.usercode = usercode;
		this.confirmed = confirmed;
		this.projects = projects;
	}
	
	public User() {
		this(-1L,"","","",false,new ArrayList<Project>());
	}
	
	@Override
	public Long getId() {
		return id;
	}
	@Override
	public void setId(Long id) {
		this.id = id;
	}
	@Override
	public String getUsername() {
		return username;
	}
	@Override
	public void setUsername(String username) {
		this.username = username;
	}
	@Override
	public String getPassword() {
		return password;
	}
	@Override
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public String getUsercode() {
		return usercode;
	}
	@Override
	public void setUsercode(String usercode) {
		this.usercode = usercode;
	}
	@Override
	public boolean isConfirmed() {
		return confirmed;
	}
	@Override
	public void setConfirmed(boolean confirmed) {
		this.confirmed = confirmed;
	}

	@Override
	public List<Project> getProjects() {
		// TODO Auto-generated method stub
		return this.projects;
	}

	@Override
	public void setProjects(List<Project> projects) {
		// TODO Auto-generated method stub
		this.projects=projects;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof User))
			return false;
		User other = (User) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", usercode=" + usercode
				+ ", confirmed=" + confirmed + ", projects=" + projects + "]";
	}
}