package andersonfds.pibic.Database;

import android.app.Application;
import android.os.AsyncTask;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import andersonfds.pibic.Classes.Markers;

public class Markers_Repository {

    private Markers_DAO markerDAO;
    private List<Markers> allMarkers;

    Markers_Repository(Application application) {
        ApplicationDatabase applicationDatabase = ApplicationDatabase.getDatabase(application, new AppExecutors());
        markerDAO = applicationDatabase.markerDAO();
        allMarkers = new ArrayList<>();
    }

    /*LiveData<List<Markers>> getAllMarkers() {
        return allMarkers;
    }*/

    public List<Markers> getAllMarkers() {
        allMarkers.addAll(markerDAO.selectAllMarkers());
        //new Thread( new Task() ).start();
        return allMarkers;
    }

    public void insertMarker(Markers markers) {
        new insertAsyncTask(markerDAO).execute(markers);
    }

    private class Task implements Runnable {

        private static final String TAG = "Task";

        @Override
        public void run() {
            allMarkers.addAll(markerDAO.selectAllMarkers());
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                Log.d(TAG, "run: Deu pau." + e.getMessage());
            }
        }
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