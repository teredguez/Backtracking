import java.util.Arrays;

public class MazeOne {

	// Movimientos posibles: Arriba, Abajo, Izquierda, Derecha
	private static final int[] MOV_X = {-1, 1, 0, 0};
	private static final int[] MOV_Y = {0, 0, -1, 1};

	private static final int EMPTY = 0;
	private static final int WALL = 1;
	private static final int PATH = 2;

	private final int[][] maze;
	private final int rows;
	private final int cols;
	private boolean solved;
	private int minSteps;

	public MazeOne(int[][] initialMaze) {
		this.rows = initialMaze.length;
		this.cols = initialMaze[0].length;
		this.maze = new int[rows][cols];
		for (int i = 0; i < rows; i++) {
			this.maze[i] = Arrays.copyOf(initialMaze[i], cols);
		}
		this.solved = false;
		this.minSteps = -1;
	}

	public boolean solve(int startX, int startY, int targetX, int targetY) {
		if (!isValid(startX, startY) || !isValid(targetX, targetY)) {
			return false;
		}
		if (maze[startX][startY] == WALL || maze[targetX][targetY] == WALL) {
			return false;
		}

		solved = false;
		maze[startX][startY] = PATH;
		backtrack(startX, startY, targetX, targetY, 0);

		return solved;
	}

	private void backtrack(int x, int y, int targetX, int targetY, int steps) {
		if (x == targetX && y == targetY) {
			solved = true;
			minSteps = steps;
			return;
		}

		for (int k = 0; k < 4; k++) {
			int nextX = x + MOV_X[k];
			int nextY = y + MOV_Y[k];

			if (!solved && isValid(nextX, nextY) && maze[nextX][nextY] == EMPTY) {
				maze[nextX][nextY] = PATH; // Marcar paso

				backtrack(nextX, nextY, targetX, targetY, steps + 1);

				if (!solved) {
					maze[nextX][nextY] = EMPTY; // Desmarcar en retroceso si no llevó a la meta
				}
			}
		}
	}

	private boolean isValid(int x, int y) {
		return x >= 0 && x < rows && y >= 0 && y < cols;
	}

	public int getMinSteps() {
		return minSteps;
	}

	public void printMaze() {
		for (int[] row : maze) {
			for (int cell : row) {
				switch (cell) {
					case WALL -> System.out.print("██ "); // Pared
					case PATH -> System.out.print("·· "); // Camino solución
					default   -> System.out.print("   "); // Espacio libre
				}
			}
			System.out.println();
		}
	}

	public static void main(String[] args) {
		// 0 = libre, 1 = pared
		int[][] sampleMaze = {
				{0, 0, 1, 0, 0},
				{1, 0, 1, 0, 1},
				{0, 0, 0, 0, 0},
				{0, 1, 1, 1, 0},
				{0, 0, 0, 1, 0}
		};

		MazeOne solver = new MazeOne(sampleMaze);

		int startX = 0, startY = 0;
		int targetX = 4, targetY = 4;

		System.out.println("Resolviendo laberinto desde (" + startX + "," + startY + ") hasta (" + targetX + "," + targetY + "):");
		boolean found = solver.solve(startX, startY, targetX, targetY);

		if (found) {
			System.out.println("Solución encontrada en " + solver.getMinSteps() + " pasos:\n");
			solver.printMaze();
		} else {
			System.out.println("NO HAY SOLUCIÓN");
		}
	}
}