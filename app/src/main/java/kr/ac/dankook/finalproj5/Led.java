package kr.ac.dankook.finalproj5;

public class Led {
    Led(){
        on(ledFull);
    }

    char ledFull = 0xFF;    //8
    char ledFour = 0x3F;
    char ledThree = 0xF;   //6
    char ledTwo = 0x3;      //4
    char ledOne = 0x1;      //2
    char ledEmpty = 0;      //0

    void currentLives(int lives){

        if (lives == 4) {
            on(ledFour);
        } else if (lives == 3) {
            on(ledThree);
        } else if (lives == 2) {
            on(ledTwo);
        } else if(lives == 1){
            on(ledOne);
        } else {
            on(ledEmpty);
        }
    }

    void setLedFull(){
        on(ledFull);
    }

    void reset(){
        on(ledFull);
    }
    public native void on(char data);
}
