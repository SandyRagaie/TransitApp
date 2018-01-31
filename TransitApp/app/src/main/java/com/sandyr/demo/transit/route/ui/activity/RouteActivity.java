package com.sandyr.demo.transit.route.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ProgressBar;

import com.sandyr.demo.transit.R;
import com.sandyr.demo.transit.route.Injector.DaggerRouteApplication_ApplicationComponent;
import com.sandyr.demo.transit.route.Injector.modules.ContextModule;
import com.sandyr.demo.transit.route.model.Route;
import com.sandyr.demo.transit.route.presenter.RoutePresenterImpl;
import com.sandyr.demo.transit.route.ui.adapters.RouteAdapter;
import com.sandyr.demo.transit.route.ui.adapters.RouteAdapterListener;
import com.sandyr.demo.transit.route.view.RouteView;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class  RouteActivity extends AppCompatActivity implements RouteView, RouteAdapterListener {
    @Inject
    RoutePresenterImpl mPresenter;
    @BindView(R.id.images_list)
    RecyclerView mRecyclerView;
    @BindView(R.id.progress)
    ProgressBar progress;
    RouteAdapter mAdapter;
    Unbinder unbinder;
    private static final String SAVED_INSTANCE_DATA = "#SAVED_INSTANCE_DATA";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_route);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        unbinder = ButterKnife.bind(this);
        /* The component setup in the RouteApplication takes all Module classes and fills in @Inject
         * annotated fields for you automatically.*/
        DaggerRouteApplication_ApplicationComponent.builder().contextModule(new ContextModule(getApplicationContext()))
                .build().inject(this);

        mPresenter.setView(this);

        initRecyclerView();

        //Load data once
        if( savedInstanceState==null) {
            mPresenter.loadRoutes();
        }
    }

    /**
     * initialize recycler view
     * Add Grid manager
     * TODO: different screen sizes must be considers while initialize GridLayoutManager
     */
    private void initRecyclerView() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        GridLayoutManager lLayout = new GridLayoutManager(RouteActivity.this, 2);

        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(lLayout);
        mAdapter = new RouteAdapter(RouteActivity.this);
        mRecyclerView.setAdapter(mAdapter);

    }

    @Override
    public void showProgress() {
        progress.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progress.setVisibility(View.GONE);
    }

    @Override
    public void onLoadDataSuccess(ArrayList<Route> images) {
        mAdapter.insertItems(images);
    }

    @Override
    public void onLoadDataError(String result) {
        String message = getString(R.string.error_downloading);
        Snackbar snackbar = Snackbar
                .make(mRecyclerView, message, Snackbar.LENGTH_INDEFINITE)
                .setAction(R.string.retry, new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mPresenter.loadRoutes();
                    }
                });
        snackbar.show();
    }

    @Override
    public void onEmptyResult() {
        String message = getString(R.string.warning_empty_result);
        Snackbar snackbar = Snackbar.make(mRecyclerView, message, Snackbar.LENGTH_INDEFINITE);
        snackbar.show();
    }

    @Override
    public void onRemoveImagesFromUI() {
        mAdapter.removeAllItems();
    }

    @Override
    public void onItemClick(int position) {
          Route data = mAdapter.getCachedRoutes().get(position);
          Intent intent = RouteDetailsActivity.newIntent(RouteActivity.this, data);
          startActivity(intent);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putSerializable(SAVED_INSTANCE_DATA, mAdapter.getCachedRoutes());
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
         ArrayList<Route> items = (ArrayList<Route>) savedInstanceState.getSerializable(SAVED_INSTANCE_DATA);
         mPresenter.getRouteView().onLoadDataSuccess(items);
        }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.onDestroy();
        unbinder.unbind();
    }
}

