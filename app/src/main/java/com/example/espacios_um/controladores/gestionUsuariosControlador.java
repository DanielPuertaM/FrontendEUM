    package com.example.espacios_um.controladores;

    import android.content.Intent;
    import android.os.Bundle;
    import android.view.View;
    import android.widget.AdapterView;
    import android.widget.ArrayAdapter;
    import android.widget.ListView;
    import android.widget.SearchView;
    import android.widget.Spinner;
    import android.widget.Toast;

    import androidx.activity.EdgeToEdge;
    import androidx.appcompat.app.AppCompatActivity;
    import androidx.core.graphics.Insets;
    import androidx.core.view.ViewCompat;
    import androidx.core.view.WindowInsetsCompat;

    import com.example.espacios_um.R;
    import com.example.espacios_um.adapters.EspacioAdapter;
    import com.example.espacios_um.adapters.OnUsuariosClickListener;
    import com.example.espacios_um.adapters.UsuarioAdapter;
    import com.example.espacios_um.api.ApiClient;
    import com.example.espacios_um.api.EspacioApi;
    import com.example.espacios_um.api.UsuarioApi;
    import com.example.espacios_um.modelos.Espacio;
    import com.example.espacios_um.modelos.Usuario;

    import java.util.List;

    import retrofit2.Call;
    import retrofit2.Callback;
    import retrofit2.Response;

    public class gestionUsuariosControlador extends AppCompatActivity implements OnUsuariosClickListener {


        ListView listUsuarios;

        SearchView shUsuarios;

        UsuarioApi usuarioApi;

        Spinner spinner;




        List<Usuario> usuarios;
        UsuarioAdapter adapter;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            EdgeToEdge.enable(this);
            setContentView(R.layout.activity_gestion_usuarios);
            ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
                Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
                v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
                return insets;
            });



            listUsuarios= findViewById(R.id.listUsuarios);
            shUsuarios= findViewById(R.id.shBuscarUsuario);


            usuarioApi = ApiClient.getRetrofit().create(UsuarioApi.class);
            spinner = findViewById(R.id.spinner);

            estudiantes();








            spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    String seleccion = parent.getItemAtPosition(position).toString();
                    if (adapter != null) {
                        adapter.setFiltro(seleccion);
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) { }
            });




            shUsuarios.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {
                    if (adapter != null) {
                        adapter.filtrar(query);
                    }
                    return false;
                }

                @Override
                public boolean onQueryTextChange(String newText) {
                    adapter.filtrar(newText);
                    return false;
                }
            });


        }

        /**
         * @param usuario
         */
        @Override
        public void onModificarUsuario(Usuario usuario) {
            Intent intent = new Intent(gestionUsuariosControlador.this, ModificarUsuario.class);
            intent.putExtra("usuario_a_modificar", usuario);
            startActivity(intent);
        }

        /**
         * @param usuario
         */
        @Override
        public void onEliminarUsuario(Usuario usuario) {

        }

        private void estudiantes(){

            Call<List<Usuario>> call = usuarioApi.all();
            call.enqueue(new Callback<List<Usuario>>() {
                @Override
                public void onResponse(Call<List<Usuario>> call, Response<List<Usuario>> response) {
                    if (response.isSuccessful() && response.body() != null) {
                        List<Usuario> usuarios1 = response.body();

                        UsuarioAdapter adapter1 = new UsuarioAdapter(gestionUsuariosControlador.this, usuarios1,gestionUsuariosControlador.this,"Estudiante");
                        listUsuarios.setAdapter(adapter1);

                        adapter=adapter1;
                        usuarios=usuarios1;

                    }
                }


                @Override
                public void onFailure(Call<List<Usuario>> call, Throwable t) {
                    Toast.makeText(gestionUsuariosControlador.this, "Error al cargar los usuarios", Toast.LENGTH_SHORT).show();
                }
            });

        }

    }
