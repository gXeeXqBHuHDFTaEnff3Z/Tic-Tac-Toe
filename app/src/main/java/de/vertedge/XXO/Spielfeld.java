package de.vertedge.XXO;

import android.util.Log;

import androidx.annotation.NonNull;

import java.util.Arrays;

public class Spielfeld {

    // mögliche Werte für Spielfeld. Unbelegt = null
    public enum Spieler {X, O};

    private Spieler[][] _spielfeld = new Spieler[3][3];

    public void setzeFeld(int feldnummer, Spieler spieler){
        Log.d("setzeFeld", "Feldnummer: " + feldnummer + " Spieler: " + spieler.toString());

        switch(feldnummer){
            case 1:
                setzeFeld(0,0, spieler);;
                break;
            case 2:
                setzeFeld(0,1, spieler);
                break;
            case 3:
                setzeFeld(0,2, spieler);
                break;
            case 4:
                setzeFeld(1,0, spieler);
                break;
            case 5:
                setzeFeld(1,1, spieler);
                break;
            case 6:
                setzeFeld(1,2, spieler);
                break;
            case 7:
                setzeFeld(2,0, spieler);
                break;
            case 8:
                setzeFeld(2,1, spieler);
                break;
            case 9:
                setzeFeld(2,2, spieler);
                break;
            default:
                break;
        }
    }

    public void setzeFeld(int x, int y, Spieler spieler){
        Log.d("setzeFeld[x][y]", "x: " + x + " y:" + y);
        _spielfeld[x][y] = spieler;
    }

    public Spieler wasIstAufFeld(int feldnummer){
        switch (feldnummer){
            case 1:
                return _spielfeld[0][0];
            case 2:
                return _spielfeld[0][1];
            case 3:
                return _spielfeld[0][2];
            case 4:
                return _spielfeld[1][0];
            case 5:
                return _spielfeld[1][1];
            case 6:
                return _spielfeld[1][2];
            case 7:
                return _spielfeld[2][0];
            case 8:
                return _spielfeld[2][1];
            case 9:
                return _spielfeld[2][2];
            default:
                throw new ArrayIndexOutOfBoundsException("Ein Feld " + feldnummer + " gibt es nicht");
        }
    }

    public Spieler gewonnen(){
        Log.d("gewonnen?", "prüfe...");
        Log.d("Spielfeld", "Stand: \n" + this.toString());
        // prüfe spalten
        for(int i = 0; i<3; i++){
            if (_spielfeld[0][i] != null){
                Log.d("gewonnen?", "kandidat spalte " + i);
                if ((_spielfeld[0][i] == _spielfeld[1][i]) && (_spielfeld[1][i] == _spielfeld[2][i])){
                    Log.d("gewonnen?", "gefunden bei Spalte " + i + ": " + _spielfeld[0][i] );
                    return _spielfeld[0][i];
                }
            }
        }

        // prüfe Zeilen
        for(int i = 0; i<3; i++){
            if (_spielfeld[i][0] != null){
                Log.d("gewonnen?", "kandidat Zeile " + i);
                if ((_spielfeld[i][0] == _spielfeld[i][1]) && (_spielfeld[i][1] == _spielfeld[i][2])){
                    Log.d("gewonnen?", "gefunden bei Zeile " + i + ": " + _spielfeld[i][0] );
                    return _spielfeld[i][0];
                }
            }
        }

        // prüfe kreuz links \
        if (_spielfeld[0][0] != null){
            if ( (_spielfeld[0][0] == _spielfeld[1][1]) && (_spielfeld[1][1] == _spielfeld[2][2]) ){
                Log.d("gewonnen?", "gefunden bei quer \\ : " + _spielfeld[0][0] );
                return _spielfeld[0][0];
            }
        }

        // prüfe kreuz rechts /
        if (_spielfeld[0][2] != null){
            if ( (_spielfeld[0][2] == _spielfeld[1][1]) && (_spielfeld[1][1] == _spielfeld[2][0]) ){
                Log.d("gewonnen?", "gefunden bei quer / : " + _spielfeld[0][2] );
                return _spielfeld[0][2];
            }
        }

        // falls alles durchgegangen und nichts gefunden, dann hat noch keiner gewonnen
        Log.d("gewonnen?", "kein gewinner bisher");
        return null;
    }

    @NonNull
    @Override
    public String toString() {
        return "[" + _spielfeld[0][0] + " " + _spielfeld[0][1] + " " + _spielfeld[0][2] + "]\n" +
               "[" + _spielfeld[1][0] + " " + _spielfeld[1][1] + " " + _spielfeld[2][2] + "]\n" +
               "[" + _spielfeld[2][0] + " " + _spielfeld[2][1] + " " + _spielfeld[2][2] + "]\n";
    }
}
