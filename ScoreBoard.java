// TO DO: add your implementation and JavaDocs.

/**
* A class that will act as a score board by storing a collection of payer records. 
*It also allows multiple operations for maintenance, including appending/prepending new records, updating a
*record, removing a record, and displaying details of one player or one game  
**/
public class ScoreBoard {
	
	//number of games
	/**
	* The amount of games in the scoreboard.
	**/
	private int gameCount;
	
	//player records - you MUST use this for credit!
	//Do NOT change the name or type
	//NOTE: you cannot use any arrays or JCF instances in your implementation.
	/**
	*An ThreeTenDynArray object that stores the record of every record.
	**/
	private ThreeTenDynArray<PlayerRec> records;
	
	// ADD MORE PRIVATE MEMBERS HERE IF NEEDED!
	/**
	*A constructor that initalizes records to be ab empty list, 
	*and the game count to be the given parameter.
	*@param gameCount the amount of games
	**/
	public ScoreBoard(int gameCount){
		//Constructor
		// set the game count and initialize the records to be an empty list
		
		// - Throw IllegalArgumentException if gameCount is not positive 
		// - Use this _exact_ error message for the exception
		//   (quotes are not part of the message):
		//    "Must have at least one game!"
		if(gameCount < 0)
			throw new IllegalArgumentException("Must have at least one game!");
		this.gameCount = gameCount;
		records = new ThreeTenDynArray<PlayerRec>();
		
	}
	
	/**
	*A getter method for the number of players.
	*@return int the number of players in records
	**/
	public int playerCount(){
		//report number of players 
		//O(1)

		return records.size(); //default return, remove or update as needed
	}
	
	/**
	*A getter method for the number of games.
	*@return int the number of games in ScoreBoard
	**/
	public int gameCount(){
		//report number of games 
		//O(1)

		return this.gameCount; //default return, remove or update as needed
	}
	
	/**
	* A void method that adds a new player to the end of the record.
	*If the player's name is found in the records then that record will be updated.
	*
	*@param player A PlayerRec object that will be added to the records
	**/
	public void addPlayer(PlayerRec player){
		// append new player into record
		// if player is already present, updated the record 
		//   - assume each player has a unique name, i.e. matching names means same player
		
		// O(N) where N is the number of players present
		boolean foundMatch = false;
		for(int i = 0; i < this.playerCount(); i ++){
			if(player.name().equals(records.get(i).name())){
				records.set(i, player);
				foundMatch = true;
				break;
				
			}
		}
		if(!foundMatch)
			records.append(player);
	}
	/**
	* A getter method that will return the record of the player at the given index.
	*
	*@param index the index of the desired record
	*@return PlayerRec the desired player record
	**/
	public PlayerRec getPlayer(int index){
	
		//return player record corresponding to index
		//return null for invalid indexes
		
		//O(1)
		if(index < 0 || index>= this.playerCount())
			return null;
		return records.get(index); //default return, remove or update as needed
	
	}
	/**
	* A method that will find and return the player record that matches the given name.
	*
	*@param name the name tha the method will try to find
	*@return PlayerRec the player record that matched the given name, if not found returns null
	**/
	public PlayerRec findPlayer(String name){
		//find and return player record with the matching name
		//return null if not present
		
		// O(N) where N is the number of players present
		for(int i = 0; i < this.playerCount(); i ++){
			if(records.get(i).name().equals(name)){
				return records.get(i);
			}
		}
		return null; //default return, remove or update as needed
	}
	/**
	* A method that removes the player's record with the matching name.
	*
	*@param name the name that should be removed
	*@return boolean true if the player was removed successfully; false if otherwise
	**/
	public boolean removePlayer(String name){
		//remove player with the matching name
		//return true if a record is removed successfully; false otherwise
		
		// O(N) where N is the number of players present
		for(int i = 0; i < this.playerCount(); i ++){
			if(records.get(i).name().equals(name)){
				records.remove(i);
				return true;
			}
		}
		return false; //default return, remove or update as needed
		
	}
	
	/**
	* A method that will change the score of the given game for the player with a matching name.
	*
	*@param name the name of the player that will be changes
	*@param game the game that will be changed
	*@param newScore the new score for the game
	*@return boolean false if the newScore cannot be set, and true if it was
	**/
	public boolean changeScore(String name, int game, int newScore){
		//set the score of the given game for the player with a matching name
		//return false if newScore cannot be set for any reason 
		// (e.g player /game not present);   return true otherwise
		if(newScore<0)
			return false;
		if(game<0 || game > this.gameCount())
			return false;
		// O(N) where N is the number of players present
		for(int i = 0; i< this.playerCount(); i++){
			if(records.get(i).name().equals(name)){
				return records.get(i).replaceScore(game, newScore);
			}
		}

		return false; //default return, remove or update as needed
		
	}
	/**
	* A method that will find the largest score among all players.
	*
	*@return int the largest total score 
	**/
	public int topTotalScore(){
		//return largest total score among all players
		// return -1 if no player present
			
		// O(N) where N is the number of players present
		int totalScoreMax = records.get(0).totalScore();
		for(int i = 1; i < this.playerCount(); i++){
			if(records.get(i).totalScore() > totalScoreMax)
				totalScoreMax = records.get(i).totalScore();
		}
			

		return totalScoreMax; //default return, remove or update as needed
	}

	/**
	* A method that will find the player or players if tied with the top total score.
	*
	*@return ThreeTenDynArray a list of type String for all players with the top score
	**/
	public ThreeTenDynArray<String> topPlayers(){
		//return the list of players (names only) with the top total score
		// if no player present, return an empty list
		// if there are multiple players, keep the names in the same order as 
		//   the players in the current record
		
		// O(N) where N is the number of players present 
		//   _if_ appending to the list to return is O(1)
		ThreeTenDynArray<String> topNames = new ThreeTenDynArray<String>();
		int maxScore = this.topTotalScore();
		for(int i =0; i < playerCount(); i++){
			if(records.get(i).totalScore() == maxScore)
				topNames.append(records.get(i).name());
		}
		return topNames; //default return, remove or update as needed
	}
	/**
	* A getter method that will return all scores for the given game index as a list.
	*If no players are present or if the game index is , the the list will be empty.
	*
	*@param game the game from which the scores will be from
	*@return ThreeTenDynArray the list of scores of type Integer found for the gievn game.
	**/
	public ThreeTenDynArray<Integer> getGame(int game){
		// return all scores for the given game index as a list
		// - if multiple players are present, keep scores in the same order as 
		//    order of players in records 
		// - return an empty list if no players present or game index is invalid
		if(game >= this.gameCount() || game < 0 || playerCount() == 0)
			return new ThreeTenDynArray<Integer>(); 
		// O(M) where M is the number of players 
		//   _if_ appending to the list to return is O(1)
		ThreeTenDynArray<Integer> gameScores = new ThreeTenDynArray<Integer>();
		for(int i =0; i < playerCount(); i++){
			//System.out.println("Count " + records.get(i).count());
			if(records.get(i).count() <= game){
				//System.out.println("Entered");
				return new ThreeTenDynArray<Integer>();
			}
			gameScores.append(records.get(i).getScore(game));
		}

		return gameScores; //default return, remove or update as needed
	}
	
	/**
	* A method that finds the total amount of all players' scores for the given game. 
	*@param game the index of the desired game
	*@return int the sum of all scores for the game, returns -1 if no players present or game index is invalid
	**/
	public int getGameTotal(int game){
		// return the sum of all players' scores of the given game
		// - return -1 if no players present or game index is invalid
		if(this.playerCount() < 1 || game < 0 || game >= this.gameCount())
			return -1;
		// O(M) where M is the number of players 
		int total =0;
		for(int i = 0; i < this.playerCount(); i++){
			//if(records.get(i).count() <= game)
			//return -1;
			//System.out.println(records.get(i).getScore(game));
			total += records.get(i).getScore(game);
		}
		return total; //default return, remove or update as needed
		
	}
	/**
	* A method that finds the max score of the given game, 
	*will return -1 if there are no players present or game index is invalid.
	*
	@param game the index of the desired game
	*@return int the greatest score for the game, returns -1 if no players present or game index is invalid
	**/
	public int getGameMax(int game){	
		// return the max score of the given game
		// - return -1 if no players present or game index is invalid
		
		// O(M) where M is the number of players 
		if(this.playerCount() < 1 || game < 0 || game >= this.gameCount())
			return -1;
		int gameMax = records.get(0).getScore(game);
		for(int i = 1; i < this.playerCount(); i++){
			if(records.get(i).getScore(game) > gameMax)
				gameMax = records.get(i).getScore(game);
			
		}
		return gameMax; //default return, remove or update as needed	
	}
	
	/**
	* A method that finds the minimum score of the given game, 
	*will return -1 if there are no players present or game index is invalid.
	*
	@param game the index of the desired game
	*@return int the smallest score for the game, returns -1 if no players present or game index is invalid
	**/
	public int getGameMin(int game){	
		// return the min score of the given game
		// - return -1 if no players present or game index is invalid
		
		// O(M) where M is the number of players 
		if(this.playerCount() < 1 || game < 0)
			return -1;
		int gameMin = records.get(0).getScore(game);
		for(int i = 1; i < this.playerCount(); i++){
			if(records.get(i).getScore(game) < gameMin)
				gameMin = records.get(i).getScore(game);
			
		}
	
		return gameMin; //default return, remove or update as needed
	}
	/**
	* A method that will combine two score boards. New records will be added 
	*to the end of the list if the boolean parameter is true, or to the start if it is false.
	*If a the new record's player name matches one already pressent in the scoreboard, 
	*then the record will be updated to match the new record.
	*
	*@param another A ScoreBoard object that will be added in to the existing scoreboard
	*@param append A boolean variable that determines where the new records will be added.If true then new records are added to the end, else they will be added to the begining.
	*@return boolean true if the scoreboads are combined successfully, and false if the two score boards cannot be combined.
	**/
	public boolean combine(ScoreBoard another, boolean append){
		// add records from another scoreboard into the current one
		//  - if a player is already present, update the record
		//  - otherwise, 
		//       -if append is false, records from another should be added 
		//        to the start of the current records but keep their original order
		//       -if append is true, add new records to the end of the list
		//  Check main() below and description document for examples.
		
		// return false if two score boards cannot be combined (game count mismatch etc.); 
		// return true otherwise
		// Although we do not have a big-O requirement for this method, you should 
		// practice code reuse and utilize existing operations as much as possible.
		int count =0;
		for(int i =0; i < another.playerCount(); i++){
			if(records.get(i).count() != another.getPlayer(i).count())
				return false;
			PlayerRec temp = this.findPlayer(another.getPlayer(i).name());
			if(temp != null || append){
				this.addPlayer(another.getPlayer(i));
			}
			else{
				records.insert(count, another.getPlayer(i));
				count++;
			}
		}
		return true; //default return, remove or update as needed
				
	}
	

	//******************************************************
	//*******     BELOW THIS LINE IS PROVIDED code   *******
	//*******             Do NOT edit code!          *******
	//*******		   Remember to add JavaDoc		 *******
	//******************************************************

	/**
	* A toString method that is used to display the given game's information.
	*@param game the game which will be displayed
	*@return String the information of a given game
	**/
	public String gameToString(int game){
		if (game<0 || game>gameCount()-1)
			return ("Game index " + game + " invalid!");
			
		StringBuilder s = new StringBuilder("Game [" + game + "]: ");
		s.append("  "+ getGame(game).toString());
		s.append("\n  Game Total: "+ getGameTotal(game) + "\n");	
		s.append("  Game Max: "+ getGameMax(game) + "\n");	
		s.append("  Game Min: "+ getGameMin(game) + "\n");	
	
		return s.toString().trim();
	}
	/**
	* An overridden toString method that displays the scoreboard in a readable format.
	*
	*@return String the rstring version of the scoreboard of each player's record
	**/
	@Override
	public String toString(){
		StringBuilder s = 
			new StringBuilder("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%\n");
		s.append("Game Count: " + gameCount() + "\t");
		s.append(" Player Count: " + playerCount() + "\n");
		s.append("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%\n");
		for (int i=0; i<playerCount(); i++){
			s.append("[" + i +"]");
			s.append(records.get(i).toString());
			if (i!=playerCount()-1)
				s.append("\n------------------------------------------------------------------\n");
		}
		s.append("\n%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%\n");
		return s.toString().trim();
		
	}



	//******************************************************
	//*******     BELOW THIS LINE IS TESTING CODE    *******
	//*******      Edit it as much as you'd like!    *******
	//*******		Remember to add JavaDoc			 *******
	//******************************************************
	/**
	* A main method that is used to test the given methods above.
	*@param args the user input if needed
	**/
	public static void main(String args[]){
		//example tests
		//remember to change and/or add more of your test cases
		
		//create a board
		ScoreBoard board = new ScoreBoard(2);
		if (board.playerCount() == 0 && board.gameCount() == 2
			&& board.getGameMin(0) == -1 ){			
			System.out.println("Yay 1");		
		}

		//create a player
		PlayerRec player1 = new PlayerRec("George");
		//addScore
		player1.addScore(1);
		player1.addScore(2);
		//add player
		board.addPlayer(player1);
		if (board.playerCount() == 1 && board.gameCount() == 2  
			&& board.getGameMin(0) == 1 && board.topTotalScore()== 3){			
			System.out.println("Yay 2");		
		}
		
		//another board
		ScoreBoard another = new ScoreBoard(2);
		PlayerRec player2 = new PlayerRec("A");
		player2.addScore(2);
		player2.addScore(0);
		another.addPlayer(player2);
		
		PlayerRec player3 = new PlayerRec("B");
		player3.addScore(5);
		player3.addScore(10);
		another.addPlayer(player3);
		another.addPlayer(player3);
		
		//System.out.println(another + "\n");
		//System.out.println(board + "\n\n");
		
		//prepend, pay attention to the order of records after combination
		if (board.combine(another, false) && board.getPlayer(0).equals(player2)
			&& board.getPlayer(1).equals(player3) && board.getPlayer(2).equals(player1) ){
			System.out.println("Yay 3");		
		} 
		
		//uncomment to see details
		//System.out.println(board);
		//System.out.println(board.getGame(1).toString());
		//System.out.println(board.getGameTotal(1) + " " + board.getGameMax(0) + " " + board.getGameMin(0) + " " + board.getGameMin(3) );
		//game-related
		if (board.getGame(1).toString().equals("[0, 10, 2]") 
			&& board.getGameTotal(1) == 12 && board.getGameMax(0) == 5 
			&& board.getGameMin(0) == 1 && board.getGameMin(3) == -1){
			System.out.println("Yay 4");		
		}
		
		//player-related
		//System.out.println(board+ "\n");
		//System.out.println(board.findPlayer("A").equals(player2));
		if (board.findPlayer("A").equals(player2) && !board.removePlayer("X")
			&& board.removePlayer("B") && board.playerCount() == 2	
			&& board.changeScore("A", 1, 1)
			&& board.topPlayers().toString().equals("[A, George]")){			
			System.out.println("Yay 5");		
			
		}
		System.out.println(board+ "\n After");
		another = new ScoreBoard(2);
		player2 = new PlayerRec("George");
		player2.addScore(3);
		player2.addScore(4);
		player2.addScore(5);
		another.addPlayer(player2);
		board.combine(another, true);
		System.out.println(board + "\n\n");
		
	}
	
}

