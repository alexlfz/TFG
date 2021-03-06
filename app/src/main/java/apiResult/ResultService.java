package apiResult;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;

import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ResultService {
    @GET("/v2/competitions/PD/matches/")
    Call<Result> listResult(@Header("X-Auth-Token") String a, @Query("matchday") String j);

    @GET("iniciarsesion1/{email}/{pass}")
    Call<Usuario> iniciarSesion(@Path("email") String email,@Path("pass") String pass);


    @GET("registro/{nombre}/{apellido}/{fechaNac}/{email}/{pass}")
    Call<UsuarioRegistro> registrarUsuario(@Path("nombre") String nombre,
                                           @Path("apellido") String apellido,
                                           @Path("fechaNac") String fechaNac,
                                           @Path("email") String email,
                                           @Path("pass") String pass);

    @GET("listarporpuntos")
    Call<List<UsuarioL>> listarPorPuntos();

    @PUT("restarpuntos/{id}/{puntos}")
    Call<Usuario> comprarTienda(@Path("id") int id,@Path("puntos") int puntos);

    @DELETE("delete/{id}")
    Call<Usuario> eliminarUsuario(@Path("id") int id);

    @GET("cambiarpass/{email}")
    Call<Usuario> cambiarPass(@Path("email") String email);

    @PUT("formularioresetpass/{codigo}/{email}/{pass}")
    Call<Usuario> cambiarContrasenia(@Path("codigo") String codigo,@Path("email") String email,@Path("pass") String pass);
}
