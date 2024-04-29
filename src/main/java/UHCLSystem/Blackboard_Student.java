package UHCLSystem;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

public class Blackboard_Student extends Blackboard{

	/**
	 * @param id
	 * @param d
	 */
	public Blackboard_Student(String id, Data d) {
		super(id, d);
		// TODO Auto-generated constructor stub
	}

	public void welcome(String selectionCourse,String writer) {
	    String selection = "";
	    Scanner input = new Scanner(System.in);
	    int count = 0;
	    String s = DateAndTime.DateTime();

	    while (!selection.equals("x")) {
	        System.out.println();
	        System.out.println("Please select your options:");
	        System.out.println("v: view course notes");
	        System.out.println("x: leave the course");

	        selection = input.nextLine();
	        if(selection.equals("v"))
	        {
	        	for (coursenote note : super.getD().getCourseNotes(selectionCourse)) {
	        		count++;
	        		if(count == 1)
		        	{
		        		System.out.println("Course Notes of " + selectionCourse + " is :");
		        	}
	        	    System.out.println(s +" : " + note.getContent());
	        	}
	        }
	        else
	        {
	        	return;
	        }

	        
	    }
	}
	
	@Override
	public void viewMyCourses(String writer)
	{
		Scanner input = new Scanner(System.in);
		ArrayList<String> teachings = getD().getMyCourses(getId());
		for(int i=0; i<teachings.size(); i++)
		{
			System.out.printf("%d: %s\n", i+1, teachings.get(i));
		}
		System.out.println("Please select your options");
		String intSel = input.nextLine();
		String selectionCourse = "";
		if(isInteger.test(intSel))
		{
			int sel = Integer.parseInt(intSel);
			if(sel>0 && sel<=teachings.size())
			{
				selectionCourse = teachings.get(sel-1);
				welcome(selectionCourse,writer);
			}
			
		}
	}
	
	private String getCurrentDateTime() {
	    return LocalDateTime.now().toString();
	}
	 
	
	

}
