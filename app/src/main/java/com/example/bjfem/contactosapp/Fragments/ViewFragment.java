package com.example.bjfem.contactosapp.Fragments;


import android.content.Context;
import android.content.DialogInterface;
import android.icu.text.DateFormat;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.bjfem.contactosapp.R;
import com.example.bjfem.contactosapp.Recursos.Contacto;

/**
 * A simple {@link Fragment} subclass.
 */
public class ViewFragment extends Fragment {

    private TextView name;
    private TextView phone;
    private TextView uppername;
    private Button btnBack;
    private Button btnSave;
    private DataCallback callback;
    private Boolean flag = false;
    private Contacto c;
    private Boolean start = false;
    private Button btnDelete;


    public ViewFragment() {
        // Required empty public constructor
    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try{
            callback = (DataCallback) context;
        }catch (Exception e){
            throw new ClassCastException(context.toString() + "should implement DataListener");
        }
    }
// Enabled
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_view, container, false);
        flag = false;
        initViews(view);
        if (start){
            name.setText(c.getNombre());
            phone.setText(c.getTelefono().toString());
            uppername.setText(c.getNombre());
        }

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment nuevoFragmento = new MainFragment();
                android.support.v4.app.FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.biglayout, nuevoFragmento);
                transaction.addToBackStack(null);
                if (this.checkInner()) {
                    if (flag) {
                        String nameToSend = name.getText().toString();
                        Integer phoneToSend = Integer.parseInt(phone.getText().toString());
                        contactCallback(nameToSend, phoneToSend, c);
                    } else {
                        String nameToSend = name.getText().toString();
                        Integer phoneToSend = Integer.parseInt(phone.getText().toString());
                        c = new Contacto(nameToSend, phoneToSend);
                        contactCallback(c);
                    }
                } else {
                    Snackbar.make(view, "Campos obligatorios", Snackbar.LENGTH_SHORT).show();
                }
                flag =false;
                transaction.commit();
            }
            private boolean checkInner() {
                if (TextUtils.isEmpty(name.getText())) {
                    return false;
                } else if (TextUtils.isEmpty(phone.getText())) {
                    return false;
                } else {
                    return true;
                }
            }
        });
        return view;
    }

    public void renderContact(Contacto cont) {
        c = cont;
        start = true;
        flag = true;
    }
    public void initViews(View view){
        btnDelete = view.findViewById(R.id.btn_delete);
        name = view.findViewById(R.id.edt_name);
        phone= view.findViewById(R.id.edt_phone);
        uppername = view.findViewById(R.id.tv_name_fragment);
        btnBack = view.findViewById(R.id.btn_volver);
        btnSave = view.findViewById(R.id.btn_save);
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment nuevoFragmento = new MainFragment();
                android.support.v4.app.FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.biglayout, nuevoFragmento);
                transaction.addToBackStack(null);
                deleteCallback(c);
            }
        });
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainFragment mainfragment = new MainFragment();
                android.support.v4.app.FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.biglayout, mainfragment);
                transaction.commit();
            }
        });
    }


    private void contactCallback(Contacto c){
        callback.dataCall(c);
    }
    private void contactCallback(String name, Integer phone, Contacto c){
        callback.dataCall(name, phone, c);
    }
    private void deleteCallback(Contacto c){
        callback.deleteCall(c);
    }



    public interface DataCallback {
        void dataCall(Contacto c);
        void dataCall(String name, Integer phone, Contacto c);
        void deleteCall(Contacto c);
    }
}
