// CS 0445 Fall 2020
// This program tests the additional methods required in Assignment 3.
// Make sure to also test your program with file Assig3.java

// Some additional comments follow in individual code segments
public class Assig3B
{
	public static void main(String [] args)
	{
		// Testing lastIndexOf method.
		System.out.println("Testing lastIndexOf method");
		MyStringBuilder2 b1 = new MyStringBuilder2("The sixth sick Sheik's sixth sheep's sick but the sixteenth sick Sheik's sixtieth sheep's sicker");
		String s1 = new String("sixth");
		String s2 = new String("sick");
		String s3 = new String("six");
		String s4 = new String("The");
		String s5 = new String("the");
		String s6 = new String("sixty");
		int i1 = b1.lastIndexOf(s1);
		System.out.println(s1 + " found last at " + i1);
		int i2 = b1.lastIndexOf(s2);
		System.out.println(s2 + " found last at " + i2);
		int i3 = b1.lastIndexOf(s3);
		System.out.println(s3 + " found last at " + i3);
		int i4 = b1.lastIndexOf(s4);
		System.out.println(s4 + " found last at " + i4);
		int i5 = b1.lastIndexOf(s5);
		System.out.println(s5 + " found last at " + i5);
		int i6 = b1.lastIndexOf(s6);
		System.out.println(s6 + " found last at " + i6);
		System.out.println();
		
		// Testing regMatch method.
		// These first 3 matches are discussed in the Assignment 3
		// specifications.
		String patA = "ABC";
		String [] patterns1 = { patA };
		MyStringBuilder2 B = new MyStringBuilder2("****BBABA999ABCABC");
		testMatch(B, patterns1);

		// B = new MyStringBuilder2("ss"); //added this case
		// String patA2020 ="s";
		// String patB2020 = "s";
		// String [] patterns2020 = {patA2020, patB2020};
		// testMatch(B, patterns2020);
		
		patA = "ABC";
		String patB = "123", patC = "XYZ";
		String [] patterns2 = { patA, patB, patC };
		B = new MyStringBuilder2("**BBB22AAYYCC3ZZZ**");
		testMatch(B, patterns2);
		
		patA = "ABC123XYZ";
		patB = "123";
		patC = "XYZ";
		String [] patterns3 = { patA, patB, patC };
		B = new MyStringBuilder2("BBB222YYYCCC");
		testMatch(B, patterns3);
		
		// Below are some additional tests to demonstrate the
		// functionality of the regMatch() method.  Look at the output and
		// make sure you understand why the output is what it is - this will
		// help you to better understand how the regMatch() method is
		// supposed to work.
		String str1 = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
		String str2 = "0123456789";
		String str3 = str1 + str2;
		String str4 = "*";
		String [] pat1 = {str3};
		String [] pat2 = {str2};
		String [] pat3 = {str1, str2};
		String [] pat4 = {str3, str2, str1};
		String [] pat5 = {str3, str2};
		String [] pat6 = {str3, str2, str4};
		String [] pat7 = {str3, str2, str1, str3, str2};
	
		b1 = new MyStringBuilder2("1234HelloThere456Friends---");
		MyStringBuilder2 b2 = new MyStringBuilder2("R2D2IsAVeryWittyDroid");
		MyStringBuilder2 b3 = new MyStringBuilder2("AA22-ABC123abc-ABC123***-BCA321***");
		
		testMatch(b1, pat1);
		testMatch(b1, pat2);
		testMatch(b1, pat3);
		testMatch(b1, pat4);
		testMatch(b2, pat1);
		testMatch(b2, pat5);
		testMatch(b3, pat6);
		testMatch(b2, pat6);
		testMatch(b1, pat7);
	}
	
	public static void testMatch(MyStringBuilder2 target, String [] pat)
	{
		System.out.print("Looking for pattern: ");
		for (String X: pat)
			System.out.print(X + " ");
		System.out.println();
		System.out.println("Looking in string: " + target);
		MyStringBuilder2 [] ans = target.regMatch(pat);
		if (ans != null)
		{
			System.out.print("Match: ");
			for (MyStringBuilder2 bb: ans)
				System.out.print(bb + " ");
			System.out.println();
		}		
		else
			System.out.println("No match found!");
		System.out.println();
	}
}