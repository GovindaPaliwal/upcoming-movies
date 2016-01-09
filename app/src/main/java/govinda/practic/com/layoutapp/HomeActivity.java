package govinda.practic.com.layoutapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import govinda.practic.com.layoutapp.controller.ApiService;
import govinda.practic.com.layoutapp.controller.HomeAdapter;
import govinda.practic.com.layoutapp.controller.ItemClickSupport;
import govinda.practic.com.layoutapp.model.ItemMovies;
import govinda.practic.com.layoutapp.model.Result;
import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Created by user on 08-Jan-16.
 */
public class HomeActivity extends AppCompatActivity{

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    SwipeRefreshLayout refreshLayout;
    HomeAdapter adapter;
    private List<Result> menuList=new ArrayList<Result>();
    ProgressBar progressBar;
    static String STATE = "true";

    List<ItemMovies> list=new ArrayList<ItemMovies>();
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

            setContentView(R.layout.home_activity);
            init();
            progressBar.setVisibility(View.VISIBLE);
            LoadMovie();

    }

    @Override
    protected void onResume() {
        super.onResume();

        ItemClickSupport.addTo(recyclerView).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                // do it
                Intent call = new Intent(getApplicationContext(), ScrollingActivity.class);
                call.putExtra("movie_id",""+adapter.getItemAtPosition(position).getId());
                call.putExtra("movie_name",""+adapter.getItemAtPosition(position).getTitle());
                startActivity(call);
            }
        });
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                menuList.clear();
                adapter=new HomeAdapter(menuList,getApplicationContext());
                recyclerView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
                LoadMovie();
            }
        });
    }

    public void LoadMovie(){
        // Asynchronous Call in Retrofit 2.0

      ApiService service = App.getRetrofit().create(ApiService.class);
        Call<ItemMovies> call = service.loadMovie();
        call.enqueue(new Callback<ItemMovies>() {
            @Override
            public void onResponse(Response<ItemMovies> response, Retrofit retrofit) {
                if (progressBar.getVisibility()==View.VISIBLE)
                    progressBar.setVisibility(View.GONE);
                refreshLayout.setRefreshing(false);
                Log.d("Movie Response",""+response.body().toString());
               ItemMovies myMovie=response.body();
                Log.d("Movie Response",""+myMovie.getResults().toString());
                list.add(myMovie);
                adapter.addNewPage(myMovie.getResults());
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Throwable t) {
                Log.d("Movie Response",""+t.getMessage());
                Toast.makeText(getApplicationContext(),"No Network connection!!",Toast.LENGTH_LONG).show();
                refreshLayout.setRefreshing(false);
                if (progressBar.getVisibility()==View.VISIBLE)
                    progressBar.setVisibility(View.GONE);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_scrolling, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_profile) {
            Intent page=new Intent(getApplicationContext(),ProfilePage.class);
            startActivity(page);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {

        super.onSaveInstanceState(outState);

    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

}

    public void init(){

        toolbar=(Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setLogo(R.mipmap.ic_launcher_movie);
        recyclerView=(RecyclerView)findViewById(R.id.cardList);
        refreshLayout=(SwipeRefreshLayout)findViewById(R.id.swipe_container);
        progressBar=(ProgressBar)findViewById(R.id.progressBar);
        progressBar.setVisibility(View.GONE);
        layoutManager=new GridLayoutManager(getApplicationContext(),1);
        recyclerView.setLayoutManager(layoutManager);
        adapter=new HomeAdapter(menuList,getApplicationContext());
        recyclerView.setAdapter(adapter);
    }
}
