package andersonfds.pibic.Database;

import android.arch.persistence.room.Database;

import andersonfds.pibic.Classes.RegisterContacts;

import static andersonfds.pibic.Database.RegisterContactsDatabase.DATABASE_VERSION;

@Database(entities = RegisterContacts.class, exportSchema = false, version = DATABASE_VERSION)
public abstract class RegisterContactsDatabase {

    protected static final int DATABASE_VERSION = 1;

    public abstract RegisterContatsDAO regConDAO();
}