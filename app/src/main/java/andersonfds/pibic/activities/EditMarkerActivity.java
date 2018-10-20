package andersonfds.pibic.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import andersonfds.pibic.R;
import andersonfds.pibic.classes.Markers;
import andersonfds.pibic.database.Markers_ViewModel;

public class EditMarkerActivity extends AppCompatActivity
{

    private final String TAG = EditMarkerActivity.class.toString();

    private EditText txtMarkerTitle;
    private Markers_ViewModel markers_viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_marker);

        markers_viewModel = new Markers_ViewModel(getApplication());
        final LatLng latLng = getIntent().getParcelableExtra("location");
        txtMarkerTitle = findViewById( R.id.txtMarkerTitle );
        Button btSaveMTitle = findViewById(R.id.btSaveMTitle);

        btSaveMTitle.setOnClickListener(v -> {
            MarkerOptions marker = new MarkerOptions().position(latLng);
            if (txtMarkerTitle.getText() != null) {
                marker.title(txtMarkerTitle.getText().toString());
            }
            Markers m = new Markers("teste@teste.com", marker.getTitle(),
                    latLng.latitude, latLng.longitude);
            markers_viewModel.insertMarker(m);
            Log.d(TAG, "onCreate: Saved the marker in database");
            Intent intent = new Intent();
            intent.putExtra("marker", marker);
            setResult(Activity.RESULT_OK, intent);
            finish();
        });
    }
}
