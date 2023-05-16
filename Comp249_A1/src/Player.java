// -----------------------------------------------------
// Assignment 1
// Player class.
// Written by: Ambrose McLaughlin ID#40239754
//Due: Feb 3rd 2023 11:59 pm
// -----------------------------------------------------
/*
 * Player Class is used to create the Object "Player".  An Object of type Player has two attributes, A string for the player's name as well as an integers that represents the players position on the board.
 * Getter and Setter methods are present for the position attribute, as well as a toString method for the player's name attribute.
 * 
 */
/**
* The Player class includes the integer for a player's position, a string for their name, and also has a Die attribute, so that the flipDice method can be utilized.
* 
* @author Ambrose McLaughlin
*/
public class Player {

	private String playerName;
	private static Die die;
	private int position;
	
	public Player(String playerName) {
		
		this.playerName = playerName;
		die = new Die();
		this.position = 0;
	}
	
	/**
	*Returns the integer value that represents a player's position on the board.
	* @return  and integer corresponding to a player's position.
	* @author Ambrose McLaughlin
	*/
	public int getPos() {
		return this.position;
	}
	/**
	*Sets the integer value that corresponds to a player's position.
	*
	*@
	* @author Ambrose McLaughlin
	*/
	public void setPos(int position) {
		this.position = position;
	}
	
	
	/**
	* Determines which player will have the first turn in Snakes and Ladders.
	* Achieved through 2 dicer rolls. The higher value roll will determine who begins the game.
	* If a tie is achieved, the method will continue to roll dice until one roll has a higher value.
	*
	* @author Ambrose McLaughlin
	* @return Integer value that will become the index of the player that will play first.
	*/
	public static int playsFirst()
	{
		//Initialize First and second roll.
		int firstRoll =0;
		int secondRoll = 0;
		int chosenPlayer=10; //Ten is used because if there was a logic error in the code, It will be evident to the user as the game should not allow more than 2 players.
		int counter =0;
		do {
			System.out.println("Lets see who plays first");
			
			
			firstRoll = die.flipDice();
			System.out.println(" Player 0 Rolling...\n Player 0 Rolled a " + firstRoll);
			secondRoll= die.flipDice();
			System.out.println(" Player 1 Rolling...\n Player 1 Rolled a " + secondRoll);
			if(firstRoll == secondRoll) {
				System.out.println("Tie achieved. Each player will roll again");
			}
			//Keep track of attempts.
			counter++;
			if (firstRoll > secondRoll)
			{
				System.out.println("Player 0 Goes first");
				System.out.println("\nIt took " + counter + " attempt(s) to decide who plays first!\n");
				chosenPlayer = 0;
				return chosenPlayer;
				
			}
			else if (firstRoll < secondRoll)	
			{
				System.out.println("Player 1 Goes first");
				System.out.println("\nIt took " + counter + " attempt(s) to decide who plays first!\n");
				chosenPlayer =1;
				return chosenPlayer;
			}
		}while(firstRoll == secondRoll); //Loop until one roll is higher than the other
		
		return chosenPlayer;
		
	}
	/**
	* Utilizes the flipDice method to return a random number that a player will use to determine where to move on the game board.
	*
	* @author Ambrose McLaughlin
	* @return Integer value corresponding to a dice flip.
	*/
	public int playTurn() 
	{
		int flip = die.flipDice();
		System.out.println(this.playerName.toString() + "Rolled a " + flip);
		
		return flip;
	}
	
	/**
	* toString Method that returns the players name. 
	* @author Ambrose McLaughlin
	* @return Player's name.
	*/
	public String toString()
	{
		return this.playerName;
	}
	
}

