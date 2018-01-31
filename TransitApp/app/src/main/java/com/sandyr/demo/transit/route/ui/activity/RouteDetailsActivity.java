package com.sandyr.demo.transit.route.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.sandyr.demo.transit.R;
import com.sandyr.demo.transit.route.model.Route;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class RouteDetailsActivity extends AppCompatActivity {

    private static final String MAIN_ACTIVITY_ITEMS = "#ITEMS";
    @BindView(R.id.image_details)
    ImageView imageView;
    @BindView(R.id.title_details)
    TextView title_textview;
    @BindView(R.id.caption_details)
    TextView caption_textview;

    Unbinder unbinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_route_details);
        unbinder = ButterKnife.bind(this);
        Route item = getIntentExtras();
        /*Picasso.with(this).load(item.getUrl())
                .error(R.drawable.ic_placeholder)
                .placeholder(R.drawable.ic_placeholder)
                .into(imageView);*/

        //Setting text view title
        title_textview.setText(item.getType());
        //Setting text view id
        caption_textview.setText(item.getProvider());
    }

    public static Intent newIntent(Context context, Route item) {
        Intent intent = new Intent(context, RouteDetailsActivity.class);
        intent.putExtra(MAIN_ACTIVITY_ITEMS, item);
        return intent;
    }

    private Route getIntentExtras() {
        Route data = (Route) getIntent().getExtras().getSerializable(MAIN_ACTIVITY_ITEMS);
        return data;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
}
