package jTesting;


import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import control.GameController;
import model.Game;
import model.PlayersCatalogue;

class GameTest {
	
	PlayersCatalogue cat;
	GameController gc;
	Game testGame;
	

	@BeforeEach
	void init(){
		
		cat = new PlayersCatalogue(gc);
		testGame = new Game(cat);
		
	}
	
	@Test
	@DisplayName("Setting players 0 and 1")
	void playersTest(){
	
		cat.addPlayer("testPlayer1");
		cat.addPlayer("testPlayer2");
		
		testGame.setPlayers0("testPlayer1");
		testGame.setPlayers1("testPlayer2");
		
		assertEquals(testGame.getPlayers0Name(),"testPlayer1" );
		assertEquals(testGame.getPlayers1Name(),"testPlayer2" );
	
	}
	
	@Test
	@DisplayName("Get opponent Test")
	void testOpponent(){
		
		cat.addPlayer("testPlayer1");
		cat.addPlayer("testPlayer2");
		
		testGame.setPlayers0("testPlayer1");
		testGame.setPlayers1("testPlayer2");
		
		assertEquals(testGame.getOpponent("testPlayer1").getName(),"testPlayer2");
	}
	
	@Test
	@DisplayName("Test set/get winner")
	void testWinner(){
		cat.addPlayer("testPlayer1");
		cat.addPlayer("testPlayer2");
		
		testGame.setPlayers0("testPlayer1");
		testGame.setPlayers1("testPlayer2");
		
		testGame.setWinner("testPlayer1");
		testGame.setLooser("testPlayer2");
		
		assertEquals(testGame.getWinnersName(), "testPlayer1");
		assertEquals(testGame.getLoosersName(), "testPlayer2");
		
	}
}
