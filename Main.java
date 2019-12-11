//Srinivas Kotha
//ssk170630

/*
 * This is a program done in Java to track 
 * inventory for Redbox given a log of transactions
 * which include renting and returning DVDs
 * as well as adding and removing DVD titles.
 * 
 * Using a Binary Search Tree, the program processes
 * every transaction and creates a report after all transactions 
 * are processed shown on the redbox_kiosk.txt file
 * 
 * The errors of the transactions will be shown on the error.log 
 * file.
 * 
 * The transaction log shows the different transactions
 * of movie titles and number of copies(to add or remove) 
 * with different operations: add, remove, rent, and return
 */



package Tickets;


import java.io.File;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.io.FileNotFoundException;
import java.io.PrintWriter;




public class Main {
	
	/*
	 * errorFile used to record the transaction errors 
	 * and store it in the error.log file
	 */
	
	/*
	 * outputFile used to record the report of
	 * the title, number of copies available
	 * and the copies rented and stores it in the
	 * redbox_kiosk.txt file
	 */
	
	PrintWriter errorFile = null;
	PrintWriter outputFile = null;

	BinarySearchTree dvdTree = new BinarySearchTree();
	private void createBaseInventory(String inventoryFile)
	{
		/*
		 * Creates DVD inventory by 
		 * reading the inventory input file 
		 */
		
		try
		{
			String line;
			String title;
			
			Scanner file = new Scanner(new File(inventoryFile));
			while(file.hasNext())
			{
				/*
				 * checks the file line by line
				 */
				line = file.nextLine();
				Scanner inventoryRecord = new Scanner(line);
				inventoryRecord.useDelimiter(","); 
				title = inventoryRecord.next();
				//System.out.println("Adding Title:"+title);
				int qtyAvailable = inventoryRecord.nextInt();
				//System.out.print(qtyAvailable);
				int qtyRented = inventoryRecord.nextInt();
				//System.out.println(qtyRented);
				
				dvdTree.insert(new Node(title, qtyAvailable, qtyRented, null, null));
			}
			
			/*
			 * closes the inventory file
			 */
			file.close(); 
			
			//dvdTree.printTree();
		} catch(FileNotFoundException e)
		{
			/*
			 * catches if the file is not found and outputs
			 * the message in the errorFile, error.log
			 */
			errorFile.println("Inventory File not found " + e);
			 String cwd = System.getProperty("user.dir");
		     errorFile.println("Current working directory : " + cwd);
		}
	}
	
	/*
	 * created to process the transactions based off
	 * of the file, transactions.log
	 */
	private void processTransactions(String transactionFile)
	{
		try
		{
			String line;
			
			/*
			 * transaction types
			 * include add, remove, rent, and return 
			 * a movie title and the number of copies to add
			 * or remove
			 */
			
			String transactionType;
			
			Scanner file = new Scanner(new File(transactionFile));
			while(file.hasNext())
			{
				line = file.nextLine();
				Scanner transaction = new Scanner(line);
				transaction.useDelimiter(",");
				transactionType = transaction.next();
				//System.out.println(transactionType);
			/*
			 * used the String tokenizer to break
			 * the string into tokens
			 */
				StringTokenizer st = new StringTokenizer(transactionType);
				String operation = st.nextToken();
				String title = st.nextToken();
				//System.out.println(operation + " " + title);
			
				int qty = 0;
				if(transaction.hasNextInt())
				{
					qty = transaction.nextInt();
					//System.out.println(qty);
				}
			/*
			 * using the different transaction types
			 * checks to see if the title of the movie 
			 * exists or not, if not, prints the error
			 * onto the error.log file
			 * 
			 * does this for all the different
			 * transaction types: rent, return, add, and remove
			 */
				if (operation.equalsIgnoreCase("rent"))
				{
					Node titleNode = dvdTree.searchNode(title);
					if (titleNode != null)
					{
						titleNode.rentDVD();
					}
					else
					{
						errorFile.println("Title: " + title + " Not found.");
					}
				}else if(operation.equalsIgnoreCase("return"))
				{
					Node titleNode = dvdTree.searchNode(title);
					if (titleNode != null)
					{
						titleNode.returnDVD();
					}
					else
					{
					errorFile.println("Title: " + title + " Not found.");
					}
				}else if(operation.equalsIgnoreCase("add"))
				{
					Node titleNode = dvdTree.searchNode(title);
					if (titleNode != null)
					{
						titleNode.addCopies(qty);
					}
					else
					{
						errorFile.println("Title: " + title + " Not found.");
					}
				
				}else if(operation.equalsIgnoreCase("remove"))
				{	
					Node titleNode = dvdTree.searchNode(title);
					if (titleNode != null)
					{
						boolean status = titleNode.removeCopies(qty);
					/*
					 * returns true or false to see
					 * whether the task was a 
					 * success or not
					 * 
					 * if false, the transaction removal fails
					 */
						if (status == false)
						{
							errorFile.println("Title: "+ title + " Remove Transaction failed");
						}
					
					}
					else
					{
						errorFile.println("Title: " + title + " Not found.");
					}
				}
			}
			
			/*
			 * throws a filenotfound exception if the
			 * file cannot be found from the user directory
			 */
		}catch(FileNotFoundException e)
		{
			errorFile.println("File not found " + e);
			String cwd = System.getProperty("user.dir");
			errorFile.println("Currently working directory : " + cwd);
			
		}
	}
	
	
	/*
	 * these two methods print
	 * the inventory to the redbox_kiosk.txt file
	 */
	public void printInventory()
	{
		Node rootNode = dvdTree.getRoot();
		printTreeLeftToRight(rootNode);
		outputFile.close();
	}
	
	
	public void printTreeLeftToRight(Node currentNode)
	{
		
		if (currentNode != null)
		{
			printTreeLeftToRight(currentNode.getLeft());
			outputFile.println("DVD Title:" + currentNode.getTitle() + ": Number of copies available=" + currentNode.getAvailable() 
			+ ": Number of copies rented = " + currentNode.getRented());
			outputFile.flush();
			printTreeLeftToRight(currentNode.getRight());
		}

	}
	
	public static void main(String[] args) {

		Main mp = new Main();
		
		/* Create an error file to log the error transactions
		 * Create an output file to log the final report
		 */
		try
		{
			mp.errorFile = new PrintWriter("error.txt");
			mp.outputFile = new PrintWriter("redbox_kiosk.txt");
		}
		catch (FileNotFoundException ex)
		{
			System.out.println("Unable to create output and error files");
			return;
		}
		
		/*
		 * reading the files
		 */
		
		/*
		 * reading the inventory file
		 */
		mp.createBaseInventory("inventory.txt"); 
		/*
		 * process the transaction log
		 */
		mp.processTransactions("transaction.txt"); 
		/*
		 * print inventory after completing the transaction processing
		 */
		mp.printInventory(); 
		
		mp.errorFile.flush();
		mp.errorFile.close();
	}

}
