package andersonfds.pibic.Database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import andersonfds.pibic.Classes.Marker;

@Dao
public interface MarkerDAO
{

    @Insert
    void addMarker( Marker m );

    @Query( "SELECT * FROM mapMarkers" )
    List<Marker> selectMarkers();

    @Query("SELECT * FROM mapMarkers WHERE emailMarker = :email")
    List<Marker> selectMarkers( String email );

    @Delete
    void deleteMarker( Marker m );

    // @Query( "DELETE FROM mapMarkers WHERE email = :email AND latitude = :lat AND longitude = :lon" )
    // void deleteMarker( String email, float lat, float lon );

}