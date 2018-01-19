package nikhil.bhople.mvpwithdagger.extra;

import java.util.Map;

import nikhil.bhople.mvpwithdagger.activities.mvp.RecipeApiResponce;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

/**
 * Created by admin on 18-Oct-17.
 */

public interface ApiInterfaces {

    @GET("/api")
    Call<RecipeApiResponce> getApiResponce(@QueryMap Map<String,String> params);
}
