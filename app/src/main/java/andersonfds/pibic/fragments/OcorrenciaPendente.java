package andersonfds.pibic.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

import andersonfds.pibic.R;
import andersonfds.pibic.adapter.ListaAdapter;
import andersonfds.pibic.classes.RegisterContacts;

public class OcorrenciaPendente extends Fragment {

    private static final String TAG = "OcorrenciaPendente";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ocorrencia_pendente, container, false);

        ListView mListView = view.findViewById(R.id.listViewOcPend);

        ArrayList<RegisterContacts> lista = new ArrayList<>();
        lista.add(new RegisterContacts("Andy", "(89) 98181-6457", "(89) 98181-6457"));
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
        lista.add(new RegisterContacts("Andu", "(89) 98181-6457", "(89) 98181-6457"));

        ListaAdapter adapter = new ListaAdapter(getContext(), R.layout.adapter_view_layout, lista);
        mListView.setAdapter(adapter);

        return view;
    }
}