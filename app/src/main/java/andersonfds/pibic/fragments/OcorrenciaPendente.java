package andersonfds.pibic.fragments;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

import andersonfds.pibic.R;
import andersonfds.pibic.adapter.ListaAdapter;
import andersonfds.pibic.classes.RegisterContacts;
import andersonfds.pibic.database.RegisterContacts_ViewModel;

public class OcorrenciaPendente extends Fragment {

    private static final String TAG = "OcorrenciaPendente";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ocorrencia_pendente, container, false);

        final ListView mListView = view.findViewById(R.id.listViewOcPend);
        mListView.setOnItemLongClickListener((adapterView, view1, position, id) -> {

            AlertDialog.Builder alert;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                alert = new AlertDialog.Builder(getActivity(), android.R.style.Theme_Material_Dialog_Alert);
            } else {
                alert = new AlertDialog.Builder(getActivity());
            }
            alert.setTitle("Confirmar")
                    .setMessage("Marcar ocorrência como resolvida?")
                    .setPositiveButton(android.R.string.yes, (dialogInterface, i) -> {
                        ((RegisterContacts) mListView.getItemAtPosition(position)).setFoiResolvido(1);
                        Log.d(TAG, "onCreateView: Confirmou");
                    })
                    .setNegativeButton(android.R.string.no, (dialogInterface, i) -> {
                        Log.d(TAG, "onCreateView: Cancelou");
                    })
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show();

            return true;
        });


        ArrayList<RegisterContacts> lista = new ArrayList<>(new RegisterContacts_ViewModel(getActivity().getApplication()).getUnsolved());

        ListaAdapter adapter = new ListaAdapter(getContext(), R.layout.adapter_view_layout, lista);
        mListView.setAdapter(adapter);

        return view;
    }
}

/*
        lista.add(new Registtacts("Andy", "(89) 98181-6457", "(89) 98181-6457"));
        lista.add(new RegisterContacts("Andq", "(89) 98181-6457", "(89) 98181-6457"));
        lista.add(new RegisterContacts("Andw", "(89) 98181-6457", "(89) 98181-6457"));
        lista.add(new RegisterContacts("Ande", "(89) 98181-6457", "(89) 98181-6457"));
        lista.add(new RegisterContacts("Andr", "(89) 98181-6457", "(89) 98181-6457"));
        lista.add(new RegisterContacts("Andt", "(89) 98181-6457", "(89) 98181-6457"));
        lista.add(new RegisterContacts("Andu", "(89) 98181-6457", "(89) 98181-6457"));
        lista.add(new RegisterContacts("Andu", "(89) 98181-6457", "(89) 98181-6457"));
        lista.add(new RegisterContacts("Andu", "(89) 98181-6457", "(89) 98181-6457"));
        lista.add(new RegisterContacts("Andu", "(89) 98181-6457", "(89) 98181-6457"));
        lista.add(new RegisterContacts("Andu", "(89) 98181-6457", "(89) 98181-6457"));
        lista.add(new RegisterContacts("Andu", "(89) 98181-6457", "(89) 98181-6457"));
        lista.add(new RegisterContacts("Andu", "(89) 98181-6457", "(89) 98181-6457"));
        lista.add(new RegisterContacts("Andu", "(89) 98181-6457", "(89) 98181-6457"));
        lista.add(new RegisterContacts("Andu", "(89) 98181-6457", "(89) 98181-6457"));
        lista.add(new RegisterContacts("Andu", "(89) 98181-6457", "(89) 98181-6457"));
        lista.add(new RegisterContacts("Andu", "(89) 98181-6457", "(89) 98181-6457"));
        lista.add(new RegisterContacts("Andu", "(89) 98181-6457", "(89) 98181-6457")); */