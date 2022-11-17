package sudoku;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

class sudokuOverride implements Sudoku{

    Globals globals = new Globals();
    Random random = new Random();
    @Override
    public int[][] unsolveSudoku(int difficulty) {
        int randX = 0;
        int randY = 0;

        for(int i = 0; i < difficulty; i++){
            randX = random.nextInt(9);
            randY = random.nextInt(9);
            moeglicheZahlen(randY, randX, globals.sudoku);
            while(globals.moeglichenListe.size() == 1){
                randX = random.nextInt(9);
                randY = random.nextInt(9);
                moeglicheZahlen(randY, randX, globals.sudoku);
            }
            globals.sudoku[randY][randX] = 0;
        }
        return globals.sudoku;
    }
    @Override
    public int[][] createSudoku() {
        //Durch alle Positionen im Sudoku gehen
        for(int y =  0; y < 9; y++){
            for(int x = 0; x < 9; x++){

                globals.map.computeIfAbsent(getKey(y, x), k -> new ArrayList<Integer>());

                moeglicheZahlen(y, x, globals.sudoku);

                //Wenn es keine möglichkeiten mehr gibt --> TraceBack
                if(globals.moeglichenListe.size() == 0){
                    int[] backKoordinate = backtracking(y, x);
                    y = backKoordinate[0];
                    x = backKoordinate[1];
                }
                //Zufallszahl aus den moeglichenListe wählen         | list.size() gibt größe von 1 beginind an, random.nextint() macht random Zahl BIS  bount --> 0-9 = random.nextint(10)
                globals.sudoku[y][x] = globals.moeglichenListe.get(random.nextInt(globals.moeglichenListe.size()));
                //An gegebner Stelle (Key) die eingesetzte Zahlen hinzufügen
                globals.map.get(getKey(y, x)).add(globals.sudoku[y][x]);

            }
        }
        return globals.sudoku;
    }

    @Override
    //So weit zurück gehen, bis es mehr Möglichen gibt
    public int[] backtracking(int y, int x) {

        int[] backKoordinate = new int[2];

        //Rückwärts gehen
        Outerloop:
        for(int i = y; i >= 0; i--){
            for(int j = x; j >= 0; j--){
                if(x == j & y == i){
                    continue;
                }
                globals.sudoku[i][j] = 0;
                //Möglichen an bereits belegten Stellen ermitteln
                moeglicheZahlen(i, j, globals.sudoku);
                for(Integer benutzte: globals.map.get(getKey(i, j)) ){
                    globals.moeglichenListe.remove(benutzte);
                }
                //Wenn es mehr als 2 Möglichkeiten gibt ist die Koordinate gut
                if(globals.moeglichenListe.size() > 0) {
                    backKoordinate[0] = i;
                    backKoordinate[1] = j;
                    break Outerloop;
                }
            }
            x = 8;
        }
        return backKoordinate ;
    }

    @Override
    public void moeglicheZahlen(int y, int x, int[][] sudoku) {

        Arrays.fill(globals.benutze, 0);
        globals.moeglichenListe.clear();

        //3x3 Feld Regel
        // Koordinaten zu Koordinate oben links in der Ecke von 3x3 Feld ändern
        for(int i = (Math.floorDiv(y, 3)*3); i < (Math.floorDiv(y, 3)*3)+3; i++){
            for(int j = (Math.floorDiv(x, 3)*3); j < (Math.floorDiv(x, 3)*3)+3; j++){
                if(sudoku[i][j] != 0) globals.benutze[sudoku[i][j] - 1] = 1;
            }
        }
        //Vertikal Regel
        for(int i = 0; i < 9; i++){
            if(sudoku[i][x] != 0) globals.benutze[sudoku[i][x]-1] = 1;
        }
        //Horizontal Regel
        for(int i = 0; i < 9; i++){
            if(sudoku[y][i] != 0) globals.benutze[sudoku[y][i] - 1] = 1;
        }
        //benutze | Position von Array korrespondiert mit Zahl im Sudoku -1   -> Position mit 1 belegt exestiert | mit 0 nicht
        //moeglichenListe besteht aus Zahlen die nicht belegt sind
        for(int i = 0; i < 9; i++){
            if(globals.benutze[i] == 0){
                globals.moeglichenListe.add(i+1);
            }
        }
    }

    @Override
    public String getKey(int y, int x) {
        return String.format("%d", y)+String.format("%d", x);
    }
}

public class App {
    public static void main(String[] args){
        sudokuOverride sudokuOverride = new sudokuOverride();
        int[][] sudoku = sudokuOverride.createSudoku();
        for(int y = 0; y < 9; y++){
            for(int x = 0; x < 9; x++){
                System.out.print(sudoku[y][x]);
            }
            System.out.println();
        }

        System.out.println("=================");

        int[][] ungeloestesSudoku = sudokuOverride.unsolveSudoku(35);
        for(int y = 0; y < 9; y++){
            for(int x = 0; x < 9; x++){
                System.out.print(ungeloestesSudoku[y][x]);
            }
            System.out.println();
        }

    }
}


