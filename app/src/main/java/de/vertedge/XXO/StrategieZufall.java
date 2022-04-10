package de.vertedge.XXO;

import java.util.Random;

public class StrategieZufall implements KIO.Strategie{
    @Override
    public Integer play(Spielfeld feld, Spielfeld.Spieler spieler) {
        // wir beginnen mit dem Feld in der Mitte, wenn es leer ist
        int gewaehltesFeld = 5;
        Random rn = new Random();

        // so lange das zufällig gewählte feld nicht leer ist neues zufälliges feld wählen
        while (feld.wasIstAufFeld(gewaehltesFeld) != null){
            gewaehltesFeld = rn.nextInt(9 ) + 1;
        }
        return gewaehltesFeld;
    }
}
