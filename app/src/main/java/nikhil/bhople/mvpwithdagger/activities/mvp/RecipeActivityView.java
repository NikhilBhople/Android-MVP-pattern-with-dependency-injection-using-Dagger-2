package nikhil.bhople.mvpwithdagger.activities.mvp;

import java.util.List;

/**
 * Created by admin on 18-Oct-17.
 */

public interface RecipeActivityView {

    void onSuccessApiResponce(List<RecipeApiResponce.Result> results);

    void onFailApiCall(String message);
}
