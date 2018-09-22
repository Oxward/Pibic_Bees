package andersonfds.pibic.Activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import andersonfds.pibic.Classes.Markers;
import andersonfds.pibic.Database.Marker_Repository;
import andersonfds.pibic.R;

public class EditMarkerActivity extends AppCompatActivity
{

    private final String TAG = EditMarkerActivity.class.toString();

    private EditText txtMarkerTitle;
    private Button btSaveMTitle;

    private Marker_Repository marker_repository;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_marker);

        marker_repository = new Marker_Repository(getApplication());
        final LatLng latLng = getIntent().getParcelableExtra("location");
        txtMarkerTitle = findViewById( R.id.txtMarkerTitle );
        btSaveMTitle = findViewById( R.id.btSaveMTitle );

        btSaveMTitle.setOnClickListener(v -> {
            MarkerOptions marker = new MarkerOptions().position(latLng);
            if (txtMarkerTitle.getText() != null) {
                marker.title(txtMarkerTitle.getText().toString());
            }
            Markers m = new Markers("teste@teste.com", marker.getTitle(),
                    latLng.latitude, latLng.longitude);
            marker_repository.insertMarker(m);
            Log.d(TAG, "onCreate: m.getNome(): " + m.getNome() + " marker.getTitle(): " + marker.getTitle());
            /*new Marker_Repository(getApplication())
                    .insertMarker(new Markers("teste@teste.com", marker.getTitle(),
                            latLng.latitude, latLng.longitude));*/
            Log.d(TAG, "onCreate: Saved the marker in database");
            Intent intent = new Intent();
            intent.putExtra("marker", marker);
            setResult(Activity.RESULT_OK, intent);
            finish();
        });
    }
}
