package sudoku;

import java.util.List;
import java.util.Random;

class sudokuOverride implements Sudoku{

    Globals globals = new Globals();

    Random random = new Random();

    @Override
    public int[][] createSudoku() {
        //Durch alle Positionen im Sudoku gehen
        for(int y =  0; y < 9; y++){
            for(int x = 0; x < 9; x++){

                möglicheZahlen(x, y);

                //Wenn es keine möglichkeiten mehr gibt --> TraceBack
                if(globals.möglichenListe.size() == 0){
                    int[] backKoordinate = traceBack(x, y);
                    x = backKoordinate[0];
                    y = backKoordinate[1];
                }

                //Zufallszahl aus den möglichenListe wählen         | list.size() gibt größe von 1 beginind an, random.nextint() macht random Zahl BIS  bount --> 0-1ß = random.nextint(10)
                globals.randNum = random.nextInt(globals.möglichenListe.size());
                //Zahl einsetzen
                globals.sudoku[x][y] = globals.möglichenListe.get(globals.randNum);

                //An gegebner Stelle (Key) die bereits eingesetzten Zahlen eintragen
                globals.benutzteZahlen = globals.map.get((y+1)*(x+1));
                globals.benutzteZahlen.add(globals.möglichenListe.get(globals.randNum));
                globals.map.put((y+1)*(x+1), globals.benutzteZahlen);

            }
        }

        return globals.sudoku;
    }

    @Override
    //So weit zurück gehen, bis es mehr Möglichen gibt
    public int[] traceBack(int x, int y) {

        int[] backKoordinate;

        //Rückwärts gehen
        Outerloop:
        for(int i = y; y >= 0; y--){
            for(int j = x; x >= 0; x--){
                globals.möglichenListe = möglicheZahlen(j, i);
                if(globals.möglichenListe.size() > 1){
                    //bereits benutzte Zahlen aus den möglichen entfernen
                    for(Integer benutzte: globals.map.get((y+1)*(x+1))){
                        //Wenn benutze nicht in möglichenListe ist passiert nicht
                        globals.möglichenListe.removeIf(s -> s.equals(benutzte));
                    }
                    if(globals.möglichenListe.size() > 0){
                        backKoordinate = new int[]{j, i};
                        break Outerloop;
                    }
                }
            }

        }

        return null ;
    }

    @Override
    public List<Integer> möglicheZahlen(int x, int y) {

        //Vertikale checken
        for(int i = 0; i < 9; i++){
            if(globals.sudoku[i][y] != 0) globals.möglichen[globals.sudoku[i][y] -1] = 1;
        }
        //horizontale checken
        for(int i = 0; i < 9; i++){
            if(globals.sudoku[x][i] != 0) globals.möglichen[globals.sudoku[x][i] - 1] = 1;
        }
        //3x3 Feld checken
        // Koordinaten zu Koordinate oben links in der Ecke von 3x3 Feld ändern
        x = x-(x%3);
        y = y-(y%3);

        for(int i = 0; i < x; i++){
            for(int j = 0; j < y; j++){
                if(globals.sudoku[i][j] != 0){
                    globals.möglichen[globals.sudoku[i][j] -1] = 1;
                }
            }
        }

        //möglichen | Position von Array korrespondiert mit Zahl im Sudoku -1   -> Position mit 1 belegt exestiert | mit 0 nicht
        //möglichenListe besteht aus Zahlen die nicht belegt sind
        for(int i = 0; i < 9; i++){
            if(globals.möglichen[i] == 0){
                globals.möglichenListe.add(i+1);
            }
        }

        return globals.möglichenListe;
    }
}

public class App {
    public static void main(String[] args){
        sudokuOverride sudokuOverride = new sudokuOverride();
        System.out.println(sudokuOverride.möglicheZahlen(0, 5));
    }
}


