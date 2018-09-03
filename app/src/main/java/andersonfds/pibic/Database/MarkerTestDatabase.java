package andersonfds.pibic.Database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import andersonfds.pibic.Class.MarkerTest;
import andersonfds.pibic.DAO.MarkerTestDAO;

import static andersonfds.pibic.Database.MarkerTestDatabase.DATABASE_VERSION;

/*
 * User Database
 */

@Database(entities = MarkerTest.class, version = DATABASE_VERSION)
public abstract class MarkerTestDatabase extends RoomDatabase {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "PIBICBees";
    private static MarkerTestDatabase mInstance;

    public static MarkerTestDatabase getInstance(Context context) {
        if (mInstance == null) {
            mInstance = Room.databaseBuilder(context, MarkerTestDatabase.class, DATABASE_NAME)
                    .fallbackToDestructiveMigration()
                    .build();
        }

        return mInstance;
    }

    public abstract MarkerTestDAO mtDao();

}