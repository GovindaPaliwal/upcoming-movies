package govinda.practic.com.layoutapp;

import android.app.Application;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;

/**
 * Created by admin on 09-01-2016.
 */
public class App extends Application {
    public static Retrofit retrofit;
    @Override
    public void onCreate() {
        super.onCreate();
        retrofit = new Retrofit.Builder()
                .baseUrl("https://api.themoviedb.org")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static Retrofit getRetrofit() {
        return retrofit;
    }
}
