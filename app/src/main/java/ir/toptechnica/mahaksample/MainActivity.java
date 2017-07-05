package ir.toptechnica.mahaksample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.List;
import butterknife.ButterKnife;
import ir.toptechnica.mahaksample.Adapter.EndlessRecyclerViewScrollListener;
import ir.toptechnica.mahaksample.Adapter.RecyclerAdapter;
import ir.toptechnica.mahaksample.Model.Contacts;
import ir.toptechnica.mahaksample.Model.Result;
import ir.toptechnica.mahaksample.Remote.SampleApi;
import jp.wasabeef.recyclerview.adapters.AlphaInAnimationAdapter;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by saeid.mhd@gmail.com on 1/18/2017.
 */

public class MainActivity extends AppCompatActivity {


    RecyclerView recyclerView;
    LinearLayoutManager layoutManager;
    RecyclerAdapter recycleAdapter;
    public static List<Result> results ;
    int current_page = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_card);
        ButterKnife.bind(this);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        fetch2();

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        EndlessRecyclerViewScrollListener scrollListener = new EndlessRecyclerViewScrollListener(layoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {


                SampleApi.Factory.getInstances().getResults(page).enqueue(new Callback<Contacts>() {
                    @Override
                    public void onResponse(Call<Contacts> call, Response<Contacts> response) {

                        List<Result> result = response.body().getResults();
                        results.addAll(result);

                    }

                    @Override
                    public void onFailure(Call<Contacts> call, Throwable t) {
                        Log.e("failed", t.getMessage());


                    }
                });


                final int curSize = recycleAdapter.getItemCount();


                view.post(new Runnable() {
                    @Override
                    public void run() {
                        recycleAdapter.notifyItemRangeInserted(curSize, results.size() - 1);

                    }
                });

            }
        };

        recyclerView.addOnScrollListener(scrollListener);

    }



    public void fetch2()
    {
        SampleApi.Factory.getInstances().getResults(current_page).enqueue(new Callback<Contacts>() {
            @Override
            public void onResponse(Call<Contacts> call, Response<Contacts> response) {

                results = response.body().getResults();


                recycleAdapter = new RecyclerAdapter(results,getApplicationContext());
                AlphaInAnimationAdapter alphaAdapter =
                        new AlphaInAnimationAdapter
                                (recycleAdapter);

                alphaAdapter.setDuration(1000);
                recyclerView.setAdapter(alphaAdapter);



            }

            @Override
            public void onFailure(Call<Contacts> call, Throwable t) {
                Log.e("failed", t.getMessage());


            }
        });


    }



}
