package com.example.xyapp;


import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
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
        setContentView(R.layout.activity_main);
        setUpMapIfNeeded();
        location();
                }
    
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.main, menu);
		return super.onCreateOptionsMenu(menu);
	}
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle presses on the action bar items
        switch (item.getItemId()) {
            case R.id.statusPosten:
            	statusPosten();
            	break;}
                return true;
                }
    
    public void statusPosten(){	
    	Intent i = new Intent(this, StatusPosten.class);
    	startActivityForResult(i, 0);
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
		mMap.addMarker(markerOptions.draggable(true));}
		});
	
  	}
    }

