package com.sandyr.demo.transit.route.Injector;

import android.app.Application;

import com.sandyr.demo.transit.route.Injector.modules.ContextModule;
import com.sandyr.demo.transit.route.Injector.modules.RoutePresenterModule;
import com.sandyr.demo.transit.route.Injector.modules.InteractorModule;
import com.sandyr.demo.transit.route.ui.activity.RouteActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by sandyr on 7/16/2017.
 */

public class RouteApplication extends Application {

    /**
     * Newly added modules must be added to the @Component annotation here. You must also provide
     * further inject() methods for new classes that want to perform injection.
     */
    @Singleton
    @Component(modules = {RoutePresenterModule.class, InteractorModule.class, ContextModule.class})
    public interface ApplicationComponent {
        void inject(RouteActivity routeActivity);
    }

    @Override
    public void onCreate() {
        super.onCreate();

        // This setups up the component which is used by other views (activities/fragments/etc., not Android views) for injection.
        // This pulls all modules which have statically declared @Provides methods automatically.
        DaggerRouteApplication_ApplicationComponent.builder().build();
    }
}
