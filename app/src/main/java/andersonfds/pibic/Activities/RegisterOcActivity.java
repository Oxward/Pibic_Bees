package andersonfds.pibic.Activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.GoogleMap;

import java.nio.file.Files;

import andersonfds.pibic.R;

public class RegisterOcActivity extends AppCompatActivity
{

    //Constantes
    public static final int REQUEST_IMAGE_CAPTURE = 101;

    private ImageView imageView;
    private FusedLocationProviderClient mFusedLocationClient;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_oc);

        imageView = findViewById( R.id.imgTiraFoto );
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
    }

    public void buttonPress( View view)
    {
        switch ( view.getId() )
        {
            case R.id.btFoto:
                takePicture(view);
                break;

            case R.id.btGal:
                break;

            case R.id.btEnv:
                break;

            case R.id.btCanc:
                break;

        }
    }

    public void buttonTeste( View v )
    {
        if(v.getId() == R.id.btTestar)
        {
            EditText txtNome = findViewById(R.id.txtNome);
            EditText txtCont = findViewById(R.id.txtCont);

            String nome, contato;
            nome = txtNome.getText().toString();
            contato = txtCont.getText().toString();

            TextView teste = findViewById(R.id.labelTesteNome);
            TextView teste2 = findViewById(R.id.labelTesteContato);
            teste.setText(""+nome);
            teste2.setText(""+contato);
        }
    }

    public void takePicture( View view )
    {
        Intent imageTakeIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        if( imageTakeIntent.resolveActivity( getPackageManager()) != null)
        {
            startActivityForResult( imageTakeIntent, REQUEST_IMAGE_CAPTURE);
        }
    }

    protected void createLocationRequest()
    {
        LocationRequest mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(10000);
        mLocationRequest.setFastestInterval(5000);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if( requestCode == REQUEST_IMAGE_CAPTURE  &&  resultCode == RESULT_OK )
        {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            imageView.setImageBitmap( imageBitmap );
        }
    }
}