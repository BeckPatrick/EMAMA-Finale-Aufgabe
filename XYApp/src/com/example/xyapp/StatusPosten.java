package com.example.xyapp;





import android.support.v7.app.ActionBarActivity;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.Uri;
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
import android.widget.Toast;

public class StatusPosten extends Activity {
	
	private String nutzerName;
	private String kommentar;
	private EditText editName;
	private EditText editKommentar;
	private Button fotoHinzufuegen;
	private static int RESULT_LOAD_IMAGE = 1;
	private Uri uriData;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_status_posten);
	
		fotoHinzufuegen=(Button) findViewById(R.id.foto);	
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
         	statusSenden();
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
			uriData = selectedImage;
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
		
	public void statusSenden(){
		
		editName = (EditText) findViewById(R.id.name);
		nutzerName = editName.getText().toString().trim();
		
		editKommentar = (EditText) findViewById(R.id.kommentar);
		kommentar = editKommentar.getText().toString().trim();
		
		
		
		
	}
}
