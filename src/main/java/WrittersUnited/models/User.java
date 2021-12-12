package WrittersUnited.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import WrittersUnited.interfaces.IUser;

@Entity
@Table(name="User")
@NamedQueries({
	@NamedQuery(name="getByName",query="SELECT u FROM User u WHERE username LIKE :username"),
	@NamedQuery(name="getByMail",query="SELECT u FROM User u WHERE mail LIKE :mail"),
	@NamedQuery(name="getByUserCode",query="SELECT u FROM User u WHERE usercode LIKE :usercode")
})
public class User implements IUser, Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Long id;
	
	@Column(name="username")
	private String username;
	
	@Column(name="mail")
	private String mail;
	
	@Column(name="password")
	private String password;
	
	@Column(name="usercode")
	private String usercode;
	
	@Column(name="confirmed") //tipo set en bbdd, necesita columnDefinition?
	private boolean confirmed;
	
	
	
	@OneToMany(mappedBy = "user_creator",orphanRemoval = true)
	Set<Project> projects;

	public User(Long id, String username,String mail, String password, String usercode, boolean confirmed, Set<Project> projects) {
		super();
		this.id = id;
		this.username = username;
		this.mail=mail;
		this.password = password;
		this.usercode = usercode;
		this.confirmed = confirmed;
		this.projects = projects;
	}

	public User(String username, String mail, String password, String usercode, boolean confirmed, Set<Project> projects) {
		super();
		this.username = username;
		this.mail=mail;
		this.password = password;
		this.usercode = usercode;
		this.confirmed = confirmed;
		this.projects = projects;
	}
	
	public User() {
		this(-1L,"","","","",false,new HashSet<Project>());
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
	public String getMail() {
		return mail;
	}
	@Override
	public void setMail(String mail) {
		this.mail = mail;
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
	public Set<Project> getProjects() {
		// TODO Auto-generated method stub
		return this.projects;
	}

	@Override
	public void setProjects(Set<Project> projects) {
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
		return "User [id=" + id + ", username=" + username + ", mail=" + mail + ", password=" + password + ", usercode="
				+ usercode + ", confirmed=" + confirmed + ", projects=" + projects + "]";
	
	}	
}