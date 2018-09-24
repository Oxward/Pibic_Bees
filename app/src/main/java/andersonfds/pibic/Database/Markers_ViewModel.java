package andersonfds.pibic.Database;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

import java.util.List;

import andersonfds.pibic.Classes.Markers;

public class Markers_ViewModel extends AndroidViewModel {

    private Marker_Repository marker_repository;
    //private LiveData<List<Markers>> allMarkers;
    private List<Markers> allMarkers;


    public Markers_ViewModel(@NonNull Application application) {
        super(application);
        marker_repository = new Marker_Repository(application);
        allMarkers = marker_repository.getAllMarkers();
    }

    /*public LiveData<List<Markers>> getAllMarkers() {
        return allMarkers;
    }*/

    public List<Markers> getAllMarkers() {
        allMarkers.addAll(new selectAsyncTask(marker_repository).doInBackground());
        return allMarkers;
    }

    public void insertMarker(Markers markers) {
        marker_repository.insertMarker(markers);
    }

    private static class selectAsyncTask extends AsyncTask<Void, Void, List<Markers>> {

        private Marker_Repository mAsyncTaskMarkerRep;
        private List<Markers> mAsyncList;

        selectAsyncTask(Marker_Repository marker_repository) {
            mAsyncTaskMarkerRep = marker_repository;
        }

        @Override
        protected List<Markers> doInBackground(Void... voids) {
            mAsyncList.addAll(mAsyncTaskMarkerRep.getAllMarkers());
            return mAsyncList;
        }
    }
}
