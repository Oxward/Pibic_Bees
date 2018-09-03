package andersonfds.pibic.Repository;

import andersonfds.pibic.Class.MarkerTest;
import andersonfds.pibic.Interfaces.Interface_MarkerTestDataSource;
import io.reactivex.Flowable;

public class MarkerTestRepository implements Interface_MarkerTestDataSource {

    private static MarkerTestRepository mInstance;
    private Interface_MarkerTestDataSource mLocalDataSource;

    public MarkerTestRepository(Interface_MarkerTestDataSource mLocalDataSource) {
        this.mLocalDataSource = mLocalDataSource;
    }

    public static MarkerTestRepository getInstance(Interface_MarkerTestDataSource mLocalDataSource) {
        if (mInstance == null) {
            mInstance = new MarkerTestRepository(mLocalDataSource);
        }

        return mInstance;
    }

    @Override
    public Flowable<MarkerTest> getMarkerTestByEmail(String email) {
        return mInstance.getMarkerTestByEmail(email);
    }

    @Override
    public void insertMarker(MarkerTest... markerTests) {
        mLocalDataSource.insertMarker(markerTests);
    }

    @Override
    public void deleteMarker(MarkerTest markerTests) {
        mLocalDataSource.deleteMarker(markerTests);
    }

    @Override
    public void deleteAllMarkers() {
        mLocalDataSource.deleteAllMarkers();
    }
}
