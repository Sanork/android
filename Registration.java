package com.example.test;

import static java.lang.Integer.parseInt;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public class Registration extends AppCompatActivity {

    public interface RequestUser{


        @POST("/api/main/reg")
        Call<ResponsePost> postUser(@Body RequestReg requestReg);

        @POST("/api/main/check")
        Call<ResponsePost> CheckMail(@Body RequestMail requestReg);
    }
    private Button button4;
    private EditText editTextText6;
    private EditText editTextText3;
    private EditText editTextText4;
    private EditText editTextText5;

    private EditText editTextText7;
    private RadioButton male;
    private RadioButton female;

    private EditText editTextDate;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        button4 = findViewById(R.id.button4);
        editTextText6 = findViewById(R.id.editTextText6);
        editTextText3 = findViewById(R.id.editTextText3);
        editTextText4 = findViewById(R.id.editTextText4);
        editTextText5 = findViewById(R.id.editTextText5);
        editTextText7 = findViewById(R.id.editTextText7);
        editTextDate = findViewById(R.id.editTextDate);
        male = findViewById(R.id.radioButton);
        female = findViewById(R.id.radioButton2);
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean flag = true;
                if (editTextText6.getText().toString().trim().equals("") == false  & editTextText3.getText().toString().trim().equals("") == false & editTextText4.getText().toString().trim().equals("") ==false  & editTextText5.getText().toString().trim().equals("") == false & editTextText7.getText().toString().trim().equals("") == false & editTextDate.getText().toString().trim().equals("") == false) {
                    
                    Retrofit retrofit = new Retrofit.Builder().baseUrl("http://192.168.43.180:8888").addConverterFactory(GsonConverterFactory.create()).build();
                    Registration.RequestUser requestUser = retrofit.create(Registration.RequestUser.class);

                    requestUser.CheckMail(new RequestMail(editTextText6.getText().toString())).enqueue(new Callback<ResponsePost>() {
                        @Override
                        public void onResponse(Call<ResponsePost> call, Response<ResponsePost> response) {

                            if(parseInt(response.body().getId()) == 0)
                            {
                                String gen;
                                if (female.isChecked() == true)
                                {
                                    gen = "Женский";
                                }
                                else
                                {
                                    gen = "Мужской";
                                }

                                requestUser.postUser(new RequestReg(editTextText6.getText().toString(), editTextText3.getText().toString(), editTextText4.getText().toString() ,editTextText5.getText().toString(), editTextDate.getText().toString(), gen, editTextText7.getText().toString())).enqueue(new Callback<ResponsePost>() {
                                    @Override
                                    public void onResponse(Call<ResponsePost> call, Response<ResponsePost> response) {
                                        Intent intent = new Intent(Registration.this, MainActivity.class);
                                        startActivity(intent);
                                    }

                                    @Override
                                    public void onFailure(Call<ResponsePost> call, Throwable t) {
                                        Toast toast = Toast.makeText(getApplicationContext(), "Неверный Email или пароль", Toast.LENGTH_SHORT);
                                        toast.show();
                                    }
                                });
                            }
                            else {
                                Toast toast = Toast.makeText(getApplicationContext(), "Данный Email уже зарегистрирован", Toast.LENGTH_SHORT);
                                toast.show();
                            }
                        }
                        @Override
                        public void onFailure(Call<ResponsePost> call, Throwable t) {
                        }
                    });


                }
                else {
                    Toast toast = Toast.makeText(getApplicationContext(), "Введите данные", Toast.LENGTH_SHORT);
                    toast.show();
                }

            }
        });
    }
}

