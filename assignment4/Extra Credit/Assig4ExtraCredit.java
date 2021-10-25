// David Reidenbaugh
// CS 0445 Fall 2020
/*
NOTE: my vairable is named minSize rather than MIN_SIZE

Assig4A is a file that tests the runtimes shellSort algorithms with different gap sequences.
The gap sequences used are n/2, Ciura's sequence, Segewick Incerpi, and Tokuda sequence.
This file has two modes.  One mode writes the results to a file.  the other is a trace output mode that
prints results to the command line.  Trace output mode runs if the array size is <= 20.
This program will ask the user for some inputs (array size, # of trials, and (if not in traceOutputMode) output file name).
If it writes to a file.  The program will provide information about the average run time per trial.  There will be 36 differet
outputs showing the different average run times for the minSize(5,50,150), data set up(random,sorted,reverse), and algorithm(simple quick, med-3, randomPivot, mergeSort).
If run in trace output mode, the program will print to the command data about the algorithm used, array size, minSize, and data set up,
as well as the array before it is sorted and after it is sorted for each trial
*/
import java.util.*; 
import java.io.*;

public class Assig4ExtraCredit{
	public static int[] ciuraGapSequence = new int[]{1750, 701, 301, 132, 57, 23, 10, 4, 1};
	public static int[] sedgewickIncerpi = new int[]{2085837936, 852913488, 343669872, 114556624, 49095696, 21479367, 8382192, 3402672, 1391376, 463792, 198768, 86961, 33936, 13776, 4592, 1968, 861, 336, 112, 48, 21, 7, 3, 1};
	public static int[] tokudaSequence = new int[]{100759940, 44782196, 19903198, 8845866, 3931496, 1747331, 776591, 345152, 153401, 68178, 30301, 13467, 5985, 2660, 1182, 525, 233, 103, 46, 20, 9, 4, 1};

	private static Random R = new Random();
	private static Integer [] randomArray;   
	private static Integer [] sortedArray;
	private static Integer [] reverseArray;

	private static int arraySizeInput; //receives input from scanner arraySize
	private static int numTrials;      //receives input from scanner trials

	private static String dataSetUp;

	private static boolean traceOutputMode;

	private static File outputFile;
	private static FileWriter myWriter;


	public static void main(String [] args){
		Scanner arraySize = new Scanner(System.in);  //maybe switch arraySize and arraySizeInput
		System.out.print("Enter array size: ");
		arraySizeInput = arraySize.nextInt();

		//set traceOutputMode
		if(arraySizeInput <= 20){
			traceOutputMode = true;
		}
		else{
			traceOutputMode = false;
		}

		Scanner trials = new Scanner(System.in);
		System.out.print("Enter number of trials: ");
		numTrials = trials.nextInt();

		if(!traceOutputMode){
			Scanner fileName = new Scanner(System.in); //should this run if in trace mode??
			System.out.print("Output file name: ");
			try{                                       //should i try, catch or throw ioexception
				outputFile = new File(fileName.nextLine());
				myWriter = new FileWriter(outputFile);
			}
			catch(Exception e){
				System.out.println(e);   //System.err.println
			}
		}

		//setting arrays to correct size
		randomArray = new Integer[arraySizeInput];
		sortedArray = new Integer[arraySizeInput];
		reverseArray = new Integer[arraySizeInput];

		//can manually add items to ciura array until filled to correct lenght

		//fill arrays with data
		// fillArrayRandom(randomArray);  // make everything in testSort in one big loop, then change var that hold the timing data
		fillArraySortedE(sortedArray); 
		fillArrayReverseSortedE(reverseArray);  

		Assig4ExtraCredit test = new Assig4ExtraCredit();

		//close the file if has been written to
		if(!traceOutputMode){ 
			try{
				myWriter.close();
			}
			catch(Exception e){
				System.out.println(e);
			}
		}

	}//end main

	public Assig4ExtraCredit(){
		
		dataSetUp = "Random";
		testSortE(randomArray);

		dataSetUp = "Sorted";
		testSortE(sortedArray);

		dataSetUp = "Reverse";
		testSortE(reverseArray);
	}

	public void testSortE(Integer [] storeA){
		Integer [] A1 = new Integer[storeA.length], A2 = new Integer[storeA.length], A3 = new Integer[storeA.length], A4 = new Integer[storeA.length];

		long allShellHalvedTimes = 0;
		double avgShellHalvedTime = 0;

		long allCiuraTimes = 0;
		double avgCiuraTime = 0;

		long allSedgewickTimes = 0;
		double avgSedgewickTime = 0;

		long allTokudaTimes = 0;
		double avgTokudaTime = 0;

		long start;
		long finish;
		long delta;


		//this if has no functionality other than to provide a visual separation to the different tests
		// added so that can see separation between different sort tests
		if(traceOutputMode){ 
			System.out.println("\n---------------------- TESTING NEW DATA SETUP: " + dataSetUp + " -------------------------"); // print to command line
		}
		else{
			try{
				myWriter.write("\n---------------------- TESTING NEW DATA SETUP: " + dataSetUp + " -------------------------\n"); // write to file
			}
			catch(Exception e){
				System.out.println(e);
			}
		} //end if

		
		for(int i=0; i < numTrials; i++){

			fillArrayRandomE(randomArray);


			//this if has no functionality other than to provide a visual separation to the different trials
			// added so that can see separation between different trials
			if(traceOutputMode){ 
				System.out.println("\n+++++++++++++++++++++++ TESTING NEW TRIAL: " + (i+1) + " ++++++++++++++++++++++++++++"); // print to command line
			} //end if


			//shellSort	n/2			
			copyArrayE(A1, storeA);

			start = System.nanoTime();
			shellSortGapHalved(A1, A1.length); //doing the actual sort
			finish = System.nanoTime();

			delta = finish - start;
			allShellHalvedTimes += delta; 

			//Ciura
			copyArrayE(A2, storeA);

			start = System.nanoTime();
			shellSortCiura(A2, ciuraGapSequence, A2.length);
			finish = System.nanoTime();

			delta = finish - start;
			allCiuraTimes += delta;

			//Sedgewick
			copyArrayE(A3, storeA);

			start = System.nanoTime();
			shellSortSedgewick(A3, sedgewickIncerpi, A3.length);
			finish = System.nanoTime();

			delta = finish - start;
			allSedgewickTimes += delta;

			//Tokuda
			copyArrayE(A4, storeA);

			start = System.nanoTime();
			shellSortTokuda(A4, tokudaSequence, A4.length);
			finish = System.nanoTime();

			delta = finish - start;
			allTokudaTimes += delta;	

			//print out trace output mode
			if(traceOutputMode){
				//shellSort trace
				System.out.println();
				System.out.println("Algorithm: Simple shellSort");
				System.out.println("Array Size: " + arraySizeInput);
				System.out.println("Gap Sequence: " + "n/2");
				System.out.println("Data Setup: " + dataSetUp);
				System.out.println("Before Simple shellSort");
				showArrayE(storeA); //may have to change if dont have same random data every trial
				System.out.println("After Simple shellSort");
				showArrayE(A1);

				//ciura trace
				System.out.println();
				System.out.println("Algorithm: Ciura shellSort");
				System.out.println("Array Size: " + arraySizeInput);
				System.out.println("Gap Sequence: " + "Ciura's Sequence");
				System.out.println("Data Setup: " + dataSetUp);
				System.out.println("Before Ciura sort");
				showArrayE(storeA); //may have to change if dont have same random data every trial
				System.out.println("After Ciura sort");
				showArrayE(A2);

				//sedgewick trace
				System.out.println();
				System.out.println("Algorithm: Sedgewick shellSort");
				System.out.println("Array Size: " + arraySizeInput);
				System.out.println("Gap Sequence: " + "Sedgewick Incerpi");
				System.out.println("Data Setup: " + dataSetUp);
				System.out.println("Before Sedgewick sort");
				showArrayE(storeA); //may have to change if dont have same random data every trial
				System.out.println("After Sedgewick sort");
				showArrayE(A3);

				//tokuda trace
				System.out.println();
				System.out.println("Algorithm: Tokuda shellSort");
				System.out.println("Array Size: " + arraySizeInput);
				System.out.println("Gap Sequence: " + "Tokuda Sequence");
				System.out.println("Data Setup: " + dataSetUp);
				System.out.println("Before Tokuda sort");
				showArrayE(storeA); //may have to change if dont have same random data every trial
				System.out.println("After Tokuda sort");
				showArrayE(A4);
			}
			
		}//end for


		//printing out sort data (if not in trace output mode)
		if(!traceOutputMode){
			try{
				// printing out shellSort info
				avgShellHalvedTime = allShellHalvedTimes/(1000000000 * (double) numTrials);

				myWriter.write("\n");
				myWriter.write("\nAlgorithm: shellSort");
				myWriter.write("\nArray Size: " + arraySizeInput);
				myWriter.write("\nGap Sequence: " + "n/2");
				myWriter.write("\nData Setup: " + dataSetUp);
				myWriter.write("\nNumber of trials: " + numTrials); //numer of trials
				myWriter.write("\nAverage Time per trial: " + avgShellHalvedTime + " sec."); // time per trial
				
				// printing out ciura info
				avgCiuraTime = allCiuraTimes/(1000000000 * (double) numTrials);

				myWriter.write("\n");
				myWriter.write("\nAlgorithm: shellSort");
				myWriter.write("\nArray Size: " + arraySizeInput);
				myWriter.write("\nGap Sequence: " + "Ciura's Sequence");
				myWriter.write("\nData Setup: " + dataSetUp);
				myWriter.write("\nNumber of trials: " + numTrials); //numer of trials
				myWriter.write("\nAverage Time per trial: " + avgCiuraTime + " sec."); // time per trial
				
				// printing out sedgewick info
				avgSedgewickTime = allSedgewickTimes/(1000000000 * (double) numTrials);

				myWriter.write("\n");
				myWriter.write("\nAlgorithm: shellSort");
				myWriter.write("\nArray Size: " + arraySizeInput);
				myWriter.write("\nGap Sequence: " + "Sedgewick Incerpi");
				myWriter.write("\nData Setup: " + dataSetUp);
				myWriter.write("\nNumber of trials: " + numTrials); //numer of trials
				myWriter.write("\nAverage Time per trial: " + avgSedgewickTime + " sec."); // time per trial
				
				// printing out sedgewick info
				avgTokudaTime = allTokudaTimes/(1000000000 * (double) numTrials);

				myWriter.write("\n");
				myWriter.write("\nAlgorithm: shellSort");
				myWriter.write("\nArray Size: " + arraySizeInput);
				myWriter.write("\nGap Sequence: " + "Tokuda Sequence");
				myWriter.write("\nData Setup: " + dataSetUp);
				myWriter.write("\nNumber of trials: " + numTrials); //numer of trials
				myWriter.write("\nAverage Time per trial: " + avgTokudaTime + " sec."); // time per trial
				
			}//end try
			catch(Exception e){
				System.out.println(e);
			}//end catch

		}//end if(!traceOutput)

	}//end TestSort


	//operations on arrays----------------------------------------------------------------

	// Fill array with random data
	public static void fillArrayRandomE(Integer [] storeA)
	{	
		for (int i = 0; i < storeA.length; i++)
		{
			storeA[i] = Integer.valueOf(R.nextInt()); //filling indeces with random ints
		}
	}

	//fill array with sorted data
	public static void fillArraySortedE(Integer [] storeA){
		for(int i = 0; i < storeA.length; i++){
			storeA[i] = i+1;
		}
	}

	//fill array with reverse sorted data
	public static void fillArrayReverseSortedE(Integer [] storeA){
		for(int index = 0, i = storeA.length; index < storeA.length; index++, i--){
			storeA[index] = i;
		}
	}


	public void copyArrayE(Integer [] newA, Integer [] storeA){
		for (int i = 0; i < storeA.length; i++)
			newA[i] = storeA[i];
	}

	public void showArrayE(Integer [] storeA)
	{
		for (int i = 0; i < storeA.length; i++)
		{
			System.out.print(storeA[i] + " ");
		}
		System.out.println();
	}


	//sorting algorithms -----------------------------------------------------------------------
	//gap seqeuence n/2
	public static <T extends Comparable<? super T>>
		   void shellSortGapHalved(T[] array, int length)
	{
		shellSortGapHalved(array, 0, length-1);
	}

	public static <T extends Comparable<? super T>>
		   void shellSortGapHalved(T[] array, int first, int last)
	{
		int logSize = (last - first) + 1;
		int space = logSize/2;

		while(space > 0){
			for(int begin = first; begin < first+space; begin++){
				incrementalInsertionSort(array, begin, last, space);
			}

			space = space/2;  //reduce gap
		}
	}//end shellSortGapHalved


	//ciura gap sequence
	public static <T extends Comparable<? super T>>
		   void shellSortCiura(T[] array, int[] ciura, int length)
	{
		shellSortCiura(array, ciura, 0, length-1);
	}

	public static <T extends Comparable<? super T>>
		   void shellSortCiura(T[] array, int[] ciura, int first, int last)
	{
		int logSize = (last - first) + 1;
		// int space = logSize/2;

		for(int gap : ciura){
			
			if(gap < logSize){
				for(int begin = first; begin < first+gap; begin++){
					incrementalInsertionSort(array, begin, last, gap);
				}
			}

			// space = space/2;  //reduce gap
		}
	}//end shellSortCiura

	//sedgewick sequence
	public static <T extends Comparable<? super T>>
		   void shellSortSedgewick(T[] array, int[] sedgewick, int length)
	{
		shellSortCiura(array, sedgewick, 0, length-1);
	}

	public static <T extends Comparable<? super T>>
		   void shellSortSedgewick(T[] array, int[] sedgewick, int first, int last)
	{
		int logSize = (last - first) + 1;
		// int space = logSize/2;

		for(int gap : sedgewick){
			
			if(gap < logSize){
				for(int begin = first; begin < first+gap; begin++){
					incrementalInsertionSort(array, begin, last, gap);
				}
			}

			// space = space/2;  //reduce gap
		}
	}//end shellSort

	//tokuda sequence
	public static <T extends Comparable<? super T>>
		   void shellSortTokuda(T[] array, int[] tokuda, int length)
	{
		shellSortCiura(array, tokuda, 0, length-1);
	}

	public static <T extends Comparable<? super T>>
		   void shellSortTokuda(T[] array, int[] tokuda, int first, int last)
	{
		int logSize = (last - first) + 1;
		// int space = logSize/2;

		for(int gap : tokuda){
			
			if(gap < logSize){
				for(int begin = first; begin < first+gap; begin++){
					incrementalInsertionSort(array, begin, last, gap);
				}
			}

			// space = space/2;  //reduce gap
		}
	}//end shellSort



	public static <T extends Comparable<? super T>>
		   void incrementalInsertionSort(T[] array, int first, int last, int space)
	{	
		int unsorted;
		int index;


		for(unsorted = first+space; unsorted <= last; unsorted+=space){
			T nextToInsert = array[unsorted];
			index = unsorted - space;

			while((index >= first) && nextToInsert.compareTo(array[index]) < 0){
				array[index + space] = array[index];
				index -= space;
			}

			array[index + space] = nextToInsert;
		}
	}//end incrementalInsertionSort


	// public static <T extends Comparable<? super T>>
	// 	   void incrementalInsertionSort(T[] a, int first, int last, int space)
	// {	
	// 	int unsorted, index;

	// 	System.out.println("INSIDE INCREMENTALINSERTIONSORT");

	// 	for(unsorted = first+space; unsorted <= last; unsorted= unsorted+space){
	// 		System.out.println("INSIDE FOR LOOP");
	// 		T nextToInsert = a[unsorted];
	// 		index = unsorted - space;
	// 		System.out.println("index: " + index + ",  first: " + first + ",  last: " + last +",  unsorted: " + unsorted + ",  space: " + space);

	// 		while((index >= first) && nextToInsert.compareTo(a[index]) < 0){
	// 			System.out.println("INSIDE WHILE LOOP");
	// 			a[index + space] = a[index];
	// 			index = index - space;
	// 			System.out.println("index: " + index + ",  first: " + first + ",  unsorted: " + unsorted + ",  space: " + space);
	// 		}

	// 		a[index + space] = nextToInsert;
	// 		System.out.println("END INCREMENTALINSERTIONSORT");
	// 	}
	// }//end incrementalInsertionSort


}