package andersonfds.pibic.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import andersonfds.pibic.classes.Markers;

@Dao
public interface Markers_DAO
{

    @Insert
    void insertMarker(Markers m);

    @Query("SELECT * FROM mapMarkers")
        //LiveData<List<Markers>> selectAllMarkers();
    List<Markers> selectAllMarkers();

    @Query("SELECT * FROM mapMarkers WHERE emailMarker = :email")
        //LiveData<List<Markers>> selectMarkersByEmail(String email);
    List<Markers> selectMarkersByEmail(String email);

    @Delete
    void deleteMarker(Markers m);

    // @Query( "DELETE FROM mapMarkers WHERE email = :email AND latitude = :lat AND longitude = :lon" )
    // void deleteMarker( String email, float lat, float lon );

}