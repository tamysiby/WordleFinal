package kr.ac.dankook.finalproj5;

import android.util.Log;
import java.util.Random;

public class Game {
    Game() {
        //initBoxColors();
        lives = 4;
        roundNum = 1;
        answer = getRandom(answers);
        win = 0;
        lose = 0;
    }
    public int win;
    public int lose;
    public int roundNum;
    int lives;
    String answer;
    String[] answers = {"TIRED", "CHEER", "WHALE", "FAINT", "SCALP",
            "DELAY", "FLOAT", "DRIVE", "DIARY", "DAILY",
            "SOUTH", "NORTH", "QUOTE", "TREAT", "TRIAL",
            "COCOA", "SWEAT", "MOUSE", "PHONE", "CLOUD"};
    String guess = "";
    String[] boxColors = new String[5];
    char[] guessArr = new char[5];

    public static String getRandom(String[] array) {
        int rnd = new Random().nextInt(array.length);
        return array[rnd];
    }

    public void init(){
        lives = 4;
        roundNum = 1;
        win = 0;
        lose = 0;
        answer = getRandom(answers);
    }

    public String[] getGreensAndYellows() {
        char[] answerArr = answer.toCharArray();
        for (int i = 0; i < 5; i++) {
            Log.d("YELLOW", answer + " " + guess);
            if (guessArr[i] == answerArr[i]) {
                boxColors[i] = "#77C66E"; //green
            } else if (answer.indexOf(guessArr[i]) != -1) { // if char guessArr[i] is in answer, it will return something other than -1
                boxColors[i] = "#fcf787"; //yellow
            } else {
                boxColors[i] = "#8E8E8E"; //gray
            }
        }
        return boxColors;
    }

    public char[] getGuessArr() {
        return guessArr;
    }

    public int round(String g) {
        guess = g;
        guessArr = guess.toCharArray();
        if (guess.equals(answer)) {
            return 1;
        } else {
            lives--;
            roundNum++;
        }
        return 0;
    }


}