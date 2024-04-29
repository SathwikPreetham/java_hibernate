package UHCLSystem;

import java.util.Scanner;

public abstract class Blackboard {
	
	private String id;
	private Data d;
	
	public Blackboard(String id, Data d)
	{
		this.id = id;
		this.d = d;
	}
	
	public void welcome() {
		// TODO Auto-generated method stub
		String selection = "";
		Scanner input = new Scanner(System.in);
		
		while(!selection.equals("x"))
		{
			System.out.println();
			System.out.println("Welcome to UHCL Blackboard: ");
			System.out.println("v: view my courses");
			System.out.println("x: Logout");
			
			selection = input.nextLine();
			
			if(selection.equals("v"))
			{
				viewMyCourses(id);
			}
			 
		}
	}
	
	public abstract void viewMyCourses(String id);

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
