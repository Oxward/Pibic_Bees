package andersonfds.pibic.database;

import android.app.Application;
import android.os.AsyncTask;

import java.util.List;

import andersonfds.pibic.classes.Markers;

public class Markers_Repository {

    private Markers_DAO markerDAO;
    //private List<Markers> allMarkers;

    Markers_Repository(Application application) {
        ApplicationDatabase applicationDatabase = ApplicationDatabase.getDatabase(application, new AppExecutors());
        markerDAO = applicationDatabase.markerDAO();
        //allMarkers = new ArrayList<>();
    }

    public List<Markers> getAllMarkers() {
        return markerDAO.selectAllMarkers();
    }

    public void insertMarker(Markers markers) {
        new insertAsyncTask(markerDAO).execute(markers);
    }

    public void deleteMarker(Markers markers) {
        new deleteAsyncTask(markerDAO).execute(markers);
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

    private static class deleteAsyncTask extends AsyncTask<Markers, Void, Void> {

        private Markers_DAO mAsyncTaskMarkerDAO;

        private deleteAsyncTask(Markers_DAO mAsyncTaskMarkerDAO) {
            this.mAsyncTaskMarkerDAO = mAsyncTaskMarkerDAO;
        }

        @Override
        protected Void doInBackground(Markers... markers) {
            mAsyncTaskMarkerDAO.deleteMarker(markers[0].getLatitude(), markers[0].getLongitude());
            return null;
        }

    }

}