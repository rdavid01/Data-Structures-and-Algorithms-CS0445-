// David Reidenbaugh
// CS 0445 Fall 2020
/*
NOTE: my vairable is named minSize rather than MIN_SIZE

SortAlgorithms is a file that contains the different sorting algorithms that will be used in Assig2B.java.
The algorithms in this file are simple quickSort, med-of-3 qucksort, randomPivot quickSort, and mergeSort.
InsertionSort is also included in this file, but it is not tested in Assig2B.
The variable minSize is responsible for determining when the algorithms should switch from performing a
quick or mergeSort to just insertinSort.  When a recursive call produces an array with size < minSize, the 
algorithm will do insertionSort.
minSize is set by the method setMinSize, which changes minSize between the values of 5, 50, and 150.
*/
import java.util.*;

public class SortAlgorithms{

	private static int minSize; 


	public static int setMinSize(int N){  
		minSize = N;
		return minSize;
	}

	private static void swap(Object [] array, int i, int j){
		Object temp = array[i];
		array[i] = array[j];
		array[j] = temp; 
	} 


 //------------------------------------------------------------------------------------------------------------------------------------------

	//simple QuickSort
	public static <T extends Comparable<? super T>>
		   void quickSort(T[] array, int length)
	{
		quickSort(array, 0, length-1);
	} // end quickSort

	public static <T extends Comparable<? super T>>
		   void quickSort(T[] array, int first, int last)
	{
		if (((last - first) + 1) < minSize){  // + 1 because has to be inclusive
			insertionSort(array, first, last);
	  	}
		else{
			int pivotIndex = partition(array, first, last);
	      
			// sort subarrays 
			quickSort(array, first, pivotIndex-1);
			quickSort(array, pivotIndex+1, last);
		} 
	}  // end quickSort

	//Partitions an array as part of quick sort into two subarrays, separated by a pivot
	//three segements, <= pivot, pivot, >= pivot
	private static <T extends Comparable<? super T>>
	        int partition(T[] array, int first, int last)
	{
		int pivotIndex = last;  // pivot is rightmost element
		T pivot = array[pivotIndex];


		int indexFromLeft = first; 
		int indexFromRight = last - 1; 

		boolean pivotSorted = false;
		while (!pivotSorted)
		{
			
			//pass over element if it is <= pivot
			//stop on element if it is >= pivot
			while (array[indexFromLeft].compareTo(pivot) < 0){
				indexFromLeft++;
			}

			//pass over element if it is >= pivot
			//stop on element if it is <= pivot
			while (array[indexFromRight].compareTo(pivot) > 0 && indexFromRight > first){
				indexFromRight--;
			}

			//  array[indexFromLeft] >= pivot and 
			//  array[indexFromRight] <= pivot.
			//	both indexes are stopped
			if (indexFromLeft < indexFromRight)
			{
				swap(array, indexFromLeft, indexFromRight);
				indexFromLeft++;
				indexFromRight--;
			}
			else{
				pivotSorted = true;
			}
		} 

		// place pivot between Smaller and Larger subarrays
		swap(array, pivotIndex, indexFromLeft);
		pivotIndex = indexFromLeft;

		return pivotIndex; 
	}  // end partition

	




 //------------------------------------------------------------------------------------------------------------------------------------------
	
	//median of Three
	public static <T extends Comparable<? super T>>
		   void medianThreeQuickSort(T[] array, int length)
	{
		medianThreeQuickSort(array, 0, length-1);
	} // end quickSort
	
	
	public static <T extends Comparable<? super T>>
	       void medianThreeQuickSort(T[] array, int first, int last)
	{
	  if (((last - first) + 1) < minSize){
	    insertionSort(array, first, last);
	  }
	  else
	  {
	    int pivotIndex = medianThreePartition(array, first, last);
	    
	    // sort subarrays 
	    medianThreeQuickSort(array, first, pivotIndex - 1);
	    medianThreeQuickSort(array, pivotIndex + 1, last);
	  } 
	} // end quickSort

	//choose 3 potential pivots (first index, mid index, last index)
	//sorts the 3 potential pivots
	//uses the median of the 3 potential pivots as the actual pivot
	//three segements, <= pivot, pivot, >= pivot
	private static <T extends Comparable<? super T>>
	        int medianThreePartition(T[] array, int first, int last)
	{
	  int mid = (first + last)/2;
	  sortFirstMiddleLast(array, first, mid, last);
	  
	
	  
	  // move pivot to next-to-last position in array
	  swap(array, mid, last - 1);
	  int pivotIndex = last - 1;
	  T pivot = array[pivotIndex];
	  
	  int indexFromLeft = first + 1; 
	  int indexFromRight = last - 2;

	  boolean pivotSorted = false;
	  while (!pivotSorted)
	  {

	    //pass over element if it is <= pivot
		//stop on element if it is >= pivot
	    while (array[indexFromLeft].compareTo(pivot) < 0){
	      indexFromLeft++;
	    }
	      
	    //pass over element if it is >= pivot
		//stop on element if it is <= pivot
	    while (array[indexFromRight].compareTo(pivot) > 0){
	      indexFromRight--;
	    }
	      
	    //  array[indexFromLeft] >= pivot and 
			//  array[indexFromRight] <= pivot.
			//	both indexes are stopped
	    if (indexFromLeft < indexFromRight)
	    {
	      swap(array, indexFromLeft, indexFromRight); 
	      indexFromLeft++;
	      indexFromRight--;
	    }
	    else{
	      pivotSorted = true;
	    }
	  } 
	  
	  // place pivot between Smaller and Larger subarrays
	  swap(array, pivotIndex, indexFromLeft);     
	  pivotIndex = indexFromLeft;
	  
	  return pivotIndex; 
	} // end partition

	
	//sorts the three potential pivots
	private static <T extends Comparable<? super T>>
	        void sortFirstMiddleLast(T[] a, int first, int mid, int last)
	{
	  order(a, first, mid); // make a[first] <= a[mid]
	  order(a, mid, last);  // make a[mid] <= a[last]
	  order(a, first, mid); // make a[first] <= a[mid]
	} 

	//orders the given array elements into ascending order
	private static <T extends Comparable<? super T>>
	        void order(T[] a, int i, int j)
	{
	  if (a[i].compareTo(a[j]) > 0)
	    swap(a, i, j);
	} // end order

 


//------------------------------------------------------------------------------------------------------------------------------------------
	
	//random pivot quicksort
	public static <T extends Comparable<? super T>>
  			void randomPivotQuickSort(T[] array, int n)
 	{
		randomPivotQuickSort(array, 0, n-1);
	} // end quickSort

	//choses a value form a random index to be the pivot
	public static <T extends Comparable<? super T>>
		   void randomPivotQuickSort(T[] array, int first, int last)
	{
		if (((last - first) + 1) < minSize){ 
			insertionSort(array, first, last);
	  	}
		else{ 
			// create the partition: Smaller | Pivot | Larger
			int pivotIndex = randomPivotPartition(array, first, last);
	      
			// sort subarrays Smaller and Larger
			randomPivotQuickSort(array, first, pivotIndex-1);
			randomPivotQuickSort(array, pivotIndex+1, last);
		}
	}  // end quickSort

	//Partitions an array as part of quick sort into two subarrays, separated by a pivot
	//three segements, <= pivot, pivot, >= pivot
	private static <T extends Comparable<? super T>>
	        int randomPivotPartition(T[] array, int first, int last)
	{

		Random randomPivot = new Random();   
		int pivotIndex = randomPivot.nextInt((last-first) + 1) + first;  // pick random number for pivot index
		T pivot = array[pivotIndex];

		swap(array, pivotIndex, last);
		pivotIndex = last;


		int indexFromLeft = first; 
		int indexFromRight = last - 1; 

		boolean pivotSorted = false;
		while (!pivotSorted)
		{
			
			while (array[indexFromLeft].compareTo(pivot) < 0){
				indexFromLeft++;
			}

			
			while (array[indexFromRight].compareTo(pivot) > 0 && indexFromRight > first){
				indexFromRight--;
			}

			

			if (indexFromLeft < indexFromRight)
			{
				swap(array, indexFromLeft, indexFromRight);
				indexFromLeft++;
				indexFromRight--;
			}
			else{
				pivotSorted = true;
			}
		}

		// place pivot between Smaller and Larger subarrays
		swap(array, pivotIndex, indexFromLeft);
		pivotIndex = indexFromLeft;

		return pivotIndex; 
	}  // end partition
  		   


 //------------------------------------------------------------------------------------------------------------------------------------------

	// MERGE SORT
	public static <T extends Comparable<? super T>>
	       void mergeSort(T[] array, int n)
	{
		mergeSort(array, 0, n - 1);
	} // end mergeSort

	public static <T extends Comparable<? super T>>
	       void mergeSort(T[] array, int first, int last)
	{
		T[] tempArray = (T[])new Comparable<?>[array.length];
 		mergeSort(array, tempArray, first, last);
	} // end mergeSort
	
	private static <T extends Comparable<? super T>>
	        void mergeSort(T[] array, T[] tempArray, int first, int last)
	{
		if (((last - first) + 1) < minSize){  
			insertionSort(array, first, last);
	  	}
	    else{  // sort each half         
	     	int mid = (first + last)/2;// index of midpoint
	    	mergeSort(array, tempArray, first, mid);  // sort left half array[first..mid]
	     	mergeSort(array, tempArray, mid + 1, last); // sort right half array[mid+1..last]

			if (array[mid].compareTo(array[mid + 1]) > 0)      // Question 2, Chapter 9
	     	 	merge(array, tempArray, first, mid, last); // merge the two halves
	   //	else skip merge step
	   }  
	}  // end mergeSort
	
	//merges the two sub arrays back together
	private static <T extends Comparable<? super T>> 
	        void merge(T[] array, T[] tempArray, int first, int mid, int last)
	{
		// Two adjacent subarrays are a[beginHalf1..endHalf1] and a[beginHalf2..endHalf2].
		int beginHalf1 = first;
		int endHalf1 = mid;
		int beginHalf2 = mid + 1;
		int endHalf2 = last;

		// while both subarrays are not empty, copy the
	   // smaller item into the temporary array
		int index = beginHalf1; // next available location in
								            // tempArray
		for (; (beginHalf1 <= endHalf1) && (beginHalf2 <= endHalf2); index++)
	   {  // Invariant: tempArray[beginHalf1..index-1] is in order
	   
	      if (array[beginHalf1].compareTo(array[beginHalf2]) <= 0)
	      {  
	      	tempArray[index] = array[beginHalf1];
	        beginHalf1++;
	      }
	      else
	      {  
	      	tempArray[index] = array[beginHalf2];
	        beginHalf2++;
	      }  // end if
	   }  // end for

	   // finish off the nonempty subarray

	   // finish off the first subarray, if necessary
	   for (; beginHalf1 <= endHalf1; beginHalf1++, index++)
	      // Invariant: tempArray[beginHalf1..index-1] is in order
	      tempArray[index] = array[beginHalf1];

	   // finish off the second subarray, if necessary
		for (; beginHalf2 <= endHalf2; beginHalf2++, index++)
	      // Invariant: tempa[beginHalf1..index-1] is in order
	      tempArray[index] = array[beginHalf2];
		
	   // copy the result back into the original array
	   for (index = first; index <= last; index++)
	      array[index] = tempArray[index];
	}  // end merge

  //------------------------------------------------------------------------------------------------------------------------------------------

  public static <T extends Comparable<? super T>> 
	       void insertionSort(T[] array, int first, int last)
	{
		// System.out.println("DOING INSERTIONSORT"); //remove before submit
		int unsorted, index;
		
		for (unsorted = first + 1; unsorted <= last; unsorted++)
		{   // Assertion: a[first] <= a[first + 1] <= ... <= a[unsorted - 1]
		
			T firstUnsorted = array[unsorted];
			
			insertInOrder(firstUnsorted, array, first, unsorted - 1);
		} // end for
	} // end insertionSort

  private static <T extends Comparable<? super T>> 
	        void insertInOrder(T element, T[] array, int begin, int end)
	{
		int index;
		
		for (index = end; (index >= begin) && (element.compareTo(array[index]) < 0); index--)
		{
			array[index + 1] = array[index]; // make room
		} // end for
		
		// Assertion: a[index + 1] is available
		array[index + 1] = element;  // insert
	} // end insertInOrder
}


