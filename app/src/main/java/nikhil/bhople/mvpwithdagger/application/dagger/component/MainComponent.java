package nikhil.bhople.mvpwithdagger.application.dagger.component;

import com.squareup.picasso.Picasso;

import dagger.Component;
import nikhil.bhople.mvpwithdagger.application.dagger.module.SharedUtilsModule;
import nikhil.bhople.mvpwithdagger.extra.ApiInterfaces;
import nikhil.bhople.mvpwithdagger.application.dagger.module.ApiInterfaceModule;
import nikhil.bhople.mvpwithdagger.application.dagger.module.PicassoModule;
import nikhil.bhople.mvpwithdagger.application.dagger.scope.MainApplicationScope;
import nikhil.bhople.mvpwithdagger.extra.SharedUtils;

/**
 * Created by admin on 18-Oct-17.
 */

@MainApplicationScope
@Component (modules = {ApiInterfaceModule.class, PicassoModule.class, SharedUtilsModule.class} )
public interface MainComponent {

    Picasso getPicasso();

    ApiInterfaces getApiInterface();

    SharedUtils getSharedUtils();
}
