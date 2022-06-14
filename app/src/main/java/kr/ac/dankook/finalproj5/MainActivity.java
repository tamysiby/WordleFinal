package kr.ac.dankook.finalproj5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.os.Vibrator;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import kr.ac.dankook.finalproj5.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    // Used to load the 'finalproj5' library on application startup.
    static {
        System.loadLibrary("finalproj5");
    }

    private ActivityMainBinding binding;

    Board b = new Board();
    private String guess = "";
    private String[] boxColors = new String[5];
    Game g = new Game();
    EditText mEdit;

    int[] boxesRow1 = {R.id.box1_1, R.id.box1_2, R.id.box1_3, R.id.box1_4, R.id.box1_5};
    int[] boxesRow2 = {R.id.box2_1, R.id.box2_2, R.id.box2_3, R.id.box2_4, R.id.box2_5};
    int[] boxesRow3 = {R.id.box3_1, R.id.box3_2, R.id.box3_3, R.id.box3_4, R.id.box3_5};
    int[] boxesRow4 = {R.id.box4_1, R.id.box4_2, R.id.box4_3, R.id.box4_4, R.id.box4_5};
    int[] boxesRow5 = {R.id.box5_1, R.id.box5_2, R.id.box5_3, R.id.box5_4, R.id.box5_5};

    int[][] rows = {boxesRow1, boxesRow2, boxesRow3, boxesRow4, boxesRow5};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }

    //Function called on button click
    public void setValidUserInput(View view) {
        mEdit = (EditText) findViewById(R.id.editText1);
        String userInput = mEdit.getText().toString();

        //Clears text box and hides keyboard after submitting
        InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(mEdit.getWindowToken(), 0);
        mEdit.getText().clear();

        //Toasts prevent user from entering invalid text submit
        userInput = userInput.toUpperCase();
        if (g.win == 1 || g.lose == 1) {
            Toast.makeText(this, "Press try again for new game!", Toast.LENGTH_SHORT).show();
            return;
        }
        if (userInput.length() != 5) {
            Toast.makeText(this, "Input 5 letters please", Toast.LENGTH_SHORT).show();
            return;
        } else {
            //If valid, start a round
            guess = userInput;
            runRound(guess);
        }
    }

    void runRound(String guess) {
        int win = 0;
        win = g.round(guess);
        b.setAnswer(g.answer);
        b.display(guess);

        if (win == 1) {
            //win
            boxColors = g.getGreensAndYellows();
            displayRow(g.roundNum);
            b.youWin();
            g.win = 1;
        } else if (g.lives == -1 && win != 1) {
            //lose
            g.lose = 1;
            boxColors = g.getGreensAndYellows();
            displayRow(g.roundNum - 1);
            b.youLost();
        } else {
            //loseALife
            b.loseALife();
            boxColors = g.getGreensAndYellows();
            displayRow(g.roundNum - 1);
        }

    }

    private void displayRow(int roundNum) {
        char[] arr = g.getGuessArr();
        if (roundNum > 5) {
            return;
        }
        for (int i = 0; i < 5; i++) {
            TextView tv = (TextView) findViewById(rows[roundNum - 1][i]);
            tv.setBackgroundColor(Color.parseColor(boxColors[i]));
            tv.setTextColor(Color.parseColor("#000000"));
            tv.setText(Character.toString(arr[i]));
        }
    }

    public void tryAgain(View view) {
        g.init();
        for (int j = 0; j < 5; j++) {
            for (int i = 0; i < 5; i++) {
                TextView tv = (TextView) findViewById(rows[j][i]);
                tv.setBackgroundColor(Color.parseColor("#FFFFFF"));
                tv.setText("");
                b.reset();
            }
        }
    }

    /**
     * A native method that is implemented by the 'finalproj5' native library,
     * which is packaged with this application.
     */
    public native String stringFromJNI();


}