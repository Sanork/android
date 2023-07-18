package com.example.test.ui.gallery;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.test.Change;
import com.example.test.MainActivity;
import com.example.test.MenuActivity;
import com.example.test.R;
import com.example.test.UserData;
import com.example.test.databinding.FragmentGalleryBinding;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;

public class GalleryFragment extends Fragment {

    interface RequestUser{

        @GET("/api/main/{uid}")
        Call<UserData> getUser(@Path("uid") String uid);

    }
    TextView name1;
    TextView mail;
    TextView phone;

    TextView birth;
    TextView gender;
    TextView city;
    Button button;
    private FragmentGalleryBinding binding;
    public MenuActivity menuActivity;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        GalleryViewModel galleryViewModel =
                new ViewModelProvider(this).get(GalleryViewModel.class);

        binding = FragmentGalleryBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        name1 = root.findViewById(R.id.textView11);
        mail = root.findViewById(R.id.textView12);
        phone = root.findViewById(R.id.textView13);
        birth = root.findViewById(R.id.textView15);
        gender = root.findViewById(R.id.textView16);
        city = root.findViewById(R.id.textView18);
        menuActivity = (MenuActivity) getActivity();
        button = root.findViewById(R.id.button);
        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://192.168.43.180:8888").addConverterFactory(GsonConverterFactory.create()).build();
        MainActivity.RequestUser requestUser = retrofit.create(MainActivity.RequestUser.class);
        requestUser.getUser(menuActivity.id).enqueue(new Callback<UserData>() {
            @Override
            public void onResponse(Call<UserData> call, Response<UserData> response) {

                name1.setText(response.body().getData().getName().toString());
                mail.setText(response.body().getData().getMail().toString());
                phone.setText(response.body().getData().getPhone().toString());
                birth.setText(response.body().getData().getBirth().toString());
                gender.setText(response.body().getData().getGender().toString());
                city.setText(response.body().getData().getCity().toString());
            }

            @Override
            public void onFailure(Call<UserData> call, Throwable t) {

            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), Change.class);
                intent.putExtra("id", menuActivity.id);
                //Toast toast = Toast.makeText(getApplicationContext(), response.body().id, Toast.LENGTH_SHORT);
                //toast.show();
                startActivity(intent);
            }
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}