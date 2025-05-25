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
import com.example.espacios_um.modelos.Horario;

import java.util.List;

public class HorariosAdapter extends ArrayAdapter<Horario> {

    private  OnHorarioClickListener listener;

    private  String tipo;
    public HorariosAdapter(Context context, List<Horario> horarios, OnHorarioClickListener listener, String tipo) {
        super(context, 0, horarios);
        this.listener=listener;
        this.tipo=tipo;
    }




    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Horario horario = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_horario, parent, false);
        }
        TextView horaInicio = convertView.findViewById(R.id.txtHorarioInicio);
        TextView horaFinal = convertView.findViewById(R.id.txtHorarioFinal);

        Button reservar=  convertView.findViewById(R.id.btnReservar);

        if(tipo.equals("Mis reservas")){
            reservar.setText("Cancelar");
        }

        horaInicio.setText(String.valueOf(horario.getHoraInicio()));
        horaFinal.setText(String.valueOf(horario.getHoraFinal()));

        reservar.setOnClickListener(view -> {
            if(listener != null) {
                if(tipo.equals("Mis reservas")) {
                    listener.onHorarioCancelarClick(horario);
                }else{
                    listener.onHorarioClick(horario);
                }
            }
        });
        return convertView;
    }

}
