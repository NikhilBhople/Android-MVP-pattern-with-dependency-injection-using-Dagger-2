package nikhil.bhople.mvpwithdagger.application.dagger.module;

import android.content.Context;

import dagger.Module;
import dagger.Provides;
import nikhil.bhople.mvpwithdagger.application.dagger.scope.MainApplicationScope;
import nikhil.bhople.mvpwithdagger.extra.SharedUtils;

/**
 * Created by NIkhil on 20-01-2018.
 */
@Module
public class SharedUtilsModule {
    @Provides
    @MainApplicationScope
    public SharedUtils sharedUtils(Context context){
        return new SharedUtils(context);
    }
}
