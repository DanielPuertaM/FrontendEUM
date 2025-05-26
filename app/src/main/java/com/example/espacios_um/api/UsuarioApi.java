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
    @POST("api/v1/estudiantes")
    Call<Usuario> registrar(@Body Map<String, String> body);

    @POST("api/v1/estudiantes/login")
    Call<Usuario> login(@Body Map<String, String> body);

    @POST("api/v1/profesores")
    Call<Usuario> registrarProfe(@Body Map<String, String> body);

    @POST("api/v1/conserjes")
    Call<Usuario> registrarConse(@Body Map<String, String> body);
    @POST("api/v1/profesores/login")
    Call<Usuario> loginProfe(@Body Map<String, String> body);

    @POST("api/v1/conserjes/login")
    Call<Usuario> loginConse(@Body Map<String, String> body);

    @GET("api/v1/profesores")
    Call<List<Usuario>> allProfesores();

    @GET("api/v1/conserjes")
    Call<List<Usuario>> allConserjes();

    @GET("api/v1/usuarios/{id}")
    Call<Usuario> obtenerUsuario(@Path("id") Long id);

    @GET("api/v1/estudiantes")
    Call<List<Usuario>> all();

    @PUT("api/v1/estudiantes/{id}")
    Call<Usuario> update(@Path("id") Long id, @Body Usuario usuario);

    @DELETE("api/v1/usuarios/{id}")
    Call<Usuario> delete(@Path("id") Long id, @Body Usuario usuario);

}
