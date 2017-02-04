package ir.toptechnica.mahaksample.Remote;

import ir.toptechnica.mahaksample.Model.Contacts;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by sa on 1/17/2017.
 */

public interface SampleApi {
    String Base_Url = "https://randomuser.me/";

    @GET("api/?results=10&seed=78954123")
    Call<Contacts> getResults(@Query("page") int page);

    class Factory {
       private static SampleApi service;

        public static SampleApi getInstances(){
            if(service == null){

                Retrofit retrofit = new Retrofit.Builder()
                        .addConverterFactory(GsonConverterFactory.create())
                        .baseUrl(Base_Url)
                        .build();

                service = retrofit.create(SampleApi.class);
                return service;
            }else {

                return service;

            }




        }



    }



}
