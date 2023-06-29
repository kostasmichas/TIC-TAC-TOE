package jTesting;


import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import control.GameController;
import model.Game;
import model.PlayersCatalogue;


class PlayerTest {
	
	GameController gc;
	PlayersCatalogue cat;
	Game gameTest;

	@BeforeEach
	void init() {
		
		cat = new PlayersCatalogue(gc);
		cat.addPlayer("testPlayer1");
		cat.addPlayer("testPlayer2");

		
	}
	
	@Test
	@DisplayName("Test starting lists capacity")
	void testStart() {
		assertEquals(cat.getPlayer("testPlayer1").getAllGames().length, 100);
		assertEquals(cat.getPlayer("testPlayer1").getTop5Games().length,5 );
		assertEquals(cat.getPlayer("testPlayer1").getLatestGames().length, 5);
	}
	
	@Test
	@DisplayName("Set all games test")
	void testAllGames(){
		gameTest = new Game(cat);
	
		gameTest.setPlayers0("testPlayer1");
		gameTest.setPlayers1("testPlayer2");
		
		cat.getPlayer("testPlayer1").setAllGames(gameTest);
		assertEquals(cat.getPlayer("testPlayer1").getAllGames(0), gameTest );
	}
	
	@Test
	@DisplayName("Test Latest Games")
	void testLatestGames(){
		gameTest = new Game(cat);
		
		gameTest.setPlayers0("testPlayer1");
		gameTest.setPlayers1("testPlayer2");
		
		cat.getPlayer("testPlayer1").setLatestGame(gameTest);
		
		Game gameTest2 = new Game(cat);
		
		gameTest2.setPlayers1("testPlayer1");
		gameTest2.setPlayers0("testPlayer2");
		
		cat.getPlayer("testPlayer1").setLatestGame(gameTest2);
		
		assertEquals(cat.getPlayer("testPlayer1").getLatestGame(1), gameTest);
		assertEquals(cat.getPlayer("testPlayer1").getLatestGame(0), gameTest2);

		cat.getPlayer("testPlayer1").printLatestGames();
		
	}
	
	
	

}
