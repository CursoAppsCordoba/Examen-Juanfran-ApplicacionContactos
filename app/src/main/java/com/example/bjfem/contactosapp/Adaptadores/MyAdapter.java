package com.example.bjfem.contactosapp.Adaptadores;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.bjfem.contactosapp.R;
import com.example.bjfem.contactosapp.Recursos.Contacto;

import java.util.List;

/**
 * Created by bjfem on 20/10/2017.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder>{

    private List<Contacto> names;
    private int layout;
    private OnItemClickListener listener;

    public MyAdapter(List<Contacto> names, int layout, OnItemClickListener listener) {
        this.names = names;
        this.layout = layout;
        this.listener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(layout, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bind(names.get(position), listener);
    }

    @Override
    public int getItemCount() {
        return names.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView name;
        public TextView phone;


        public ViewHolder(View itemView) {
            super(itemView);
            this.name = (TextView) itemView.findViewById(R.id.tv_name);
            this.phone = (TextView) itemView.findViewById(R.id.tv_phone);

        }
        public void bind(final Contacto c, final OnItemClickListener listener) {
            this.name.setText(c.getNombre());
            this.phone.setText(c.getTelefono().toString());

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onItemClick(c, view);
                }
            });
        }
    }



    public interface OnItemClickListener {
        void onItemClick(Contacto c, View v);
    }

}
