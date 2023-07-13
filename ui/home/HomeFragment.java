package com.example.test.ui.home;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.test.MainActivity;
import com.example.test.MenuActivity;
import com.example.test.R;
import com.example.test.RequestPost;
import com.example.test.ResponsePost;
import com.example.test.UserData;
import com.example.test.databinding.FragmentHomeBinding;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public class HomeFragment extends Fragment {


TextView textView;
    private FragmentHomeBinding binding;

    interface RequestUser{

        @GET("/api/main/{uid}")
        Call<UserData> getUser(@Path("uid") String uid);
    }
    TextView name;
    TextView bonus;

    @Override
    public void onCreate(@Nullable Bundle savedInstantState)
    {
        super.onCreate(savedInstantState);
    }
    public MenuActivity menuActivity;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        HomeViewModel homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        menuActivity = (MenuActivity) getActivity();
        name = root.findViewById(R.id.textView3);
        bonus = root.findViewById(R.id.textView6);
        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://192.168.43.50:8888").addConverterFactory(GsonConverterFactory.create()).build();
        MainActivity.RequestUser requestUser = retrofit.create(MainActivity.RequestUser.class);
        requestUser.getUser(menuActivity.id).enqueue(new Callback<UserData>() {
            @Override
            public void onResponse(Call<UserData> call, Response<UserData> response) {

                name.setText(response.body().getData().getName().toString());
                bonus.setText(response.body().getData().getBonus().toString());
            }

            @Override
            public void onFailure(Call<UserData> call, Throwable t) {
            }
        });

        //String name = new GetURLData().execute().toString();
        //textView = root.findViewById(R.id.textView);
        //textView.setText(name);
        //final TextView textView = binding.textHome;
        //homeViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}