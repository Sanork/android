package com.example.test;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.*;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;
import org.apache.http.*;

import javax.net.ssl.HttpsURLConnection;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;

public class MainActivity extends AppCompatActivity {

    public interface RequestUser{

        @GET ("/api/main/{uid}")
        Call<UserData> getUser(@Path("uid") String uid);


        @POST("/api/main/1")
        Call<ResponsePost> postUser(@Body RequestPost requestPost);
    }


private Button button2;

    private Button button3;

private EditText editTextText;
    private EditText editTextText2;

    private TextView textView2;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DatabaseHandler dbHandler = new DatabaseHandler();
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        editTextText = findViewById(R.id.editTextText);
        editTextText2 = findViewById(R.id.editTextText2);

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            if (editTextText2.getText().toString() != "" & editTextText.getText().toString() != "") {
                String one = editTextText2.getText().toString();
                String two = editTextText.getText().toString();
                Retrofit retrofit = new Retrofit.Builder().baseUrl("http://192.168.43.50:8888").addConverterFactory(GsonConverterFactory.create()).build();
                RequestUser requestUser = retrofit.create(RequestUser.class);
                requestUser.postUser(new RequestPost(editTextText2.getText().toString(), editTextText.getText().toString())).enqueue(new Callback<ResponsePost>() {
                    @Override
                    public void onResponse(Call<ResponsePost> call, Response<ResponsePost> response) {
                        Intent intent = new Intent(MainActivity.this, MenuActivity.class);
                        intent.putExtra("id", response.body().id);
                        //Toast toast = Toast.makeText(getApplicationContext(), response.body().id, Toast.LENGTH_SHORT);
                        //toast.show();
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
                Toast toast = Toast.makeText(getApplicationContext(), "Введите Email и пароль", Toast.LENGTH_SHORT);
                toast.show();
            }
            }

        });
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Registration.class);
                startActivity(intent);

            }
        });

        }

}

