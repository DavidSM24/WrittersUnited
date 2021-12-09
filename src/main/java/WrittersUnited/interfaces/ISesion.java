package WrittersUnited.interfaces;

import java.sql.Timestamp;

public interface ISesion {
	
	public Long getId();

	public void setId(Long id);

	public Long getId_user();

	public void setId_user(Long id_user);

	public Timestamp getTime();
	
	public void setTime(Timestamp timestamp);
	
}
