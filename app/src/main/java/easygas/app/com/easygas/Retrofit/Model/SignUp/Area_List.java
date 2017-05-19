package easygas.app.com.easygas.Retrofit.Model.SignUp;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by archi1 on 5/16/2017.
 */

public class Area_List {

    @SerializedName("area_id")
    @Expose
    private String areaId;

    @SerializedName("areaname")
    @Expose
    private String areaName;

    public String getAreaId() {
        return areaId;
    }

    public void setAreaId(String areaId) {
        this.areaId = areaId;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

}
