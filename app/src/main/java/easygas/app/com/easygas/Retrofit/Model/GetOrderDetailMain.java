package easygas.app.com.easygas.Retrofit.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import easygas.app.com.easygas.Retrofit.GasDetail;
import easygas.app.com.easygas.Retrofit.GasDetailMedium;
import easygas.app.com.easygas.Retrofit.GasDetailSmall;

/**
 * Created by archi1 on 5/19/2017.
 */

public class GetOrderDetailMain {
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("msg")
    @Expose
    private String msg;

    @SerializedName("Small")
    @Expose
    private List<GasDetailSmall> smallGasDetails;

    @SerializedName("medium")
    @Expose

    private List<GasDetailMedium> mediumGasDetails;

    public List<GasDetailSmall> getSmallGasDetails() {
        return smallGasDetails;
    }

    public void setSmallGasDetails(List<GasDetailSmall> smallGasDetails) {
        this.smallGasDetails = smallGasDetails;
    }

    public List<GasDetailMedium> getMediumGasDetails() {
        return mediumGasDetails;
    }

    public void setMediumGasDetails(List<GasDetailMedium> mediumGasDetails) {
        this.mediumGasDetails = mediumGasDetails;
    }

    public List<GasDetail> getBigGasDetails() {
        return bigGasDetails;
    }

    public void setBigGasDetails(List<GasDetail> bigGasDetails) {
        this.bigGasDetails = bigGasDetails;
    }

    @SerializedName("big")
    @Expose

    private List<GasDetail> bigGasDetails;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }



}
