package andersonfds.pibic.database;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import andersonfds.pibic.classes.Markers;

@Dao
public interface Markers_DAO
{

    @Insert
    long[] insertMarker(Markers... m);

    @Query("SELECT * FROM mapMarkers")
    LiveData<List<Markers>> selectAllMarkers();

    @Query("SELECT * FROM mapMarkers WHERE emailMarker = :email")
    LiveData<List<Markers>> selectMarkersByEmail(String email);

    @Query("DELETE FROM mapMarkers WHERE latMarker = :lat AND lonMarker = :lon")
    void deleteMarker(double lat, double lon);

    // @Query( "DELETE FROM mapMarkers WHERE email = :email AND latitude = :lat AND longitude = :lon" )
    // void deleteMarker( String email, float lat, float lon );

}