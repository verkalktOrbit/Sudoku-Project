package sudoku;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Globals {
    public int[][] sudoku = {
            {0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0}
    };
    public int[] benutze = new int[9];
    public List<Integer> moeglichenListe = new ArrayList<>();

    //Key entspricht einer Koordinate im Sudoko |  der Value beinhalten alle bereits verwendeten Zahlen an der gegeben Koordinate
    public HashMap<String, List<Integer>> map = new HashMap<>();
}
