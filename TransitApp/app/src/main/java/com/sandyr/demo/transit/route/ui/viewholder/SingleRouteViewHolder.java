package com.sandyr.demo.transit.route.ui.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.sandyr.demo.transit.R;
import com.sandyr.demo.transit.route.model.Route;
import com.sandyr.demo.transit.route.ui.adapters.RouteAdapterListener;

import butterknife.ButterKnife;

public class SingleRouteViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    private ImageView imageView;
    private TextView titleTextView;
    private TextView idTextView;
    private TextView descriptionTextView;
    private RouteAdapterListener adapterListener;

    public SingleRouteViewHolder(View view, RouteAdapterListener listener) {
        super(view);
        adapterListener = listener;
        this.imageView = ButterKnife.findById(view, R.id.gallery_item_image);
        this.titleTextView = ButterKnife.findById(view, R.id.gallery_item_title);
        this.idTextView = ButterKnife.findById(view, R.id.gallery_item_image_id);
        this.descriptionTextView = ButterKnife.findById(view, R.id.gallery_item_description);
        view.setOnClickListener(this);
    }

    public void onBind(Route item) {

        /*if (item.getUrl() != null) {
            //Download image using picasso library
            Picasso.with(itemView.getContext()).load(item.getUrl())
                    .error(R.drawable.ic_placeholder)
                    .placeholder(R.drawable.ic_placeholder)
                    .into(imageView);
        }*/
        //Setting text view Provider
        titleTextView.setText(item.getProvider());
        //Setting text view Price
        if(item.getPrice()!=null) {
            idTextView.setText(item.getPrice().getAmount() + " " + item.getPrice().getCurrency());
        }
        //Setting text View type
        descriptionTextView.setText(item.getType());
    }

    @Override
    public void onClick(View v) {
        int position = getAdapterPosition();
        adapterListener.onItemClick(position);
    }
}