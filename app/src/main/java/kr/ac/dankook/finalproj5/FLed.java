package kr.ac.dankook.finalproj5;

import java.util.concurrent.TimeUnit;

public class FLed {
    FLed(){
        FLEDControl(5, 0,0,0);
    }
    private int[] led_val;
    private int reset = 0;

    void loseALife(int l){
        if ( l == 4 ) {
            FLEDControl(9, 0x50,0x70,0);
        } else if (l == 3) {
            FLEDControl(9, 0x50,0x70,0);
            FLEDControl(8, 0x50,0x70,0);
        } else if (l == 2) {
            FLEDControl(9, 0x50,0x70,0);
            FLEDControl(8, 0x50,0x70,0);
            FLEDControl(7, 0x50,0x70,0);
        } else if (l == 1) {
            FLEDControl(5, 0x50,0x70,0);
        } else {

        }
    }

    void lost(){
        FLEDControl(5,0x50, 0, 0);
    }

    void win(){
        FLEDControl(5,0x80, 0x80, 0x80);
        reset = 0;
    }

    void reset(){
        FLEDControl(5, 0,0,0);
        reset = 1;
    }


    public native void FLEDControl(int ledNum, int red, int green, int blue);
}