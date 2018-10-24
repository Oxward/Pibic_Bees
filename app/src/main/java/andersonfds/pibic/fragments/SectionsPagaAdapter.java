package andersonfds.pibic.fragments;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class SectionsPagaAdapter extends FragmentPagerAdapter {

    private static final String TAG = "SectionsPagaAdapter";

    public SectionsPagaAdapter(FragmentManager fm) {
        super(fm);
    }

    /**
     * Return the Fragment associated with a specified position.
     *
     * @param position
     */
    @Override
    public Fragment getItem(int position) {
        return null;
    }

    /**
     * Return the number of views available.
     */
    @Override
    public int getCount() {
        return 2;
    }
}