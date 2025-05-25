package com.example.espacios_um.api;

import com.example.espacios_um.modelos.Usuario;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface UsuarioApi {
    @POST("api/usuarios/registro")
    Call<Usuario> registrar(@Body Usuario usuario);

    @POST("api/v1/estudiantes/login")
    Call<Usuario> login(@Body Map<String, String> body);

    @GET("api/usuarios/{id}")
    Call<Usuario> obtenerUsuario(@Path("id") Long id);

    @GET("api/usuarios")
    Call<List<Usuario>> all();

    @PUT("api/usuarios/{id}")
    Call<Usuario> update(@Path("id") Long id, @Body Usuario usuario);

    @DELETE("api/usuarios/{id}")
    Call<Usuario> delete(@Path("id") Long id, @Body Usuario usuario);

}
