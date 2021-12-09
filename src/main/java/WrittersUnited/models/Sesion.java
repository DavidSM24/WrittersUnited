package WrittersUnited.models;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import WrittersUnited.interfaces.ISesion;

@Entity
@Table(name="Sesion")
@NamedQueries({
	@NamedQuery(name="isLogged",query="SELECT s FROM Sesion s WHERE id_user = :id_user"),
})
public class Sesion implements ISesion, Serializable{
	private Long id;
	private Long id_user;
	private Timestamp time;
	
	public Sesion(Long id, Long id_user, Timestamp time) {
		super();
		this.id = id;
		this.id_user = id_user;
		this.time = time;
	}

	public Sesion() {
		this(-1L,-1L, null);
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
	public Long getId_user() {
		return id_user;
	}

	@Override
	public void setId_user(Long id_user) {
		this.id_user = id_user;
	}

	@Override
	public Timestamp getTime() {
		return time;
	}

	@Override
	public void setTime(Timestamp timestamp) {
		this.time = timestamp;
	}

	@Override
	public String toString() {
		return "Session [id=" + id + ", id_user=" + id_user + ", time=" + time + "]";
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Sesion))
			return false;
		Sesion other = (Sesion) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
	
}
