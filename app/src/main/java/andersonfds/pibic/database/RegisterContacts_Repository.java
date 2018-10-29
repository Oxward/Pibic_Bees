package andersonfds.pibic.database;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;

import andersonfds.pibic.classes.RegisterContacts;

public class RegisterContacts_Repository {

    private RegisterContacts_DAO registerContactsDAO;
    private LiveData<List<RegisterContacts>> allContacts;

    //Administra o BD e inicializa as variáveis
    public RegisterContacts_Repository(Application application) {
        ApplicationDatabase applicationDatabase = ApplicationDatabase.getDatabase(application, new AppExecutors());
        registerContactsDAO = applicationDatabase.regConDAO();
        allContacts = registerContactsDAO.selectAllContacts();
    }

    //Retorna todos os itens salvos
    LiveData<List<RegisterContacts>> getAllContacts() {
        return allContacts;
    }

    public void insertContact(RegisterContacts registerContacts) {
        new insertAsyncTask(registerContactsDAO).execute(registerContacts);
    }

    private static class insertAsyncTask extends AsyncTask<RegisterContacts, Void, Void> {
        private RegisterContacts_DAO mAsyncTaskRegisterContactsDAO;

        insertAsyncTask(RegisterContacts_DAO registerContactsDAO) {
            mAsyncTaskRegisterContactsDAO = registerContactsDAO;
        }

        @Override
        protected Void doInBackground(RegisterContacts... registerContacts) {
            mAsyncTaskRegisterContactsDAO.insertContacts(registerContacts[0]);
            return null;
        }
    }
}