package com.example.brunoorocha.petshop;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends Activity {
    static TextView textView;
    Async downloadJsonAsync = new Async(this);
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.textview_pets);
    }

    public void onClickListPets(View view) {
        downloadJsonAsync.execute("https://raw.githubusercontent.com/humbertobeltrao/AndroidUtils/master/pets.json");
    }
}
