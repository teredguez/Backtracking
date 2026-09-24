import java.util.ArrayList;
import java.util.List;

public class Subsets {

	private final int[] numbers;
	private final int targetSum;
	private final List<List<Integer>> solutions;

	public Subsets(int[] numbers, int targetSum) {
		this.numbers = numbers.clone();
		this.targetSum = targetSum;
		this.solutions = new ArrayList<>();
	}

	public List<List<Integer>> findSubsets() {
		solutions.clear();
		List<Integer> currentPath = new ArrayList<>();
		backtrack(0, 0, currentPath);
		return new ArrayList<>(solutions);
	}

	private void backtrack(int index, int currentSum, List<Integer> currentPath) {
		// Si alcanzamos la suma objetivo, guardamos una copia de la solución actual
		if (currentSum == targetSum) {
			solutions.add(new ArrayList<>(currentPath));
			return;
		}

		// caso base, si superamos el objetivo o nos quedamos sin números
		if (index == numbers.length || currentSum > targetSum) {
			return;
		}

		//incluir numbers[index]
		currentPath.add(numbers[index]);
		backtrack(index + 1, currentSum + numbers[index], currentPath);

		// backtrack
		currentPath.remove(currentPath.size() - 1);

		//no incluir numbers[index]
		backtrack(index + 1, currentSum, currentPath);
	}

	public int getCount() {
		return solutions.size();
	}

	public static void main(String[] args) {
		// números de prueba y suma que buscamos
		int[] data = {1, 2, 3, 4, 5};
		int target = 5;

		Subsets solver = new Subsets(data, target);
		List<List<Integer>> results = solver.findSubsets();

		System.out.println("Subconjuntos que suman " + target + ":");
		for (List<Integer> subset : results) {
			System.out.println(subset + " = " + target);
		}
		System.out.println("Total de soluciones: " + solver.getCount());
	}
}