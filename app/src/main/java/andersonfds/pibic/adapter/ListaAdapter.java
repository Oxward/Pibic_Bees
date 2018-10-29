package andersonfds.pibic.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.cache.memory.impl.WeakMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;

import java.util.ArrayList;

import andersonfds.pibic.R;
import andersonfds.pibic.classes.RegisterContacts;

public class ListaAdapter extends ArrayAdapter<RegisterContacts> {

    private static final String TAG = "ListaAdapter";

    private Context mContext;
    private int mResource;
    private int lastPosition = -1;

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        //sets up the image loader
        setupImageLoader();

        String name = getItem(position).getNome();
        String cont = String.valueOf(getItem(position).getNumTel());
        String wpp = String.valueOf(getItem(position).getNumWpp());
        byte[] img1 = getItem(position).getImg1();
        byte[] img2 = getItem(position).getImg2();

        RegisterContacts pessoa = new RegisterContacts(name, cont, wpp, img1, img2);

        final View result;
        ViewHolder mViewHolder;

        if (convertView == null) {
            LayoutInflater mLayoutInflater = LayoutInflater.from(mContext);
            convertView = mLayoutInflater.inflate(mResource, parent, false);

            mViewHolder = new ViewHolder();
            mViewHolder.name = convertView.findViewById(R.id.TVnome);
            mViewHolder.cont = convertView.findViewById(R.id.TVcont);
            mViewHolder.wpp = convertView.findViewById(R.id.TVwpp);
            mViewHolder.img1 = convertView.findViewById(R.id.IVimg1);
            mViewHolder.img2 = convertView.findViewById(R.id.IVimg2);

            result = convertView;
            convertView.setTag(mViewHolder);
        } else {
            mViewHolder = (ViewHolder) convertView.getTag();
            result = convertView;
        }


        Animation mAnimation = AnimationUtils.loadAnimation(mContext,
                (position > lastPosition) ? R.anim.load_down_anim : R.anim.load_up_anim);
        result.startAnimation(mAnimation);
        lastPosition = position;

        int defaultImage = mContext.getResources().getIdentifier("@drawable/image_failed", null, mContext.getPackageName());

        ImageLoader imageLoader = ImageLoader.getInstance();
        DisplayImageOptions options = new DisplayImageOptions.Builder().cacheInMemory(true)
                .cacheOnDisk(true).resetViewBeforeLoading(true)
                .showImageForEmptyUri(defaultImage)
                .showImageOnFail(defaultImage)
                .showImageOnLoading(defaultImage).build();

        imageLoader.displayImage("", mViewHolder.img1, options);
        imageLoader.displayImage("", mViewHolder.img2, options);

        mViewHolder.name.setText(pessoa.getNome());
        mViewHolder.cont.setText(pessoa.getNumTel());
        mViewHolder.wpp.setText(pessoa.getNumWpp());

        return convertView;
    }

    public ListaAdapter(Context mContext, int resource, ArrayList<RegisterContacts> mList) {
        super(mContext, resource, mList);
        this.mContext = mContext;
        mResource = resource;
    }

    private void setupImageLoader() {
        // UNIVERSAL IMAGE LOADER SETUP
        DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder()
                .cacheOnDisk(true).cacheInMemory(true)
                .imageScaleType(ImageScaleType.EXACTLY)
                .displayer(new FadeInBitmapDisplayer(300)).build();

        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(
                getContext())
                .defaultDisplayImageOptions(defaultOptions)
                .memoryCache(new WeakMemoryCache())
                .diskCacheSize(100 * 1024 * 1024).build();

        ImageLoader.getInstance().init(config);
        // END - UNIVERSAL IMAGE LOADER SETUP
    }

    static class ViewHolder {
        TextView name;
        TextView cont;
        TextView wpp;
        ImageView img1;
        ImageView img2;
    }
}