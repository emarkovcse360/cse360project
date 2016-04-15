package sliceAndDice;

import java.util.ArrayList;
enum Winner {NONE, PLAYER_ONE, PLAYER_TWO};
enum IllegalMove {NONE, NOFOOD, NOMANA, NOTIMPLEMENTED};

public class Game {
	Player playerOne;
	Player playerTwo;
	int winnerID;
	int loserID;
	Status playerOneStatus;
	Status playerTwoStatus;
	int totalTurns;
	boolean playerOneTurn;
	Turn nextTurn;
	
	Game(String playerOne, String playerTwo) {
		totalTurns = 0;
		this.playerOne = Scoreboard2.getPlayerByUsername(playerOne);
		this.playerTwo = Scoreboard2.getPlayerByUsername(playerOne);
		playerOneStatus = new Status();
		playerTwoStatus = new Status();
		playerOneTurn = true;
	}

	Winner  PlayNextTurn(Move nextMove) {
		Winner gameWinner = Winner.NONE;
		
		if(playerOneTurn){	// Determines whose move it is
			nextTurn = new Turn(playerOneStatus, playerTwoStatus);
			gameWinner = nextTurn.playTurnPlayerOne(nextMove);
			playerOneTurn = false;
			updateMoveCount(playerOne, nextMove);
		}
		else{
			gameWinner = nextTurn.playTurnPlayerTwo(nextMove);
			playerOneTurn = true;
			updateMoveCount(playerTwo, nextMove);
		}

		totalTurns++;
		
		if(gameWinner == Winner.PLAYER_ONE){
			winnerID = playerOne.getID();
			loserID = playerTwo.getID();
			playerOne.getPlayerData().incrWinCount();
		}
		else if(gameWinner == Winner.PLAYER_TWO){
			winnerID = playerTwo.getID();
			loserID = playerOne.getID();
			playerTwo.getPlayerData().incrWinCount();
		}
		
		return gameWinner;
		
	}
	IllegalMove nextMoveLegality(Move nextMove) {
		IllegalMove violation = IllegalMove.NONE;
		if(playerOneTurn) {
			violation = Turn.moveIsLegal(playerOneStatus, nextMove);
		}
		else {
			violation = Turn.moveIsLegal(playerTwoStatus, nextMove);
		}
		return violation;
	}
	boolean isPlayerOneTurn(){
		return playerOneTurn;
	}
	Status getPlayerOneStatus() {
		return playerOneStatus;
	}
	Status getPlayerTwoStatus() {
		return playerTwoStatus;
	}
	int getWinnerID() {
		return winnerID;
	}
	int getLoserID() {
		return loserID;
	}
	int getTotalTurns() {
		return totalTurns;
	}
	int[] getLastRoll() {
		return nextTurn.getLastRoll();
	}
	void updateStats() {
		playerOne.getPlayerData().incrGameCount();
		playerOne.getPlayerData().updateHealthLost(playerOneStatus.getHitPts());
		playerOne.getPlayerData().updateManaUsed(playerOneStatus.getMana());
		playerOne.getPlayerData().updateFoodUsed(playerOneStatus.getFoodCount());
		playerTwo.getPlayerData().incrGameCount();
		playerTwo.getPlayerData().updateHealthLost(playerTwoStatus.getHitPts());
		playerTwo.getPlayerData().updateManaUsed(playerTwoStatus.getMana());
		playerTwo.getPlayerData().updateFoodUsed(playerTwoStatus.getFoodCount());
	}
	void updateMoveCount(Player turnPlayer, Move turnPlayerMove) {
		switch (turnPlayerMove) {
		case ATTACK:
			turnPlayer.getPlayerData().incrNumAttacks();
			break;
		case FOOD:
			turnPlayer.getPlayerData().incrNumMeals();
			break;
		case FREEZE:
			break;
		case DOUBLEATK:
			break;
		case SPATK3:
			break;
		case SPATK4:
			break;
		default:
			throw new IllegalArgumentException("Error: Illegal move not caught.");	
		}

	}
}

class Turn {
	Status statusP1;
	Status statusP2;
	static int[] lastRoll;

	Turn(Status statusP1, Status statusP2) {
		this.statusP1 = statusP1;
		this.statusP2 = statusP2;
	}
	
	Winner playTurnPlayerOne(Move moveP1) {
		Winner gameWinner = Winner.NONE;	
		
		if(Turn.moveIsLegal(statusP1, moveP1) != IllegalMove.NONE) {
			throw new IllegalArgumentException("Error: Illegal move not caught.");
		}
		
		playNextTurn(moveP1, statusP1, statusP2);
		
		if(statusP2.getHitPts() == 0) {
			// Return if player one has won
			gameWinner = Winner.PLAYER_ONE;
		}
		return gameWinner;
	}
	
	Winner playTurnPlayerTwo(Move moveP2){	
		Winner gameWinner = Winner.NONE;
		
		if(Turn.moveIsLegal(statusP2, moveP2) != IllegalMove.NONE) {
				throw new IllegalArgumentException("Error: Illegal move not caught.");
		}
		
		playNextTurn(moveP2, statusP2, statusP1);
			
		if(statusP1.getHitPts() == 0) {
			gameWinner = Winner.PLAYER_TWO;
		}

		return gameWinner;
	}
	void playNextTurn(Move nextMove, Status turnPlayer, Status otherPlayer) {
		switch(nextMove) {
		case ATTACK:
			attack(turnPlayer, otherPlayer);
			break;
		case FOOD:
			food(turnPlayer);
			break;
		case FREEZE:
			freeze(turnPlayer, otherPlayer);
			break;
		case DOUBLEATK:
			doubleAtk(turnPlayer, otherPlayer);
			break;
		case SPATK3:
			spAtk3(turnPlayer, otherPlayer);
			break;
		case SPATK4:
			spAtk4(turnPlayer, otherPlayer);
			break;
		default:
			throw new IllegalArgumentException("Error: Illegal move not caught.");	
		}
	}
	int[] getLastRoll(){
		return lastRoll;
	}

	static IllegalMove moveIsLegal(Status turnPlayer, Move nextMove) {
		IllegalMove violation = IllegalMove.NONE;
		if(nextMove == Move.FOOD && turnPlayer.getFoodCount() == 0) {
			violation = IllegalMove.NOFOOD;
		}
		else if (nextMove != Move.ATTACK) {
			violation = IllegalMove.NOTIMPLEMENTED;
		}
		return violation;
	}
	
	void attack(Status turnPlayer, Status otherPlayer) {
		// Roll 4 dice, do damage equal to combined result.
		int numRoll = 4;
		lastRoll = DiceRoll.roll(numRoll);
		int sumDamage = 0;
		for(int rollCount = 0; rollCount < numRoll; rollCount++) {
			sumDamage += lastRoll[rollCount];
		}
		
		int oppHP = otherPlayer.getHitPts();
		if(oppHP <= sumDamage) {
			otherPlayer.setHitPts(0);
		}
		else {
			otherPlayer.setHitPts(oppHP - sumDamage);
		}
	}
	
	void food(Status turnPlayer) {
		// Add 25 hp, do not overmax hp
		int healValue = 25;
		int currHP = turnPlayer.getHitPts();
		if(currHP + healValue > Status.getMaxHP()) {
			turnPlayer.setHitPts(Status.getMaxHP());
		}
		else {
			turnPlayer.setHitPts(currHP + healValue);
		}
		turnPlayer.reduceFoodCount();
	}
	
	void freeze(Status turnPlayer, Status otherPlayer) {
		throw new IllegalArgumentException("Error: Special attacks not yet implemented.");
	}
	
	void doubleAtk(Status turnPlayer, Status otherPlayer) {
		throw new IllegalArgumentException("Error: Special attacks not yet implemented.");
	}
	
	void spAtk3(Status turnPlayer, Status otherPlayer) {
		throw new IllegalArgumentException("Error: Special attacks not yet implemented.");
	}
	
	void spAtk4(Status turnPlayer, Status otherPlayer) {
		throw new IllegalArgumentException("Error: Special attacks not yet implemented.");
	}
}

