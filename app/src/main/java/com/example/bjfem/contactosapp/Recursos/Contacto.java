package com.example.bjfem.contactosapp.Recursos;

import android.app.Application;

import com.example.bjfem.contactosapp.Application.MyApplication;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

/**
 * Created by bjfem on 20/10/2017.
 */

public class Contacto extends RealmObject{

    @PrimaryKey
    private int ID;
    @Required
    private String nombre;
    @Required
    private Integer telefono;


    public Contacto(String nombre, Integer telefono) {
        this.ID = MyApplication.ContactoID.incrementAndGet();
        this.nombre = nombre;
        this.telefono = telefono;

    }

    public int getID() {
        return ID;
    }


    public Contacto() {
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getTelefono() {
        return telefono;
    }

    public void setTelefono(Integer telefono) {
        this.telefono = telefono;
    }
}

