package com.example.espacios_um.api;



import com.example.espacios_um.modelos.Espacio;

import java.util.List;
import java.util.Map;
import java.util.Vector;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface EspacioApi {
    @POST("api/v1/espacios")
    Call<Espacio> crear(@Body Map<String, String> body);

    @GET("api/v1/espacios")
    Call<List<Espacio>> listar();

    @GET("api/v1/espacios/tipo/{tipo}")
    Call<List<Espacio>> listarPorTipo(@Path("tipo") String tipo);

    @GET("api/v1/espacios/{id}")
    Call<Espacio> buscar(@Path("id") Long id);

    @PUT("api/v1/espacios/{id}")
    Call<Espacio> update(@Path("id") Long id, @Body Map<String, String> body);

    @DELETE("api/v1/espacios/{id}")
    Call<Void> delete(@Path("id") Long id);


}
