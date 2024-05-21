public class SudokuSolver {

    private static final int SIZE = 9; // Size of the Sudoku grid

    // Method to print the Sudoku grid
    private static void printGrid(int[][] grid) {
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                System.out.print(grid[row][col] + " ");
            }
            System.out.println();
        }
    }

    // Method to check if a number can be placed in a specific cell
    private static boolean isSafe(int[][] grid, int row, int col, int num) {
        // Check the row
        for (int x = 0; x < SIZE; x++) {
            if (grid[row][x] == num) {
                return false;
            }
        }

        // Check the column
        for (int x = 0; x < SIZE; x++) {
            if (grid[x][col] == num) {
                return false;
            }
        }

        // Check the 3x3 sub-grid
        int startRow = row - row % 3;
        int startCol = col - col % 3;
        for (int i = startRow; i < startRow + 3; i++) {
            for (int j = startCol; j < startCol + 3; j++) {
                if (grid[i][j] == num) {
                    return false;
                }
            }
        }

        return true;
    }

    // Method to solve the Sudoku puzzle using backtracking
    private static boolean solveSudoku(int[][] grid, int row, int col) {
        // If we have reached the last cell, the Sudoku is solved
        if (row == SIZE - 1 && col == SIZE) {
            return true;
        }

        // Move to the next row if we have reached the end of the current row
        if (col == SIZE) {
            row++;
            col = 0;
        }

        // Skip cells that are already filled
        if (grid[row][col] != 0) {
            return solveSudoku(grid, row, col + 1);
        }

        for (int num = 1; num <= SIZE; num++) {
            if (isSafe(grid, row, col, num)) {
                grid[row][col] = num;
                if (solveSudoku(grid, row, col + 1)) {
                    return true;
                }
                grid[row][col] = 0; // Undo assignment (backtrack)
            }
        }

        return false; // Trigger backtracking
    }

    public static void main(String[] args) {
        int[][] grid = {
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

        if (solveSudoku(grid, 0, 0)) {
            System.out.println("Sudoku puzzle solved successfully:");
            printGrid(grid);
        } else {
            System.out.println("Unable to solve the Sudoku puzzle.");
        }
    }
}
