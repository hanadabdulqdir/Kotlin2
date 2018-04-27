package com.example.hanad.cucknorisapi;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.hanad.cucknorisapi.api.Api;
import com.example.hanad.cucknorisapi.model.Joke;
import com.example.hanad.cucknorisapi.model.Jokes;

import java.util.List;
import java.util.function.Consumer;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.JokeslistView)
    ListView listView;

    @BindView(R.id.swipe2Refresh)
    SwipeRefreshLayout strl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);


        strl.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                strl.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        getJokesMethod();
                    }
                }, 10000L);
            }
        });

        getJokesMethod();
    }

    private void getJokesMethod() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        Api api = retrofit.create(Api.class);

        Call<Jokes> call = api.getJokes();
        call.enqueue(new Callback<Jokes>() {
            @Override
            public void onResponse(Call<Jokes> call, Response<Jokes> response) {
                Jokes jokesList = response.body();

                Joke[] jokes =  jokesList.getValue();

                for (Joke joke : jokes) {
                    System.out.println(joke.getJokes());
                    //System.out.print(joke.getId());
                }
                listView.setAdapter(new ArrayAdapter<Joke>(getApplicationContext(),android.R.layout.simple_list_item_1,jokes));
            }

            @Override
            public void onFailure(Call<Jokes> call, Throwable t) {
                Toast.makeText(getApplicationContext(), " There has been and error", Toast.LENGTH_SHORT).show();

            }
        });

    }
}
