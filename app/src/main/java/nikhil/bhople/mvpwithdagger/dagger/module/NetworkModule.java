package nikhil.bhople.mvpwithdagger.dagger.module;

import android.content.Context;

import java.io.File;

import dagger.Module;
import dagger.Provides;
import nikhil.bhople.mvpwithdagger.dagger.scope.MainApplicationScope;
import okhttp3.Cache;
import okhttp3.OkHttpClient;

/**
 * Created by admin on 18-Oct-17.
 */
@Module (includes = ContextModule.class) // what ever dependency you need form other module add here
public class NetworkModule {

    @Provides
    @MainApplicationScope
    public File provideCacheFile(Context context){
        return new File(context.getCacheDir(), "okhttp_cache");
    }

    @Provides
    @MainApplicationScope
    public Cache provideCache(File cacheFile){
        return new Cache(cacheFile , 10*1000*1000); // creating 10Mb cache
    }

    @Provides
    @MainApplicationScope
    public OkHttpClient provideOkhttpClient(Cache cache){
        return new OkHttpClient.Builder()
                .cache(cache)
                .build();
        /* we are providing cache file for okhttp because
         it will store network call or image, by this way we can
         optimise the network efficiency  */
    }
}
