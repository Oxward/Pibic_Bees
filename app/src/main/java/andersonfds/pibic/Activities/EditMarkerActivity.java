package andersonfds.pibic.Activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import andersonfds.pibic.R;

public class EditMarkerActivity extends AppCompatActivity
{

    private EditText txtMarkerTitle;
    private Button btSaveMTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_marker);

        final LatLng latLng = getIntent().getParcelableExtra("location");
        txtMarkerTitle = findViewById( R.id.txtMarkerTitle );
        btSaveMTitle = findViewById( R.id.btSaveMTitle );

        btSaveMTitle.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                MarkerOptions marker = new MarkerOptions().position(latLng);
                if( txtMarkerTitle.getText() != null )
                {
                    marker.title( txtMarkerTitle.getText().toString() );
                }
                Intent intent = new Intent();
                intent.putExtra("marker", marker );
                setResult(Activity.RESULT_OK,  intent);
                finish();
            }
        });
    }
}
