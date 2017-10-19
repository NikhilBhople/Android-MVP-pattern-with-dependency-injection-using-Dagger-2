package nikhil.bhople.mvpwithdagger.dagger.module;

import android.content.Context;

import com.jakewharton.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;

import dagger.Module;
import dagger.Provides;
import nikhil.bhople.mvpwithdagger.dagger.scope.MainApplicationScope;
import okhttp3.OkHttpClient;

/**
 * Created by admin on 18-Oct-17.
 */
@Module (includes = {NetworkModule.class, ContextModule.class}) // what ever dependency you need form other module add here
public class PicassoModule {

    @Provides
    @MainApplicationScope
    public OkHttp3Downloader provideOkHttp3Downloder(OkHttpClient okHttpClient){
        return new OkHttp3Downloader(okHttpClient);

       /*  if you want that both retrofit and picasso will call through OkHttpClient
         then use it so that both can share same cache file
         otherwise directly use following provider to get Picasso, picasso will have
         there own caching mechanism  */
    }

    @Provides
    @MainApplicationScope
    public Picasso providePicasso(Context context, OkHttp3Downloader okHttp3Downloader){
        return new Picasso.Builder(context)
                .downloader(okHttp3Downloader)
                .build();
    }

}
