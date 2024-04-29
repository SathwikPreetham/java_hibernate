package UHCLSystem;

public class isInteger {
	
	public static boolean test(String a)
	{
	 
		try
		{
			int i = Integer.parseInt(a);
			return true;
		}
		catch (Exception e)
		{
			return false;
		}
	     
	}

}
