/*
David Reidenbaugh
CS 445 Fall 2020

IndexAddRemoveDeque<T> allows new items to be inserted into or removed from the middle of the deque.
IndexDeque has additional functionality to remove and return an entry located at an index in the middle
of the deuqe.  It can do this using either front or back as a starting point.  
IndexDeque can also add an entry in the middle of the deque at a location specifed by the user.
Setting indexes can also be done using either the front or back as a starting point.
*/

public class IndexAddRemoveDeque<T> extends IndexDeque<T> implements IndexableAddRemove<T>{
	
	public IndexAddRemoveDeque(int sz){
		super(sz);
	}

	
	public void addToFront(int i, T item){ 

		if(logSize < i){   //case 1: if an IndexOutOfBoundsException needs to be thrown
			throw new IndexOutOfBoundsException("Illegal Index " + i); 
		}
		if(logSize == data.length){ 	//upSize if necessary
			upSize();
		}

		int physicalEnd = data.length-1;

		if(isEmpty()){ 		//case 2: if deque is empty
			front = 0;
			back = 0;
			logSize++;
			data[front] = item;
		}
		else if(front == 0){   //case 3: if front is at physical index 0
		
			front = physicalEnd;
			data[physicalEnd] = data[0];
	
			for(int x=0; x < i; x++){
				data[x] = data[x+1];
			}

			data[i-1] = item;
			logSize++;
		}
		else{					//case 4: if no exception, deque is not empty, and front is not 0
			front--;

			int x=front%data.length;
			int y=(front+1)%data.length;
			for(int z=0; z < i; z++){
				data[x] = data[y];

				x = (x+1)%data.length;
				y= (y+1)%data.length;
			}

			data[x] =item;
			logSize++;
		}
	}

	
	public void addToBack(int i, T item){
		if(logSize < i){   //case 1: if an IndexOutOfBoundsException needs to be thrown
			throw new IndexOutOfBoundsException("Illegal Index " + i); 
		}
		if(logSize == data.length){   //upSize array if necessary
			upSize();
		}


		int physicalEnd = data.length-1;

		if(isEmpty()){ 		//case 2: if deque is empty
			front = 0;
			back = 0;
			logSize++;
			data[back] = item;
		}
		else if(back == physicalEnd){  //case 3: back is at physical end of array
			back = 0;
			data[0] = data[physicalEnd];

			for(int x=0; x < i; x++){
				data[physicalEnd-x] = data[physicalEnd-1-x];
			}

			data[data.length-i] = item;
			logSize++;

		}
		else{		//case 4: no exception, deque not empty, back not at physical end

			back++;

			int x = (back+data.length)%data.length;
			int y = (back+data.length-1)%data.length;

			for(int z=0; z < i; z++){
				
				data[x] = data[y];

				x = (x+data.length-1)%data.length;
				y = (y+data.length-1)%data.length;  
			}

			data[x] = item;
			logSize++;
			
		}

		
	}

	
	public T removeFront(int i){  
		int physicalEnd = data.length-1;
		T indexLoad = data[front];

		if(logSize < i){    //case 1: if an IndexOutOfBoundsException needs to be thrown
			throw new IndexOutOfBoundsException("Illegal Index " + i); 
		}

		else{				//case 2: no exception thrown
			int x = (front+1)%data.length;

			for(int z=0; z < i; z++){
				T indexStore = data[x];  //storing the index entry that will be replaced 

				data[x] = indexLoad; //putting previous index into index one above
				indexLoad = indexStore;  // putting the replaced index entry into indexStore to be placed in index one above next iteration

				x = (x+1)%data.length;
			}
		}

		data[front] = null;
		logSize--;
		
		if(isEmpty()){   // case A: deque is empty after removal, set front and back to -1
			front = -1;
			back = -1;
		}
		else if(front == physicalEnd){   // case B: front at physical end
			front=0;
		}
		else{					// case C: deque not empty and front not at physical end
			front++;
		}

		return indexLoad;
	}

	
	public T removeBack(int i){
		int physicalEnd = data.length-1;
		T indexLoad = data[back];

		if(logSize < i){    //case 1: if an IndexOutOfBoundsException needs to be thrown
			throw new IndexOutOfBoundsException("Illegal Index " + i); 
		}
		else{				//case 2: no exception thrown
			int x = (back+data.length-1)%data.length;

			for(int z=0; z < i; z++){
				T indexStore = data[x]; //storing index entry that will be written over

				data[x] = indexLoad;   //putting previous value into index one lower
				indexLoad = indexStore; // putting the replaced index entry into indexStore to be placed into index one lower during next iteration

				x = (x+data.length-1)%data.length;
			}
		}

		data[back] = null;
		logSize--;
		
		if(isEmpty()){  // case A: if deque is empty after removal, set front and back to -1
			back = -1;
			front = -1;
		}
		else if(back == 0){    //case B: if back is at physical index 0
			back = physicalEnd;
		}
		else{				//case C: if deque not empty and back not at physical 0
			back--;
		}

		return indexLoad;
	}

}