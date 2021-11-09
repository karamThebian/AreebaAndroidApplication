package com.example.areebaclientcontrolsystem;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface AreebaApi {
    @GET("client/")
    Call<List<Post>> getPosts();


    @GET("client/{field}/{searchfield}")
    Call<List<Post>> getPostsByField(@Path("field") String field,@Path("searchfield") String name);


    @PUT("client/{id}")
    Call<Void> EditClient(
    @Path("id") String id,
    @Query("name") String name,
    @Query("address") String address,
    @Query("phoneNumber") String phoneNumber
    );

    @DELETE("client/{id}")
    Call<Void> deleteClient(@Path("id") String id);

    @POST("client/")
    Call<Void> addClient(@Body Post post);
}
