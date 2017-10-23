package com.example.bjfem.contactosapp.Activities;

import android.app.Dialog;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.example.bjfem.contactosapp.Fragments.MainFragment;
import com.example.bjfem.contactosapp.Fragments.ViewFragment;
import com.example.bjfem.contactosapp.R;
import com.example.bjfem.contactosapp.Recursos.Contacto;
import com.example.bjfem.contactosapp.Adaptadores.MyAdapter;


import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmObject;
import io.realm.RealmResults;

public class MainActivity extends FragmentActivity implements MainFragment.DataListener, ViewFragment.DataCallback{

    private ViewFragment viewfragment;
    private MainFragment mainfragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (findViewById(R.id.mainfragment) == null){
            mainfragment = new MainFragment();
            viewfragment = new ViewFragment();
            android.support.v4.app.FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.biglayout, mainfragment);
            transaction.commit();
        }
    }



    @Override
    public void sendData(Contacto c) {
        viewfragment = new ViewFragment();
        viewfragment.renderContact(c);
        android.support.v4.app.FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.biglayout, viewfragment);
        transaction.commit();


    }

    @Override
    public void dataCall(Contacto c) {
        mainfragment = new MainFragment();
        mainfragment.renderBack(c);
        android.support.v4.app.FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.biglayout, mainfragment);
        transaction.commit();


    }

    @Override
    public void dataCall(String name, Integer phone, Contacto c) {
        mainfragment = new MainFragment();
        mainfragment.renderBack(c, name, phone);
        android.support.v4.app.FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.biglayout, mainfragment);
        transaction.commit();
    }

    @Override
    public void deleteCall(Contacto c) {
        mainfragment = new MainFragment();
        mainfragment.deleteBack(c);
        android.support.v4.app.FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.biglayout, mainfragment);
        transaction.commit();
    }
}
