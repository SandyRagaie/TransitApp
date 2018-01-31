package com.sandyr.demo.transit.route.ui.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sandyr.demo.transit.R;
import com.sandyr.demo.transit.route.model.Route;
import com.sandyr.demo.transit.route.ui.viewholder.SingleRouteViewHolder;

import java.util.ArrayList;
import java.util.List;


public class RouteAdapter extends RecyclerView.Adapter<SingleRouteViewHolder> {
    private final ArrayList<Route> routeList = new ArrayList<>();
    private final RouteAdapterListener adapterListener;

    public RouteAdapter(RouteAdapterListener listener) {
        this.adapterListener = listener;
    }

    @Override
    public SingleRouteViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_route_adapter_cell, null);
        return new SingleRouteViewHolder(view, adapterListener);
    }

    @Override
    public void onBindViewHolder(SingleRouteViewHolder customViewHolder, int position) {
        customViewHolder.onBind(routeList.get(position));
    }


    @Override
    public int getItemCount() {
        return routeList.size();
    }

    @Override
    public void onViewAttachedToWindow(final SingleRouteViewHolder holder) {
        super.onViewAttachedToWindow(holder);
    }

    public void insertItems(List<Route> routes) {
        int startPosition = routeList.size();
        int endPosition = startPosition + routes.size() - 1;
        routeList.addAll(routes);
      //  notifyItemRangeInserted(startPosition, endPosition);
    }

    public void removeAllItems() {
        routeList.clear();
        notifyDataSetChanged();
    }

    public ArrayList<Route> getCachedRoutes() {
        return routeList;
    }

}

