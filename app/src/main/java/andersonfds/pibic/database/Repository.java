package andersonfds.pibic.database;

import android.arch.lifecycle.LiveData;
import android.content.Context;

import java.util.List;
import java.util.concurrent.ExecutionException;

import andersonfds.pibic.AsyncTasks.InsertAsyncTask;
import andersonfds.pibic.classes.Products;

public class Repository {

    private ApplicationDatabase applicationDatabase;

    public Repository(Context context) {
        applicationDatabase = ApplicationDatabase.getDatabase(context, new AppExecutors());
    }

    public long[] insertProduct(Products products) {
        try {
            return new InsertAsyncTask(applicationDatabase.products_dao()).execute(products).get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            return new long[]{-1};
        }
    }

    public LiveData<List<Products>> selectProducts() {
        return applicationDatabase.products_dao().getAllProducts();
    }

}