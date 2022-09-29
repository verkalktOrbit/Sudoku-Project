package sudoku;

import java.sql.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Globals {
    public int[][] sudoku = {
            {8, 0, 1, 5, 4, 0, 7, 2, 0},
            {0, 0, 4, 0, 1, 9, 3, 0, 0},
            {7, 0, 3, 0, 0, 6, 0, 0, 1},
            {9, 0, 0, 0, 0, 2, 0, 5, 0},
            {0, 0, 8, 9, 7, 1, 2, 0, 4},
            {0, 3, 0, 6, 0, 8, 1, 0, 9},
            {1, 0, 9, 0, 8, 4, 5, 0, 3},
            {3, 4, 5, 0, 0, 0, 0, 0, 0},
            {2, 0, 6, 0, 0, 5, 4, 1, 7}
    };
    public int randNum;
    public int[] möglichen = new int[9];
    public List<Integer> möglichenListe = new ArrayList<>();
    public List<Integer> benutzteZahlen = new ArrayList<>();

    //Key entspricht einer Koordinate im Sudoko -> (y+1)*(x+1), damit hat jede Koordinate eine einzigartige "ID"  |  der Value beinhalten alle bereits verwendeten Zahlen an der gegeben Koordinate
    public HashMap<Integer, List<Integer>> map = new HashMap<>();

}
