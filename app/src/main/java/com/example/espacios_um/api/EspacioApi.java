package com.example.espacios_um.api;



import com.example.espacios_um.modelos.Espacio;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface EspacioApi {
    @POST("api/espacios")
    Call<Espacio> crear(@Body Espacio espacio);

    @GET("api/v1/espacios")
    Call<List<Espacio>> listar();

    @GET("api/v1/espacios/tipo/{tipo}")
    Call<List<Espacio>> listarPorTipo(@Path("tipo") String tipo);

    @GET("api/v1/espacios/{id}")
    Call<Espacio> buscar(@Path("id") Long id);

    @PUT("api/v1/espacios/{id}")
    Call<Espacio> update(@Path("id") Long id, @Body Espacio espacio);

    @DELETE("api/v1/espacios/{id}")
    Call<Espacio> delete(@Path("id") Long id);
}
