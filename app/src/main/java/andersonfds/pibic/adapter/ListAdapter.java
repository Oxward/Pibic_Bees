package andersonfds.pibic.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

import andersonfds.pibic.classes.RegisterContacts;

public class ListAdapter extends ArrayAdapter<RegisterContacts> {

    private static final String TAG = "ListAdapter";

    private Context mContext;
    private int mResource;

    public ListAdapter(Context mContext, int resource, ArrayList<RegisterContacts> mList) {
        super(mContext, resource, mList);
        this.mContext = mContext;
        mResource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        String name = getItem(position).getNome();
        String cont = String.valueOf(getItem(position).getNumTel());
        String wpp = String.valueOf(getItem(position).getNumWpp());
        byte[] img1 = getItem(position).getImg1();
        byte[] img2 = getItem(position).getImg2();

        RegisterContacts pessoa = new RegisterContacts(name, cont, wpp, img1, img2);

        LayoutInflater mLayoutInflater = LayoutInflater.from(mContext);
        convertView = mLayoutInflater.inflate(mResource, parent, false);

        return convertView;
    }
}