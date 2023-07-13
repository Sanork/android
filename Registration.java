package com.example.test;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Registration extends AppCompatActivity {
    private Button button4;
    private EditText editTextText6;
    private EditText editTextText3;
    private EditText editTextText4;
    private EditText editTextText5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        button4 = findViewById(R.id.button4);
        editTextText6 = findViewById(R.id.editTextText6);
        editTextText3 = findViewById(R.id.editTextText3);
        editTextText4 = findViewById(R.id.editTextText4);
        editTextText5 = findViewById(R.id.editTextText5);
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DatabaseHandler dbHandler = new DatabaseHandler();
                dbHandler.signUpUser(editTextText6.getText().toString(), editTextText3.getText().toString(),
                        editTextText4.getText().toString(), editTextText5.getText().toString(), "100");
            }
        });
    }
}

