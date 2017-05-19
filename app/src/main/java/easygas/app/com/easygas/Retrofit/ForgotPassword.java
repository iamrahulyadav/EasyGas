package easygas.app.com.easygas.Retrofit;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by archi1 on 5/18/2017.
 */

public class ForgotPassword {
    @SerializedName("status")
    @Expose
    private String status;

    @SerializedName("message")
    @Expose
    private String msg;

    @SerializedName("data")
    @Expose
    private DataForgotPassword dataForgotPassword;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public DataForgotPassword getDataForgotPassword() {
        return dataForgotPassword;
    }

    public void setDataForgotPassword(DataForgotPassword dataForgotPassword) {
        this.dataForgotPassword = dataForgotPassword;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
