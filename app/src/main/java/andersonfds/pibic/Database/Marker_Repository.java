package andersonfds.pibic.Database;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;

import andersonfds.pibic.Classes.Markers;

public class Marker_Repository {

    private Marker_DAO markerDAO;
    private LiveData<List<Markers>> allMarkers;


    public Marker_Repository(Application application) {
        ApplicationDatabase applicationDatabase = ApplicationDatabase.getDatabase(application);
        markerDAO = applicationDatabase.markerDAO();
        allMarkers = markerDAO.selectAllMarkers();
    }

    LiveData<List<Markers>> getAllMarkers() {
        return allMarkers;
    }

    public void insertMarker(Markers markers) {
        new insertAsyncTask(markerDAO).execute(markers);
    }


    private static class insertAsyncTask extends AsyncTask<Markers, Void, Void> {
        private Marker_DAO mAsyncTaskMarkerDAO;

        insertAsyncTask(Marker_DAO markerDAO) {
            mAsyncTaskMarkerDAO = markerDAO;
        }

        @Override
        protected Void doInBackground(Markers... markers) {
            mAsyncTaskMarkerDAO.insertMarker(markers[0]);
            return null;
        }
    }

}