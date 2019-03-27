package andersonfds.pibic.database;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import andersonfds.pibic.classes.Markers;
import andersonfds.pibic.classes.RegisterContacts;

import static andersonfds.pibic.database.ApplicationDatabase.DATABASE_VERSION;

@Database(entities = {Markers.class, RegisterContacts.class}, exportSchema = false, version = DATABASE_VERSION)
public abstract class ApplicationDatabase extends RoomDatabase {

    private static final String DATABASE_NAME = "pibees_project";
    private static final String TAG = "ApplicationDatabase";
    static final int DATABASE_VERSION = 2;

    private final MutableLiveData<Boolean> mIsDatabaseCreated = new MutableLiveData<>();
    private static volatile ApplicationDatabase INSTANCE;

    static ApplicationDatabase getDatabase(final Context context, final AppExecutors executors) {
        if (INSTANCE == null) {
            synchronized (ApplicationDatabase.class) {
                if (INSTANCE == null) {
                    //BD criado aqui
                    INSTANCE = buildDatabase(context.getApplicationContext(), executors);
                    INSTANCE.updateDatabaseCreated(context.getApplicationContext());
                }
            }
        }
        return INSTANCE;
    }

    private static ApplicationDatabase buildDatabase(final Context context, final AppExecutors executors) {
        return Room.databaseBuilder(context.getApplicationContext(), ApplicationDatabase.class, DATABASE_NAME)
                .addCallback(new Callback() {
                    @Override
                    public void onCreate(@NonNull SupportSQLiteDatabase db) {
                        super.onCreate(db);
                        executors.diskIO().execute(() -> {
                            addDelay();
                            ApplicationDatabase database = ApplicationDatabase.getDatabase(context, executors);
                            database.setDatabaseCreated();
                        });
                    }
                }).fallbackToDestructiveMigration().allowMainThreadQueries().build();
    }

    private static void addDelay() {
        try {
            Thread.sleep(4000);
        } catch (InterruptedException ignored) {
            Log.d(TAG, "addDelay: Thread error.");
        }
    }

    private void updateDatabaseCreated(final Context context) {
        if (context.getDatabasePath(DATABASE_NAME).exists()) {
            setDatabaseCreated();
        }
    }

    private void setDatabaseCreated() {
        mIsDatabaseCreated.postValue(true);
    }

    public LiveData<Boolean> getDatabaseCreated() {
        return mIsDatabaseCreated;
    }

    //Markers DAO
    public abstract Markers_DAO markerDAO();

    //RegisterContacts DAO
    public abstract RegisterContacts_DAO regConDAO();

    public abstract Products_DAO products_dao();

}