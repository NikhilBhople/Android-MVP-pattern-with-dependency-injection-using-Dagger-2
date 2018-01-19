package nikhil.bhople.mvpwithdagger.activities.dagger;

import dagger.Component;
import nikhil.bhople.mvpwithdagger.activities.RecipeActivity;
import nikhil.bhople.mvpwithdagger.application.dagger.component.MainComponent;

/**
 * Created by admin on 18-Oct-17.
 */

@RecipeActivityScope
@Component(modules = RecipeActivityModule.class, dependencies = MainComponent.class)
// when ever it require extra dependency it will fetch form main component eg: Picasso, retrofit,etc

public interface RecipeActivityComponent {
    void injectRecipeActivity(RecipeActivity activity);

/*     like this way you can create seperate component, module and scope
     for each activity or fragment according to your requirement  */
}
