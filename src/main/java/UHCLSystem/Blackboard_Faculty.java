package UHCLSystem;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

public class Blackboard_Faculty extends Blackboard{

	/**
	 * @param id
	 * @param d
	 */
	public Blackboard_Faculty(String id, Data d) {
		super(id, d);
		// TODO Auto-generated constructor stub
	}
	
	public void welcome(String selectionCourse,String writer) {
	    String selection = "";
	    Scanner input = new Scanner(System.in);
	    String s = DateAndTime.DateTime();
	    int count = 0;
	    while (!selection.equals("x")) {
	        System.out.println();
	        System.out.println("Please select your options:");
	        System.out.println("v: view course notes");
	        System.out.println("p: post new course note");
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
	        else if(selection.equals("p"))
	        {
	        	System.out.println("Enter your note:");
	            String content = input.nextLine();
	            String datetime = getCurrentDateTime(); // assuming you have a method to get the current date and time
	        	super.getD().writeNote(writer, content, selectionCourse, datetime);
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
		ArrayList<String> teachings = getD().getMyTeachingCourses(getId());
		for(int i=0; i<teachings.size(); i++)
		{
			System.out.printf("%d: %s\n", i+1, teachings.get(i));
		}
		System.out.println("Please select a course of your teaching to view: ");
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
