package com.sandyr.demo.transit.route.view;

import com.sandyr.demo.transit.route.model.Route;

import java.util.ArrayList;

/**
 * Created by sandyr on 11/20/2017.
 */

public interface RouteView {
    /**
     * Show progress
     */
    void showProgress();

    /**
     * hide progress
     */
    void hideProgress();

    /**
     * called when items received from server
     *
     * @param images
     */
    void onLoadDataSuccess(ArrayList<Route> images);

    /**
     * called when error received from server
     *
     * @param result
     */
    void onLoadDataError(String result);

    /**
     * called when empty result received from server
     */
    void onEmptyResult();

    /**
     * called when ui clear needed
     */
    void onRemoveImagesFromUI();
}
