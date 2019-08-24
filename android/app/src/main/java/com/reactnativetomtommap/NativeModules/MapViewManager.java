package com.reactnativetomtommap.NativeModules;

import android.view.View;


import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.common.MapBuilder;
import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.annotations.ReactProp;


import java.util.Map;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class MapViewManager extends SimpleViewManager<View> {
    @Nonnull
    @Override
    public String getName() {
        return "MapViewManager";
    }

    @Nonnull
    @Override
    protected View createViewInstance(@Nonnull ThemedReactContext reactContext) {
        TomTomMap tomTomMap = new TomTomMap(reactContext);
        tomTomMap.onResume();
        return tomTomMap;
    }

    @ReactProp(name = "mapCenter")
    public void setCenter(TomTomMap mapView, @Nullable ReadableMap center) {
        mapView.setMapCenter(center);
    }

    @ReactProp(name = "markers")
    public void setMarkers(TomTomMap mapView, @Nullable ReadableArray markers) {
        mapView.setMarkers(markers);
    }

    @ReactProp(name = "mapZoom")
    public void setMapZoom(TomTomMap mapView, @Nullable Integer zoom) {
        mapView.setMapZoom(zoom);
    }


    @Nullable
    @Override
    public Map getExportedCustomBubblingEventTypeConstants() {
        return MapBuilder.builder()
                .put("onMapReady", MapBuilder.of("phasedRegistrationNames", MapBuilder.of("bubbled", "onMapReady")))
                .build();
    }


}
