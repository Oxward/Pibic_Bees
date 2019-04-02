package andersonfds.pibic.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import andersonfds.pibic.R;
import andersonfds.pibic.adapter.ListaAdapter;
import andersonfds.pibic.adapter.VerticalPadding;
import andersonfds.pibic.classes.RegisterContacts;
import andersonfds.pibic.database.Repository;
import andersonfds.pibic.interfaces.RecyclerViewClickListener;

public class OcorrenciaFinalizada extends Fragment implements RecyclerViewClickListener {

    private static final String TAG = "OcorrenciaFinalizada";
    private RecyclerView recyclerView;

    private ArrayList<RegisterContacts> mContactsArrayList;
    private ListaAdapter listaAdapter;
    private Repository repository;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContactsArrayList = new ArrayList<>();
        repository = new Repository(this.getContext());
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ocorrencia_finalizada, container, false);

        recyclerView = view.findViewById(R.id.ocFinRecycler);
        setRecyclerView();
        retrieveData();

        return view;
    }

    private void setRecyclerView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this.getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        VerticalPadding verticalPadding = new VerticalPadding(10);
        recyclerView.addItemDecoration(verticalPadding);
        listaAdapter = new ListaAdapter(mContactsArrayList, this.getContext(), this);
        recyclerView.setAdapter(listaAdapter);
    }

    private void retrieveData() {
        repository.selectFinContacts().observe(this, items -> {
            if (items != null && items.size() > 0) {
                mContactsArrayList.clear();
            }
            if (mContactsArrayList != null) {
                mContactsArrayList.addAll(items);
            }
            listaAdapter.notifyDataSetChanged();
        });
    }

    @Override
    public void recyclerViewListItemClicked(int position) {

    }
}