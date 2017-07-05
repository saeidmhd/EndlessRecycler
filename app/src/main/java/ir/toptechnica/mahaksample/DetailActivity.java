package ir.toptechnica.mahaksample;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import ir.toptechnica.mahaksample.Model.Result;

/**
 * Created by saeid.mhd@gmail.com on 1/18/2017.
 */

public class DetailActivity extends AppCompatActivity {

    private List<Result> results = MainActivity.results;


    @BindView(R.id.iv_profile)ImageView iv_profile;
    @BindView(R.id.iv_profile_back)ImageView iv_profile_back;
    @BindView(R.id.tv_name)TextView tv_name;
    @BindView(R.id.tv_email)TextView tv_email;
    @BindView(R.id.tv_detail)TextView tv_detail;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout);
        ButterKnife.bind(this);

        //get position clicked from RecyclerAdapter
        Intent mIntent = getIntent();
        int intValue = mIntent.getIntExtra("position", 0);

        //name and lastname
        tv_name.setText(results.get(intValue).getName().getFirst() + " " +
                results.get(intValue).getName().getLast());
       //email
        tv_email.setText(results.get(intValue).getEmail());


        tv_detail.setText("City: " + results.get(intValue).getLocation().getCity()+ "\n"
                + "State: " + results.get(intValue).getLocation().getState() + "\n"
                + "Street: " + results.get(intValue).getLocation().getStreet() + "\n"
                + "Username: " + results.get(intValue).getLogin().getUsername() + "\n"
                + "Password: " + results.get(intValue).getLogin().getPassword());

        //profile image
        Picasso.with(this)
                .load(results.get(intValue).getPicture().getLarge())
                .into(iv_profile);

        Picasso.with(this)
                .load(results.get(intValue).getPicture().getLarge())
                .into(iv_profile_back);

    }
}
