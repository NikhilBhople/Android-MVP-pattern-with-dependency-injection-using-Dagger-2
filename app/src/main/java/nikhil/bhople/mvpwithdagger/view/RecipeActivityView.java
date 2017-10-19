package nikhil.bhople.mvpwithdagger.view;

import java.util.List;

import nikhil.bhople.mvpwithdagger.model.RecipeApiResponce;

/**
 * Created by admin on 18-Oct-17.
 */

public interface RecipeActivityView {

    void onSuccessApiResponce(List<RecipeApiResponce.Result> results);

    void onFailApiCall(String message);
}
