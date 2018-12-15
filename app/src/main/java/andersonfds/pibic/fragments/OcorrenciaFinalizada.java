package andersonfds.pibic.fragments;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

import andersonfds.pibic.R;
import andersonfds.pibic.adapter.ListaAdapter;
import andersonfds.pibic.classes.RegisterContacts;

public class OcorrenciaFinalizada extends Fragment {

    private static final String TAG = "OcorrenciaFinalizada";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ocorrencia_finalizada, container, false);

        ListView mListView = view.findViewById(R.id.listViewOcFin);

        //ArrayList<RegisterContacts> lista = new ArrayList<>(new RegisterContacts_ViewModel(getActivity().getApplication()).getSolved());
        ArrayList<RegisterContacts> lista = new ArrayList<>();

        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.imag);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
        byte[] bytes = byteArrayOutputStream.toByteArray();

        lista.add(new RegisterContacts("Otávio", "(89) 98181-4321", "(89) 98181-4321", bytes, bytes));

        ListaAdapter adapter = new ListaAdapter(getContext(), R.layout.adapter_view_layout, lista);
        mListView.setAdapter(adapter);

        return view;
    }
}

/*
        lista.add(new RegisterContacts("Dom pedro primeiro de seilaoque nunes silva camargo freire santos carvalho jorge", "(89) 98181-6457", "(89) 98181-6457"));
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
        */