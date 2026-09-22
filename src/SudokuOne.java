public class SudokuOne {

	private static final int EMPTY = 0;
	private static final int SIZE = 9;
	private static final int SUBGRID = 3;

	private final int[][] board;
	private boolean solved;

	public SudokuOne(int[][] initialBoard) {
		this.board = new int[SIZE][SIZE];
		for (int i = 0; i < SIZE; i++) {
			System.arraycopy(initialBoard[i], 0, this.board[i], 0, SIZE);
		}
		this.solved = false;
	}

	public boolean solve() {
		this.solved = false;
		backtracking();
		return this.solved;
	}

	private boolean backtracking() {
		int[] emptyCell = findNextEmpty();

		// Si no hay más casillas vacías, Sudoku resuelto
		if (emptyCell == null) {
			this.solved = true;
			return true;
		}

		int row = emptyCell[0];
		int col = emptyCell[1];

		// Probar números válidos del 1 al 9
		for (int num = 1; num <= SIZE; num++) {
			if (isValid(row, col, num)) {
				board[row][col] = num;

				if (backtracking()) {
					return true;
				}

				board[row][col] = EMPTY; //backtrack
			}
		}

		return false;
	}

	private boolean isValid(int row, int col, int num) {
		return checkRow(row, num) && checkColumn(col, num) && checkRegion(row, col, num);
	}

	private boolean checkRow(int row, int num) {
		for (int col = 0; col < SIZE; col++) {
			if (board[row][col] == num) {
				return false;
			}
		}
		return true;
	}

	private boolean checkColumn(int col, int num) {
		for (int row = 0; row < SIZE; row++) {
			if (board[row][col] == num) {
				return false;
			}
		}
		return true;
	}

	private boolean checkRegion(int row, int col, int num) {
		int startRow = row - (row % SUBGRID);
		int startCol = col - (col % SUBGRID);

		for (int i = startRow; i < startRow + SUBGRID; i++) {
			for (int j = startCol; j < startCol + SUBGRID; j++) {
				if (board[i][j] == num) {
					return false;
				}
			}
		}
		return true;
	}

	// Busca la siguiente posición libre con valor 0
	private int[] findNextEmpty() {
		for (int r = 0; r < SIZE; r++) {
			for (int c = 0; c < SIZE; c++) {
				if (board[r][c] == EMPTY) {
					return new int[]{r, c};
				}
			}
		}
		return null;
	}

	public void printBoard() {
		for (int r = 0; r < SIZE; r++) {
			if (r > 0 && r % SUBGRID == 0) {
				System.out.println("------+-------+------");
			}
			for (int c = 0; c < SIZE; c++) {
				if (c > 0 && c % SUBGRID == 0) {
					System.out.print("| ");
				}
				System.out.print(board[r][c] == EMPTY ? ". " : board[r][c] + " ");
			}
			System.out.println();
		}
	}

	public static void main(String[] args) {
		// Tablero de prueba (0 es casilla vacía)
		int[][] puzzle = {
				{5, 3, 0, 0, 7, 0, 0, 0, 0},
				{6, 0, 0, 1, 9, 5, 0, 0, 0},
				{0, 9, 8, 0, 0, 0, 0, 6, 0},
				{8, 0, 0, 0, 6, 0, 0, 0, 3},
				{4, 0, 0, 8, 0, 3, 0, 0, 1},
				{7, 0, 0, 0, 2, 0, 0, 0, 6},
				{0, 6, 0, 0, 0, 0, 2, 8, 0},
				{0, 0, 0, 4, 1, 9, 0, 0, 5},
				{0, 0, 0, 0, 8, 0, 0, 7, 9}
		};

		SudokuOne sudoku = new SudokuOne(puzzle);

		System.out.println("Tablero inicial:\n");
		sudoku.printBoard();

		if (sudoku.solve()) {
			System.out.println("\nSudoku resuelto:\n");
			sudoku.printBoard();
		} else {
			System.out.println("\nNo existe solución válida para el tablero proporcionado.");
		}
	}
}