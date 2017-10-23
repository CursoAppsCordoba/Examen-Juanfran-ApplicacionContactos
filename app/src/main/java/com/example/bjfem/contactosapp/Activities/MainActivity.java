package com.example.bjfem.contactosapp.Activities;

import android.app.Dialog;
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

    }



    @Override
    public void sendData(Contacto c) {
        viewfragment = (ViewFragment) getSupportFragmentManager().findFragmentById(R.id.fragmentview);
        viewfragment.renderContact(c);
    }

    @Override
    public void dataCall(Contacto c) {
        mainfragment = (MainFragment) getSupportFragmentManager().findFragmentById(R.id.mainfragment);
        mainfragment.renderBack(c);


    }

    @Override
    public void dataCall(String name, Integer phone, Contacto c) {
        mainfragment = (MainFragment) getSupportFragmentManager().findFragmentById(R.id.mainfragment);
        mainfragment.renderBack(c, name, phone);

    }
}
