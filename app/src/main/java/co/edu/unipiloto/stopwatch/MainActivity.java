package co.edu.unipiloto.stopwatch;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private int seconds = 0;
    private boolean running;
    private int cantVueltas = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState != null) {
            seconds = savedInstanceState.getInt("seconds");
            running = savedInstanceState.getBoolean("running");
        }
        runTimer();
    }

    /*@Override
    public void onSaveInstanceState (Bundle savedInstanceState) {
        savedInstanceState.putInt("seconds", seconds);
        savedInstanceState.putBoolean("running", running);
    }*/

    public void onClickStart (View view){
        running=true;
    }

    public void onClickStop (View view){
        running=false;
    }

    public void onClickReset (View view) {
        running=false;
        seconds=0;
        cantVueltas=0;
        final Button lapButton = (Button) findViewById(R.id.lap_button);
        lapButton.setEnabled(true);
        final TextView nLapView = (TextView) findViewById(R.id.nLap_textView);
        final TextView tableTimes = (TextView) findViewById(R.id.time_textView);
        nLapView.setText("");
        tableTimes.setText("");
    }

    public void onClickLap (View view) {
        final TextView timeView = (TextView) findViewById(R.id.time_view);
        final TextView nLapView = (TextView) findViewById(R.id.nLap_textView);
        final TextView tableTimes = (TextView) findViewById(R.id.time_textView);
        final Button lapButton;
        if(cantVueltas == 0) {
            nLapView.setText("N° VUELTA \n");
            tableTimes.setText("TIEMPO \n");
        }
        nLapView.append(++cantVueltas + "\n");
        tableTimes.append(timeView.getText() + "\n");
        if(cantVueltas == 5) {
            lapButton = (Button) findViewById(R.id.lap_button);
            lapButton.setEnabled(false);
        }
    }

    private void runTimer () {
        final TextView timeView = (TextView) findViewById(R.id.time_view);
        final Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                int hours =seconds/3600;
                int minutes = (seconds%3600)/60;
                int secs = seconds % 60;
                String time = String.format(Locale.getDefault(), "%d:%02d:%02d", hours, minutes, secs);
                timeView.setText(time);
                if(running){
                    seconds++;
                }
                handler.postDelayed(this, 1000);
            }
        });
    }
}