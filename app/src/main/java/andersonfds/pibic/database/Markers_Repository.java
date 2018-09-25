package andersonfds.pibic.database;

import android.app.Application;
import android.os.AsyncTask;
import android.widget.Toast;

import java.util.List;

import andersonfds.pibic.ApplicationContextProvider;
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

    private static class deleteAsyncTask extends AsyncTask<Markers, Void, Boolean> {

        private Markers_DAO mAsyncTaskMarkerDAO;

        private deleteAsyncTask(Markers_DAO mAsyncTaskMarkerDAO) {
            this.mAsyncTaskMarkerDAO = mAsyncTaskMarkerDAO;
        }

        @Override
        protected Boolean doInBackground(Markers... markers) {
            return (mAsyncTaskMarkerDAO.deleteMarker(markers[0]));
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            if (aBoolean)
                Toast.makeText(ApplicationContextProvider.getContext(), "Excluído com sucesso.",
                        Toast.LENGTH_SHORT).show();
            else
                Toast.makeText(ApplicationContextProvider.getContext(), "Erro ao remover marcador.",
                        Toast.LENGTH_SHORT).show();
        }
    }

}