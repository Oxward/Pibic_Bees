package andersonfds.pibic.AsyncTasks;

import android.os.AsyncTask;

import andersonfds.pibic.classes.RegisterContacts;
import andersonfds.pibic.database.RegisterContacts_DAO;

public class InsertAsyncTask_RegisterContacts extends AsyncTask<RegisterContacts, Void, long[]> {

    private static final String TAG = "InsertAsyncTask_RegisterContacts";
    private RegisterContacts_DAO registerContacts_dao;

    public InsertAsyncTask_RegisterContacts(RegisterContacts_DAO registerContacts_dao) {
        this.registerContacts_dao = registerContacts_dao;
    }

    @Override
    protected long[] doInBackground(RegisterContacts... registerContacts) {
        return registerContacts_dao.insertContacts(registerContacts);
    }

    @Override
    protected void onPostExecute(long[] longs) {
        super.onPostExecute(longs);
    }
}
