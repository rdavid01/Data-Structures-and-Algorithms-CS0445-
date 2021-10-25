// David Reidenbaugh
// CS 0445 Fall 2020
/*
Assig2B is a testfile designed to test the run-times of append(c), delete(0,1), and insert(c)
in the StringBuilder, MyStringBuilder, and String classes.  
Append is tested by appending the character 'A' to the end of an object.  Delete is tested by
removing the first character from an object.  Insert is tested by inserting the character 'A' 
in the middle of an object.  
An argument, N, must be provided in the command line, which is used to tell the program to perform 
each operation N times.  For example, if the command line argument is 10000, then each 
class will append 10000 characters, delete 10000 characters from the front, and insert 10000 characters
into the middle.  Calls to System.nanoTime() are used to mark the begining and end of the N appends,
N deletes, and N inserts for each of the three different classes.
This information is then used to calculate the elapsed time and average time per 
operation, which are printed out at the end of each method.
*/


public class Assig2B 
{
	public static void main(String [] args)
	{
		int n = 0;
		// checking that an argument given on command line
		if (args.length < 1)
		{
			System.out.println("\nYou must enter a number on cmd line.\n");
			System.exit(0);
		}
		// makes sure that the argument can be converted to an int
		try{
			n = Integer.parseInt(args[0]);  
		}
		catch(NumberFormatException e){
			System.out.println("\n You must enter an integer on cmd line. \n");
			System.exit(0);
		}

		//testing predefined StringBuilder
		StringBuilder predefinedSB = new StringBuilder();
		testAppend(predefinedSB, n);
		testDelete(predefinedSB, n);
		testInsert(predefinedSB, n);
		//testing MyStringBuilder
		MyStringBuilder mySB = new MyStringBuilder();
		testAppend(mySB, n);
		testDelete(mySB, n);
		testInsert(mySB, n);
		//testing String
		String string = "";
		string = testAppend(string, n);
		string = testDelete(string, n);
		string = testInsert(string, n);

		System.out.println("\n--------------------------------------------");
		System.out.println("EXTRA CREDIT TEST CASES BELOW\n");
		//extra credit testing delete character at end
		testDeleteCharAt(predefinedSB, n);
		testDeleteCharAt(mySB, n);
		string = testDeleteCharAt(string, n);
		//extra credit testing insert at front
		testInsertAtFront(predefinedSB, n);
		testInsertAtFront(mySB, n);
		string = testInsertAtFront(string, n);

	} //end main

	//methods to test Predefined Stringbuilder
	static void testAppend(StringBuilder sb, int n){
		System.out.println("Testing Append:");
		System.out.println("\tPredefined StringBuilder:");

		long start = System.nanoTime();
		for(int i=0; i < n; i++){  
			sb.append('A');
		}
		long stop = System.nanoTime();

		long elapsed = stop - start;
		double average = elapsed/ (double) n;
		System.out.println("\t\tTotal time: " + elapsed + " ns for " + n + " appends");
		System.out.println("\t\tTime per append: " + average + " ns");
	}

	static void testDelete(StringBuilder sb, int n){
		System.out.println("Testing delete(0,1):");
		System.out.println("\tPredefined StringBuilder:");

		long start = System.nanoTime();
		for(int i=0; i < n; i++){  
			sb.delete(0,1);
		}
		long stop = System.nanoTime();

		long elapsed = stop - start;
		double average = elapsed/ (double) n;
		System.out.println("\t\tTotal time: " + elapsed + " ns for " + n + " deletes");
		System.out.println("\t\tTime per delete: " + average + " ns");
	}

	static void testInsert(StringBuilder sb, int n){ 
		System.out.println("Testing Insert:");
		System.out.println("\tPredefined StringBuilder:");
		int middle = sb.length()/2;

		long start = System.nanoTime();
		for(int i=0; i < n; i++){  
			sb.insert(middle, 'A');
			middle = sb.length()/2;
		}
		long stop = System.nanoTime();

		long elapsed = stop - start;
		double average = elapsed/ (double) n;
		System.out.println("\t\tTotal time: " + elapsed + " ns for " + n + " inserts");
		System.out.println("\t\tTime per insert: " + average + " ns");
	}



	//methods to test MyStringBuilder
	static void testAppend(MyStringBuilder sb, int n){
		System.out.println("Testing Append:");
		System.out.println("\tMyStringBuilder:");

		System.out.println("sb: " + sb);

		long start = System.nanoTime();
		for(int i=0; i < n; i++){  
			sb.append('A');
		}
		long stop = System.nanoTime();

		long elapsed = stop - start;
		double average = elapsed/ (double) n;
		System.out.println("\t\tTotal time: " + elapsed + " ns for " + n + " appends");
		System.out.println("\t\tTime per append: " + average + " ns");
	}

	static void testDelete(MyStringBuilder sb, int n){
		System.out.println("Testing delete(0,1):");
		System.out.println("\tMyStringBuilder:");

		long start = System.nanoTime();
		for(int i=0; i < n; i++){  
			sb.delete(0,1);
		}
		long stop = System.nanoTime();

		long elapsed = stop - start;
		double average = elapsed/ (double) n;
		System.out.println("\t\tTotal time: " + elapsed + " ns for " + n + " deletes");
		System.out.println("\t\tTime per delete: " + average + " ns");
	}

	static void testInsert(MyStringBuilder sb, int n){  
		System.out.println("Testing Insert:");
		System.out.println("\tMyStringBuilder:");
		int middle = sb.length()/2;

		long start = System.nanoTime();
		for(int i=0; i < n; i++){  
			sb.insert(middle, 'A');
			middle = sb.length()/2;

		}
		long stop = System.nanoTime();

		long elapsed = stop - start;
		double average = elapsed/ (double) n;
		System.out.println("\t\tTotal time: " + elapsed + " ns for " + n + " inserts");
		System.out.println("\t\tTime per insert: " + average + " ns");
	}


	//methods to test String
	static String testAppend(String s, int n){
		System.out.println("Testing Append:");
		System.out.println("\tString:");

		long start = System.nanoTime();
		for(int i=0; i < n; i++){  
			s += 'A';  
		}
		long stop = System.nanoTime();

		long elapsed = stop - start;
		double average = elapsed/ (double) n;
		System.out.println("\t\tTotal time: " + elapsed + " ns for " + n + " append");
		System.out.println("\t\tTime per append: " + average + " ns");

		return s;
	}

	static String testDelete(String s, int n){
		System.out.println("Testing delete(0,1):");
		System.out.println("\tString:");

		long start = System.nanoTime();
		for(int i=0; i < n; i++){  
			s = s.substring(1); 
		}
		long stop = System.nanoTime();

		long elapsed = stop - start;
		double average = elapsed/ (double) n;
		System.out.println("\t\tTotal time: " + elapsed + " ns for " + n + " delete");
		System.out.println("\t\tTime per delete: " + average + " ns");

		return s;
	}

	static String testInsert(String s, int n){  
		System.out.println("Testing Insert:");
		System.out.println("\tString:");
		int middle = s.length()/2;

		long start = System.nanoTime();
		for(int i=0; i < n; i++){     
			String beforeInsert = s.substring(0, middle);
			String afterInsert = s.substring(middle);
			s = beforeInsert + 'A' + afterInsert;
			middle = s.length()/2;
		}
		long stop = System.nanoTime();

		long elapsed = stop - start;
		double average = elapsed/ (double) n;
		System.out.println("\t\tTotal time: " + elapsed + " ns for " + n + " insert");
		System.out.println("\t\tTime per insert: " + average + " ns");

		return s;
	}

	//--------------------------------------------------------------------------------------------------------
	// Additional test cases for bonus points below

	//tests for deleteCharAt(length()-1)
	static void testDeleteCharAt(StringBuilder sb, int n){
		System.out.println("Testing deleteCharAt(length()-1):");
		System.out.println("\tPredefined StringBuilder:");

		long start = System.nanoTime();
		for(int i=0; i < n; i++){  
			sb.deleteCharAt(sb.length()-1);
		}
		long stop = System.nanoTime();

		long elapsed = stop - start;
		double average = elapsed/ (double) n;
		System.out.println("\t\tTotal time: " + elapsed + " ns for " + n + " deletes at length()-1");
		System.out.println("\t\tTime per delete at length()-1: " + average + " ns");
	}

		static void testDeleteCharAt(MyStringBuilder sb, int n){
		System.out.println("Testing deleteCharAt(length()-1):");
		System.out.println("\tMyStringBuilder:");

		long start = System.nanoTime();
		for(int i=0; i < n; i++){  
			sb.deleteCharAt(sb.length()-1);
		}
		long stop = System.nanoTime();

		long elapsed = stop - start;
		double average = elapsed/ (double) n;
		System.out.println("\t\tTotal time: " + elapsed + " ns for " + n + " deletes at length()-1");
		System.out.println("\t\tTime per delete at length()-1: " + average + " ns");
	}
	static String testDeleteCharAt(String s, int n){
		System.out.println("Testing delete(length()-1):");
		System.out.println("\tString:");

		long start = System.nanoTime();
		for(int i=0; i < n; i++){  
			s = s.substring(0, s.length()-1); 
		}
		long stop = System.nanoTime();

		long elapsed = stop - start;
		double average = elapsed/ (double) n;
		System.out.println("\t\tTotal time: " + elapsed + " ns for " + n + " deletes at length()-1");
		System.out.println("\t\tTime per delete at length()-1: " + average + " ns");

		return s;
	}


	//testing insert at front
	static void testInsertAtFront(StringBuilder sb, int n){ 
		System.out.println("Testing Insert at front:");
		System.out.println("\tPredefined StringBuilder:");

		long start = System.nanoTime();
		for(int i=0; i < n; i++){  
			sb.insert(0, 'A');
		}
		long stop = System.nanoTime();

		long elapsed = stop - start;
		double average = elapsed/ (double) n;
		System.out.println("\t\tTotal time: " + elapsed + " ns for " + n + " inserts at front");
		System.out.println("\t\tTime per insert at front: " + average + " ns");
	}

	static void testInsertAtFront(MyStringBuilder sb, int n){  
		System.out.println("Testing Insert at front:");
		System.out.println("\tMyStringBuilder:");

		long start = System.nanoTime();
		for(int i=0; i < n; i++){  
			sb.insert(0, 'A');

		}
		long stop = System.nanoTime();

		long elapsed = stop - start;
		double average = elapsed/ (double) n;
		System.out.println("\t\tTotal time: " + elapsed + " ns for " + n + " inserts at front");
		System.out.println("\t\tTime per insert at front: " + average + " ns");
	}

	static String testInsertAtFront(String s, int n){  
		System.out.println("Testing Insert at front:");
		System.out.println("\tString:");

		long start = System.nanoTime();
		for(int i=0; i < n; i++){     
			s = 'A' + s;
		}
		long stop = System.nanoTime();

		long elapsed = stop - start;
		double average = elapsed/ (double) n;
		System.out.println("\t\tTotal time: " + elapsed + " ns for " + n + " insert");
		System.out.println("\t\tTime per insert: " + average + " ns");

		return s;
	}

}