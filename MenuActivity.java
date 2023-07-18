package com.example.test;

import android.os.Bundle;
import android.view.Menu;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.test.databinding.ActivityMenuBinding;
import com.google.android.material.navigation.NavigationView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;

public class MenuActivity extends AppCompatActivity {
    public String id;
    TextView name;
    TextView bonus;
    TextView name1;
    TextView mail;
    TextView phone;

    //
    interface RequestUser{

        @GET("/api/main/{uid}")
        Call<UserData> getUser(@Path("uid") String uid);

        @GET("/api/main/count/{uid}")
        Call<CountData> getCount(@Path("uid") String uid);

    }
    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMenuBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMenuBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.appBarMenu.toolbar);

        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow)
                .setOpenableLayout(drawer)
                .build();
        Bundle arguments = getIntent().getExtras();
        id = arguments.get("id").toString();

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_menu);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
        name = findViewById(R.id.textView3);
        bonus = findViewById(R.id.textView6);
        name1 = findViewById(R.id.textView11);
        mail = findViewById(R.id.textView12);
        phone = findViewById(R.id.textView13);

        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://192.168.43.180:8888").addConverterFactory(GsonConverterFactory.create()).build();
        MenuActivity.RequestUser requestUser = retrofit.create(MenuActivity.RequestUser.class);
        requestUser.getUser(id).enqueue(new Callback<UserData>() {
            @Override
            public void onResponse(Call<UserData> call, Response<UserData> response) {
                name.setText(response.body().getData().getName().toString());
            }

            @Override
            public void onFailure(Call<UserData> call, Throwable t) {

            }
        });

        requestUser.getCount(id).enqueue(new Callback<CountData>() {
            @Override
            public void onResponse(Call<CountData> call, Response<CountData> response) {

                bonus.setText(response.body().getData().getCount().toString());
            }

            @Override
            public void onFailure(Call<CountData> call, Throwable t) {
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_menu);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
}