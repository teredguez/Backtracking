# Java Backtracking Algorithms

A collection of practical exercises implemented in Java to understand and apply the **Backtracking** technique across different problems.

---

## Solved Problems

### 1. Maze Solver (`MazeOne`)
Finds a path from a start cell to a target destination within a 2D grid containing obstacles (walls).
* **Strategy:** Explores movements in 4 directions (up, down, left, right) while avoiding cycles and blocked cells. Unmarks the current path if a branch does not lead to the exit.

<img width="292" height="146" alt="image" src="https://github.com/user-attachments/assets/4132188b-825f-4037-928e-8b3c200964a8" />


---

### 2. Sudoku Solver (`SudokuOne`)
Solves a standard 9x9 Sudoku board respecting the classic rules of the game.
* **Strategy:** Identifies the next empty cell and tests numbers from 1 to 9, verifying that the candidate does not violate row, column, or 3x3 subgrid constraints. If a conflict arises down the road, it backtracks and tests the next candidate digit.

<img width="146" height="224" alt="image" src="https://github.com/user-attachments/assets/275c3ebe-e260-4c43-8a7f-e5db4639e346" />
<img width="148" height="232" alt="image" src="https://github.com/user-attachments/assets/e5bb1e43-ed2d-4c3b-80a2-2d8b246f6009" />


---

### 3. Subset Sum (`Subsets`)
Finds all unique combinations within a set of integers that sum up exactly to a given `targetSum`.
* **Strategy:** Applies a binary decision tree (include or exclude the current element at each recursion level) combined with branch pruning whenever the cumulative sum exceeds the target.

<img width="165" height="95" alt="image" src="https://github.com/user-attachments/assets/d40748cc-355d-4836-a660-221ff8eda554" />

---

## How to Run

Open the project in any Java-compatible IDE and run the `main` method in any of the classes:

* `MazeOne.java`
* `SudokuOne.java`
* `Subsets.java`

---


Teresa Domínguez Otero
