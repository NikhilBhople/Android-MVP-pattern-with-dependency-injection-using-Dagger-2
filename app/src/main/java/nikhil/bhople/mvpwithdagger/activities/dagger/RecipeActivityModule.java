package nikhil.bhople.mvpwithdagger.activities.dagger;

import dagger.Module;
import dagger.Provides;
import nikhil.bhople.mvpwithdagger.activities.mvp.RecipeActivityView;

/**
 * Created by admin on 18-Oct-17.
 */
@Module
public class RecipeActivityModule {

    /*     like this way you can create seperate component, module and scope
     for each activity or fragment according to your requirement  */

    private final RecipeActivityView view;
    /*  check the constructor of presenter what it require externally.
     It require view and apiInterface, apiInterface we are getting form main component
     so we have provide only view here */

    public RecipeActivityModule(RecipeActivityView view) {
        this.view = view;
    }

    @Provides
    @RecipeActivityScope
    RecipeActivityView getView(){
        return view;
    }
}
