package com.example.bjfem.contactosapp.Fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.bjfem.contactosapp.Adaptadores.MyAdapter;
import com.example.bjfem.contactosapp.R;
import com.example.bjfem.contactosapp.Recursos.Contacto;

import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmResults;

/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends Fragment implements RealmChangeListener<RealmResults<Contacto>> {

    private RealmResults<Contacto> names;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private Realm realm;
    private DataListener callback;
    private FragmentManager fragmentManager;


    public MainFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try{
            callback = (DataListener) context;
        }catch (Exception e){
            throw new ClassCastException(context.toString() + "should implement DataListener");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);

        realm= Realm.getDefaultInstance();
        names = realm.where(Contacto.class).findAll();
        if (names.size()<1)  createNewContact(new Contacto("Prueba", 1));
        names.addChangeListener(this);

        mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        mLayoutManager = new LinearLayoutManager(view.getContext());
        mAdapter = new MyAdapter(names, R.layout.recycler_view_item, new MyAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Contacto c, View v) {
                sendContact(c);
            }
        });
        FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ViewFragment viewfragment = new ViewFragment();
               getFragmentManager().beginTransaction().replace(R.id.biglayout, viewfragment).commit();
            }


        });

        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(view.getContext(), DividerItemDecoration.VERTICAL));


        return view;
    }
    public void createNewContact(Contacto c){
        realm.beginTransaction();
        realm.copyToRealmOrUpdate(c);
        realm.commitTransaction();
        mAdapter.notifyDataSetChanged();
    }

    private void editContact(String name, Integer phone, Contacto c) {
        realm.beginTransaction();
        c.setNombre(name);
        c.setTelefono(phone);
        realm.copyToRealmOrUpdate(c);
        realm.commitTransaction();
        mAdapter.notifyDataSetChanged();
    }



    @Override
    public void onChange(RealmResults<Contacto> contactos) {
        mAdapter.notifyDataSetChanged();

    }

    public interface DataListener {
        void sendData(Contacto c);
    }
    private void sendContact(Contacto c){
        callback.sendData(c);
    }



    public void renderBack(Contacto c) {
        createNewContact(c);
    }
    public void renderBack(Contacto c, String name, Integer phone) {
        editContact(name, phone, c);
    }
}
