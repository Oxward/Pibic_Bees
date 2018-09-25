package andersonfds.pibic.database;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.support.annotation.NonNull;

import java.util.List;

import andersonfds.pibic.classes.Markers;

public class Markers_ViewModel extends AndroidViewModel {

    private Markers_Repository markers_repository;


    public Markers_ViewModel(@NonNull Application application) {
        super(application);
        markers_repository = new Markers_Repository(application);
    }

    /*public LiveData<List<Markers>> getAllMarkers() {
        return allMarkers;
    }*/

    public List<Markers> getAllMarkers() {
        return markers_repository.getAllMarkers();
    }

    public void insertMarker(Markers markers) {
        markers_repository.insertMarker(markers);
    }

}
