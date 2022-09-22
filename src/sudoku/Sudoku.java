package sudoku;

import java.util.ArrayList;
import java.util.List;

public interface Sudoku {
    int[][] unsolvedSudoku = new int[9][9];
    int[][] createSudoku();
    List<Integer> möglicheZahlen(int x, int y);
}
