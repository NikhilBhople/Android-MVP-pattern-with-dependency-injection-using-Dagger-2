package nikhil.bhople.mvpwithdagger.application.dagger.module;

import dagger.Module;
import dagger.Provides;
import nikhil.bhople.mvpwithdagger.extra.ApiInterfaces;
import nikhil.bhople.mvpwithdagger.extra.Constant;
import nikhil.bhople.mvpwithdagger.application.dagger.scope.MainApplicationScope;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by admin on 18-Oct-17.
 */
@Module(includes = {NetworkModule.class, ContextModule.class} )
public class ApiInterfaceModule {

    @Provides
    @MainApplicationScope
    public Retrofit provideRetrofit(OkHttpClient okHttpClient){
        return new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .baseUrl(Constant.baseUrl)
                .build();
    }

    @Provides
    @MainApplicationScope
    public ApiInterfaces provideApiInterface(Retrofit retrofit){
        return retrofit.create(ApiInterfaces.class);
    }

}
