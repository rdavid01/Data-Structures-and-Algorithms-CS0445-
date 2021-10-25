// CS 0445 Fall 2020
// Additional test file for indexOf and lastIndexOf methods
public class Assig3C
{
	public static void main(String [] args)
	{
		MyStringBuilder2 b1 = new MyStringBuilder2("XXXXXXXXXXXXXXXXY");
		MyStringBuilder2 b2 = new MyStringBuilder2("XXXXXXXXXXXXXXXXX");
		String check1 = "XXXXXY";
		String check2 = "XXXXXX";
		int ans1 = b1.indexOf(check1);
		int ans2 = b1.lastIndexOf(check1);
		int ans3 = b2.indexOf(check1);
		int ans4 = b2.lastIndexOf(check1);
		
		System.out.println(check1 + " found at " + ans1 + " in " + b1);
		System.out.println(check1 + " found last at " + ans2 + " in " + b1);
		System.out.println(check1 + " found at " + ans3 + " in " + b2);
		System.out.println(check1 + " found last at " + ans4 + " in " + b2);
		
		ans1 = b1.indexOf(check2);
		ans2 = b1.lastIndexOf(check2);
		ans3 = b2.indexOf(check2);
		ans4 = b2.lastIndexOf(check2);

		System.out.println(check2 + " found at " + ans1 + " in " + b1);
		System.out.println(check2 + " found last at " + ans2 + " in " + b1);
		System.out.println(check2 + " found at " + ans3 + " in " + b2);
		System.out.println(check2 + " found last at " + ans4 + " in " + b2);
	}
}