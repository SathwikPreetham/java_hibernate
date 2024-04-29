package UHCLSystem;

import java.util.ArrayList;
import java.util.Scanner;

public class eService_Student extends eService{
	
	public eService_Student(String i, Data d)
	{
		super(i, d);
		 
	}
	
	@Override
	public void welcome()
	{
		String selection = "";
		Scanner input = new Scanner(System.in);
		
		while(!selection.equals("x"))
		{
			System.out.println();
			System.out.println("Welcome to UHCL eService: ");
			System.out.println("v: view my courses");
			System.out.println("r: register a course");
			System.out.println("x: Logout");
			
			selection = input.nextLine();
			
			if(selection.equals("v"))
			{
				viewMyCourses();
			}
			else if(selection.equals("r"))
			{
				//register a new course
				register();
			}
		}
		 
	}
	
	@Override
	public void viewMyCourses()
	{
 
		ArrayList<String> courses = super.getD().getMyCourses(super.getId());
		for(String s: courses)
		{
			System.out.println(s);
		}
	}
	
	 
	public void register()
	{
		Scanner input = new Scanner(System.in);
		ArrayList<String>openCourses = super.getD().getOpenCourses(super.getId());
		for(int i=0; i<openCourses.size(); i++)
		{
			System.out.printf("%d: %s\n", i+1, openCourses.get(i));
		}
		System.out.println("Please enter your choice for the course registration: ");
		String intSel = input.nextLine();
		String selection = "";
		if(isInteger.test(intSel))
		{
			int sel = Integer.parseInt(intSel);
			if(sel>0 && sel<=openCourses.size())
			{
				selection = openCourses.get(sel-1);
				super.getD().registerCourse(super.getId(), selection);
			}
			
		}
		
	}
	
	
	

	
}
