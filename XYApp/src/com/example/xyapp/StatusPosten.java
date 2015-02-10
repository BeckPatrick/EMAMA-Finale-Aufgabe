package com.example.xyapp;





import java.sql.Blob;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.support.v7.app.ActionBarActivity;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class StatusPosten extends Activity {
	
	public String user ;
	public String kommentar ;
	private EditText editName;
	private EditText editKommentar;
	private Button fotoHinzufuegen;
	private static int RESULT_LOAD_IMAGE = 1;
	private Uri uriData;
	double mlong;
	double mlat;
	private ProgressDialog pDialog;
	private static String url_saveStatus = "http://halloooo.byethost15.com/input.php";
	public Bitmap bitmap;
	
	private static final String TAG_SUCCESS = "success";
	
	   /* private static final Blob TAG_BILD = ;
	    private static final double TAG_STANDORTLA = ":standordla";
	    private static final double TAG_STANDORTLO = ":standortlo";*/
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_status_posten);
	
		fotoHinzufuegen=(Button) findViewById(R.id.foto);
		
		

		//Speichern der aktuellen GPS Koordinaten
		LocationManager lm = (LocationManager)getSystemService(Context.LOCATION_SERVICE); 
		Location location = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
		mlong = location.getLongitude();
		mlat = location.getLatitude();
		
		//Speichern des Bildes
		fotoHinzufuegen.setOnClickListener(new View.OnClickListener() {

		public void onClick(View v) {
		openPicture();
		}
	});}
	
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.status_posten, menu);
		return super.onCreateOptionsMenu(menu);
	}
	
	

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		 switch (item.getItemId()) {
         case R.id.senden:
        	new SaveStatusPosten().execute();
         	break;}
             return true;
             }
	public void openPicture(){
		
		
		Intent i = new Intent(Intent.ACTION_PICK,
				android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
		Log.i("Gallery", "Open Gallery!");

		startActivityForResult(i, RESULT_LOAD_IMAGE);
		Log.i("Gallery", "Start new Activity");
	}
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);

		if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK
				&& null != data) {
			Uri selectedImage = data.getData();
			uriData =   selectedImage;
			String[] filePathColumn = { MediaStore.Images.Media.DATA };
			Log.i("open" + "", MediaStore.Images.Media.DATA);

			Cursor cursor = getContentResolver().query(selectedImage,
					filePathColumn, null, null, null);
			cursor.moveToFirst();

			int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
			String picturePath = cursor.getString(columnIndex);
			Log.i("open", picturePath);
			cursor.close();
			
			Toast.makeText(getApplicationContext(),
					"Bild gespeichert", Toast.LENGTH_SHORT).show();
		
		}}
	
	//Status Senden 
		
	JSONParser jsonParser = new JSONParser();
	
	class SaveStatusPosten extends AsyncTask<String, String, String> {
		 
        
          //Before starting background thread Show Progress Dialog
      
        @Override
        protected void onPreExecute() {
        	 super.onPreExecute();
             pDialog = new ProgressDialog(StatusPosten.this);
             pDialog.setMessage("Speichere Status..");
             pDialog.setIndeterminate(false);
             pDialog.setCancelable(true);
             pDialog.show();
        }
 
        
        
      
        protected String doInBackground(String... args) {
 
        	editName = (EditText) findViewById(R.id.name);
    		user = editName.getText().toString();
    		
    		editKommentar = (EditText) findViewById(R.id.kommentar);
    		kommentar = editKommentar.getText().toString();
 
            // Building Parameters
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("user", user));
            params.add(new BasicNameValuePair("kommentar", kommentar));
            /*params.add(new BasicNameValuePair("", uriData));
            params.add(new BasicNameValuePair("", mlong));
            params.add(new BasicNameValuePair("", mlat));*/

            // sending modified data through http request
            // Notice that update product url accepts POST method
            JSONObject json = jsonParser.makeHttpRequest(url_saveStatus,
                    "POST", params);
            
            Log.d("Create Response", json.toString());
 
            // check json success tag
            try {
                JSONArray successArray = json.getJSONArray(TAG_SUCCESS);
                int success = successArray.getInt(0);
 
                if (success == 1) {
                    // successfully updated
                	
                	Toast.makeText(getApplicationContext(),
        					"Success", Toast.LENGTH_SHORT).show();
                    
                } else{
                		
                	Toast.makeText(getApplicationContext(),
        					"Fail", Toast.LENGTH_SHORT).show();
                                    }
            } catch (JSONException e) {
                e.printStackTrace();
            }
 
            return null;
        }
 
        //After completing background task Dismiss the progress dialog

        protected void onPostExecute(String file_url) {
            // dismiss the dialog once product updated
        	pDialog.dismiss();
        }	
}
	}
