package game;

import java.util.Scanner;

/**
 * This class represents the actions and attributes of a player in a Tic Tac Toe game.
 * 
 * @author Missy Carletta
 */
public class Player {

	/** 
	 * This array allows for input from the console to be easily translated into the correct constant.
	 * This is organized according to the number mapping to cells. For example, the bottom left is
	 * represented as 1 for the player. That means it's the first in the array. Since an array is
	 * zero indexed, the index of the first element is 0. So, you can take the players input, minus
	 * 1 from it, and get the array index to the wanted cell constant.
	 */
	public static int[] cells = { Board.BOTTOM_LEFT, Board.BOTTOM_CENTER, Board.BOTTOM_RIGHT,
		Board.MIDDLE_LEFT, Board.MIDDLE_CENTER, Board.MIDDLE_RIGHT,
		Board.TOP_LEFT, Board.TOP_CENTER, Board.TOP_RIGHT };

	/** The symbol of this player. Typically either 'X' or 'O'. */
	private char symbol;
	/** The board this player is playing on. */
	private Board board;

	private Scanner in;

	/**
	 * This sets up the Player object. In this call, all it does is
	 * set values to the two instance variables.
	 * 
	 * @param symbol
	 *            the play character
	 * @param board
	 *            the board to play on
	 */
	public Player(char symbol, Board board) {
		this.symbol = symbol;
		this.board = board;
	}

	/**
	 * Make a play at the designated cell. If the designated cell is
	 * not empty, method will loop until an empty cell is selected.
	 * 
	 * @param cell
	 *            the cell to make a play in
	 * @return true if the player won the game, false otherwise
	 */
	public boolean makePlay(int cell) {
		
		// This loop will run forever unless a break statement is used
		while (true) {
			
			// Grabs cell's contents and checks to see if it's empty
			if (board.getCellContents(cell) != Board.EMPTY) {
				
				in = new Scanner(System.in);
				System.out.print("Must choose an empty cell. Try again: ");
				cell = cells[in.nextInt() - 1];
				
			// User chose an empty cell, so break the loop and continue
			} else {
				break;
			}
		}
		
		// Actually make the mark on the board
		return board.mark(cell, symbol);
	}

	/**
	 * Retrieves this player's name.
	 * 
	 * @return the name of the player
	 */
	public String getName() {
		return "Player " + symbol;
	}

}
