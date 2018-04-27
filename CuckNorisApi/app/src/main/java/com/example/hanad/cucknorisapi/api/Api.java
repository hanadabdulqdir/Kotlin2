package com.example.hanad.cucknorisapi.api;

import com.example.hanad.cucknorisapi.model.Jokes;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by hanad on 22/02/2018.
 */

public interface Api {

    String BASE_URL =" http://api.icndb.com/jokes/jokenumber/";

    @GET("jokenumber")
    //Call<List<Jokes>> getJokes();
    Call<Jokes> getJokes();

    //http://api.icndb.com/jokes/jokenumber
}
