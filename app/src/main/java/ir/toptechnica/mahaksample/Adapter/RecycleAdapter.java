package ir.toptechnica.mahaksample.Adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import ir.toptechnica.mahaksample.DetailActivity;
import ir.toptechnica.mahaksample.Model.Result;
import ir.toptechnica.mahaksample.R;


/**
 * Created by sa on 1/18/2017.
 */

public class RecycleAdapter extends RecyclerView.Adapter<RecycleAdapter.ContactViewHolder> {

    private List<Result> results;
    private Context context;


    class ContactViewHolder extends RecyclerView.ViewHolder{

        //declare widgets with ButterKnife
        @BindView(R.id.First)TextView First;
        @BindView(R.id.Last)TextView Last;
        @BindView(R.id.Email)TextView Email;
        @BindView(R.id.Face)ImageView Face ;
        @BindView(R.id.card_view)CardView card_view ;

        public ContactViewHolder(final View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    // send clicked position to DetailActivity and launch this
                    int position = getAdapterPosition();
                    Intent intent = new Intent(context, DetailActivity.class);
                    intent.putExtra("position",position);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);

                }
            });

        }
    }
    // RecycleAdapter constructor that receive results and contexts
    public RecycleAdapter(List<Result> results, Context context) {
        this.results = results;
        this.context = context;
    }

    @Override
    public RecycleAdapter.ContactViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_view,parent,false);
        RecycleAdapter.ContactViewHolder viewHolder = new ContactViewHolder(v);
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(ContactViewHolder ViewHolder, final int position) {

        ViewHolder.First.setText(results.get(position).getName().getFirst());
        ViewHolder.Last.setText(results.get(position).getName().getLast());
        ViewHolder.Email.setText(results.get(position).getEmail());
        Picasso.with(context)
                .load(results.get(position).getPicture().getLarge())
                .into(ViewHolder.Face);

    }

    @Override
    public int getItemCount() {
        return results.size();
    }


}
