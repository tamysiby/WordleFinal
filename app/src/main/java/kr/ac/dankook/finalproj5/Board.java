package kr.ac.dankook.finalproj5;

public class Board {
    Board() {
        lives = 5;
//        led =
//        fled =
//        textlcd =
    }

    DotMatrix dotmatrix = new DotMatrix();
    Led led = new Led();
    FLed fled = new FLed();
    TextLCD textlcd = new TextLCD();

    private String answer = new String();
//    DotMatrix dotmatrix = new DotMatrix();
//    Led led = new Led();
//    FLed fled = new FLed();
//    TextLCD textlcd = new TextLCD();

    int lives;

    public void loseALife() {
        lives = lives - 1;
        led.currentLives(lives);
        fled.loseALife(lives);
    }

    public void setAnswer(String ans){
        answer = ans;
    }

    public void youLost() {
        dotmatrix.lost();
        fled.lost();
        textlcd.lost(answer);
        led.currentLives(0);
    }

    public void youWin(){
        dotmatrix.win();
        fled.win();
        textlcd.win();
        led.setLedFull();
    }

    public void display(String str){
        textlcd.display(str);
    }

    public void reset(){
        lives = 5;
        led.reset();
        dotmatrix.stop();
        fled.reset();
        textlcd.initialize();
    }
}
//must do
//TODO 1) LED:  Why does it return to full. Behavior: Full -> Three -> Full -> One {check from hb_ source in assam. ask nad cause i think it's the hw}
//TODO 2) FullColorLed: Yellow for lose a life, Green for You win!!, Red for you lose

//extra
//TODO 1) DotMatrix: fix the restart, stop. So when "play again?" can stop the thread.
//TODO 2) Make celebration louder by lights in loops (dancing lights like how the board comes with)





