package andersonfds.pibic.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import andersonfds.pibic.R;
import andersonfds.pibic.fragments.SectionsPagaAdapter;

public class OcorrenciaReportActivity extends AppCompatActivity {

    private static final String TAG = "OcorrenciaReportActivit";

    private SectionsPagaAdapter mSectionsPagaAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ocorrencia_report);

    }
}