package easygas.app.com.easygas.Retrofit.Model.AddOrder;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by archi1 on 5/18/2017.
 */

public class MainOrderResponse {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("msg")
    @Expose
    private String msg;

}
