// TO DO: add your implementation and JavaDocs.
/**
* A generic public class that implements a dynamic array to be used by other classes.
*@author Starling Devine
*@param <T> the generic for the class
**/
public class ThreeTenDynArray<T> {
	//default initial capacity / minimum capacity
	/**
	* A private constant int variable that represents the minimum capacity of 2 for the array.
	**/
	private static final int MIN_CAPACITY = 2;
	
	//underlying array for storage -- you MUST use this for credit!
	//Do NOT change the name or type
	/**
	*An array of generic type T that represents the data stored in the class.
	**/
	private T[] data;

	// ADD MORE PRIVATE MEMBERS HERE IF NEEDED!
	/**
	*A private variable that represents how much of the availible space is being used.
	**/
	private int size;
	/**
	* A defult constructor that initalizes the capacity of data array to be the MIN_CAPACITY,
	*and the size to be zero.
	**/
	@SuppressWarnings("unchecked")
	public ThreeTenDynArray() {
		// Constructor
		
		// Initial capacity of the storage should be MIN_CAPACITY
		// Hint: Can't remember how to make an array of generic Ts? It's in the textbook...
		this.data = (T[]) new Object[MIN_CAPACITY];
		this.size = 0;

	}
	/**
	* A constructor that initializes the capacity of data to be initCapacity. 
	*
	*@param initCapacity an integer that represents the initail apasity of the data
	*@throw IllegalArgumentException if initCapacity is smaller than the MIN_CAPACITY of 2
	**/
	@SuppressWarnings("unchecked")
	public ThreeTenDynArray(int initCapacity) {
		// Constructor
		// Initial capacity of the storage should be initCapacity.
		// - Throw IllegalArgumentException if initCapacity is smaller than 
		//   MIN_CAPACITY 2
		// - Use this _exact_ error message for the exception
		//   (quotes are not part of the message):
		//    "Capacity must be at least 2!"
		if(initCapacity < MIN_CAPACITY)
			throw new IllegalArgumentException("Capacity must be at least 2!");
		this.data = (T[]) new Object[initCapacity];
		this.size = 0;
	}
	/**
	* A getter method for size.
	*@return int size the current size of the array
	**/
	public int size() {	
		// Report the current number of elements
		// O(1)
		return size; //default return, remove/change as needed
	}  
	/**
	*A getter method for capacity.
	*
	*@return int capacity the ammount of space availible in the array
	**/
	public int capacity() { 
		// Report max number of elements of the current storage
		// (subject to change since this is a _dynamic_ )
		
		// O(1)
		return data.length; //default return, remove/change as needed
	}
	
	/**
	*A public T method that will replace the value at the index with the given value.
	*
	*@throw IndexOutOfBoundsException if the index is not valid
	*@throw IllegalArgumentException if the value is null 
	*@param index the location where the new data will be placed
	*@param value the data that will be added to the array
	*@return the value that was replaced
	**/
	public T set(int index, T value) {
		// Replace the item at the given index to be the given value.
		// Return the old item at that index.
		// Note: You cannot add new items (i.e. cannot increase size) with this method.
		
		// O(1)
		
		// - Throw IndexOutOfBoundsException if index is not valid
		// - Use this code to produce the correct error message for
		// the exception (do not use a different message):
		//	  "Index: " + index + " out of bounds!"
		if(index <0 || index > size){
			throw new IndexOutOfBoundsException("Index: " + index + " out of bounds!");
		}
		// - Throw IllegalArgumentException if value is null. 
		// - Use this _exact_ error message for the exception 
		//  (quotes are not part of the message):
		//    "Cannot include null values!"
		if(value == null)
			throw new IllegalArgumentException("Cannot include null values!");
		T oldVal = data[index];
		data[index] = value;
		return oldVal; //default return, remove/change as needed
		
	}
	/**
	* A getter method that return the value at the given index.
	*
	*@param index an integer representing the location of the wanted value
	*@return T the value at the given index
	**/
	public T get(int index) {
		// Return the item at the given index
		
		// O(1)
		
		// Use the exception (and error message) described in set()
		// for invalid indicies.
		if(index<0 || index > size){
			throw new IndexOutOfBoundsException("Index: " + index + " out of bounds!");
		}
				
		return data[index]; //default return, remove/change as needed
	}
	
	/**
	* A void method that will add the given value to the end of the array, 
	*and increases the size by one.
	*
	*@param value the value of type T that will be added to the end of the array
	*@throw IllegalArgumentException if the value is null
	**/
	@SuppressWarnings("unchecked")
	public void append(T value) {
		// Append an element to the end of the storage.		
		// Double the capacity if no space available.
		// Code reuse! Consider using setCapacity (see below).
		
		// For a null value,  use the same exception and message 
		// as set(). 
		
		// You can assume we will never need to grow the capacity to a value 
		// beyond Integer.MAX_VALUE/4.  No need to check or test that boundary 
		// value when you grow the capacity.
		
		// Amortized O(1)
		
		// - Throw IllegalArgumentException if value is null. 
		// - Use the same error message as set().
		if(value == null)
			throw new IllegalArgumentException("Cannot include null values!");
		boolean temp = setCapacity(size+1);
		data[size] = value;
		size++;
		//System.out.println("Size" + size);
		
	} 
	/**
	* A void method that insets the given value at the given index, 
	*shifting the rest of the data to the right and increasing the size by one. 
	* The capacity of the array will be doubled the size becomes greater than
	*the array's capacity
	*
	*@throw IndexOutOfBoundsException if the index is invalid
	*@throw IllegalArgumentException if the value is null
	*@param index the place where the value will be inserted
	*@param value the value that will be added
	**/
	@SuppressWarnings("unchecked")
	public void insert(int index, T value) {
		// Insert the given value at the given index and shift elements if needed. 
		// You _can_ append items with this method.
		// Double capacity if no space available. 
		// Assume the same as append() for the upper bound of capacity.
		// Code reuse! Consider using setCapacity (see below).
		
		// For an invalid index or a null value,  use the same exception and message 
		// as set(). However, remember that the condition of the exception is
		// different (a different invalid range for indexes).
		if(index <0 || index > size+1){
			throw new IndexOutOfBoundsException("Index: " + index + " out of bounds!");
		}
		if(value == null)
			throw new IllegalArgumentException("Cannot include null values!");
		//boolean temp = setCapacity(size+1);
        setCapacity(size+1);
		if(index == size)
			this.set(index,value);
		size++;
		T tempVal = this.set(index,value);
		for(int i=index+1; i< size; i++){
			tempVal = this.set(i,tempVal);
		}
		// O(N) where N is the number of elements in the storage
	} 
	
	/**
	* A T method that removes the given value at the given index, 
	*shifting the rest of the data to the left and decreasing the size by 1. 
	*The capacity of the array will be halved if the size becomes lesser than
	*1/4th's the array's capacity.
	*
	*@throw IndexOutOfBoundsException if the index is invalid
	*@throw IllegalArgumentException if the value is null
	*@param index the place where the value will be removed
	*@return T the value that was removed
	**/
	@SuppressWarnings("unchecked")
	public T remove(int index) {
		// Remove and return the element at the given index. Shift elements
		// to ensure no gap. Throw an exception when there is an invalid
		// index (see set(), get(), etc. above).
		if(index <0 || index >= size){
			throw new IndexOutOfBoundsException("Index: " + index + " out of bounds!");
		}
		// Halve capacity (rounding down) of the storage if the number of elements falls
		// below or at 1/4 of the capacity. 
		// However, capacity should NOT go below MIN_CAPACITY.
		// Code reuse! Consider using setCapacity (see below).
		// O(N) where N is the number of elements currently in the storage
		boolean expand = setCapacity(index);
		T returnVal = this.get(index);
		size--;
		for(int i=index; i<size; i++){
			T tempVal = this.set(i,this.get(i+1));
			//System.out.println("TempVal " + tempVal);
		}
		
		return returnVal; //default return, remove/change as needed

	}  

	/**
	* A boolean method that will change the max number of items allowed in the array
	*if the newCapacity is greater than the MIN_CAPACITY and the current capacity of the data array.
	*The 
	*
	*@param newCapacity the desired new capacity of the array
	*@return boolean true if the capacity can be updated
	**/
	@SuppressWarnings("unchecked")
	public boolean setCapacity(int newCapacity) {
		// Change the max number of items allowed before next expansion to newCapacity.
		// No other changes to the current values in storage 
		// (i.e. they should all keep the same index).
		
		// Capacity should not be changed if:
		//   - newCapacity is below MIN_CAPACITY; or 
		//   - newCapacity is not large enough to accommodate current number of items
		T[] newData;
		if(newCapacity < MIN_CAPACITY || (newCapacity < data.length && newCapacity > data.length/4))
			return false;
		else if (newCapacity == data.length)
			return true;
		else if (newCapacity < data.length/4){
			newData = (T[]) new Object [size/2];
		}
		else{
			newData = (T[]) new Object [size*2];
		}
		// Return false if newCapacity cannot be applied; return true otherwise.
		// Special case: if newCapacity is identical to the current max number of items,
		// no need to change anything but still return true.
		
		// O(N) where N is the number of elements in the array
		
		for(int i=0; i<size; i++){
			newData[i] = data[i];
		}
		this.data = newData;

		return true; //default return, remove/change as needed
		
		
	}
	/**
	* A public method that returns the index of the first occurrence of value or -1 if not found.
	*
	*@param value the value you are looking to find 
	*@throw IllegalArgumentException if the value is null
	*@return int the index the value is found at or -1 if the value is not in the array
	**/
	public int firstIndexOf(T value) {
		// Return the index of the first occurrence of value or -1 if not found. 
		// NOTES: - Remember null is not a valid item in list.
		//        - Remember to use .equals for comparison of non-null values.
		// O(N) where N is the number of elements in the list
		if(value == null)
			throw new IllegalArgumentException("Cannot include null values!");
		for(int i =0; i < size; i++){
			if(this.get(i).equals(value))
				return i;
		}
		return -1; //default return, remove/change as needed
	}
	
	//******************************************************
	//*******     BELOW THIS LINE IS PROVIDED code   *******
	//*******             Do NOT edit code!          *******
	//*******		   Remember to add JavaDoc		 *******
	//******************************************************
	/**
	* An overriden toString method that will be used to display the contents
	*of the array within the class.
	*@return String the string version of the data array
	**/
	@Override
	public String toString() {
		//This method is provided. Add JavaDoc and comments.
		
		StringBuilder s = new StringBuilder("[");
		for (int i = 0; i < size(); i++) {
			s.append(get(i));
			if (i<size()-1)
				s.append(", ");
		}
		s.append("]");
		return s.toString().trim();
		
	}
	
	//******************************************************
	//*******     BELOW THIS LINE IS TESTING CODE    *******
	//*******      Edit it as much as you'd like!    *******
	//*******		Remember to add JavaDoc			 *******
	//******************************************************
	
	/**
	* A toString method that print out the details of ThreeTenDynArray for easy veiwing.
	*@return String the contents of ThreeTenDynArray
	**/
	public String toStringDebug() {
		//This method is provided for debugging purposes
		//(use/modify as much as you'd like), it just prints
		//out the ThreeTenDynArray details for easy viewing.
		StringBuilder s = new StringBuilder("ThreeTenDynArray with " + size()
			+ " items and a capacity of " + capacity() + ":");
		for (int i = 0; i < size(); i++) {
			s.append("\n  ["+i+"]: " + get(i));
		}
		return s.toString().trim();
		
	}
	/**
	* A main method that tests the methods of ThreeTenDynArray.
	*
	*@param args the user input if needed. 
	**/
	public static void main(String args[]){
		//These are _sample_ tests. If you're seeing all the "yays" that's
		//an excellend first step! But it does NOT guarantee your code is 100%
		//working... You may edit this as much as you want, so you can add
		//own tests here, modify these tests, or whatever you need!

		//create a ThreeTenDynArray of integers
		ThreeTenDynArray<Integer> nums = new ThreeTenDynArray<>();
		if((nums.size() == 0) && (nums.capacity() == MIN_CAPACITY)){
			System.out.println("Yay 1");
		}

		//append some numbers 
		for(int i = 0; i < 3; i++) {
			nums.append(i*3 % 2);
		}
		//uncomment to check details
		System.out.println(nums);
		//System.out.println(nums.size() +" "+ nums.get(2)+" "+nums.capacity()+ " ");
		//checking
		if(nums.size() == 3 && nums.get(2) == 0 && nums.capacity() == 4
			&& nums.firstIndexOf(1) == 1 && nums.firstIndexOf(0) == 0
			&& nums.firstIndexOf(6) == -1 ){
			System.out.println("Yay 2");
		}
		
		//create a ThreeTenDynArray of strings
		ThreeTenDynArray<String> title = new ThreeTenDynArray<>();
		
		//insert some strings
		title.insert(0,"using");
		title.insert(0,"problem");
		title.insert(1,"solving");
		title.insert(3,"c");
		
		//checking and replace
		if (title.get(0).equals("problem") && title.set(3,"java").equals("c") 
			&& title.size() == 4 && title.capacity() == 4){
			System.out.println("Yay 3");
		}
		
		title.insert(0,"&");
		title.insert(0,"structures");
		title.insert(0,"data");
		//uncomment to check details
		System.out.println(title);
		
		//delete 
		if (title.capacity() == 8 && title.remove(5).equals("using") &&
			title.remove(title.size()-1).equals("java")){
			System.out.println("Yay 4");
		}

		//change capacity 
		if (!title.setCapacity(4) && !title.setCapacity(1) 
			&& title.setCapacity(20) && title.capacity()==20){
			System.out.println("Yay 5");
		}
		
		//remove and shrinking
		if (title.remove(2).equals("&") && title.capacity()==10){
			System.out.println("Yay 6");
		}
				
		//exception checking
		try{
			title.append(null);
		}
		catch (IllegalArgumentException ex){
			if (ex.getMessage().equals("Cannot include null values!")){
				System.out.println("Yay 7");			
			}
		}
	}	
}