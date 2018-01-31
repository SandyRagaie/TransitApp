package com.sandyr.demo.transit.route.Interactor.Services;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sandyr.demo.transit.common.util.JSONFileReaderUtil;
import com.sandyr.demo.transit.route.Interactor.Services.responses.RouteServiceResponse;

import rx.Observable;
import rx.functions.Func0;

/**
 * Created by Sandy on 1/31/2018.
 */

public class MockRouteService implements RouteService{

    Context mContext;

    public MockRouteService(Context context) {
        mContext=context;
    }
    @Override
    public Observable<RouteServiceResponse> getRoutes() {
        final RouteServiceResponse routeServiceResponse = fetchDataFromFile();

        Observable observable= Observable.defer(new Func0<Observable<RouteServiceResponse>>() {
            @Override
            public Observable<RouteServiceResponse> call() {
                return Observable.just(routeServiceResponse);
            }
        });
        return observable;
    }

    private RouteServiceResponse fetchDataFromFile(){
        String json = JSONFileReaderUtil.getInstance().readFileAsString(mContext);
        Gson gson = new GsonBuilder().create();
        return gson.fromJson(json, RouteServiceResponse.class);
    }
}
