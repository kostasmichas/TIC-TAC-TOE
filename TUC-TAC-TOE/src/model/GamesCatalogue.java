package model;

import java.io.Serializable;

import control.GameController;

public class GamesCatalogue implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6L;

	private Game[] games;
	
	private int numOfGames;
	

	GameController gc;
	
	public GamesCatalogue( GameController gc ) {
		
		games = new Game[100];
		this.gc = gc;
		this.numOfGames = 0;
	
		
	}

	public Game[] getGames() {
		return games;
	}
	public Game getGame( int i ) {
		return games[i];
	}

	public void addGame(Game game) {
		this.games[numOfGames] = game;
		numOfGames++;
	}

	
	public void printAllGames(){
		System.out.println("============ALL GAMES=============");
		for( int j = 0; j<this.numOfGames;j++) {
			if(games[j] != null && games[j].getWinnersName() != null && games[j].getLoosersName() != null) {
				System.out.println("Game: "+(j+1));
				System.out.println("Winner: "+games[j].getWinnersName());
				System.out.println("Looser: "+games[j].getLoosersName());
				System.out.println("");
				
			}else if(games[j] != null) {
				System.out.println("Game: "+(j+1));
				System.out.println("Player 1: "+games[j].getPlayers0Name());
				System.out.println("Player 2: "+games[j].getPlayers1Name());
				System.out.println("Its a tie!");
				System.out.println("");
			}
		}
		System.out.println("==================================");
		
	}
	
	
	
}


