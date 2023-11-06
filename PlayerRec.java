// TO DO: add your implementation and JavaDocs.
/**
* A public class representing a player record. Each record has a name, a collection of
* scores, a count of scores, and a total score.
*
*@author Starling Devine
**/
public class PlayerRec {

	/**
	*The name of player.
	**/
	private String name;
	
	//score record - you MUST use this for credit!
	//Do NOT change the name or type
	//NOTE: you cannot use any arrays or JCF instances in your implementation.
	/**
	*A ThreeTenDynArray class object of type integer.
	*
	**/
	private ThreeTenDynArray<Integer> scores;

	// ADD MORE PRIVATE MEMBERS HERE IF NEEDED!
	/**
	* The total score of the player.
	**/	
	private int totalScore;
	/**
	* A default constructor that sets the name of the player and 
	*initializes the scores to be an empty list.
	*
	*@param name the name of the player
	**/
	public PlayerRec(String name){
		// Constructor
		// set the name of the player and initialize the scores to be an empty list
		// you can assume name is not null
		this.name = name;
		scores = new ThreeTenDynArray<Integer>();
		// if you have other private members, initialize those as well
		//count = 0;
		totalScore = 0;
		
	}
	
	/**
	* A getter method that returns the size of scores.
	*@return int the size of scores
	**/
	public int count(){
		//report the number of scores
		// O(1)
		return scores.size(); //default return, remove or update as needed
	}
	/**
	* A getter method for the name of the player.
	*@return String the name of the player
	**/
	public String name(){
		//report the name of the player
		// O(1)
		return this.name; //default return, remove or update as needed
	}
	/**
	* A getter method for the total amount of scores that the player has scored.
	*
	*@return int the total scores
	**/
	public int totalScore(){
		//report the sum of all scores of the player
		//O(1)
		return this.totalScore; //default return, remove or update as needed
	}
	
	
	/**
	* A public method that will append the new score to the end of scores record.
	*
	*@param score the score that will be added to the end of the record
	*@return boolean true if if score appended successfully, and false for any errors
	**/
	public boolean addScore(int score){
		// append a score at the end of scores record
		// return true if score appended successfully;
		// return false for any errors (e.g. a negative score)
		if(score < 0)
			return false;
		try{
			this.scores.append(score);
		}
		catch(Exception e){
			return false;
		}
		//count++;
		totalScore+= score;
		// amortized O(1) 
		return true; //default return, remove or update as needed

	}
	/**
	* A public method that will replace the score of the specified game to be newScore.
	*
	*@param game the game that will have its' score changed
	*@param newScore the new score of the game
	*@return boolean false if any errors accure, and true if otherwise
	**/
	public boolean replaceScore(int game, int newScore){
		//replace the score of the specified game to be newScore
		//return false for any errors; return true otherwise
		int oldScore=0;
		try{
			oldScore = this.scores.set(game, newScore);
		}
		catch(Exception e){
			return false;
		}
		totalScore += newScore - oldScore;
		
		//O(1)
		return true; //default return, remove or update as needed
		
	}
	/**
	* A public method that will return the score if the specified game.
	*
	*@param game the specifed game 
	*@return int the score in the specified game
	**/
	public int getScore(int game){
		//return score of the specified game
		//return -1 for invalid game index
		if(game >= this.count() || game < 0){
			return -1;
		}
		//O(1)
		return scores.get(game); //default return, remove or update as needed
		
	}
	/**
	* A public method that will find the indexes with the highest score of this player.
	*
	*
	*@return ThreeTenDynArray an Integer list of game indexes in ascending order of the highest scores
	**/
	
	public ThreeTenDynArray<Integer> getTopGames(){
		//return the list of game indexes with the highest score of this player
		//if the player has no scores, return an empty list of size 0
		//if there is a tie, the list should include all indexes in the ascending order
		ThreeTenDynArray<Integer> topGames = new ThreeTenDynArray<Integer>();
		if(this.count() == 0)
			return new ThreeTenDynArray<Integer>();;
		int maxScore = scores.get(0);
		topGames.append(0);
		for(int i = 1; i < scores.size(); i++){
			if(scores.get(i)> maxScore){
				maxScore = scores.get(i);
				topGames = new ThreeTenDynArray<Integer>();
				topGames.append(i);
			}
			else if (scores.get(i) == maxScore){
				topGames.append(i);
			}
		}
		//O(n) where n is the number of scores in record _if_ appending to list is O(1)
		
		return topGames; //default return, remove/change as needed
		
		
	}
	/**
	* A public method that checks if the two PlayerRecs object are equal
	*by checking their names are the same.
	*
	*@param o a PlayerRec object that will be checked 
	*@return boolean true if they have matching player names, false if otherwise.
	**/
	@Override
	public boolean equals(Object o) {
		// Two PlayerRecs are equal if they have matching player names: return true
		// return false otherwise
		if((o instanceof PlayerRec) && (((PlayerRec)o).name().equals(this.name())))
			return true;
		// remember to check whether the incoming object is an instance of this class
				
		return false; //default return, remove/change as needed
		
	}

	//******************************************************
	//*******     BELOW THIS LINE IS PROVIDED code   *******
	//*******             Do NOT edit code!          *******
	//*******		   Remember to add JavaDoc		 *******
	//******************************************************

	/**
	* An overriden toString() method that displays the scores of the player.
	*@return String the scores of the player
	**/
	@Override
	public String toString() {
		StringBuilder s = new StringBuilder(name()+",");
		s.append(count() + ",");
		s.append(totalScore() + ",");
		s.append(scores.toString());
		return s.toString().trim();
		
	}
	/**
	* A toString method that displays the contents of PlayerRec in a readable format.
	*
	*@return String the details of PlayerRec
	**/
	public String toNiceString() {
		StringBuilder s = new StringBuilder("Player Name: "+ name() + "\n");
		s.append("  Game Count: " + count() + ", ");
		s.append("Total Score: " + totalScore() + "\n");
		s.append("  Scores: ");
		s.append(scores.toString());
		ThreeTenDynArray<Integer> bestGames = getTopGames();
		s.append("\n  Top Games: ");
		s.append(bestGames.toString());
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
	
		//create a player
		PlayerRec player1 = new PlayerRec("George");
		
		if (player1.name().equals("George") && player1.count()==0 
			&& player1.totalScore()==0){
			System.out.println("Yay 1");
		}
		
		//addScore
		if (!player1.addScore(-2) && player1.addScore(2) && player1.addScore(1) 
			&& player1.addScore(5) && player1.count()==3 && player1.totalScore()==8){
			System.out.println("Yay 2");
		}
		
		//uncomment to check details
		System.out.println(player1);
		
		
		//getScore, replaceScore, getTopGames
		if (!player1.replaceScore(5,5)	&& player1.replaceScore(0,5)
			&& player1.getScore(6) == -1 && player1.getScore(0) == 5
			&& player1.getTopGames().toString().equals("[0, 2]")){
			System.out.println("Yay 3");			
		}

		//equals, toString
		PlayerRec player2 = new PlayerRec("Mason");
		PlayerRec player3 = new PlayerRec("George");
		if (!player1.equals(null) && !player1.equals(player2) && player1.equals(player3)
			&& player1.toString().equals("George,3,11,[5, 1, 5]")){
			System.out.println("Yay 4");						
		}
				
		
	}
		
}
