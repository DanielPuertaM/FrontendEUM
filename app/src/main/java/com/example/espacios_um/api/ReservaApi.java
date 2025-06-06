package com.example.espacios_um.api;



import com.example.espacios_um.modelos.Reserva;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ReservaApi {
    @POST("api/v1/reservas")
    Call<Reserva> crear(@Body Map<String, String> body);

    @GET("api/v1/reservas")
    Call<List<Reserva>> listar();

    @GET("api/v1/reservas/{id}")
    Call<Reserva> buscar(@Path("id") Long id);

    @GET("api/v1/reservas/estudiante/{id}")
    Call<List<Reserva>>  buscarPorIdEstudiante(@Path("id") Long id);

    @PUT("api/v1/reservas/{id}")
    Call<Reserva> update(@Path("id") Long id, @Body Reserva reserva);

    @DELETE("api/v1/reservas/{id}")
    Call<Reserva> delete(@Path("id") Long id);
}
