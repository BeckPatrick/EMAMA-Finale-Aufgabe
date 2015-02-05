package com.example.xyapp;

import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.os.Build;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnMapClickListener;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;



public class MainActivity extends Activity {
	private GoogleMap mMap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_main);
        setUpMapIfNeeded();
        location();
                }
    @Override
	protected void onResume() {
		super.onResume();
		setUpMapIfNeeded();
	}
    private void location(){
    	mMap.setMyLocationEnabled(true);
    	
    }
	private void setUpMapIfNeeded(){
		if (mMap == null) {
			mMap = ((MapFragment) getFragmentManager().findFragmentById(R.id.map) ).getMap();
			if (mMap != null) {
				setUpMap();
			}
		}
	} // setUpMapIfNeeded
	private void setUpMap() {
		/*mMap.addMarker(new MarkerOptions().position(new LatLng(54.0, 11.0)).title("GMap V2").draggable(true));*/		
		
	
	mMap.setOnMapClickListener(new OnMapClickListener() {
		
		@Override
		public void onMapClick(LatLng latLng) {
		MarkerOptions markerOptions = new MarkerOptions();
		markerOptions.position(latLng);
		markerOptions.title(latLng.latitude + " : " + latLng.longitude);
		mMap.clear();
		mMap.animateCamera(CameraUpdateFactory.newLatLng(latLng));
		mMap.addMarker(markerOptions);}
		});
	
	/*@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.gmap_test, menu);
		return true;
	}*/




  

	}
    }

