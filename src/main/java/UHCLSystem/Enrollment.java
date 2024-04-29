package UHCLSystem;
import javax.persistence.*;


@Entity
@Table(name="enrollment")
public class Enrollment {
	
	@Id
	@Column(name="autoID")
	private int id;
	@Column(name="studentID")
	private String sid;
	@Column(name="courseID")
	private String cid;
	
	public Enrollment()
	{
		
	}
	
	public Enrollment(int i, String s, String c)
	{
		this.id = i;
		this.sid = s;
		this.cid = c;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSid() {
		return sid;
	}

	public void setSid(String sid) {
		this.sid = sid;
	}

	public String getCid() {
		return cid;
	}

	public void setCid(String cid) {
		this.cid = cid;
	}
	
	

}