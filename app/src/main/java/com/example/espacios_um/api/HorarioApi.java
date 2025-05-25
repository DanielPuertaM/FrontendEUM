package com.example.espacios_um.api;



import com.example.espacios_um.modelos.Horario;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface HorarioApi{

    @POST("api/horarios")
    Call<Horario> crear(@Body Horario horario);

    @GET("api/horarios")
    Call<List<Horario>> listar();

    @GET("api/v1/horarios/espacio/{id}")
    Call<List<Horario>> listarPorId(@Path("id") Long id);

    @GET("api/horarios/{id}")
    Call<Horario> get(@Path("id") Long id);

    @PUT("api/horarios/{id}")
    Call<List<Horario>> update(@Path("id") Long id, @Body Horario horario);

    @DELETE("api/horarios/{id}")
    Call<List<Horario>> delete(@Path("id") Long id);
}
