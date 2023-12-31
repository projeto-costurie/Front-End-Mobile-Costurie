package br.senai.sp.jandira.costurie_app.service

import br.senai.sp.jandira.costurie_app.model.BaseResponseGivePoint
import br.senai.sp.jandira.costurie_app.model.BaseResponseIdPublication
import br.senai.sp.jandira.costurie_app.model.BaseResponsePopularPublication
import br.senai.sp.jandira.costurie_app.model.BaseResponsePublication
import br.senai.sp.jandira.costurie_app.model.BaseResponseTag
import br.senai.sp.jandira.costurie_app.model.GetPointResponse
import br.senai.sp.jandira.costurie_app.model.GivePointResponse
import br.senai.sp.jandira.costurie_app.model.PublicationGetResponse
import br.senai.sp.jandira.costurie_app.model.PublicationResponse
import br.senai.sp.jandira.costurie_app.model.UserResponse
import com.google.gson.JsonObject
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface PublicationService {

    @Headers("Content-Type: application/json")
    @POST("/publicacao/inserir")
    suspend fun createPublication(
        @Body body: JsonObject,
        @Header("x-access-token") token: String
    ): Response<PublicationResponse>

    @Headers("Content-Type: application/json")
    @GET("/publicacao/select_all")
    suspend fun getAllPublications(
        @Header("x-access-token") token: String
    ): Response<BaseResponsePublication>

    @Headers("Content-Type: application/json")
    @GET("/publicacao/populares")
    suspend fun getPupularPublication(
        @Header("x-access-token") token: String
    ): Response<BaseResponsePopularPublication>

    @Headers("Content-Type: application/json")
    @GET("/publicacao/select_by_id/{id}")
    suspend fun getPublicationById(
        @Header("x-access-token") token: String,
        @Path("id") id: Int
    ): Response<BaseResponseIdPublication>

    @Headers("Content-Type: application/json")
    @DELETE("/publicacao/{id}")
    suspend fun deletePublicationById(
        @Header("x-access-token") token: String,
        @Path("id") id: Int
    ): Response<JsonObject>

    @Headers("Content-Type: application/json")
    @POST("/publicacao/curtir")
    suspend fun givePoint(
        @Header("x-access-token") token: String,
        @Body body: JsonObject
    ): Response<GivePointResponse>

    @Headers("Content-Type: application/json")
    @POST("/publicacao/retirar_curtida")
    suspend fun removePoint(
        @Header("x-access-token") token: String,
        @Body body: JsonObject
    ): Response<GivePointResponse>

    @Headers("Content-Type: application/json")
    @POST("/publicacao/verificar_curtida")
    suspend fun getPoint(
        @Header("x-access-token") token: String,
        @Body body: JsonObject
    ): Response<GetPointResponse>

    @Headers("Content-Type: application/json")
    @PUT("/publicacao/editar_publicacao")
    suspend fun updatePublication(
        @Header("x-access-token") token: String,
        @Body body: JsonObject
    ): Response<JsonObject>

}