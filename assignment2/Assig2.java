// CS 0445 Fall 2020
// Assignment 2 Driver program to test MyStringBuilder class.  The output
// from your execution should exactly match that shown in the file
// A2Out.txt.  Look at the output and match it line by line with the
// println() statements in this program to see which output corresponds to
// which println().

// Some additional comments follow in individual code segments
public class Assig2
{
	public static void main(String [] args)
	{
		System.out.println("Testing constructor methods");
		MyStringBuilder b1 = new MyStringBuilder("this is a string");
		char [] c = {' ','a','n','o','t','h','e','r',' ','s','t','r','i','n','g'}; 
		MyStringBuilder b2 = new MyStringBuilder(c);
		MyStringBuilder b3 = new MyStringBuilder();

		System.out.println(b1);
		System.out.println(b2);
		System.out.println(b3);  // will show as an empty line
		
		System.out.println("Testing copy constructor");
		MyStringBuilder b4 = new MyStringBuilder(b2);
		System.out.println(b4);
		
		System.out.println("\nTesting Append methods");
		b1.append(b2);
		System.out.println(b1);
		b1.append(" and another");
		System.out.println(b1);
		b1.append(c);
		System.out.println(b1);
		b1.append('!');  b1.append('!');  // char append
		System.out.println(b1);
		System.out.println();
		
		 b2.append(" different strings?");
		
		System.out.println("Testing for independence of strings");
		System.out.println(b1); // Testing for independence of the StringBuilders
		System.out.println(b2); // after append.
		System.out.println(b4); 
		System.out.println();
		
		System.out.println("Special case append to empty string");
		// Special case appending to empty object
		b3.append("...appending data");
		System.out.println(b3);

		b2 = new MyStringBuilder(c);  // reinitialize b2
		System.out.println("\nTesting charAt method");
		for (int i = 0; i < b2.length(); i++)
		{
			System.out.print(b2.charAt(i));
		}
		System.out.println();

		System.out.println("\nTesting delete method");
		b1 = new MyStringBuilder("we build a string of everything");
		System.out.println(b1);
		b1.delete(9,21);
		System.out.println(b1);
		// Deleting from the front
		b1.delete(0,3);
		System.out.println(b1);
		// Deleting "past" the end just deletes to the end
		b1.delete(5,60);
		System.out.println(b1);

		System.out.println("\nTesting deleteCharAt method");
		b1 = new MyStringBuilder("Xhere is a funney little stringh");
		System.out.println(b1);
		// Delete from the front
		b1.deleteCharAt(0);
		System.out.println(b1);
		// Delete in middle
		b1.deleteCharAt(14);
		System.out.println(b1);
		// Delete at end
		b1.deleteCharAt(b1.length()-1);
		System.out.println(b1);
		// Delete at location past the end does nothing
		b1.deleteCharAt(40);
		System.out.println(b1);

		System.out.println("\nTesting indexOf method");
		b1 = new MyStringBuilder("who is whoing over in whoville");
		// MyStringBuilder b9 = new MyStringBuilder("hehelloh"); //added this to test
		String s1 = new String("who");
		String s2 = new String("whoing");
		String s3 = new String("whoville");
		String s4 = new String("whoviller");
		String s5 = new String("wacky");
		// String s6 = new String("hello"); //added this to test
		int i1 = b1.indexOf(s1);
		int i2 = b1.indexOf(s2);
		int i3 = b1.indexOf(s3);
		int i4 = b1.indexOf(s4);
		int i5 = b1.indexOf(s5);
		// int i6 = b9.indexOf(s6); //added this to test
		System.out.println(s1 + " was found at " + i1);
		System.out.println(s2 + " was found at " + i2);
		System.out.println(s3 + " was found at " + i3);
		System.out.println(s4 + " was found at " + i4);
		System.out.println(s5 + " was found at " + i5);
		// System.out.println(s6 + " was found at " + i6); //added this to test

		System.out.println("\nTesting insert and replace methods");
		b1 = new MyStringBuilder("this is cool");
		b1.insert(8, "very ");
		System.out.println(b1);
		// Insert at the front
		b1.insert(0, "Wow, ");
		System.out.println(b1);
		// Testing replace method
		b1.replace(13, 17, "seriously");
		System.out.println(b1);
		// Replace deletes to end, then inserts to end of string
		b1.replace(23, 40, "challenging");
		System.out.println(b1);

		System.out.println("\nTesting substring method");
		b1 = new MyStringBuilder("work hard to finish this assignment");
		String sub = b1.substring(25, 31);
		System.out.println(sub);
		sub = b1.substring(5,9);
		System.out.println(sub);
		// Substring at front
		sub = b1.substring(0,4);
		System.out.println(sub);
		
		System.out.println("\nTesting MyStringBuilder return types");
		b1 = new MyStringBuilder("Hello");
		b2 = new MyStringBuilder("StringBuilder");
		b1.append(" there ").append(b2).append(' ').append("fans!");
		System.out.println(b1);
		b1.delete(12,25).insert(12,"CS0445").append('!');
		System.out.println(b1);
		System.out.println();
		
		System.out.println("Testing Reverse");
		b1 = new MyStringBuilder("Let's Reverse a String");
		System.out.println("Orig: " + b1);
		b1.reverse();
		System.out.println("Reversed: " + b1);
		b1.reverse();
		System.out.println("Back to orig: " + b1);
		// Reverse should also return a StringBuilder
		b1.reverse().reverse().reverse();
		System.out.println("Should be reversed: " + b1);
	}
}