package com.sandyr.demo.transit.route.Interactor.Services;

import com.sandyr.demo.transit.route.Interactor.Services.responses.RouteServiceResponse;

import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by sandyr on 11/23/2017.
 */

public interface RouteService {

    @GET("/door2door-io/transit-app-task/47148de6d977b2b46124a756325bfae5bb0dd402/data.json")
    Observable<RouteServiceResponse> getRoutes();

}
