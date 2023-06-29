package model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Game implements Serializable {
	
	private static final long serialVersionUID = 2L;

	private Player[] players;
	private PlayersCatalogue cat;
	
	private long timeElapsed;
	private long start;
	private long finish;

	private String date;
	
	private int winTieLoss;
	
	private Player winner;
	private Player looser;
	
	public Game(PlayersCatalogue cat){
		players = new Player[2];
		this.cat = cat;
		this.timeElapsed = 0;
		this.winTieLoss = 0;
		this.date = null;
	}

	public Player[] getPlayers() {
		return players;
	}

	public void setPlayers0 (String player) {
		Player[] allPlayers = new Player[cat.numOfPlayers()];
		allPlayers = cat.getPlayers();
		for(int j = 0;j<cat.numOfPlayers();j++) {
			allPlayers[j] = cat.getPlayer(j);
		}
		
		
		for(int i = 0; i<cat.numOfPlayers();i++) {
			if( allPlayers[i].getName().equals(player) ) {
				players[0] = allPlayers[i];
			
			}
			
		}
		
	}
	
	
	
	
	public void setPlayers1(String player) {
		Player[] allPlayers = new Player[cat.numOfPlayers()];
		allPlayers = cat.getPlayers();
		for(int j = 0;j<cat.numOfPlayers();j++) {
			allPlayers[j] = cat.getPlayer(j);
		}
		
		
		for(int i = 0; i<cat.numOfPlayers();i++) {
			if( allPlayers[i].getName().equals(player) ) {
				players[1] = allPlayers[i];
			
			}
			
		}
	}

	
	
	public String getPlayers0Name() {
		return players[0].getName();
	}
	public String getPlayers1Name() {
		return players[1].getName();
	}

	public String getWinnersName() {
		if( winner != null ) {
			return winner.getName();
		}else {
			return null;
		}
		
	}
	public Player getOpponent(String pName) {
		if( pName.equals(this.getPlayers0Name())) {
			return this.players[1];
		}else {
			return this.players[0];
		}
	}

	public int getWinTieLoss() {
		return winTieLoss;
	}

	public void setWinTieLoss(int winTieLoss) {
		this.winTieLoss = winTieLoss;
	}

	public void setWinner(String winner) {
		Player[] allPlayers = new Player[cat.numOfPlayers()];
		allPlayers = cat.getPlayers();
		for(int j = 0;j<cat.numOfPlayers();j++) {
			allPlayers[j] = cat.getPlayer(j);
		}
		
		
		for(int i = 0; i<cat.numOfPlayers();i++) {
			if( allPlayers[i].getName().equals(winner) ) {
				this.winner = allPlayers[i];
			
			}
			
		}
	}

	public String getLoosersName() {
		if( looser != null ) {
			return looser.getName();
		}else {
			return null;
		}
		
	}

	public void setLooser(String looser) {
		Player[] allPlayers = new Player[cat.numOfPlayers()];
		allPlayers = cat.getPlayers();
		for(int j = 0;j<cat.numOfPlayers();j++) {
			allPlayers[j] = cat.getPlayer(j);
		}
		
		
		for(int i = 0; i<cat.numOfPlayers();i++) {
			if( allPlayers[i].getName().equals(looser) ) {
				this.looser = allPlayers[i];
			
			}
			
		}
	}
	
	public void startTimer(){
		 start = System.currentTimeMillis();
	}
	
	public void stopTimer() {
		 finish = System.currentTimeMillis();
		 this.timeElapsed = this.finish - this.start;
	}
	public long getTime() {
		 return (timeElapsed/1000);
	}
	
	public void getDate() {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");  
		LocalDateTime now = LocalDateTime.now();   
		this.date = dtf.format(now);
		System.out.println("Date: "+date); 
		
	}
	public String date(){
		return date;
	}
	
}
