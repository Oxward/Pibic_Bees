package andersonfds.pibic.Database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import andersonfds.pibic.Classes.Marker;

@Database( entities = Marker.class, exportSchema = false, version = 1)
public abstract class MarkerDatabase extends RoomDatabase
{

    public abstract MarkerDAO markerDAO();

}