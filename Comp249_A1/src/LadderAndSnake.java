// -----------------------------------------------------
// Assignment 1
// LadderAndSnake class.
// Written by: Ambrose McLaughlin ID#40239754
// Due: Feb 3rd 2023 11:59 pm
// -----------------------------------------------------
/*
 
 * This class includes 3 2D Arrays. One being a for gameboard of 10 rows and 10 collumns. The second being for multiple snakes with a starting position and ending position. The third being for multiple ladders ,also with a starting and ending position.
 * Players are stored in an array list.
 * This class includes the crucial methods needed to play Snakes and ladders including playerMove, doesPlayerWin, play, and toString. 
 */
import java.util.Scanner;
import java.util.ArrayList;

/**
* The LadderAndSnake class .
* This class includes the crucial methods needed to play the game.
* These methods include: play, doesPlayerWin, playerMove, doesPlayerWin, toString, etc.
*
* @author Ambrose McLaughlin
*/
public class LadderAndSnake {

	private int rows =10;
	private int collumns =10;
	private int numLadders =9;
	private int numSnakes = 8;
	private int numPlayers;
	
	private int [][] gameBoard;
	private int [][] snakes;
	private int [][] ladders;
	ArrayList<Player> players;
	
	/**
	* Parameterized constructor that defines the gameBoard using rows and collumns, sets snakes and ladders, and verifies the number of players.
	*
	* @param ArrayList<Player> player
	* @author Ambrose McLaughlin
	*/
	public LadderAndSnake(ArrayList<Player> players)
	{

		Scanner sc = new Scanner(System.in);
		this.numPlayers=0;;
		
		//Verify validity of input
		do {
			while(!sc.hasNextInt())
			{
				System.out.println("Not a valid Integer. Please try again");
				sc.nextLine();
			}
	
		}while(!sc.hasNextInt());
		this.numPlayers = sc.nextInt();
		
		//Verify that only 2 players can play the game.
		if(this.numPlayers >2)
		{
			System.out.println("Error: Maximum number of players is 2. Number of players has been set to 2.");
			this.numPlayers = 2;
		}
		if (this.numPlayers < 2)
		{
			System.out.println("Error: Minimum number of Players is 2. Shutting Down Snakes and Ladders. GoodBye.");
			System.exit(this.numPlayers);
		}
		else
		{
			this.numPlayers =2;
		}
		this.players = players;
		
		
		//Add players to ArrayList
		for (int i = 0; i<this.numPlayers; i++)
		{
			Player player = new Player (">P" + i + "<");
			players.add(player);
		}
		
		//Define gameboard
		gameBoard = new int[rows][collumns];
		for (int i = 0; i<rows; i++)
		{
			for (int j =0; j<collumns; j++)
			{
				gameBoard[i][j] = i*rows + j +1;
			}
		}
		//Set positions of snakes and ladders. 
		setSnakes();
		setLadders();
	}
	

	
	
	/**
	* Method that checks whether or not the position a player occupies is the winning position.
	* If a player rolls a dice value that sends the player beyond the 100 position, this method will also send them backwards by the excess amount.
	* theMethod landedOnPlayer is called in this method.
	*
	* @param  Player player corresponding to the player currently playing.
	* @param  Integer number representing the player's position.
	* @author Ambrose McLaughlin
	* @return a boolean value representing whether or not a player has reached position 100.
	* 
	*/
	public boolean doesPlayerWin(Player player, int number)
	{
		//Check to see if a player has landed on the position of another player
		landedOnPlayer(player);
		int pos = player.getPos();
		pos =0;
		pos+= number;
		
		player.setPos(pos);
		
		
		//Declare winner if position of 100 is reached
		if (pos == 100) {
			player.setPos(pos);
			System.out.println(player + " Wins! Congrats!");
			
			return true;
			
		}
		
		//Check if player rolls a number that would cause them to exceed 100 and send them back the excess amount.
		if (pos>=100)
		{
			int reverseMove = 100 - (pos - 100);
			player.setPos(reverseMove);
			System.out.println(player +"Almost won! They bounced back to position: " + (reverseMove));
			return false;
		}
		
		//Just set position if its less than 100.
		if (pos<100)
		{
	
			player.setPos(pos);
			return false;
		}
		else
		return false;
	}
	
	/**
	* Checks to see if a player lands on a position already occupied by another player.
	* If the above is so, the player who occupied the contested position initially will have their position set to 0.
	*
	* @param  Player movingPlayer corresponding to the player currently playing.
	* @author Ambrose McLaughlin
	* 
	*/
	public void landedOnPlayer(Player movingPlayer) {
		for (Player player: players) {
			if(movingPlayer.toString() != player.toString() && movingPlayer.getPos() == player.getPos()) {
				player.setPos(0);
			System.out.println(movingPlayer + "Landed on " + player +"! " +player + " kicked to position 0.");
			}
		}
	}
	/**
	* Method responsible for incrementing the position of player.
	* This method will also check to see if the players position corresponds to the starting point of a ladder or snake, as well as send them to the ladder or snakes respective ending position.
	* Also applies the doesPlayerWin to check if the player's position ever reaches 100.
	* @param  Player player
	* @param Integer number
	* @author Ambrose McLaughlin
	* @return Boolean result of the doesPlayerWin method based on the players position
	*/
	public boolean playerMove (Player player, int number)
	{
		 int pos =  player.getPos();
		 //increment player position by an integer value
		 pos += number;
		
		 //set players new position
		player.setPos(pos);
		
		//Check if player has reached winning position
		doesPlayerWin(player, pos); 
		
		 {
			 //Check if player lands on the head of a snake
			for (int i = 0; i < numSnakes; i++)
			{
				if (snakes[i][0]== pos) 
				{
					pos = snakes[i][1];
					
					//Set player position to tail of snake
					player.setPos(pos);
					//Inform player of starting and finishing position of the snake
					System.out.println("Looks like " + player + " tripped and fell on a snake! " + player + " fell from " + snakes[i][0]+ " to " + snakes[i][1]);
				
					return doesPlayerWin(player, pos);
				}
			}
			
			//Check if player lands on the bottom of a ladder
			for (int i =0; i< numLadders; i++)
			{
				if(ladders[i][0] == pos)
				{
					pos = ladders[i][1];
					//set player position to the top of ladder.
					player.setPos(pos);
					
					//Inform player of the starting and finishing position of the ladder
					System.out.println(player + " climbed a ladder from " + ladders[i][0] + " to " + ladders[i][1]);
					player.setPos(pos);
					return doesPlayerWin(player, pos);
					
				}
			}
			
			player.setPos(pos);
			
			return doesPlayerWin(player,pos); 
		 }
	}
	
	/**
	* Defines the starting and ending position of 8 snakes
	*
	* @author Ambrose McLaughlin
	* 
	*/
	public void setSnakes()
	{
		snakes = new int[numSnakes][2];
		snakes[0][0] = 16;
		snakes[0][1] = 6;
		snakes[1][0] = 48;
		snakes[1][1] = 30;
		snakes[2][0] = 79;
		snakes[2][1] = 19; 
		snakes[3][0] = 64; 
		snakes[3][1] = 60;
		snakes[4][0] = 93;
		snakes[4][1] = 68;
		snakes[5][0] = 95;
		snakes[5][1] = 24;
		snakes[6][0] = 97;
		snakes[6][1] = 76;
		snakes[7][0] = 98;
		snakes[7][1] = 78;
		
	}
	/**
	* Defines the starting and ending position of 9 ladders.
	*
	* @author Ambrose McLaughlin
	* 
	*/
	public void setLadders()
	
	{
		ladders = new int[numLadders][2];
		ladders[0][0] =  1;
		ladders[0][1] = 38;
		ladders[1][0] = 4;
		ladders[1][1] = 14;
		ladders[2][0] = 9;
		ladders[2][1] = 31;
		ladders[3][0] = 21;
	    ladders[3][1] = 42;
	    ladders[4][0] = 28;
	    ladders[4][1] = 84;
	    ladders[5][0] = 36;
	    ladders[5][1] = 44;
	    ladders[6][0] = 51;
	    ladders[6][1] = 67;
	    ladders[7][0] = 71;
	    ladders[7][1] = 91;
	    ladders[8][0] = 80;
	    ladders[8][1] = 100;
	}
	/**
	* Checks to see if a player lands on as position already occupied by another player.
	* If the above is so, the player who occupied the contested position initially will have their position set to 0.
	*
	* @author Ambrose McLaughlin
	* @return A string corresponding to a visual representation of the game board with the current position of players that occupy a position from 1 to 100. 
	*/
	public String toString()
	{
		System.out.println("____________________________________________________________________________");
		System.out.println("****************************************************************************\n");
		
	
		StringBuilder readable = new StringBuilder();
		boolean isOddRow = true;
		
		for (int i = rows-1; i>=0; i--)
		{
			for (int j = 0; j < collumns; j++)
			{
				if (isOddRow)
					//for odd rows: 1,3,5,7,9
				{
					String p1 = "";
					boolean occupied = false;
					for(Player player : players)
					{
						//Check if player occupies position
						if (player.getPos() == gameBoard[i][collumns -1-j])
						{
							occupied = true;
						
							p1 += player +" ";
						}
					}
					
					if (occupied) 
				
					{
					//Print a players name on the occupied position
						p1 += "\t";
						readable.append(p1);
					}
					else
					{
						readable.append("|" +gameBoard[i][collumns-1-j] +"|"+"\t");
					}
				}
				else
				//Even rows; 0,2,6,8
				{
					boolean occupied = false;
					String p1 = "";
					for (Player player : players)
					{
						//Check if position is occupied by a player
						if (player.getPos() == gameBoard[i][j])
						{
							
							occupied = true;
							p1 += (player + " ");
						}
					}
					if (occupied)
					{
						//Print a players name on the occupied position
						p1 += "\t";
						readable.append(p1);
					}
					else
					{
						readable.append("|"+gameBoard[i][j]+ "|"+ "\t");
					}
				}
			}
			
			isOddRow = !isOddRow;
			readable.append("\n\n");
		}
		readable.append("\n");
		
		return readable.toString();
	}
	
	/**
	* Allows users to play the game. Uses previously defined methods to apply the rules of the game.
	* Welcomes, prompts, updates, and delivers a closing message to users.
	*
	* @author Ambrose McLaughlin 
	*/
	public static void play() {
		
		//Welcome user.
		System.out.println("*********************************************");
		System.out.println("       Welcome to Snakes and Ladders");
		System.out.println("       Created by Ambrose McLaughlin");
		System.out.println("*********************************************\n");

		//Prompt user
		System.out.print("Enter the number of players: ");
	
		//Initialize ArrayList of Players
		ArrayList<Player> players = new ArrayList<Player>();
		
		//initialize playBoard
		LadderAndSnake playBoard = new LadderAndSnake(players);
		
		
		boolean somebodyWon = false;
		
		//determine who plays first
		int playerIndex = Player.playsFirst();
		
	//Loop until a player reaches the 100th position
	while(!somebodyWon)
		{
		Player chosenPlayer = players.get(playerIndex);
		
			int flip = chosenPlayer.playTurn();
			
			//Boolean will be notified if a player reaches the 100th position.
			somebodyWon = playBoard.playerMove(chosenPlayer, flip);
			
			//Display border
			System.out.println(playBoard);
			System.out.println("____________________________________________________________________________");
			System.out.println("****************************************************************************\n");
		
		
			playerIndex++;
			//Restart turn order once player index is at the maximum number of players.
			if(playerIndex == players.size())
			{
				playerIndex = 0;
			}
		}	
		//Display closing message
		System.out.println("Thank you for playing Snakes and Ladders. Goodbye.");
		
	}
}
