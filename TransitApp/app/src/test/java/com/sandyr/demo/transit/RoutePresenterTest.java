package com.sandyr.demo.transit;

import com.sandyr.demo.transit.route.model.Route;
import com.sandyr.demo.transit.route.presenter.RoutePresenterImpl;
import com.sandyr.demo.transit.route.view.RouteView;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;

import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class RoutePresenterTest {

    @Mock
    public RouteView view;
    @Mock
    public RoutePresenterImpl presenter;

    @Before
    public void setUp() throws Exception {
        presenter = new RoutePresenterImpl();
        presenter.setView(view);
    }


    @Test
    public void checkIfViewIsReleasedOnDestroy() {
        presenter.onDestroy();
        assertNull(presenter.getRouteView());
    }

    @Test
    public void checkIfItemsArePassedToView() {
        ArrayList<Route> items = new ArrayList<Route>();
        Route item = new Route();
        item.setType("type");
        item.setProvider("provider");

        items.add(item);
        presenter.getRouteView().onLoadImagesByPhraseSuccess(items);
        verify(view, times(1)).onLoadImagesByPhraseSuccess(items);
    }
}
