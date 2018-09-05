package andersonfds.pibic.Database.Interface;

import andersonfds.pibic.Class.MarkerTest;
import io.reactivex.Flowable;

public interface Interface_MarkerTestDataSource {
    Flowable<MarkerTest> getMarkerTestByEmail(String email);

    void insertMarker(MarkerTest... markerTests);

    void deleteMarker(MarkerTest markerTests);

    void deleteAllMarkers();
}