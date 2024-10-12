import com.example.dearfutureme.UserLogin
import com.example.dearfutureme.UserRegistration
import retrofit2.Call
import retrofit2.http.*



interface ApiService {

    @POST("register")
    fun registerUser(@Body request: UserRegistration): Call<UserRegistration>

    @POST("login")
    fun loginUser(@Body request: UserLogin): Call<UserLogin>

    @GET("/showName/{user}")
    fun displayName(@Path("name") name: String): Call<UserRegistration>
}
