package govinda.practic.com.layoutapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.viewpagerindicator.CirclePageIndicator;

import java.util.ArrayList;
import java.util.List;

import govinda.practic.com.layoutapp.controller.ApiService;
import govinda.practic.com.layoutapp.controller.CustomPagerAdapter;
import govinda.practic.com.layoutapp.model.ItemImages;
import govinda.practic.com.layoutapp.model.ItemMovieDetail;
import govinda.practic.com.layoutapp.model.ItemMovies;
import govinda.practic.com.layoutapp.model.Poster;
import govinda.practic.com.layoutapp.model.Result;
import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

public class ScrollingActivity extends AppCompatActivity {

    ViewPager viewpager;
    Toolbar toolbar;
    TextView txt_title,txt_overview,txt_ratting;
    RatingBar ratingBar;
    Result item;
    String id,name;
    ItemMovieDetail details;
    ItemImages images;
    CollapsingToolbarLayout collapsingToolbarLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling);
        id=getIntent().getExtras().getString("movie_id");
        name=getIntent().getExtras().getString("movie_name");
        init();

    }



    private void init(){
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        txt_title=(TextView)findViewById(R.id.txt_title);
        txt_overview=(TextView)findViewById(R.id.txt_overview);
        txt_ratting=(TextView)findViewById(R.id.txt_ratting);
        ratingBar=(RatingBar)findViewById(R.id.ratingBar);
        collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout);
        collapsingToolbarLayout.setTitle("");
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        /*
        Rigth Side option icon
         */
        // toolbar.setOverflowIcon(getResources().getDrawable(R.mipmap.ic_launcher));
        viewpager = (ViewPager) findViewById(R.id.pager);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(""+details.getHomepage()));
                    startActivity(browserIntent);
                }catch (Exception e)
                {
                    e.printStackTrace();
                }

            }
        });

        LoadMovieDetail(id);
        LoadImages(id);
    }

    public void LoadImages(String id){
        // Asynchronous Call in Retrofit 2.0

        ApiService service = App.getRetrofit().create(ApiService.class);
        Call<ItemImages> call = service.getImagesByMovieID(id);
        call.enqueue(new Callback<ItemImages>() {
            @Override
            public void onResponse(Response<ItemImages> response, Retrofit retrofit) {
                try {

                images=response.body();
                viewpager.setAdapter(new CustomPagerAdapter(getApplicationContext(),images.getPosters()));
                viewpager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                    @Override
                    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                    }

                    @Override
                    public void onPageSelected(int position) {

                    }

                    @Override
                    public void onPageScrollStateChanged(int state) {

                    }
                });
                CirclePageIndicator titleIndicator = (CirclePageIndicator)findViewById(R.id.titles);
                titleIndicator.setViewPager(viewpager);

                }catch (Exception e)
                {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Throwable t) {
                Log.d("Movie Response",""+t.getMessage());
                Toast.makeText(getApplicationContext(),"No Network connection!!",Toast.LENGTH_LONG).show();
            }
        });
    }

    public void LoadMovieDetail(String id){
        // Asynchronous Call in Retrofit 2.0
        ApiService service = App.getRetrofit().create(ApiService.class);
        Call<ItemMovieDetail> call = service.getDetailsByID(id);
        call.enqueue(new Callback<ItemMovieDetail>() {
            @Override
            public void onResponse(Response<ItemMovieDetail> response, Retrofit retrofit) {
                try {
                details=response.body();
                txt_title.setText(details.getTitle());
                txt_overview.setText(details.getOverview());
                txt_ratting.setText(""+details.getVoteAverage());
                    ratingBar.setMax(5);
                    ratingBar.setNumStars(5);
                    float rate = Float.valueOf(String.valueOf(details.getVoteAverage()));
                    ratingBar.setRating(rate);
                }catch (Exception e)
                {
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(Throwable t) {
                Log.d("Movie Response",""+t.getMessage());
                Toast.makeText(getApplicationContext(),"No Network connection!!",Toast.LENGTH_LONG).show();
            }
        });
    }
}
