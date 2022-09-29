package sudoku;

import java.util.ArrayList;
import java.util.List;

public interface Sudoku {
    int[][] unsolvedSudoku = new int[9][9];
    int[][] createSudoku();
    int[] traceBack(int x, int y);
    List<Integer> möglicheZahlen(int x, int y);
}
