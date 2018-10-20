package andersonfds.pibic.activities;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.maps.DirectionsApiRequest;
import com.google.maps.GeoApiContext;
import com.google.maps.PendingResult;
import com.google.maps.internal.PolylineEncoding;
import com.google.maps.model.DirectionsResult;
import com.google.maps.model.DirectionsRoute;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import andersonfds.pibic.ApplicationContextProvider;
import andersonfds.pibic.MapsRouteTracer.DirectionsParser;
import andersonfds.pibic.R;
import andersonfds.pibic.classes.Markers;
import andersonfds.pibic.database.Markers_ViewModel;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback
{

    private static final String TAG = "MapsActivity";
    private static final int REQUEST_LOCATION_PERMISSION = 1;
    private static final int LOCATION_UPDATE_INTERVAL = 3000;
    private static final int EDIT_REQUEST = 1;

    private static List<Markers> mList = new ArrayList<>();
    private List<Marker> listMark = new ArrayList<>();

    private static GoogleMap mMap;
    private GeoApiContext mGeoApiContext = null;
    private Handler mHandler = new Handler();
    private Runnable mRunnable;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        if (mGeoApiContext == null) {
            mGeoApiContext = new GeoApiContext.Builder().apiKey(getString(R.string.api_key)).build();
        }

        //Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                //.setAction("Action", null).show();
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap)
    {
        mMap = googleMap;
        mMap.clear();
        // Marcador no CTF
        LatLng ctf = new LatLng(-6.785604, -43.041879);
        mMap.addMarker(new MarkerOptions().position(ctf).title("CTF"));
        float zoom = 15.0f;
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ctf, zoom));
        setMapLongClick(mMap);
        markerClick(mMap);

        Markers_ViewModel markers_viewModel = new Markers_ViewModel(getApplication());
        new selectAsync(markers_viewModel, mList).execute();

        mMap.setOnInfoWindowClickListener(marker -> {
            markers_viewModel.deleteMarker(new Markers(marker.getId(),
                    marker.getPosition().latitude,
                    marker.getPosition().longitude));
            marker.remove();
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults)
    {
        switch(requestCode)
        {
            case REQUEST_LOCATION_PERMISSION:
                if(grantResults.length > 0  &&  grantResults[0] == PackageManager.PERMISSION_GRANTED)
                {
                    enableMyLocation();
                    break;
                }
        }
    }

    private void setMapLongClick(final GoogleMap map)
    {
        map.setOnMapLongClickListener(latLng -> {
            Intent edit = new Intent(MapsActivity.this, EditMarkerActivity.class);
            edit.putExtra("location", latLng);
            MapsActivity.this.startActivityForResult(edit, EDIT_REQUEST);
            /*
            MarkerOptions marker = new MarkerOptions();
            marker.position(latLng);

            String info = String.format(Locale.getDefault(), "Lat: %1$.5f, Long: %1$.5f",
                    latLng.latitude, latLng.longitude);
            map.insertMarker(new MarkerOptions().icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ROSE))
                    .position(latLng).draggable(true).flat(true).alpha(0.6f).snippet(info));
            */
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case (EDIT_REQUEST): {
                if (resultCode == Activity.RESULT_OK) {
                    MarkerOptions markerOptions = data.getParcelableExtra("marker");
                    mMap.addMarker(markerOptions.draggable(true)
                            .flat(true)
                            .alpha(0.6f)
                            .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ROSE))
                    );
                }
                break;
            }
        }
    }

    private void markerClick(final GoogleMap map)
    {
        map.setOnMarkerClickListener(marker -> {

            Log.d("markerClick", "Entrou no evento. Lista de Pontos: " + listMark.size());
            listMark.add(marker);
            if (listMark.size() % 2 == 0) {
                calculateDirections(listMark.get(0), listMark.get(1));
                //String url = getRequestedUrl(listMark.get(0), listMark.get(1));
                //new TaskRequestDirections().execute(url);
                //Log.d("markerClick", "Traçou a rota. Lista de Pontos: " + listMark.size());
                // new Thread(new Task()).start();
                //listMark.clear();
                return true;
            } else {
                Log.d("markerClick", "Fez nada. Lista de Pontos: " + listMark.size());
                return false;
            }
        });
    }

    private void calculateDirections(Marker orig, Marker dest) {
        Log.d(TAG, "calculateDirections: calculating directions.");

        com.google.maps.model.LatLng destination = new com.google.maps.model.LatLng(
                dest.getPosition().latitude,
                dest.getPosition().longitude
        );
        DirectionsApiRequest directions = new DirectionsApiRequest(mGeoApiContext);

        directions.alternatives(true);
        directions.origin(
                new com.google.maps.model.LatLng(
                        orig.getPosition().latitude,
                        orig.getPosition().longitude
                )
        );
        Log.d(TAG, "calculateDirections: destination: " + destination.toString());
        directions.destination(destination).setCallback(new PendingResult.Callback<DirectionsResult>() {
            @Override
            public void onResult(DirectionsResult result) {
                Log.d(TAG, "calculateDirections: routes: " + result.routes[0].toString());
                Log.d(TAG, "calculateDirections: duration: " + result.routes[0].legs[0].duration);
                Log.d(TAG, "calculateDirections: distance: " + result.routes[0].legs[0].distance);
                Log.d(TAG, "calculateDirections: geocodedWayPoints: " + result.geocodedWaypoints[0].toString());
                addPolylinesToMap(result);
            }

            @Override
            public void onFailure(Throwable e) {
                Log.e(TAG, "calculateDirections: Failed to get directions: " + e.getMessage());

            }
        });
        listMark.clear();
    }

    private void addPolylinesToMap(final DirectionsResult result) {
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                Log.d(TAG, "run: result routes: " + result.routes.length);

                for (DirectionsRoute route : result.routes) {
                    Log.d(TAG, "run: leg: " + route.legs[0].toString());
                    List<com.google.maps.model.LatLng> decodedPath = PolylineEncoding.decode(route.overviewPolyline.getEncodedPath());

                    List<LatLng> newDecodedPath = new ArrayList<>();

                    // This loops through all the LatLng coordinates of ONE polyline.
                    for (com.google.maps.model.LatLng latLng : decodedPath) {

//                        Log.d(TAG, "run: latlng: " + latLng.toString());

                        newDecodedPath.add(new LatLng(
                                latLng.lat,
                                latLng.lng
                        ));
                    }
                    Polyline polyline = mMap.addPolyline(new PolylineOptions().addAll(newDecodedPath));
                    polyline.setColor(ContextCompat.getColor(getApplicationContext(), R.color.colorPrimary));
                    polyline.setClickable(true);

                }
            }
        });
    }

    private String getRequestedUrl(Marker orig, Marker dest) {
        Log.d("getRequestedUrl", "Teste de LatLong orig: "+orig.getPosition().latitude+ ", "+orig.getPosition().longitude);
        Log.d("getRequestedUrl", "Teste de LatLong dest: "+dest.getPosition().latitude+", "+dest.getPosition().longitude);
        String origem = "origin="+orig.getPosition().latitude+","+orig.getPosition().longitude;  //LatLong ponto de origem
        String destino = "destination="+dest.getPosition().latitude+","+dest.getPosition().longitude; //LatLong ponto de destino
        String sensor = "sensor=false";
        String mode = "mode=driving";
        String param = origem + "&" + destino + "&" + sensor + "&" + mode;
        String output = "json";
        return "https://maps.googleapis.com/maps/api/directions/" + output + "?" + param;
    }

    //Traça uma Rota Entre os Pontos Demarcados
    private static String requestDirections(String reqUrl)
    {
        String respString = "";
        InputStream inputStream = null;
        HttpURLConnection httpURLConnection = null;
        try
        {
            URL url = new URL(reqUrl);
            httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.connect();

            //resultado do requerimento
            inputStream = httpURLConnection.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            StringBuilder stringBuffer = new StringBuilder();
            String linha;

            while( (linha = bufferedReader.readLine()) != null)
            {
                stringBuffer.append(linha);
            }

            respString = stringBuffer.toString();
            bufferedReader.close();
            inputStreamReader.close();

        }catch(IOException e)
        {
            e.getStackTrace();
        }finally
        {
            if(inputStream != null)
                try
                {
                    inputStream.close();
                }catch(IOException e)
                {
                    e.getStackTrace();
                }

            assert httpURLConnection != null;
            httpURLConnection.disconnect();
        }
        return respString;
    }

    private static class selectAsync extends AsyncTask<Void, Void, List<Markers>> {

        private Markers_ViewModel markers_viewModel;
        private List<Markers> mAsyncList;

        private selectAsync(Markers_ViewModel markers_viewModel, List<Markers> mList) {
            this.markers_viewModel = markers_viewModel;
            this.mAsyncList = mList;
        }

        @Override
        protected List<Markers> doInBackground(Void... voids) {
            Log.d("asd", "doInBackground: " + mAsyncList.size());
            mAsyncList.addAll(markers_viewModel.getAllMarkers());
            Log.d("asd", "doInBackground: " + mAsyncList.size());
            return mAsyncList;
        }

        @Override
        protected void onPostExecute(List<Markers> list) {
            Log.d("asd", "doInBackground: " + list.size());
            mList = list;
            Log.d("asd", "doInBackground: " + mList.size());
            for (Markers markers : mList) {
                mMap.addMarker(new MarkerOptions().icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ROSE))
                        .position(new LatLng(markers.getLatitude(), markers.getLongitude())).alpha(0.6f).title(markers.getNome()));
            }
            mAsyncList.clear();
            mList.clear();
        }
    }

    //CLASSES PARA TRAÇAR AS ROTAS(Utiliza Internet, estilo GoogleMaps)
    public static class TaskRequestDirections extends AsyncTask<String, Void, String>
    {

        @Override
        protected String doInBackground(String... strings)
        {
            String responseString;
            responseString = requestDirections(strings[0]);

            return responseString;
        }

        @Override
        protected void onPostExecute(String s)
        {
            super.onPostExecute(s);
            //passa o json
            new TaskParser().execute(s);
        }
    }

    //Ativa a Localização Atual(GPS)
    private void enableMyLocation() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) ==
                PackageManager.PERMISSION_GRANTED) {
            mMap.setMyLocationEnabled(true);
        } else {
            ActivityCompat.requestPermissions(this, new String[]
                    {Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION_PERMISSION);
        }
    }

    public static class TaskParser extends AsyncTask<String, Void, List<List<HashMap<String, String>>>>
    {
        @Override
        protected List<List<HashMap<String, String>>> doInBackground(String... strings)
        {
            JSONObject jsonObject;
            List<List<HashMap<String, String>>> routes = null;
            try
            {
                jsonObject = new JSONObject(strings[0]);
                routes = new DirectionsParser().parse(jsonObject);
            }catch(JSONException e)
            {
                e.getStackTrace();
            }

            return routes;
        }

        @Override
        protected void onPostExecute(List<List<HashMap<String, String>>> lists)
        {
            //mostra as rotas no mapa
            ArrayList<LatLng> pontos;
            PolylineOptions polylineOptions = null;

            for(List<HashMap<String, String>> caminho : lists)
            {
                pontos = new ArrayList<>();
                polylineOptions = new PolylineOptions();

                for(HashMap<String, String> ponto : caminho)
                {
                    double lat = Double.parseDouble(ponto.get("lat"));
                    double lon = Double.parseDouble(ponto.get("lon"));

                    pontos.add(new LatLng(lat,lon));
                }

                polylineOptions.addAll(pontos);
                polylineOptions.width(15);
                polylineOptions.color(Color.GREEN);
                polylineOptions.geodesic(true);
            }

            if(polylineOptions != null)
            {
                mMap.addPolyline(polylineOptions);
            } else
            {
                Toast.makeText(ApplicationContextProvider.getContext(), "Rota não encontrada!", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private class Task implements Runnable {
        @Override
        public void run() {
            for (int i = 0; i < 2; i++) {
                try {
                    Thread.sleep(4000);
                } catch (InterruptedException ieE) {
                    Toast.makeText(getApplicationContext(), "Deu Pau.", Toast.LENGTH_LONG).show();
                }
            }
        }
    }
}