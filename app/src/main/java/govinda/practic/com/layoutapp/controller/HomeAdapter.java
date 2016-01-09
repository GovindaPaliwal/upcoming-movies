package govinda.practic.com.layoutapp.controller;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import govinda.practic.com.layoutapp.R;
import govinda.practic.com.layoutapp.model.ItemMovies;
import govinda.practic.com.layoutapp.model.Result;

/**
 * Created by user on 08-Jan-16.
 */
public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.PageViewHolder> {

    private List<Result> menuList;
    private  Context context;
    public HomeAdapter(List<Result> menuList,Context context) {
        this.menuList = menuList;
        this.context=context;
    }

    public void addNewPage(List<Result> itemMovies){
        this.menuList.addAll(itemMovies);
    }

    @Override
    public int getItemCount() {
        return menuList.size();
    }

    public Result getItemAtPosition(int position){
        return this.menuList.get(position);
    }


    @Override
    public void onBindViewHolder(PageViewHolder pageViewHolder, int i) {
        Result result = menuList.get(i);
        pageViewHolder.v_title.setText(""+result.getTitle());
        pageViewHolder.v_date.setText("Release Date:"+result.getReleaseDate());
        if(result.getAdult()) {
            pageViewHolder.v_isadult.setText("Adult:Yes");
        }else{
            pageViewHolder.v_isadult.setText("Adult:No");
        }
        Picasso.with(context).load("https://image.tmdb.org/t/p/w185"+result.getPosterPath()).placeholder(R.drawable.ic_placeholder).into(pageViewHolder.v_img);

    }

    @Override
    public PageViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.
                from(viewGroup.getContext()).
                inflate(R.layout.item_home, viewGroup, false);

        return new PageViewHolder(itemView);
    }

    public static class PageViewHolder extends RecyclerView.ViewHolder {
        protected TextView v_title,v_date,v_isadult;
        protected ImageView v_img;

        public PageViewHolder(View v) {
            super(v);
            v_title =  (TextView) v.findViewById(R.id.item_home_title);
            v_date =  (TextView) v.findViewById(R.id.item_home_date);
            v_isadult =  (TextView) v.findViewById(R.id.item_home_adult);
            v_img=(ImageView)v.findViewById(R.id.item_home_img);
        }
    }

}
