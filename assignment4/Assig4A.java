// David Reidenbaugh
// CS 0445 Fall 2020
/*
NOTE: my vairable is named minSize rather than MIN_SIZE

Assig4A is a file that tests the runtimes of the algorithms in SortAlgoritms.java.
simple Quicksort is not tested if array size is > 100000.
This file has two modes.  One mode writes the results to a file.  the other is a trace output mode that
prints results to the command line.  Trace output mode runs if the array size is <= 20.
This program will ask the user for some inputs (array size, # of trials, and (if not in traceOutputMode) output file name).
If it writes to a file.  The program will provide information about the average run time per trial.  There will be 36 or 27 differet
outputs (depnding on array size) showing the different average run times for the minSize(5,50,150), data set up(random,sorted,reverse), and algorithm(simple quick, med-3, randomPivot, mergeSort).
If run in trace output mode, the program will print to the command data about the algorithm used, array size, minSize, and data set up,
as well as the array before it is sorted and after it is sorted for each trial
*/
import java.util.*; 
import java.io.*;

public class Assig4A{
	private static int[] minSizeArray = new int[]{5, 50, 150};  
	private static Random R = new Random();
	private static Integer [] randomArray;   //why Integer instead of int???
	private static Integer [] sortedArray;
	private static Integer [] reverseArray;

	private static int arraySizeInput; //receives input from scanner arraySize
	private static int numTrials;      //receives input from scanner trials

	private static int minSize; 
	private static String dataSetUp;

	private static boolean traceOutputMode; //tells wether program is run in trace output mode

	private static File outputFile;
	private static FileWriter myWriter;


	public static void main(String [] args){
		//ask user for array size
		Scanner arraySize = new Scanner(System.in);  
		System.out.print("Enter array size: ");
		arraySizeInput = arraySize.nextInt();

		//set traceOutputMode
		if(arraySizeInput <= 20){
			traceOutputMode = true;
		}
		else{
			traceOutputMode = false;
		}

		//ask user for # of trials
		Scanner trials = new Scanner(System.in);
		System.out.print("Enter number of trials: ");
		numTrials = trials.nextInt();

		if(!traceOutputMode){
			Scanner fileName = new Scanner(System.in); //only ask for output file name if not in traceOutputMode
			System.out.print("Output file name: ");
			try{                                       
				outputFile = new File(fileName.nextLine());
				myWriter = new FileWriter(outputFile);
			}
			catch(Exception e){
				System.out.println(e);   
			}
		}

		//setting arrays to correct size
		randomArray = new Integer[arraySizeInput];
		sortedArray = new Integer[arraySizeInput];
		reverseArray = new Integer[arraySizeInput];

		//fill arrays with data
		//random array is filled later so that unique data is used for each trial
		fillArraySorted(sortedArray); 
		fillArrayReverseSorted(reverseArray);  

		//loop that runs test for each minSize
		for(int size : minSizeArray){  
			minSize = SortAlgorithms.setMinSize(size); 

			//this if has no functionality other than to provide a visual separation to the different tests
			//added so that can see separation between different minSize tests
			if(traceOutputMode){ 
				System.out.println("\n=================================== TESTING NEW MINSIZE: " + minSize + " ======================================="); // print to command line
			}
			else{
				try{
					myWriter.write("\n=================================== TESTING NEW MINSIZE: " + minSize + " =======================================\n"); // write to file
				}
				catch(Exception e){
					System.out.println(e);
				}
			}//end if/else


			Assig4A test = new Assig4A();

		}//end for

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

	public Assig4A(){
		
		dataSetUp = "Random";
		testSort(randomArray);

		dataSetUp = "Sorted";
		testSort(sortedArray);

		dataSetUp = "Reverse";
		testSort(reverseArray);
	}


	// Fill array with random data
	public static void fillArrayRandom(Integer [] storeA)
	{	
		for (int i = 0; i < storeA.length; i++)
		{
			storeA[i] = Integer.valueOf(R.nextInt()); //filling indeces with random ints
		}
	}

	//fill array with sorted data
	public static void fillArraySorted(Integer [] storeA){
		for(int i = 0; i < storeA.length; i++){
			storeA[i] = i+1;
		}
	}

	//fill array with reverse sorted data
	public static void fillArrayReverseSorted(Integer [] storeA){
		for(int index = 0, i = storeA.length; index < storeA.length; index++, i--){
			storeA[index] = i;
		}
	}

	//copy array
	public void copyArray(Integer [] newA, Integer [] storeA){
		for (int i = 0; i < storeA.length; i++)
			newA[i] = storeA[i];
	}

	//show array
	public void showArray(Integer [] storeA)
	{
		for (int i = 0; i < storeA.length; i++)
		{
			System.out.print(storeA[i] + " ");
		}
		System.out.println();
	}


	//testSort is where the algorithms are timed and results are printed/written
	public void testSort(Integer [] storeA){
		Integer [] A1 = new Integer[storeA.length], A2 = new Integer[storeA.length], A3 = new Integer[storeA.length], A4 = new Integer[storeA.length];

		//variables to store info regarding the timing of algorithms

		long allQuickTimes = 0; //total time of all simple quicksort trials
		double avgQuickTime = 0;  //average time of all quicksorts

		long allMed3Times = 0;   //toatal time of all med-3 trials
		double avgMed3Time = 0;  //avg time of all med-3

		long allRandomTimes = 0;   //total time of all random pivot trials
		double avgRandomTime = 0;  //avg time of all med-3

		long allMergeTimes = 0;   //total time of all mergeSort trials
		double avgMergeTime = 0;  //avg time of all mergeSorts

		long start;
		long finish;
		long delta; //difference between finish and start


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

		//runs i # of trials based on user input
		for(int i=0; i < numTrials; i++){

			//results are filled with new random data each trial
			fillArrayRandom(randomArray);


			//this if has no functionality other than to provide a visual separation to the different trials
			// added so that can see separation between different trials
			if(traceOutputMode){ 
				System.out.println("\n+++++++++++++++++++++++ TESTING NEW TRIAL: " + (i+1) + " ++++++++++++++++++++++++++++"); // print to command line
			} //end if


			//Simple QuickSort
			if(arraySizeInput <= 100000){ //flag, do not test simple Quicksort if arraySize > 100,000
				
					copyArray(A1, storeA);

					start = System.nanoTime();
					SortAlgorithms.quickSort(A1, A1.length); //doing the actual sort
					finish = System.nanoTime();

					delta = finish - start;
					allQuickTimes += delta; 


			}

			// Med-of-3
			copyArray(A2, storeA);

			start = System.nanoTime();
			SortAlgorithms.medianThreeQuickSort(A2, A2.length); //doing the actual sort
			finish = System.nanoTime();

			delta = finish - start;
			allMed3Times += delta;


			// Random Pivot
			copyArray(A3, storeA);

			start = System.nanoTime();
			SortAlgorithms.randomPivotQuickSort(A3, A3.length); //doing the actual sort
			finish = System.nanoTime();

			delta = finish - start;
			allRandomTimes += delta;

			// MergeSort
			copyArray(A4, storeA);

			start = System.nanoTime();
			SortAlgorithms.mergeSort(A4, A4.length); //doing the actual sort
			finish = System.nanoTime();

			delta = finish - start;
			allMergeTimes += delta;

			//print out trace output mode
			if(traceOutputMode){
				//simple QuickSort trace
				System.out.println();
				System.out.println("Algorithm: Simple QuickSort");
				System.out.println("Array Size: " + arraySizeInput);
				System.out.println("Base Case Less Than: " + minSize);
				System.out.println("Data Setup: " + dataSetUp);
				System.out.println("Before simple Quicksort");
				showArray(storeA); //may have to change if dont have same random data every trial
				System.out.println("After simple Quicksort");
				showArray(A1);

				//med-of-3 trace
				System.out.println();
				System.out.println("Algorithm: Med-of-3 QuickSort");
				System.out.println("Array Size: " + arraySizeInput);
				System.out.println("Base Case Less Than: " + minSize);
				System.out.println("Data Setup: " + dataSetUp);
				System.out.println("Before Med-of-3 Quicksort");
				showArray(storeA);
				System.out.println("After Med-of-3 Quicksort");
				showArray(A2);

				//random pivot trace
				System.out.println();
				System.out.println("Algorithm: Random Pivot QuickSort");
				System.out.println("Array Size: " + arraySizeInput);
				System.out.println("Base Case Less Than: " + minSize);
				System.out.println("Data Setup: " + dataSetUp);
				System.out.println("Before random pivot Quicksort");
				showArray(storeA);
				System.out.println("After random pivot Quicksort");
				showArray(A3);

				//mergeSort trace
				System.out.println(); 
				System.out.println("Algorithm: MergeSort");
				System.out.println("Array Size: " + arraySizeInput);
				System.out.println("Base Case Less Than: " + minSize);
				System.out.println("Data Setup: " + dataSetUp);
				System.out.println("Before mergeSort");
				showArray(storeA);
				System.out.println("After mergeSort");
				showArray(A4);
			}// end if
			
		}//end for


		//printing out sort data (if not in trace output mode)
		if(!traceOutputMode){
			try{
				
				// printing out simple quickSort info
				if(arraySizeInput <= 100000){
					avgQuickTime = allQuickTimes/(1000000000 * (double) numTrials); //calculating avg time in seconds

					myWriter.write("\n");
					myWriter.write("\nAlgorithm: Simple QuickSort");
					myWriter.write("\nArray Size: " + arraySizeInput);
					myWriter.write("\nBase Case Less Than: " + minSize);
					myWriter.write("\nData Setup: " + dataSetUp);
					myWriter.write("\nNumber of trials: " + numTrials); //numer of trials
					myWriter.write("\nAverage Time per trial: " + avgQuickTime + " sec."); // time per trial
				}

				//printing out med-of-3 info
				avgMed3Time = allMed3Times/(1000000000 * (double) numTrials);  //calculating avg time in seconds

				myWriter.write("\n");
				myWriter.write("\nAlgorithm: Med-of-3 QuickSort");
				myWriter.write("\nArray Size: " + arraySizeInput);
				myWriter.write("\nBase Case Less Than: " + minSize);
				myWriter.write("\nData Setup: " + dataSetUp);
				myWriter.write("\nNumber of trials: " + numTrials); //numer of trials
				myWriter.write("\nAverage Time per trial: " + avgMed3Time + " sec."); // time per trial


				//printing out random pivot data
				avgRandomTime = allRandomTimes/(1000000000 * (double) numTrials);  //calculating avg time in seconds

				myWriter.write("\n");
				myWriter.write("\nAlgorithm: Random Pivot QuickSort");
				myWriter.write("\nArray Size: " + arraySizeInput);
				myWriter.write("\nBase Case Less Than: " + minSize);
				myWriter.write("\nData Setup: " + dataSetUp);
				myWriter.write("\nNumber of trials: " + numTrials); //numer of trials
				myWriter.write("\nAverage Time per trial: " + avgRandomTime + " sec."); // time per trial

				//printing out mergeSort info
				avgMergeTime = allMergeTimes/(1000000000 * (double) numTrials);  //calculating avg time in seconds

				myWriter.write("\n"); 
				myWriter.write("\nAlgorithm: MergeSort");
				myWriter.write("\nArray Size: " + arraySizeInput);
				myWriter.write("\nBase Case Less Than: " + minSize);
				myWriter.write("\nData Setup: " + dataSetUp);
				myWriter.write("\nNumber of trials: " + numTrials); //numer of trials
				myWriter.write("\nAverage Time per trial: " + avgMergeTime + " sec.\n"); // time per trial

			}//end try
			catch(Exception e){
				System.out.println(e);
			}//end catch

		}//end if(!traceOutput)
	}//end TestSort

}


