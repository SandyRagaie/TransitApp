package com.sandyr.demo.transit.route.Interactor.Services.responses;

import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.sandyr.demo.transit.route.model.Route;

public class RouteServiceResponse implements Serializable {

    @SerializedName("routes")
    @Expose
    private List<Route> routes = null;
    //TODO: provider_attributes to be added

    public List<Route> getRoutes() {
        return routes;
    }

    public void setRoutes(List<Route> routes) {
        this.routes = routes;
    }


}
