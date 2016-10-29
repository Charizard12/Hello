package project.secondapp.alicm.hello;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ScoreActivity extends AppCompatActivity {

    int scoreA = 0, scoreB = 0;
    TextView teamA, teamB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);
        teamA = (TextView)findViewById(R.id.TeamAscore);
        teamB = (TextView)findViewById(R.id.TeamBscore);
    }

    public void score3A(View view){
        displayA(3);
    }
    public void score2A(View view){
        displayA(2);
    }
    public void score1A(View view){
        displayA(1);
    }
    public void score3B(View view){
        displayB(3);
    }
    public void score2B(View view){
        displayB(2);
    }
    public void score1B(View view){
        displayB(1);
    }

    private void displayA(int points){
        scoreA = scoreA + points;
        teamA.setText("" + scoreA);
    }
    private void displayB(int points){
        scoreB = scoreB + points;
        teamB.setText("" + scoreB);
    }

    public void reset(View view){
        scoreA = 0;
        scoreB = 0;
        displayA(0);
        displayB(0);
    }

}
