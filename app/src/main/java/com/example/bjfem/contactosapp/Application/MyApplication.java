package com.example.bjfem.contactosapp.Application;

import android.app.Application;

import com.example.bjfem.contactosapp.Recursos.Contacto;

import java.util.concurrent.atomic.AtomicInteger;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmObject;
import io.realm.RealmResults;

/**
 * Created by bjfem on 22/10/2017.
 */

public class MyApplication extends Application{

    public static AtomicInteger ContactoID = new AtomicInteger();

    @Override
    public void onCreate() {
        super.onCreate();
        setRealmDefault();

        Realm realm = Realm.getDefaultInstance();
        ContactoID = getIdByTable(realm, Contacto.class);
        realm.close();
    }

    private void setRealmDefault(){
        Realm.init(getApplicationContext());

        RealmConfiguration config = new RealmConfiguration
                        .Builder()
                        .deleteRealmIfMigrationNeeded()
                .build();
        Realm.setDefaultConfiguration(config);
    }



    private <T extends RealmObject> AtomicInteger getIdByTable(Realm realm, Class<T> anyClass){
        RealmResults<T> results = realm.where(anyClass).findAll();
        return (results.size() > 0) ? new AtomicInteger(results.max("ID").intValue()) : new AtomicInteger();
    }
}
