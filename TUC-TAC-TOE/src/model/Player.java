package model;

import java.io.Serializable;

public class Player implements Serializable {

    private static final long serialVersionUID = 1L;

	
	private String name;
	private int gamesPlayed;
	private float score;
	private int wins;
	private int looses;
	private int ties;
	
	private int numOfGames;
	
	
	private Game[] allGames;
	private Game[] topGames;
	private Game[] top5Games;
	private Game[] latestGames;
	
	public Player( String name) {
		this.name = name;
		this.gamesPlayed = 0;
		this.score = 0;
		this.wins = 0;
		this.looses = 0;
		this.ties = 0;
		this.numOfGames = 0;

		this.top5Games = new Game[5];
		this.latestGames = new Game[5];
		this.allGames = new Game[100];
	}


	public int getGamesPlayed() {
		return gamesPlayed;
	}


	public void setGamesPlayed(int gamesPlayed) {
		this.gamesPlayed = gamesPlayed;
	}


	public float getScore() {
		return score;
	}


	public void setScore(float score) {
		this.score = score;
	}


	public int getWins() {
		return wins;
	}


	public void setWins(int wins) {
		this.wins = wins;
	}


	public int getLooses() {
		return looses;
	}


	public void setLooses(int looses) {
		this.looses = looses;
	}


	public String getName() {
		if(name != null) {
			return name;
		}else {
			return null;
		}
		
			
	}


	public int getTies() {
		return ties;
	}


	public void setTies() {
		this.ties++;
	}
	
	
	public void setAllGames(Game game) {
		this.allGames[numOfGames] = game;
		numOfGames++;
	}
	public Game getAllGames(int i){
		if( i < 100 && i>= 0) {
			if( this.allGames[i] != null) {
				return allGames[i];
			}
		}
		return null;
	}


	public void setLatestGame( Game game ) {

		if(this.latestGames[0] == null && this.latestGames[1] == null && this.latestGames[2] == null && this.latestGames[3] == null && this.latestGames[4] == null ) {
			this.latestGames[0] = game;
			return;
		}
		if(this.latestGames[0] != null && this.latestGames[1] == null && this.latestGames[2] == null && this.latestGames[3] == null && this.latestGames[4] == null ) {
			this.latestGames[1] = this.latestGames[0];
			this.latestGames[0] = game;
			return;
		}
		if(this.latestGames[0] != null && this.latestGames[1] != null && this.latestGames[2] == null && this.latestGames[3] == null && this.latestGames[4] == null ) {
			this.latestGames[2] = this.latestGames[1];
			this.latestGames[1] = this.latestGames[0];
			this.latestGames[0] = game;
			return;
		}
		if(this.latestGames[0] != null && this.latestGames[1] != null && this.latestGames[2] != null && this.latestGames[3] == null && this.latestGames[4] == null ) {
			this.latestGames[3] = this.latestGames[2];
			this.latestGames[2] = this.latestGames[1];
			this.latestGames[1] = this.latestGames[0];
			this.latestGames[0] = game;
			return;
		}
		if(this.latestGames[0] != null && this.latestGames[1] != null && this.latestGames[2] != null && this.latestGames[3] != null && this.latestGames[4] == null ) {
			this.latestGames[4] = this.latestGames[3];
			this.latestGames[3] = this.latestGames[2];
			this.latestGames[2] = this.latestGames[1];
			this.latestGames[1] = this.latestGames[0];
			this.latestGames[0] = game;
			return;
		}
		if(this.latestGames[0] != null && this.latestGames[1] != null && this.latestGames[2] != null && this.latestGames[3] != null && this.latestGames[4] != null ) {
			this.latestGames[4] = null;
			this.latestGames[4] = this.latestGames[3];
			this.latestGames[3] = this.latestGames[2];
			this.latestGames[2] = this.latestGames[1];
			this.latestGames[1] = this.latestGames[0];
			this.latestGames[0] = game;
			return;
		}
	
			
	}
	
	public Game getLatestGame( int i ) {
		
		if( i>=0 && i < 5 ) {
			if( latestGames[i] != null ) {
				return latestGames[i];
			}
		}
			return null;
		
	}
	
	public void printLatestGames() {
		
		System.out.println(this.name+"'s 5 Latest Games: ");
		
		for(int j = 0; j<5;j++) {
			if(latestGames[j] != null ) {
				System.out.println((j+1)+". "+latestGames[j].getPlayers0Name()+" vs "+latestGames[j].getPlayers1Name());
			}
			
		}
		
	}
	
	public void setBestGames( Game game ) {
		
		this.topGames = new Game[numOfGames];

		for(int j = 0;j<numOfGames;j++) {
			this.topGames[j] = allGames[j];
		}
		
	
	        for (int i = 0; i < numOfGames; i++) {
		            if( topGames[i].getWinnersName() == null && topGames[i].getLoosersName() == null ) {
		            	topGames[i].setWinTieLoss(2);
		            }else if (topGames[i].getWinnersName().equals(this.getName())) {
			               topGames[i].setWinTieLoss(1);
			            }
		            else {
		            	topGames[i].setWinTieLoss(3);
		            }
		           
		        }
	        
	        boolean sorted = false;         //sort players by win/ties/looses ( lower to highest )
			 Game temp;
			    while(!sorted) {
			        sorted = true;
			        for (int u = 0; u < numOfGames - 1; u++) {
			            if (topGames[u].getWinTieLoss() > topGames[u+1].getWinTieLoss()) {
			                temp = topGames[u];
			                topGames[u] = topGames[u+1];
			                topGames[u+1] = temp;
			                sorted = false;
			            }else if( (topGames[u].getWinTieLoss() == topGames[u+1].getWinTieLoss()) && (topGames[u].getOpponent(getName()).getScore() < topGames[u+1].getOpponent(getName()).getScore())){
			            	 temp = topGames[u];
				                topGames[u] = topGames[u+1];
				                topGames[u+1] = temp;
				                sorted = false;
			            }/*else if((topGames[u].getWinTieLoss() == topGames[u+1].getWinTieLoss()) && (topGames[u].getOpponent(getName()).getScore() == topGames[u+1].getOpponent(getName()).getScore()) && (topGames[u].date().compareTo(topGames[u+1].date()) == 1 )) {
			            	 temp = topGames[u];
				                topGames[u] = topGames[u+1];
				                topGames[u+1] = temp;
				                sorted = false;
			            }*/
			        }
			    }
	        
			    System.out.println(this.getName()+": ");     //print shorted games
			    for(int y = 0; y<numOfGames; y++) {
			    	
			    	  System.out.println("Game "+(y+1)+": ");
			           if(topGames[y].getWinnersName() != null && topGames[y].getLoosersName() != null) {
			        	   System.out.println("Winner: "+topGames[y].getWinnersName());
			        	   System.out.println("Looser: "+topGames[y].getLoosersName());
			        	   System.out.println("");

			           }else {
			        	   System.out.println("Its a tie!");
			        	   System.out.println("");
			           }  
			    }
			     
			    for(int o = 0;o<numOfGames;o++) {
			    	if( (o <  5) && topGames[o] != null ) {
			    		top5Games[o] = topGames[o];
			    	}
			    }
			    
	        
		    }  
		
		public void printTop5Games() {
			
			System.out.println("========TOP 5 GAMES=========");
			System.out.println("");
			System.out.println(this.getName()+": ");
			
			for( int i = 0; i<numOfGames;i++) {
				if( i < 5 ) {
					System.out.println("Winner: "+top5Games[i].getWinnersName());
		        	System.out.println("Looser: "+top5Games[i].getLoosersName());
		        	System.out.println("");
				}
			}
			
			System.out.println("============================");

		}


		public Game[] getAllGames() {
			return allGames;
		}


		public Game[] getTop5Games() {
			return top5Games;
		}


		public Game[] getLatestGames() {
			return latestGames;
		}
		
		
		
}    
	

