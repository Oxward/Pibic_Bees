package andersonfds.pibic.AsyncTasks;

import android.os.AsyncTask;

import andersonfds.pibic.classes.Markers;
import andersonfds.pibic.database.Markers_DAO;

public class InsertAsyncTask_Markers extends AsyncTask<Markers, Void, long[]> {

    private static final String TAG = "InsertAsyncTask_Markers";
    private Markers_DAO markers_dao;

    public InsertAsyncTask_Markers(Markers_DAO markers_dao) {
        this.markers_dao = markers_dao;
    }

    @Override
    protected long[] doInBackground(Markers... markers) {
        return markers_dao.insertMarker(markers);
    }

    @Override
    protected void onPostExecute(long[] longs) {
        super.onPostExecute(longs);
    }
}
