//David Reidenbaugh 
//CS 0445 Fall 2020

/*
MyStringBuilder has similar functionality to the StringBuilder class, but the main difference is
it stores a linked list of characters in its implementation.  
MyStringBuilder has functionality to append a String, a char, an array of char, or another MyStringBuilder to the current StringBuilder.
It also has functionality to delete a char at a specific index or a sequence of characters between two indexes.
Additionaly, it has functionality to insert a char, a String, an array of char, or another MyStringbuilder at a specific index.
In addition to these methods, MyStringBuilder can also use charAt() to get the char at a specific location,
indexOf() to to check if an object contains a specific String, 
substring() to get the sequence of char between two indexes,
replace() to replace a sequence of char with a new String,
and reverse() to reverse the characters in an object.
MyStringBuilder also has standard length() and toString() methods.
*/

// Read this class and its comments very carefully to make sure you implement
// the class properly.  Note the items that are required and that cannot be
// altered!  Generally speaking you will implement your MyStringBuilder using
// a singly linked list of nodes.  See more comments below on the specific
// requirements for the class.

// For more details on the general functionality of most of these methods, 
// see the specifications of the similar method in the StringBuilder class.  
public class MyStringBuilder
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

	// Create a new MyStringBuilder initialized with the chars in String s
	// Note: This method is implemented for you.  See A2Help.java
	public MyStringBuilder(String s)
	{
		if (s == null || s.length() == 0) // Special case for empty String
		{					 			  // or null reference
			firstC = null;
			lastC = null;
			length = 0;
		}
		else
		{
			// Create front node to get started
			firstC = new CNode(s.charAt(0));
			length = 1;
			CNode currNode = firstC;
			// Create remaining nodes, copying from String.  Note
			// how each new node is simply added to the end of the
			// previous one.  Trace this to see what is going on.
			for (int i = 1; i < s.length(); i++)
			{
				CNode newNode = new CNode(s.charAt(i));
				currNode.next = newNode;
				currNode = newNode;
				length++;
			}
			lastC = currNode;
		}
	}

	// Create a new MyStringBuilder initialized with the chars in array s
	public MyStringBuilder(char [] s)
	{
		if(s == null || s.length == 0){  // special case for empty array or null reference
			firstC = null;
			lastC = null;
			length = 0;
		}
		//create nodes from array of char
		else{
			firstC = new CNode(s[0]); //create front node
			length = 1;
			CNode currNode = firstC;

			//create remaining nodes
			for(int i=1; i < s.length; i++){
				CNode newNode = new CNode(s[i]);
				currNode.next = newNode;
				currNode = newNode;
				length++;
			}
			lastC = currNode;
		}
	}
	
	// Copy constructor -- make a new MyStringBuilder from an old one.  Be sure
	// that you make new nodes for the copy.
	public MyStringBuilder(MyStringBuilder old)
	{
		if(old.length == 0){ // special case for empty StringBuilder
			firstC = null;
			lastC = null;
			length = 0;
		}
		//copy nodes from old
		else{
			CNode oldCurrNode = old.firstC;  //front node of old
			firstC = new CNode(old.firstC.data); //create front node for new MyStringBuilder
			CNode currNode = firstC;  
			
			//create remaining nodes
			while(oldCurrNode.next != null){
				oldCurrNode = oldCurrNode.next;
				CNode newNode = new CNode(oldCurrNode.data);
				currNode.next = newNode;
				currNode = newNode;
			}

			lastC = currNode;
			length = old.length;
		}
	}
	
	// Create a new empty MyStringBuilder
	public MyStringBuilder()
	{
		firstC = null;
		lastC = null;
		length = 0;
	}
	
	// Return the entire contents of the current MyStringBuilder as a String
	// For this method you should do the following:
	// 1) Create a character array of the appropriate length
	// 2) Fill the array with the proper characters from your MyStringBuilder
	// 3) Return a new String using the array as an argument, or
	//    return new String(charArray);
	// Note: This method is implemented for you.  See A2Help.java
	public String toString()
	{
		char [] c = new char[length];  // Make array of correct size 
		int i = 0;
		CNode currNode = firstC;
		while (currNode != null)   	// Iterate through the MyStringBuilder
		{							// putting the characters into the
			c[i] = currNode.data;	// correct positions in the array
			i++;
			currNode = currNode.next;
		}
		return new String(c);	// return a new String from the array
	}

	// Append MyStringBuilder b to the end of the current MyStringBuilder, and
	// return the current MyStringBuilder.  Be careful for special cases!
	public MyStringBuilder append(MyStringBuilder b)
	{
		CNode currNode;

		//if there is nothing in b
		if(b.length == 0){
			return this; // nothing changes
		}

		//the current MyStingBuilder is empty
		if(length == 0){        
			CNode bCurrNode = b.firstC; 
			firstC = new CNode(b.firstC.data);  //create front node
			currNode = firstC; 
			
			while(bCurrNode.next != null){
				bCurrNode = bCurrNode.next;
				CNode newNode = new CNode(bCurrNode.data);
				currNode.next = newNode;
				currNode = newNode;
			}
		}
		// both MyStringBuilders contain something
		else{
			CNode bCurrNode = b.firstC;  
			lastC.next = new CNode(b.firstC.data);
			currNode = lastC.next;
			
			while(bCurrNode.next != null){
				bCurrNode = bCurrNode.next;
				CNode newNode = new CNode(bCurrNode.data);
				currNode.next = newNode;
				currNode = newNode;
			}
		}

		lastC = currNode;
		length += b.length;
		return this;
	}

	// Append String s to the end of the current MyStringBuilder, and return
	// the current MyStringBuilder.  Be careful for special cases!
	public MyStringBuilder append(String s)
	{
		CNode currNode;

		//nothing in s
		if(s == null || s.length() == 0){
			return this;  //nothing changes
		}
		// the current MyStringBuilder is empty
		if(length == 0){                               
			firstC = new CNode(s.charAt(0));
			currNode = firstC;

			for (int i = 1; i < s.length(); i++){
				CNode newNode = new CNode(s.charAt(i));
				currNode.next = newNode;
				currNode = newNode;
			}
		}
		//both MyStringBuilders contain something
		else{

			lastC.next = new CNode(s.charAt(0));
			currNode = lastC.next;

			for (int i = 1; i < s.length(); i++)
				{
					CNode newNode = new CNode(s.charAt(i));
					currNode.next = newNode;
					currNode = newNode;
				}
		}

		lastC = currNode;
		length += s.length();
		return this;
	}

	// Append char array c to the end of the current MyStringBuilder, and
	// return the current MyStringBuilder.  Be careful for special cases!
	public MyStringBuilder append(char [] c)
	{
		CNode currNode;
		//special case if c is empty or null refrence
		if(c == null || c.length == 0){
			return this;    //nothing changes
		}
		//the current MyStringBuilder is empty
		if(length == 0){
			firstC = new CNode(c[0]); // create front node
			currNode = firstC;

			for(int i=1; i < c.length; i++){
				CNode newNode = new CNode(c[i]);
				currNode.next = newNode;
				currNode = newNode;
			}
		}
		//the current MyStringBuilder contains something
		else{
			lastC.next = new CNode(c[0]);
			currNode = lastC.next;

			for(int i=1; i < c.length; i++){
				CNode newNode = new CNode(c[i]);
				currNode.next = newNode;
				currNode = newNode;
			}
		}

		lastC = currNode;
		length += c.length;
		return this;
	}


	// Append char c to the end of the current MyStringBuilder, and
	// return the current MyStringBuilder.  Be careful for special cases!
	public MyStringBuilder append(char c)
	{
		//the current MyStringBuilder is empty
		if(length == 0){
			firstC = new CNode(c);
			lastC = firstC;
		}
		//current MyStringBuilder not empty
		else{
			lastC.next = new CNode(c);
			lastC = lastC.next;
			
		}
		length++;
		return this;
	}


	// Return the character at location "index" in the current MyStringBuilder.
	// If index is invalid, throw an IndexOutOfBoundsException.
	public char charAt(int index)
	{
		// throw exception if index invalid
		if(index >= length || index < 0){
			throw new IndexOutOfBoundsException("Illegal position given to charAt operation. ");
		}
		
		CNode currNode = firstC;
		for(int i=0; i < index; i++){
			currNode = currNode.next;
		}
		return currNode.data;
	}


	// Delete the characters from index "start" to index "end" - 1 in the
	// current MyStringBuilder, and return the current MyStringBuilder.
	// If "start" is invalid or "end" <= "start" do nothing (just return the
	// MyStringBuilder as is).  If "end" is past the end of the MyStringBuilder, 
	// only remove up until the end of the MyStringBuilder. Be careful for 
	// special cases!
	public MyStringBuilder delete(int start, int end)  //!!!!!!! should i reset instance variables if empty!!!!!!
	{
		CNode currNode = firstC;

		//do nothing if invalid argument
		if(start >= length || start < 0 || start > end){        
			return this;  // nothing changes
		}

		// deletes to the end if end > length
		if(end > length){
			for(int i=1; i < start; i++){
				currNode = currNode.next;
			}
			lastC = currNode;
			lastC.next = null;
			length -= ((length-1) - start);
		}
		// special case if start is 0
		else if(start == 0){
			for(int i=1; i < end; i++){
				currNode = currNode.next;
			}
			firstC = currNode.next;	
			length -= (end - start);		
		}
		// arguments valid and no special cases
		else{
			for(int i=1; i < start; i++){
				currNode = currNode.next;
			}
			CNode beforeStart = currNode;

			for(int i=start; i < end; i++){
				currNode = currNode.next;
			}
			beforeStart.next = currNode.next;
			length -= (end - start);
		}

		if(length == 0){  //reset lastC if empty
			lastC = null;
		}

		return this;
	}

	// Delete the character at location "index" from the current
	// MyStringBuilder and return the current MyStringBuilder.  If "index" is
	// invalid, do nothing (just return the MyStringBuilder as is).
	// Be careful for special cases!
	public MyStringBuilder deleteCharAt(int index) 
	{												
		CNode currNode = firstC;

		// stays the same if argument is invalid
		if(index > length-1 || index < 0){
			return this;  //nothing changes
		}
		// special case if start is 0
		else if(index == 0){
			firstC = currNode.next;
			length--;
		}
		//index valid and > 0
		else{
			for(int i=1; i < index; i++){
				currNode = currNode.next;
			}
			CNode beforeIndex = currNode;
			CNode afterIndex = currNode.next.next;
			
			beforeIndex.next = afterIndex;

			if(index == length-1){
				lastC = currNode;
			}

			length--;
		}

		if(length == 0){ //reset lastC if empty
			lastC = null;
		}

		return this;
	}

	// Find and return the index within the current MyStringBuilder where
	// String str first matches a sequence of characters within the current
	// MyStringBuilder.  If str does not match any sequence of characters
	// within the current MyStringBuilder, return -1.  Think carefully about
	// what you need to do for this method before implementing it.
	public int indexOf(String str)
	{
		//j is used to keep track of position in str
		//i is used to keep track of position in the current MyStringBuilder
		CNode currNode = firstC;

		// only do if str.length() <= length
		if(str.length() <= length){
			for(int i=0; i <= length-str.length(); i++){
				
				CNode iterNode = currNode;
				for(int j=0; j<str.length(); j++){
					if(j == str.length()-1){
						if(str.charAt(j) == iterNode.data){ //solved
							return i;
						}
					}
					else if(str.charAt(j) == iterNode.data){ //move to next node
						iterNode = iterNode.next;
					}
					
					else{                 //no match at current SB index
						j = str.length(); //set j to value where inner loop wont continue
					}
				}
				currNode = currNode.next; //increment the node being compared to str.charAt(0)
			}
		}
		return -1;
	}


	// Insert String str into the current MyStringBuilder starting at index
	// "offset" and return the current MyStringBuilder.  if "offset" == 
	// length, this is the same as append.  If "offset" is invalid
	// do nothing.
	public MyStringBuilder insert(int offset, String str)
	{
		CNode currNode;

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
			CNode oldFirstC = firstC;
			firstC = new CNode(str.charAt(0));
			currNode = firstC;

			for(int i=1; i < str.length(); i++){
				CNode newNode = new CNode(str.charAt(i));
				currNode.next = newNode;
				currNode = newNode;
			}

			currNode.next = oldFirstC;
			length += str.length(); 
		}
		//offset is valid and between 0 and length-1 inclusive
		else{
			currNode = firstC;

			for(int i=1; i < offset; i++){
				currNode = currNode.next;
			}

			CNode nodeAfter = currNode.next;

			for(int i=0; i < str.length(); i++){
				CNode newNode = new CNode(str.charAt(i));
				currNode.next = newNode;
				currNode = newNode;
				//length++;
			}

			currNode.next = nodeAfter;
			length += str.length();
		}

		return this;
	}


	// Insert character c into the current MyStringBuilder at index
	// "offset" and return the current MyStringBuilder.  If "offset" ==
	// length, this is the same as append.  If "offset" is invalid, 
	// do nothing.
	public MyStringBuilder insert(int offset, char c) 
	{
		CNode currNode;
		//offset is invalid
		if(offset > length || offset < 0){
			//nothing happens
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
			currNode = firstC;

			for(int i=1; i < offset; i++){
				currNode = currNode.next;
			}
			CNode newNode = new CNode(c);
			CNode nodeAfter = currNode.next;

			currNode.next = newNode;
			newNode.next = nodeAfter;
			length++;
		}

		return this;
	}


	// Insert char array c into the current MyStringBuilder starting at index
	// index "offset" and return the current MyStringBuilder.  If "offset" is
	// invalid, do nothing.
	public MyStringBuilder insert(int offset, char [] c)
	{
		CNode currNode;
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
			CNode oldFirstC = firstC;
			firstC = new CNode(c[0]);
			currNode = firstC;

			for(int i=1; i < c.length; i++){
				CNode newNode = new CNode(c[i]);
				currNode.next = newNode;
				currNode = newNode;
			}

			currNode.next = oldFirstC;
		}
		// offset is valid and between 0 and length-1 inclusive
		else{
			currNode = firstC;

			for(int i=1; i < offset; i++){
				currNode = currNode.next;
			}

			CNode nodeAfter = currNode.next;

			for(int i=0; i < c.length; i++){
				CNode newNode = new CNode(c[i]);
				currNode.next = newNode;
				currNode = newNode;
				//length++;
			}

			currNode.next = nodeAfter;
		}

		length += c.length; 
		return this;
	}

	// Return the length of the current MyStringBuilder
	public int length()
	{
		return this.length;
	}

	// Delete the substring from "start" to "end" - 1 in the current
	// MyStringBuilder, then insert String "str" into the current
	// MyStringBuilder starting at index "start", then return the current
	// MyStringBuilder.  If "start" is invalid or "end" <= "start", do nothing.
	// If "end" is past the end of the MyStringBuilder, only delete until the
	// end of the MyStringBuilder, then insert.  This method should be done
	// as efficiently as possible.  In particular, you may NOT simply call
	// the delete() method followed by the insert() method, since that will
	// require an extra traversal of the linked list.
	public MyStringBuilder replace(int start, int end, String str)  
	{
		CNode currNode = firstC;

		//return same MyStringBuilder
		if(start >= length || start < 0 || start >= end || str == null){        
			return this;//nothing happens
		}
		// deletes to the end if end > length
		else if(end > length){
			for(int i=1; i < start; i++){
				currNode = currNode.next;
			}
			for(int i=0; i < str.length(); i++){
				CNode newNode = new CNode(str.charAt(i));
				currNode.next = newNode;
				currNode = newNode;
			}
			lastC = currNode;
			lastC.next = null;
			length = length + str.length() - ((length-1)-start);
		}
		// special case if start is 0
		else if(start == 0){
			for(int i=1; i < end; i++){
				currNode = currNode.next;
			}
			CNode endReplace = currNode.next;

			firstC = new CNode(str.charAt(0));	
			for(int i=1; i < str.length(); i++){
				CNode newNode = new CNode(str.charAt(i));
				currNode.next = newNode;
				currNode = newNode;
			}
			currNode.next = endReplace;
			length = length + str.length() - (end-start);
		}
		//valid and no special cases
		else{
			//find node before start
			for(int i=1; i < start; i++){
				currNode = currNode.next;
			}
			//store the node before start
			CNode beforeStart = currNode;
			//find node end-1
			for(int i=start; i < end; i++){
				currNode = currNode.next;
			}
			//sotre node at end
			CNode endReplace = currNode.next;
			//make the node before start the currNode
			currNode = beforeStart;
			//put each char in str into a node
			//these are inserted into MyStringBuilder after the node beforeStart
			for(int i=0; i < str.length(); i++){
				CNode newNode = new CNode(str.charAt(i));
				currNode.next = newNode;
				currNode = newNode;
			}
			// link the end of str to the node at index end
			currNode.next = endReplace;
			length = length + str.length() - (end-start);
		}

		return this;
	}

	// Reverse the characters in the current MyStringBuilder and then
	// return the current MyStringBuilder.
	public MyStringBuilder reverse()
	{
		CNode beforeCurr = null;
		CNode currNode = firstC;
		CNode afterCurr = currNode.next;

		while(afterCurr != null){  
			currNode.next = beforeCurr; //currNode references the node before it
			beforeCurr = currNode;      // beforeCurr is now currNode
			currNode = afterCurr;       // currNode is now afterCurr
			afterCurr = currNode.next;  // afterCurr is the node after currNode
		}
		lastC = firstC;
		firstC = currNode;
		firstC.next = beforeCurr;
		return this;
		
	}
	
	// Return as a String the substring of characters from index "start" to
	// index "end" - 1 within the current MyStringBuilder.  For this method
	// you should do the following:
	// 1) Create a character array of the appropriate length
	// 2) Fill the array with the proper characters from your MyStringBuilder
	// 3) Return a new String using the array as an argument, or
	//    return new String(charArray);
	public String substring(int start, int end)
	{
		int substringLength = (end-start);
		CNode currNode = firstC;
		//create charArray to store substring
		char [] charArray = new char[substringLength];  
		
		//throw exception if arguments invalid
		if(start >= length || start < 0 || start >= end || end > length){        
			throw new IndexOutOfBoundsException("Illegal position given to substring operation. ");
		}
		// substring starting at beginning
		//put characters into charArray
		if(start == 0){
			for(int i=0; i < substringLength; i++){
				charArray[i] = currNode.data;
				currNode = currNode.next;
			}
		}
		//substring when start != 0
		else{
			for(int i=1; i < start; i++){   
				currNode = currNode.next;
			}
			//put characters into charArray
			for(int i=0; i < substringLength; i++){
				currNode = currNode.next;
				charArray[i] = currNode.data;
			}
		}

		return new String(charArray);
	}

	// You must use this inner class exactly as specified below.  Note that
	// since it is an inner class, the MyStringBuilder class MAY access the
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
}

