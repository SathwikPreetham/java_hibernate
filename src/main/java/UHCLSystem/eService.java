package UHCLSystem;

public abstract class eService {
	
	private String id;
	private Data d;
	
	
	public eService(String i, Data d)
	{
		this.id = i;
		this.d = d;
	}
	
	public abstract void welcome();
	
	public abstract void viewMyCourses();

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Data getD() {
		return d;
	}

	public void setD(Data d) {
		this.d = d;
	}
	
	
	

}
