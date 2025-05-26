package com.example.espacios_um.adapters;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.espacios_um.R;
import com.example.espacios_um.modelos.Espacio;
import com.example.espacios_um.modelos.Reserva;

import java.util.List;


public class ReservaAdapter extends ArrayAdapter<Reserva> {

    private OnReservasClickListener listener;

    private  String tipo;
    public ReservaAdapter(Context context, List<Reserva> reservas, OnReservasClickListener listener, String tipo) {
        super(context, 0, reservas);
        this.listener=listener;
        this.tipo=tipo;
    }




    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Reserva reserva = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_espacio, parent, false);
        }
        TextView nombre = convertView.findViewById(R.id.txtNombre);
        TextView capacidad = convertView.findViewById(R.id.txtCapacidad);

        Button horarios =  convertView.findViewById(R.id.btnHorarios);
        if(tipo.equals("Mis reservas")){
            horarios.setText("Cancelar");

        }
        /*
        nombre.setText(r.getNombre());
        capacidad.setText("Capacidad: " + espacio.getCapacidad());
        horarios.setOnClickListener(view -> {
            if (listener != null) {
                listener.onEspacioClick(espacio);
            }
        });

         */

        return convertView;
    }

}

