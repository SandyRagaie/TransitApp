package com.sandyr.demo.transit.route.presenter;


import com.sandyr.demo.transit.route.Interactor.Services.RouteService;
import com.sandyr.demo.transit.route.Interactor.Services.responses.RouteServiceResponse;
import com.sandyr.demo.transit.route.model.Route;
import com.sandyr.demo.transit.route.view.RouteView;

import java.util.ArrayList;

import javax.inject.Inject;

import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by sandyr on 11/23/2017.
 */

public class RoutePresenterImpl implements RoutePresenter {
    public Subscription subscription;
    private RouteView routeView;
    private ArrayList<Route> items;

    @Inject
    RouteService mService;

    @Inject
    public RoutePresenterImpl() {
    }

    public void setView(RouteView view) {
        routeView = view;
    }

    @Override
    public void onDestroy() {
        if(subscription != null && !subscription.isUnsubscribed()){
            subscription.unsubscribe();
        }
        routeView = null;
    }

    @Override
    public void loadRoutes() {
        Observer<RouteServiceResponse> myObserver = new Observer<RouteServiceResponse>() {

            @Override
            public void onCompleted() {
                if (items != null) {
                    routeView.onLoadDataSuccess(items);
                }
                routeView.hideProgress();
            }

            @Override
            public void onError(Throwable e) {
                routeView.onLoadDataError(e.getMessage());
                routeView.hideProgress();
            }

            @Override
            public void onNext(RouteServiceResponse content) {

                items= (ArrayList<Route>) content.getRoutes();
            }

        };

        subscription = mService.getRoutes()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(myObserver);

        routeView.showProgress();
    }

    /**
     * get view instance
     *
     * @return
     */
    public RouteView getRouteView() {
        return routeView;
    }
}
