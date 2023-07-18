package com.example.test.ui.slideshow;

import static java.lang.Integer.parseInt;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.test.LenData;
import com.example.test.MainActivity;
import com.example.test.MenuActivity;
import com.example.test.R;
import com.example.test.RequestTrans;
import com.example.test.ResponsePost;
import com.example.test.ResponseTrans;
import com.example.test.UserData;
import com.example.test.databinding.FragmentSlideshowBinding;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public class SlideshowFragment extends Fragment {
    interface RequestUser{

        @GET("/api/main/len/{uid}")
        Call<LenData> getUser(@Path("uid") String uid);

        @POST("/api/main/trans")
        Call<ResponseTrans> postUser(@Body RequestTrans requestTrans);

    }
    TableLayout table;
    public int off;
    boolean flag = true;
    public int i;
    LinearLayout layout;
    public MenuActivity menuActivity;
    public String id;
    private FragmentSlideshowBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        SlideshowViewModel slideshowViewModel =
                new ViewModelProvider(this).get(SlideshowViewModel.class);

        binding = FragmentSlideshowBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        menuActivity = (MenuActivity) getActivity();


        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://192.168.43.180:8888").addConverterFactory(GsonConverterFactory.create()).build();
        SlideshowFragment.RequestUser requestUser = retrofit.create(SlideshowFragment.RequestUser.class);
        requestUser.getUser(menuActivity.id).enqueue(new Callback<LenData>() {
            @Override
            public void onResponse(Call<LenData> call, Response<LenData> response) {
                String len = response.body().getData().getLen();
                //Bundle arguments = getIntent().getExtras();
                //id = arguments.get("id").toString();
                ;
                layout = root.findViewById(R.id.linear);
                //Retrofit retrofit = new Retrofit.Builder().baseUrl("http://192.168.43.180:8888:8888").addConverterFactory(GsonConverterFactory.create()).build();
                //SlideshowFragment.RequestUser requestUser = retrofit.create(SlideshowFragment.RequestUser.class);

                LinearLayout constraintLayout = new LinearLayout(SlideshowFragment.super.getContext());
                constraintLayout.setOrientation(LinearLayout.VERTICAL);
                layout.bringToFront();
                constraintLayout.setPadding(0,100,0,0);
                off = 0;
                for (i = 0; i < parseInt(len); i++)
                {
                    requestUser.postUser(new RequestTrans(menuActivity.id, i)).enqueue(new Callback<ResponseTrans>()

                    {
                        @Override
                        public void onResponse(Call<ResponseTrans> call, Response<ResponseTrans> response) {
                            String data[] = {response.body().getDate(), response.body().getTime(), response.body().getSumm()};
                            off++;
                            TextView textView = new TextView(SlideshowFragment.super.getContext());
                            // установка текста текстового поля
                            textView.setText(response.body().getDate()+ "    " + response.body().getTime()+ "      " + response.body().getSumm()+ "руб. (" + parseInt(response.body().getSumm())/10+ " бонусов)");
                            // установка размера текста
                            textView.setTextSize(16);

                            //textView.setPadding(0,170,0,0);
                            ConstraintLayout.LayoutParams layoutParams = new ConstraintLayout.LayoutParams
                                    (ConstraintLayout.LayoutParams.WRAP_CONTENT , ConstraintLayout.LayoutParams.WRAP_CONTENT);
                            // позиционирование в левом верхнем углу контейнера
                            // эквивалент app:layout_constraintLeft_toLeftOf="parent"
                            layoutParams.leftToLeft = ConstraintLayout.LayoutParams.PARENT_ID;
                            // эквивалент app:layout_constraintTop_toTopOf="parent"
                            layoutParams.topToTop = ConstraintLayout.LayoutParams.PARENT_ID;
                            // устанавливаем размеры
                            textView.setLayoutParams(layoutParams);
                            // добавляем TextView в ConstraintLayout
                            layout.addView(textView);

                            //SlideshowFragment.this.getActivity().setContentView(constraintLayout);

                        }

                        @Override
                        public void onFailure(Call<ResponseTrans> call, Throwable t) {
                            //flag = false;
                            i = 10;
                        }

                    });
                    layout.bringToFront();



                }


            }

            @Override
            public void onFailure(Call<LenData> call, Throwable t) {
            }
        });

        //Bundle arguments = getIntent().getExtras();
        //id = arguments.get("id").toString();
        ;
        layout = root.findViewById(R.id.linear);
        //Retrofit retrofit = new Retrofit.Builder().baseUrl("http://192.168.43.180:8888:8888").addConverterFactory(GsonConverterFactory.create()).build();
        //SlideshowFragment.RequestUser requestUser = retrofit.create(SlideshowFragment.RequestUser.class);

        LinearLayout constraintLayout = new LinearLayout(SlideshowFragment.super.getContext());
        constraintLayout.setOrientation(LinearLayout.VERTICAL);
        layout.bringToFront();
        constraintLayout.setPadding(0,100,0,0);
        off = 0;



        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}