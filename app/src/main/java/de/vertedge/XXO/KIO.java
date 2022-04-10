package de.vertedge.XXO;

public class KIO {

    public interface Strategie{
        Integer play(Spielfeld feld, Spielfeld.Spieler spieler);
    }

    private boolean _enabled = true;
    private Strategie _currentStrategie = new StrategieZufall();

    public boolean is_enabled() {
        return _enabled;
    }

    public void set_enabled(boolean _enabled) {
        this._enabled = _enabled;
    }

    public void set_currentStrategie(Strategie _currentStrategie) {
        this._currentStrategie = _currentStrategie;
    }

    public Integer spiele(Spielfeld feld, Spielfeld.Spieler spieler){
        // wenn ausgeschaltet tue nichts
        if (!_enabled) return -1;

        // lass die Strategie aussuchen welcher Zug gespielt werden soll
        return _currentStrategie.play(feld, spieler);
    }
}
