package com.example.unknown.okta_verify;

import android.app.IntentService;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startActivity(new Intent(this, Video.class));

        TextView textView = findViewById(R.id.textView);
        textView.setText("Trolling begins in 5 seconds");
    }

    @Override
    protected void onStop() {
        super.onStop();
    }
}
