package sliceAndDice;

import sliceAndDice.Data;
import java.util.Scanner;



/**
 * Data class stores data for Players.
 * @author Andrew Stanton, PIN: 817, CSE 360, Spring 2016
 * @version 4/15/16
 */
public class Data {
	int numAborts;
	double score;
	int rank; // may want to eliminte this and just sort by score
	int totalGameCount;
	int totalWinCount;
	int totalNumAttacks;
	//int totalNumBaseAttacks;
	//int totalNumSPAttacks; // do we want to keep track of every type of attack?
	int totalNumMeals; // might want to keep this
	int totalHealthLost;
	int totalManaUsed;
	int totalFoodUsed;
	// float totalGameTime; // we might be able to include this - System.nanotime()
	// float totalTurnTime; // we might be able to include this - System.nanotime()
	//int totalNumTurns; // do we want to have this or not or just display but not store?

	/**
	 * Constructor for Data, setting all of the data but score to 0 and score to 0.
	 */
	Data() {
	score = 1000;
	rank = 0;
	totalGameCount = 0;
	totalWinCount = 0;
	totalNumAttacks = 0;
	//totalNumBaseAttacks = 0;
	//totalNumSPAttacks = 0;
	totalNumMeals = 0;
	totalHealthLost = 0;
	totalManaUsed = 0;
	totalFoodUsed = 0;
	// totalGameTime = 0;
	// totalTurnTime = 0;
	//totalNumTurns = 0;
	}
	/**
	 * Constructor for Data that uses a Scanner to read in data from data.txt.
	 * @param dataReader Scanner used to read in data from data.txt
	 */
	Data(Scanner dataReader) {
		score = dataReader.nextDouble();
		rank = dataReader.nextInt();
		totalGameCount = dataReader.nextInt();
		totalWinCount = dataReader.nextInt();
		totalNumAttacks = dataReader.nextInt();
		//totalNumBaseAttacks = dataReader.nextInt();
		//totalNumSPAttacks = dataReader.nextInt();
		totalNumMeals = dataReader.nextInt();
		totalHealthLost = dataReader.nextInt();
		totalManaUsed = dataReader.nextInt();
		totalFoodUsed = dataReader.nextInt();
	}
	/**
	 * Returns Data in String form with information on separate lines.
	 */
	public String toString() { // put this as a method in Data
		String dataString = "";
		dataString += score + "\n";
		dataString += rank + "\n";
		dataString += totalGameCount + "\n";
		dataString += totalWinCount + "\n";
		dataString += totalNumAttacks + "\n";
		//dataString += totalNumBaseAttacks + "\n";
		//dataString += totalNumSPAttacks + "\n";
		dataString += totalNumMeals + "\n";
		dataString += totalHealthLost + "\n";
		dataString += totalManaUsed + "\n";
		dataString += totalFoodUsed + "\n";
		
		return dataString;
	}
	/**
	 * resets data in Data object to initial setting
	 */
	public void resetData() {
		score = 1000;
		rank = 0;
		totalGameCount = 0;
		totalWinCount = 0;
		totalNumAttacks = 0;
		//totalNumBaseAttacks = 0;
		//totalNumSPAttacks = 0;
		totalNumMeals = 0;
		totalHealthLost = 0;
		totalManaUsed = 0;
		totalFoodUsed = 0;
	}
// replaces an existing Data object with one given, but not needed.
//	void loadData(Data data) {
//	score = data.getScore();
//	rank = data.getRank();
//	totalGameCount = data.getGame();
//	totalWinCount = data.getWin();
//	//totalNumTurns = data.getTurn();
//	totalNumAttacks = data.getAttack();
//	totalNumMeals = data.getMeal();
//	// totalNumSPAttacks = data.getSPAttack();
//	totalHealthLost = data.getHPLost();
//	totalManaUsed = data.getManaUsed();
//	totalFoodUsed = data.getFoodUsed();
//	// totalGameTime = data.getGameTime();
//	// totalTurnTime = data.getTurnTime();
//	}
	/**
	 * Sets the score to the given score.
	 * @param score the new score
	 */
	void setScore(double score) {
		this.score = score;
	}
	/**
	 * Sets the rank to the given rank.
	 * @param rank the new rank
	 */
	void setRank(int rank) {
		this.rank = rank;
	}
	/**
	 * Sets the number of games to the given number.
	 * @param totalGameCount new number of games
	 */
	void setGame(int totalGameCount) {
		this.totalGameCount = totalGameCount;
	}
	 /**
	  * Sets the number of wins to the given number.
	  * @param totalWinCount new number of wins
	  */
	void setWin(int totalWinCount) {
		this.totalWinCount = totalWinCount;
	}
	//void setTurn(int totalNumTurns) {
	//	this.totalNumTurns = totalNumTurns;
	//}
	/**
	 * Sets the number of attacks to the given number.
	 * @param totalNumAttacks new number of attacks
	 */
	void setAttack(int totalNumAttacks) {
		this.totalNumAttacks = totalNumAttacks;
	}
	/**
	 * Sets the number of base attacks to the given number.
	 * @param totalNumbBaseAttacks new number of base attacks
	 */
	void setBaseAttack(int totalNumBaseAttacks) {
		//this.totalNumBaseAttacks = totalNumBaseAttacks;
	}
	/**
	 * Sets the number of special attacks to the given number.
	 * @param totalNumSPAttacks new number of special attacks
	 */
	void setSPAttack(int totalNumSPAttacks) {
		//this.totalNumSPAttacks = totalNumSPAttacks;
	}
	/**
	 * Sets the number of meals to the given number.
	 * @param totalNumMeals new number of meals
	 */
	void setMeal(int totalNumMeals) {
		this.totalNumMeals = totalNumMeals;
	}
	/**
	 * Sets the amount of health points lost to the given number.
	 * @param totalHealthLost new health points lost
	 */
	void setHPLost(int totalHealthLost) {
		this.totalHealthLost = totalHealthLost;
	}
	/**
	 * Sets the amount of mana points used to the given number.
	 * @param totalManaUsed new number of mana points used
	 */
	void setManaUsed(int totalManaUsed) {
		this.totalManaUsed = totalManaUsed;
	}
	/**
	 * Sets the amount of food points used to the given number.
	 * @param totalFoodUsed new amount of food points used
	 */
	void setFoodUsed(int totalFoodUsed) {
		this.totalFoodUsed = totalFoodUsed;
	}
	/**
	 * Returns the score of the Data object.
	 * @return score
	 */
	double getScore() {
		return score;
	}
	/**
	 * Returns the rank of the Data object.
	 * @return rank
	 */
	int getRank() {
		return rank;
	}
	/**
	 * Returns the number of games of the Data object.
	 * @return number of games
	 */
	int getGame() {
		return totalGameCount;
	}
	/**
	 * Returns the number of wins of the Data object.
	 * @return number of wins
	 */
	int getWin() {
		return totalWinCount;
	}
	//int getTurn() {
	//	return totalNumTurns;
	//}
	/**
	 * Returns the number of attacks of the Data object.
	 * @return number of attacks
	 */
	int getAttack() {
		return totalNumAttacks;
	}
	/**
	 * Returns the number of base attacks of the Data object.
	 * @return number of base attacks
	 */
	//int getBaseAttack() {
		//return totalNumBaseAttacks;
	//}
	/**
	 * Returns the number of special attacks of the Data object.
	 * @return number of special attacks
	 */
	//int getSPAttack() {
		//return totalNumSPAttacks;
	//}
	/**
	 * Returns the number of meals of the Data object.
	 * @return number of meals
	 */
	int getMeal() {
		return totalNumMeals;
	}
	/**
	 * Returns the amount of health points lost of the Data object.
	 * @return amount of health points lost
	 */
	int getHPLost() {
		return totalHealthLost;
	}
	/**
	 * Returns the amount of mana points used of the Data object.
	 * @return amount of mana points used
	 */
	int getManaUsed() {
		return totalManaUsed;
	}
	/**
	 * Returns the amount of food points used of the Data object.
	 * @return amount of food points used
	 */
	int getFoodUsed() {
		return totalFoodUsed;
	}
	/**
	 * Increments the Data object's number of games. Called at the end of games.
	 */
	void incrGameCount() {
		totalGameCount++;
	}
	/**
	 * Increments the Data object's number of wins. Called at the end of games if the given Player wins.
	 */
	public void incrWinCount() {
		totalWinCount++;
	}
	/* Not needed now.
	 public void incrNumTurns() {
		totalNumTurns++;
	}
	 */
	/**
	 * Increments the Data object's number of attacks. Called when a Player attacks.
	 */
	public void incrNumAttacks() {
		totalNumAttacks++;
	}
	/**
	 * Increments the Data object's number of base attacks. Called when a Player performs a base attack.
	 */
	public void incrNumBaseAttacks() {
		//totalNumBaseAttacks++;
	}
	/**
	 * Increments the Data object's number of special attacks. Called when a Player performs a special attack.
	 */
	public void incrNumSPAttacks() {
		//totalNumSPAttacks++;
	}
	/**
	 * Increments the Data object's number of meals. Called when a Player heals.
	 */
	public void incrNumMeals() {
		totalNumMeals++;
	}
	/**
	 * Increments the Data object's number of aborted games. Called when a Player ends a Game before a Player wins.
	 */
	public void incrNumAborts() {
		numAborts++;
	}
	/**
	 * Updates the amount of health points a Player has lost. Called at the end of games.
	 * @param finalHP amount of health points the Player has at the end of the game
	 */
	public void updateHealthLost(int finalHP) {
		totalHealthLost += (100 - finalHP); // 100 is maxHP
	}
	/**
	 * Updates the amount of mana points a Player has used. Called at the end of games.
	 * @param finalMana amount of mana points the Player has at the end of the game
	 */
	public void updateManaUsed(int finalMana) {
		totalManaUsed += (30 - finalMana); // 30 is maxMana
	}
	/**
	 * Updates the amount of food points a Player has used. Called at the end of games.
	 * @param finalFood amount of food points the Player has at the end of the game
	 */
	public void updateFoodUsed(int finalFood) {
		totalFoodUsed += (5 - finalFood); // 5 is maxFood
	}
	/* May be implemented. Updates the amount of time the Player has played.
	public void updateTotalGameTime() {
	}
	*/
}