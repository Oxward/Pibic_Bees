package andersonfds.pibic.Database;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import java.util.List;

import andersonfds.pibic.Classes.Markers;

public class Markers_ViewModel extends AndroidViewModel {

    private Marker_Repository marker_repository;
    private LiveData<List<Markers>> allMarkers;


    public Markers_ViewModel(@NonNull Application application) {
        super(application);
        marker_repository = new Marker_Repository(application);
        allMarkers = marker_repository.getAllMarkers();
    }

    public LiveData<List<Markers>> getAllMarkers() {
        return allMarkers;
    }

    public void insertMarker(Markers markers) {
        marker_repository.insertMarker(markers);
    }
}
