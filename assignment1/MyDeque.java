/*
David Reidenbaugh
CCS 445 Fall 2020

The MyDeque class is an array-based implementation of the DequeInterface<T>, which is also in this class.
MyDeque objects are deques, entries can be added/removed to the front/back, and theyhave functionality 
similar to a circular array where the logical front/back will wrap around to the other side of the array if it 
would pass the physical front/back.   
In addition to the methods specified in DequeInterface<T>, MyDeque also contains a a copy constructor, an equals method,
a toString method, and an upSize method.  
MyDeque(MyDeque<T> old) is a copy constructor that returns a MyDeque object
identical to the one passed in.  
equals(MyDeque<T> rhs) will compare the inidividual entries of two MyDeque to and return true if 
both have identical entries in the the same relative positions.
toString() will make and return a string from entries stored in the deque.
upSize() will double the length of the deque if try to add when it is full.
*/

public class MyDeque<T> implements DequeInterface<T>{

	protected T [] data;
	//front is the logical front of data
	//back is the logical back of data
	//logSize is the logical size of data/ number of items
	protected int front, back, logSize;
	
	public MyDeque(int sz){
		data = (T []) new Object[sz];

		back = -1; 
		front = -1;  
		logSize = 0;
	}

	//makes a new MyDeque logically equivalent identical to the MyDeque passed in
	//new MyDeque is a deeper copy 
	public MyDeque(MyDeque<T> old){  
		data = (T []) new Object[old.data.length];  

		for(int i = old.front, j=0; j < old.logSize; j++){
			data[i] = old.data[i];

			i = (i+1)%data.length;
		}
		
     	logSize = old.logSize;
     	front = old.front; 
     	back = old.back; 
	}



	public void addToFront(T newEntry){

		if(logSize == data.length){   //upsize array if necessary
			upSize();
		}
						
		if(isEmpty()){  // case 1: deque is empty, set front and back to 0
			front =0;
			back =0;
			logSize++;
			data[front] = newEntry;
		}
		else if(front == 0){  				//case 2: if front is at index 0
			int lastIndex = data.length-1;
			data[lastIndex] = newEntry;
			front = lastIndex;
			logSize++;
		}
		else{  							//case 3: deque not empty and front not index 0
			data[front-1] = newEntry;
			front--;
			logSize++;
		}		
	}


	public void addToBack(T newEntry){

		if(logSize == data.length){  //upSize array if necessary
			upSize(); 
		}

		
		if(isEmpty()){  //case 1: deque is empty, set front and back to 0
			front = 0;
			back =0;
			logSize++;
			data[back] = newEntry;
		}
		else if(back == data.length-1){  //case 2: back is data.length-1
			data[0] = newEntry;
			back = 0;
			logSize++;
		}
		else{							//case 3: deque not empty and back not data.length-1
			data[back+1] = newEntry;
			back++;
			logSize++;
		}	
	}


	public T removeFront(){  
		if(isEmpty()){   // when empty return null
			return null;
		}
		
		T entryReturn = data[front]; // since front will be updated, entryReturn is a temporary variable to store the entry that will be returned

		if(front == (data.length-1)){  // case 1: if front is at the physical end of the array
			data[front] = null;
			front = 0;
		}
		else{							//case 2: if front is not at the physical end of the array
			data[front] = null;
			front++;
		}

		logSize--;
		
		if(isEmpty()){ //if deque is empty, instance variables front and back are reset to -1   
			back = -1;
			front = -1;
		}
		return entryReturn;
		
	}


	public T removeBack(){
		if(isEmpty()){   //when empty return null
			return null;
		}
		
		T entryReturn = data[back];  //since back will be updated, temp will store the entry that will be returned

		if(back == 0){				//case 1: if back is at index 0
			data[back] = null;
			back = data.length-1;

		}
		else{						//case 2: if back is not at index 0
			data[back] = null;
			back--;
		}

		logSize--;

		if(isEmpty()){  //added this to reset instance variables if deque is empty
			back = -1;
			front = -1;
		}
		return entryReturn;	
	}


	public T getFront(){
		if(isEmpty()){   //return null if empty
			return null;
		}
		
		return data[front];
	}


	public T getBack(){

		if(isEmpty()){   //return null if empty
			return null;
		}
		
		return data[back];
		
	}


	public boolean isEmpty(){
		return logSize == 0;
	}


	public void clear(){
		data = (T []) new Object[data.length];
		front = -1;  
		back = -1;
		logSize = 0;
	}


	public int size(){
		return logSize;
	}


	public int capacity(){
		return data.length;
	}


	// compares two deques, returns true if both are logically equivalent ot each other
	// items in both deques are identical and in the same relative positions to front and back
	public boolean equals(MyDeque<T> rhs){ 
		if(logSize == rhs.logSize){   // test if the logical sizes of the two deques are equal
			
			int i = front;
			int j = rhs.front;

			for(int x=0; x < logSize; x++){ 

				if(data[i] != rhs.data[j]){  // if entries at logical indexes in the two deques don't match
					return false;
				}

				if(i != data.length-1){    // case 1: if i is not at the last index, i++
					i++;
				}
				else{						//case 2: if i is at last index, i = 0
					i = 0;
				}

				if(j != rhs.data.length-1){    //case 1: if j is not at last index, i++
					j++;
				}
				else{							//case 2: if j is at last index, j = 0
					j = 0;
				}
			}
			return true;  //return true if entries are identical and in same relative order
		}
		return false; // return false if logSize different or entries different
	}


	// Make and return single String from data stored in deque
	// Proceed from front to back.
	public String toString(){
	
			StringBuilder total = new StringBuilder("Contents: ");

			for(int i=front, j=0; j < logSize; j++){
				total.append(data[i].toString() + " ");   
				i = (i+1)%data.length;
			}

			return total.toString();
	}

	// makes a new MyDeque (biggerDeque) that is double the size of the old MyDeque
	// copies the entries from old MyDeque into biggerDeque, while maintaining the same relative order
	protected void upSize(){  
		T[] biggerDeque = (T[]) new Object[data.length * 2]; // creates a new deque twice the length of the full deque

		
		for(int i=front, j=0; j < logSize; j++){   // copying entries from old MyDeque into biggerDeque
			biggerDeque[j] = data[i];
			i = (i+1)%data.length;
		}

		data = biggerDeque;  
		front = 0;
		back = logSize-1;		
	}
}