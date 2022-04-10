package de.vertedge.XXO;

import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.CompoundButton;

import android.os.Bundle;
import android.widget.Toast;

/* DEBUGGING CHEAT SHEET

//Toast.makeText(this, "Clicked on Button", Toast.LENGTH_LONG).show();
 */

public class MainActivity extends AppCompatActivity {

    Spielfeld.Spieler spielerDran = Spielfeld.Spieler.X;
    Spielfeld _feld = new Spielfeld();
    KIO _kio = new KIO();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Spieler switch listener verbinden
        Switch s = (Switch) findViewById(R.id.switchSpieler);
        s.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (spielerDran.equals(Spielfeld.Spieler.X)) {
                    spielerDran = Spielfeld.Spieler.O;
                } else spielerDran = Spielfeld.Spieler.X;

                Switch s = (Switch) buttonView;
                if (isChecked) {
                    s.setText("Spieler O");
                } else s.setText("Spieler X");
            }
        });

        // KI switch listener verbinden
        Switch sKI = (Switch) findViewById(R.id.switchKIO);
        sKI.setOnCheckedChangeListener((buttonView, isChecked) -> {
            _kio.set_enabled(sKI.isChecked());
        });
    }

    public void onClickButton(View v) {
        Button b = (Button) v;

        // wenn button schon beschritet, dann nichts tun
        if (!b.getText().toString().equals("")) {
            return;
        }

        // button beschriften
        Log.d("onClickButton", "Tag: " + b.getTag().toString());
        int position = Integer.parseInt(b.getTag().toString());
        _feld.setzeFeld(position, spielerDran);
        b.setText(spielerDran.toString());

        // Spielfeld aktualisieren
        // int feldnummer = Integer.parseInt( v.getTag().toString() );
        // Spielfeld.Spieler spieler = (spielerDran.equals("X") ? Spielfeld.Spieler.X : Spielfeld.Spieler.O);
        // _feld.setFeld(feldnummer, spieler);

        // prüfen ob jemand gewonnen hat
        // _feld.gewonnen();

        // schalter umstellenn, nächster spieler dran
        Switch s = (Switch) findViewById(R.id.switchSpieler);
        s.toggle();

        String gewinner = werHatGewonnen();

        if (!gewinner.equals("")) {
            Toast.makeText(this, "Gewinner: " + gewinner, Toast.LENGTH_SHORT).show();
        }

        // wenn KI aktiv und dran, spielen lassen
        if (_kio.is_enabled() && (spielerDran == Spielfeld.Spieler.O)) {
            int gewaehltesFeld = _kio.spiele(_feld, Spielfeld.Spieler.O);

            // finde button und klicke an
            Button bKI;
            bKI = findButtonByNumber(gewaehltesFeld);
            bKI.callOnClick();
        }
    }

    public void onClickNeu(View v) {
        // Spielfeld leeren
        _feld = new Spielfeld();

        // button leeren
        Button b = (Button) findViewById(R.id.button);
        b.setText("");
        b = (Button) findViewById(R.id.button2);
        b.setText("");
        b = (Button) findViewById(R.id.button3);
        b.setText("");
        b = (Button) findViewById(R.id.button4);
        b.setText("");
        b = (Button) findViewById(R.id.button5);
        b.setText("");
        b = (Button) findViewById(R.id.button6);
        b.setText("");
        b = (Button) findViewById(R.id.button7);
        b.setText("");
        b = (Button) findViewById(R.id.button8);
        b.setText("");
        b = (Button) findViewById(R.id.button9);
        b.setText("");

        // schalter umstellen
        Switch s = (Switch) findViewById(R.id.switchSpieler);
        s.toggle();
    }

    public String werHatGewonnen() {
        Spielfeld.Spieler gewinner = _feld.gewonnen();
        if (gewinner == null) {
            return "";
        } else return gewinner.toString();
    }

    private Button findButtonByNumber(int nummer) {
        switch (nummer) {
            case 1:
                return findViewById(R.id.button);
            case 2:
                return findViewById(R.id.button2);
            case 3:
                return findViewById(R.id.button3);
            case 4:
                return findViewById(R.id.button4);
            case 5:
                return findViewById(R.id.button5);
            case 6:
                return findViewById(R.id.button6);
            case 7:
                return findViewById(R.id.button7);
            case 8:
                return findViewById(R.id.button8);
            case 9:
                return findViewById(R.id.button9);
            default:
                throw new IllegalArgumentException("Button " + nummer + " gibt es nicht");
        }
    }
}