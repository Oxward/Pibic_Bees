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
import andersonfds.pibic.classes.RegisterContacts;
import andersonfds.pibic.interfaces.RecyclerViewClickListener;

public class ListaAdapter extends RecyclerView.Adapter<ListaAdapter.ViewHolder> {

    private static final String TAG = "ListaAdapter";

    private ArrayList<RegisterContacts> mContactsArrayList;
    private Context mContext;
    private RecyclerViewClickListener mRecyclerViewClickListener;

    private int mResource;
    private int lastPosition = -1;

    public ListaAdapter(ArrayList<RegisterContacts> mContactsArrayList, Context mContext, RecyclerViewClickListener mRecyclerViewClickListener) {
        this.mContactsArrayList = mContactsArrayList;
        this.mContext = mContext;
        this.mRecyclerViewClickListener = mRecyclerViewClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_view_layout, parent, false);
        return new ViewHolder(view, mRecyclerViewClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        RegisterContacts rc = mContactsArrayList.get(holder.getAdapterPosition());
        holder.name.setText(rc.getNome());
        holder.cont.setText(rc.getNumTel());
        holder.wpp.setText(rc.getNumWpp());
        holder.img1.setImageDrawable(new BitmapDrawable(mContext.getResources(), BitmapFactory.decodeByteArray(rc.getImg1(), 0, rc.getImg1().length)));
        holder.img2.setImageDrawable(new BitmapDrawable(mContext.getResources(), BitmapFactory.decodeByteArray(rc.getImg2(), 0, rc.getImg2().length)));
    }

    @Override
    public int getItemCount() {
        return mContactsArrayList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView name;
        TextView cont;
        TextView wpp;
        ImageView img1;
        ImageView img2;

        RecyclerViewClickListener recyclerViewClickListener;

        ViewHolder(@NonNull View itemView, RecyclerViewClickListener listener) {
            super(itemView);
            name = itemView.findViewById(R.id.TVnome);
            cont = itemView.findViewById(R.id.TVcont);
            wpp = itemView.findViewById(R.id.TVwpp);
            img1 = itemView.findViewById(R.id.IVimg1);
            img2 = itemView.findViewById(R.id.IVimg2);
            this.recyclerViewClickListener = listener;
        }

        @Override
        public void onClick(View view) {
            recyclerViewClickListener.recyclerViewListItemClicked(getAdapterPosition());
        }
    }
}