package nikhil.bhople.mvpwithdagger.presenter;

import java.util.Map;

import javax.inject.Inject;

import nikhil.bhople.mvpwithdagger.ApiInterfaces;
import nikhil.bhople.mvpwithdagger.model.RecipeApiResponce;
import nikhil.bhople.mvpwithdagger.view.RecipeActivityView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by admin on 18-Oct-17.
 */

public class RecipeActivityPresenter {

    private final ApiInterfaces apiInterfaces;
    private final RecipeActivityView view;

    @Inject // give this annotation so that dagger will inject it for you
    public RecipeActivityPresenter(ApiInterfaces apiInterfaces, RecipeActivityView view) {
        this.apiInterfaces = apiInterfaces;
        this.view = view;
    }

    public void getRecipeData(Map<String, String> params) {

        Call<RecipeApiResponce> call = apiInterfaces.getApiResponce(params);
        call.enqueue(new Callback<RecipeApiResponce>() {
            @Override
            public void onResponse(Call<RecipeApiResponce> call, Response<RecipeApiResponce> response) {

             if(response.body()!=null)
             {
                 if(response.body().getResults().size()>0){
                     view.onSuccessApiResponce(response.body().getResults());
                 }else {
                     view.onFailApiCall("No more search result");
                 }
             }
             else {
                 view.onFailApiCall("Search result is not available");
             }
            }

            @Override
            public void onFailure(Call<RecipeApiResponce> call, Throwable t) {

                view.onFailApiCall("Check your internet connection");

            }
        });
    }
}
