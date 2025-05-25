package com.example.espacios_um.api;



import com.example.espacios_um.modelos.Reporte;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ReporteApi {
    @POST("api/reportes")
    Call<Reporte> crear(@Body Reporte reporte);

    @GET("api/reportes")
    Call<List<Reporte>> listar();

    @GET("api/reportes/{id}")
    Call<Reporte> buscar(@Path("id") Long id);

    @PUT("api/reportes/{id}")
    Call<Reporte> update(@Path("id") Long id, @Body Reporte reporte);

    @DELETE("api/reportes/{id}")
    Call<Reporte> delete(@Path("id") Long id);
}
