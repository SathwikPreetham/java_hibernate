package UHCLSystem;

import java.util.Scanner;

public class UHCLESystem {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Scanner input = new Scanner(System.in);
		String selection = "";
		Data d = new Hibernate();
		
		while(!selection.equals("x"))  //while not x, keep displaying the menu
		{
			//display the menu
			System.out.println();
			System.out.println("**** Go Hawks! ****");
            System.out.println("Please make your selection");
            System.out.println("1: e-Service");
            System.out.println("2: BlackBoard");
            System.out.println("x: Leave");
            System.out.println();
			
			//get the selection from the user
			selection = input.nextLine();
			System.out.println();
			
			if(selection.equals("1"))
			{
				System.out.println("Please enter your id: ");
				String id = input.nextLine();
				System.out.println("Please enter your password: ");
				String password = input.nextLine();
				//login eService;
				 
				uhcluser u = d.login(id, password);
				
				if(u!=null && u.getType().equals("student"))
				{
					
					eService_Student e = new eService_Student(u.getLoginID(), d);
					e.welcome();
				}
				
				if(u!=null && u.getType().equals("faculty"))
				{
					eService_Faculty e = new eService_Faculty(u.getLoginID(), d);
					e.welcome();
					
				}
				 
			}
			
			if(selection.equals("2"))
			{
				System.out.println("Please enter your id: ");
				String id = input.nextLine();
				System.out.println("Please enter your password: ");
				String password = input.nextLine();
				//login eService;
				 
				uhcluser u = d.login(id, password);
				
				if(u!=null && u.getType().equals("student"))
				{
					
					Blackboard_Student e = new Blackboard_Student(u.getLoginID(), d);
					e.welcome();
				}
				
				
				if(u!=null && u.getType().equals("faculty")) 
				{ 
					Blackboard_Faculty e = new
						Blackboard_Faculty(u.getLoginID(), d); 
					
					e.viewMyCourses(id);

				}
				 
				 
			}
		}
	}

}
