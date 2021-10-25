// David Reidenbaugh
//CS 0445 Fall 2020
/*
This program tests the additional mehtod allIndexOf that I wrote to get extra credit for assignment3.
If MSB appears in the comments, that means MyStringBuilder2

allIndexOf finds and returns each index where a string appears in an ArrayList of Integer
If the String is not found, return an arrayList with only -1

Another thing to note is that I wrote tests for special cases.
These special cases inclde:
	When the String being searched for is longer than the MSB
	when the String has a length of 1
	when the String is has a length of 0 (is empty)
*/

// Some additional comments follow in individual code segments
import java.util.ArrayList;
public class Assig3ExtraCreditTest
{
	public static void main(String [] args)
	{
		System.out.println("Testing allIndexOf method\n");
		MyStringBuilder2 b1 = new MyStringBuilder2("The sixth sick Sheik's sixth sheep's sick but the sixteenth sick Sheik's sixtieth sheep's sicker");
		System.out.println(b1);
		String s1 = new String("sixth");
		String s2 = new String("sick");
		String s3 = new String("six");
		String s4 = new String("The");
		String s5 = new String("the");
		String s6 = new String("sixty");
		ArrayList<Integer> i1 = b1.allIndexOf(s1);
		System.out.println("\t" + s1 + " found at " + i1);
		ArrayList<Integer> i2 = b1.allIndexOf(s2);
		System.out.println("\t" + s2 + " found at " + i2);
		ArrayList<Integer> i3 = b1.allIndexOf(s3);
		System.out.println("\t" + s3 + " found at " + i3);
		ArrayList<Integer> i4 = b1.allIndexOf(s4);
		System.out.println("\t" + s4 + " found at " + i4);
		ArrayList<Integer> i5 = b1.allIndexOf(s5);
		System.out.println("\t" + s5 + " found at " + i5);
		ArrayList<Integer> i6 = b1.allIndexOf(s6);
		System.out.println("\t" + s6 + " found at " + i6);
		System.out.println();

		MyStringBuilder2 b2 = new MyStringBuilder2("attention, the ship was out at sea, but now is shipwrecked on the seashore");
		System.out.println(b2);
		s1 = new String("ship");
		s2 = new String("sea");
		s3 = new String("at");
		s4 = new String("the");
		s5 = new String("sixty");
		s6 = new String("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~"); //testing for String longer than MSB
		i1 = b2.allIndexOf(s1);
		System.out.println("\t" + s1 + " found at " + i1);
		i2 = b2.allIndexOf(s2);
		System.out.println("\t" + s2 + " found at " + i2);
		i3 = b2.allIndexOf(s3);
		System.out.println("\t" + s3 + " found at " + i3);
		i4 = b2.allIndexOf(s4);
		System.out.println("\t" + s4 + " found at " + i4);
		i5 = b2.allIndexOf(s5);
		System.out.println("\t" + s5 + " found at " + i5);
		i6 = b2.allIndexOf(s6); //testing if search for a String longer than MSB
		// System.out.println("\tb2.length(): " + b2.length() + ",  s6.length(): " + s6.length()); //if you want to check that s6 is longer than b2, please uncomment this line and then run 
		System.out.println("\t" + s6 + " found at " + i6);
		System.out.println();

		MyStringBuilder2 b3 = new MyStringBuilder2("classless, peewee, ssss, ss, seaseashore, soundless, sound");
		System.out.println(b3);
		s1 = new String("sea");
		s2 = new String("ss");
		s3 = new String("s"); //testing a String with length of 1
		s4 = new String("ee");
		s5 = new String("sound");
		s6 = new String(""); //testing an empty string
		i1 = b3.allIndexOf(s1);
		System.out.println("\t" + s1 + " found at " + i1);
		i2 = b3.allIndexOf(s2);  
		System.out.println("\t" + s2 + " found at " + i2);
		i3 = b3.allIndexOf(s3);  //testing search for String with length of 1
		System.out.println("\t" + s3 + " found at " + i3);
		i4 = b3.allIndexOf(s4);
		System.out.println("\t" + s4 + " found at " + i4);
		i5 = b3.allIndexOf(s5);
		System.out.println("\t" + s5 + " found at " + i5);
		i6 = b3.allIndexOf(s6); //testing if search for an empty string
		System.out.println("\t" + s6 + " found at " + i6);
		System.out.println();

		
	}
}