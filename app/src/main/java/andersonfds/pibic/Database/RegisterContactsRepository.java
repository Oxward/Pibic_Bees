package andersonfds.pibic.Database;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;

import andersonfds.pibic.Classes.RegisterContacts;

public class RegisterContactsRepository {

    private RegisterContactsDAO registerContactsDAO;
    private LiveData<List<RegisterContacts>> allContacts;

    //Administra o BD e inicializa as variáveis
    public RegisterContactsRepository(Application application) {
        ApplicationDatabase applicationDatabase = ApplicationDatabase.getDatabase(application);
        registerContactsDAO = applicationDatabase.regConDAO();
        allContacts = registerContactsDAO.selectAllContacts();
    }

    //Retorna todos os itens salvos
    LiveData<List<RegisterContacts>> getAllContacts() {
        return allContacts;
    }

    public void insert(RegisterContacts registerContacts) {
        new insertAsyncTask(registerContactsDAO).execute(registerContacts);
    }

    private static class insertAsyncTask extends AsyncTask<RegisterContacts, Void, Void> {
        private RegisterContactsDAO mAsyncTaskDAO;

        insertAsyncTask(RegisterContactsDAO registerContactsDAO) {
            mAsyncTaskDAO = registerContactsDAO;
        }


        @Override
        protected Void doInBackground(RegisterContacts... registerContacts) {
            mAsyncTaskDAO.insertContacs(registerContacts[0]);
            return null;
        }
    }
}