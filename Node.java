//Srinivas Kotha
//ssk170630

package Tickets;

public class Node {
	/*
	 * created variables for title, available,
	 * rented, and pointers for left and right
	 */
	private String title; 
	private int avail; 
	private int rented; 
	private Node left; 
	private Node right; 
	
	/*
	 * created a default constructor
	 */
	public Node()
	{
		title = " ";
		avail = 0;
		rented = 0;
		left = null;
		right = null;
		
	}
	

	/*
	 * created an overloaded constructor
	 */
	public Node(String title, int avail, int rented, Node left, Node right)
	{
		this.title = title;
		this.avail = avail;
		this.rented = rented;
		this.left = left;
		this.right = right;
	}
	
	//
	/*
	 * accessors(getters)
	 */
	
	public String getTitle()
	{
		return title;
	}
	
	public int getAvailable()
	{
		return avail;
	}
	
	public int getRented()
	{
		return rented;
	}
	
	public Node getLeft()
	{
		return left;
	}
	
	public Node getRight()
	{
		return right;
	}
	

	/*
	 * mutators
	 */
	public void setTitle(String title)
	{
		this.title = title;
	}
	
	public void setAvailable(int avail)
	{
		this.avail = avail;
	}
	
	public void setRented(int rented)
	{
		this.rented = rented;
	}
	
	public void setLeft(Node left)
	{
		this.left = left;
	}
	
	public void setRight(Node right)
	{
		this.right = right;
	}
	
	/*
	 * created this method, according to the availability,
	 * once you rent a DVD, the number in the rented increases
	 * while the availability decreases as the customer
	 * has the copy
	 */
	
	public void rentDVD()
	{
		if (avail >0 )
		{
			avail--;
			rented++;
		}
			
		
	}
	
	/*
	 *  
	 * Once the person returns the DVD, he no longer
	 * has the DVD, so it comes back to the Redbox system
	 * so the availability increases
	 */

	public void returnDVD()
	{
		avail++;
		rented--;
	}
	
	/*
	 * adding the number of copies to the available
	 */
	public void addCopies(int copies)
	{
		avail = avail + copies;
	}
	/*
	 * subtracting the number of copies from the available
	 */
	public boolean removeCopies(int copies)
	{
		
		
		if (avail >= copies)
		{
			avail = avail - copies;
			return true;
		}
		else
		{
			return false;
		}
	}
}
