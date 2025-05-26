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
import com.example.espacios_um.modelos.Usuario;

import java.util.ArrayList;
import java.util.List;

public class UsuarioAdapter extends ArrayAdapter<Usuario> {

    private OnUsuariosClickListener listener;
    private List<Usuario> originalList;
    private List<Usuario> filteredList;
    private String filtroActual = "Nombre";

    private String tipo;

    @Override
    public int getCount() {
        return filteredList.size();
    }

    @Override
    public Usuario getItem(int position) {
        return filteredList.get(position);
    }

    public UsuarioAdapter(Context context, List<Usuario> usuarios, OnUsuariosClickListener listener, String tipo) {
        super(context, 0, usuarios);
        this.listener = listener;
        this.tipo = tipo;
        this.originalList = new ArrayList<>(usuarios);
        this.filteredList = new ArrayList<>(usuarios);
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Usuario usuario = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_usuario, parent, false);
        }
        TextView nombre = convertView.findViewById(R.id.txtNombre);
        TextView codigo = convertView.findViewById(R.id.txtCodigo);


        Button modificar = convertView.findViewById(R.id.btnModificar);
        Button eliminar = convertView.findViewById(R.id.btnEliminar);

        nombre.setText(usuario.getNombre());
        if ( tipo.equalsIgnoreCase("Conserje")||tipo.equalsIgnoreCase("Profesor")){
            codigo.setText("Identificacion: " + usuario.getIdentificacion());
        }else{
            codigo.setText("Codigo: " + usuario.getCodigo());

        }
        modificar.setOnClickListener(view -> {
            if (listener != null) {
                listener.onModificarUsuario(usuario);
            }
        });

        eliminar.setOnClickListener(view -> {
            if (listener != null) {
                listener.onEliminarUsuario(usuario);
            }

        });

        return convertView;
    }

    public void setFiltro(String filtro) {
        this.filtroActual = filtro;
    }

    public void filtrar(String texto) {
        filteredList.clear();

        if (texto == null || texto.trim().isEmpty()) {
            filteredList.addAll(originalList);
        } else {
            String q = texto.toLowerCase().trim();
            for (Usuario u : originalList) {
                if (filtroActual.equals("Nombre") && u.getNombre().toLowerCase().contains(q)) {
                    filteredList.add(u);
                } else if (filtroActual.equals("Codigo") && u.getCodigo().toLowerCase().contains(q)) {
                    filteredList.add(u);
                }
            }
        }

        notifyDataSetChanged();
    }


    public void filtrar2(String texto) {
        filteredList.clear();

        if (texto == null || texto.trim().isEmpty()) {
            filteredList.addAll(originalList);
        } else {
            String q = texto.toLowerCase().trim();
            for (Usuario u : originalList) {
                if (filtroActual.equals("Nombre") && u.getNombre().toLowerCase().contains(q)) {
                    filteredList.add(u);
                } else if (filtroActual.equals("Identificacion") && u.getIdentificacion().toLowerCase().contains(q)) {
                    filteredList.add(u);
                }
            }
        }

        notifyDataSetChanged();
    }

}