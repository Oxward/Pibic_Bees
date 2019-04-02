package andersonfds.pibic.database;

import android.arch.lifecycle.LiveData;
import android.content.Context;

import java.util.List;
import java.util.concurrent.ExecutionException;

import andersonfds.pibic.AsyncTasks.InsertAsyncTask_Markers;
import andersonfds.pibic.AsyncTasks.InsertAsyncTask_Product;
import andersonfds.pibic.AsyncTasks.InsertAsyncTask_RegisterContacts;
import andersonfds.pibic.classes.Markers;
import andersonfds.pibic.classes.Products;
import andersonfds.pibic.classes.RegisterContacts;

public class Repository {

    private ApplicationDatabase applicationDatabase;

    public Repository(Context context) {
        applicationDatabase = ApplicationDatabase.getDatabase(context, new AppExecutors());
    }

    public long[] insertProduct(Products products) {
        try {
            return new InsertAsyncTask_Product(applicationDatabase.products_dao()).execute(products).get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            return new long[]{-1};
        }
    }

    public long[] insertRegisterContact(RegisterContacts contacts) {
        try {
            return new InsertAsyncTask_RegisterContacts(applicationDatabase.regConDAO()).execute(contacts).get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            return new long[]{-1};
        }
    }

    public long[] insertMarker(Markers markers) {
        try {
            return new InsertAsyncTask_Markers(applicationDatabase.markerDAO()).execute(markers).get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            return new long[]{-1};
        }
    }

    public LiveData<List<Products>> selectProducts() {
        return applicationDatabase.products_dao().getAllProducts();
    }

    public LiveData<List<RegisterContacts>> selectContacts() {
        return applicationDatabase.regConDAO().selectAllContacts();
    }

    public LiveData<List<RegisterContacts>> selectFinContacts() {
        return applicationDatabase.regConDAO().selectSolved();
    }

    public LiveData<List<RegisterContacts>> selectPendContacts() {
        return applicationDatabase.regConDAO().selectUnsolved();
    }

    public LiveData<List<Markers>> selectMarkers() {
        return applicationDatabase.markerDAO().selectAllMarkers();
    }

}