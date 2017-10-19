package nikhil.bhople.mvpwithdagger.dagger.component;

import com.squareup.picasso.Picasso;

import dagger.Component;
import nikhil.bhople.mvpwithdagger.ApiInterfaces;
import nikhil.bhople.mvpwithdagger.dagger.module.ApiInterfaceModule;
import nikhil.bhople.mvpwithdagger.dagger.module.PicassoModule;
import nikhil.bhople.mvpwithdagger.dagger.scope.MainApplicationScope;

/**
 * Created by admin on 18-Oct-17.
 */

@MainApplicationScope
@Component (modules = {ApiInterfaceModule.class, PicassoModule.class} )
public interface MainComponent {

    Picasso getPicasso();

    ApiInterfaces getApiInterface();
}
