package andersonfds.pibic.database;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import java.util.List;

import andersonfds.pibic.classes.RegisterContacts;

public class RegisterContacts_ViewModel extends AndroidViewModel {

    private RegisterContacts_Repository registerContacts_repository;
    private LiveData<List<RegisterContacts>> allContacts;

    public RegisterContacts_ViewModel(@NonNull Application application) {
        super(application);
        registerContacts_repository = new RegisterContacts_Repository(application);
        allContacts = registerContacts_repository.getAllContacts();
    }

    public LiveData<List<RegisterContacts>> getAllContacts() {
        return allContacts;
    }

    public void insertContact(RegisterContacts registerContacts) {
        registerContacts_repository.insertContact(registerContacts);
    }
}