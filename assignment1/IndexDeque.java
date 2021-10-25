/*
David Reidenbaugh
CS 445 Fall 2020

IndexDeque<T> is an array based implementation of Indexable<T> and a subclass of MyDeque<T>.
IndexDeque provides access to indexes between the logical front and back of the deque.
IndexDeque has additional functionality to return an entry located at an index in the middle
of the deuqe.  It can do this using either front or back as a starting point.  
IndexDeque can also set an index in the middle of the deque to a specific entry determined
by the user. This replaces the current entry with an item specified by the user. Setting indexes can
also be done using either the front or back as a starting point.
*/

public class IndexDeque<T> extends MyDeque<T> implements Indexable<T>{


	public IndexDeque(int sz){  
		super(sz);
	}
	
	
	public T getFront(int i){

		if(logSize < i+1){    //case 1: if an IndexOutOfBoundsException needs to be thrown
			throw new IndexOutOfBoundsException("Illegal Index " + i);
		}

		if(front < data.length - i){		// case 2: this if  runs if the program can move i indexes from front without passing the physical end of the array
			return data[i+front];
		}
		
															//case 3: if you have to wrap around to the physical beginning of the array, the code below runs
		int distanceFromZero = i - (data.length - front);  //data.length - front is the number of indexes between front and the physical end
		return data[distanceFromZero];				  	   //distanceFromZero stores the amount of indexes away from physical index 0 that i is
	}


	public T getBack(int i){
		
		if(logSize < i+1){  //case 1: if an IndexOutOfBoundsException needs to be thrown
			throw new IndexOutOfBoundsException("Illegal Index " + i);
		}

		
		if(back >= i){         // case 2: this if runs if the program can move i indexes without passing the physical beginning of the array 
			return data[back-i];
		}
		
		
		int indexMovesRemaining = i - (back+1);					  //case 3: runs if you have to wrap around to the physical end of the array
		int indexToReturn = data.length-1 - indexMovesRemaining;    //indexMovesRemaining is the number of indexes we still have to move after wrapping to the physical end, before index i is found
		return data[indexToReturn]; 								// indexToReturn specifies the logical index i 
	}


	public void setFront(int i, T item){

		if(logSize < i+1){      //case 1: if an IndexOutOfBoundsException needs to be thrown
			throw new IndexOutOfBoundsException("Illegal Index " + i); 
		}

		
		else if(front < data.length - i){   // case 2: this if runs if the program can move i indexes from front without passing the physical end of the array
			data[i+front] = item;
		}
		
		
		else{														// case 3: if you have to wrap around to the physical beginning of the array, this code runs
			int distanceFromZero = i - (data.length - front);     		//data.length - front is the number of indexes between front and the physical end
			data[distanceFromZero] = item;								//distanceFromZero stores the amount of indexes away from physical index 0 that i is
		}


	}


	public void setBack(int i, T item){

		if(logSize < i+1){   //case 1: if an IndexOutOfboundsException needs to be thrown
			throw new IndexOutOfBoundsException("Illegal Index " + i); 
		}

		
		else if(back >= i){			//case 2: this if runs if the program can move i indexes without passing the physical beginning of the array 
			data[back-i] = item;
		}
		
		
		
		else{
			int indexMovesRemaining = i - (back+1);				  //case 3: the code below runs if you have to wrap around to the physical end of the array
			int valToSet = data.length-1 - indexMovesRemaining;		//indexMovesRemaining is the number of indexes we still have to move after wrapping to the physical end, before index i is found
			data[valToSet] = item;									// indexToReturn specifies the logical index i 
		}
	}
}