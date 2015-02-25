package game;

/**
 * This class represents the actions and attributes of a board in a Tic Tac Toe game.
 * 	
 * @author Missy Carletta
 */
public class Board {
	
	/**
	 * These are constants that correspond to their described cell.
	 * These are set up for easy information extraction. The method
	 * deals with its position in the array. It's a little confusing,
	 * hence why I didn't have you work on this class. Look at the 
	 * getColumn and getRow methods for more explanation, but you
	 * should be able to notice a pattern below.
	 */
	// Corresponds to index [0][0]
	public static final int TOP_LEFT = 0;
	
	// Corresponds to index [0][1]
	public static final int TOP_CENTER = 1;
	
	// Corresponds to index [0][2]
	public static final int TOP_RIGHT = 2;
	
	// Corresponds to index [1][0]
	public static final int MIDDLE_LEFT = 10;
	
	// Corresponds to index [1][1]
	public static final int MIDDLE_CENTER = 11;
	
	// Corresponds to index [1][2]
	public static final int MIDDLE_RIGHT = 12;
	
	// Corresponds to index [2][0]
	public static final int BOTTOM_LEFT = 20;
	
	// Corresponds to index [2][1]
	public static final int BOTTOM_CENTER = 21;
	
	// Corresponds to index [2][2]
	public static final int BOTTOM_RIGHT = 22;
	
	/** Represents an empty cell. */
	public static final char EMPTY = ' ';
	
	/**
	 * A 2D array that stores the state of the board. The first
	 * index is for the row, the second index if for the column.
	 */
	private char[][] board;
	
	/**
	 * The constructor. This loops through all the cells in the
	 * 2D array and sets them to the EMPTY constant.
	 */
	public Board() {
		board = new char[3][3];
		for (int row = 0; row < board.length; row++) {
			for (int col = 0; col < board[row].length; col++) {
				board[row][col] = EMPTY;
			}
		}
	}
	
	/**
	 * Retrieves how many rows are on the board.
	 * 
	 * @return the number of rows
	 */
	public int getRowCount() {
		return board.length;
	}
	
	/**
	 * Retrieves how many columns are on the board.
	 * 
	 * @return the number of columns
	 */
	public int getColumnCount() {
		return board[0].length;
	}
	
	/**
	 * Marks the given cell with the given symbol.Then it checks
	 * to see if that play won the game.
	 * 
	 * @param cell
	 *            the cell to mark
	 * @param symbol
	 *            the symbol to mark with
	 * @return if that play won the game
	 */
	public boolean mark(int cell, char symbol) {
		board[getRow(cell)][getColumn(cell)] = symbol;
		return hasWon(cell, symbol);
	}
	
	/**
	 * Checks to see if the given play won the game. This method uses a technique
	 * of returning early. Typically you only return at the end of a method. However,
	 * this method returns multiple times throughout the method. This is useful because
	 * if a win is found, then no other combinations are checked, as there is no need.
	 * When you return out of the method, the code after the return is not executed.
	 * 
	 * @param lastPlay
	 *            the last play that was made
	 * @param symbol
	 *            the symbol of the last play
	 * @return if the last play won the game
	 */
	private boolean hasWon(int lastPlay, char symbol) {
		
		// Grabs needed indexes
		int row = getRow(lastPlay);
		int col = getColumn(lastPlay);
		int index;
		
		// Check diagonals
		if (checkRightDiagonal(lastPlay, symbol) || checkLeftDiagonal(lastPlay, symbol)) {
			return true;
		}
		
		// Check row by looping through the last play's row
		for (index = 0; index < getColumnCount(); index++) {
			
			// If there is a cell that doesn't have the wanted index, break out.
			// No need to continue looping, as it's impossible to have won this way.
			if (board[row][index] != symbol) break;
		}
		
		// If the index from looping is the same as the amount of columns,
		// that means that the loop looped all the way without breaking.
		// The only happens when the entire column is the wanted symbol,
		// thus there is a win.
		if (index == getColumnCount()) return true;
		
		//Check column. Same as row, but with columns.
		for (index = 0; index < getRowCount(); index++) {
			if (board[index][col] != symbol) break;
		}
		if (index == getRowCount()) return true;
		
		// If none of the above combinations returned a true, then
		// the board is not a winning board
		return false;
	}
	

	/**
	 * Retrieves the contents of the given cell.  Uses the cell constants.
	 * 
	 * @param cell
	 *            the wanted cell
	 * @return the contents
	 */
	public char getCellContents(int cell) {
		return board[getRow(cell)][getColumn(cell)];
	}
	
	/**
	 * Retrieves the contents of the given coordinate. Uses ints for separate row and
	 * column values.
	 * 
	 * @param row
	 *            the row of the wanted cell
	 * @param col
	 *            the column of the wanted cell
	 * @return the contents
	 */
	public char getCellContents(int row, int col) {
		return board[row][col];
	}
	
	/**
	 * Gets the row for the given cell. The first number is the row number. We can
	 * retrieve this by dividing by 10. When there isn't a first number, it gets zero.
	 * 
	 * @param cell
	 *            the cell to get a row for
	 * @return the row for the wanted cell
	 */
	private int getRow(int cell) {
		return cell / 10;
	}
	
	/**
	 * Gets the column for the given cell.The second number is the column number. We
	 * can retrieve this by getting the remainder of dividing by ten. 
	 * 
	 * @param cell
	 *            the cell to get a column for
	 * @return the column for the watned cell
	 */
	private int getColumn(int cell) {
		return cell % 10;
	}
	
	/**
	 * Checks to see if the diagonal going from the top left to the bottom right is a win.
	 * This is done by seeing if the top left is the same as the given symbol, then checking
	 * to see if the rest of the diagonal has the same contents.
	 * 
	 * @param lastPlay
	 *            the cell that was last played
	 * @param symbol
	 *            the symbol of the last play
	 * @return if the right diagonal is a win
	 */
	private boolean checkRightDiagonal(int lastPlay, char symbol) {
		return getCellContents(TOP_LEFT) == symbol && getCellContents(TOP_LEFT) == getCellContents(MIDDLE_CENTER) &&
				getCellContents(MIDDLE_CENTER) == getCellContents(BOTTOM_RIGHT);
	}
	
	/**
	 * Same as right diagonal, only checking from top right to bottom left.
	 * 
	 * @param lastPlay
	 *            the cell that was last played
	 * @param symbol
	 *            the symbol of the last play
	 * @return if the left diagonal is a win
	 */
	private boolean checkLeftDiagonal(int lastPlay, char symbol) {
		return getCellContents(TOP_RIGHT) == symbol && getCellContents(TOP_RIGHT) == getCellContents(MIDDLE_CENTER) &&
				getCellContents(MIDDLE_CENTER) == getCellContents(BOTTOM_LEFT);
	}
	
}
