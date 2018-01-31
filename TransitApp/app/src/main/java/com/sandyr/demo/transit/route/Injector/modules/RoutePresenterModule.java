package com.sandyr.demo.transit.route.Injector.modules;

import com.sandyr.demo.transit.route.presenter.RoutePresenter;
import com.sandyr.demo.transit.route.presenter.RoutePresenterImpl;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
/**
 * Created by sandyr on 7/16/2017.
 */

/**
 * Modules aren't used by you directly, they're used by Dagger. @Provides annotated methods are
 * used to lookup classes during injection. @Singleton indicates that only one instance of the object
 * is used, and used everywhere when injected.
 */
@Module
public class RoutePresenterModule {
    @Singleton
    @Provides
    static RoutePresenter providesGalleryPresenter() {
        return new RoutePresenterImpl();
    }
}