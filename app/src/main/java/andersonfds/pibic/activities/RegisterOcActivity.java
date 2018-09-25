package andersonfds.pibic.activities;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.os.Looper;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.model.LatLng;

import andersonfds.pibic.R;
import andersonfds.pibic.classes.RegisterContacts;

public class RegisterOcActivity extends AppCompatActivity {

    //Constantes
    private static final int REQUEST_IMAGE_CAPTURE = 101;
    private static final int REQUEST_CODE = 1000;
    private static final int PICK_IMAGE = 1;


    private  ImageView imageView2;
    private ImageView imageView;
    private FusedLocationProviderClient mFusedLocationProviderClient;
    private LocationRequest mLocationRequest;
    private LocationCallback mLocationCallback;
    private TextView lbTest;
    private Button btEnv;
    private Button btGal;
    private Button btGal2;


    private LatLng location;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_oc);

        imageView = findViewById(R.id.imgTiraFoto);
        imageView2 = findViewById(R.id.imageView2);
        lbTest = findViewById(R.id.lbTest);
        btEnv = findViewById(R.id.btEnv);
        if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_FINE_LOCATION)) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION,
                            Manifest.permission.ACCESS_COARSE_LOCATION,
                            Manifest.permission.ACCESS_NETWORK_STATE}, REQUEST_CODE);
        } else
        {
            //Permissions granted
            buildLocationRequest();
            buildLocationCallback();

            //fusedProviderClient
            mFusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);

            btEnv.setOnClickListener(view ->
            {
              if (ActivityCompat.checkSelfPermission(RegisterOcActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) !=
                      PackageManager.PERMISSION_GRANTED) {
                  ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_CODE);
              }
                mFusedLocationProviderClient.requestLocationUpdates(mLocationRequest, mLocationCallback, Looper.myLooper());
            });
        }
        mFusedLocationProviderClient.removeLocationUpdates(mLocationCallback);

        btGal = findViewById(R.id.btGal);
        btGal2 = findViewById(R.id.btGal2);

        btGal.setOnClickListener(view ->
        {
            openGallery();
        });

        btGal2.setOnClickListener(view ->
        {
            openGallery();
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {




        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            if( imageView.getDrawable() == null)
                imageView.setImageBitmap(imageBitmap);
            else
                imageView2.setImageBitmap(imageBitmap);
        } else if (requestCode == PICK_IMAGE && resultCode == RESULT_OK) {
            Uri uri = data.getData();
            imageView.setImageURI(uri);


        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case REQUEST_CODE: {
                if (grantResults.length > 0) {
                    if (grantResults[0] == PackageManager.PERMISSION_GRANTED)
                    {
                        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_CODE);
                    }
                    else
                      if( grantResults[0] == PackageManager.PERMISSION_DENIED)
                      {
                          Toast.makeText(getApplicationContext(), "Permissões não aceitas.", Toast.LENGTH_SHORT).show();
                      }
                }
            }
        }
    }

    public void buttonPress(View view)
    {
        switch ( view.getId() )
        {
            case R.id.btFoto:
                takePicture(view);
                break;

            case R.id.btGal:
                break;


            case R.id.btGal2:
                break;


            case R.id.btEnv:
                saveData();
                break;

            case R.id.btCanc:
                break;

        }
    }

    private void saveData() {
        EditText txtNome = findViewById(R.id.txtNome);
        EditText txtCont = findViewById(R.id.txtCont);
        EditText txtWpp = findViewById(R.id.txtWpp);
        //ImageView imgTiraFoto = findViewById(R.id.imgTiraFoto);

        String nome = txtNome.getText().toString();
        long cont = Long.parseLong(txtCont.getText().toString());
        long wpp = Long.parseLong(txtWpp.getText().toString());

        RegisterContacts registerContacts = new RegisterContacts(nome, cont, wpp, location.latitude, location.longitude);


    }

    public void takePicture( View view )
    {
        Intent imageTakeIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        if( imageTakeIntent.resolveActivity( getPackageManager()) != null)
        {
            startActivityForResult( imageTakeIntent, REQUEST_IMAGE_CAPTURE);
        }
    }

    private void openGallery() {
        Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(gallery, PICK_IMAGE);
    }

    private void buildLocationRequest()
    {
        mLocationRequest = new LocationRequest();
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        mLocationRequest.setInterval(5000);
        mLocationRequest.setFastestInterval(3000);
        mLocationRequest.setSmallestDisplacement(10);
    }

    private void buildLocationCallback()
    {
        mLocationCallback = new LocationCallback()
        {
            @Override
            public void onLocationResult(LocationResult locationResult)
            {
                for ( Location l : locationResult.getLocations() )
                {
                    //lbTest.setText(String.valueOf(l.getLatitude() + "/" + String.valueOf(l.getLongitude())));
                    lbTest.setText(String.valueOf(l.getLatitude() + "/" + String.valueOf(l.getLongitude())));
                    location = new LatLng(l.getLatitude(), l.getLongitude());
                }
            }
        };
    }

}