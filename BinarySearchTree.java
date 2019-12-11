//Srinivas Kotha
//ssk170630

package Tickets;



public class BinarySearchTree {
	
	private Node root;
	
	/*
	 * default constructor
	 */
	BinarySearchTree()
	{
		 root = null;
	}
	/*
	 * overloaded constructor
	 */
	BinarySearchTree(Node root)
	{
		this.root = root;
	}
	/*
	 * Accessors
	 */
	public Node getRoot()
	{
		return root;
	}
	
	/*
	 * Mutators
	 */
	public void setRoot(Node root)
	{
		this.root = root;
	}
	
	/*
	 * created a Binary Search tree using generics
	 * the insert method is done recursively
	 * first it starts from null, the first title in
	 * my list is Joker, it will look to see if the number of characters
	 * of the next title is either greater than or less than the previous title
	 * 
	 */
	public void insert(Node newNode)
	{
		if (newNode == null) {
			return;
		}
		
		root = insertRecursive(root, newNode);
		
	}
	
	private Node insertRecursive(Node currentNode, Node newNode)
	{
		if (currentNode == null)
		{
			currentNode = newNode;
			return currentNode;
		}
		
		/*
		 * insert the node to the left
		 */
		if (newNode.getTitle().compareTo(currentNode.getTitle())>0) 
		{
			currentNode.setLeft(insertRecursive(currentNode.getLeft(), newNode));
		}
		/*
		 * insert node to right
		 */
		else if (newNode.getTitle().compareTo(currentNode.getTitle())<0) 
		{
			currentNode.setRight(insertRecursive(currentNode.getRight(), newNode));
		}
		/*
		 * it will return the currentNode if the node
		 * already exists
		 */
		return currentNode; 
	}
	/*
	 * prints the tree left to right
	 * bigger numbers go to the right of the tree
	 * smaller numbers go to the left of the tree
	 */
	public void printTree()
	{
		printTreeLeftToRight(root);
	}
	
	public void printTreeLeftToRight(Node currentNode)
	{
		
		if (currentNode != null)
		{
			printTreeLeftToRight(currentNode.getLeft());
			System.out.println(currentNode.getTitle());
			printTreeLeftToRight(currentNode.getRight());
		}

	}
	
	/*
	 * the search method is done recursively
	 * it will check from the root to see if any of
	 * the titles exist
	 */
	
	public Node searchNode(String key)
	{
		if (root == null)
			return null;
		
		return searchNodeRecursive(root, key);
		
	}
	
	private Node searchNodeRecursive(Node currentNode, String key)
	{
		if (currentNode == null)
		{
			return null;
		}
		else
		{
			if (currentNode.getTitle().equals(key))
				return currentNode;
		}
		
		if (key.compareTo(currentNode.getTitle()) > 0)
        {
        	return searchNodeRecursive(currentNode.getLeft(), key);
        }
        else
        {
        	return searchNodeRecursive(currentNode.getRight(), key);
        }
	}
	
}
