package easygas.app.com.easygas.Retrofit;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by archi1 on 5/17/2017.
 */

public class DataForgotPassword {
    @SerializedName("code")
    @Expose
    private String code;

    @SerializedName("status")
    @Expose
    private String status_code;


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getStatus_code() {
        return status_code;
    }

    public void setStatus_code(String status_code) {
        this.status_code = status_code;
    }
}
