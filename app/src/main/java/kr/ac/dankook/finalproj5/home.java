package kr.ac.dankook.finalproj5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import kr.ac.dankook.finalproj5.MainActivity;

public class home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        TextView goButHome = (TextView) findViewById(R.id.textView);

        goButHome.setOnClickListener(view -> goHome());
    }


    private void goHome(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}