// CS 0445 Fall 2020
// Additional test file for indexOf 
public class Assig2C
{
	public static void main(String [] args)
	{
		MyStringBuilder b1 = new MyStringBuilder("XXXXXXXXXXXXXXXXY");
		MyStringBuilder b2 = new MyStringBuilder("XXXXXXXXXXXXXXXXX");
		String check1 = "XXXXXY";
		String check2 = "XXXXXX";
		int ans1 = b1.indexOf(check1);
		int ans3 = b2.indexOf(check1);
		
		System.out.println(check1 + " found at " + ans1 + " in " + b1);
		System.out.println(check1 + " found at " + ans3 + " in " + b2);
		
		ans1 = b1.indexOf(check2);
		ans3 = b2.indexOf(check2);

		System.out.println(check2 + " found at " + ans1 + " in " + b1);
		System.out.println(check2 + " found at " + ans3 + " in " + b2);
	}
}