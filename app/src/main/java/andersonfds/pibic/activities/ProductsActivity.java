package andersonfds.pibic.activities;

import android.content.Context;
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

    private Context context;
    private Repository repository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products);
        recyclerView = findViewById(R.id.productsList);
        repository = new Repository(this);
        setRecyclerView();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void recyclerViewListItemClicked(int position) {

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

}