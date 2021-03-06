package andersonfds.pibic.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

import andersonfds.pibic.R;
import andersonfds.pibic.adapter.ProductsAdapter;
import andersonfds.pibic.adapter.VerticalPadding;
import andersonfds.pibic.classes.Products;
import andersonfds.pibic.database.Repository;
import andersonfds.pibic.interfaces.RecyclerViewClickListener;

public class ProductsActivity extends AppCompatActivity implements RecyclerViewClickListener {

    private static final String TAG = "ProductsActivity";
    private RecyclerView recyclerView;

    private ArrayList<Products> productsArrayList = new ArrayList<>();
    private ProductsAdapter productsAdapter;

    private Repository repository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products);
        recyclerView = findViewById(R.id.productsList);
        repository = new Repository(this);
        setRecyclerView();

        retrieveData();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    private void setRecyclerView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        VerticalPadding verticalPadding = new VerticalPadding(10);
        recyclerView.addItemDecoration(verticalPadding);
        productsAdapter = new ProductsAdapter(productsArrayList, ProductsActivity.this, this);
        recyclerView.setAdapter(productsAdapter);
        recyclerView.setAdapter(productsAdapter);
    }

    private void retrieveData() {
        repository.selectProducts().observe(this, items -> {
            if (items != null && items.size() > 0) {
                productsArrayList.clear();
            }
            if (productsArrayList != null) {
                productsArrayList.addAll(items);
            }
            productsAdapter.notifyDataSetChanged();
        });
    }

    @Override
    public void recyclerViewListItemClicked(int position) {

    }
}