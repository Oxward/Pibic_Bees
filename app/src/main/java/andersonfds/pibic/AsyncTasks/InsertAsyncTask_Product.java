package andersonfds.pibic.AsyncTasks;

import android.os.AsyncTask;

import andersonfds.pibic.classes.Products;
import andersonfds.pibic.database.Products_DAO;

public class InsertAsyncTask_Product extends AsyncTask<Products, Void, long[]> {

    private static final String TAG = "InsertAsyncTask_Product";
    private Products_DAO products_dao;

    public InsertAsyncTask_Product(Products_DAO products_dao) {
        this.products_dao = products_dao;
    }

    @Override
    protected long[] doInBackground(Products... products) {
        return products_dao.insertProduct(products);
    }

    @Override
    protected void onPostExecute(long[] longs) {
        super.onPostExecute(longs);
    }
}
