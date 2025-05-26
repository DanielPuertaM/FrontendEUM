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

import java.util.List;


public class EspacioAdapter extends ArrayAdapter<Espacio> {

    private OnEspaciosClickListener listener;

    private  String tipo;
    public EspacioAdapter(Context context, List<Espacio> espacios, OnEspaciosClickListener listener, String tipo) {
        super(context, 0, espacios);
        this.listener=listener;
        this.tipo=tipo;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Espacio espacio = getItem(position);
        if (convertView == null) {
            if (tipo.equals("Conserje")) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_reportar_espacio, parent, false);
            } else {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_espacio, parent, false);
            }
        }

        TextView nombre = convertView.findViewById(R.id.txtNombre);
        TextView capacidad = convertView.findViewById(R.id.txtCapacidad);

        nombre.setText(espacio.getNombre());
        capacidad.setText("Capacidad: " + espacio.getCapacidad());

        if (tipo.equals("Conserje")) {
            Button reportar= convertView.findViewById(R.id.btnReportar);
            reportar.setOnClickListener(view -> {
                if (listener != null){
                    listener.onEspacioReportarClick(espacio);
                }

            });
        } else {
            Button horarios =  convertView.findViewById(R.id.btnHorarios);
            horarios.setOnClickListener(view -> {
                if (listener != null) {
                    listener.onEspacioClick(espacio);
                }
            });
        }
        return convertView;
    }
}
