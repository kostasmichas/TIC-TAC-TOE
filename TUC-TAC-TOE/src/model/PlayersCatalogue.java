package model;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import javax.swing.JOptionPane;

import control.GameController;




public class PlayersCatalogue implements Serializable{
	
    

	private static final long serialVersionUID = 4L;

	private Player[] players;
	
	GameController gc;
	
	public PlayersCatalogue(GameController gc) {
		this.gc = gc;
		players = new Player[20];
		players[0]= new Player("Vasilis");
		players[1]=new Player("Nektarios");
		players[2]=new Player("Yannis");
		players[3]=new Player("Eleni");
		players[4]=new Player("Mr.Bean");
		players[5]=new Player("Hall");
	}
	
	
	
	
	public void loadPlayers(){
		
		ObjectInputStream is = null;
		FileInputStream fis = null;		
		int pos = 0;
		
		try {
			fis = new FileInputStream("Players.txt");			
			is = new ObjectInputStream(fis);			
			while (fis.available()>0) {				
				Player s = (Player)is.readObject();				
				this.players[pos++] = s;

			}
			
			System.out.println("Loaded " + numOfPlayers() + " players!");
			System.out.println("............................................");
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		}catch( EOFException e ) {
			System.out.println("No Players Found to Load!");
		}
		catch (IOException e) {			
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			System.out.println("Class not found for read objects...");
		}finally {
			try {is.close(); fis.close();}catch (Exception e) {
			}
		}
		
		
	}
		public void storePlayers() {
		ObjectOutputStream os = null;
		FileOutputStream fos = null;
		           //
		try {
			fos = new FileOutputStream("Players.txt");			
			os = new ObjectOutputStream(fos);
			
			for (Player s: players) {
				os.writeObject(s);
			}
			System.out.println("All players stored successfully....");
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		} catch (IOException e) {			
			e.printStackTrace();
		}finally {
			try {os.close(); fos.close();}catch (Exception e) {
			}
		}
	}
	
	
	
	
	
	public void addPlayer( String player ) {
		for (int i = 0;i<20;i++) {
			if( players[i] ==null ) {			
				this.players[i] = new Player(player);
				System.out.println("Player: "+player+" succesfully added!");
				return;
			}
			
		}
		System.out.println("No more Players can be stored!");
		JOptionPane.showMessageDialog(gc.getView(), 						
				"No more Players can be stored!",
				"Ooops...",
				JOptionPane.ERROR_MESSAGE);
		
	}
	
	
	public void delPlayer( String player) {
		
		for (int i = 0;i<numOfPlayers();i++) {
			if( ( (players[i].getName().equals(player) && gc.getView().getLeftPanel().getCurrentPlayer() == 
					null) && (gc.getView().getRightPanel().getCurrentPlayer() == null ) ) ||(( players[i].getName().equals(player )) && (gc.getView().getLeftPanel().getCurrentPlayer().equals(player) ==
					false) && (gc.getView().getRightPanel().getCurrentPlayer().equals(player) == false ))) {		
				
				this.players[i] = null;
				while( i < numOfPlayers() ) {
					this.players[i] = this.players[i+1];
					i++;
				}
				System.out.println("Player: "+player+" succesfully deleted!");
				return;
			}
			
		}
		System.out.println("No Player found with name: "+player+"! or Player Already Selected");
		JOptionPane.showMessageDialog(gc.getView(), 						
				"Error",
				"Ooops...",
				JOptionPane.ERROR_MESSAGE);     
	  
		
	}
	
	
	public Player getPlayer(int i) {
		if (i<0 || i>19) {
			return null;
		}
		if(players[i] == null)
			return null;
		else
		return players[i];
	}
	
	
	
	public String getPlayerName(int i) {
		if (i<0 || i>19) {
			return null;
		}
		return players[i].getName();
	}
	
	
	public String[] getPlayersName() {
		String playerCat[] = new String[numOfPlayers()];
		for( int i = 0; i<20; i++) {
			
			if(players[i] != null) {
				playerCat[i] = players[i].getName();
			}
			
		}
		return playerCat;
		
	}
	
	
	public int numOfPlayers() {
		
		int num = 0;
		for(int j=0;j<20;j++) {
			if( players[j] != null ) {
				num++;
			}	
		}
		return num;
	}
	
	public int setPlayerWin( String pName ) {
		for(int i = 0; i<numOfPlayers(); i++) {
			if( players[i].getName().equals(pName)) {
				players[i].setWins(players[i].getWins() + 1);
				return players[i].getWins();
			}
		}
		return 0;   
		
	}
	
	public int setPlayerLoose( String pName ) {
		for(int i = 0; i<numOfPlayers(); i++) {
			if( players[i].getName().equals(pName)) {
				players[i].setLooses(players[i].getLooses() + 1);
				return players[i].getLooses();
			}
		}
		return 0;   
	
	}
	
	public int getPlayerLoose( String pName ) {
		for(int i = 0; i<numOfPlayers(); i++) {
			if( players[i].getName().equals(pName)) {
				
				return players[i].getLooses();
			}
		}
		return 0;   
	
	}
	
	
	public int getPlayerWin( String pName ) {
		for(int i = 0; i<numOfPlayers(); i++) {
			if( players[i].getName().equals(pName)) {
				
				return players[i].getWins();
			}
		}
		return 0;   
		
	}	
	
	public int setPlayerGames( String pName ) {
		for(int i = 0; i<numOfPlayers(); i++) {
			if( players[i].getName().equals(pName)) {
				players[i].setGamesPlayed(players[i].getGamesPlayed() + 1);
				return players[i].getGamesPlayed();
			}
		}
		return 0;   
		
		
	}
	
	public int getPlayerGames( String pName) {
		for(int i = 0; i<numOfPlayers(); i++) {
			if( players[i].getName().equals(pName)) {
				return players[i].getGamesPlayed();
			}
		}
		return 0;
	}
	
	
	public float setPlayerScore( String pName ){
		
		for(int i = 0; i<numOfPlayers(); i++) {
			if( players[i].getName().equals(pName)) {
				players[i].setScore(50*(( 2*players[i].getWins()+players[i].getTies() ) / ( float ) players[i].getGamesPlayed())  );
				return players[i].getScore();
			}
		}
		return 0;
		
	}
	
	
	public float getPlayerScore( String pName ){
		
		for(int i = 0; i<numOfPlayers(); i++) {
			if( players[i].getName().equals(pName)) {
				return players[i].getScore();
			}
		}
		return 0;
		
	}

	public int getPlayerTies( String pName ){
		
		for(int i = 0; i<numOfPlayers(); i++) {
			if( players[i].getName().equals(pName)) {
				return players[i].getTies();
			}
		}
		return 0;
		
	}


	public Player[] getPlayers() {
		return players;
	}
	
	public Player getPlayer( String pName ) {

		for(int i = 0; i<numOfPlayers(); i++) {
			if( players[i].getName().equals(pName)) {
				return players[i];
			}
		}
		return null;
	}
	
}

