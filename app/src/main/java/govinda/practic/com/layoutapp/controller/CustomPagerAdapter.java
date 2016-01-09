package govinda.practic.com.layoutapp.controller;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.squareup.picasso.Picasso;

import java.util.List;

import govinda.practic.com.layoutapp.R;
import govinda.practic.com.layoutapp.model.ItemImages;
import govinda.practic.com.layoutapp.model.Poster;

/**
 * Created by user on 08-Jan-16.
 */
public class CustomPagerAdapter extends PagerAdapter {

    Context mContext;
    LayoutInflater mLayoutInflater;
    List<Poster> images;
    public CustomPagerAdapter(Context context, List<Poster> images) {
        mContext = context;
        this.images=images;
        mLayoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        if(images.size()>=5){
            return 5;
        }else
        return images.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == ((LinearLayout) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View itemView = mLayoutInflater.inflate(R.layout.pager_item, container, false);

        ImageView imageView = (ImageView) itemView.findViewById(R.id.imageView);
        //imageView.setImageResource(mResources[position]);
        Picasso.with(mContext).load("https://image.tmdb.org/t/p/w185"+images.get(position).getFilePath()).placeholder(R.drawable.ic_placeholder).into(imageView);
        container.addView(itemView);

        return itemView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((LinearLayout) object);
    }
}
