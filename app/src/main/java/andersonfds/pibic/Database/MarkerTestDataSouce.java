package andersonfds.pibic.Database;

import andersonfds.pibic.Class.MarkerTest;
import andersonfds.pibic.Database.DAO.MarkerTestDAO;
import andersonfds.pibic.Database.Interface.Interface_MarkerTestDataSource;
import io.reactivex.Flowable;

public class MarkerTestDataSouce implements Interface_MarkerTestDataSource {

    private static MarkerTestDataSouce mInstance;
    private MarkerTestDAO mtDAO;

    public MarkerTestDataSouce(MarkerTestDAO mtDAO) {
        this.mtDAO = mtDAO;
    }

    public static MarkerTestDataSouce getInstance(MarkerTestDAO mtDAO) {
        if (mInstance == null) {
            mInstance = new MarkerTestDataSouce(mtDAO);
        }

        return mInstance;
    }

    @Override
    public Flowable<MarkerTest> getMarkerTestByEmail(String email) {
        return mtDAO.getMarkerTestByEmail(email);
    }

    @Override
    public void insertMarker(MarkerTest... markerTests) {
        mtDAO.insertMarker(markerTests);
    }

    @Override
    public void deleteMarker(MarkerTest markerTests) {
        mtDAO.deleteMarker(markerTests);
    }

    @Override
    public void deleteAllMarkers() {
        mtDAO.deleteAllMarkers();
    }
}