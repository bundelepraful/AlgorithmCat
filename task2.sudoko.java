import java.util.*;

public class SudokuGame {

    private static final int SIZE = 9; // Size of the grid
    private static final int SUBGRID = 3; // Size of subgrids
    private int[][] board = new int[SIZE][SIZE];

    public static void main(String[] args) {
        SudokuGame game = new SudokuGame();
        game.startGame();
    }

    public void startGame() {
        System.out.println("Welcome to Sudoku!");
        Scanner scanner = new Scanner(System.in);

        System.out.println("Select difficulty: 1. Easy 2. Medium 3. Hard");
        int difficulty = scanner.nextInt();

        generatePuzzle(difficulty);
        printBoard();

        while (!isSolved()) {
            System.out.println("Enter row, column, and number (1-9) to place: (e.g., 1 1 5)");
            int row = scanner.nextInt() - 1;
            int col = scanner.nextInt() - 1;
            int num = scanner.nextInt();

            if (isValidMove(row, col, num)) {
                board[row][col] = num;
                printBoard();
            } else {
                System.out.println("Invalid move. Try again.");
            }
        }

        System.out.println("Congratulations! You solved the puzzle!");
        scanner.close();
    }

    private void generatePuzzle(int difficulty) {
        // Generate a complete Sudoku board
        fillBoard();

        // Remove numbers based on difficulty
        int cellsToRemove = difficulty == 1 ? 30 : (difficulty == 2 ? 45 : 60);
        Random random = new Random();

        for (int i = 0; i < cellsToRemove; i++) {
            int row, col;
            do {
                row = random.nextInt(SIZE);
                col = random.nextInt(SIZE);
            } while (board[row][col] == 0);

            board[row][col] = 0;
        }
    }

    private void fillBoard() {
        solveBoard(0, 0);
    }

    private boolean solveBoard(int row, int col) {
        if (row == SIZE) {
            return true;
        }

        int nextRow = (col == SIZE - 1) ? row + 1 : row;
        int nextCol = (col + 1) % SIZE;

        if (board[row][col] != 0) {
            return solveBoard(nextRow, nextCol);
        }

        for (int num = 1; num <= SIZE; num++) {
            if (isValidMove(row, col, num)) {
                board[row][col] = num;
                if (solveBoard(nextRow, nextCol)) {
                    return true;
                }
                board[row][col] = 0;
            }
        }

        return false;
    }

    private boolean isValidMove(int row, int col, int num) {
        for (int i = 0; i < SIZE; i++) {
            if (board[row][i] == num || board[i][col] == num) {
                return false;
            }
        }

        int subGridRowStart = (row / SUBGRID) * SUBGRID;
        int subGridColStart = (col / SUBGRID) * SUBGRID;

        for (int i = 0; i < SUBGRID; i++) {
            for (int j = 0; j < SUBGRID; j++) {
                if (board[subGridRowStart + i][subGridColStart + j] == num) {
                    return false;
                }
            }
        }

        return true;
    }

    private boolean isSolved() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (board[i][j] == 0) {
                    return false;
                }
            }
        }
        return true;
    }

    private void printBoard() {
        for (int i = 0; i < SIZE; i++) {
            if (i % SUBGRID == 0 && i != 0) {
                System.out.println("---------------------");
            }

            for (int j = 0; j < SIZE; j++) {
                if (j % SUBGRID == 0 && j != 0) {
                    System.out.print("| ");
                }
                System.out.print((board[i][j] == 0 ? "." : board[i][j]) + " ");
            }
            System.out.println();
        }
    }
}