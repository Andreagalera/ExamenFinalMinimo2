package dsa.eetac.upc.edu.finalexamenminimo2;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

public interface APIRest {

    //We specify the url
    String BASE_URL = "https://do.diba.cat/api/dataset/municipis/format/json/pag-ini/1/pag-fi/";

    //Get
    @GET("11")
    Call<Municipi> getAllMunicipi();

    static APIRest createAPIRest() {
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        return retrofit.create(APIRest.class);
    }
}
