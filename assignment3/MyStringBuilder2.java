// David Reidenbaugh
// CS 0445 Fall 2020
/*
If MSB appears in the comments, that stands for MyStringBuilder2
MyStirngBuilder2 has similiar functionality to MyStringBuilder, except all of its methods
are implemented using recursion.  No loops are used.
There are two additional methods (lastIndexOf and regMatch) that provide additional functionality.
The lastIndexOf finds and returns the final index where a particular String appears. If not found returns -1.
The regmatch searches an MSB and tries to match a sequence of char Nodes to a String from
an array of Strings. It does this for each String in the array of Strings.  If matches are found,
an array of the longest possible MSBs is returned, if not matched returns null.
*/

// Read this class and its comments very carefully to make sure you implement
// the class properly.  The data and public methods in this class are identical
// to those in MyStringBuilder, with the exception of the two additional methods
// shown at the end.  You cannot change the data or add any instance
// variables.  However, you may (and will need to) add some private methods.
// No iteration (i.e. no loops) is allowed in this implementation. 

// For more details on the general functionality of most of these methods, 
// see the specifications of the similar method in the StringBuilder class.  

import java.util.ArrayList; //this line is added because I use ArrayList in a function I wrote for extra credit

public class MyStringBuilder2
{
	// These are the only three instance variables you are allowed to have.
	// See details of CNode class below.  In other words, you MAY NOT add
	// any additional instance variables to this class.  However, you may
	// use any method variables that you need within individual methods.
	// But remember that you may NOT use any variables of any other
	// linked list class or of the predefined StringBuilder or 
	// or StringBuffer class in any place in your code.  You may only use the
	// String class where it is an argument or return type in a method.
	private CNode firstC;	// reference to front of list.  This reference is necessary
							// to keep track of the list
	private CNode lastC; 	// reference to last node of list.  This reference is
							// necessary to improve the efficiency of the append()
							// method
	private int length;  	// number of characters in the list

	// You may also add any additional private methods that you need to
	// help with your implementation of the public methods.

	// Create a new MyStringBuilder2 initialized with the chars in String s
	public MyStringBuilder2(String s)
	{
		if (s != null && s.length() > 0)
			makeBuilder(s, 0);  //second 0 is a temp
		else // no String so initialize empty MyStringBuilder2
		{
			length = 0;
			firstC = null;
			lastC = null;
		}

	}
	// Recursive method to set up a new MyStringBuilder2 from a String
	private void makeBuilder(String s, int pos){
		// Recursive case – we have not finished going through the String
		if (pos < s.length()-1){
			// Note how this is done – we make the recursive call FIRST, then
			// add the node before it. In this way the LAST node we add is
			// the front node, and it enables us to avoid having to make a
			// special test for the front node. However, many of your
			// methods will proceed in the normal front to back way.
			makeBuilder(s, pos+1);
			firstC = new CNode(s.charAt(pos), firstC);
			length++;
		}
		else if (pos == s.length()-1){ // Special case for last char in String
			// This is needed since lastC must be
			// set to point to this node
			firstC = new CNode(s.charAt(pos));
			lastC = firstC;
			length = 1;
		}
		else{ // This case should never be reached, due to the way the
			// constructor is set up. However, I included it as a
			// safeguard (in case some other method calls this one)
			length = 0;
			firstC = null;
			lastC = null;
		}
	}

//----------------------------------------------------------------------------------------------------------------------------------------------------------


	// Create a new MyStringBuilder2 initialized with the chars in array s
	public MyStringBuilder2(char [] s)
	{
		if (s != null && s.length > 0)
			makeBuilder(s, 0);
		else // no array so initialize empty MyStringBuilder2
		{
			length = 0;
			firstC = null;
			lastC = null;
		}
	}
	// Recursive method to set up a new MyStringBuilder2 from an array
	private void makeBuilder(char[] s, int pos){
		// Recursive case – we have not finished going through the array
		if (pos < s.length-1){
			makeBuilder(s, pos+1);
			firstC = new CNode(s[pos], firstC);
			length++;
		}
		else if (pos == s.length-1){ // Special case for last char in array
			// This is needed since lastC must be
			// set to point to this node
			firstC = new CNode(s[pos]);
			lastC = firstC;
			length = 1;
		}
		else{ // This case should never be reached, due to the way the
			// constructor is set up. However, I included it as a
			// safeguard (in case some other method calls this one)
			length = 0;
			firstC = null;
			lastC = null;
		}
	}

//----------------------------------------------------------------------------------------------------------------------------------------------------------

	// Copy constructor -- make a new MyStringBuilder2 from an old one.  Be sure
	// that you make new nodes for the copy.
	public MyStringBuilder2(MyStringBuilder2 old)
	{
		if (old.length > 0)
			makeBuilder(old.firstC);
		else // no String so initialize empty MyStringBuilder2
		{
			length = 0;
			firstC = null;
			lastC = null;
		}
	}
	// Recursive method to set up a new MyStringBuilder2 from a MSB
	private void makeBuilder(CNode oldCurr){
		// Recursive case
		if (oldCurr.next != null){
			makeBuilder(oldCurr.next); //recursively traverse to end of MSB
			firstC = new CNode(oldCurr.data, firstC);//attatch newNode to front of list
			length++;
		}
		// base case, at last node
		else if (oldCurr.next == null){ // Special case for last char in MSB
			firstC = new CNode(oldCurr.data); //make the lastNode
			lastC = firstC; //set lastC to currNode
			length = 1;
		}
		else{ // This case should never be reached, due to the way the
			// constructor is set up. However, I included it as a
			// safeguard (in case some other method calls this one)
			length = 0;
			firstC = null;
			lastC = null;
		}
	}

//----------------------------------------------------------------------------------------------------------------------------------------------------------

	
	// Create a new empty MyStringBuilder2
	public MyStringBuilder2()
	{
		length = 0;
		firstC = null;
		lastC = null;
	}

//----------------------------------------------------------------------------------------------------------------------------------------------------------
	// Append MyStringBuilder2 b to the end of the current MyStringBuilder2, and
	// return the current MyStringBuilder2.  Be careful for special cases!
	public MyStringBuilder2 append(MyStringBuilder2 b)
	{
		//if there is nothing in b
		if(b.length == 0){
			return this; // nothing changes
		}
		
		if(length == 0){
			appendBuilderEmpty(b.firstC);
		}
		else{
			appendBuilder(b.firstC);
		}
		return this;
	}

	//append when MSB is empty, if(length == 0)
	private void appendBuilderEmpty(CNode bCurr){
			if(bCurr.next != null){
				appendBuilder(bCurr.next);  //traverse to end of list
				firstC = new CNode(bCurr.data, firstC);  //add node when return to recursive call
				length++;
			}
			else{
				firstC = new CNode(bCurr.data);
				lastC = firstC;
				length++;
			}
	}//end appendBuilderEmpty


	//append when length > 0
	private void appendBuilder(CNode bCurr){
		if(bCurr != null){
			CNode newNode = new CNode(bCurr.data); //add node
			lastC.next = newNode;
			lastC = newNode;  //make lastC the newly added node
			length++;
			appendBuilder(bCurr.next);  //add next node
		}
	}//end appendBuilder

//---------------------------------------------------------------------------------------------------------------------------------------------------------


	// Append String s to the end of the current MyStringBuilder2, and return
	// the current MyStringBuilder2.  Be careful for special cases!
	public MyStringBuilder2 append(String s)
	{
		//nothing in s
		if(s == null || s.length() == 0){
			return this;  //nothing changes
		}

		if(length == 0){
			appendBuilderEmpty(s, 0);
		}
		else{
			appendBuilder(s, 0);
		}

		return this;
	}


	//counter holds the position in String s

	//append if MSB is empty, if(length == 0)
	private void appendBuilderEmpty(String s, int counter){
		//recursive case
		if(counter < s.length()-1){
			appendBuilderEmpty(s, counter+1); //traverse to end of the String
			firstC = new CNode(s.charAt(counter), firstC); //appending new node with current char from s
			length++;
		}
		//base case, at last char in the String
		else{  //counter == s.length()-1
			firstC = new CNode(s.charAt(counter));
			lastC = firstC;
			length++;
		}
	}//end appendBuilderEmpty


	//append when length > 0
	private void appendBuilder(String s, int counter){
		if(counter != s.length()){
			CNode newNode = new CNode(s.charAt(counter)); //make new last noe
			lastC.next = newNode;  //attatch previous node to current node
			lastC = lastC.next;  //set lastC to the current node
			length++;
			appendBuilder(s, counter+1);
		}
	}//end appendBuilder

//---------------------------------------------------------------------------------------------------------------------------------------------------------


	// Append char array c to the end of the current MyStringBuilder2, and
	// return the current MyStringBuilder2.  Be careful for special cases!
	public MyStringBuilder2 append(char [] c)
	{
		if(c == null || c.length == 0){
			return this;    //nothing changes
		}
		//the current MyStringBuilder is empty
		if(length == 0){
			appendBuilderEmpty(c, 0);
		}
		//the current MyStringBuilder contains something
		else{
			appendBuilder(c, 0);	
		}
		return this;
	}


	//counter holds the position in char[] c

	//append when MSB is empty
	private void appendBuilderEmpty(char[] c, int counter){
		//recursive case
		if(counter < c.length-1){
			appendBuilderEmpty(c, counter+1); //traverse to end of array
			firstC = new CNode(c[counter], firstC); //new firstC is added to front of list
			length++;
		}
		//base case
		else{
			firstC = new CNode(c[counter]); //add new node to end of empty list
			lastC = firstC; // new node is both the firstC and lastC
			length++;
		}
	}// end appendBuilderEmpty


	//append when MSB length > 0
	private void appendBuilder(char[] c, int counter){
		if(counter != c.length){
			CNode newNode = new CNode(c[counter]); //add new node w/ data from c to end of list
			lastC.next = newNode;
			lastC = newNode;
			length++;
			appendBuilder(c, counter+1);
		}
	} //end appendBuilder


//---------------------------------------------------------------------------------------------------------------------------------------------------------

	// Append char c to the end of the current MyStringBuilder2, and
	// return the current MyStringBuilder2.  Be careful for special cases!
	public MyStringBuilder2 append(char c)
	{
		//the current MyStringBuilder2 is empty
		if(length == 0){
			firstC = new CNode(c);
			lastC = firstC;
		}
		//current MyStringBuilder2 not empty
		else{
			lastC.next = new CNode(c);
			lastC = lastC.next;
			
		}
		length++;
		return this;
	}

//---------------------------------------------------------------------------------------------------------------------------------------------------------
	// Return the character at location "index" in the current MyStringBuilder2.
	// If index is invalid, throw an IndexOutOfBoundsException.
	public char charAt(int index)
	{
		// throw exception if index invalid
		if(index >= length || index < 0){
			throw new IndexOutOfBoundsException("Illegal position given to charAt operation. ");
		}
		
		//return the char at location index
		return getCharAt(index, 0, firstC);
	}


	//finds and returns the char at index
		//counter keeps track of location in MSB2
		//index is where we want to get data from
	private char getCharAt(int index, int counter, CNode currNode){
		//recursive case, traverse until have access to the correct node
		if(counter < index){
			return getCharAt(index, counter+1, currNode.next);
		}
		//base case, return the data located at index
		else{
			return currNode.data;
		}
	}
//---------------------------------------------------------------------------------------------------------------------------------------------------------


	// Delete the characters from index "start" to index "end" - 1 in the
	// current MyStringBuilder2, and return the current MyStringBuilder2.
	// If "start" is invalid or "end" <= "start" do nothing (just return the
	// MyStringBuilder2 as is).  If "end" is past the end of the MyStringBuilder2, 
	// only remove up until the end of the MyStringBuilder2. Be careful for 
	// special cases!
	public MyStringBuilder2 delete(int start, int end)
	{


		//???? what happens when < 0 and > end ?????????

		//do nothing if invalid argument
		if(start >= length || start < 0 || start > end){        
			return this;  // nothing changes
		}

		if(end > length){
			deleteBuilderEnd(start, 0, firstC);
			length -= ((length-1) - start);
		}
		else if (start == 0){
			deleteBuilderStart(end, 0, firstC);
			length -= (end - start);
		}
		else{
			// System.out.println("inside else");
			deleteBuilder(start, end, 0, firstC, firstC);
			length -= (end - start);
		}

		if(length == 0){  //reset lastC if empty
			lastC = null;
		}

		return this;
	} //end delete


	// delete to end, if(end >length)
	private void deleteBuilderEnd(int start, int index, CNode currNode){
		if(index < start-1){
			deleteBuilderEnd(start, index+1, currNode.next);
		}
		else{
			lastC = currNode;
			lastC.next = null;
		}
	} //end deleteBuilderEnd


	//delete from start, if(start == 0) 
	private void deleteBuilderStart(int end, int index, CNode currNode){
		if(index < end-1){
			deleteBuilderStart(end, index+1, currNode.next);
		}
		else{
			firstC = currNode.next;
		}
	} //end deleteBuilderStart


	//delete when no special cases
	private void deleteBuilder(int start, int end, int index, CNode beforeDelete, CNode currNode){
		if(index < end){
			if(index == start-1){
				beforeDelete = currNode;
			}
			index++;
			deleteBuilder(start, end, index, beforeDelete, currNode.next);  
		}
		else{
			beforeDelete.next = currNode;
		}
	}// end deleteBuilder


//---------------------------------------------------------------------------------------------------------------------------------------------------------

	// Delete the character at location "index" from the current
	// MyStringBuilder2 and return the current MyStringBuilder2.  If "index" is
	// invalid, do nothing (just return the MyStringBuilder2 as is).
	// Be careful for special cases!
	public MyStringBuilder2 deleteCharAt(int index)
	{
		// stays the same if argument is invalid
		if(index > length-1 || index < 0){
			return this;  //nothing changes
		}
		// special case if start is 0
		else if(index == 0){
			CNode currNode = firstC;
			firstC = currNode.next;
			length--;
		}
		//index valid and > 0
		else{
			deleteChar(index, 0, firstC);
		}

		if(length == 0){ //reset lastC if empty
			lastC = null;
		}

		return this;
	}

	//remove the char at index
	private void deleteChar(int index, int counter, CNode currNode){
		//recursive case
		if(counter < index-1){
			deleteChar(index, counter+1, currNode.next);
		}
		//base case, 
		else{
			CNode beforeIndex = currNode;
			CNode afterIndex = currNode.next.next;
			beforeIndex.next = afterIndex;  //removing char at index
			
			if(index == length-1){ //if deleted char at the last node, make the currNode lastC
				lastC = currNode;
			}
			length--; //decrease after the conditional or else it would never run
		}
	}

//---------------------------------------------------------------------------------------------------------------------------------------------------------

	// Find and return the index within the current MyStringBuilder2 where
	// String str first matches a sequence of characters within the current
	// MyStringBuilder2.  If str does not match any sequence of characters
	// within the current MyStringBuilder2, return -1.  Think carefully about
	// what you need to do for this method before implementing it.
	public int indexOf(String str)
	{
		
		int indexReturn = -1;

		if(str.length() <= length && str.length() > 0){
			indexReturn = getIndexOf(0, str, firstC);
		}
		//return -1 if length of str is larger than SB length

		return indexReturn;
	}

	//find potential index where str could be located in MSB
	//returns the first index where a match is found, if no match returns -1
		//builderPosition keeps track of the location in MSB
	private int getIndexOf(int builderPosition, String str, CNode currNode){

		if(builderPosition <= length-str.length()){ //only search for match if there is space for it to be found in MSB
			boolean found = false;

			if(str.charAt(0) == currNode.data)
				if(str.length() == 1){  //if str is only a single char, have already found first index
					found = true;
				}
				else{
					found = compareStrings(1, str, currNode.next);  //test if string found at index, if found, index is returned					
				}

			if(found){ //if a match has been found, return the current builderPosition
				return builderPosition;
			}
			else{  //if no match found, search for match at next node
				return getIndexOf(builderPosition+1, str, currNode.next); 
			}
		}
		return -1; //no match found

	}//end getIndexOf



	//return boolean telling whether the sequence of char in MSB is equivalent to str
		//strPosition keeps track of the location in str
		//iterNode keeps track of the node to which a char is being compared
	private boolean compareStrings(int strPosition, String str, CNode iterNode){
		if(strPosition == str.length()-1){
			if(str.charAt(strPosition) == iterNode.data){
				return true;  //final char in str matches the current node
			}
		}
		else if(str.charAt(strPosition) == iterNode.data){ //move to next node
			return compareStrings(strPosition+1, str, iterNode.next);
		}
		//no match at current SB index
		return false; //inner loop wont continue

	} //end compareStrings

//---------------------------------------------------------------------------------------------------------------------------------------------------------


	// Insert String str into the current MyStringBuilder2 starting at index
	// "offset" and return the current MyStringBuilder2.  if "offset" == 
	// length, this is the same as append.  If "offset" is invalid
	// do nothing.
	public MyStringBuilder2 insert(int offset, String str)
	{
		//offset is invalid or str is a null refrence
		if(offset > length || offset < 0 || str == null){
			return this; // nothing happens
		}
		//insert at the end 
		else if(offset == length){
			append(str);
		}
		//insert at beginning
		else if(offset == 0){
			insertBuilderBeginning(0, str);
			length += str.length(); 
		}
		//offset is valid and between 0 and length-1 inclusive
		else{
			insertBuilder(0, offset, str, false, firstC, null);
			length += str.length();
		}

		return this;
	}

	//insert at beginning
	private void insertBuilderBeginning(int counter, String str){
		if(counter != str.length()){
			insertBuilderBeginning(counter+1, str);
			firstC = new CNode(str.charAt(counter), firstC);
		}
	}


	//offset is valid and between 0 and length-1 inclusive
		//after offset is found, nodeAfter references the node after offset
	private void insertBuilder(int counter, int offset, String str, boolean offsetReached, CNode currNode, CNode nodeAfter){
		//runs until node before offset has been reached
		if(!offsetReached){
			if(counter < offset-1){
				insertBuilder(counter+1, offset, str, offsetReached, currNode.next, nodeAfter); //increae counter, move to next node in SB
			}
			else if (counter == offset-1){
				insertBuilder(0, offset, str, true, currNode, currNode.next); //reset counter, offsetReached =true, pass on current node, save reference to nodeAfter	
			}
			
		}
		//else, runs when the node before offset has been reached
		else{
			if(counter < str.length()-1){
				CNode newNode = new CNode(str.charAt(counter)); //make a new node
				currNode.next = newNode; //attatch the new node to the insert
				insertBuilder(counter+1, offset, str, offsetReached, newNode, nodeAfter);  //increase counter and make the newly added node the current node
			}
			else if (counter == str.length()-1){
				CNode newNode = new CNode(str.charAt(counter)); //make a new node
				currNode.next = newNode; //attatch the new node to the insert
				newNode.next = nodeAfter; //reattach to other end of SB
			}
		}
	} //end insertBuilder

	//-----------------------------------------------------------------------------------------------------------------------------------------------------

	// Insert character c into the current MyStringBuilder2 at index
	// "offset" and return the current MyStringBuilder2.  If "offset" ==
	// length, this is the same as append.  If "offset" is invalid, 
	// do nothing.
	public MyStringBuilder2 insert(int offset, char c)
	{
		//offset is invalid
		if(offset > length || offset < 0){
			return this;//nothing changes
		}
		//insert at end of 
		else if(offset == length){
			append(c);
		}
		//insert at begining
		else if(offset == 0){
			CNode oldFirstC = firstC;
			firstC = new CNode(c);
			firstC.next = oldFirstC;
			length++;
		}
		//offset is valid and between 0 and length-1 inclusive
		else{
			insertBuilder(0, offset, c, firstC);
			length++;
		}

		return this;
	}


	private void insertBuilder(int counter, int offset, char c, CNode currNode){
		if(counter < offset-1){
			insertBuilder(counter+1, offset, c, currNode.next); //increae counter, move to next node in SB
		}
		else if (counter == offset-1){
			currNode.next = new CNode(c, currNode.next);
		}
	}

	//-----------------------------------------------------------------------------------------------------------------------------------------------------

	// Insert char array c into the current MyStringBuilder2 starting at index
	// index "offset" and return the current MyStringBuilder2.  If "offset" is
	// invalid, do nothing.
	public MyStringBuilder2 insert(int offset, char [] c)
	{
		//offset is invalid or array is null refrence
		if(offset > length || offset < 0 || c == null){
			return this;//nothing happpens                   
		}
		//insert at end
		else if(offset == length){
			append(c);
		}
		//insert at begining
		else if(offset == 0){
			insertBuilderBeginning(0, c);
		}
		// offset is valid and between 0 and length-1 inclusive
		else{
			insertBuilder(0, offset, c, false, firstC, null);
		}

		length += c.length; 
		return this;
	}

	//insert at beginning
	private void insertBuilderBeginning(int counter, char[] cArray){
		if(counter != cArray.length){
			insertBuilderBeginning(counter+1, cArray);
			firstC = new CNode(cArray[counter], firstC);
		}
	}

	//offset is valid and between 0 and length-1 inclusive
	private void insertBuilder(int counter, int offset, char[] cArray, boolean offsetReached, CNode currNode, CNode nodeAfter){
		//runs until node before offset has been reached
		if(!offsetReached){
			if(counter < offset-1){
				insertBuilder(counter+1, offset, cArray, offsetReached, currNode.next, nodeAfter); //increae counter, move to next node in SB
			}
			else if (counter == offset-1){
				insertBuilder(0, offset, cArray, true, currNode, currNode.next); //reset counter, offsetReached =true, pass on current node, save reference to nodeAfter	
			}
			
		}
		//else, runs when reached node before offset
		else{
			if(counter < cArray.length-1){
				CNode newNode = new CNode(cArray[counter]); //make a new node
				currNode.next = newNode; //attatch the new node to the insert
				insertBuilder(counter+1, offset, cArray, offsetReached, newNode, nodeAfter);  //increase counter and make the newly added node the current node
			}
			else if (counter == cArray.length-1){
				CNode newNode = new CNode(cArray[counter]); //make a new node
				currNode.next = newNode; //attatch the new node to the insert
				newNode.next = nodeAfter; //reattach to other end of SB
			}
		}
	} //end insertBuilder



//---------------------------------------------------------------------------------------------------------------------------------------------------------
	// Return the length of the current MyStringBuilder2
	public int length()
	{
		return this.length;
	}

//---------------------------------------------------------------------------------------------------------------------------------------------------------
	// Delete the substring from "start" to "end" - 1 in the current
	// MyStringBuilder2, then insert String "str" into the current
	// MyStringBuilder2 starting at index "start", then return the current
	// MyStringBuilder2.  If "start" is invalid or "end" <= "start", do nothing.
	// If "end" is past the end of the MyStringBuilder2, only delete until the
	// end of the MyStringBuilder2, then insert.  This method should be done
	// as efficiently as possible.  In particular, you may NOT simply call
	// the delete() method followed by the insert() method, since that will
	// require an extra traversal of the linked list.
	public MyStringBuilder2 replace(int start, int end, String str)
	{
		//return same MyStringBuilder
		if(start >= length || start < 0 || start >= end || str == null){        
			return this;//nothing happens
		}
		// deletes to the end if end > length
		else if(end > length){
			replaceBuilderEnd(0, start, str, false, firstC, null);
			length = length + str.length() - ((length-1)-start);
		}
		// special case if start is 0
		else if(start == 0){
			replaceBuilderBeginning(0, end, str, false, firstC);
			length = length + str.length() - (end-start);
		}
		//valid and no special cases
		else{
			
			replaceBuilder(0, start, end, str, false, firstC, null, null);
			length = length + str.length() - (end-start);
		}

		return this;
	} //end replace


	//deletes to the end if end > length
	private void replaceBuilderEnd(int counter, int start, String str, boolean startReached, CNode currNode, CNode nodeBefore){
		//runs until reach the node before start
		if(!startReached){
			if(counter < start-1){
				replaceBuilderEnd(counter+1, start, str, startReached, currNode.next, nodeBefore); //move to next node, increase counter
			}
			else if(counter == start-1){
				replaceBuilderEnd(0, start, str, true, currNode, currNode); //set startReached to true, nodeBefore is the currNode
			}
			
			
		}
		//runs after node before start can be accessed
		else{
			//recursive case
			if(counter < str.length()-1){
				CNode newNode = new CNode(str.charAt(counter));//make a new node
				currNode.next = newNode; //attatch new node 
				replaceBuilderEnd(counter+1, start, str, startReached, newNode, nodeBefore);
			}
			//base case, last char in str
			else if(counter == str.length()-1){
				CNode newNode = new CNode(str.charAt(counter));//make new node
				currNode.next = newNode;
				lastC = newNode;
			}
			
		}
	} //end replaceBuilderEnd


	//special case if start is 0
	private void replaceBuilderBeginning(int counter, int end, String str, boolean endReached, CNode currNode){
		// runs until reach the node before end
		if(!endReached){
			if(counter < end-1){
				//currNode = currNode.next
				replaceBuilderBeginning(counter+1, end, str, endReached, currNode.next);
			}
			else if(counter == end-1){
				//afterNode = currNode
				replaceBuilderBeginning(0, end, str, true, currNode.next);
			}
			
		}
		//runs after have access to node before end
		else{
			//recursive case
			if(counter < str.length()-1){
				replaceBuilderBeginning(counter+1, end, str, endReached, currNode);
				firstC= new CNode(str.charAt(counter), firstC);
			}
			//base case, last char in str
			else if(counter == str.length()-1){
				firstC = new CNode(str.charAt(counter), currNode);
			}
		}
	} //end replaceBuilderBeginning


	//valid and no special cases
	private void replaceBuilder(int counter, int start, int end, String str, boolean endReached, CNode currNode, CNode nodeBefore, CNode nodeAfter){
		//runs until reach node before end
		//gain access to the nodes before start and end
		if(!endReached){
			//counter keeps track of the position within MSB inside this if conditional
			if(counter < end-1){ 
				if(counter == start-1){ //find the node before start
					nodeBefore = currNode; //node before references the node before the index start
				}
				//move to next node
				replaceBuilder(counter+1, start, end, str, endReached, currNode.next, nodeBefore, nodeAfter);
			}
			else if(counter == end-1){
				//reset counter to 0, pass endReached as true, move to next node
				replaceBuilder(0, start, end, str, true, nodeBefore, nodeBefore, currNode.next); 
			}		
		}
		//else runs if haven't reached node before offset
		//insert str into the MSB
		else{
			//counter keeps track of the position within str inside
			if(counter < str.length()-1){
				CNode newNode = new CNode(str.charAt(counter)); //make a new node
				currNode.next = newNode; //attatch the new node 
				//currNode = newNode
				replaceBuilder(counter+1, start, end, str, endReached, newNode, nodeBefore, nodeAfter); 
			}
			//base case, insert last char from str
			else if(counter == str.length()-1){
				CNode newNode = new CNode(str.charAt(counter)); //make a new node
				currNode.next = newNode; //attatch the new node
				newNode.next = nodeAfter; //reattach to other end of SB
			}
		}
	} //end replaceBuilder


//---------------------------------------------------------------------------------------------------------------------------------------------------------
	// Reverse the characters in the current MyStringBuilder2 and then
	// return the current MyStringBuilder2.
	public MyStringBuilder2 reverse()     //compare this to rec5 solution
	{
		reverseBuilder(null, firstC, firstC.next);

		return this;
	}

	private void reverseBuilder(CNode beforeCurr, CNode currNode, CNode afterCurr){
		if(afterCurr != null){
			currNode.next = beforeCurr; //currNode references the node before it
			beforeCurr = currNode;      // beforeCurr is now currNode
			currNode = afterCurr;       // currNode is now afterCurr
			afterCurr = currNode.next;  // afterCurr is the node after currNode
			reverseBuilder(beforeCurr, currNode, afterCurr);
		}
		else{
			lastC = firstC;
			firstC = currNode; 
			firstC.next = beforeCurr;
		}
	}

	//-----------------------------------------------------------------------------------------------------------------------------------------------------



	// Return as a String the substring of characters from index "start" to
	// index "end" - 1 within the current MyStringBuilder2
	public String substring(int start, int end)
	{
		int substringLength = (end-start);
		//create charArray to store substring
		char [] charArray = new char[substringLength];  
		
		//throw exception if arguments invalid
		if(start >= length || start < 0 || start >= end || end > length){        
			throw new IndexOutOfBoundsException("Illegal position given to substring operation. ");
		}
		// substring starting at beginning
		if(start == 0){
			builderSubstringBeginning(0, substringLength, charArray, firstC);
		}
		//substring when start != 0
		else{
			builderSubstring(0, start, substringLength, false, charArray, firstC);
		}

		return new String(charArray);
	}


	//substring starting at beginning
	//put characters into charArray
	private void builderSubstringBeginning(int counter, int subLength, char[] cArray, CNode currNode){
		if(counter < subLength){
			cArray[counter] = currNode.data;
			builderSubstringBeginning(counter+1, subLength, cArray, currNode.next);
		}
	}


	//substring when start != 0
	private void builderSubstring(int counter, int start, int subLength, boolean startReached, char[] cArray, CNode currNode){
		//runs until node before start is reached
		//counter keeps track of position in MSB
		if(!startReached){
			if(counter < start-1){
				builderSubstring(counter+1, start, subLength, startReached, cArray, currNode.next); // not at start-1, move to next node
			}
			else if(counter == start-1){
				builderSubstring(0, start, subLength, true, cArray, currNode.next);// at start-1, reset counter
			}
		}
		//begin collecting char from substring
		//counter keeps track of position in substring
		else{
			if(counter < subLength){
				cArray[counter] = currNode.data; //put char into cArray
				builderSubstring(counter+1, start, subLength, startReached, cArray, currNode.next); //move to next char
			}
		}
	}


//-----------------------------------------------------------------------------------------------------------------------------------------------------


	// Return the entire contents of the current MyStringBuilder2 as a String
	public String toString()
	{
		char [] c = new char[length];
		getString(c, 0, firstC);
		return (new String(c));
	}


	// Here we need the char array to store the characters, the pos value to
	// indicate the current index in the array and the curr node to access
	// the data in the actual MyStringBuilder2. Note that these rec methods
	// are private – the user of the class should not be able to call them.
	private void getString(char [] c, int pos, CNode curr){
		if (curr != null){ // Not at end of the list
			c[pos] = curr.data; // put next char into array
			getString(c, pos+1, curr.next); // recurse to next node and
		} // next pos in array
	}

//-----------------------------------------------------------------------------------------------------------------------------------------------------



	// Find and return the index within the current MyStringBuilder2 where
	// String str LAST matches a sequence of characters within the current
	// MyStringBuilder2.  If str does not match any sequence of characters
	// within the current MyStringBuilder2, return -1.  Think carefully about
	// what you need to do for this method before implementing it.  For some
	// help with this see the Assignment 3 specifications.
	public int lastIndexOf(String str)
	{
		int indexReturn = -1;

		if(str.length() <= length && str.length() > 0){ //only run if
			indexReturn = getLastIndexOf(0, str, firstC);
		}
		return indexReturn;
	}


	//find potential index where str could be located in MSB
	//returns the last index where a match is found, if no match returns -1
		//builderPosition keeps track of the location in MSB
	private int getLastIndexOf(int builderPosition, String strGoal, CNode currNode){  		
        int strIndex = length-strGoal.length(); //keeps track of index where strGoal found 

        if(builderPosition <= (length-strGoal.length()) ){  // doesn't run if, length of strGoal is > distance to end of builder
            
            strIndex = getLastIndexOf(builderPosition+1, strGoal, currNode.next); //recursively get to end
            
            if(strIndex == builderPosition){  //if counter and strIndex don't match (return a higher counter because solved), will not run                
                if(currNode.data == strGoal.charAt(0)){ //initial char found 
                	boolean found = false;
                	if(strGoal.length() == 1){ //if String is a single char, already found the last index
                		found = true;
                	}
                	else{
                    	found = compareStringsEnd(0, strGoal, currNode); 
                	}
                    
                    if(found){  // if match found, goal reached
                        return builderPosition;  // index in MSB will be returned to strIndex
                        						 // strIndex will no longer match builderPosition, so the inner if will be skipped, and strIndex will be returned
                    }
                }
                strIndex--;
            }
        }
        
        //if no match has been found, then strIndex decremented to -1

        return strIndex;

	} //end getLastIndexOf


	//return boolean telling whether the sequence of char in MSB is equivalent to str
		//strGoalPos keeps track of the location in str
		//currNode keeps track of the node to which a char is being compared
	private boolean compareStringsEnd(int strGoalPos, String strGoal, CNode currNode){

		if(strGoalPos < strGoal.length()){ //checking if strings match
			if(strGoal.charAt(strGoalPos) != currNode.data){  // if chars don't match, return false
				 return false;
			}
			else
				return compareStrings(strGoalPos+1, strGoal, currNode.next);  //continue comparing if chars match
			
		}

		return true; //return true if str is matched
	}


//-----------------------------------------------------------------------------------------------------------------------------------------------------

	
	// Find and return an array of MyStringBuilder2, where each MyStringBuilder2
	// in the return array corresponds to a part of the match of the array of
	// patterns in the argument.  If the overall match does not succeed, return
	// null.  For much more detail on the requirements of this method, see the
	// Assignment 3 specifications.
	public MyStringBuilder2 [] regMatch(String [] pats)
	{
		MyStringBuilder2 [] sbArray = new MyStringBuilder2[pats.length]; //create array to store MSB objects 
		makeSubArrays(sbArray, 0);//initialize objects in the array

		boolean match = findPats(0, pats, sbArray, firstC); //returns a boolean telling whether overall match succeeded

		if(match){
			return sbArray;
		}

		return null;
	}


	//initialize the MSB objects within sbArray
	private MyStringBuilder2 [] makeSubArrays(MyStringBuilder2 [] sbArray, int index){
		if(index < sbArray.length-1){
				sbArray = makeSubArrays(sbArray, index+1);
		}

		sbArray[index] = new MyStringBuilder2(); //initializing MSB
		return sbArray;
	}



	//return a boolean value telling whether overall match can succeed
	//the parameter locPats is the location in pats (which string is being searched)
	//the parameter locPats can also be used to place nodes in the correct index/stringBuilder of sbArray
	private boolean findPats(int locPats, String [] pats, MyStringBuilder2 [] sbArray, CNode currNode){
		boolean charFound = charInPats(0, locPats, pats, currNode);  //locPats will always be 0 here?
		boolean solved = false;


		//if the node data matches the first char of pats[locPats]
		if(charFound){  //currNode.data == pats[locPats].charAt(0)


			CNode newNode = new CNode(currNode.data);
			sbArray[locPats].append(newNode.data);
			solved = matchStringPat(locPats, pats, sbArray, currNode.next); //using currNode.next, so must only run matchStringPat if currNode.next != null //staying in same locPats
	
		}

		if(solved){  //base case solved, matching patterns found
			return true;
		}
		else if(charFound){
			sbArray[locPats].deleteCharAt(sbArray[locPats].length-1); // remove the node you added, it did not lead to solution  //if no match was found, skip this step
		}

		if(currNode.next == null){ //base case no solutioin found			
			return false;  //return false if the next node is null, match not found in list
		}

		//character not found, haven't started matching yet
		return findPats(locPats, pats, sbArray, currNode.next); //recursively try findPats() on next node 
		
	} //end findPats



	//This function tries to match the current pats string with the longest possible sequence of char from the MSB
	//returns true if can find sequences of char to match each String in pats, or returns false if match fails
	private boolean matchStringPat(int locPats, String [] pats, MyStringBuilder2 [] sbArray, CNode currNode){
		//traversing length of MSB
		if(currNode != null){ 
			boolean charFound = charInPats(0, locPats, pats, currNode); //checking if currNode could match current String
			boolean answer = false;  

			//character is found in pats[locPats]
			if(charFound){
				
				CNode newNode = new CNode(currNode.data);
				sbArray[locPats].append(newNode.data);  //make a new node for MSB, inside the sbArray
				
				if(currNode.next == null && (locPats == pats.length-1) ){
					return true;  //solved: return true if match successful for last node, and matching final pats String 
				}
				else{
					answer = matchStringPat(locPats, pats, sbArray, currNode.next); //continue matching current string
				}
			}


			//move to matching next pattern
			//
			//at END of list but haven't matched last pattern, need to delete char and then compare next pattern before trying to compare
			if(currNode.next == null && (locPats == pats.length-1)){
				sbArray[locPats].deleteCharAt(sbArray[locPats].length-1); // remove the node that was added, it did not lead to solution
				// answer = nextStringPat(locPats+1, pats, sbArray, currNode); //try comparing same node to next pats String  //not needed but it will not ruin the program
			}
			//character not found, matched previous String, but NOT matching last pattern
			else if( (!charFound && locPats < pats.length-1)){  
				//try finding character in next String pattern from pats
				answer = nextStringPat(locPats+1, pats, sbArray, currNode); //try comparing same node to next pats String
			}


			//base cases
			//
			//base case: no solution -> char was found, but answer returned false
			if(charFound && (!answer) ){ // if answer returns as true from one of the above conditionals, will this affect it
				sbArray[locPats].deleteCharAt(sbArray[locPats].length-1); // remove the node you added, it did not lead to solution
				answer =  nextStringPat(locPats+1, pats, sbArray, currNode);// try matching next string pattern
			}


			//base case: solution found -> character not found, and matching last pattern in pats
			if(!charFound && locPats == pats.length-1 && sbArray[locPats].length != 0){				
				return true; //solved: char not found, have already matched final pats String
			}

			return answer; 
		}


		//if currNode == null and have matched last pats, return true
		if(currNode == null && (sbArray[sbArray.length-1].length > 0) ){
			return true;
		}
		//else return false
		return false;

	}// end matchStringPat



	//this function checks to see if we can begin matching the next String in pats
	//if can try to match, it will call match Strings pat, returns true if can match
	//returns false if failure to match
	private boolean nextStringPat(int locPats, String [] pats, MyStringBuilder2 [] sbArray, CNode currNode){
		boolean charFound = charInPats(0, locPats, pats, currNode);
		boolean answer = false;  

		if(charFound){
			CNode newNode = new CNode(currNode.data);
			sbArray[locPats].append(newNode.data);  // add new node to current sbArray MSB

			
			if(currNode.next != null){
				answer = matchStringPat(locPats, pats, sbArray, currNode.next); //trying to match a MSB pattern to the Sting from pats
			}
			else if (locPats == (pats.length-1)){
				return true;
			}
		}

		if(charFound && (answer == false)){
			sbArray[locPats].deleteCharAt(sbArray[locPats].length-1); // remove the node you added, it did not lead to solution
		}

		return answer;

	} //end nextStringPat


	
	//Search through the current String sub-array to see if the current MSB node data is contained
	//if match found return true, if not found return false
	private boolean charInPats(int index, int locPats, String [] pats, CNode currNode){
		String currPattern = pats[locPats];

		if(index < currPattern.length()){  //only perform if index for sub-array is valid

			if(currPattern.charAt(index) == currNode.data){
				return true;  // return true if the current node.data is found in current sub-array
			}
			else{
				return charInPats(index+1, locPats ,pats, currNode);  //char not at current sub-array location, compare node.data to next location
			}
		}

		return false;  //char not found
	}


	//-----------------------------------------------------------------------------------------------------------------------------------------------------


	
	// You must use this inner class exactly as specified below.  Note that
	// since it is an inner class, the MyStringBuilder2 class MAY access the
	// data and next fields directly.
	private class CNode
	{
		private char data;
		private CNode next;

		public CNode(char c)
		{
			data = c;
			next = null;
		}

		public CNode(char c, CNode n)
		{
			data = c;
			next = n;
		}
	}



	//==========================================================================================================================================================
	//EXTRA CREDIT BELOW
	//==========================================================================================================================================================

	//allIndexOf finds and returns each index where a string appears in an ArrayList of Integer
	//if the String is not found, return an arrayList with only -1
	public ArrayList<Integer> allIndexOf(String str){
		ArrayList<Integer> indexReturn = new ArrayList<Integer>();

		if(str.length() <= length && str.length() > 0){
			getAllIndexOf(0, str, indexReturn, firstC);
		}

		if(indexReturn.size() == 0) 
			indexReturn.add(-1);  //add -1 to indexReturn if no index was found

		return indexReturn;
	}

	//find potential index where str could be located in MSB
	//return the all indecies where a match is found, if no match then return empty ArrayList
		//builderPosition keeps track of the location in MSB
	private void getAllIndexOf(int builderPosition, String str, ArrayList<Integer> indexReturn, CNode currNode){
		if(builderPosition <= length-str.length()){

			if(str.charAt(0) == currNode.data){
				if(str.length() == 1){ //if String is a single char, match found
					indexReturn.add(builderPosition); //adding builderPosition to indexReturn
				}
				else{
					compareStrings(builderPosition, 1, str, indexReturn, currNode.next);  //test if string found at index, if found, index is returned					
				}
			}
			getAllIndexOf(builderPosition+1, str, indexReturn, currNode.next); //test next node
		}

	}

	//return boolean telling whether the sequence of char in MSB is equivalent to str
		//strPosition keeps track of the location in str
		//builderPosition keeps track of the index where the sequence begins in MSB
		//iterNode keeps track of the node to which a char is being compared
	private void compareStrings(int builderPosition, int strPosition, String str, ArrayList<Integer> indexReturn, CNode iterNode){
		if(strPosition == str.length()-1){ //at last char of Str
			if(str.charAt(strPosition) == iterNode.data){ //if last char is the same, found match
				indexReturn.add(builderPosition);  //adding builderPosition to indexReturn		
			}
		}
		else if(str.charAt(strPosition) == iterNode.data){ //if char are equal, compare to next node
			compareStrings(builderPosition, strPosition+1, str, indexReturn, iterNode.next);
		}
		
	}
	//==========================================================================================================================================================
	//END OF EXTRA CREDIT 
	//==========================================================================================================================================================

}// end class MyStringBuilder2


