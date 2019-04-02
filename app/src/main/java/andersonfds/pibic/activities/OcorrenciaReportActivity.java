package andersonfds.pibic.activities;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import andersonfds.pibic.R;
import andersonfds.pibic.fragments.OcorrenciaFinalizada;
import andersonfds.pibic.fragments.OcorrenciaPendente;
import andersonfds.pibic.fragments.SectionsPageAdapter;

public class OcorrenciaReportActivity extends AppCompatActivity {

    private static final String TAG = "OcorrenciaReportActivit";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ocorrencia_report);
        Log.d(TAG, "onCreate: Iniciou");

        ViewPager mViewPager = findViewById(R.id.container);
        setupViewPager(mViewPager);

        TabLayout tabLayout = findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);
    }

    private void setupViewPager(ViewPager viewPager) {
        SectionsPageAdapter adapter = new SectionsPageAdapter(getSupportFragmentManager());
        adapter.addFragment(new OcorrenciaPendente(), "Ocorrências Pendentes");
        adapter.addFragment(new OcorrenciaFinalizada(), "Ocorrências Finalizadas");
        viewPager.setAdapter(adapter);
    }
}