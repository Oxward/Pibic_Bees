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

    /*LiveData<List<Markers>> getAllMarkers() {
        return allMarkers;
    }*/

    public List<Markers> getAllMarkers() {
        //allMarkers.addAll(markerDAO.selectAllMarkers());
        //return allMarkers;
        return markerDAO.selectAllMarkers();
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

    /*private static class selectAsyncTask extends AsyncTask<Void, Void, List<Markers>>{

        private List<Markers> mAsyncList;
        private Markers_DAO markers_dao;

        private selectAsyncTask(List<Markers> mAsyncList, Markers_DAO markers_dao) {
            this.mAsyncList = mAsyncList;
            this.markers_dao = markers_dao;
        }

        @Override
        protected List<Markers> doInBackground(Void... voids){
            mAsyncList.addAll(markers_dao.selectAllMarkers());
            return mAsyncList;
        }

        @Override
        protected void onPostExecute(List<Markers> list){

        }
    }*/

}