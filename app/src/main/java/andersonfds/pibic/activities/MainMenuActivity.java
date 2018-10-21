package andersonfds.pibic.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.Task;
import com.google.maps.errors.ApiException;

import andersonfds.pibic.R;

public class MainMenuActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private static final String TAG = "MainMenuActivity";
    private static final int RC_SIGN_IN = 1;

    private MenuItem menuLogin;
    private MenuItem menuLogOut;

    private GoogleSignInOptions mGoogleSignInOptions;
    private GoogleSignInClient mGoogleSignInClient;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        menuLogin = findViewById(R.id.menuLogin);
        menuLogOut = findViewById(R.id.menuLogout);

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        mGoogleSignInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail().build();
        mGoogleSignInClient = GoogleSignIn.getClient(this, mGoogleSignInOptions);
    }

    @Override
    public void onBackPressed()
    {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item)
    {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        switch (id)
        {
            case R.id.menuLogin:
                try {
                    signIn();
                } catch (Exception e) {
                    Log.d(TAG, "onNavigationItemSelected: " + e.getMessage());
                }
                break;

            case R.id.menuRegOcor:
                try
                {
                  Intent i = new Intent(MainMenuActivity.this, RegisterOcActivity.class);
                  startActivity(i);
                }catch( Exception e)
                {
                    Log.d(TAG, "" + e.getMessage());
                }
                break;

            case R.id.menuRegLoc:
                try
                {
                  Intent i = new Intent(MainMenuActivity.this, MapsActivity.class);
                  startActivity(i);
                } catch(Exception e)
                {
                    Log.d(TAG, "" + e.getMessage());
                }
                break;

            case R.id.menuExit:
                moveTaskToBack(true );
                android.os.Process.killProcess( android.os.Process.myPid() );
                System.exit(1 );
                break;
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    protected void onStart() {
        super.onStart();
        GoogleSignInAccount googleSignInAccount = GoogleSignIn.getLastSignedInAccount(this);
        updateUi(googleSignInAccount);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
    }

    private void updateUi(GoogleSignInAccount signInAccount) {
        if (signInAccount != null) {
            menuLogin.setVisible(false);
            menuLogOut.setVisible(true);
        } else {
            menuLogin.setVisible(true);
            menuLogOut.setVisible(false);
        }
    }

    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount googleSignInAccount = completedTask.getResult(ApiException.class);
            updateUi(googleSignInAccount);
        } catch (ApiException e) {
            Log.d(TAG, "handleSignInResult: " + e.getMessage());
            updateUi(null);
        }
    }
}
