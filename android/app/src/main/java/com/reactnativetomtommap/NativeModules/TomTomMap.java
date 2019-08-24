package com.reactnativetomtommap.NativeModules;

import android.content.Context;

import androidx.annotation.NonNull;

import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.events.RCTEventEmitter;
import com.tomtom.online.sdk.common.location.LatLng;
import com.tomtom.online.sdk.map.CameraPosition;
import com.tomtom.online.sdk.map.MapView;
import com.tomtom.online.sdk.map.MarkerBuilder;
import com.tomtom.online.sdk.map.OnMapReadyCallback;
import com.tomtom.online.sdk.map.SimpleMarkerBalloon;
import com.tomtom.online.sdk.map.TomtomMap;

public class TomTomMap extends MapView implements OnMapReadyCallback {

    private int _zoom;
    private ReadableArray _markers;
    private ReadableMap _center;
    private Boolean isMapReady = false;
    private TomtomMap _tomTomMap;


    public TomTomMap(@NonNull Context context) {
        super(context);
        this.addOnMapReadyCallback(this);
    }

    protected void setMapCenter(ReadableMap center) {
        _center = center;
        if (isMapReady){
            _tomTomMap.centerOn(_center.getDouble("lat"), center.getDouble("lng"));
        }
    }

    protected void setMarkers(ReadableArray markers) {
        _markers = markers;
        if (isMapReady){
            _tomTomMap.removeMarkers();
            drawMarkers();
        }
    }

    protected void setMapZoom(Integer zoom) {
        _zoom = zoom;
        if (isMapReady){
            _tomTomMap.zoomTo(_zoom);
        }
    }


    @Override
    public void onMapReady(@NonNull TomtomMap tomtomMap) {
        isMapReady = true;
        _tomTomMap = tomtomMap;
        reactNativeEvent("onMapReady", null);
        LatLng mapCenter = new LatLng(_center.getDouble("lat"), _center.getDouble("lng"));
        tomtomMap.centerOn(CameraPosition.builder(mapCenter).zoom(_zoom).build());
        drawMarkers();
    }

    private void drawMarkers(){
        for (int i = 0; i < _markers.size(); i++) {
            ReadableMap marker = _markers.getMap(i);
            SimpleMarkerBalloon balloon = new SimpleMarkerBalloon(marker.getString("label"));
            LatLng markerCenter = new LatLng(marker.getDouble("lat"), marker.getDouble("lng"));
            _tomTomMap.addMarker(new MarkerBuilder(markerCenter).markerBalloon(balloon));
        }
    }

    protected void reactNativeEvent(String eventName, WritableMap eventParams) {
        ReactContext reactContext = (ReactContext) this.getContext();
        reactContext
                .getJSModule(RCTEventEmitter.class)
                .receiveEvent(this.getId(), eventName, eventParams);
    }
}
