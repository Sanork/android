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


public class Change extends AppCompatActivity {

    interface RequestUser{

        @POST("/api/main/change")
        Call<ResponsePost> postUser(@Body RequestChange requestChange);

        @GET("/api/main/{uid}")
        Call<UserData> getUser(@Path("uid") String uid);

    }

    public String id;

    private EditText editTextText6;
    private EditText editTextText3;
    private EditText editTextText4;
    private EditText editTextText5;

    private EditText editTextText7;
    private RadioButton male;
    private RadioButton female;

    private EditText editTextDate;
    private Button button;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change);
            editTextText6 = findViewById(R.id.editTextText6);
            editTextText3 = findViewById(R.id.editTextText3);
            editTextText4 = findViewById(R.id.editTextText4);
            editTextText5 = findViewById(R.id.editTextText5);

        editTextText7 = findViewById(R.id.editTextText7);
        editTextDate = findViewById(R.id.editTextDate);
        male = findViewById(R.id.radioButton);
        female = findViewById(R.id.radioButton2);
        button  = findViewById(R.id.button4);
        Bundle arguments = getIntent().getExtras();
        id = arguments.get("id").toString();
        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://192.168.43.180:8888").addConverterFactory(GsonConverterFactory.create()).build();
        MainActivity.RequestUser requestUser = retrofit.create(MainActivity.RequestUser.class);
        requestUser.getUser(id).enqueue(new Callback<UserData>() {
            @Override
            public void onResponse(Call<UserData> call, Response<UserData> response) {

                editTextText4.setText(response.body().getData().getName().toString());
                editTextText5.setText(response.body().getData().getPhone().toString());
                editTextText7.setText(response.body().getData().getCity());
                editTextDate.setText(response.body().getData().getBirth());
                if (response.body().getData().getGender().toString().equals("Мужской"))
                {
                    male.setChecked(true);

                }
                else {
                    female.setChecked(true);
                }
            }

            @Override
            public void onFailure(Call<UserData> call, Throwable t) {

            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (editTextText4.getText().toString().trim().equals("") ==false  & editTextText5.getText().toString().trim().equals("") == false & editTextText7.getText().toString().trim().equals("") == false & editTextDate.getText().toString().trim().equals("") == false) {

                    String gen;
                    if (female.isChecked() == true) {
                        gen = "Женский";
                    } else {
                        gen = "Мужской";
                    }
                    Retrofit retrofit = new Retrofit.Builder().baseUrl("http://192.168.43.180:8888").addConverterFactory(GsonConverterFactory.create()).build();
                    Change.RequestUser requestUser = retrofit.create(Change.RequestUser.class);
                    requestUser.postUser(new RequestChange(editTextText4.getText().toString(), editTextText5.getText().toString(), editTextDate.getText().toString(), gen, editTextText7.getText().toString(), id)).enqueue(new Callback<ResponsePost>() {
                        @Override
                        public void onResponse(Call<ResponsePost> call, Response<ResponsePost> response) {
                            Intent intent = new Intent(Change.this, MenuActivity.class);
                            intent.putExtra("id", response.body().id);
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
                    Toast toast = Toast.makeText(getApplicationContext(), "Введите данные", Toast.LENGTH_SHORT);
                    toast.show();
                }

            }
        });
    }
}