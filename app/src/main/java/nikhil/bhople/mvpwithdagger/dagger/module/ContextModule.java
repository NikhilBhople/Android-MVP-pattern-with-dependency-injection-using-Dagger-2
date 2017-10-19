package nikhil.bhople.mvpwithdagger.dagger.module;

import android.content.Context;

import dagger.Module;
import dagger.Provides;
import nikhil.bhople.mvpwithdagger.dagger.scope.MainApplicationScope;

/**
 * Created by admin on 18-Oct-17.
 */
@Module
public class ContextModule {

    // it is called as external dependency so each time while building daggerMainComponent we have to provide context

    private final Context context;

    public ContextModule(Context context) {
        this.context = context;
    }

    @Provides
    @MainApplicationScope // giving main application costume scope
    Context provideContext(){
        return context;
    }
}
