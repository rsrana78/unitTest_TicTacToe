package gameTest;

import static org.junit.Assert.*;
import game.Board;
import game.Runner;

public class Test {
	
		Runner run = new Runner();
		Board board = new Board();

	@org.junit.Test
	public void testPrintBoard() {
		assertEquals("Return null",null,Runner.playGame(board));
	}

}
