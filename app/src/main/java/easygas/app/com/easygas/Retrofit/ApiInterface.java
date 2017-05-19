package easygas.app.com.easygas.Retrofit;

import java.util.Map;

import easygas.app.com.easygas.Retrofit.Model.GetOrderDetailMain;
import easygas.app.com.easygas.Retrofit.Model.ResiterMainStatus;
import easygas.app.com.easygas.Retrofit.Model.SignUp.AreaMainStatus;
import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiInterface {

    @FormUrlEncoded
    @POST("api/login_new.php")
    Call<LoginMainStatus> getLogin(@FieldMap Map<String, String> map);

    @GET("api/get_city_areas.php")
    Call<AreaMainStatus> getArea();

    @FormUrlEncoded
    @POST("api/register.php")
    Call<ResiterMainStatus> getregister(@FieldMap Map<String, String> map);

    @FormUrlEncoded
    @POST("api/Forgot_password.php")
    Call<ForgotPassword> forgotpassword(@FieldMap Map<String, String> map);

    @FormUrlEncoded
    @POST("api/reset_password.php")
    Call<ChangePasswordResponse> changepassword(@FieldMap Map<String, String> map);

    @GET("api/get_gas_category.php")
    Call<GetOrderDetailMain> getOrderDetail();

}