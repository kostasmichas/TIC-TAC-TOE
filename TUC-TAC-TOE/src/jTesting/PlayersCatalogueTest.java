package jTesting;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import control.GameController;
import model.PlayersCatalogue;

class PlayersCatalogueTest {

	PlayersCatalogue playersCat;
	GameController gc;
	
	@BeforeEach
	void init(){
		GameController gc = new GameController();
		
		gc.start();
		playersCat = new PlayersCatalogue(gc);
	}
	
	@Test
	@DisplayName("Checking Capacity of Players List")
	void testInitialCapacity() {
		assertEquals(playersCat.getPlayers().length, 20, "Initial Capacity of Players List should be 20" );
	}
	
	@Test
	@DisplayName("Check starting Players")
	void testStartingPlayers(){
		assertEquals(playersCat.getPlayer(0).getName(), "Vasilis");
		assertEquals(playersCat.getPlayer(1).getName(), "Nektarios");
		assertEquals(playersCat.getPlayer(2).getName(), "Yannis");
		assertEquals(playersCat.getPlayer(3).getName(), "Eleni");
		assertEquals(playersCat.getPlayer(4).getName(), "Mr.Bean");
	}
	
	@Test
	@DisplayName("Test adding Player")
	void testAddingPlayer(){
		playersCat.addPlayer("Michalis");
		assertEquals(playersCat.getPlayer(5).getName(), "Michalis", "Michalis should be placed in place 5 of the array");
	
		
	}
	
	@Test
	@DisplayName("Test del Player")
	void testDelPlayer() {
		
		
		playersCat.delPlayer("Eleni");
		assertEquals(playersCat.getPlayer(4), null, "Eleni should be removed from the  array");  
		assertEquals(playersCat.getPlayer(3).getName(), "Mr.Bean", "Mr.Bean  shifts down in the  array");  
		
	}
	
	@Test
	@DisplayName("Test num Of Players")
	void testNumOfPlayers() {
		
		assertEquals(playersCat.numOfPlayers(), 5);
		
	}
	
	
	@Test
	@DisplayName("Test set/get Wins")
	void testSetWins() {
		playersCat.getPlayer(0).setWins(0);
		playersCat.setPlayerWin(playersCat.getPlayerName(0));
		
		assertEquals(playersCat.getPlayer(0).getWins(), 1);
		
		
	}
	
	@Test
	@DisplayName("Test set/get Looses")
	void testSetLooses(){
		playersCat.getPlayer(0).setLooses(0);
		playersCat.setPlayerLoose(playersCat.getPlayerName(0));
		
		assertEquals(playersCat.getPlayer(0).getLooses(), 1);
		
	}
	
	
	@Test
	@DisplayName("Test set/get Games Played")
	void testSetGames(){
		playersCat.getPlayer(0).setGamesPlayed(0);
		playersCat.setPlayerGames(playersCat.getPlayerName(0));
		
		assertEquals(playersCat.getPlayer(0).getGamesPlayed(),1 );
	
	}
	

		
	

}
