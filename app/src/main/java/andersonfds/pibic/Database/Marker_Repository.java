package andersonfds.pibic.Database;

import android.app.Application;
import android.os.AsyncTask;

import java.util.List;

import andersonfds.pibic.Classes.Markers;

public class Marker_Repository {

    private Markers_DAO markerDAO;
    //private LiveData<List<Markers>> allMarkers;
    private List<Markers> allMarkers;


    public Marker_Repository(Application application) {
        ApplicationDatabase applicationDatabase = ApplicationDatabase.getDatabase(application, new AppExecutors());
        markerDAO = applicationDatabase.markerDAO();
        allMarkers = markerDAO.selectAllMarkers();
    }

    /*LiveData<List<Markers>> getAllMarkers() {
        return allMarkers;
    }*/

    public List<Markers> getAllMarkers() {
        return allMarkers;
    }

    public void insertMarker(Markers markers) {
        new insertAsyncTask(markerDAO).execute(markers);
    }


    private static class insertAsyncTask extends AsyncTask<Markers, Void, Void> {
        private Markers_DAO mAsyncTaskMarkerDAO;

        insertAsyncTask(Markers_DAO markerDAO) {
            mAsyncTaskMarkerDAO = markerDAO;
        }

        @Override
        protected Void doInBackground(Markers... markers) {
            mAsyncTaskMarkerDAO.insertMarker(markers[0]);
            return null;
        }
    }

}