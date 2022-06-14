package kr.ac.dankook.finalproj5;

public class TextLCD {
    TextLCD(){
        on();
        clear();
        initialize();
    }

    void display(String guess){
        //displays current guess
        off();
        on();
        print1Line("Your guess is:");
        print2Line(guess);
    }

    void win(){
        //YOU WINNN
        clear();
        print1Line("You win!");
        print2Line("Congrats:)");
    }

    void lost(String ans){
        //You lose:(
        clear();
        print1Line("You lose boohoo:(");
        print2Line("Answer is " + ans);
    }





    public native void on();
    public native void off();
    public native void initialize();
    public native void clear();

    public native void print1Line(String str);
    public native void print2Line(String str);
}
