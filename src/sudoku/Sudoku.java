package sudoku;

public interface Sudoku {
    int[][] unsolveSudoku(int difficulty);
    int[][] createSudoku();
    int[] backtracking(int x, int y);
    void moeglicheZahlen(int x, int y, int[][] sudoku);
    String getKey(int x, int y);
}
