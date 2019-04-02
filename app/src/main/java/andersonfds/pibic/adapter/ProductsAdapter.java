package andersonfds.pibic.adapter;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import andersonfds.pibic.R;
import andersonfds.pibic.classes.Products;
import andersonfds.pibic.interfaces.RecyclerViewClickListener;

public class ProductsAdapter extends RecyclerView.Adapter<ProductsAdapter.ViewHolder> {

    private ArrayList<Products> productsArrayList;
    private Context context;
    private RecyclerViewClickListener mRecyclerViewClickListener;

    public ProductsAdapter(ArrayList<Products> productsArrayList, Context context, RecyclerViewClickListener recyclerViewClickListener) {
        this.productsArrayList = productsArrayList;
        this.context = context;
        this.mRecyclerViewClickListener = recyclerViewClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_products, parent, false);
        return new ViewHolder(view, mRecyclerViewClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Products p = productsArrayList.get(holder.getAdapterPosition());
        holder.descProd.setText(p.getDescProd());
        holder.valProd.setText(String.valueOf(p.getValorProd()));
        holder.imgProd.setImageDrawable(new BitmapDrawable(context.getResources(), BitmapFactory.decodeByteArray(p.getImgProd(), 0, p.getImgProd().length)));
    }

    @Override
    public int getItemCount() {
        return productsArrayList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView descProd, valProd;
        ImageView imgProd;

        RecyclerViewClickListener recyclerViewClickListener;

        ViewHolder(@NonNull View itemView, RecyclerViewClickListener listener) {
            super(itemView);
            descProd = itemView.findViewById(R.id.descProd);
            valProd = itemView.findViewById(R.id.valProd);
            imgProd = itemView.findViewById(R.id.imgProd);
            this.recyclerViewClickListener = listener;
        }

        @Override
        public void onClick(View view) {
            recyclerViewClickListener.recyclerViewListItemClicked(getAdapterPosition());
        }
    }

}