package andersonfds.pibic.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import andersonfds.pibic.R;
import andersonfds.pibic.classes.Markers;
import andersonfds.pibic.database.Repository;

public class EditMarkerActivity extends AppCompatActivity
{

    private final String TAG = EditMarkerActivity.class.toString();

    private EditText txtMarkerTitle;
    private Repository repository;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_marker);

        repository = new Repository(this);
        final LatLng latLng = getIntent().getParcelableExtra("location");
        txtMarkerTitle = findViewById( R.id.txtMarkerTitle );
        Button btSaveMTitle = findViewById(R.id.btSaveMTitle);

        btSaveMTitle.setOnClickListener(v -> {
            MarkerOptions marker = new MarkerOptions().position(latLng);

            if ((TextUtils.isEmpty(txtMarkerTitle.getText().toString()))) {
                txtMarkerTitle.setError("Preencha este campo.");
                return;
            }

            marker.title(txtMarkerTitle.getText().toString());
            Markers m = new Markers("teste@teste.com", marker.getTitle(),
                    latLng.latitude, latLng.longitude);
            repository.insertMarker(m);
            Log.d(TAG, "onCreate: Saved the marker in database");
            Intent intent = new Intent();
            intent.putExtra("marker", marker);
            setResult(Activity.RESULT_OK, intent);
            finish();
        });
    }
}
