package nikhil.bhople.mvpwithdagger.application;

import android.app.Activity;
import android.app.Application;

import nikhil.bhople.mvpwithdagger.application.dagger.component.DaggerMainComponent;
import nikhil.bhople.mvpwithdagger.application.dagger.component.MainComponent;
import nikhil.bhople.mvpwithdagger.application.dagger.module.ContextModule;

/**
 * Created by admin on 18-Oct-17.
 */

public class MyApplication extends Application{

    private MainComponent component;
    private static MyApplication instance;

    @Override
    public void onCreate() {
        super.onCreate();

        component = DaggerMainComponent.builder()
                .contextModule(new ContextModule(this)) // it is external dependency so we have to provide
                .build();

        instance = this;

    }

    public static MyApplication get(Activity activity){
        return (MyApplication) activity.getApplication();
    }

    public static synchronized MyApplication getInstance(){
        return instance;
    }

    public MainComponent getComponent(){
        return component;
    }
}
