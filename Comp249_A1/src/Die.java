// -----------------------------------------------------
// Assignment 1
// Die Class.
// Written by: Ambrose McLaughlin ID#40239754
// Due: Feb 3rd 2023 11:59 pm
// -----------------------------------------------------
/**
* The Die class .
* This class creates the object Die and includes the flipDice Method.
* Because the dice class has its own file, it could theoretically be used for other developments.
* 
*
* @author Ambrose McLaughlin
*/
import java.util.Random;
import java.util.Scanner;
public class Die {

	private Random oneToSix;
	
	/**
	* Constructor. creates Random Object oneToSix
	*
	* @author Ambrose McLaughlin
	*/
	public Die()
	{
		oneToSix = new Random();
	}
	
	/**
	* The flipDice Class returns a random integer from 1 to 6.
	* 
	*
	* @author Ambrose McLaughlin
	* @return a random integer between 1 and 6.
	*/
	public int flipDice()
	{
		Scanner sc = new Scanner(System.in);
		System.out.println("Press any letter or number key then Press <Enter> to Roll the dice.");
		//This input allows the user to decide when dice roll occurs, as opposed to the game being played automatically until completion.
		String rollKey = new String(sc.next());
			
			int out =oneToSix.nextInt(6)+1;
			return out ;
			
		
		
	} 
} 